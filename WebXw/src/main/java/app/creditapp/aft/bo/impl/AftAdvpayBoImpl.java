package  app.creditapp.aft.bo.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import accounting.biz.loan.RepayBiz;
import accounting.biz.pub.AcLnRepayPlnBiz;
import accounting.biz.pub.ParmBiz;
import accounting.domain.fee.AcChrgLog;
import accounting.domain.sys.OperaInfo;
import accounting.interf.loan.CalIntst;
import accounting.interf.loan.IntstDetailDatas;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.Operation;
import accounting.plat.core.OperationFactory;
import accounting.plat.util.NumberUtil;
import accounting.plat.util.TimeUtil;
import app.base.BaseService;
import app.base.CreateKey;
import app.base.ServiceException;
import app.creditapp.acc.loan.bo.AcDebitSuspBo;
import app.creditapp.acc.loan.dao.AcDebitDao;
import app.creditapp.acc.loan.dao.AcLnMstDao;
import app.creditapp.acc.loan.dao.AcLnRepayPlnCurDao;
import app.creditapp.acc.loan.dao.AcLoanBackLogDao;
import app.creditapp.acc.loan.dao.AcLoanLogDao;
import app.creditapp.acc.loan.entity.AcDebit;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.loan.entity.AcLnRepayPlnCur;
import app.creditapp.acc.loan.entity.AcLoanBackLog;
import app.creditapp.acc.loan.entity.AcLoanLog;
import app.creditapp.acc.log.dao.AcChrgLogDao;
import app.creditapp.acc.log.dao.AcTraceLogDao;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.acc.option.bo.AcDamFormulaBo;
import app.creditapp.acc.option.dao.AcLnSetlmtLogDao;
import app.creditapp.acc.option.entity.AcLnSetlmtLog;
import app.creditapp.aft.bo.AftAdvpayBo;
import app.creditapp.aft.dao.AftAdvpayDao;
import app.creditapp.aft.entity.AftAdvpay;
import app.creditapp.corp.dao.CorpAcctDao;
import app.creditapp.inf.dao.WsElyRepyDao;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsElyRepy;
import app.creditapp.inf.entity.WsIn2806;
import app.creditapp.inf.entity.WsIn2806_1;
import app.creditapp.inf.entity.WsOut2806;
import app.creditapp.ln.dao.LnAcctDao;
import app.creditapp.ln.dao.LnDueDao;
import app.creditapp.ln.entity.LnAcct;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.proj.dao.ProjAcctDao;
import app.oscache.CachecodeUtil;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: AftAdvpayBoImplImpl.java
* Description:
**/
public class AftAdvpayBoImpl extends BaseService implements AftAdvpayBo {

	private AftAdvpayDao aftAdvpayDao;
	private AcLnMstDao acLnMstDao;
	private DataSource dataSource;
	private AcDamFormulaBo acDamFormulaBo;
	private AcTraceLogDao acTraceLogDao;
	private AcLnSetlmtLogDao acLnSetlmtLogDao;
	private AcLnRepayPlnCurDao acLnRepayPlnCurDao;
	private FiterEngineInterface fiterEngineInterface;
	private WsElyRepyDao wsElyRepyDao;
	private AcDebitDao acDebitDao;
	private LnDueDao lnDueDao;
	private ProjAcctDao projAcctDao;
	private LnAcctDao lnAcctDao;
	private AcLoanBackLogDao acLoanBackLogDao;
	private CorpAcctDao corpAcctDao;
	private AcLoanLogDao acLoanLogDao;
	private AcDebitSuspBo acDebitSuspBo;
	private AcChrgLogDao acChrgLogDao;

	public AcLnRepayPlnCurDao getAcLnRepayPlnCurDao() {
		return acLnRepayPlnCurDao;
	}

	public void setAcLnRepayPlnCurDao(AcLnRepayPlnCurDao acLnRepayPlnCurDao) {
		this.acLnRepayPlnCurDao = acLnRepayPlnCurDao;
	}

