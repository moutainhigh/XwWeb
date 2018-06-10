package accounting.biz.aft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import accounting.biz.pub.ParmBiz;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;

/**
 * 生成待回购记录
 *
 */
public class AftRebuyBiz {
	private final Logger log = Logger.getLogger(AftRebuyBiz.class);
	public void generateAftRebuy(OperaInfo operaInfo, String txCde) throws Exception{
		Connection conn = operaInfo.getConn();
		//获取欠款表中未结清且为进回购表的最早一期欠款
		String allLnLoListSql = "SELECT LOAN_NO FROM (" +
				"SELECT L.LOAN_NO, L.PERD_NO, L.BR_NO, L.PAY_DT, P.BACK_DAYS,  TO_DATE(?, 'YYYYMMDD') - TO_DATE(L.PAY_DT, 'YYYYMMDD') AS DAYS, L.SETL_STS, DENSE_RANK() OVER(PARTITION BY L.LOAN_NO ORDER BY L.PERD_NO) AS XC " +
				"FROM AC_LN_LO L, AC_LN_MST M, PROJ_PARM P,CORP_PARM C WHERE L.PRCP_AMT-L.REPAY_PRCP_AMT-L.WV_PRCP_AMT >= 0 AND L.LOAN_NO = M.LOAN_NO AND M.PROJ_NO = P.PROJ_NO AND M.BR_NO = C.BR_NO  AND C.REBUY_SIGN = '01' " +
				"UNION ALL SELECT L.LOAN_NO, L.PERD_NO, L.BR_NO, L.PAY_DT, P.BACK_DAYS, TO_DATE(?, 'YYYYMMDD') - TO_DATE(L.PAY_DT, 'YYYYMMDD') AS DAYS, L.SETL_STS, DENSE_RANK() OVER(PARTITION BY L.LOAN_NO ORDER BY L.PERD_NO) AS XC " +
				"FROM AC_LN_LO L, AC_LN_MST M, PROJ_PARM P,CORP_PARM C  WHERE L.NORM_INT-L.REPAY_NORM_INT-L.WV_NORM_INT >= 0 AND L.LOAN_NO = M.LOAN_NO AND M.PROJ_NO = P.PROJ_NO AND M.BR_NO = C.BR_NO AND C.REBUY_SIGN = '02' " +
				"UNION ALL SELECT L.LOAN_NO, L.PERD_NO, L.BR_NO, L.PAY_DT, P.BACK_DAYS, TO_DATE(?, 'YYYYMMDD') - TO_DATE(L.PAY_DT, 'YYYYMMDD') AS DAYS, L.SETL_STS, DENSE_RANK() OVER(PARTITION BY L.LOAN_NO ORDER BY L.PERD_NO) AS XC " +
				"FROM AC_LN_LO L, AC_LN_MST M, PROJ_PARM P,CORP_PARM C WHERE L.PRCP_AMT-L.REPAY_PRCP_AMT-L.WV_PRCP_AMT >= 0 AND L.NORM_INT-L.REPAY_NORM_INT-L.WV_NORM_INT > 0 AND L.LOAN_NO = M.LOAN_NO AND M.PROJ_NO = P.PROJ_NO AND M.BR_NO = C.BR_NO AND C.REBUY_SIGN = '03') A " +
				"WHERE A.XC = 1 AND DAYS >= A.BACK_DAYS " +
				"AND NOT EXISTS (SELECT 1 FROM AFT_REBUY B WHERE A.LOAN_NO = B.LOAN_NO) " +
				"GROUP BY LOAN_NO";
		PreparedStatement allLnLoListPst = conn.prepareStatement(allLnLoListSql);
		allLnLoListPst.setString(1, operaInfo.getBizDt());
		allLnLoListPst.setString(2, operaInfo.getBizDt());
		allLnLoListPst.setString(3, operaInfo.getBizDt());
		ResultSet allLnLoListRs  = allLnLoListPst.executeQuery();
		//获取贷款的应收本金、利息
		String acLnReplnSql = "select sum(nvl(prcp_amt,0)) as prcp_amt,sum(nvl(norm_int,0)) as norm_int ,sum(nvl(repay_prcp_amt,0)) as repay_prcp_amt,sum(nvl(repay_norm_int,0)) as repay_norm_int,sum(nvl(repay_fine_int,0)) as repay_fine_int from ac_ln_repay_pln where loan_no = ? ";
		PreparedStatement acLnReplnPst = conn.prepareStatement(acLnReplnSql);
		ResultSet acLnReplnRs  = null;
		
		//获取核算主表信息
		String queryAcLnMst = "select loan_no,pact_no,br_no,cif_no,cif_name,proj_no from ac_ln_mst where loan_no=?";
		PreparedStatement queryAcLnMstPst = conn.prepareStatement(queryAcLnMst);
		ResultSet queryAcLnMstRs = null;
		
		//获取核算主表信息
		String updateAcLnMst = "update ac_ln_mst set loan_sts=?, INT_TO_STP_IND=?,UP_DT=? where loan_no=?";
		PreparedStatement updateAcLnMstPst = conn.prepareStatement(updateAcLnMst);
		ResultSet updateAcLnMstRs = null;
		
		//获取核算主表信息
		String deletePlnCur = "delete from ac_ln_repay_pln_cur where loan_no=?";
		PreparedStatement deletePlnCurPst = conn.prepareStatement(deletePlnCur);
		
		//增加日志
		String insertTrace = "insert into ac_trace_log(TRACE_NO,TRACE_CNT,TX_DT,TX_TIME,TX_CDE,SUB_TX_CDE,LOAN_NO,BR_NO," +
				"PACT_NO,AMT,CANCEL_IND,MEMO,APP_STS) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertTracePst = conn.prepareStatement(insertTrace);
		
		//增加待回购记录
		String insertRebuy = "insert into aft_rebuy (REBUY_ID,LOAN_NO,PACT_NO,BR_NO,PROJ_NO,CIF_NO,CIF_NAME,PRCP_AMT,NORM_INT,REPAY_PRCP_AMT,REPAY_NORM_INT,REPAY_FINE_INT,REBUY_AMT,REBUY_STS,REBUY_DATE,TX_DATE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertRebuyPst = conn.prepareStatement(insertRebuy);
		
		while (allLnLoListRs.next()) {
			queryAcLnMstPst.setString(1, allLnLoListRs.getString("loan_no"));
			queryAcLnMstRs = queryAcLnMstPst.executeQuery();
			if (queryAcLnMstRs.next()) {
				updateAcLnMstPst.setString(1, PUBConstant.LOAN_STS_07);// 待回购
				updateAcLnMstPst.setString(2, PUBConstant.Y);// 停罚息
				updateAcLnMstPst.setString(3, operaInfo.getBizDt());
				updateAcLnMstPst.setString(4, queryAcLnMstRs.getString("loan_no"));
				updateAcLnMstRs = updateAcLnMstPst.executeQuery();

				// 生成待回购后，该笔贷款不进行扣款，需将当期还款计划删除
				deletePlnCurPst.setString(1, queryAcLnMstRs.getString("loan_no"));
				deletePlnCurPst.executeQuery();

				insertRebuyPst.setString(1, ParmBiz.getAftRebuyId(conn));
				insertRebuyPst.setString(2, queryAcLnMstRs.getString("loan_no"));
				insertRebuyPst.setString(3, queryAcLnMstRs.getString("pact_no"));
				insertRebuyPst.setString(4, queryAcLnMstRs.getString("br_no"));
				insertRebuyPst.setString(5, queryAcLnMstRs.getString("proj_no"));
				insertRebuyPst.setString(6, queryAcLnMstRs.getString("cif_no"));
				insertRebuyPst.setString(7, queryAcLnMstRs.getString("cif_name"));
				acLnReplnPst.setString(1, queryAcLnMstRs.getString("loan_no"));
				acLnReplnRs = acLnReplnPst.executeQuery();
				if (acLnReplnRs.next()) {
					insertRebuyPst.setDouble(8, acLnReplnRs.getDouble("prcp_amt"));
					insertRebuyPst.setDouble(9, acLnReplnRs.getDouble("norm_int"));
					insertRebuyPst.setDouble(10, acLnReplnRs.getDouble("repay_prcp_amt"));
					insertRebuyPst.setDouble(11, acLnReplnRs.getDouble("repay_norm_int"));
					insertRebuyPst.setDouble(12, acLnReplnRs.getDouble("repay_fine_int"));
				} else {
					insertRebuyPst.setDouble(8, 0.00);
					insertRebuyPst.setDouble(9, 0.00);
					insertRebuyPst.setDouble(10, 0.00);
					insertRebuyPst.setDouble(11, 0.00);
					insertRebuyPst.setDouble(12, 0.00);
				}
				
				insertRebuyPst.setDouble(13, 0.00);
				insertRebuyPst.setString(14, "01");
				insertRebuyPst.setString(15, "");
				insertRebuyPst.setString(16, operaInfo.getBizDt());

				insertRebuyPst.addBatch();
				// insertTracePst.addBatch();
			}
		}
//		insertTracePst.executeBatch();
		int[] is = insertRebuyPst.executeBatch();
		int count = 0;
		for(int i=0;i<is.length;i++){
			count+=is[i];
		}
		log.info("生成待回购记录："+count);
		
		if(null!=allLnLoListPst) allLnLoListPst.close();
		if(null!=allLnLoListRs) allLnLoListRs.close();
		if(null!=acLnReplnPst) acLnReplnPst.close();
		if(null!=acLnReplnRs) acLnReplnRs.close();
		if(null!=queryAcLnMstPst) queryAcLnMstPst.close();
		if(null!=queryAcLnMstRs) queryAcLnMstRs.close();
		if(null!=updateAcLnMstPst) updateAcLnMstPst.close();
		if(null!=updateAcLnMstRs) updateAcLnMstRs.close();
		if(null!=deletePlnCurPst) deletePlnCurPst.close();
		if(null!=insertTracePst) insertTracePst.close();
		if(null!=insertRebuyPst) insertRebuyPst.close();
	}
	
