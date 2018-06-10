package  app.creditapp.acc.loan.bo.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import accounting.biz.pub.ParmBiz;
import accounting.plat.PUBConstant;
import accounting.plat.TransCode;
import accounting.plat.core.AccountingException;
import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.loan.bo.AcLnLoBo;
import app.creditapp.acc.loan.dao.AcDebitDao;
import app.creditapp.acc.loan.dao.AcLnLoDao;
import app.creditapp.acc.loan.entity.AcDebit;
import app.creditapp.acc.loan.entity.AcDebitDtl;
import app.creditapp.acc.loan.entity.AcLnLo;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.log.dao.AcChrgLogDao;
import app.creditapp.acc.log.dao.AcTraceLogDao;
import app.creditapp.acc.log.entity.AcChrgLog;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn3203;
import app.creditapp.inf.entity.WsOut3203_1;
import app.creditapp.ln.dao.LnAcctDao;
import app.creditapp.ln.dao.LnDueDao;
import app.creditapp.ln.entity.LnAcct;
import app.creditapp.ln.entity.LnDue;
import app.creditapp.proj.dao.ProjAcctDao;
import app.creditapp.proj.entity.ProjAcct;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: AcLnLoBoImplImpl.java
* Description:
**/
public class AcLnLoBoImpl extends BaseService implements AcLnLoBo {

