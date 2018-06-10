package accounting.biz.aft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import accounting.biz.pub.ParmBiz;
import accounting.domain.sys.OperaInfo;
import accounting.plat.core.AccountingException;

/**
 * 生成待代偿记录
 * 
 * 
 */
public class AftCompstBiz {
	private final Logger log = Logger.getLogger(AftCompstBiz.class);

	// public void generateAftCompst(OperaInfo operaInfo, String txCde) throws
	// AccountingException{
	// Connection conn = operaInfo.getConn();
	// List<AcLnLo> allLnLoList = (ArrayList)JdbcDao.queryList(new AcLnLo(),
	// "select l.* from ac_ln_lo l where setl_sts = '"+PUBConstant.N+"' order by l.loan_no,l.perd_no",
	// conn);
	// for(AcLnLo acLnLo : allLnLoList){
	// AcLnMst acLnMst = (AcLnMst)JdbcDao.query(new AcLnMst(),
	// "loan_no='"+acLnLo.getLoanNo()+"'", "ac_ln_mst", conn);
	// if(acLnLo.getPayDt().equals(operaInfo.getTxDt())){
	// //获取当期还款计划
	// AcLnRepayPln acLnRepayPln = (AcLnRepayPln)JdbcDao.query(new
	// AcLnRepayPln(),
	// "loan_no='"+acLnLo.getLoanNo()+"' and perd_no='"+acLnLo.getPerdNo()+"'",
	// "ac_ln_repay_pln", conn);
	// //获取当期费用信息
	// AcChrgLog acChrgLog = (AcChrgLog)JdbcDao.query(new AcChrgLog(),
	// "loan_no='"+acLnLo.getLoanNo()+"' and perd_no='"+acLnLo.getPerdNo()+"'",
	// "ac_chrg_log", conn);
	//
	// AftCompst aftCompst = getAftCompst(acLnMst, acLnRepayPln, acChrgLog ,
	// operaInfo);
	// aftCompstList.add(aftCompst);
	// AcTraceLog acTraceLog = getTraceLog(aftCompst,acLnMst,operaInfo);
	// traceLogList.add(acTraceLog);
	// }
	// }
	//
	// //处理代偿信息
	// if(aftCompstList.size()>0){
	// JdbcDao.insertList(aftCompstList, "AFT_COMPST", conn);
	// }
	//
	// //处理交易流水表
	// if(traceLogList.size()>0){
	// JdbcDao.insertList(traceLogList, "AC_TRACE_LOG", conn);
	// }
	// }

