package accounting.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import accounting.biz.pub.AcLnRepayPlnBiz;
import accounting.biz.pub.FeeBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.fee.AcChrgLog;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnRepayPln;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.loan.AcTraceLog;
import accounting.domain.loan.PrdtBase;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.core.init.BusinessInitializer;
import accounting.plat.dao.JdbcDao;
import app.creditapp.aft.entity.AftExp;
import app.util.DateUtil;

/**
 * 日终进行展期交易
 * 
 * 
 */
public class LoanExpandBatch extends Batch {
	private final Logger log = Logger.getLogger(LoanExpandBatch.class);
	
	public boolean doBatch(String startOrg, String endOrg) {
		String step = "7";// 第7步

		boolean batchFlag = false;
		Connection conn = null; // 数据库连接
		try {
			conn = this.getConnection(); // 获取连接

			String batchDt = "";// 当前批量日期
			PreparedStatement selectSysGlobalPst = conn.prepareStatement("select sys_date from sys_global ");

			ResultSet selectSysGlobalRs = selectSysGlobalPst.executeQuery();
			if (selectSysGlobalRs.next()) {
				batchDt = selectSysGlobalRs.getString("sys_date");
			}
			String batchStp = "";// 当前批量成功运行步骤
			PreparedStatement selectAcComSysParmPst = conn
					.prepareStatement("select batch_stp from ac_com_sys_parm where batch_dt='" + batchDt + "'");

			ResultSet selectAcComSysParmRs = selectAcComSysParmPst.executeQuery();
			if (selectAcComSysParmRs.next()) {
				batchStp = selectAcComSysParmRs.getString("batch_stp");
			}

			// 若批量步骤小于7则说明当前该步骤还未处理
			if (Integer.parseInt(batchStp) < Integer.parseInt(step)) {

				String traceNo = ParmBiz.getTraceNo(conn); // 获取交易流水号

				String txDt = this.getTxDt(conn); // 得到当前营业日期

				String aftExpSql = "SELECT * FROM aft_exp WHERE beg_date='" + txDt + "' AND exp_sts ='02' ";
				log.info("批量[展期]SQL=="+aftExpSql);
				PreparedStatement aftExpPs = conn.prepareStatement(aftExpSql);

				ResultSet aftExpRs = aftExpPs.executeQuery();

				while (aftExpRs.next()) {
					String pactNo = aftExpRs.getString("PACT_NO"); // 合同号
					String brNo = aftExpRs.getString("BR_NO");// 机构号
					// 查询贷款主表信息
					AcLnMst acLnMst = (AcLnMst) JdbcDao.query(new AcLnMst(), "pact_no='" + pactNo + "' and br_no='" + brNo + "'", "ac_ln_mst", conn);

					AftExp aftExp = this.parseAftExp(aftExpRs);
					// 获得核算参数表
					PrdtBase prdtBase = PUBConstant.PUB_ACCOUNT_PRAM.get(acLnMst.getPrdtNo());

					OperaInfo operaInfo = new OperaInfo(conn);
					operaInfo.setTxBrNo(aftExp.getBrNo()); // 核算交易机构号
					operaInfo.setTxDt(aftExp.getBegDate()); // 系统交易日期
					operaInfo.setTraceNo(traceNo); // 交易流水号

					acLnMst.setMtrDt(aftExp.getEndDate()); // 到期日期
					acLnMst.setLnRate(aftExp.getExpRate()); // 展期利率

					if ("A".equals(acLnMst.getBrAccType())) {
						// 新贷款主文件，用于生成新还款计划
						AcLnMst newAcLnMst = new AcLnMst();
						newAcLnMst = acLnMst;
						newAcLnMst.setOpnDt(aftExp.getBegDate());
						// 重新计算后半期贷款期限，用于生成新还款计划
						long[] terms = DateUtil.getMonthsAndDays(aftExp.getBegDate(), acLnMst.getMtrDt());
						int month = (int) terms[0];
						if (terms[1] > 0) {
							month += 1;
						}
						acLnMst.setLoanTerm(month);

						List<AcLnRepayPln> acLnRepayPlnList = AcLnRepayPlnBiz.getAcLnRepayPln(acLnMst, prdtBase, operaInfo);

						AcLnRepayPlnCur alrpc = (AcLnRepayPlnCur) JdbcDao.query(new AcLnRepayPlnCur(), "loan_no='" + acLnMst.getLoanNo() + "'", "ac_ln_repay_pln_cur", conn);

						// 新生成的还款计划期次从1开始，需改为从原还款计划最后一期开始
						for (int i = 0; i < acLnRepayPlnList.size(); i++) {
							acLnRepayPlnList.get(i).setPerdNo(acLnRepayPlnList.get(i).getPerdNo() + alrpc.getPerdNo());
						}

						AcLnRepayPlnCur newAcLnRepayPlnCur = AcLnRepayPlnBiz.getAcLnRepayPlnCur(acLnRepayPlnList.get(0), acLnMst, operaInfo.getTxDt());

						// 生成费用信息--展期服务费
						List<AcChrgLog> acChrgLogList = new ArrayList<AcChrgLog>();
						acChrgLogList = FeeBiz.getAcChrgLogListFk(operaInfo.getTraceCnt(), acLnMst.getLoanNo(),
								acLnMst.getPrdtNo(), "LN06", aftExp.getExpAmt(), acLnMst.getCurNo(), "", operaInfo);

						// 更换当期还款计划
						JdbcDao.delete("loan_no='" + acLnMst.getLoanNo() + "'", "ac_ln_repay_pln_cur", operaInfo.getConn());
						JdbcDao.insert(newAcLnRepayPlnCur, "ac_ln_repay_pln_cur", operaInfo.getConn());

						// 新增展期后还款计划
						JdbcDao.insertList(acLnRepayPlnList, "ac_ln_repay_pln", operaInfo.getConn());

						// 新增展期手续费
						if (acChrgLogList.size() > 0) {
							JdbcDao.insertList(acChrgLogList, "ac_chrg_log", operaInfo.getConn());
						}
					}
					// 更新贷款主文件
					JdbcDao.update(acLnMst, "loan_no='" + acLnMst.getLoanNo() + "'", "AC_LN_MST", conn);

					// 更新贷后展期申请表
					aftExp.setExpSts("03");// 更改状态为已展期
					JdbcDao.update(aftExp, "exp_id='" + aftExp.getExpId() + "'", "AFT_EXP", conn);

					// 获取交易流水
					AcTraceLog acTraceLog = new AcTraceLog();
					acTraceLog.setTraceNo(traceNo);
					acTraceLog.setTraceCnt(1);
					acTraceLog.setTxDt(txDt);
					acTraceLog.setTxTime(ParmBiz.getOracleTxTime(conn));
					acTraceLog.setTxCde("LN06");
					acTraceLog.setSubTxCde("LN06");
					acTraceLog.setSvcInd("LN06");
					acTraceLog.setCurNo(acLnMst.getCurNo());
					acTraceLog.setPrdtNo(acLnMst.getPrdtNo());
					acTraceLog.setBrNo(acLnMst.getBrNo());
					acTraceLog.setPactNo(acLnMst.getPactNo());
					acTraceLog.setLoanNo(acLnMst.getLoanNo());
					acTraceLog.setAmt(aftExp.getExpAmt());
					acTraceLog.setMemo("批量展期");
					JdbcDao.insert(acTraceLog, "ac_trace_log", conn);
				}
				aftExpPs.close();
				aftExpRs.close();

				Statement updateAcComSysParmSt2 = conn.createStatement();
				String updateAcComSysParmSql2 = "update AC_COM_SYS_PARM set batch_dt='" + batchDt + "',batch_stp='"
						+ step + "'";
				updateAcComSysParmSt2.executeUpdate(updateAcComSysParmSql2);
				updateAcComSysParmSt2.close();

				conn.commit();

			} else {
				System.out.println(batchDt + "批量步骤7已执行");
				log.info("批量[展期]已执行");
			}

			selectSysGlobalPst.close();
			selectSysGlobalRs.close();
			selectAcComSysParmPst.close();
			selectAcComSysParmRs.close();

			batchFlag = true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			error(e);
			log.error("展期处理失败："+e.getMessage(),e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return batchFlag;
	}

	public AftExp parseAftExp(ResultSet aftExpRs) throws SQLException {
		AftExp aftExp = new AftExp();
		aftExp.setExpId(aftExpRs.getString("EXP_ID"));
		aftExp.setPactId(aftExpRs.getString("PACT_ID"));
		aftExp.setPactNo(aftExpRs.getString("PACT_NO"));
		aftExp.setBrNo(aftExpRs.getString("BR_NO"));
		aftExp.setBegDate(aftExpRs.getString("BEG_DATE"));
		aftExp.setEndDate(aftExpRs.getString("END_DATE"));
		aftExp.setExpRate(aftExpRs.getDouble("EXP_RATE"));
		aftExp.setExpAmt(aftExpRs.getDouble("EXP_AMT"));
		aftExp.setExpReason(aftExpRs.getString("EXP_REASON"));
		aftExp.setExpSts(aftExpRs.getString("EXP_STS"));
		aftExp.setOpNo(aftExpRs.getString("OP_NO"));
		aftExp.setTxDate(aftExpRs.getString("TX_DATE"));
		aftExp.setUpDate(aftExpRs.getString("UP_DATE"));
		return aftExp;
	}

	public static void main(String[] args) throws Exception {
		BusinessInitializer buz = new BusinessInitializer();
		buz.initialize();
		LoanExpandBatch loanExpandBatch = new LoanExpandBatch();
		boolean c = false;
		c = loanExpandBatch.doBatch("1", "2");
		if (!c) {
			throw new Exception("LoanExpandBatch");
		}
		System.out.println("贷款展期 is OK!");
	}
}