	private AcLnLoDao acLnLoDao;
	private FiterEngineInterface filterEngineInterface;
	private AcChrgLogDao acChrgLogDao;
	private AcTraceLogDao acTraceLogDao;
	private DataSource dataSource;
	private LnDueDao lnDueDao;
	private LnAcctDao lnAcctDao;
	private ProjAcctDao projAcctDao;
	private AcDebitDao acDebitDao;
	 
	 
	public void del(AcLnLo acLnLo) throws ServiceException {
		try {
			acLnLoDao.del(acLnLo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLnLo acLnLo)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnLoDao
					.getCount(acLnLo) });// 初始化分页类
			acLnLo.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnLoDao.findByPage(acLnLo));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcLnLo getById(AcLnLo acLnLo) throws ServiceException {
		try {
			acLnLo = acLnLoDao.getById(acLnLo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnLo;
	}
	
	public AcLnLo getLoAmt(AcLnLo acLnLo) throws ServiceException {
		try {
			acLnLo = acLnLoDao.getLoAmt(acLnLo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnLo;
	}

	public void insert(AcLnLo acLnLo) throws ServiceException {
		try {
			acLnLoDao.insert(acLnLo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLnLo acLnLo) throws ServiceException {
		try {
			acLnLoDao.update(acLnLo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-4
	 * @描述	欠款测算
	 */
	public void lnLoCalcul(AcLnMst acLnMst) throws ServiceException{
		
		//获取 欠款金额 （欠本、欠息、罚息）
		AcLnLo acLnLo = new AcLnLo();
		acLnLo.setLoanNo(acLnMst.getLoanNo());
		acLnLo = acLnLoDao.getLoAmt(acLnLo);
		
		double loPrcpAmt = acLnLo.getPrcpAmt();//欠本
		double loNormInt = acLnLo.getNormInt();//欠息
		double loFineInt = acLnLo.getFineInt();//罚息
		
		AcChrgLog acChrgLog = new AcChrgLog();
		acChrgLog.setLoanNo(acLnMst.getLoanNo());
		acChrgLog.setChrgSts(PUBConstant.CHRG_STS_20);//拖欠
		double loFeeAmt = acChrgLogDao.getLoFeeAmt(acChrgLog);//欠费
	}
	
	/**
	 * 
	 * @throws AccountingException 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-4
	 * @描述	欠款归还
	 */
	public void returnLnlo(AcLnMst acLnMst) throws ServiceException, AccountingException{
		Connection conn = this.getConnection();
		try{
			AcTraceLog acTraceLog = new AcTraceLog();
			String traceNo =  acTraceLogDao.getKey();
			
			//获取 欠款金额 （欠本、欠息、罚息）
			AcLnLo acLnLo = new AcLnLo();
			acLnLo.setLoanNo(acLnMst.getLoanNo());
			acLnLo = acLnLoDao.getLoAmt(acLnLo);
			
			if(acLnLo != null){
				double loPrcpAmt = acLnLo.getPrcpAmt();//欠本
				double loNormInt = acLnLo.getNormInt();//欠息
				double loFineInt = acLnLo.getFineInt();//罚息
				
				AcChrgLog acChrgLog = new AcChrgLog();
				acChrgLog.setLoanNo(acLnMst.getLoanNo());
				acChrgLog.setChrgSts(PUBConstant.CHRG_STS_20);//拖欠
				double loFeeAmt = acChrgLogDao.getLoFeeAmt(acChrgLog);//欠费
				
				double loAmt = loPrcpAmt + loNormInt + loFineInt + loFeeAmt;//欠款总计
				
				LnDue lnDue = new LnDue();
				lnDue.setDueNo(acLnMst.getLoanNo());
				lnDue = lnDueDao.getById(lnDue);
				
				//贷款账号信息
				LnAcct lnAcct = new LnAcct();
				lnAcct.setAppId(lnDue.getAppId());
				lnAcct.setAcUse("01");//扣款账号
				lnAcct = lnAcctDao.getById(lnAcct);
				
				//信托账号信息
				ProjAcct projAcct = new ProjAcct();
				projAcct.setProjNo(acLnMst.getProjNo());
				projAcct.setAcctType("01");//专户
				projAcct = projAcctDao.getByProjNoAndAcctType(projAcct);
				
				AcDebitDtl acDebitDtl = new AcDebitDtl();
				acDebitDtl.setTraceNo(traceNo);
				acDebitDtl.setLoanNo(acLnMst.getLoanNo());
				acDebitDtl.setPactNo(acLnMst.getPactNo());
				acDebitDtl.setBrNo(acLnMst.getBrNo());
				acDebitDtl.setDdtlNo(ParmBiz.getAcDebitDtlNo(conn));
				acDebitDtl.setDdtlPrcpAmt(loPrcpAmt);
				acDebitDtl.setDdtlNormInt(loNormInt);
				acDebitDtl.setDdtlFineInt(loFineInt);
				acDebitDtl.setDdtlFeeAmt(loFeeAmt);
				acDebitDtl.setDdtlAmt(loAmt);
				acDebitDtl.setDdtlDate(ParmBiz.getOracleTxDate(conn));
				acDebitDtl.setXtAcNo(projAcct.getAcctNo());
				acDebitDtl.setDdtlAcNo(lnAcct.getAcNo());
				acDebitDtl.setDdtlAcName(lnAcct.getAcName());
				acDebitDtl.setDdtlBankCode(lnAcct.getBankCode());
				acDebitDtl.setDdtlBankCity(lnAcct.getBankCity());
				acDebitDtl.setDdtlBankProv(lnAcct.getBankProv());
				acDebitDtl.setDdtlBankSite(lnAcct.getBankSite());
				acDebitDtl.setDdtlSts("02");
				acDebitDtl.setTxDate(ParmBiz.getOracleTxDate(conn));
				acDebitDtl.setTxTime(ParmBiz.getOracleTxTime(conn));
				
				AcDebit acDebit = new AcDebit();
				acDebit.setTraceCnt(1);
				acDebit.setTraceNo(traceNo);
				acDebit.setDebitNo(ParmBiz.getDebitNo(conn));
				acDebit.setTxDt(ParmBiz.getOracleTxDate(conn));
				acDebit.setLoanNo(acLnMst.getLoanNo());
				acDebit.setPactNo(acLnMst.getPactNo());
				acDebit.setBrNo(acLnMst.getBrNo());
				acDebit.setAcctBankCde(acDebitDtl.getDdtlBankCode());
				acDebit.setAcNo(acDebitDtl.getDdtlAcNo());
				acDebit.setXtAcNo(acDebitDtl.getXtAcNo());
				acDebit.setCurNo(acLnMst.getCurNo());
				acDebit.setDebitType("01");
				acDebit.setAtpyAmt(loAmt);
				acDebit.setLoAmt(loAmt);
				acDebit.setCurAmt(0.00);
				acDebit.setMyFeeAmt(0.00);
				acDebit.setOtherFeeAmt(0.00);
				acDebit.setRepayAmt(0.00);
				acDebit.setSts("0");
				acDebit.setCreateDt(ParmBiz.getOracleTxDate(conn));
				acDebitDao.insert(acDebit);
				
				acTraceLog.setTraceNo(traceNo);
				acTraceLog.setTraceCnt(1);
				acTraceLog.setTxDt(ParmBiz.getOracleTxDate(conn));
				acTraceLog.setTxTime(ParmBiz.getOracleTxTime(conn));
				acTraceLog.setTxBrNo(acLnMst.getBrNo());
				acTraceLog.setTxCde(TransCode.LNLO);
				acTraceLog.setSubTxCde(TransCode.LNLO);
				acTraceLog.setSvcInd(TransCode.LNLO);
				acTraceLog.setBrNo(acLnMst.getBrNo());
				acTraceLog.setPactNo(acLnMst.getPactNo());
				acTraceLog.setLoanNo(acLnMst.getLoanNo());
				acTraceLog.setMemo("欠款归还");
				
				acTraceLogDao.insert(acTraceLog);
			}else{
				throw new AccountingException("该贷款无欠款记录");
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
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
	//接口ws3203校验
	public ResponseParm getresponseParm(WsIn3203 wsIn3203)throws ServiceException{
		ResponseParm responseParm =  new ResponseParm();
		ValidateLog _vlws3203;
		responseParm.setRespCode("0000");
		responseParm.setRespDesc("信息查询成功！");
		try {
			_vlws3203 = filterEngineInterface.createValidateLog("wsIn3203Id", wsIn3203, true);
			if(!_vlws3203.isSuccess()){
				//校验未通过设置返回码为0001
				responseParm.setRespCode("9004");
				responseParm.setRespDesc("数据校验完成，但输入数据格式存在错误！");
			}else{
				AcLnLo wsIn3203forsearch = new AcLnLo();
				String brNo = wsIn3203.getBrNo();
				String pactNo = wsIn3203.getPactNo();
				wsIn3203forsearch.setBrNo(brNo);
				int count = acLnLoDao.getCount(wsIn3203forsearch);
				//判断是否存在该批次号
				if(count == 0){
					responseParm.setRespCode("1004");
					responseParm.setRespDesc("查询记录不存在！");
				}else{
					wsIn3203forsearch.setPactNo(pactNo);
					int countforPactNo = acLnLoDao.getCount(wsIn3203forsearch);
					if(countforPactNo == 0){
						responseParm.setRespCode("1001");
						responseParm.setRespDesc("合同号为：  "+ pactNo +"  的记录不存在！");
					}
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseParm;
	}
	//接口ws3203符合查找条件 返回分页信息
	public Ipage findByPageforws3203(Ipage ipg, AcLnLo acLnLo)throws ServiceException {
			try {
					ipg.initPageCounts(new Integer[] { (Integer) acLnLoDao.getCountfor3203(acLnLo) });// 初始化分页类
						acLnLo.setStartNumAndEndNum (ipg);
						ipg.setResult(acLnLoDao.findByPageforws3203(acLnLo));
			} catch (Exception e) {
				throw new ServiceException(e.getMessage());
			}
			return ipg;
	}
	//接口ws3203符合查找条件 返回总数数量
	public int  getCountforws3203(AcLnLo acLnLo) throws ServiceException {
		int count = 0;
		try {
			count = acLnLoDao.getCountfor3203(acLnLo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}


	public AcLnLoDao getAcLnLoDao() {
		return acLnLoDao;
	}

	public void setAcLnLoDao(AcLnLoDao acLnLoDao) {
		this.acLnLoDao = acLnLoDao;
	}

	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
	}

	@Override
	public AcLnLo getAcLnLoByCnt(AcLnLo acLnLo) {
		return acLnLoDao.getAcLnLoByCnt(acLnLo);
	}
	
}