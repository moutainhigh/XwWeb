package accounting.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import accounting.plat.PUBConstant;
import accounting.plat.util.TimeUtil;

/**
 * 核算系统换日
 *
 */
public class ChangeSysDateBatch extends Batch {
	
	public boolean doBatch(String startOrg, String endOrg){
		String step="6";//第6步

		boolean batchFlag=false;
		Connection conn = null;				// 数据库连接
		try {
			conn = this.getConnection();	// 获取连接
			
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
			
			//若批量步骤小于6则说明当前该步骤还未处理
			if (Integer.parseInt(batchStp) < Integer.parseInt(step)) {

				// 将系统日期换到下一天；上次系统日期 =当前营业日期
				String txDt = this.getTxDt(conn);
				String lstDt = txDt;
				txDt = TimeUtil.ADD_DAY(txDt, 1);
				// 更新AC_COM_SYS_PARM表中系统日期和上次系统日期
				Statement updateAcComSysParmSt = conn.createStatement();
				String updateAcComSysParmSql = "update AC_COM_SYS_PARM set lst_dt='" + lstDt + "',sys_dt='" + txDt + "'";
				updateAcComSysParmSt.executeUpdate(updateAcComSysParmSql);
				updateAcComSysParmSt.close();

				PUBConstant.CUR_PRCS_DT = txDt;
				PUBConstant.LAST_PRCS_DT = lstDt;

				log(txDt + "：核算系统换日交易  核算换日成功");

				Statement updateAcComSysParmSt2 = conn.createStatement();
				String updateAcComSysParmSql2 = "update AC_COM_SYS_PARM set batch_dt='" + batchDt + "',batch_stp='" + step + "'";
				updateAcComSysParmSt2.executeUpdate(updateAcComSysParmSql2);
				updateAcComSysParmSt2.close();

				conn.commit();

			}else{
				System.out.println(batchDt+"批量步骤6已执行");
			}
			
			selectSysGlobalPst.close();
			selectSysGlobalRs.close();
			selectAcComSysParmPst.close();
			selectAcComSysParmRs.close();
			
			batchFlag=true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			error(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return batchFlag;
	}
	
	
}
