package  app.creditapp.aft.bo.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import accounting.biz.loan.RepayBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.sys.OperaInfo;
import accounting.interf.loan.AcLoanBack;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import accounting.plat.core.Operation;
import accounting.plat.core.OperationFactory;
import accounting.plat.util.NumberUtil;
import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.loan.dao.AcDebitDao;
import app.creditapp.acc.loan.dao.AcDebitSuspDao;
import app.creditapp.acc.loan.dao.AcLnLoDao;
import app.creditapp.acc.loan.dao.AcLnMstDao;
import app.creditapp.acc.loan.dao.AcLnRepayPlnCurDao;
import app.creditapp.acc.loan.dao.AcLoanBackLogDao;
import app.creditapp.acc.loan.dao.AcLoanLogDao;
import app.creditapp.acc.loan.entity.AcDebit;
import app.creditapp.acc.loan.entity.AcDebitSusp;
import app.creditapp.acc.loan.entity.AcLnLo;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.loan.entity.AcLnRepayPlnCur;
import app.creditapp.acc.loan.entity.AcLoanBackLog;
import app.creditapp.acc.loan.entity.AcLoanLog;
import app.creditapp.acc.log.dao.AcTraceLogDao;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.acc.option.dao.AcLnRepayPlnDao;
import app.creditapp.acc.option.entity.AcLnRepayPln;
import app.creditapp.aft.bo.AftRepyClearBo;
import app.creditapp.aft.dao.AftRepyClearDao;
import app.creditapp.aft.entity.AftRepyClear;
import app.creditapp.corp.dao.CorpAcctDao;
import app.creditapp.corp.dao.CorpBaseDao;
import app.creditapp.corp.entity.CorpAcct;
import app.creditapp.corp.entity.CorpBase;
import app.creditapp.inf.client.zf.Exception_Exception;
import app.creditapp.inf.dao.WsBaseDao;
import app.creditapp.inf.dao.WsRepyClearDao;
import app.creditapp.inf.dao.WsRepyFineDao;
import app.creditapp.inf.entity.WsRepyClear;
import app.creditapp.inf.entity.WsRepyFine;
import app.creditapp.ln.dao.LnAcctDao;
import app.creditapp.ln.dao.LnDueDao;
import app.creditapp.ln.entity.LnAcct;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.proj.dao.ProjAcctDao;
import app.creditapp.proj.dao.ProjBaseDao;
import app.creditapp.proj.entity.ProjAcct;
import app.creditapp.proj.entity.ProjBase;
import app.util.DBUtils;
import app.util.toolkit.Ipage;
/**
* Title: AftRepyClearBoImplImpl.java
**/
public class AftRepyClearBoImpl extends BaseService implements AftRepyClearBo {

	private AftRepyClearDao aftRepyClearDao;
	private AcLnMstDao acLnMstDao;
	private AcLnRepayPlnCurDao acLnRepayPlnCurDao;
	private WsRepyClearDao wsRepyClearDao;
	private LnDueDao lnDueDao;
	private LnAcctDao lnAcctDao;
	private	AcDebitSuspDao acDebitSuspDao;
	private AcTraceLogDao acTraceLogDao;
	private ProjAcctDao projAcctDao;
	private AcDebitDao acDebitDao;
	private AcLoanBackLogDao acLoanBackLogDao;
	private CorpAcctDao corpAcctDao;
	private AcLoanLogDao acLoanLogDao;
	private WsBaseDao wsBaseDao;
	private ProjBase projBase;
	private ProjBaseDao projBaseDao;
	private WsRepyFineDao wsRepyFineDao;
	private AcLnLoDao acLnLoDao;
	private AcLnRepayPlnDao acLnRepayPlnDao;
	private CorpBaseDao corpBaseDao;
	
