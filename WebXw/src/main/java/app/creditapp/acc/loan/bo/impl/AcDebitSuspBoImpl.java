package app.creditapp.acc.loan.bo.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import redis.clients.jedis.Jedis;
import accounting.biz.loan.RepayBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.sys.OperaInfo;
import accounting.interf.loan.AcLoanBack;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.core.OperationFactory;
import accounting.plat.dao.JdbcDao;
import accounting.plat.util.NumberUtil;
import app.base.BaseService;
import app.base.CreateKey;
import app.base.ServiceException;
import app.base.SourceTemplate;
import app.creditapp.acc.dao.WsRepyFeeDao;
import app.creditapp.acc.entity.WsRepyFee;
import app.creditapp.acc.loan.bo.AcDebitSuspBo;
import app.creditapp.acc.loan.dao.AcDebitDao;
import app.creditapp.acc.loan.dao.AcDebitSuspDao;
import app.creditapp.acc.loan.dao.AcLnLoDao;
import app.creditapp.acc.loan.dao.AcLnMstDao;
import app.creditapp.acc.loan.dao.AcLnPmLogDao;
import app.creditapp.acc.loan.dao.AcLnRepayPlnCurDao;
import app.creditapp.acc.loan.dao.AcLoanBackLogDao;
import app.creditapp.acc.loan.dao.AcLoanLogDao;
import app.creditapp.acc.loan.entity.AcDebit;
import app.creditapp.acc.loan.entity.AcDebitSusp;
import app.creditapp.acc.loan.entity.AcLnLo;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.loan.entity.AcLnPmLog;
import app.creditapp.acc.loan.entity.AcLnRepayPlnCur;
import app.creditapp.acc.loan.entity.AcLoanBackLog;
import app.creditapp.acc.loan.entity.AcLoanLog;
import app.creditapp.acc.log.dao.AcChrgLogDao;
import app.creditapp.acc.log.dao.AcTraceLogDao;
import app.creditapp.acc.log.entity.AcChrgLog;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.acc.option.dao.AcLnRepayPlnDao;
import app.creditapp.acc.option.entity.AcLnRepayPln;
import app.creditapp.aft.dao.AftRepyClearDao;
import app.creditapp.aft.entity.AftRepyClear;
import app.creditapp.corp.dao.CorpAcctDao;
import app.creditapp.corp.entity.CorpAcct;
import app.creditapp.inf.client.QueryService;
import app.creditapp.inf.client.XMLUtil;
import app.creditapp.inf.client.ZFHead;
import app.creditapp.inf.client.ZFHeadQuery;
import app.creditapp.inf.client.ZfBodyQuery;
import app.creditapp.inf.client.zf.AccountInfo;
import app.creditapp.inf.client.zf.BodyLoan;
import app.creditapp.inf.client.zf.EntryInfo;
import app.creditapp.inf.client.zf.Exception_Exception;
import app.creditapp.inf.client.PayInfo;
import app.creditapp.inf.client.zf.SourceAccountInfo;
import app.creditapp.inf.client.zf.TargetAccountInfo;
import app.creditapp.inf.client.zf.TradeInfo;
import app.creditapp.inf.client.zf.ZfB100003;
import app.creditapp.inf.client.zf.ZfB100007;
import app.creditapp.inf.dao.WsBaseDao;
import app.creditapp.inf.dao.WsRepyClearDao;
import app.creditapp.inf.dao.WsRepyFineDao;
import app.creditapp.inf.dao.WsRepyMesDao;
import app.creditapp.inf.entity.WsBase;
import app.creditapp.inf.entity.WsIn2102;
import app.creditapp.inf.entity.WsRepyClear;
import app.creditapp.inf.entity.WsRepyMes;
import app.creditapp.ln.dao.LnAcctDao;
import app.creditapp.ln.dao.LnDueDao;
import app.creditapp.ln.entity.LnAcct;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.proj.dao.ProjAcctDao;
import app.creditapp.proj.dao.ProjBaseDao;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.redis.util.RedisUtil;
import app.oscache.CachecodeUtil;
import app.util.DBUtils;
import app.util.DateUtil;
import app.util.toolkit.Ipage;

/**
 * Title: AcDebitSuspBoImplImpl.java Description:
 * 
 **/
public class AcDebitSuspBoImpl extends BaseService implements AcDebitSuspBo {
	Logger logger = Logger.getLogger(AcDebitSuspBoImpl.class);
	private AcDebitSuspDao acDebitSuspDao;
	private AcLnRepayPlnCurDao acLnRepayPlnCurDao;
	private WsRepyMesDao wsRepyMesDao;
	private AcLnMstDao acLnMstDao;
	private AcLnLoDao acLnLoDao;
	private AftRepyClearDao aftRepyClearDao;
	private AcTraceLogDao acTraceLogDao;
	private DataSource dataSource;
	private LnDueDao lnDueDao;
	private AcDebitDao acDebitDao;
	private ProjAcctDao projAcctDao;
	private AcLoanBackLogDao acLoanBackLogDao;
	private CorpAcctDao corpAcctDao;
	private LnAcctDao lnAcctDao;
	private AcLoanLogDao acLoanLogDao;
	private WsBaseDao wsBaseDao;
	private AcLnPmLogDao acLnPmLogDao;
	private AcChrgLogDao acChrgLogDao;
	private AcLnRepayPlnDao acLnRepayPlnDao;
	private AcDebit acDebit;
	private ProjBase projBase;
	private WsRepyMes wsRepyMes;
	private ProjBaseDao projBaseDao;
	private WsRepyFineDao wsRepyFineDao;
	private WsRepyFeeDao wsRepyFeeDao;
	private WsRepyClearDao wsRepyClearDao;

