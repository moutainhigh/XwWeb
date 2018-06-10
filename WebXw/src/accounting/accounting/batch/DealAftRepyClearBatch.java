/**
 * 
 */
package accounting.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import accounting.domain.loan.AcLnMst;
import accounting.plat.dao.JdbcDao;

/**
 * 
 * @项目名称：CMSIINEW
 * @类名称：UpdateRepayPln
 * @类描述：
 * @创建人：LIUJ
 * 
 */

public class DealAftRepyClearBatch extends Batch {
	private final Logger log = Logger.getLogger(DealAftRepyClearBatch.class);
	
	public boolean doBatch(String startOrg, String endOrg) throws Exception {
		String step="5";//第5步
		boolean batchFlag = false;
		Connection conn = null; // 数据库连接
		
		PreparedStatement selectSysGlobalPst = null;
		ResultSet selectSysGlobalRs = null;
		PreparedStatement selectAcComSysParmPst = null;
		ResultSet selectAcComSysParmRs = null;
		
		PreparedStatement updateAftRepyClearParmSt = null;
		String updateAftRepyClearSql = "update aft_repy_clear set CLEAR_STS=? where ws_id=?";
		
		PreparedStatement updateWsRepyClearParmSt = null;
		String updateWsRepyClearSql = "update ws_repy_clear set deal_STS=? where ws_id=?";
		
		String selectClearSql = "select ws_id,br_no,pact_no,pay_dt from aft_repy_clear where pay_dt>=? and clear_sts='01'";
		PreparedStatement selectClearPst = null ;
		ResultSet selectClearRs = null;
		log.info("提前清贷SQL=="+selectClearSql);
		try {
			conn = this.getConnection();
			String txDt = this.getTxDt(conn);	
			
			String batchDt = "";//当前批量日期
			selectSysGlobalPst = conn.prepareStatement("select sys_date from sys_global ");
			
			selectSysGlobalRs = selectSysGlobalPst.executeQuery();
			if(selectSysGlobalRs.next()){
				batchDt = selectSysGlobalRs.getString("sys_date");
			}
			String batchStp = "";//当前批量成功运行步骤
			selectAcComSysParmPst = conn.prepareStatement("select batch_stp from ac_com_sys_parm where batch_dt='"+batchDt+"'");
			
			selectAcComSysParmRs = selectAcComSysParmPst.executeQuery();
			if(selectAcComSysParmRs.next()){
				batchStp = selectAcComSysParmRs.getString("batch_stp");
			}
			
			//若批量步骤小于5则说明当前该步骤还未处理
			if (Integer.parseInt(batchStp) < Integer.parseInt(step)) {
				
			//查询待处理提前清贷申请
			selectClearPst = conn.prepareStatement(selectClearSql);
			selectClearPst.setString(1, txDt);
			selectClearRs = selectClearPst.executeQuery();
			
			double prec = 0.00001;//精确位数
			int tot=9,succ=0;
			while(selectClearRs.next()){
				tot++;
				String pactNo = selectClearRs.getString("pact_no");
				String brNo = selectClearRs.getString("br_no");
				String wsId = selectClearRs.getString("ws_id");
				AcLnMst acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(), "pact_no='"+pactNo+"' and br_no='"+brNo+"'", "ac_ln_mst", conn);
				//若借据余额位0则说明贷款为还清-既提前清贷扣款成功
				if(acLnMst.getLoanBal()-0>prec || acLnMst.getLoanBal()-0<prec){

					updateAftRepyClearParmSt = conn.prepareStatement(updateAftRepyClearSql);
					updateAftRepyClearParmSt.setString(1, "02");
					updateAftRepyClearParmSt.setString(2, wsId);
					updateAftRepyClearParmSt.executeUpdate();
					
					updateWsRepyClearParmSt = conn.prepareStatement(updateWsRepyClearSql);
					updateWsRepyClearParmSt.setString(1, "03");
					updateWsRepyClearParmSt.setString(2, wsId);
					updateWsRepyClearParmSt.executeUpdate();
					succ++;
				}else{
					updateAftRepyClearParmSt = conn.prepareStatement(updateAftRepyClearSql);
					updateAftRepyClearParmSt.setString(1, "03");
					updateAftRepyClearParmSt.setString(2, wsId);
					updateAftRepyClearParmSt.executeUpdate();
					
					updateWsRepyClearParmSt = conn.prepareStatement(updateWsRepyClearSql);
					updateWsRepyClearParmSt.setString(1, "04");
					updateWsRepyClearParmSt.setString(2, wsId);
					updateWsRepyClearParmSt.executeUpdate();
				}
			}
			
			Statement updateAcComSysParmSt = conn.createStatement();
			String updateAcComSysParmSql = "update AC_COM_SYS_PARM set batch_stp='" + step + "'";
			updateAcComSysParmSt.executeUpdate(updateAcComSysParmSql);
			updateAcComSysParmSt.close();

			conn.commit();
				log.info("提前清贷处理完成："+succ+"/"+tot);
			}else{
				System.out.println(batchDt+"批量步骤5已执行");
				log.info("批量[提前清贷处理]已执行");
			}
			batchFlag = true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.error("批量[提前清贷处理]执行失败："+e.getMessage(),e);
			e.printStackTrace();
			error(e);
			batchFlag = false;
		} finally {
			try {
				if(selectSysGlobalPst!=null) selectSysGlobalPst.close();
				if(selectSysGlobalRs!=null) selectSysGlobalRs.close();
				if(selectAcComSysParmPst!=null) selectAcComSysParmPst.close();
				if(selectAcComSysParmRs!=null) selectAcComSysParmRs.close();
				if(updateAftRepyClearParmSt!=null) updateAftRepyClearParmSt.close();
				if(selectClearPst!=null) selectClearPst.close();
				if(selectClearRs!=null) selectClearRs.close();
				if(updateWsRepyClearParmSt!=null) updateWsRepyClearParmSt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return batchFlag;
	}
	
	public static void main(String[] args) throws Exception {
		DealAftRepyClearBatch pln = new DealAftRepyClearBatch();
		pln.doBatch("", "");
		
	}
}