	public void del(AftRepyClear aftRepyClear) throws ServiceException {
		try {
			aftRepyClearDao.del(aftRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftRepyClear aftRepyClear)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRepyClearDao
					.getCount(aftRepyClear) });// 初始化分页类
			aftRepyClear.setStartNumAndEndNum (ipg);
			ipg.setResult(aftRepyClearDao.findByPage(aftRepyClear));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftRepyClear getById(AftRepyClear aftRepyClear) throws ServiceException {
		try {
			aftRepyClear = aftRepyClearDao.getById(aftRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftRepyClear;
	}

	public void insert(AftRepyClear aftRepyClear) throws ServiceException {
		try {
			aftRepyClearDao.insert(aftRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-10-14
	 * @描述	提前清贷实时查询交易
	 */
	public WsRepyClear selectClearResult(WsRepyClear wsRepyClear) throws ServiceException{
		AftRepyClear aftRepyClear = new AftRepyClear();
		aftRepyClear.setWsId(wsRepyClear.getWsId());
		aftRepyClear = aftRepyClearDao.getByWsId(aftRepyClear);
		
		AcLnMst acLnMst = new AcLnMst();
		acLnMst.setPactNo(aftRepyClear.getPactNo());
		acLnMst.setBrNo(aftRepyClear.getBrNo());
		acLnMst = acLnMstDao.getByPactNo(acLnMst);
		
		double exact = 0.0001;//精确小数
		if(acLnMst.getLoanBal()<exact && acLnMst.getLoanBal()>-exact ){//处理成功
			aftRepyClear.setClearSts("02");
			aftRepyClear.setDealDesc("处理成功");
			aftRepyClearDao.update(aftRepyClear);
			
			wsRepyClear.setDealSts("03");
			wsRepyClear.setDealDesc("处理成功");
			wsRepyClearDao.update(wsRepyClear);
		}
		return wsRepyClear;
				
	}
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-23
	 * @描述	提前清贷申请从WS表向SUSP表过度
	 */
	public WsRepyClear WsInsert(WsRepyClear wsRepyClear) throws ServiceException{
		boolean result = false;
		Connection conn = DBUtils.getConn();
		try {
			AcLnRepayPlnCur acLnRepayPlnCur = new AcLnRepayPlnCur();
			AcLnMst acLnMst = new AcLnMst();
			String pactNo = wsRepyClear.getPactNo();// 合同号
			String brNo = wsRepyClear.getBrNo();// 机构号

			double repayAmt = wsRepyClear.getRepayAmt();// 扣款本金
			double repayInte = wsRepyClear.getRepayInte();// 扣款利息

			// 分别放到贷款主文件的实体 查询用
			acLnMst.setPactNo(pactNo);
			acLnMst.setBrNo(brNo);
			acLnMst = acLnMstDao.getByPactNo(acLnMst);
			if (acLnMst == null) {
				// 更新WS进件表失败
				wsRepyClear.setDealDesc("贷款主文件不存在");
				wsRepyClear.setDealSts("05");
				wsRepyClearDao.update(wsRepyClear);
				return wsRepyClear;
			} else if("01".equals(acLnMst.getLoanSts()) ||"02".equals(acLnMst.getLoanSts()) 
						||"03".equals(acLnMst.getLoanSts()) ||"04".equals(acLnMst.getLoanSts())){//主文件状态为01/02/03/04才能扣款
			}else{
				//更新WS进件表失败
				wsRepyClear.setDealDesc("贷款主文件非正常或逾期");
				wsRepyClear.setDealSts("05");
				wsRepyClearDao.update(wsRepyClear);
				return wsRepyClear;
			}
			// 分别放到当期还款计划表的实体 查询用
			acLnRepayPlnCur.setPactNo(pactNo);
			acLnRepayPlnCur.setBrNo(brNo);
			acLnRepayPlnCur = acLnRepayPlnCurDao.getByPactNo(acLnRepayPlnCur);
			if(acLnRepayPlnCur == null){
				// 更新WS进件表失败
				wsRepyClear.setDealDesc("还款计划不存在，不能发起提前清贷申请！");
				wsRepyClear.setDealSts("05");
				wsRepyClearDao.update(wsRepyClear);
				return wsRepyClear;
			}
			
			//查询处理中的提前清贷
			WsRepyClear wsRepyClearIng = new WsRepyClear();
			wsRepyClearIng = wsRepyClearDao.getDealIngByPactno(wsRepyClear);
			if(wsRepyClearIng!=null){
				wsRepyClear.setDealDesc("存在处理中的提前清贷申请，不能重复发起！");
				wsRepyClear.setDealSts("05");
				wsRepyClearDao.update(wsRepyClear);
				return wsRepyClear;
			}
			
			// 查询借据信息
			LnDue lnDue = new LnDue();
			lnDue.setDueNo(acLnMst.getLoanNo());
			lnDue = lnDueDao.getById(lnDue);

			// 根据扣款账号查询 扣款信息
			LnAcct lnAcct = new LnAcct();
			lnAcct.setAppId(lnDue.getAppId());
			lnAcct.setAcNo(wsRepyClear.getAcNo());
			lnAcct.setAcUse("1");// 扣款
			lnAcct = lnAcctDao.getById(lnAcct);

			if (lnAcct == null) {// 扣款账号 不存在则拒收该扣款请求
				// 更新WS进件表失败
				wsRepyClear.setDealDesc("扣款账号不存在");
				wsRepyClear.setDealSts("05");
				wsRepyClearDao.update(wsRepyClear);
				return wsRepyClear;
			} else if (!"01".equals(lnAcct.getAcSts())) {// 扣款账号未生效，则拒收扣款请求
				wsRepyClear.setDealDesc("扣款账号未生效");
				wsRepyClear.setDealSts("05");
				wsRepyClearDao.update(wsRepyClear);
				return wsRepyClear;
			}

			//更新应收罚息
			WsRepyFine wsRepyFine = new WsRepyFine();
			wsRepyFine.setWsId(wsRepyClear.getWsId());
			List<WsRepyFine> wsRepyFineList = wsRepyFineDao.getListByWsId(wsRepyFine);
			AcLnRepayPln acLnRepayPln = null;
			AcLnLo alo = null;
			for(WsRepyFine wrf : wsRepyFineList){
				//更新还款计划表应收罚息
				acLnRepayPln = new AcLnRepayPln();
				acLnRepayPln.setLoanNo(acLnMst.getLoanNo());
				acLnRepayPln.setPerdNo(wrf.getCnt());
				acLnRepayPln = acLnRepayPlnDao.getById(acLnRepayPln);
				if(acLnRepayPln != null){
					acLnRepayPln.setFineInt(wrf.getTxPayOver());
					acLnRepayPlnDao.update(acLnRepayPln);
				}
				//更新欠款表应收罚息
				alo = new AcLnLo();
				alo.setLoanNo(acLnMst.getLoanNo());
				alo.setPerdNo(wrf.getCnt());
				alo = acLnLoDao.getById(alo);
				if(alo != null){
					alo.setFineInt(wrf.getTxPayOver());
					acLnLoDao.update(alo);
				}
			}
			
			// 提前清贷 贷款余额必须与扣本金额一直
			double exact = 0.0001;
			double bal = NumberUtil.amtSubs(acLnMst.getLoanBal(), wsRepyClear.getRepayAmt());
			if (bal < exact && bal > -exact ) {
				result = true;
			} else {
				result = false;

				// 更新WS进件表失败
				wsRepyClear.setDealDesc("存在提前清贷申请，扣本金额与贷款余额："+acLnMst.getLoanBal()+"不等");
				wsRepyClear.setDealSts("05");
				wsRepyClearDao.update(wsRepyClear);
				return wsRepyClear;
			}
			
				wsRepyClear.setDealDesc("处理中");
				wsRepyClear.setDealSts("02");
				wsRepyClearDao.update(wsRepyClear);

				AcDebitSusp acDebitSusp = new AcDebitSusp();
				acDebitSusp.setWsId(wsRepyClear.getWsId());
				acDebitSusp.setBatchNo("");
				acDebitSusp.setBrNo(wsRepyClear.getBrNo());
				acDebitSusp.setPactNo(wsRepyClear.getPactNo());
				acDebitSusp.setAcName(wsRepyClear.getAcName());
				acDebitSusp.setAcNo(wsRepyClear.getAcNo());
				acDebitSusp.setOpnCode(wsRepyClear.getOpnCode());
				acDebitSusp.setOpnName(wsRepyClear.getOpnName());
				acDebitSusp.setPayOver(wsRepyClear.getPayOver());
				acDebitSusp.setFeeRec(wsRepyClear.getFeeRec());
				acDebitSusp.setRepayTotal(wsRepyClear.getRepayTotal());
				acDebitSusp.setRepayAmt(wsRepyClear.getRepayAmt());
				acDebitSusp.setRepayInte(wsRepyClear.getRepayInte());
				acDebitSusp.setRepayOver(wsRepyClear.getRepayOver());
				acDebitSusp.setFeeTotal(wsRepyClear.getFeeTotal());
				acDebitSusp.setSerialNo(wsRepyClear.getSerialNo());
				acDebitSusp.setDealSts(wsRepyClear.getDealSts());
				acDebitSusp.setDealDesc(wsRepyClear.getDealDesc());
				acDebitSusp.setTxCde(TransCode.LNCL);// 提前清贷
				
				// acDebitSusp.setTxDate(wsRepyClear.getTxDate());
				acDebitSuspDao.insert(acDebitSusp);

				// 进行业务处理
				acDebitSuspExec(acDebitSusp);

		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return wsRepyClear;
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-25
	 * @描述	扣款文件上传逻辑处理
	 */
	public void acDebitSuspExec(AcDebitSusp acDebitSusp)throws ServiceException{
		Connection conn = DBUtils.getConn();
		AcTraceLog acTraceLog = new AcTraceLog();
		String traceNo =  acTraceLogDao.getKey();
		
		//借据信息
		LnDue lnDue = new LnDue();
		lnDue.setPactNo(acDebitSusp.getPactNo());
		lnDue.setBrNo(acDebitSusp.getBrNo());
		lnDue = lnDueDao.getByPactNoAndBrNo(lnDue);
		
		//信托虚拟账户
		ProjAcct projAcct = null;
		if(acDebitSusp.getCardChn()!=null && acDebitSusp.getCardChn().length()>0){
			projAcct = new ProjAcct();
			projAcct.setProjNo(lnDue.getProjNo());
			projAcct.setAcctType("02");//虚拟账户
			projAcct.setCardSts("01");//生效
			projAcct.setCardChn(acDebitSusp.getCardChn());
			projAcct = projAcctDao.getByProjNoAndAcctTypeAndChn(projAcct);
		}
		
		LnAcct lnAcct = new LnAcct();
		lnAcct.setAppId(lnDue.getAppId());
		lnAcct.setAcUse("1");//扣款账号
		lnAcct.setAcNo(acDebitSusp.getAcNo());
		lnAcct.setAcSts("01");//状态正常
		lnAcct = lnAcctDao.getById(lnAcct);
		
		AcDebit acDebit = new AcDebit();
		acDebit.setTraceCnt(1);
		acDebit.setTraceNo(traceNo);
		try {
			List<AcLoanBackLog> acLoanBackLogList = new ArrayList<AcLoanBackLog>();

			acDebit.setDebitNo(ParmBiz.getDebitNo(conn));
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
			acDebit.setSts("01");
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
			
			if(acDebitSusp.getCardChn()!=null && acDebitSusp.getCardChn().length()>0){//上传扣款文件中指定支付渠道
				acDebit.setCardChn(acDebitSusp.getCardChn());
				acDebit.setXtAcNo(projAcct.getAcctNo());
			}else{
				acDebit.setCardChn("");
				acDebit.setXtAcNo("");
			}
			acDebit.setCreateDt(ParmBiz.getOracleTxDate(conn));
			
			acDebitDao.insert(acDebit);
			
			AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
			String backLogNo = ParmBiz.getAcLoanLogNo(conn) ;
			acLoanBackLog.setBackLogNo(backLogNo);
			acLoanBackLog.setBackCnt("1");
			acLoanBackLog.setLoanLogNo(acDebit.getDebitNo());
			acLoanBackLog.setBackType(PUBConstant.BACK_TYPE_02);
			acLoanBackLog.setBackRes("");
			acLoanBackLog.setFailCaus("");
			acLoanBackLog.setBackSts(PUBConstant.BACK_STS_01);
			acLoanBackLog.setBusOrderType(busOrderType);
			acLoanBackLog.setBusEntryType(acDebit.getBusEntryType());
			acLoanBackLogDao.insert(acLoanBackLog);
			acLoanBackLogList.add(acLoanBackLog);
			
			
			// 代收费大于0 ， 需将代收费从虚拟账户中划拨给合作机构
			if (acDebitSusp.getFeeTotal() > 0) {
				//获取合作机构账户信息
				CorpAcct corpAcct = new CorpAcct();
				corpAcct.setBrNo(lnDue.getBrNo());
				corpAcct = corpAcctDao.getByBrNo(corpAcct);
				
				AcLoanLog acLoanLog = new AcLoanLog();
				acLoanLog.setLoanLogNo(ParmBiz.getAcLoanLogNo(conn));
				acLoanLog.setTraceNo(traceNo);
				acLoanLog.setLoanNo(lnDue.getDueNo());
				acLoanLog.setPactNo(lnDue.getPactNo());
				acLoanLog.setBrNo(lnDue.getBrNo());
				acLoanLog.setLoanAmt(acDebitSusp.getFeeTotal());
				acLoanLog.setLoanAcNo(corpAcct.getOpnAcno());
				acLoanLog.setLoanAcType("12");
				acLoanLog.setLoanAcName(corpAcct.getAcName());
				acLoanLog.setLoanBankCode(corpAcct.getExchange());
				acLoanLog.setLoanBankProv(corpAcct.getProvince());
				acLoanLog.setLoanBankCity(corpAcct.getCity());
				acLoanLog.setLoanBankSite(corpAcct.getOpnBank());
				acLoanLog.setLoanSts("01");
				acLoanLog.setTxDate(ParmBiz.getBizDt(conn));
				acLoanLog.setTxTime(ParmBiz.getOracleTxTime(conn));
				if(acDebitSusp.getCardChn()!=null && acDebitSusp.getCardChn().length()>0){//上传扣款文件中指定支付渠道
					acLoanLog.setCardChn(acDebitSusp.getCardChn());
					acLoanLog.setXtAcNo(projAcct.getAcctNo());
				}else{
					acLoanLog.setCardChn("");
					acLoanLog.setXtAcNo("");
				}
				acLoanLog.setBusEntryType(busOrderType+"02");
				
				acLoanLogDao.insert(acLoanLog);

				acLoanBackLog = new AcLoanBackLog();
				acLoanBackLog.setBackLogNo(backLogNo);
				acLoanBackLog.setBackCnt("2");
				acLoanBackLog.setLoanLogNo(acLoanLog.getLoanLogNo());
				acLoanBackLog.setBackType(PUBConstant.BACK_TYPE_01);
				acLoanBackLog.setBackRes("");
				acLoanBackLog.setFailCaus("");
				acLoanBackLog.setBackSts(PUBConstant.BACK_STS_01);
				acLoanBackLog.setBusOrderType(busOrderType);
				acLoanBackLog.setBusEntryType(acLoanLog.getBusEntryType());
				acLoanBackLogDao.insert(acLoanBackLog);
				acLoanBackLogList.add(acLoanBackLog);
			}
			
			
			acDebitSusp.setDealSts("02");
			acDebitSusp.setSerialNo(acDebit.getDebitNo());
			acDebitSuspDao.update(acDebitSusp);
			
			//更新扣款进件表
			WsRepyClear wsRepyClear = new WsRepyClear();
			wsRepyClear.setWsId(acDebitSusp.getWsId());
			wsRepyClear = wsRepyClearDao.getById(wsRepyClear);
			wsRepyClear.setDealSts("02");
			wsRepyClearDao.update(wsRepyClear);
			
			acTraceLog.setTraceNo(traceNo);
			acTraceLog.setTraceCnt(1);
			acTraceLog.setTxDt(ParmBiz.getOracleTxDate(conn));
			acTraceLog.setTxTime(ParmBiz.getOracleTxTime(conn));
			acTraceLog.setTxBrNo(acDebitSusp.getBrNo());
			acTraceLog.setTxCde(TransCode.LNC4);
			acTraceLog.setSubTxCde(TransCode.LNCL);
			acTraceLog.setSvcInd(TransCode.LNCL);
			acTraceLog.setCurNo(lnDue.getCurNo());
			acTraceLog.setPrdtNo(lnDue.getPrdtNo());
			acTraceLog.setAmt(acDebitSusp.getRepayTotal());
			acTraceLog.setCancelInd(PUBConstant.REV_ROL_NORM);//正常
			acTraceLog.setAppSts("01");//新增
			acTraceLog.setBrNo(lnDue.getBrNo());
			acTraceLog.setPactNo(lnDue.getPactNo());
			acTraceLog.setLoanNo(lnDue.getDueNo());
			acTraceLog.setMemo("扣款信息上传");
			
			acTraceLogDao.insert(acTraceLog);
			
			//合作机构配置信息
			CorpBase corpBase = new CorpBase();
			corpBase.setBrNo(wsRepyClear.getBrNo());
			corpBase = corpBaseDao.getById(corpBase);
			//对公结算处理
			if ("01".equals(corpBase.getStateSts())) {
				//发送第三方支付
				sendZfMes(acLoanBackLogList,lnDue.getProjNo(), traceNo, conn); 
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			try {
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void dealClear(WsRepyClear wsRepyClear){
		Connection conn = DBUtils.getConn();
		try{
		//合作机构配置信息
		CorpBase corpBase = new CorpBase();
		corpBase.setBrNo(wsRepyClear.getBrNo());
		corpBase = corpBaseDao.getById(corpBase);
		//对公结算处理
		if ("01".equals(corpBase.getStateSts())) {
		}else{
			AcDebitSusp acDebitSusp = new AcDebitSusp();
			acDebitSusp.setWsId(wsRepyClear.getWsId());
			acDebitSusp = acDebitSuspDao.getById(acDebitSusp);
			
			AcLoanBackLog acLoanBackLog = new AcLoanBackLog();
			acLoanBackLog.setLoanLogNo(acDebitSusp.getSerialNo());
			acLoanBackLog.setBackType("02");
			List<AcLoanBackLog> albList = acLoanBackLogDao.getListByLogNoAndType(acLoanBackLog);
			List<AcLoanBackLog> acLoanBackLogList = acLoanBackLogDao.getListByBackLogNo(albList.get(0));
			//线下实收，则直接进行填账动作
			OperaInfo operaInfo = new OperaInfo(conn);
			operaInfo.setTraceCnt(1);
			operaInfo.setBizDt(ParmBiz.getBizDt(conn));
			operaInfo.setTxDt(ParmBiz.getOracleTxDate(conn));
			for(AcLoanBackLog  all:acLoanBackLogList){
				if (PUBConstant.BACK_TYPE_01.equals(all.getBackType())) {// 放款
					AcLoanBack acLoanBack = new AcLoanBack();
					acLoanBack.setBackLogNo(all.getBackLogNo());
					acLoanBack.setBackCnt(all.getBackCnt());
					acLoanBack.setBackRes("01");
					acLoanBack.setFailCaus("提前清贷线下处理");
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
				} else if (PUBConstant.BACK_TYPE_02.equals(all.getBackType())) {// 扣款
					RepayBiz.acDebitBack(all.getBackLogNo(),all.getBackCnt(), "01","线下实收处理成功", operaInfo,"15000","");
				} 
			}
		}
		}catch(Exception e){
			throw new ServiceException(e.getMessage());
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	/**
	 * 
	 * @throws AccountingException 
	 * @throws Exception_Exception 
	 * @throws IOException 
	 * @throws SQLException 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-14
	 * @描述	发送第三方支付放款
	 */
	public  Map sendZfMes(List<AcLoanBackLog> acLoanBackLogList,String projNo,String traceNo,Connection conn) throws AccountingException, Exception_Exception, IOException, SQLException {

		return null ;
	}
	public void updateBack(int status,String dealDesc,List<AcLoanLog> acLoanLogList,List<AcDebit> acDebitList,List<AcLoanBackLog> acLoanBackLogList,Connection conn){
		if(status>=10000&&status<20000){//发送成功
			for(AcLoanLog acLoanLog1 : acLoanLogList){
				acLoanLog1.setLoanSts("02");
				try {
					acLoanLog1.setUpDate(ParmBiz.getOracleUpDate(conn));
				} catch (AccountingException e) {
					e.printStackTrace();
				}
				acLoanLogDao.update(acLoanLog1);
			}
			for(AcDebit acDebit1 : acDebitList){
				acDebit1.setSts("02");//已发送
				acDebitDao.update(acDebit1);
			}
		}else{
			for(AcLoanLog acLoanLog1 : acLoanLogList){
				acLoanLog1.setLoanSts("05");
				try {
					acLoanLog1.setUpDate(ParmBiz.getOracleUpDate(conn));
				} catch (AccountingException e) {
					e.printStackTrace();
				}
				acLoanLogDao.update(acLoanLog1);
			}
			for(AcDebit acDebit1 : acDebitList){
				acDebit1.setSts("05");//发送失败
				acDebitDao.update(acDebit1);
				AcDebitSusp acDebitSusp = new AcDebitSusp();
				acDebitSusp.setSerialNo(acDebit1.getDebitNo());
				acDebitSusp = acDebitSuspDao.getBySerialNo(acDebitSusp);
				WsRepyClear wsRepyClear = new WsRepyClear();
				wsRepyClear.setWsId(acDebitSusp.getWsId());
				wsRepyClear.setDealSts("05");
				wsRepyClear.setDealDesc(dealDesc.length()>60?dealDesc.substring(0,30):dealDesc);
				wsRepyClearDao.updateSts(wsRepyClear);
			}
			for(AcLoanBackLog acLoanBackLog : acLoanBackLogList){
				acLoanBackLog.setBackSts("02");//发送失败
				acLoanBackLog.setBackRes("02");
				acLoanBackLog.setFailCaus("发送失败");
				acLoanBackLogDao.updateSts(acLoanBackLog);
			}
		}
	}
	public void update(AftRepyClear aftRepyClear) throws ServiceException {
		try {
			aftRepyClearDao.update(aftRepyClear);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
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
	
	
	public AftRepyClearDao getAftRepyClearDao() {
		return aftRepyClearDao;
	}

	public void setAftRepyClearDao(AftRepyClearDao aftRepyClearDao) {
		this.aftRepyClearDao = aftRepyClearDao;
	}

	public AcLnMstDao getAcLnMstDao() {
		return acLnMstDao;
	}

	public AcLnRepayPlnCurDao getAcLnRepayPlnCurDao() {
		return acLnRepayPlnCurDao;
	}

	public WsRepyClearDao getWsRepyClearDao() {
		return wsRepyClearDao;
	}

	public void setAcLnMstDao(AcLnMstDao acLnMstDao) {
		this.acLnMstDao = acLnMstDao;
	}

	public void setAcLnRepayPlnCurDao(AcLnRepayPlnCurDao acLnRepayPlnCurDao) {
		this.acLnRepayPlnCurDao = acLnRepayPlnCurDao;
	}

	public void setWsRepyClearDao(WsRepyClearDao wsRepyClearDao) {
		this.wsRepyClearDao = wsRepyClearDao;
	}

	public LnDueDao getLnDueDao() {
		return lnDueDao;
	}

	public LnAcctDao getLnAcctDao() {
		return lnAcctDao;
	}

	public AcDebitSuspDao getAcDebitSuspDao() {
		return acDebitSuspDao;
	}

	public AcTraceLogDao getAcTraceLogDao() {
		return acTraceLogDao;
	}

	public ProjAcctDao getProjAcctDao() {
		return projAcctDao;
	}

	public AcDebitDao getAcDebitDao() {
		return acDebitDao;
	}

	public AcLoanBackLogDao getAcLoanBackLogDao() {
		return acLoanBackLogDao;
	}

	public CorpAcctDao getCorpAcctDao() {
		return corpAcctDao;
	}

	public AcLoanLogDao getAcLoanLogDao() {
		return acLoanLogDao;
	}

	public void setLnDueDao(LnDueDao lnDueDao) {
		this.lnDueDao = lnDueDao;
	}

	public void setLnAcctDao(LnAcctDao lnAcctDao) {
		this.lnAcctDao = lnAcctDao;
	}

	public void setAcDebitSuspDao(AcDebitSuspDao acDebitSuspDao) {
		this.acDebitSuspDao = acDebitSuspDao;
	}

	public void setAcTraceLogDao(AcTraceLogDao acTraceLogDao) {
		this.acTraceLogDao = acTraceLogDao;
	}

	public void setProjAcctDao(ProjAcctDao projAcctDao) {
		this.projAcctDao = projAcctDao;
	}

	public void setAcDebitDao(AcDebitDao acDebitDao) {
		this.acDebitDao = acDebitDao;
	}

	public void setAcLoanBackLogDao(AcLoanBackLogDao acLoanBackLogDao) {
		this.acLoanBackLogDao = acLoanBackLogDao;
	}

	public void setCorpAcctDao(CorpAcctDao corpAcctDao) {
		this.corpAcctDao = corpAcctDao;
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

	public AcLnLoDao getAcLnLoDao() {
		return acLnLoDao;
	}

	public AcLnRepayPlnDao getAcLnRepayPlnDao() {
		return acLnRepayPlnDao;
	}

	public void setWsRepyFineDao(WsRepyFineDao wsRepyFineDao) {
		this.wsRepyFineDao = wsRepyFineDao;
	}

	public void setAcLnLoDao(AcLnLoDao acLnLoDao) {
		this.acLnLoDao = acLnLoDao;
	}

	public void setAcLnRepayPlnDao(AcLnRepayPlnDao acLnRepayPlnDao) {
		this.acLnRepayPlnDao = acLnRepayPlnDao;
	}

	public CorpBaseDao getCorpBaseDao() {
		return corpBaseDao;
	}

	public void setCorpBaseDao(CorpBaseDao corpBaseDao) {
		this.corpBaseDao = corpBaseDao;
	}
}