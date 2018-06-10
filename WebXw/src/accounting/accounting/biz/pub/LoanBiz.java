package accounting.biz.pub;

import java.sql.Connection;

import accounting.domain.loan.AcLnMst;
import accounting.domain.loan.AcTraceLog;
import accounting.domain.sys.OperaInfo;
import accounting.plat.PUBConstant;
import accounting.plat.core.AccountingException;
import accounting.plat.dao.JdbcDao;

/**
 *  借据相关类
 */
public class LoanBiz {
	
	/**
	 * 插入交易日志的字段
	 * 
	 * @param log
	 *            交易日志
	 * @param acLnMst
	 *            贷款主文件
	 * @param operaInfo
	 *            业务交易对象
	 * @param txCode
	 *            交易代码
	 * @param subTxCode
	 *            子交易代码
	 * @param addInd
	 *            增减标志
	 * @throws AccountingException
	 */
	public static void insertLog(AcTraceLog log, AcLnMst acLnMst,
			OperaInfo operaInfo, String txCode, String subTxCode, String addInd)
			throws AccountingException {
		Connection conn = operaInfo.getConn();

		log.setTraceNo(operaInfo.getTraceNo());
		log.setTraceCnt(operaInfo.getTraceCnt()); 
		log.setTxDt(operaInfo.getTxDt()); 
		log.setTxTime(ParmBiz.getOracleTxTime(conn)); 
		log.setTxCde(txCode); 
		log.setSubTxCde(subTxCode); 
		log.setSvcInd("0"); 
		log.setCurNo(acLnMst.getCurNo()); 
		log.setPrdtNo(acLnMst.getPrdtNo()); 
		log.setBrNo(acLnMst.getBrNo());
		log.setPactNo(acLnMst.getPactNo());
		log.setLoanNo(acLnMst.getLoanNo()); 
		log.setAddInd(addInd); 
		log.setCtInd(PUBConstant.CASH); 
		log.setHstInd(PUBConstant.ACCOUNTED); 
		log.setCancelInd(PUBConstant.REV_ROL_NORM); 
		log.setPtTraceNo(0); 
		JdbcDao.insert(log, "AC_TRACE_LOG", conn);
	}


	

}