	/**
	 * 扣款业务单笔查询
	 */
	public void querySingleMes(WsRepyMes wsRepyMes){
		Connection conn = DBUtils.getConn();
		try {
			wsRepyMes = wsRepyMesDao.getByBatchAndPactNo(wsRepyMes);

			// 查询贷后处理扣款信息上传
			AcDebitSusp acDebitSusp = new AcDebitSusp();
			acDebitSusp.setWsId(wsRepyMes.getWsId());
			acDebitSusp = acDebitSuspDao.getById(acDebitSusp);

			// 查询批扣明细
			AcDebit acDebit = new AcDebit();
			acDebit.setDebitNo(acDebitSusp.getSerialNo());
			acDebit = acDebitDao.getByDebitNo(acDebit);

			// 查询发送支付条目信息
			AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
			acLoanBackLog.setLoanLogNo(acDebitSusp.getSerialNo());
			acLoanBackLog.setBackType(PUBConstant.BACK_TYPE_02);
			List<AcLoanBackLog> acLoanBackLogList = acLoanBackLogDao
					.getListByLogNoAndType(acLoanBackLog);
			// 循环调用支付单笔查询交易
			Map returnMap = null;
			for (AcLoanBackLog albl : acLoanBackLogList) {
				returnMap = sendZfB100003(acDebit.getTraceNo(), albl);
				String resultSts = (String) returnMap.get("Status");//返回状态
				String resultMessage = (String) returnMap.get("Message");//返回描述信息
				if (resultSts != null && resultSts.length() > 0) {
				//字典转换
				if("1".equals(resultSts)){//返回成功
					resultSts="01";
				}else if("0".equals(resultSts) || "6".equals(resultSts)){//未决
					resultSts="03";
				}else{//其他即为失败
					resultSts="02";
				}
				albl.setBackSts(resultSts);
				albl.setFailCaus(resultMessage);
				
				if(albl.getBackRes()==null || albl.getBackRes().length()==0){//原支付未回调
					//相当于支付回盘正常处理
					dealZfBack(albl,conn);
				}else if(albl.getBackRes().equals("03") && (resultSts.equals("01") || resultSts.equals("02"))){//原未决，现成功或失败
					//相当于支付回盘正常处理
					dealZfBack(albl,conn);
				}else if(albl.getBackRes().equals("02") && (resultSts.equals("01") || resultSts.equals("03"))){//原失败，现成功或未决
					//相当于支付回盘正常处理
					dealZfBack(albl,conn);
				}else if(albl.getBackRes().equals("01") && (resultSts.equals("02") || resultSts.equals("03"))){//原成功，现失败或未决
					//冲正
					reverseZfBack(albl,resultSts,conn);
				}
				}
			}
			conn.commit();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * 提前清贷单笔查询
	 */
	public void querySingleClear(WsRepyClear wsRepyClear){
		Connection conn = DBUtils.getConn();
		try {
			wsRepyClear = wsRepyClearDao.getDealIngByPactno(wsRepyClear);
			if(wsRepyClear==null || "".equals(wsRepyClear)){
				
			}else{
				
				// 查询贷后处理提前清贷扣款信息上传
				AcDebitSusp acDebitSusp = new AcDebitSusp();
				acDebitSusp.setWsId(wsRepyClear.getWsId());
				acDebitSusp = acDebitSuspDao.getById(acDebitSusp);

				// 查询批扣明细
				AcDebit acDebit = new AcDebit();
				acDebit.setDebitNo(acDebitSusp.getSerialNo());
				acDebit = acDebitDao.getByDebitNo(acDebit);

				// 查询发送支付条目信息
				AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
				acLoanBackLog.setLoanLogNo(acDebitSusp.getSerialNo());
				acLoanBackLog.setBackType(PUBConstant.BACK_TYPE_02);
				List<AcLoanBackLog> acLoanBackLogList = acLoanBackLogDao
						.getListByLogNoAndType(acLoanBackLog);
				// 循环调用支付单笔查询交易
				Map returnMap = null;
				for (AcLoanBackLog albl : acLoanBackLogList) {
					returnMap = sendZfB100003(acDebit.getTraceNo(), albl);
					if (returnMap==null){
						System.out.println("loanLogNo=["+albl.getLoanLogNo()+"],backLogNo=["+albl.getBackLogNo()+"]");
						continue;
					}
					String resultSts = (String) returnMap.get("Status");//返回状态
					String resultMessage = (String) returnMap.get("Message");//返回描述信息
					if (resultSts != null && resultSts.length() > 0) {
					//字典转换
					if("1".equals(resultSts)){//返回成功
						resultSts="01";
					}else if("0".equals(resultSts) || "6".equals(resultSts)){//未决
						resultSts="03";
					}else{//其他即为失败
						resultSts="02";
					}
					albl.setBackSts(resultSts);
					albl.setFailCaus(resultMessage);
					
					if(albl.getBackRes()==null || albl.getBackRes().length()==0){//原支付未回调
						//相当于支付回盘正常处理
						dealZfBack(albl,conn);
					}else if(albl.getBackRes().equals("03") && (resultSts.equals("01") || resultSts.equals("02"))){//原未决，现成功或失败
						//相当于支付回盘正常处理
						dealZfBack(albl,conn);
					}else if(albl.getBackRes().equals("02") && (resultSts.equals("01") || resultSts.equals("03"))){//原失败，现成功或未决
						//相当于支付回盘正常处理
						dealZfBack(albl,conn);
					}else if(albl.getBackRes().equals("01") && (resultSts.equals("02") || resultSts.equals("03"))){//原成功，现失败或未决
						//冲正
						reverseZfBack(albl,resultSts,conn);
					}
					}
				}
				conn.commit();
				
			}
			
		}  catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 进件单笔查询
	 */
	public void querySingleLoan(WsIn2102 wsIn2102){
		Connection conn = DBUtils.getConn();
		try {
			//获取核算主文件
			AcLnMst acLnMst = new AcLnMst();
			acLnMst.setPactNo(wsIn2102.getPactNo());
			acLnMst.setBrNo(wsIn2102.getBrNo());
			acLnMst = acLnMstDao.getByPactNo(acLnMst);
			//判断主文件是否存在，走人工审批的时候不会生成主文件
			if(acLnMst == null ){
				
			}else{
				//获取该贷款放款日志
				AcTraceLog acTraceLog = new AcTraceLog();
				acTraceLog.setLoanNo(acLnMst.getLoanNo());
				acTraceLog.setTxCde("LNC3");
				acTraceLog = acTraceLogDao.getByLoanNoAndTxCde(acTraceLog);
				
				//获取放款流水
				AcLoanLog acLoanLog = new AcLoanLog();
				acLoanLog.setTraceNo(acTraceLog.getTraceNo());
				List<AcLoanLog> acLoanLogList = acLoanLogDao.getListByTraceNo(acLoanLog);
				
				for(AcLoanLog all : acLoanLogList){
				// 查询发送支付条目信息
				AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
				acLoanBackLog.setLoanLogNo(all.getLoanLogNo());
				acLoanBackLog.setBackType(PUBConstant.BACK_TYPE_01);
				List<AcLoanBackLog> acLoanBackLogList = acLoanBackLogDao
						.getListByLogNoAndType(acLoanBackLog);
				// 循环调用支付单笔查询交易
				Map returnMap = null;
				for (AcLoanBackLog albl : acLoanBackLogList) {
					returnMap = sendZfB100003(acLoanLog.getTraceNo(), albl);
					String resultSts = (String) returnMap.get("Status");//返回状态
					String resultMessage = (String) returnMap.get("Message");//返回描述信息
						//排除支付无明细情况
						if (resultSts != null && resultSts.length() > 0) {
							// 字典转换
							if ("1".equals(resultSts)) {// 返回成功
								resultSts = "01";
							} else if ("0".equals(resultSts) || "6".equals(resultSts)) {// 未决
								resultSts = "03";
							} else {// 其他即为失败
								resultSts = "02";
							}
							albl.setBackSts(resultSts);
							albl.setFailCaus(resultMessage);
							if (albl.getBackRes() == null || albl.getBackRes().length() == 0) {// 原支付未回调
								// 相当于支付回盘正常处理
								dealZfBack(albl, conn);
							} else if (albl.getBackRes().equals("03") && (resultSts.equals("01") || resultSts.equals("02"))) {// 原未决，现成功或失败
								// 相当于支付回盘正常处理
								dealZfBack(albl, conn);
							} else if (albl.getBackRes().equals("02") && (resultSts.equals("01") || resultSts.equals("03"))) {// 原失败，现成功或未决
								// 相当于支付回盘正常处理
								dealZfBack(albl, conn);
							} else if (albl.getBackRes().equals("01") && (resultSts.equals("02") || resultSts.equals("03"))) {// 原成功，现失败或未决
								// 冲正
								reverseZfBack(albl, resultSts, conn);
							}
						}
				}
				}
			}
			
			
			conn.commit();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-30
	 * @描述	处理支付回盘--单笔对账
	 */
	public void dealZfBack(AcLoanBackLog acLoanBackLog,Connection conn) throws Exception{
		if (PUBConstant.BACK_TYPE_01
				.equals(acLoanBackLog.getBackType())) {// 放款
			AcLoanBack acLoanBack = new AcLoanBack();
			acLoanBack.setBackLogNo(acLoanBackLog.getBackLogNo());
			acLoanBack.setBackCnt(acLoanBackLog.getBackCnt());
			acLoanBack.setBackRes(acLoanBackLog.getBackRes());
			acLoanBack.setFailCaus(acLoanBackLog.getFailCaus());
			try {
				acLoanBack.setBackDate(ParmBiz.getOracleUpDate(conn));
			} catch (AccountingException e) {
				e.printStackTrace();
			}
			acLoanBack.setTxDt(ParmBiz.getOracleTxDate(conn));
			Operation op = (Operation) OperationFactory
					.getFactoryInstance()
					.getOperation(TransCode.C3BK, conn);
			op.execute(acLoanBack);
		} else if (PUBConstant.BACK_TYPE_02.equals(acLoanBackLog
				.getBackType())) {// 扣款
			OperaInfo operaInfo = new OperaInfo(conn);
			operaInfo.setTraceCnt(1);
			operaInfo.setTraceNo(ParmBiz.getTraceNo(conn));
			operaInfo.setBizDt(ParmBiz.getBizDt(conn));
			operaInfo.setTxDt(ParmBiz.getOracleTxDate(conn));
			RepayBiz.acDebitBack(acLoanBackLog.getBackLogNo(),
					acLoanBackLog.getBackCnt(), acLoanBackLog.getBackRes(), acLoanBackLog.getFailCaus(),
					operaInfo,acLoanBackLog.getStatus(),acLoanBackLog.getCardChn());
		} 
	}
	
	/**
	 * 
	 * @throws AccountingException 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-10-1
	 * @描述	放款或扣款冲正，银行并未处理成功，之前返回成功，回冲
	 */
	
	public void reverseZfBack(AcLoanBackLog acLoanBackLog , String resultSts , Connection conn) throws AccountingException{
		if(PUBConstant.BACK_TYPE_02.equals(acLoanBackLog.getBackType())){//扣款
			//查询扣款记录
			AcDebit acDebit = new AcDebit();
			acDebit.setDebitNo(acLoanBackLog.getLoanLogNo());
			acDebit = acDebitDao.getByDebitNo(acDebit);
			AcLnPmLog acLnPmLog = new AcLnPmLog();
			acLnPmLog.setTraceNo(acDebit.getTraceNo());
			acLnPmLog.setLoanNo(acDebit.getLoanNo());
			List<AcLnPmLog> acLnPmLogList = acLnPmLogDao.getListByTraceNoAndLoanNo(acLnPmLog);
			for(AcLnPmLog alpl : acLnPmLogList){
				if(alpl.getChrgId()!=null && alpl.getChrgId().length()>0){//存在费用编号，则说明该还款是还费用
					AcChrgLog acChrgLog = new AcChrgLog();
					acChrgLog.setChrgId(alpl.getChrgId());
					acChrgLog = acChrgLogDao.getById(acChrgLog);
					acChrgLog.setRepayChrgAmt(acChrgLog.getRepayChrgAmt()-alpl.getRepayFeeAmt());
					acChrgLog.setChrgSts("01");
					acChrgLogDao.update(acChrgLog);
				}else{//其他为正常还本利罚
					AcLnRepayPln acLnRepayPln = new AcLnRepayPln();
					acLnRepayPln.setLoanNo(alpl.getLoanNo());
					acLnRepayPln.setPerdNo(Integer.parseInt(alpl.getPerdNo()));
					acLnRepayPln = acLnRepayPlnDao.getById(acLnRepayPln);
					
					//回滚还款计划实收
					acLnRepayPln.setRepayPrcpAmt(acLnRepayPln.getRepayPrcpAmt()-alpl.getRepayPrcpAmt());
					acLnRepayPln.setRepayNormInt(acLnRepayPln.getRepayNormInt()-alpl.getRepayNormInt());
					acLnRepayPln.setRepayFineInt(acLnRepayPln.getRepayFineInt()-alpl.getRepayFineInt());
					acLnRepayPlnDao.update(acLnRepayPln);
					
					//回购当期还款计划
					AcLnRepayPlnCur acLnRepayPlnCur = new AcLnRepayPlnCur();
					acLnRepayPlnCur.setLoanNo(alpl.getLoanNo());
					acLnRepayPlnCur = acLnRepayPlnCurDao.getById(acLnRepayPlnCur);
					if(acLnRepayPlnCur != null && Integer.parseInt(alpl.getPerdNo())==acLnRepayPlnCur.getPerdNo()){
						acLnRepayPlnCur.setRepayPrcpAmt(acLnRepayPlnCur.getRepayPrcpAmt()-alpl.getRepayPrcpAmt());
						acLnRepayPlnCur.setRepayNormInt(acLnRepayPlnCur.getRepayNormInt()-alpl.getRepayNormInt());
					}
					
					//回滚欠款
					AcLnLo acLnLo = new AcLnLo();
					acLnLo.setLoanNo(alpl.getLoanNo());
					acLnLo.setPerdNo(Integer.getInteger(alpl.getPerdNo()));
					acLnLo = acLnLoDao.getById(acLnLo);
					if(acLnLo != null){
						acLnLo.setRepayPrcpAmt(acLnLo.getRepayPrcpAmt()-alpl.getRepayPrcpAmt());
						acLnLo.setRepayNormInt(acLnLo.getRepayNormInt()-alpl.getRepayNormInt());
						acLnLo.setRepayFineInt(acLnLo.getRepayFineInt()-alpl.getRepayFineInt());
						acLnLoDao.update(acLnLo);
					}
				}
			}
			//更新放款、扣款支付明细表
			acLoanBackLog.setBackRes(resultSts);
			acLoanBackLog.setBackSts("02");
			acLoanBackLog.setBackDate(ParmBiz.getOracleTxDate(conn));
			acLoanBackLogDao.update(acLoanBackLog);
			//更新扣款信息表
			if("03".equals(resultSts)){//未决
				acDebit.setSts("06");
			}else if("02".equals(resultSts)){//返回失败
				acDebit.setSts("04");//扣款失败
			}
			acDebit.setRtnDt(ParmBiz.getOracleTxDate(conn));
			acDebit.setRtnTime(ParmBiz.getOracleTxTime(conn));
			acDebitDao.update(acDebit);
			
			AcDebitSusp acDebitSusp = new AcDebitSusp();
			acDebitSusp.setSerialNo(acDebit.getDebitNo());
			acDebitSusp = acDebitSuspDao.getBySerialNo(acDebitSusp);
			if(acDebitSusp != null){//不为空时，则说明是扣款接口上传扣款文件，需更新扣款进件表状态
				if("03".equals(resultSts)){//未决
					acDebitSusp.setDealSts("02");
				}else if("02".equals(resultSts)){//返回失败
					acDebitSusp.setDealSts("04");
				}
				acDebitSuspDao.update(acDebitSusp);
				
				WsRepyMes wsRepyMes = new WsRepyMes();
				wsRepyMes.setWsId(acDebitSusp.getWsId());
				wsRepyMes = wsRepyMesDao.getById(wsRepyMes);
				//扣款文件上传结果更新
				if(wsRepyMes!=null){
					if("03".equals(resultSts)){//未决
						wsRepyMes.setDealSts("02");
					}else if("02".equals(resultSts)){//返回失败
						wsRepyMes.setDealSts("04");
					}
					wsRepyMesDao.update(wsRepyMes);
				}
				
				WsRepyClear wsRepyClear = new WsRepyClear();
				wsRepyClear.setWsId(acDebitSusp.getWsId());
				wsRepyClear = wsRepyClearDao.getById(wsRepyClear);
				//提前清贷结果更新
				if(wsRepyClear != null){
					if("03".equals(resultSts)){//未决
						wsRepyClear.setDealSts("02");
					}else if("02".equals(resultSts)){//返回失败
						wsRepyClear.setDealSts("04");
					}
					wsRepyClearDao.update(wsRepyClear);
				}
			}
		}else{//放款成功冲正
			AcLoanLog acLoanLog = new AcLoanLog();
			acLoanLog.setLoanLogNo(acLoanBackLog.getLoanLogNo());
			acLoanLog = acLoanLogDao.getById(acLoanLog);
			if("02".equals(resultSts)){//失败
				acLoanLog.setLoanSts("04");
			}else if("03".equals(resultSts)){//未决
				acLoanLog.setLoanSts("06");
			}
			acLoanLog.setUpDate(ParmBiz.getOracleTxDate(conn));
			acLoanLogDao.update(acLoanLog);
			
			AcLnMst acLnMst = new AcLnMst();
			acLnMst.setLoanNo(acLoanLog.getLoanNo());
			acLnMst = acLnMstDao.getById(acLnMst);
			
			LnDue lnDue = new LnDue();
			lnDue.setDueNo(acLnMst.getLoanNo());
			lnDue = lnDueDao.getById(lnDue);
			
			//获取放款账户信息
			LnAcct lnAcct = new LnAcct();
			lnAcct.setAppId(lnDue.getAppId());
			lnAcct.setAcUse("2");
			lnAcct.setAcNo(acLoanLog.getLoanNo());
			lnAcct = lnAcctDao.getById(lnAcct);
			
			if ("00101".equals(acLoanBackLog.getBusEntryType())
					|| "00201".equals(acLoanBackLog.getBusEntryType())
					|| "00301".equals(acLoanBackLog.getBusEntryType())) {// 放款				
				double exact = 0.0001;//精确小数
				if((acLnMst.getLoanBal()-acLoanLog.getLoanAmt())<exact && (acLnMst.getLoanBal()-acLoanLog.getLoanAmt())>-exact ){//
					acLnMst.setDealSts("04");
				}else{
					acLnMst.setDealSts("05");
				}
				acLnMst.setLoanBal(acLnMst.getLoanBal()-acLoanLog.getLoanAmt());
				acLnMst.setLoanSts("");
				acLnMst.setFailCaus(acLoanBackLog.getFailCaus().length()>60?acLoanBackLog.getFailCaus().substring(0,30):acLoanBackLog.getFailCaus());
				acLnMst.setUpDt(ParmBiz.getBizDt(conn));
				
				acLnMstDao.update(acLnMst);
			}else{//放费
				AcChrgLog acChrgLog = new AcChrgLog();
				acChrgLog.setTraceNo(acLoanLog.getTraceNo());
				List<AcChrgLog> acChrgLogList = acChrgLogDao.getListByTraceno(acChrgLog);
				double feeAmt = acLoanLog.getLoanAmt();
				for(AcChrgLog acl : acChrgLogList){
					if(feeAmt>0.0001 && NumberUtil.isAmtGreat(feeAmt,acl.getRepayChrgAmt())){
						acl.setPayChrgAmt(0.00);
						acl.setChrgSts("02");
						feeAmt = feeAmt - acl.getRepayChrgAmt();
					}else{
						acl.setPayChrgAmt(0.00);
						acl.setChrgSts("02");
						feeAmt = 0.00;
					}
					acChrgLogDao.update(acl);
				}
				
			}
		}
	}
	/**
	 * 
	 * @throws AccountingException 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-30
	 * @描述	发送支付单笔查询交易
	 */
	public  Map sendZfB100003(String traceNo,AcLoanBackLog acLoanBackLog) throws AccountingException{
		Connection conn = DBUtils.getConn();
		//返回数据
		Map returnMap = null;
		try {
			ZFHead zfHead = new ZFHead();
			zfHead.setRequestType("B100003");
			zfHead.setUUID(traceNo);// 交易流水号
			zfHead.setComId(CachecodeUtil.MFSPREFIX);
			zfHead.setComIP("11");
			zfHead.setSendTime(ParmBiz.getOracleUpDate2(conn));
			zfHead.setAsyn("0");
			zfHead.setReturnUrl("");
			zfHead.setSigned("");
			zfHead.setClientType("0");
			zfHead.setPayCount(1);
			String headXml = XMLUtil.createHead(zfHead); // 头文件

			ZfB100003 zfB100003 = new ZfB100003();
			zfB100003.setBatchNo(traceNo);
			zfB100003.setOrderNo(acLoanBackLog.getBackLogNo());
			zfB100003.setEntryNo(acLoanBackLog.getBackCnt());
			String bodyXml = XMLUtil.createBody(zfB100003);// 转换为XML

			String xml = headXml + bodyXml + "</Package>";

			WsBase wsBase = new WsBase();
			wsBase.setWsDate(ParmBiz.getOracleTxDate(conn));
			wsBase.setWsTime(ParmBiz.getOracleUpDate(conn));
			wsBase.setTxCode("B100003");
			wsBase.setWsSerial(traceNo);
			wsBase.setRespDesc("单笔交易查询");
			wsBase.setWsSts("01");
			wsBase.setReqContent(xml);
			wsBase.setRespContent("");
			wsBaseDao.insert(wsBase);
			
			QueryService queryService = (QueryService) SourceTemplate
					.getSpringContextInstance().getBean("queryService");
			String result = "";

			result = queryService.doAction("B100003", xml);
			
//			result = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><Package>	<Header>		<RequestType>B100003</RequestType>		<UUID>100000000010319</UUID>		<ComId>0001</ComId>		<From>null</From>		<SendTime>2016-10-19 10:38:56</SendTime>		<Signed></Signed>		<ResultCode>1</ResultCode>		<ResultMsg>success</ResultMsg>	</Header>	<Body>		<PayInfo>			<BatchNo>0001100000000010319</BatchNo>			<OrderNo>00011000009674</OrderNo>			<BusinessOrderType>004</BusinessOrderType>			<EntryNo>00011</EntryNo>			<BusinessEntryType>00401</BusinessEntryType>			<Amount>12</Amount>			<PayChannel>CL0001</PayChannel>			<TradeType>02</TradeType>			<PayTime>2016-10-16 10:49:35</PayTime>			<Status>3</Status>		</PayInfo>	</Body></Package>";
			returnMap = readZfXmlB100003(result);
			//根据交易流水号查询合作机构号码
			acDebit = new AcDebit();
			acDebit.setTraceNo(traceNo);
			acDebit = acDebitDao.getById(acDebit);
			
			wsBase.setRespContent(result);
			if(acDebit!=null){
				acDebit.setBrNo(acDebit.getBrNo());//新增合作机构号码
			}
			wsBaseDao.update(wsBase);
			
		} catch (Exception_Exception e) {
			e.printStackTrace();
			throw new AccountingException(e);
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnMap ;
	
	} 
	// 扣款明细 业务逻辑的合法性判断：sunmingyi
	public Map<String,String> validateAcDebitSusp(String wsId) {
		boolean result = false;
		Connection conn = DBUtils.getConn();
		Map<String,String> map = new HashMap<String,String>();
		try{
			WsRepyMes wsRepyMess = new WsRepyMes();
			wsRepyMess.setWsId(wsId);
			wsRepyMess = wsRepyMesDao.getById(wsRepyMess);
			AcLnMst acLnMst = new AcLnMst();
			
			String pactNo = wsRepyMess.getPactNo();// 合同号
			String brNo = wsRepyMess.getBrNo();// 机构号

			// 分别放到欠款表的实体 查询用
			AcLnLo acLnLo = new AcLnLo();
			acLnLo.setPactNo(pactNo);
			acLnLo.setBrNo(brNo);

			//分别放到贷款主文件的实体 查询用
			acLnMst.setPactNo(pactNo);
			acLnMst.setBrNo(brNo);
			acLnMst = acLnMstDao.getByPactNo(acLnMst);
			if (acLnMst != null) {
				if("01".equals(acLnMst.getLoanSts()) ||"02".equals(acLnMst.getLoanSts()) 
						||"03".equals(acLnMst.getLoanSts()) ||"04".equals(acLnMst.getLoanSts())){//主文件状态为01/02/03/04才能扣款
					String loanNo = acLnMst.getLoanNo();
					acLnLo.setLoanNo(loanNo);
				}else{
					//更新WS进件表失败
					wsRepyMess.setDealDesc("贷款主文件非正常或逾期");
					wsRepyMess.setDealSts("05");
					wsRepyMesDao.update(wsRepyMess);
					return map;
					
				}
			} else  {
				//更新WS进件表失败
				wsRepyMess.setDealDesc("贷款主文件不存在");
				wsRepyMess.setDealSts("05");
				wsRepyMesDao.update(wsRepyMess);
				return map;
			}
			
			//处理维信分包扣问题，若本、利、罚之和大于0，则需要判断是否存在未决的本利罚扣款，存在则不允许扣款，不存在，通过
			if(NumberUtil.amtAdd(NumberUtil.amtAdd(wsRepyMess.getRepayAmt(),wsRepyMess.getRepayInte()),wsRepyMess.getRepayOver())>0){
				//存在未决的扣款指令，则不允许再次发起扣款
				AcDebit acDebit = new AcDebit();
				acDebit.setLoanNo(acLnMst.getLoanNo());
				acDebit = acDebitDao.getByLoanNoIng(acDebit);
				if(acDebit != null){
					wsRepyMess.setDealDesc("合同号:"+acLnMst.getPactNo()+"存在未决的扣款记录,不能再次发起扣款！");
					wsRepyMess.setDealSts("05");
					wsRepyMesDao.update(wsRepyMess);
//					continue ;
					return map;
				}
			}
			AftRepyClear aftRepyClear = new AftRepyClear();
			aftRepyClear.setPactNo(pactNo);
			aftRepyClear.setBrNo(brNo);
			aftRepyClear = aftRepyClearDao.getByPactNoIng(aftRepyClear);

			double repayAmt = wsRepyMess.getRepayAmt();// 扣款本金
			double repayInte = wsRepyMess.getRepayInte();// 扣款利息
			double sumTxPayOver = 0;//实收罚息总额
			double repaysumTxPayOver = 0;//应收罚息总额
			// 分别放到当期还款计划表的实体 查询用
			AcLnRepayPlnCur acLnRepayPlnCur = new AcLnRepayPlnCur();
			acLnRepayPlnCur.setPactNo(pactNo);
			acLnRepayPlnCur.setBrNo(brNo);
			
			//查询借据信息
			LnDue lnDue = new LnDue();
			lnDue.setDueNo(acLnMst.getLoanNo());
			lnDue = lnDueDao.getById(lnDue);
			
			//根据扣款账号查询 扣款信息
			LnAcct lnAcct = new LnAcct();
			lnAcct.setAppId(lnDue.getAppId());
			lnAcct.setAcNo(wsRepyMess.getAcNo());
			lnAcct.setAcUse("1");//扣款
			lnAcct = lnAcctDao.getById(lnAcct);
			
			if(lnAcct==null){//扣款账号 不存在则拒收该扣款请求
				//更新WS进件表失败
				wsRepyMess.setDealDesc("扣款账号不存在");
				wsRepyMess.setDealSts("05");
				wsRepyMesDao.update(wsRepyMess);
				return map;
			}else if(!"01".equals(lnAcct.getAcSts())){//扣款账号未生效，则拒收扣款请求
				wsRepyMess.setDealDesc("扣款账号未生效");
				wsRepyMess.setDealSts("05");
				wsRepyMesDao.update(wsRepyMess);
				return map;
			}
			//判断代扣费总额是否相等
			double feeTotal = wsRepyMess.getFeeTotal();//费用总额
			double repayFeeTotal = 0;
				WsRepyFee wsRepyFee = new WsRepyFee();
				wsRepyFee.setFeeKind("02");
				wsRepyFee.setWsId(wsRepyMess.getWsId());
				repayFeeTotal = wsRepyFeeDao.getFeeAmt(wsRepyFee);//费用明细总额
				if(!NumberUtil.isAmtEqual(feeTotal, repayFeeTotal)){
					wsRepyMess.setDealDesc("上传代扣费总额不等");
					wsRepyMess.setDealSts("05");
					wsRepyMesDao.update(wsRepyMess);
					return map;
				}

			double prcpAmt = 0.0;// 应收本金
			double repayPrcpAmt = 0.0;// 实收本金
			double wvPrcpAmt = 0.0;// 减免本金
			double normInt = 0.0;// 应收利息
			double repayNormInt = 0.0;// 实收利息
			double wvNormInt = 0.0;// 减免利息
			double fineInt = 0.00;//应收罚息
			double repayFineInt = 0.00;//实收罚息
			double wvFineInt = 0.00;//减免罚息
			List<AcLnLo> acLnLoList = acLnLoDao.getByLoanPactBrNo(acLnLo);
			for(AcLnLo all : acLnLoList){
				prcpAmt += all.getPrcpAmt();// 应收本金
				repayPrcpAmt += all.getRepayPrcpAmt();// 实收本金
				wvPrcpAmt += all.getWvPrcpAmt();// 减免本金

				normInt += all.getNormInt();// 应收利息
				repayNormInt += all.getRepayNormInt();// 实收利息
				wvNormInt += all.getWvNormInt();// 减免利息
				
				fineInt += all.getFineInt();//应收罚息
				repayFineInt += all.getRepayFineInt();//实收罚息
				wvFineInt += all.getWvFineInt();//减免罚息
			} 
			if (aftRepyClear == null) {// 不存在提前清贷申请

				// 1、扣款本金 <=
				// (ac_ln_repay_pln_cur当期还款计划表中的应收本金-已还本金+欠款表ac_ln_lo表（应收本金-实收本金-减免本金）
				// PRCP_AMT- REPAY_PRCP_AMT +
				// PRCP_AMT-REPAY_PRCP_AMT-WV_PRCP_AMT)
				double prcpAmtCur = 0.0; // 当期还款计划表中的应收本金
				double repayPrcpAmtCur = 0.0; // 当期还款计划表中的已还本金
				double wvPrcpAmtCur = 0.0; // 当期还款计划表中的减免本金
				double normIntCur = 0.0; // 当期还款计划表中的应收利息
				double repayNormIntCur = 0.0; // 当期还款计划表中的已还利息
				double wvNormIntCur = 0.0; // 当期还款计划表中的减免利息
				acLnRepayPlnCur = acLnRepayPlnCurDao.getByPactNo(acLnRepayPlnCur);
				//当期还款计划存在且 扣款日在当期还款日之后 及产生当期账单
				if (acLnRepayPlnCur != null && !DateUtil.checkDate1BeforeDate2(DateUtil.changeToShow(ParmBiz.getBizDt(conn)), DateUtil.changeToShow(acLnRepayPlnCur.getPayDt()))) {
					prcpAmtCur = acLnRepayPlnCur.getPrcpAmt();// 当期还款计划表中的应收本金
					repayPrcpAmtCur = acLnRepayPlnCur.getRepayPrcpAmt();// 当期还款计划表中的已还本金
					wvPrcpAmtCur = acLnRepayPlnCur.getWvPrcpAmt();// 当期还款计划表中的减免本金
					
					normIntCur = acLnRepayPlnCur.getNormInt();// 当期还款计划表中的应收利息
					repayNormIntCur = acLnRepayPlnCur.getRepayNormInt();// 当期还款计划表中的已还利息
					wvNormIntCur = acLnRepayPlnCur.getWvNormInt();// 当期还款计划表中的减免利息
				} 
				boolean amtLean = true;
				boolean normIntLean = true;
				double pcamt = NumberUtil.amtSubs(NumberUtil.amtSubs(NumberUtil.amtSubs(NumberUtil.amtSubs(NumberUtil.amtAdd(prcpAmtCur, prcpAmt),repayPrcpAmtCur),repayPrcpAmt), wvPrcpAmt),wvPrcpAmtCur);
//					boolean fineLean = true;
				if(repayAmt>0){
					if (repayAmt <= pcamt) {
						amtLean = true;
					} else {
						amtLean = false;
						//更新WS进件表失败
						wsRepyMess.setDealDesc("扣款本金大于应收本金,应收本金为："+ pcamt +"");
						wsRepyMess.setDealSts("05");
						wsRepyMesDao.update(wsRepyMess);
//							continue ;
						return map;
					}	
				}
			
				if(repayInte>0){
					double pyinte = NumberUtil.amtSubs(NumberUtil.amtSubs(NumberUtil.amtSubs(NumberUtil.amtSubs(NumberUtil.amtAdd(normIntCur, normInt),repayNormIntCur), repayNormInt), wvNormInt),wvNormIntCur);//应收利息
					if (repayInte <= pyinte) {
						normIntLean = true;
					} else {
						normIntLean = false;
						//更新WS进件表失败
						wsRepyMess.setDealDesc("扣款利息大于应收利息 ，应收利息为："+pyinte+"元");
						wsRepyMess.setDealSts("05");
						wsRepyMesDao.update(wsRepyMess);
//							continue ;
						return map;
					}
				}
				
				if (amtLean && normIntLean) {
					result = true;
				} else {
					result = false;
				}
			} else {// 存在提前清贷申请
				// 提前清贷 贷款余额必须与扣本金额一直
				double exact = 0.0001;
				if ((acLnMst.getLoanBal()- wsRepyMess.getRepayAmt())<exact && (acLnMst.getLoanBal()- wsRepyMess.getRepayAmt())>-exact && acLnMst!=null  && wsRepyMess!=null ) {
					result = true;
				} else {
					result = false;
					//更新WS进件表失败
					wsRepyMess.setDealDesc("存在提前清贷申请，扣本金额==贷款余额 不成立");
					wsRepyMess.setDealSts("05");
					wsRepyMesDao.update(wsRepyMess);
					return map;
				}
			}
			if (result) {
				
				//更新WS进件表处理中
				wsRepyMess.setDealDesc("处理中");
				wsRepyMess.setDealSts("02");
				wsRepyMesDao.update(wsRepyMess);
				
				AcDebitSusp acDebitSusp = new AcDebitSusp();
				acDebitSusp.setWsId(wsRepyMess.getWsId());
				acDebitSusp.setBatchNo(wsRepyMess.getBatchNo());
				acDebitSusp.setBrNo(wsRepyMess.getBrNo());
				acDebitSusp.setPactNo(wsRepyMess.getPactNo());
				acDebitSusp.setAcName(wsRepyMess.getAcName());
				acDebitSusp.setAcNo(wsRepyMess.getAcNo());
				acDebitSusp.setOpnCode(wsRepyMess.getOpnCode());
				acDebitSusp.setOpnName(wsRepyMess.getOpnName());
				acDebitSusp.setPayOver(wsRepyMess.getPayOver());
				acDebitSusp.setFeeRec(wsRepyMess.getFeeRec());
				acDebitSusp.setRepayTotal(wsRepyMess.getRepayTotal());
				acDebitSusp.setRepayAmt(wsRepyMess.getRepayAmt());
				acDebitSusp.setRepayInte(wsRepyMess.getRepayInte());
				acDebitSusp.setRepayOver(wsRepyMess.getRepayOver());
				acDebitSusp.setFeeTotal(wsRepyMess.getFeeTotal());
				acDebitSusp.setSerialNo(wsRepyMess.getSerialNo());
				acDebitSusp.setDealSts(wsRepyMess.getDealSts());
				acDebitSusp.setDealDesc(wsRepyMess.getDealDesc());
				acDebitSusp.setCardChn(wsRepyMess.getCardChn());
				acDebitSusp.setTxCde(TransCode.LNSU);//B类合作机构与扣款文件上传
				acDebitSuspDao.insert(acDebitSusp);
				
				//进行业务处理
				map = acDebitSuspExec(map ,acDebitSusp);

			}
		} catch (AccountingException e) {
			throw new ServiceException(e.getMessage());
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return map;
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-25
	 * @描述	扣款文件上传逻辑处理
	 */
	public Map<String,String> acDebitSuspExec(Map<String,String> map ,AcDebitSusp acDebitSusp)throws ServiceException{
		Connection conn = this.getConnection();
		AcTraceLog acTraceLog = new AcTraceLog();
		String traceNo =  acTraceLogDao.getKey();
		DecimalFormat df = new DecimalFormat("00");
		//借据信息
		LnDue lnDue = new LnDue();
		lnDue.setPactNo(acDebitSusp.getPactNo());
		lnDue.setBrNo(acDebitSusp.getBrNo());
		lnDue = lnDueDao.getByPactNoAndBrNo(lnDue);
		
		LnAcct lnAcct = new LnAcct();
		lnAcct.setAppId(lnDue.getAppId());
		lnAcct.setAcUse("1");//扣款账号
		lnAcct.setAcNo(acDebitSusp.getAcNo());
		lnAcct.setAcSts("01");//状态正常
		lnAcct = lnAcctDao.getById(lnAcct);
		
		AcDebit acDebit = new AcDebit();
		acDebit.setTraceCnt(1);
		acDebit.setTraceNo(traceNo);
		
		String uUID = CreateKey.getUUID();//接口流水号
		try {
			List<AcLoanBackLog> acLoanBackLogList = new ArrayList<AcLoanBackLog>();

			acDebit.setDebitNo(ParmBiz.getDebitNo(conn));
//			acDebit.setDebitNo(acDebitSusp.getSerialNo());
			acDebit.setTxDt(ParmBiz.getBizDt(conn));
			acDebit.setLoanNo(lnDue.getDueNo());
			acDebit.setPactNo(lnDue.getPactNo());
			acDebit.setBrNo(lnDue.getBrNo());
			acDebit.setCurNo(lnDue.getCurNo());
			acDebit.setAcctBankCde(lnAcct.getBankCode());
			acDebit.setAcNo(lnAcct.getAcNo());
			acDebit.setAcType(lnAcct.getAcType());
			acDebit.setAcName(lnAcct.getAcName());
			acDebit.setBankCode(lnAcct.getBankCode());
			acDebit.setBankProv(lnAcct.getBankProv());
			acDebit.setBankCity(lnAcct.getBankCity());
			acDebit.setBankSite(lnAcct.getBankSite());
			acDebit.setAtpyAmt(acDebitSusp.getRepayAmt()+acDebitSusp.getRepayInte()+acDebitSusp.getRepayOver()+acDebitSusp.getFeeTotal());
			acDebit.setLoAmt(0.00);
			acDebit.setCurAmt(0.00);
			acDebit.setMyFeeAmt(0.00);
			acDebit.setOtherFeeAmt(acDebitSusp.getFeeTotal());
			acDebit.setRepayAmt(0.00);
			acDebit.setSts("02");
			String busOrderType="";//业务订单交易类型
			if(acDebitSusp.getFeeTotal()>0){//若费用大于0
				busOrderType="004";//含费用
			}else{
				busOrderType="003";//不含费用
			}
			acDebit.setBusEntryType(busOrderType+"01");
			acDebit.setIdType(lnAcct.getIdType());
			acDebit.setIdNo(lnAcct.getIdNo());
	 		acDebit.setPhoneNo(lnAcct.getPhoneNo());
	 		acDebit.setValidDate(lnAcct.getValidDate());
	 		acDebit.setCvvNo(lnAcct.getCvvNo());
			
			acDebit.setCardChn(acDebitSusp.getCardChn());
			acDebit.setXtAcNo(CachecodeUtil.MFSPREFIX+"_"+PUBConstant.ACCT_TYPE_04+"_"+lnDue.getProjNo());
			acDebit.setDebitMold(PUBConstant.DEBIT_MOLD_04);
			acDebit.setCreateDt(ParmBiz.getBizDt(conn));
			
			acDebitDao.insert(acDebit);
			acDebitSusp.setSerialNo(acDebit.getDebitNo());
			acDebitSuspDao.update(acDebitSusp);
			
			AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
			String backLogNo = ParmBiz.getAcLoanBackLogNo(conn) ;
			acLoanBackLog.setBackLogNo(backLogNo);
			acLoanBackLog.setBackCnt(ParmBiz.getBackCntSeq(conn));
			acLoanBackLog.setLoanLogNo(acDebit.getDebitNo());
			acLoanBackLog.setBackType(PUBConstant.BACK_TYPE_02);
			acLoanBackLog.setBackRes("");
			acLoanBackLog.setFailCaus("");
			acLoanBackLog.setBackSts(PUBConstant.BACK_STS_01);//01 待处理 02 已处理
			acLoanBackLog.setBusOrderType(busOrderType);
			acLoanBackLog.setBusEntryType(acDebit.getBusEntryType());
			acLoanBackLog.setTraceNo(acDebit.getTraceNo());//交易流水
			acLoanBackLog.setUuid(CachecodeUtil.MFSPREFIX+uUID);
//			acLoanBackLog.setTxTime(ParmBiz.getOracleUpDate(conn));
			acLoanBackLogDao.insert(acLoanBackLog);
			acLoanBackLogList.add(acLoanBackLog);
			
			//费用类型
			WsRepyFee wsRepyFee = null;
			List<WsRepyFee> wsRepyFeeList = new ArrayList<WsRepyFee>();
			// 代收费大于0 ， 需将代收费从虚拟账户中划拨给合作机构
			if (acDebitSusp.getFeeTotal() > 0) {
				//获取合作机构账户信息
				CorpAcct corpAcct = new CorpAcct();
				corpAcct.setBrNo(lnDue.getBrNo());
				
//				corpAcct = corpAcctDao.getByBrNo(corpAcct);
				
				wsRepyFee = new WsRepyFee();
				wsRepyFee.setWsId(acDebitSusp.getWsId());
				wsRepyFee.setFeeKind("02");
				wsRepyFeeList = wsRepyFeeDao.getFeeType(wsRepyFee);
				int bus = 2;
				for(WsRepyFee wsRepyFe : wsRepyFeeList){
					String acNo = CachecodeUtil.MFSPREFIX+"_"+lnDue.getBrNo()+"_"+lnDue.getProjNo()+"_"+wsRepyFe.getFeeType();
//					corpAcct.setOpnAcno(acNo);
//					corpAcct = corpAcctDao.getByBrNoAndAcNo(corpAcct);
					AcLoanLog acLoanLog = new AcLoanLog();
					acLoanLog.setLoanLogNo(ParmBiz.getLoanLogNo(conn));
					acLoanLog.setTraceNo(traceNo);
					acLoanLog.setLoanNo(lnDue.getDueNo());
					acLoanLog.setPactNo(lnDue.getPactNo());
					acLoanLog.setBrNo(lnDue.getBrNo());
					acLoanLog.setLoanAmt(wsRepyFe.getFeeAmt());//费用金额
					acLoanLog.setLoanAcNo(acNo);
					acLoanLog.setLoanAcType("12");
//					acLoanLog.setLoanAcName(corpAcct.getAcName());
//					acLoanLog.setLoanBankCode(corpAcct.getExchange());
//					acLoanLog.setLoanBankProv(corpAcct.getProvince());
//					acLoanLog.setLoanBankCity(corpAcct.getCity());
//					acLoanLog.setLoanBankSite(corpAcct.getOpnBank());
					acLoanLog.setLoanSts("02");
					acLoanLog.setTxDate(ParmBiz.getBizDt(conn));
					acLoanLog.setTxTime(ParmBiz.getOracleTxTime(conn));

					acLoanLog.setCardChn(acDebitSusp.getCardChn());
					acLoanLog.setXtAcNo(CachecodeUtil.MFSPREFIX+"_"+PUBConstant.ACCT_TYPE_04+"_"+lnDue.getProjNo());
	
					acLoanLog.setBusEntryType(busOrderType+String.valueOf(df.format(bus)));
					bus+=1;
					acLoanLogDao.insert(acLoanLog);

					acLoanBackLog = new AcLoanBackLog();
					acLoanBackLog.setBackLogNo(backLogNo);
					acLoanBackLog.setBackCnt(ParmBiz.getBackCntSeq(conn));
					acLoanBackLog.setLoanLogNo(acLoanLog.getLoanLogNo());
					acLoanBackLog.setBackType(PUBConstant.BACK_TYPE_01);
					acLoanBackLog.setBackRes("");
					acLoanBackLog.setFailCaus("");
					acLoanBackLog.setBackSts(PUBConstant.BACK_STS_01);
					acLoanBackLog.setBusOrderType(busOrderType);
					acLoanBackLog.setBusEntryType(acLoanLog.getBusEntryType());
					acLoanBackLog.setTraceNo(acLoanLog.getTraceNo());//交易流水
					acLoanBackLog.setUuid(CachecodeUtil.MFSPREFIX+uUID);
//					acLoanBackLog.setTxTime(ParmBiz.getOracleUpDate(conn));
					acLoanBackLogDao.insert(acLoanBackLog);
					acLoanBackLogList.add(acLoanBackLog);
				}

			}
			
			acTraceLog.setTraceNo(traceNo);
			acTraceLog.setTraceCnt(1);
			acTraceLog.setTxDt(ParmBiz.getBizDt(conn));
			acTraceLog.setTxTime(ParmBiz.getOracleTxTime(conn));
			acTraceLog.setTxBrNo(acDebitSusp.getBrNo());
			acTraceLog.setTxCde(TransCode.LNC4);
			acTraceLog.setSubTxCde(TransCode.LNSU);//B类合作机构扣款文件上传
			acTraceLog.setSvcInd(TransCode.LNSU);
			acTraceLog.setCurNo(lnDue.getCurNo());
			acTraceLog.setPrdtNo(lnDue.getPrdtNo());
			acTraceLog.setAmt(acDebitSusp.getRepayTotal());
			acTraceLog.setCancelInd(PUBConstant.REV_ROL_NORM);//正常
			acTraceLog.setAppSts("01");//新增
			acTraceLog.setBrNo(lnDue.getBrNo());
			acTraceLog.setPactNo(lnDue.getPactNo());
			acTraceLog.setLoanNo(lnDue.getDueNo());
			acTraceLog.setMemo("B类合作机构扣款文件上传");
			
			acTraceLogDao.insert(acTraceLog);
			
			//发送第三方支付
			map = sendZfMess(map ,acLoanBackLogList, lnDue, acLoanBackLog.getTraceNo(), conn);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
		finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/*
	 * @描述 组装发送报文 老接口
	 */
	public  Map<String,String> sendZfMess(Map<String,String> map ,List<AcLoanBackLog> acLoanBackLogList,LnDue lnDue,String traceNo,Connection conn) throws Exception {
		
		ZFHead zfHead = new ZFHead();
		zfHead.setRequestType("P100001");
		String uuid = acLoanBackLogList.get(0).getUuid();
		zfHead.setUUID(uuid);//接口流水号
		zfHead.setComId(CachecodeUtil.MFSPREFIX);
		zfHead.setComIP("11");
		zfHead.setSendTime(ParmBiz.getOracleUpDate2(conn));
		zfHead.setAsyn("0");
		Properties pathProperties = new Properties();
		pathProperties.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("application.properties"));
		String zfReturnUrl = pathProperties.getProperty("zfloanreturn.url");
		zfHead.setReturnUrl(zfReturnUrl);
		zfHead.setSigned("");
		zfHead.setClientType("0");
		zfHead.setPayCount(1);
		String headXml = XMLUtil.createHead(zfHead); // 头文件
		
		BodyLoan bodyLoan = new BodyLoan();
		
		List<TradeInfo> tradeList = new ArrayList<TradeInfo>();
		
		TradeInfo tradeInfo = new TradeInfo();
		
		tradeInfo.setTradeNo(CachecodeUtil.MFSPREFIX+traceNo);
		tradeInfo.setTradeTypeNo(acLoanBackLogList.get(0).getBusOrderType());
		tradeInfo.setTotalEntryNum(acLoanBackLogList.size());
		
		EntryInfo entryInfo = null;
		List<EntryInfo> entryList = new ArrayList<EntryInfo>();

		//放款信息
		AcLoanLog acLoanLog = null;
		//放款信息List
		List<AcLoanLog> acLoanLogList = new ArrayList<AcLoanLog>();
		//扣款信息
		AcDebit acDebit = null;
		//扣款信息list
		List<AcDebit> acDebitList = new ArrayList<AcDebit>();
		//账户信息
		AccountInfo accountInfo = null;
		
		SourceAccountInfo sourceAccountInfo = new SourceAccountInfo();//源
		TargetAccountInfo targetAccountInfo = new TargetAccountInfo();//目的
		
		for (AcLoanBackLog acLoanBackLog : acLoanBackLogList) {
			if(PUBConstant.BACK_STS_01.equals(acLoanBackLog.getBackType())){//放款
				/*//获取放款信息
				acLoanLog = new AcLoanLog();
				acLoanLog.setLoanLogNo(acLoanBackLog.getLoanLogNo());
				acLoanLog = acLoanLogDao.getById(acLoanLog);
				acLoanLogList.add(acLoanLog);
				
				entryInfo = new EntryInfo();
				
				List<SourceAccountInfo> sourceAccountInfoListfk = new ArrayList<SourceAccountInfo>();
				List<TargetAccountInfo> targetAccountInfoListfk = new ArrayList<TargetAccountInfo>();
				
				sourceAccountInfo = new SourceAccountInfo();// 源
				targetAccountInfo = new TargetAccountInfo();// 目的

				entryInfo.setEntryNo(acLoanBackLog.getBackCnt());
				entryInfo.setEnTradeType(PUBConstant.ENTRADE_TYPE_01);// 放款
				entryInfo.setAmount(acLoanLog.getLoanAmt());
				entryInfo.setEntryTypeNo(acLoanBackLog.getBusEntryType());
				entryInfo.setChannelNo(acLoanLog.getCardChn());

				accountInfo = new AccountInfo();
				// 源
				accountInfo.setAccountNo(acLoanLog.getXtAcNo());
				accountInfo.setAccountType("0");
				sourceAccountInfo.setLevel("01");
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListfk.add(sourceAccountInfo);

				accountInfo = new AccountInfo();//目的
				
				accountInfo.setAccountNo(acLoanLog.getLoanAcNo());
				
				if("10".equals(acLoanLog.getLoanAcType())){//个人贷记卡
					accountInfo.setAccountType("3");
				}else if("11".equals(acLoanLog.getLoanAcType())){//个人借记卡
					accountInfo.setAccountType("1");
				}
				
				if("14".equals(acLoanLog.getLoanAcType())||"12".equals(acLoanLog.getLoanAcType())){//商户或对公
					accountInfo.setAccountNo(acLoanLog.getLoanAcNo());//(商户或对公)放款账户
					targetAccountInfo.setLevel("01");
					accountInfo.setAccountType("0");
					targetAccountInfo.setAccountInfo(accountInfo);
					targetAccountInfoListfk.add(targetAccountInfo);
					entryInfo.setSourceAccountList(sourceAccountInfoListfk);
					entryInfo.setTargetAccountList(targetAccountInfoListfk);
					entryInfo.setRemark1(lnDue.getBrNo());
					entryInfo.setRemark2(lnDue.getProjNo());
					entryInfo.setRemark3(lnDue.getPactNo());
					entryList.add(entryInfo);
				}else{
					accountInfo.setAccountName(acLoanLog.getLoanAcName());
					accountInfo.setChannelNo(acLoanLog.getLoanBankCode());
					accountInfo.setBankDetailNo("");
					accountInfo.setBankName(acLoanLog.getLoanBankSite());
					accountInfo.setProvince(acLoanLog.getLoanBankProv());
					accountInfo.setCity(acLoanLog.getLoanBankCity()!=null?acLoanLog.getLoanBankCity():"");
					accountInfo.setCVN2(acLoanLog.getCvvNo()!=null?acLoanLog.getCvvNo():"");
					accountInfo.setVALDATE(acLoanLog.getValidDate()!=null?acLoanLog.getValidDate():"");
					accountInfo.setPhoneNo(acLoanLog.getPhoneNo()!=null?acLoanLog.getPhoneNo():"");
					accountInfo.setEmail("");
					accountInfo.setCertificateType(acLoanLog.getIdType()!=null?acLoanLog.getIdType():"");
					accountInfo.setCertificateNo(acLoanLog.getIdNo()!=null?acLoanLog.getIdNo():"");
					targetAccountInfo.setLevel("01");
					targetAccountInfo.setAccountInfo(accountInfo);
					targetAccountInfoListfk.add(targetAccountInfo);
					entryInfo.setSourceAccountList(sourceAccountInfoListfk);
					entryInfo.setTargetAccountList(targetAccountInfoListfk);
					entryInfo.setRemark1(lnDue.getBrNo());
					entryInfo.setRemark2(lnDue.getProjNo());
					entryInfo.setRemark3(lnDue.getPactNo());
					entryList.add(entryInfo);}*/
					
			}else{
				acDebit = new AcDebit();
				acDebit.setDebitNo(acLoanBackLog.getLoanLogNo());
				acDebit = acDebitDao.getByDebitNo(acDebit);
				acDebitList.add(acDebit);

				acDebitList.add(acDebit);
				
				entryInfo = new EntryInfo();
				
				entryInfo.setEntryNo(acLoanBackLog.getBackCnt());
				entryInfo.setEnTradeType(PUBConstant.ENTRADE_TYPE_02);//扣款
				entryInfo.setAmount(acDebit.getAtpyAmt());
				entryInfo.setEntryTypeNo(acDebit.getBusEntryType());
				entryInfo.setChannelNo(acDebit.getCardChn());
				
				/*accountInfo = new AccountInfo();*/
				
				List<SourceAccountInfo> sourceAccountInfoListsk = new ArrayList<SourceAccountInfo>();
				List<TargetAccountInfo> targetAccountInfoListsk = new ArrayList<TargetAccountInfo>();
				
				/*sourceAccountInfo = new SourceAccountInfo();//源1
*/				targetAccountInfo = new TargetAccountInfo();//目的
				//源：客户卡（溢缴款账户，银行卡） 目的：项目账户
				/*accountInfo.setAccountNo(CachecodeUtil.MFSPREFIX+"_"+acDebit.getBrNo()+"_"+acDebit.getPactNo());//支付平台账户
				accountInfo.setAccountType("0");
				sourceAccountInfo.setLevel("01");
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListsk.add(sourceAccountInfo);*/
				
				accountInfo = new AccountInfo();
				
				sourceAccountInfo = new SourceAccountInfo();//源2
				
				accountInfo.setAccountNo(acDebit.getAcNo());//银行卡
				if("10".equals(acDebit.getAcType())){//个人贷记卡
					accountInfo.setAccountType("3");
				}else if("11".equals(acDebit.getAcType())){//个人借记卡
					accountInfo.setAccountType("1");
				}else if("12".equals(acDebit.getAcType())||"13".equals(acDebit.getAcType())||"14".equals(acDebit.getAcType())){//企业账户（自如）
					accountInfo.setAccountType("0");
				}
				accountInfo.setAccountName(acDebit.getAcName());
				accountInfo.setChannelNo(acDebit.getAcctBankCde());
				accountInfo.setBankDetailNo("");
				accountInfo.setBankName(acDebit.getBankSite());
				accountInfo.setProvince(acDebit.getBankProv());
				accountInfo.setCity(acDebit.getBankCity());
				accountInfo.setCVN2(acDebit.getCvvNo()!=null?acDebit.getCvvNo():"");
				accountInfo.setVALDATE(acDebit.getValidDate()!=null?acDebit.getValidDate():"");
				accountInfo.setPhoneNo(acDebit.getPhoneNo()!=null?acDebit.getPhoneNo():"");
				accountInfo.setEmail("");
				accountInfo.setCertificateType(acDebit.getIdType()!=null?acDebit.getIdType():"");
				accountInfo.setCertificateNo(acDebit.getIdNo()!=null?acDebit.getIdNo():"");
				sourceAccountInfo.setLevel("01");
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListsk.add(sourceAccountInfo);
				
				accountInfo = new AccountInfo();
				
				accountInfo.setAccountNo(CachecodeUtil.MFSPREFIX+"_"+PUBConstant.ACCT_TYPE_04+"_"+lnDue.getProjNo());//项目收款账户
				accountInfo.setAccountType("0");
				targetAccountInfo.setLevel("01");
				targetAccountInfo.setAccountInfo(accountInfo);
				targetAccountInfoListsk.add(targetAccountInfo);
				
				entryInfo.setRemark1(lnDue.getBrNo());
				entryInfo.setRemark2(lnDue.getProjNo());
				entryInfo.setRemark3(lnDue.getPactNo());
				entryInfo.setSourceAccountList(sourceAccountInfoListsk);
				entryInfo.setTargetAccountList(targetAccountInfoListsk);
				entryList.add(entryInfo);
			}
			
		}
		tradeInfo.setEntryList(entryList);
		tradeList.add(tradeInfo);
		bodyLoan.setTradeList(tradeList);
		String bodyXml = XMLUtil.createBody(bodyLoan);// 转换为XML

		String xml = headXml + bodyXml + "</Package>";

		map.put(uuid, xml);
		
		return map ;
	}
	
	/**
	 * 轮询对账
	 */
	public void queryReconciliation() throws ServiceException {
		Properties pathProperties = new Properties();
		try {
			pathProperties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("application.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String timeSec  = pathProperties.getProperty("queryReconciliation.sec");

		List<AcLoanBackLog> acLoanBackLogList = acLoanBackLogDao.reconciZf(timeSec);//获取已发送支付未回调的扣款及放款交易条目

		List<PayInfo> payInfoList = null;
		ZfB100007 zfB100007 = null;
		int count = 0;
		int pageNo = 0;
		int listSize = acLoanBackLogList.size();//发送记录总条数
		StringBuilder buf = new StringBuilder();
		List<String> backCntList = new ArrayList<String>();//用于存储支付不存在条目
		try {
			for (AcLoanBackLog acLoanBackLog : acLoanBackLogList) {
				count = count + 1;
				String backCnt = acLoanBackLog.getBackCnt();// 条目号
				backCntList.add(backCnt);
				zfB100007 = new ZfB100007();
				zfB100007.setNo(backCnt);
				buf.append(zfB100007.toXml());
				if (count % 50 == 0) {//每50条发送一次
					pageNo = pageNo + 1;
					payInfoList = sendZfB100007(count, buf.toString());
					analyBack(payInfoList,backCntList);
					count = 0;
					buf = new StringBuilder();
					backCntList = new ArrayList<String>();
				}
				
			}
			int sendNum = pageNo*50;//已发送的条数
			if(sendNum<listSize){//发送剩余的
				buf = new StringBuilder();
				List<AcLoanBackLog> acLoanBackLogListSur = acLoanBackLogList.subList(sendNum, listSize);
				backCntList = new ArrayList<String>();
				for(AcLoanBackLog acLoanBackLog : acLoanBackLogListSur){
					String backCnt = acLoanBackLog.getBackCnt();// 条目号
					backCntList.add(backCnt);
					zfB100007 = new ZfB100007();
					zfB100007.setNo(backCnt);
					buf.append(zfB100007.toXml());
					
				}
				payInfoList = sendZfB100007(listSize-sendNum, buf.toString());
				analyBack(payInfoList,backCntList);
			}
		} catch (AccountingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} 
	}
	
	public  List<PayInfo> sendZfB100007(int count,String bodyXml) throws AccountingException{
		Connection conn = DBUtils.getConn();
		//返回数据
		List<PayInfo> payInfoList = null;
		String uuid = CreateKey.getUUID();
		try {
			ZFHeadQuery zfHeadQuery = new ZFHeadQuery();
			zfHeadQuery.setRequestType("B100007");
			zfHeadQuery.setUUID(CachecodeUtil.MFSPREFIX+uuid);// 流水号
			zfHeadQuery.setComId(CachecodeUtil.MFSPREFIX);
			zfHeadQuery.setComIP("11");
			zfHeadQuery.setSendTime(ParmBiz.getOracleUpDate2(conn));
			zfHeadQuery.setAsyn("0");
			zfHeadQuery.setReturnUrl("");
			zfHeadQuery.setSigned("");
			zfHeadQuery.setClientType("0");
			zfHeadQuery.setPageSize(count);//每页条数
			zfHeadQuery.setPageNo(1);//页数
			String headXml = zfHeadQuery.toXml(); // 头文件
			ZfBodyQuery zfBodyQuery = new ZfBodyQuery();
			
			zfBodyQuery.setQueryType("4");//按交易条目号查询

			String xml = headXml + zfBodyQuery.toXml()+ bodyXml + "</QueryList></Body></Package>";

			QueryService queryService = (QueryService) SourceTemplate
					.getSpringContextInstance().getBean("queryService");
			String result = "";

			result = queryService.doAction("B100007", xml);
			
			payInfoList = readZfXmlB100007(result);

			WsBase wsBase = new WsBase();
			wsBase.setWsDate(ParmBiz.getOracleTxDate(conn));
			wsBase.setWsTime(ParmBiz.getOracleUpDate(conn));
			wsBase.setTxCode("B100007");
			wsBase.setWsSerial(CachecodeUtil.MFSPREFIX+uuid);
			wsBase.setRespDesc("轮询对账查询");
			wsBase.setWsSts("01");
			wsBase.setReqContent(xml);
			wsBase.setRespContent(result);

			wsBaseDao.insert(wsBase);
		} catch (Exception_Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		return payInfoList ;
	
	}
	
	public List<PayInfo> readZfXmlB100007(String xml) {
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Package><Header><RequestType>B100007</RequestType><UUID>流水号</UUID><ComId>业务系统编号</ComId><SendTime>请求发送时间</SendTime><Signed></Signed><ResultCode>10000</ResultCode><ResultMsg>成功</ResultMsg><MaxPage>100</MaxPage><PageSize>50</PageSize><PageNo>1</PageNo></Header><Body><PayInfoList><PayInfo><FlowNo>0000031000000880</FlowNo><TradeNo>000003120000000055092</TradeNo><TradeTypeNo>001</TradeTypeNo><EntryNo>1000000412</EntryNo><EntryTypeNo>00101</EntryTypeNo><RpayMoney>300000</RpayMoney><PayMoney>300000</PayMoney><EnTradeType>02</EnTradeType><PayTime>2016-12-23 10:03:55</PayTime><Status>32000</Status><Message>处理失败啦</Message></PayInfo></PayInfoList></Body></Package>";
		Document doc = null;
		List<PayInfo> payInfoList = null;
		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			Element rootElt = doc.getRootElement(); // 获取根节点
			Iterator iter = rootElt.elementIterator("Body"); // 获取根节点下的子节点Body
			Element recordEle = (Element) iter.next();
			Iterator iterpayInfoList = recordEle.elementIterator("PayInfoList");
			PayInfo payInfo = null;
			while(iterpayInfoList.hasNext()){
				recordEle = (Element)iterpayInfoList.next();
				Iterator iterPayInfo = recordEle.elementIterator("PayInfo"); 
				payInfoList = new ArrayList<PayInfo>();
				while(iterPayInfo.hasNext()){
					recordEle = (Element) iterPayInfo.next();
					payInfo = new PayInfo();
					payInfo.setFlowNo(recordEle.elementTextTrim("EntryInfo"));//UUID
					payInfo.setTradeNo(recordEle.elementTextTrim("TradeNo").substring(CachecodeUtil.MFSPREFIX.length()));//流水号
					payInfo.setTradeChannel(recordEle.elementTextTrim("TradeChannel"));
					payInfo.setTradeTypeNo(recordEle.elementTextTrim("TradeTypeNo"));//交易类型
					payInfo.setEntryNo(recordEle.elementTextTrim("EntryNo").substring(CachecodeUtil.MFSPREFIX.length()));//条目号
					payInfo.setEntryTypeNo(recordEle.elementTextTrim("EntryTypeNo"));//条目类型
					payInfo.setRpayMoney(Double.parseDouble(recordEle.elementTextTrim("RpayMoney"))/100);
					payInfo.setPayMoney(Double.parseDouble(recordEle.elementTextTrim("PayMoney"))/100);
					payInfo.setEnTradeType(recordEle.elementTextTrim("EnTradeType"));
					payInfo.setPayTime(recordEle.elementTextTrim("PayTime"));
					payInfo.setStatus(recordEle.elementTextTrim("Status"));
					payInfo.setMessage(recordEle.elementTextTrim("Message"));
					payInfoList.add(payInfo);
				}
			}
		} catch (DocumentException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return payInfoList;
	}
	
	/**
	 * 根据支付返回报文做响应操作
	 * @param payInfoList
	 */
	public void analyBack(List<PayInfo> payInfoList,List<String> backCntList){
		
		Connection conn = DBUtils.getConn();
		
		AcLoanBackLog albl = null;
		try {
			if(payInfoList!=null&&payInfoList.size()>0){
				for (PayInfo PayInfo : payInfoList) {
					albl = new AcLoanBackLog();
					String entryNo = PayInfo.getEntryNo();//条目号
					backCntList.remove(entryNo);//移除支付查询到的条目号
					albl.setBackCnt(entryNo);
					albl = acLoanBackLogDao.getByBackCnt(albl);
					if(!PUBConstant.BACK_STS_06.equals(albl.getBackSts())){//01 待发送 02 发送成功 03 发送异常 04 发送失败 05 异常回调 06 成功回调;已经成功回调不再处理
						//更新未处理为处理状态
						int result = 0;
						String resultSts = PayInfo.getStatus();// 返回状态
						int status = Integer.parseInt(resultSts);
						String resultMessage = PayInfo.getMessage();// 返回描述信息
						String cardChn = PayInfo.getTradeChannel();//获取支付渠道 等待支付返回报文加上支付渠道
						// 排除支付无明细情况
						if (resultSts != null && resultSts.length() > 0) {
							String sql = "UPDATE AC_LOAN_BACK_LOG SET BACK_STS = '"+PUBConstant.BACK_STS_06+"'";
							// 字典转换
							if (status >= 10000 && status < 20000) {// 反馈为1XXXX时，则为交易成功
								resultSts = "01";
//								albl.setBackSts(PUBConstant.BACK_STS_06);
//								result = JdbcDao.update(albl, "back_log_no='"+albl.getBackLogNo()+"' and back_cnt='"+entryNo+"' and back_sts <> '"+PUBConstant.BACK_STS_06+"'", "ac_loan_back_log", conn);
								result = JdbcDao.updateSql(sql+" WHERE back_log_no='"+albl.getBackLogNo()+"' and back_cnt='"+entryNo+"' and back_sts <> '"+PUBConstant.BACK_STS_06+"'", conn);
								if(result>0){
									conn.commit();
								}else{
									continue;
								}
							} else if (status >= 20000 && status < 31000) {// 反馈为31XXX时，则为交易未决
								resultSts = "03";
							}else if(99999==status){
								resultSts = "04";//支付返回99999为未决不处理
							}else {// 其他则为失败
								resultSts = "02";
//								albl.setBackSts(PUBConstant.BACK_STS_06);
//								result = JdbcDao.update(albl, "back_log_no='"+albl.getBackLogNo()+"' and back_cnt='"+entryNo+"' and back_sts <> '"+PUBConstant.BACK_STS_06+"'", "ac_loan_back_log", conn);
								result = JdbcDao.updateSql(sql+" WHERE back_log_no='"+albl.getBackLogNo()+"' and back_cnt='"+entryNo+"' and back_sts <> '"+PUBConstant.BACK_STS_06+"'", conn);
								if(result>0){
									conn.commit();
								}else{
									continue;
								}
							}
							albl.setBackRes(resultSts);
							albl.setFailCaus(resultMessage);
							albl.setStatus(PayInfo.getStatus());
							albl.setCardChn(cardChn);
							// 相当于支付回盘正常处理
							dealZfBack(albl, conn);
						}
					}

				}
			}
			//支付系统中没有查询到的条目号，默认返回码为99990
			if(backCntList!=null&&backCntList.size()>0){
				for(String backCnt : backCntList){
					albl = new AcLoanBackLog();
					albl.setBackCnt(backCnt);
					albl = acLoanBackLogDao.getByBackCnt(albl);
					if(!PUBConstant.BACK_STS_06.equals(albl.getBackSts())){
						String resultMessage = "支付不存在条目";// 返回描述信息
						albl.setBackRes("02");
						albl.setFailCaus(resultMessage);
						albl.setStatus("99990");
						// 相当于支付回盘正常处理
						dealZfBack(albl, conn);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	/**
	 * 
	 * @throws Exception 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-14
	 * @描述	发送第三方支付放款
	 */
	public  Map sendZfMes(List<AcLoanBackLog> acLoanBackLogList,String projNo,String traceNo,Connection conn) throws Exception {
		ZFHead zfHead = new ZFHead();
		zfHead.setRequestType("P100001");
		String uuid = acLoanBackLogList.get(0).getUuid();
		zfHead.setUUID(uuid);//接口流水号
		zfHead.setComId(CachecodeUtil.MFSPREFIX);
		zfHead.setComIP("11");
		zfHead.setSendTime(ParmBiz.getOracleUpDate2(conn));
		zfHead.setAsyn("0");
		Properties pathProperties = new Properties();
		pathProperties.load(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("application.properties"));
		String zfReturnUrl = pathProperties.getProperty("zfloanreturn.url");
		zfHead.setReturnUrl(zfReturnUrl);
		zfHead.setSigned("");
		zfHead.setClientType("0");
		zfHead.setPayCount(1);
		String headXml = XMLUtil.createHead(zfHead); // 头文件
		
		BodyLoan bodyLoan = new BodyLoan();
		
		List<TradeInfo> tradeList = new ArrayList<TradeInfo>();
		
		TradeInfo tradeInfo = new TradeInfo();
		
		tradeInfo.setTradeNo(CachecodeUtil.MFSPREFIX+traceNo);
		tradeInfo.setTradeTypeNo(acLoanBackLogList.get(0).getBusOrderType());
		tradeInfo.setTotalEntryNum(acLoanBackLogList.size());
		
		EntryInfo entryInfo = null;
		List<EntryInfo> entryList = new ArrayList<EntryInfo>();

		//放款信息
		AcLoanLog acLoanLog = null;
		//放款信息List
		List<AcLoanLog> acLoanLogList = new ArrayList<AcLoanLog>();
		//扣款信息
		AcDebit acDebit = null;
		//扣款信息list
		List<AcDebit> acDebitList = new ArrayList<AcDebit>();
		//账户信息
		AccountInfo accountInfo = null;
		
		SourceAccountInfo sourceAccountInfo = new SourceAccountInfo();//源
		TargetAccountInfo targetAccountInfo = new TargetAccountInfo();//目的
		
		for (AcLoanBackLog acLoanBackLog : acLoanBackLogList) {
			if(PUBConstant.BACK_STS_01.equals(acLoanBackLog.getBackType())){//放款
				/*//获取放款信息
				acLoanLog = new AcLoanLog();
				acLoanLog.setLoanLogNo(acLoanBackLog.getLoanLogNo());
				acLoanLog = acLoanLogDao.getById(acLoanLog);
				acLoanLogList.add(acLoanLog);
				
				entryInfo = new EntryInfo();
				
				List<SourceAccountInfo> sourceAccountInfoListfk = new ArrayList<SourceAccountInfo>();
				List<TargetAccountInfo> targetAccountInfoListfk = new ArrayList<TargetAccountInfo>();
				
				sourceAccountInfo = new SourceAccountInfo();// 源
				targetAccountInfo = new TargetAccountInfo();// 目的

				entryInfo.setEntryNo(acLoanBackLog.getBackCnt());
				entryInfo.setEnTradeType("01");// 放款
				entryInfo.setAmount(acLoanLog.getLoanAmt());
				entryInfo.setEntryTypeNo(acLoanBackLog.getBusEntryType());
				entryInfo.setChannelNo(acLoanLog.getCardChn());

				accountInfo = new AccountInfo();
				// 源
				accountInfo.setAccountNo(acLoanLog.getXtAcNo());
				accountInfo.setAccountType("0");
				sourceAccountInfo.setLevel("01");
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListfk.add(sourceAccountInfo);

				accountInfo = new AccountInfo();

				// 目的
				accountInfo.setAccountNo(acLoanLog.getLoanAcNo());
				accountInfo.setAccountType("0");
				targetAccountInfo.setLevel("01");
				targetAccountInfo.setAccountInfo(accountInfo);
				targetAccountInfoListfk.add(targetAccountInfo);
				entryInfo.setRemark1(acLoanLog.getBrNo());
				entryInfo.setRemark2(projNo);
				entryInfo.setRemark3(acLoanLog.getPactNo());
				entryInfo.setSourceAccountList(sourceAccountInfoListfk);
				entryInfo.setTargetAccountList(targetAccountInfoListfk);

				entryList.add(entryInfo);*/
			}else{
				acDebit = new AcDebit();
				acDebit.setDebitNo(acLoanBackLog.getLoanLogNo());
				acDebit = acDebitDao.getByDebitNo(acDebit);
				acDebitList.add(acDebit);
				
				entryInfo = new EntryInfo();
				
				entryInfo.setEntryNo(acLoanBackLog.getBackCnt());
				entryInfo.setEnTradeType("02");//扣款
				entryInfo.setAmount(acDebit.getAtpyAmt());
				entryInfo.setEntryTypeNo(acDebit.getBusEntryType());
				entryInfo.setChannelNo(acDebit.getCardChn());
//				entryInfo.setChannelNo("CL0002");
				
				accountInfo = new AccountInfo();
				
				List<SourceAccountInfo> sourceAccountInfoListsk = new ArrayList<SourceAccountInfo>();
				List<TargetAccountInfo> targetAccountInfoListsk = new ArrayList<TargetAccountInfo>();
				
				/*sourceAccountInfo = new SourceAccountInfo();//源1
				targetAccountInfo = new TargetAccountInfo();//目的
				//源：客户卡（溢缴款账户，银行卡） 目的：项目账户
				accountInfo.setAccountNo(CachecodeUtil.MFSPREFIX+"_"+acDebit.getBrNo()+"_"+acDebit.getPactNo());//支付平台账户
				accountInfo.setAccountType("0");
				sourceAccountInfo.setLevel("01");
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListsk.add(sourceAccountInfo);*/
				
				accountInfo = new AccountInfo();
				
				sourceAccountInfo = new SourceAccountInfo();//源2
				
				accountInfo.setAccountNo(acDebit.getAcNo());//银行卡
				if("10".equals(acDebit.getAcType())){//个人贷记卡
					accountInfo.setAccountType("3");
				}else if("11".equals(acDebit.getAcType())){//个人借记卡
					accountInfo.setAccountType("1");
				}else if("12".equals(acDebit.getAcType())||"13".equals(acDebit.getAcType())||"14".equals(acDebit.getAcType())){//企业账户（自如）
					accountInfo.setAccountType("0");
				}
				accountInfo.setAccountName(acDebit.getAcName());
				accountInfo.setChannelNo(acDebit.getAcctBankCde());
				accountInfo.setBankDetailNo("");
				accountInfo.setBankName(acDebit.getBankSite());
				accountInfo.setProvince(acDebit.getBankProv());
				accountInfo.setCity(acDebit.getBankCity());
				accountInfo.setCVN2(acDebit.getCvvNo()!=null?acDebit.getCvvNo():"");
				accountInfo.setVALDATE(acDebit.getValidDate()!=null?acDebit.getValidDate():"");
				accountInfo.setPhoneNo(acDebit.getPhoneNo()!=null?acDebit.getPhoneNo():"");
				accountInfo.setEmail("");
				accountInfo.setCertificateType(acDebit.getIdType()!=null?acDebit.getIdType():"");
				accountInfo.setCertificateNo(acDebit.getIdNo()!=null?acDebit.getIdNo():"");
				sourceAccountInfo.setLevel("01");
				sourceAccountInfo.setAccountInfo(accountInfo);
				sourceAccountInfoListsk.add(sourceAccountInfo);
				
				accountInfo = new AccountInfo();
				
//				accountInfo.setAccountNo(acDebit.getXtAcNo());//项目收款账户
				accountInfo.setAccountNo(CachecodeUtil.MFSPREFIX+"_"+PUBConstant.ACCT_TYPE_04+"_"+projNo);//项目收款账户
				accountInfo.setAccountType("0");
				targetAccountInfo.setLevel("01");
				targetAccountInfo.setAccountInfo(accountInfo);
				targetAccountInfoListsk.add(targetAccountInfo);
				
				entryInfo.setRemark1(acDebit.getBrNo());
				entryInfo.setRemark2(projNo);
				entryInfo.setRemark3(acDebit.getPactNo());
				entryInfo.setSourceAccountList(sourceAccountInfoListsk);
				entryInfo.setTargetAccountList(targetAccountInfoListsk);
				entryList.add(entryInfo);
			}
			
		}
		tradeInfo.setEntryList(entryList);
		tradeList.add(tradeInfo);
		bodyLoan.setTradeList(tradeList);
		String bodyXml = XMLUtil.createBody(bodyLoan);// 转换为XML

		String xml = headXml + bodyXml + "</Package>";

		Jedis jedis = RedisUtil.getJedis();
		jedis.lpush(RedisUtil.QUEUE7, xml);// 
		jedis.close();
		return null ;
	}
	public List<WsRepyMes> getByBatch(String batchNo) throws ServiceException {
		List<WsRepyMes> wsRepyMesList = new ArrayList<WsRepyMes>();
		try {
			wsRepyMesList = wsRepyMesDao.getByBatch(batchNo);
			for(WsRepyMes WsRepyMes : wsRepyMesList){
				Jedis jedis = RedisUtil.getJedis();
    			jedis.lpush(RedisUtil.QUEUE10, WsRepyMes.getWsId());// 
    			jedis.close();
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsRepyMesList;
	}

	 /**

   * @description 将xml字符串转换成map

   * @param xml

   * @return Map

   */

	public static Map readStringXmlOut(String xml) {

		Map map = new HashMap();

		Document doc = null;

		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点

			Iterator iter = rootElt.elementIterator("Header"); // 获取根节点下的子节点header

			Element recordEle = (Element) iter.next();

			String RequestType = recordEle.elementTextTrim("RequestType");
			map.put("RequestType", RequestType);

			String UUID = recordEle.elementTextTrim("UUID");
			map.put("UUID", UUID);

			String ComId = recordEle.elementTextTrim("ComId");
			map.put("ComId", ComId);

			String SendTime = recordEle.elementTextTrim("SendTime");
			map.put("SendTime", SendTime);

			String Signed = recordEle.elementTextTrim("Signed");
			map.put("Signed", Signed);

			String ResultCode = recordEle.elementTextTrim("ResultCode");
			map.put("ResultCode", ResultCode);

			String ResultMsg = recordEle.elementTextTrim("ResultMsg");
			map.put("ResultMsg", ResultMsg);

		} catch (DocumentException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return map;

	}
	

	 /**

  * @description 将xml字符串转换成map

  * @param xml

  * @return Map

  */

	public static Map readZfXmlB100003(String xml) {

		Map map = new HashMap();

		Document doc = null;

		try {
			doc = DocumentHelper.parseText(xml); // 将字符串转为XML

			Element rootElt = doc.getRootElement(); // 获取根节点

			Iterator iter = rootElt.elementIterator("Body"); // 获取根节点下的子节点Body

			Element recordEle = (Element) iter.next();
			
			Iterator iterPayInfo = recordEle.elementIterator("PayInfo"); 

			recordEle = (Element) iterPayInfo.next();

			String BatchNo = recordEle.elementTextTrim("BatchNo");
			map.put("BatchNo", BatchNo);

			String OrderNo = recordEle.elementTextTrim("OrderNo");
			map.put("OrderNo", OrderNo);

			String BusinessOrderType = recordEle.elementTextTrim("BusinessOrderType");
			map.put("BusinessOrderType", BusinessOrderType);

			String EntryNo = recordEle.elementTextTrim("EntryNo");
			map.put("EntryNo", EntryNo);

			String BusinessEntryNo = recordEle.elementTextTrim("BusinessEntryNo");
			map.put("BusinessEntryNo", BusinessEntryNo);

			String Amount = recordEle.elementTextTrim("Amount");
			map.put("Amount", Amount);

			String PayChannel = recordEle.elementTextTrim("PayChannel");
			map.put("PayChannel", PayChannel);
			
			String TradeType = recordEle.elementTextTrim("TradeType");
			map.put("TradeType", TradeType);
			
			String PayTime = recordEle.elementTextTrim("PayTime");
			map.put("PayTime", PayTime);
			
			String Status = recordEle.elementTextTrim("Status");
			map.put("Status", Status);
			
			String Message = recordEle.elementTextTrim("Message");
			map.put("Message", Message);

		} catch (DocumentException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return map;

	}
	private Connection getConnection() {

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return conn;
	}
	public void del(AcDebitSusp acDebitSusp) throws ServiceException {
		try {
			acDebitSuspDao.del(acDebitSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcDebitSusp acDebitSusp)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acDebitSuspDao
					.getCount(acDebitSusp) });// 初始化分页类
			acDebitSusp.setStartNumAndEndNum(ipg);
			ipg.setResult(acDebitSuspDao.findByPage(acDebitSusp));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcDebitSusp getById(AcDebitSusp acDebitSusp) throws ServiceException {
		try {
			acDebitSusp = acDebitSuspDao.getById(acDebitSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acDebitSusp;
	}

	public void insert(AcDebitSusp acDebitSusp) throws ServiceException {
		try {
			acDebitSuspDao.insert(acDebitSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcDebitSusp acDebitSusp) throws ServiceException {
		try {
			acDebitSuspDao.update(acDebitSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcDebitSuspDao getAcDebitSuspDao() {
		return acDebitSuspDao;
	}

	public void setAcDebitSuspDao(AcDebitSuspDao acDebitSuspDao) {
		this.acDebitSuspDao = acDebitSuspDao;
	}

	public WsRepyMesDao getWsRepyMesDao() {
		return wsRepyMesDao;
	}

	public void setWsRepyMesDao(WsRepyMesDao wsRepyMesDao) {
		this.wsRepyMesDao = wsRepyMesDao;
	}

	public AcLnRepayPlnCurDao getAcLnRepayPlnCurDao() {
		return acLnRepayPlnCurDao;
	}

	public void setAcLnRepayPlnCurDao(AcLnRepayPlnCurDao acLnRepayPlnCurDao) {
		this.acLnRepayPlnCurDao = acLnRepayPlnCurDao;
	}

	public AcLnMstDao getAcLnMstDao() {
		return acLnMstDao;
	}

	public void setAcLnMstDao(AcLnMstDao acLnMstDao) {
		this.acLnMstDao = acLnMstDao;
	}

	public AcLnLoDao getAcLnLoDao() {
		return acLnLoDao;
	}

	public void setAcLnLoDao(AcLnLoDao acLnLoDao) {
		this.acLnLoDao = acLnLoDao;
	}

	public AftRepyClearDao getAftRepyClearDao() {
		return aftRepyClearDao;
	}

	public void setAftRepyClearDao(AftRepyClearDao aftRepyClearDao) {
		this.aftRepyClearDao = aftRepyClearDao;
	}

	public AcTraceLogDao getAcTraceLogDao() {
		return acTraceLogDao;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public LnDueDao getLnDueDao() {
		return lnDueDao;
	}

	public AcDebitDao getAcDebitDao() {
		return acDebitDao;
	}

	public ProjAcctDao getProjAcctDao() {
		return projAcctDao;
	}

	public void setAcTraceLogDao(AcTraceLogDao acTraceLogDao) {
		this.acTraceLogDao = acTraceLogDao;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setLnDueDao(LnDueDao lnDueDao) {
		this.lnDueDao = lnDueDao;
	}

	public void setAcDebitDao(AcDebitDao acDebitDao) {
		this.acDebitDao = acDebitDao;
	}

	public void setProjAcctDao(ProjAcctDao projAcctDao) {
		this.projAcctDao = projAcctDao;
	}

	public AcLoanBackLogDao getAcLoanBackLogDao() {
		return acLoanBackLogDao;
	}

	public CorpAcctDao getCorpAcctDao() {
		return corpAcctDao;
	}

	public void setAcLoanBackLogDao(AcLoanBackLogDao acLoanBackLogDao) {
		this.acLoanBackLogDao = acLoanBackLogDao;
	}

	public void setCorpAcctDao(CorpAcctDao corpAcctDao) {
		this.corpAcctDao = corpAcctDao;
	}

	public LnAcctDao getLnAcctDao() {
		return lnAcctDao;
	}

	public void setLnAcctDao(LnAcctDao lnAcctDao) {
		this.lnAcctDao = lnAcctDao;
	}

	public AcLoanLogDao getAcLoanLogDao() {
		return acLoanLogDao;
	}

	public void setAcLoanLogDao(AcLoanLogDao acLoanLogDao) {
		this.acLoanLogDao = acLoanLogDao;
	}
	public WsBaseDao getWsBaseDao() {
		return wsBaseDao;
	}
	public void setWsBaseDao(WsBaseDao wsBaseDao) {
		this.wsBaseDao = wsBaseDao;
	}

	public AcLnPmLogDao getAcLnPmLogDao() {
		return acLnPmLogDao;
	}

	public AcChrgLogDao getAcChrgLogDao() {
		return acChrgLogDao;
	}

	public void setAcLnPmLogDao(AcLnPmLogDao acLnPmLogDao) {
		this.acLnPmLogDao = acLnPmLogDao;
	}

	public void setAcChrgLogDao(AcChrgLogDao acChrgLogDao) {
		this.acChrgLogDao = acChrgLogDao;
	}

	public AcLnRepayPlnDao getAcLnRepayPlnDao() {
		return acLnRepayPlnDao;
	}

	public void setAcLnRepayPlnDao(AcLnRepayPlnDao acLnRepayPlnDao) {
		this.acLnRepayPlnDao = acLnRepayPlnDao;
	}

	public AcDebit getAcDebit() {
		return acDebit;
	}

	public void setAcDebit(AcDebit acDebit) {
		this.acDebit = acDebit;
	}

	public ProjBase getProjBase() {
		return projBase;
	}

	public void setProjBase(ProjBase projBase) {
		this.projBase = projBase;
	}

	public ProjBaseDao getProjBaseDao() {
		return projBaseDao;
	}

	public void setProjBaseDao(ProjBaseDao projBaseDao) {
		this.projBaseDao = projBaseDao;
	}

	public WsRepyFineDao getWsRepyFineDao() {
		return wsRepyFineDao;
	}

	public void setWsRepyFineDao(WsRepyFineDao wsRepyFineDao) {
		this.wsRepyFineDao = wsRepyFineDao;
	}


	public WsRepyClearDao getWsRepyClearDao() {
		return wsRepyClearDao;
	}


	public void setWsRepyClearDao(WsRepyClearDao wsRepyClearDao) {
		this.wsRepyClearDao = wsRepyClearDao;
	}


	public WsRepyMes getWsRepyMes() {
		return wsRepyMes;
	}


	public void setWsRepyMes(WsRepyMes wsRepyMes) {
		this.wsRepyMes = wsRepyMes;
	}


	public WsRepyFeeDao getWsRepyFeeDao() {
		return wsRepyFeeDao;
	}


	public void setWsRepyFeeDao(WsRepyFeeDao wsRepyFeeDao) {
		this.wsRepyFeeDao = wsRepyFeeDao;
	}
}