	public void generateAftCompst(OperaInfo operaInfo, String txCde) throws AccountingException {

		Connection conn = operaInfo.getConn();
		// 获取需代偿的欠款信息插入待代偿表中
		PreparedStatement queryCompstLnLoPstPrc = null;//本金
		PreparedStatement queryCompstLnLoPstInt = null;//利息
		PreparedStatement queryCompstLnLoPstPi = null;//本利
		// ResultSet queryCompstLnLoRs = null;
//		  AND A.PRCP_AMT - A.REPAY_PRCP_AMT - A.WV_PRCP_AMT > 0 
		try {
			queryCompstLnLoPstPrc = conn.prepareStatement("INSERT INTO AFT_COMPST (COMPST_ID, LOAN_NO, PACT_NO, BR_NO, PROJ_NO, PRDT_NO, CIF_NO, CIF_NAME, PERD_NO, PRCP_AMT, NORM_INT, FINE_INT, REPAY_PRCP_AMT, REPAY_NORM_INT, REPAY_FINE_INT, COMPST_DT, COMPST_STS, TX_DATE, TX_TIME) (SELECT AFT_COMPST_SEQ.NEXTVAL, A.LOAN_NO,  A.PACT_NO, A.BR_NO, C.PROJ_NO,  C.PRDT_NO,  C.CIF_NO, FUN_GETCIFNAME(C.CIF_NO), A.PERD_NO, A.PRCP_AMT, A.NORM_INT,  A.FINE_INT, A.REPAY_PRCP_AMT, A.REPAY_NORM_INT,  A.REPAY_FINE_INT,  '', '01', ?, ? FROM  AC_LN_LO A, AC_LN_MST C, CORP_PARM P, PROJ_PARM M " +
					" WHERE A.LOAN_NO = C.LOAN_NO  AND A.BR_NO = P.BR_NO AND P.CORP_SIGN = '01'  AND M.PROJ_NO = C.PROJ_NO AND A.PRCP_AMT - A.REPAY_PRCP_AMT - A.WV_PRCP_AMT >= 0 AND TO_DATE(?, 'YYYYMMDD') - TO_DATE(A.PAY_DT, 'YYYYMMDD') >=  M.COMPST_DAYS AND NOT EXISTS (SELECT 1 FROM  AFT_COMPST T  WHERE T.LOAN_NO = A.LOAN_NO AND T.PERD_NO = A.PERD_NO))");
			queryCompstLnLoPstInt = conn.prepareStatement("INSERT INTO AFT_COMPST (COMPST_ID, LOAN_NO, PACT_NO, BR_NO, PROJ_NO, PRDT_NO, CIF_NO, CIF_NAME, PERD_NO, PRCP_AMT, NORM_INT, FINE_INT, REPAY_PRCP_AMT, REPAY_NORM_INT, REPAY_FINE_INT, COMPST_DT, COMPST_STS,  TX_DATE, TX_TIME)  (SELECT AFT_COMPST_SEQ.NEXTVAL, A.LOAN_NO, A.PACT_NO, A.BR_NO, C.PROJ_NO, C.PRDT_NO,  C.CIF_NO, FUN_GETCIFNAME(C.CIF_NO), A.PERD_NO, A.PRCP_AMT, A.NORM_INT, A.FINE_INT, A.REPAY_PRCP_AMT, A.REPAY_NORM_INT, A.REPAY_FINE_INT,  '', '01', ?,  ?  FROM  AC_LN_LO A, AC_LN_MST C, CORP_PARM P, PROJ_PARM M  " +
					" WHERE A.LOAN_NO = C.LOAN_NO AND A.BR_NO = P.BR_NO  AND P.CORP_SIGN = '02'  AND M.PROJ_NO = C.PROJ_NO AND A.PRCP_AMT - A.REPAY_PRCP_AMT - A.WV_PRCP_AMT >= 0  AND TO_DATE(?, 'YYYYMMDD') - TO_DATE(A.PAY_DT, 'YYYYMMDD') >= M.COMPST_DAYS AND NOT EXISTS (SELECT 1  FROM  AFT_COMPST T WHERE T.LOAN_NO = A.LOAN_NO AND T.PERD_NO = A.PERD_NO))");
			queryCompstLnLoPstPi = conn.prepareStatement("INSERT INTO AFT_COMPST (COMPST_ID, LOAN_NO, PACT_NO, BR_NO,  PROJ_NO, PRDT_NO, CIF_NO, CIF_NAME, PERD_NO, PRCP_AMT, NORM_INT, FINE_INT, REPAY_PRCP_AMT, REPAY_NORM_INT, REPAY_FINE_INT, COMPST_DT, COMPST_STS, TX_DATE, TX_TIME)  (SELECT AFT_COMPST_SEQ.NEXTVAL, A.LOAN_NO, A.PACT_NO, A.BR_NO,  C.PROJ_NO, C.PRDT_NO,  C.CIF_NO, FUN_GETCIFNAME(C.CIF_NO), A.PERD_NO, A.PRCP_AMT, A.NORM_INT,  A.FINE_INT,  A.REPAY_PRCP_AMT, A.REPAY_NORM_INT, A.REPAY_FINE_INT, '', '01', ?, ?  FROM  AC_LN_LO A, AC_LN_MST C, CORP_PARM P, PROJ_PARM M " +
					" WHERE A.LOAN_NO = C.LOAN_NO AND A.BR_NO = P.BR_NO  AND P.CORP_SIGN = '03'  AND M.PROJ_NO = C.PROJ_NO AND A.PRCP_AMT - A.REPAY_PRCP_AMT - A.WV_PRCP_AMT >= 0  AND A.NORM_INT - A.REPAY_NORM_INT - A.WV_NORM_INT > 0  AND TO_DATE(?, 'YYYYMMDD') - TO_DATE(A.PAY_DT, 'YYYYMMDD') >= M.COMPST_DAYS  AND NOT EXISTS (SELECT 1 FROM  AFT_COMPST T WHERE T.LOAN_NO = A.LOAN_NO AND T.PERD_NO = A.PERD_NO))");
 
			queryCompstLnLoPstPrc.setString(1, operaInfo.getBizDt());
			queryCompstLnLoPstPrc.setString(2, ParmBiz.getOracleTxTime(conn));
			queryCompstLnLoPstPrc.setString(3, operaInfo.getBizDt());

			queryCompstLnLoPstInt.setString(1, operaInfo.getBizDt());
			queryCompstLnLoPstInt.setString(2, ParmBiz.getOracleTxTime(conn));
			queryCompstLnLoPstInt.setString(3, operaInfo.getBizDt());
			
			queryCompstLnLoPstPi.setString(1, operaInfo.getBizDt());
			queryCompstLnLoPstPi.setString(2, ParmBiz.getOracleTxTime(conn));
			queryCompstLnLoPstPi.setString(3, operaInfo.getBizDt());
			
			int numPrc = queryCompstLnLoPstPrc.executeUpdate();
			log.info("产生本金待代偿记录：" + numPrc);
			int numInt = queryCompstLnLoPstInt.executeUpdate();
			log.info("产生利息待代偿记录：" + numInt);
			int numPi = queryCompstLnLoPstPi.executeUpdate();
			log.info("产生本利待代偿记录：" + numPi);
		} catch (Exception e) {
			log.error("产生待代偿记录：" + e.getMessage(),e);
			throw new AccountingException(e);
		} finally {
			try {
				if (queryCompstLnLoPstPrc != null)
					queryCompstLnLoPstPrc.close();
				if (queryCompstLnLoPstInt != null)
					queryCompstLnLoPstInt.close();
				if (queryCompstLnLoPstPi != null)
					queryCompstLnLoPstPi.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 根据还款计划及贷款主文件信息获取待代偿记录
	 * 
	 * @throws AccountingException
	 */
//	private AftCompst getAftCompst(AcLnMst acLnMst, AcLnRepayPln acLnRepayPln, AcChrgLog acChrgLog, OperaInfo operaInfo)
//			throws AccountingException {
//		AftCompst aftCompst = new AftCompst();
//		aftCompst.setCompstId(ParmBiz.getAftCompstId(operaInfo.getConn()));
//		aftCompst.setLoanNo(acLnMst.getLoanNo());
//		aftCompst.setPactNo(acLnMst.getPactNo());
//		aftCompst.setBrNo(acLnMst.getBrNo());
//		aftCompst.setProjNo(acLnMst.getProjNo());
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
//		aftCompst.setCompstSts(PUBConstant.COMPST_STS_01);// 待代偿
//		return aftCompst;
//	}

	/**
	 * 获取日志流水
	 * 
	 * @throws AccountingException
	 */
//	private AcTraceLog getTraceLog(AftCompst aftCompst, AcLnMst acLnMst, OperaInfo operaInfo)
//			throws AccountingException {
//		AcTraceLog acTraceLog = new AcTraceLog();
//		acTraceLog.setTraceNo(ParmBiz.getTraceNo(operaInfo.getConn()));
//		acTraceLog.setTraceCnt(1);
//		acTraceLog.setTxCde(TransCode.LNCP);
//		acTraceLog.setSubTxCde(TransCode.LNCP);
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
