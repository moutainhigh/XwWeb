package accounting.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import accounting.biz.aft.AftCompstBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.sys.OperaInfo;
import accounting.plat.TransCode;

/**
 * 生成待代偿数据
 * 
 * 
 */
public class LoanAftCompstBatch extends Batch {
	private final Logger log = Logger.getLogger(LoanAftCompstBatch.class);

	@Override
	public boolean doBatch(String startOrg, String endOrg) throws Exception {
		String step = "3";// 第3步
		boolean batchFlag = false;
		Connection conn = null; // 数据库连接
		String traceNo = null;
		try {
			conn = this.getConnection();
			String txDt = this.getTxDt(conn);

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

			// 若批量步骤小于3则说明当前该步骤还未处理
			if (Integer.parseInt(batchStp) < Integer.parseInt(step)) {
				traceNo = ParmBiz.getTraceNo(conn); // 得到交易流水号

				OperaInfo operaInfo = new OperaInfo(conn);
				operaInfo.setBizDt(txDt);
				operaInfo.setTraceNo(traceNo);
				operaInfo.setTraceCnt(1);
				operaInfo.setTxDt(txDt);

				AftCompstBiz a = new AftCompstBiz();
				a.generateAftCompst(operaInfo, TransCode.LNCP);

				Statement updateAcComSysParmSt = conn.createStatement();
				String updateAcComSysParmSql = "update AC_COM_SYS_PARM set batch_stp='" + step + "'";
				updateAcComSysParmSt.executeUpdate(updateAcComSysParmSql);
				updateAcComSysParmSt.close();

				conn.commit();

			} else {
				System.out.println(batchDt + "批量步骤3已执行");
				log.info(batchDt + "批量[待代偿记录]已执行");
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
			log.error("批量[待代偿记录]执行失败：" + e.getMessage(), e);
			batchFlag = false;
		} finally {
			try {
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
		// LoanAftCompstBatch acb = new LoanAftCompstBatch();
		// acb.doBatch("100000", "100001");
		// int a = TimeUtil.getBetweenDays("2010-7-24","2010-10-22");
		// System.out.println(a);
		// String oldDay = TimeUtil.ADD_DAY("2010-10-22", -90);
		// System.out.println(oldDay);

	}
}