	/*
	 * 提前还款 确认
	 * (non-Javadoc)
	 * @see app.creditapp.aft.bo.AftAdvpayBo#execAdvpay(app.creditapp.aft.entity.AftAdvpay)
	 */
	public void execAdvpay(AftAdvpay aftAdvpay) throws ServiceException{
		System.out.println("进入提前还款 确认方法execAdvpay()");
		Connection conn = this.getConnection();
		List<AcChrgLog> acChrgLogList = new ArrayList<AcChrgLog>();
		DecimalFormat df  = new DecimalFormat("00");
		try {
			String bizDt = aftAdvpay.getPayDate();//交易日期
			String txDt = ParmBiz.getBizDt(conn);//系统日期
			if(aftAdvpay.getPayDate()==null || aftAdvpay.getPayDate().length()==0){//还款日为空,则默认当天还款
				aftAdvpay.setPayDate(bizDt);
			}
			// 利息计算
			AcLnMst acLnMst = new AcLnMst();
			acLnMst.setPactNo(aftAdvpay.getPactNo());
			acLnMst.setBrNo(aftAdvpay.getBrNo());
			acLnMst = acLnMstDao.getByPactNo(acLnMst);
			CalIntst calIntst = new CalIntst();
			calIntst.setLoanNo(acLnMst.getLoanNo());
			
			//全额还款
			if("01".equals(aftAdvpay.getRepType())){
				aftAdvpay.setPayAmt(acLnMst.getLoanBal());
				calIntst.setRemAmt(acLnMst.getLoanBal());
				//借据信息
				LnDue lnDue = new LnDue();
				lnDue.setPactNo(aftAdvpay.getPactNo());
				lnDue.setBrNo(aftAdvpay.getBrNo());
				lnDue = lnDueDao.getByPactNoAndBrNo(lnDue);
				lnDue.setDueSts("07");
				lnDueDao.dueStsUpdate(lnDue); //更新放款借据表的借据状态-su
			}else{//部分还款
				calIntst.setRemAmt(aftAdvpay.getPayAmt());
				
			}
			calIntst.setTxDt(txDt);  //系统时间
			calIntst.setBizDt(bizDt);  //交易日期
			
			String traceNo = acTraceLogDao.getKey();
			
			Operation op = (Operation) OperationFactory.getFactoryInstance().getOperation(TransCode.XROR, conn);
			IntstDetailDatas datas = (IntstDetailDatas) op.execute(calIntst);
			//欠本+欠息+罚息+0，ac_ln_lo表中逾期的还款期次
			double sumLoAmt = NumberUtil.amtAdd(NumberUtil.amtAdd(datas.getPrcpAmt(), NumberUtil.amtAdd(datas.getNormInt(), datas.getFineInt())),datas.getLoFeeAmt());
			// 违约金计算
//			double damAmt = acDamFormulaBo.getDamAmtInAftAdvpay(aftAdvpay,acLnMst,conn,traceNo);
			acChrgLogList = acDamFormulaBo.getDamAmtInAftAdvpay(aftAdvpay,acLnMst,traceNo);
			double feeTotal = 0;//费用合计
			double damAmt = 0;// 违约金计算
			double premAmt = 0;//保费
			double serAmt = 0;//服务费
			for(AcChrgLog acChrgLog :acChrgLogList){
				if(PUBConstant.FEE_TYPE_06.equals(acChrgLog.getFeeType())){
					damAmt = NumberUtil.amtAdd(damAmt, acChrgLog.getChrgAmt());
				}
				if(PUBConstant.FEE_TYPE_01.equals(acChrgLog.getFeeType())){
					serAmt = NumberUtil.amtAdd(serAmt, acChrgLog.getChrgAmt());
				}
				if(PUBConstant.FEE_TYPE_02.equals(acChrgLog.getFeeType())){
					premAmt = NumberUtil.amtAdd(premAmt, acChrgLog.getChrgAmt());
				}
				feeTotal = NumberUtil.amtAdd(feeTotal, acChrgLog.getChrgAmt()); //1150？
			}

			AcLnRepayPlnCur acCur = new AcLnRepayPlnCur();
			acCur.setLoanNo(acLnMst.getLoanNo());
			acCur = acLnRepayPlnCurDao.getById(acCur);
			//当期归还利息
			double norm = NumberUtil.amtSubs(NumberUtil.amtSubs(acCur.getNormInt(), acCur.getRepayNormInt()),acCur.getWvNormInt());
			
			double normInt = NumberUtil.isAmtGreatAndEqual(datas.getCurInt(), norm)?datas.getCurInt():norm;
//			normInt = NumberUtil.amtSubs(normInt, acCur.getRepayNormInt());
			AcTraceLog acTraceLog = new AcTraceLog();
			//欠本利罚费+当期费用+当期还本金+当期还利息
			double sumAmt = NumberUtil.amtAdd(NumberUtil.amtAdd(NumberUtil.amtAdd(sumLoAmt, feeTotal),norm),datas.getRemPrcpAmt());

			AcLnSetlmtLog acLnSetlmtLog = new AcLnSetlmtLog();
			acLnSetlmtLog.setTraceNo(traceNo);
			acLnSetlmtLog.setLoanNo(acLnMst.getLoanNo());
			acLnSetlmtLog.setPactNo(acLnMst.getPactNo());
			acLnSetlmtLog.setBrNo(acLnMst.getBrNo());
			acLnSetlmtLog.setRecvAmt(sumAmt);
			acLnSetlmtLog.setRecvDt(aftAdvpay.getPayDate());
			acLnSetlmtLog.setDamAmt(damAmt);
			acLnSetlmtLog.setCurInt(norm);
			acLnSetlmtLog.setRemPrcpAmt(aftAdvpay.getPayAmt());
			acLnSetlmtLog.setRepayType(aftAdvpay.getRepayType());
			// 还款计划需进行变更
			OperaInfo operaInfo = new OperaInfo(conn);
			operaInfo.setTraceNo(traceNo);
			AcLnRepayPlnBiz.aftAdvpayChangeRepln(acLnSetlmtLog, operaInfo);
			operaInfo.getConn().commit();

			acLnSetlmtLogDao.insert(acLnSetlmtLog);

			if("01".equals(aftAdvpay.getOnlinOff())){//线上
				System.out.println("线上还款等待后续开发。。。");
			}else if("02".equals(aftAdvpay.getOnlinOff())){//线下 184行
				accounting.domain.loan.AcDebit acd = new accounting.domain.loan.AcDebit();
				acd.setLoanNo(acLnMst.getLoanNo());
				acd.setRepayAmt(sumAmt);//提前还款应扣金额
				acd.setLoAmt(sumLoAmt);
				acd.setTraceNo(traceNo);
//				acd.setCurAmt(acDebit.getCurAmt());
				operaInfo.setTraceCnt(1);
				operaInfo.setBizDt(bizDt);  //交易时间
				operaInfo.setTxDt(txDt);  //系统时间
				
				//获取当期还款
				AcLnRepayPlnCur acLnRepayPlnCur = new AcLnRepayPlnCur();
				acLnRepayPlnCur.setLoanNo(acLnMst.getLoanNo());
				acLnRepayPlnCur = acLnRepayPlnCurDao.getById(acLnRepayPlnCur);
				
				accounting.domain.loan.AcLnRepayPlnCur acLC = new accounting.domain.loan.AcLnRepayPlnCur();
				acLC.setPrcpAmt(acLnRepayPlnCur.getPrcpAmt());
				acLC.setRepayPrcpAmt(acLnRepayPlnCur.getRepayPrcpAmt());
				acLC.setNormInt(acLnRepayPlnCur.getNormInt());
				acLC.setRepayNormInt(acLnRepayPlnCur.getRepayNormInt());
				acLC.setWvPrcpAmt(acLnRepayPlnCur.getWvPrcpAmt());
				acLC.setWvNormInt(acLnRepayPlnCur.getWvNormInt());
				RepayBiz.repayAmtOrder(acLC, acd, operaInfo, conn);
				
				//453
				aftAdvpay.setTraceNo(traceNo);
				aftAdvpay.setIntst(datas.getFineInt());
//				aftAdvpay.setAcName(lnAcct.getAcName());
//				aftAdvpay.setAcNo(lnAcct.getAcNo());
//				aftAdvpay.setAcType(lnAcct.getAcType());
				aftAdvpay.setPactAmt(acLnMst.getLoanAmt());
				aftAdvpay.setRepSts("02");
				aftAdvpay.setTxDate(ParmBiz.getBizDt(conn));
				aftAdvpayDao.update(aftAdvpay);
				
				WsElyRepy wsElyRepy = new WsElyRepy();
				wsElyRepy.setWsId(aftAdvpay.getRepId());
				wsElyRepy = wsElyRepyDao.getById(wsElyRepy);
				wsElyRepy.setDealDesc("处理成功");
				wsElyRepy.setDealSts("03");
				
				wsElyRepyDao.updateStsAndDesc(wsElyRepy);
				
				
				AcDebit acDebit1 = new AcDebit();
				acDebit1.setLoanNo(acLnMst.getLoanNo());
				acDebit1.setSts("01");
				
				acDebitDao.delByLoanNoAndSts(acDebit1);//线下提前还款后删除未发送批扣
				System.out.println("已经走到最后了");
			}
			
			acTraceLog.setTraceNo(traceNo);
			acTraceLog.setTraceCnt(1);
			acTraceLog.setTxDt(bizDt);
			acTraceLog.setTxTime(ParmBiz.getOracleUpDate(conn));
			acTraceLog.setTxBrNo(aftAdvpay.getBrNo());
			acTraceLog.setTxCde("01".equals(aftAdvpay.getOnlinOff())?TransCode.LNAD:TransCode.LNRP);
			acTraceLog.setSubTxCde(TransCode.LNAD);
			acTraceLog.setSvcInd(TransCode.LNAD);
			acTraceLog.setBrNo(aftAdvpay.getBrNo());
			acTraceLog.setPactNo(aftAdvpay.getPactNo());
			acTraceLog.setLoanNo(acLnMst.getLoanNo());
			acTraceLog.setMemo("提前还款");
			acTraceLogDao.insert(acTraceLog);
			
			
			
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		} finally {
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
            	throw new ServiceException(e.getMessage());
            }
        }
		
		
	}
	
	/*
	 *	提前还款 测算 
	 */
	@Override
	public WsOut2806 calcAdvpay(AftAdvpay aftAdvpay) throws ServiceException{
		System.out.println("进入提前还款 测算方法calcAdvpay()");
		Connection conn = this.getConnection();
		WsOut2806 wsOut = new WsOut2806();
		try {
			String bizDt = ParmBiz.getBizDt(conn);//核算日期
			List<AcChrgLog> acChrgLogList = new ArrayList<AcChrgLog>();
			//利息计算
			AcLnMst acLnMst = new AcLnMst();
			acLnMst.setPactNo(aftAdvpay.getPactNo());
			acLnMst.setBrNo(aftAdvpay.getBrNo());
			acLnMst = acLnMstDao.getByPactNo(acLnMst);
			CalIntst calIntst = new CalIntst();
			//全额还款
			if("01".equals(aftAdvpay.getRepType())){
				calIntst.setRemAmt(acLnMst.getLoanBal());
				aftAdvpay.setPayAmt(acLnMst.getLoanBal());
			}else{//部分还款
				calIntst.setRemAmt(aftAdvpay.getPayAmt()); //还本金额
			}
			calIntst.setLoanNo(acLnMst.getLoanNo());
			
			calIntst.setTxDt(bizDt);//系统日期
			calIntst.setBizDt(aftAdvpay.getPayDate());  //交易日期，在页面提交过来的
			Operation op = (Operation) OperationFactory.getFactoryInstance()
					.getOperation(TransCode.XROR, conn);
//			long days = DateUtil.getDaysBetween(acLnMst.getOpnDt(), aftAdvpay.getPayDate());
			String betwDays = String.valueOf(TimeUtil.getBetweenDays(acLnMst
					.getOpnDt(), aftAdvpay.getPayDate()));
			long day = Integer.parseInt(betwDays);
			if(day < 0){
				throw new ServiceException("还款日期小于放款申请日期，无法还款");
			}
			IntstDetailDatas datas = (IntstDetailDatas) op.execute(calIntst);
			//欠款合计 6+7+8+9
			double sumLoAmt = NumberUtil.amtAdd(NumberUtil.amtAdd(datas.getPrcpAmt(), NumberUtil.amtAdd(datas.getNormInt(), datas.getFineInt())),datas.getLoFeeAmt());
			//违约金 、费用计算（当期）
			acChrgLogList = acDamFormulaBo.getDamAmtInAftAdvpay(aftAdvpay,acLnMst,null);
			double feeTotal = 0;//费用合计
			double damAmt = 0;// 违约金计算
			double premAmt = 0;//保费
			double serAmt = 0;//服务费
		
			for(AcChrgLog acChrgLog :acChrgLogList){
				if(PUBConstant.FEE_TYPE_06.equals(acChrgLog.getFeeType())){
					damAmt = NumberUtil.amtAdd(damAmt, acChrgLog.getChrgAmt());
				}
				if(PUBConstant.FEE_TYPE_01.equals(acChrgLog.getFeeType())){
					serAmt = NumberUtil.amtAdd(serAmt, acChrgLog.getChrgAmt());
				}
				if(PUBConstant.FEE_TYPE_02.equals(acChrgLog.getFeeType())){
					premAmt = NumberUtil.amtAdd(premAmt, acChrgLog.getChrgAmt());
				}
				feeTotal = NumberUtil.amtAdd(feeTotal, acChrgLog.getChrgAmt());
			}
			
			AcLnRepayPlnCur acCur = new AcLnRepayPlnCur();
			acCur.setLoanNo(acLnMst.getLoanNo());
			acCur = acLnRepayPlnCurDao.getById(acCur);
			//当期利息
			double norm = NumberUtil.amtSubs(NumberUtil.amtSubs(acCur.getNormInt(), acCur.getRepayNormInt()),acCur.getWvNormInt());
			
			double normInt = NumberUtil.isAmtGreatAndEqual(datas.getCurInt(), norm)?datas.getCurInt():norm;
//			normInt = NumberUtil.amtSubs(normInt, acCur.getRepayNormInt());
			
			wsOut.setBrNo(acLnMst.getBrNo());
			wsOut.setPactNo(acLnMst.getPactNo());
			wsOut.setPayDate(aftAdvpay.getPayDate());
			wsOut.setPayAmt(aftAdvpay.getPayAmt());//还款本金
			wsOut.setPayTotal(sumLoAmt);//欠本利罚费
			wsOut.setPayAmt2(datas.getPrcpAmt());//欠本
			wsOut.setPayInte(datas.getNormInt());//欠息
			wsOut.setPayOver(datas.getFineInt());//罚息
			wsOut.setPayFee(datas.getLoFeeAmt());
			wsOut.setCurInte(norm);//归还利息        当期未出账单应收利息
			wsOut.setPremAmt(premAmt);//保费  	当期未出账单应保费
			wsOut.setSerAmt(serAmt);//服务费  	当期未出账单应服务费
			wsOut.setPayDam(damAmt);//提前还款违约金
			wsOut.setFeeTotal(feeTotal);//当期费用总额 	当期未出账单应收服务费+保费+违约金
			wsOut.setCurAmt(datas.getCurPrcpAmt());//归还本金        当期未出账单应收本金
			wsOut.setBal(NumberUtil.amtSubs(acLnMst.getLoanBal(), aftAdvpay.getPayAmt()));//剩余本金
			wsOut.setTotalAmt(NumberUtil.amtAdd(NumberUtil.amtAdd(NumberUtil.amtAdd(sumLoAmt, feeTotal),normInt),aftAdvpay.getPayAmt()));//提前还款需还总金额
		
			System.out.println("试算日期-->" + aftAdvpay.getPayDate());
			System.out.println("当前交易日期-->" + ParmBiz.getBizDt(conn));
			System.out.println("本次需还款总额-->" + NumberUtil.amtAdd(NumberUtil.amtAdd(NumberUtil.amtAdd(sumLoAmt, feeTotal),normInt),aftAdvpay.getPayAmt()));
			System.out.println("欠款总额-->" + sumLoAmt);
			System.out.println("欠款本金-->" + datas.getPrcpAmt());
			System.out.println("欠正常利息-->" + datas.getNormInt());
			System.out.println("逾期利息-->" + datas.getFineInt());
			System.out.println("欠费用-->" + datas.getLoFeeAmt());
			System.out.println("提前还本金额-->" + datas.getRemPrcpAmt());
			System.out.println("还款日所在的当期利息-->" + norm);
			System.out.println("违约金-->" + damAmt);
			System.out.println("服务费-->" + serAmt);
			System.out.println("保费-->" + premAmt);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		} finally {
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
            	throw new ServiceException(e.getMessage());
            }
        }
		
		return wsOut;
	}
	
	@Override
	public WsOut2806 calcu(WsIn2806_1 wsIn2806_1) throws ServiceException {
		ValidateLog _vl;
		AftAdvpay aftAdvaday = new AftAdvpay();
		WsOut2806 wsOut2806 = new WsOut2806();

		try {
			if(wsIn2806_1.getPayDate().equals("")||wsIn2806_1.getPayDate() == null){
				throw new ServiceException("还款日期为空，请重新填写！");
			}
			
//			_vl = fiterEngineInterface.createValidateLog("wsIn2806Id", wsIn2806_1, true);	
//			if(!_vl.isSuccess()){
//				throw new ServiceException("数据校验失败，请重新填写");
//			}
			aftAdvaday.setPayDate(wsIn2806_1.getPayDate());
			aftAdvaday.setBrNo(wsIn2806_1.getBrNo());
			aftAdvaday.setPactNo(wsIn2806_1.getPactNo());
			aftAdvaday.setRepType(wsIn2806_1.getRepType());
			aftAdvaday.setPayAmt(wsIn2806_1.getPayAmt());
			AcLnMst aclnmst1 = new AcLnMst();
			aclnmst1.setPactNo(wsIn2806_1.getPactNo());
			aclnmst1.setBrNo(wsIn2806_1.getBrNo());
			AcLnMst aclnmst = acLnMstDao.getByPactNo(aclnmst1);
			if(aclnmst == null){
				throw new ServiceException(wsIn2806_1.getPactNo()+"合同信息不存在！");
			}
			wsOut2806 = calcAdvpay(aftAdvaday);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}	
		return wsOut2806;
	}

	//提前还款接口 AFT_ADVPAY的业务逻辑-sunmingyi  20160801
	public void insertAftAdvPay(String BatchNo) throws ServiceException {
		Connection conn = this.getConnection();
		int count = 0;
		try {
			
			WsElyRepy wsElyRepy = new WsElyRepy();
			wsElyRepy.setBatchNo(BatchNo);
			wsElyRepy = wsElyRepyDao.getByBatchNo(wsElyRepy);
			
			//贷款主文件ac_ln_mst存在且贷款状态为01,02,03
			AcLnMst acLnMst = new AcLnMst();
			acLnMst.setBrNo(wsElyRepy.getBrNo());
			acLnMst.setPactNo(wsElyRepy.getPactNo());
			acLnMst = acLnMstDao.getByPactNo(acLnMst);
			
			AftAdvpay aft = new AftAdvpay();
			aft.setPactNo(wsElyRepy.getPactNo());
			aft.setTxDate(ParmBiz.getBizDt(conn));
			count = aftAdvpayDao.getSucByTxDate(aft);
			if(NumberUtil.isAmtGreatThanZero(count)){
				//更新WS进件表失败
				throw new ServiceException("当天含有处理成功的提前还款");

			}
			
			if (acLnMst != null) {
				if("01".equals(acLnMst.getLoanSts()) ||"02".equals(acLnMst.getLoanSts()) 
						||"03".equals(acLnMst.getLoanSts())){//主文件状态为01/02/03才能扣款
				}else{
					//更新WS进件表失败
					throw new ServiceException("贷款主文件非正常或逾期");

					
				}
				//部分提前还款02
				if("02".equals(wsElyRepy.getDebitType())){
					if(NumberUtil.isAmtGreat(wsElyRepy.getPayAmt(), acLnMst.getLoanBal())||NumberUtil.isAmtLessThanOrEqualZero(wsElyRepy.getPayAmt())){
						//更新WS进件表失败
						throw new ServiceException("提前还本金额必须小于等于贷款余额且大于0，贷款余额为:"+acLnMst.getLoanBal());

					}
				}				
				AcLnRepayPlnCur acCur = new AcLnRepayPlnCur();
				acCur.setLoanNo(acLnMst.getLoanNo());
				acCur = acLnRepayPlnCurDao.getById(acCur);
				if(acCur==null){
					//更新WS进件表失败
					throw new ServiceException("合同:"+acLnMst.getPactNo()+"最后一期还款计划已经逾期不能发起提前还款！");
				}
			} else  {
				//更新WS进件表失败
				throw new ServiceException("贷款主文件不存在");
			}

			//扣款文件主表判断
			AcDebit acDebit = new AcDebit();
			acDebit.setLoanNo(acLnMst.getLoanNo());
			acDebit = acDebitDao.getByLoanNoIng(acDebit);
			if(acDebit != null){
				throw new ServiceException("合同号:"+acLnMst.getPactNo()+"存在未决的扣款记录,不能再次发起扣款！");
			}
			
			wsElyRepy.setDealDesc("处理中");
			wsElyRepy.setDealSts("02");
			wsElyRepyDao.update(wsElyRepy);
			
			AftAdvpay aftAdvpay = new AftAdvpay();
			aftAdvpay.setRepId(wsElyRepy.getWsId());  //长度20
			aftAdvpay.setPactNo(wsElyRepy.getPactNo());
			aftAdvpay.setBrNo(wsElyRepy.getBrNo());
			aftAdvpay.setPayAmt(wsElyRepy.getPayAmt());
			aftAdvpay.setOnlinOff(wsElyRepy.getOnlinOff());  //02
			aftAdvpay.setRepType(wsElyRepy.getDebitType());  //02
			aftAdvpay.setRepSts(wsElyRepy.getDealSts());
			aftAdvpay.setPayDate(wsElyRepy.getTxDate());
			aftAdvpayDao.insert(aftAdvpay);
			
			//删除该借据待处理批扣文件,A类合作机构删除,B类合作机构无待处理批扣
			AcDebit ac = new AcDebit();
			ac.setLoanNo(acLnMst.getLoanNo());
			ac.setSts("01");
			acDebitDao.delByLoanNoAndSts(ac);
			
			//执行业务处理
			execAdvpay(aftAdvpay);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}finally {
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
            	throw new ServiceException(e.getMessage());
            }
        }
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
	
	/**
	 * @作者 DHCC-ZKX
	 * @日期 July 20, 2016
	 * @方法说明：接口ws2806调用rule-WsIn2806.xml校验规则
	 * @返回参数 响应参数实体ResponseParm
	 */
	public ResponseParm validateWsIn(WsIn2806 wsIn2806) throws ServiceException{
		ResponseParm responseParm = new ResponseParm();
		responseParm.setRespCode("0000");
		ValidateLog _vl;
		try {
			_vl = fiterEngineInterface.createValidateLog("wsIn2806Id", wsIn2806, true);	
			if(!_vl.isSuccess()){
				responseParm.setRespCode("9004");
				responseParm.setRespDesc(_vl.getErrorMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm; 
	}
	
	public void del(AftAdvpay aftAdvpay) throws ServiceException {
		try {
			aftAdvpayDao.del(aftAdvpay);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftAdvpay aftAdvpay)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftAdvpayDao
					.getCount(aftAdvpay) });// 初始化分页类
			aftAdvpay.setStartNumAndEndNum (ipg);
			ipg.setResult(aftAdvpayDao.findByPage(aftAdvpay));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftAdvpay getById(AftAdvpay aftAdvpay) throws ServiceException {
		try {
			aftAdvpay = aftAdvpayDao.getById(aftAdvpay);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftAdvpay;
	}
	public AftAdvpay getByIdForTrace(AftAdvpay aftAdvpay) throws ServiceException {
		try {
			aftAdvpay = aftAdvpayDao.getByIdForTrace(aftAdvpay);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftAdvpay;
	}

	public void insert(AftAdvpay aftAdvpay) throws ServiceException {
		try {
			/**
			 * 
			 * 新增还款账户变更的变更ID是序列
			 */
			aftAdvpay.setRepId(aftAdvpayDao.getKey());
			aftAdvpayDao.insert(aftAdvpay);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftAdvpay aftAdvpay) throws ServiceException {
		try {
			aftAdvpayDao.update(aftAdvpay);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AftAdvpayDao getAftAdvpayDao() {
		return aftAdvpayDao;
	}

	public void setAftAdvpayDao(AftAdvpayDao aftAdvpayDao) {
		this.aftAdvpayDao = aftAdvpayDao;
	}

	public AcLnMstDao getAcLnMstDao() {
		return acLnMstDao;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public AcDamFormulaBo getAcDamFormulaBo() {
		return acDamFormulaBo;
	}

	public void setAcLnMstDao(AcLnMstDao acLnMstDao) {
		this.acLnMstDao = acLnMstDao;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setAcDamFormulaBo(AcDamFormulaBo acDamFormulaBo) {
		this.acDamFormulaBo = acDamFormulaBo;
	}

	public AcTraceLogDao getAcTraceLogDao() {
		return acTraceLogDao;
	}

	public void setAcTraceLogDao(AcTraceLogDao acTraceLogDao) {
		this.acTraceLogDao = acTraceLogDao;
	}

	public AcLnSetlmtLogDao getAcLnSetlmtLogDao() {
		return acLnSetlmtLogDao;
	}

	public void setAcLnSetlmtLogDao(AcLnSetlmtLogDao acLnSetlmtLogDao) {
		this.acLnSetlmtLogDao = acLnSetlmtLogDao;
	}

	public FiterEngineInterface getFiterEngineInterface() {
		return fiterEngineInterface;
	}

	public void setFiterEngineInterface(FiterEngineInterface fiterEngineInterface) {
		this.fiterEngineInterface = fiterEngineInterface;
	}

	public WsElyRepyDao getWsElyRepyDao() {
		return wsElyRepyDao;
	}

	public void setWsElyRepyDao(WsElyRepyDao wsElyRepyDao) {
		this.wsElyRepyDao = wsElyRepyDao;
	}

	public AcDebitDao getAcDebitDao() {
		return acDebitDao;
	}

	public void setAcDebitDao(AcDebitDao acDebitDao) {
		this.acDebitDao = acDebitDao;
	}

	public LnDueDao getLnDueDao() {
		return lnDueDao;
	}

	public void setLnDueDao(LnDueDao lnDueDao) {
		this.lnDueDao = lnDueDao;
	}

	public ProjAcctDao getProjAcctDao() {
		return projAcctDao;
	}

	public void setProjAcctDao(ProjAcctDao projAcctDao) {
		this.projAcctDao = projAcctDao;
	}

	public LnAcctDao getLnAcctDao() {
		return lnAcctDao;
	}

	public void setLnAcctDao(LnAcctDao lnAcctDao) {
		this.lnAcctDao = lnAcctDao;
	}

	public AcLoanBackLogDao getAcLoanBackLogDao() {
		return acLoanBackLogDao;
	}

	public void setAcLoanBackLogDao(AcLoanBackLogDao acLoanBackLogDao) {
		this.acLoanBackLogDao = acLoanBackLogDao;
	}

	public CorpAcctDao getCorpAcctDao() {
		return corpAcctDao;
	}

	public void setCorpAcctDao(CorpAcctDao corpAcctDao) {
		this.corpAcctDao = corpAcctDao;
	}

	public AcLoanLogDao getAcLoanLogDao() {
		return acLoanLogDao;
	}

	public void setAcLoanLogDao(AcLoanLogDao acLoanLogDao) {
		this.acLoanLogDao = acLoanLogDao;
	}

	public AcDebitSuspBo getAcDebitSuspBo() {
		return acDebitSuspBo;
	}

	public void setAcDebitSuspBo(AcDebitSuspBo acDebitSuspBo) {
		this.acDebitSuspBo = acDebitSuspBo;
	}

	public AcChrgLogDao getAcChrgLogDao() {
		return acChrgLogDao;
	}

	public void setAcChrgLogDao(AcChrgLogDao acChrgLogDao) {
		this.acChrgLogDao = acChrgLogDao;
	}

}