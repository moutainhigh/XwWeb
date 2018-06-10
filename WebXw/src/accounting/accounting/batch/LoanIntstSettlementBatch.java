package accounting.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import accounting.biz.loan.LoanIntstBiz;
import accounting.biz.pub.FeeBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.loan.AcLnLo;
import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcLnRepayPlnCur;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.core.init.BusinessInitializer;
import accounting.plat.dao.JdbcDao;

/**
 * 贷款结息批处理
 * 
 * 
 */
public class LoanIntstSettlementBatch extends Batch {

	public boolean doBatch(String startOrg, String endOrg) throws Exception {
		String step="8";//第8步

		boolean batchFlag = false;
		Connection conn = null;
		PreparedStatement queryPlnCurPst = null;
		ResultSet queryPlnCurRs = null;
		PreparedStatement queryAcLnMstPst = null;
		ResultSet queryAcLnMstRs = null;
		PreparedStatement queryAcLnLoPst = null;
		ResultSet queryAcLnLoRs = null;
		PreparedStatement selectAcLnLoPst = null;
		ResultSet selectAcLnLoRs = null;
		try {
			conn = this.getConnection();
			String txDt = this.getTxDt(conn);
			
			String batchDt = "";//当前批量日期
			PreparedStatement selectSysGlobalPst = conn.prepareStatement("select sys_date from sys_global ");
			
			ResultSet selectSysGlobalRs = selectSysGlobalPst.executeQuery();
			if(selectSysGlobalRs.next()){
				batchDt = selectSysGlobalRs.getString("sys_date");
			}
			
			String batchStp = "";//当前批量成功运行步骤
			PreparedStatement selectAcComSysParmPst = conn.prepareStatement("select batch_stp from ac_com_sys_parm where batch_dt='"+batchDt+"'");
			
			ResultSet selectAcComSysParmRs = selectAcComSysParmPst.executeQuery();
			if(selectAcComSysParmRs.next()){
				batchStp = selectAcComSysParmRs.getString("batch_stp");
			}
			
			//若批量步骤小于8则说明当前该步骤还未处理
			if (batchStp.length()!=0 && Integer.parseInt(batchStp) < Integer.parseInt(step)) {
				
			//当期到期结息--只查询项目正常运行且贷款状态为正常、待逾期、逾期的借据
			queryPlnCurPst = conn.prepareStatement("select loan_no,perd_no from ac_ln_repay_pln_cur where pay_dt='"+txDt+"' and loan_no in(select a.loan_no from ac_ln_mst a where a.br_acc_type='A' and a.loan_sts in('01','02','03') )");
			queryPlnCurRs = queryPlnCurPst.executeQuery();
			
			while(queryPlnCurRs.next()){
				
				String loanNo = queryPlnCurRs.getString("loan_no");
				String perdNo = queryPlnCurRs.getString("perd_no");
				
				AcLnMst acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
				AcLnRepayPlnCur acLnRepayPlnCur  = (AcLnRepayPlnCur)JdbcDao.query(new AcLnRepayPlnCur(), "loan_no='"+loanNo+"' and perd_no='" + perdNo +"'", "ac_ln_repay_pln_cur", conn);

				
				OperaInfo operaInfo = new OperaInfo(conn) ;
				operaInfo.setTxDt(txDt);
				operaInfo.setBizDt(txDt) ;
				operaInfo.setTraceNo(ParmBiz.getTraceNo(conn)) ;
				operaInfo.setTraceCnt(1);
				//扣款日结息
				LoanIntstBiz.loanCurIntstSettlement(acLnMst,acLnRepayPlnCur ,operaInfo, "LNAA");
				//扣款日产生当期费用
				FeeBiz.getAcChrgLogLNAA( acLnRepayPlnCur, acLnMst,  operaInfo);
			}
			
			//欠款结息
			String queryAcLnLoSql = "select loan_no from AC_LN_LO where setl_sts='"+PUBConstant.N+"' and loan_no not in (select loan_no from ac_ln_mst where br_acc_type='B' OR int_to_stp_ind='Y') group by loan_no";
			queryAcLnLoPst = conn.prepareStatement(queryAcLnLoSql);
			queryAcLnLoRs = queryAcLnLoPst.executeQuery();
			
			selectAcLnLoPst = conn.prepareStatement("select * from ac_ln_lo where loan_no=? and (prcp_sts in ('30','40') or int_sts in ('30','40'))");
			
			while(queryAcLnLoRs.next()){
				String loanNo = queryAcLnLoRs.getString("loan_no");
				
				AcLnMst acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(), "loan_no='"+loanNo+"'", "ac_ln_mst", conn);
				
				OperaInfo operaInfo = new OperaInfo(conn) ;
				operaInfo.setBizDt(txDt) ;
				operaInfo.setTraceNo(ParmBiz.getTraceNo(conn)) ;
				operaInfo.setTraceCnt(1);
				
				selectAcLnLoPst.setString(1, loanNo);
				selectAcLnLoRs = selectAcLnLoPst.executeQuery();
				List<AcLnLo> acLnLoList = new ArrayList<AcLnLo>();
				
				while(selectAcLnLoRs.next()){
					AcLnLo acLnLo = this.copyAcLnLoFromRs(selectAcLnLoRs);
					acLnLoList.add(acLnLo);
				}
				//欠款结息
				LoanIntstBiz.loanIntstSettlement(operaInfo, acLnMst, acLnLoList, TransCode.LNAA, TransCode.LNAA, true);
			}
			
			Statement updateAcComSysParmSt2 = conn.createStatement();
			String updateAcComSysParmSql2 = "update AC_COM_SYS_PARM set batch_dt='" + batchDt + "',batch_stp='" + step + "'";
			updateAcComSysParmSt2.executeUpdate(updateAcComSysParmSql2);
			updateAcComSysParmSt2.close();

			conn.commit();

			}else{
				System.out.println(batchDt+"批量步骤8已执行或第一步未执行");
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
			batchFlag = false;
		} finally {
			try {
				if(queryAcLnMstRs!=null){queryAcLnMstRs.close();}
				if(queryAcLnMstPst!=null){queryAcLnMstPst.close();}
				if(queryPlnCurRs!=null){queryPlnCurRs.close();}
				if(queryAcLnLoRs!=null){queryAcLnLoRs.close();}
				if(selectAcLnLoRs!=null){selectAcLnLoRs.close();}
				if(selectAcLnLoPst!=null){selectAcLnLoPst.close();}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return batchFlag;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BusinessInitializer buz = new BusinessInitializer();
		buz.initialize();
		LoanIntstSettlementBatch lb = new LoanIntstSettlementBatch();
		lb.doBatch("", "");
		System.out.println("批量结息完成");
	}
	
	private AcLnLo copyAcLnLoFromRs(ResultSet rs) throws AccountingException {
		AcLnLo acLnLo = new AcLnLo();
		try {
			acLnLo.setLoanNo(rs.getString("LOAN_NO"));
			acLnLo.setPactNo(rs.getString("PACT_NO"));
			acLnLo.setBrNo(rs.getString("BR_NO"));
			acLnLo.setPerdNo(rs.getInt("PERD_NO"));
			acLnLo.setPayDt(rs.getString("PAY_DT"));
			acLnLo.setPrcpAmt(rs.getDouble("PRCP_AMT"));
			acLnLo.setNormInt(rs.getDouble("NORM_INT"));
			acLnLo.setFineInt(rs.getDouble("FINE_INT"));
			acLnLo.setRepayPrcpAmt(rs.getDouble("REPAY_PRCP_AMT"));
			acLnLo.setRepayNormInt(rs.getDouble("REPAY_NORM_INT"));
			acLnLo.setRepayFineInt(rs.getDouble("REPAY_FINE_INT"));
			acLnLo.setWvPrcpAmt(rs.getDouble("WV_PRCP_AMT"));
			acLnLo.setWvNormInt(rs.getDouble("WV_NORM_INT"));
			acLnLo.setWvFineInt(rs.getDouble("WV_FINE_INT"));
			acLnLo.setPrcpSts(rs.getString("PRCP_STS"));
			acLnLo.setIntSts(rs.getString("INT_STS"));
			acLnLo.setSetlSts(rs.getString("SETL_STS"));
			acLnLo.setOverDt(rs.getString("OVER_DT"));
			acLnLo.setFineIntDt(rs.getString("FINE_INT_DT"));
			acLnLo.setMac(rs.getString("MAC"));
			acLnLo.setNextIntDt(rs.getString("NEXT_INT_DT"));
			acLnLo.setSetlDt(rs.getString("SETL_DT"));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountingException(e);
		}

		return acLnLo;
	}

}