	/**
	 *  根据还款计划及贷款主文件信息获取待代偿记录
	 * @throws AccountingException 
	 */
//	private AftCompst getAftCompst(AcLnMst acLnMst , AcLnRepayPln acLnRepayPln , AcChrgLog acChrgLog , OperaInfo operaInfo) throws AccountingException{
//		AftCompst aftCompst = new AftCompst();
//		aftCompst.setCompstId(ParmBiz.getAftCompstId(operaInfo.getConn()));
//		aftCompst.setLoanNo(acLnMst.getLoanNo());
//		aftCompst.setPactNo(acLnMst.getPactNo());
//		aftCompst.setBrNo(acLnMst.getBrNo());
//		aftCompst.setCifNo(acLnMst.getCifNo());
//		aftCompst.setCifName(acLnMst.getCifName());
//		aftCompst.setPerdNo(acLnRepayPln.getPerdNo());
//		aftCompst.setPrcpAmt(acLnRepayPln.getPrcpAmt());
//		aftCompst.setNormInt(acLnRepayPln.getNormInt());
//		aftCompst.setFineInt(acLnRepayPln.getFineInt());
//		aftCompst.setRepayPrcpAmt(acLnRepayPln.getRepayPrcpAmt());
//		aftCompst.setRepayNormInt(acLnRepayPln.getRepayNormInt());
//		aftCompst.setRepayFineInt(acLnRepayPln.getRepayFineInt());
//		aftCompst.setTxDate(operaInfo.getTxDt());
//		aftCompst.setTxTime(ParmBiz.getOracleTxTime(operaInfo.getConn()));
//		aftCompst.setCompstSts(PUBConstant.COMPST_STS_01);//待代偿
//		return aftCompst;
//	}
//	
	/**
	 *  获取日志流水
	 * @throws AccountingException 
	 */
//	private AcTraceLog getTraceLog(AftRebuy aftRebuy ,AcLnMst acLnMst , OperaInfo operaInfo) throws AccountingException{
//		AcTraceLog acTraceLog = new AcTraceLog();
//		acTraceLog.setTraceNo(ParmBiz.getTraceNo(operaInfo.getConn()));
//		acTraceLog.setTraceCnt(1);
//		acTraceLog.setTxCde(TransCode.LNRB); 
//		acTraceLog.setSubTxCde(TransCode.LNRB); 
//		acTraceLog.setLoanNo(acLnMst.getLoanNo()); 
//		acTraceLog.setCurNo(acLnMst.getCurNo()); 
//		acTraceLog.setPrdtNo(acLnMst.getPrdtNo()); 
//		acTraceLog.setAmt(0.00);
//		acTraceLog.setCancelInd(PUBConstant.REV_ROL_NORM);
//		acTraceLog.setTxDt(operaInfo.getTxDt());
//		acTraceLog.setTxTime(ParmBiz.getOracleTxTime(operaInfo.getConn()));
//		acTraceLog.setTxBrNo(operaInfo.getTxBrNo());
//		return acTraceLog;
//	}
}
