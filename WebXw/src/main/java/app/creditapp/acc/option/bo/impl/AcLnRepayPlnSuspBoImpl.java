package  app.creditapp.acc.option.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.loan.dao.AcLnMstDao;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.option.bo.AcLnRepayPlnSuspBo;
import app.creditapp.acc.option.dao.AcLnRepayPlnSuspDao;
import app.creditapp.acc.option.entity.AcLnRepayPlnSusp;
import app.creditapp.inf.dao.WsRepyPlanDao;
import app.creditapp.inf.entity.WsRepyPlan;
import app.util.toolkit.Ipage;
/**
* Title: AcLnRepayPlnSuspBoImplImpl.java
* Description:
**/
public class AcLnRepayPlnSuspBoImpl extends BaseService implements AcLnRepayPlnSuspBo {

	Logger logger = Logger.getLogger(AcLnRepayPlnSuspBoImpl.class);
	private AcLnRepayPlnSuspDao acLnRepayPlnSuspDao;
	private AcLnMstDao acLnMstDao;
	private WsRepyPlanDao wsRepyPlanDao;

	public WsRepyPlanDao getWsRepyPlanDao() {
		return wsRepyPlanDao;
	}

	public void setWsRepyPlanDao(WsRepyPlanDao wsRepyPlanDao) {
		this.wsRepyPlanDao = wsRepyPlanDao;
	}

	public void del(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException {
		try {
			acLnRepayPlnSuspDao.del(acLnRepayPlnSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLnRepayPlnSusp acLnRepayPlnSusp)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnRepayPlnSuspDao
					.getCount(acLnRepayPlnSusp) });// 初始化分页类
			acLnRepayPlnSusp.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnRepayPlnSuspDao.findByPage(acLnRepayPlnSusp));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcLnRepayPlnSusp getById(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException {
		try {
//			System.out.println(acLnRepayPlnSuspDao+"$$$$$$$$$$$"+lnPactDao);
			acLnRepayPlnSusp = acLnRepayPlnSuspDao.getById(acLnRepayPlnSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnRepayPlnSusp;
	}

	public void insert(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException {
		try {
			acLnRepayPlnSuspDao.insert(acLnRepayPlnSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException {
		try {
			acLnRepayPlnSuspDao.update(acLnRepayPlnSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<String> getByBatchDisPact(String wsRepyPlanBatch) throws ServiceException {
		List<String> wsRepyPlanList = null;
		try {
			wsRepyPlanList = acLnRepayPlnSuspDao.getByBatchDisPact(wsRepyPlanBatch);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsRepyPlanList;
	}
	
	public void delByPactnoAndBrno(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException {
		
		try {
			acLnRepayPlnSuspDao.delByPactnoAndBrno(acLnRepayPlnSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
    public List<WsRepyPlan> getListByBatchAndPactno(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException {
    	List<WsRepyPlan> wsRepyPlanList = null;
		try {
			wsRepyPlanList = acLnRepayPlnSuspDao.getListByBatchAndPactno(acLnRepayPlnSusp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsRepyPlanList;
	}
	

	////还款计划 业务逻辑的合法性判断重写：sunmingyi
	public boolean validAcLnRepayPlnSusp(String acLnRepayPlnSuspBatch,String brNo){

		boolean result = false;
		//记录该批次下某一合同所有还款计划
		List<WsRepyPlan> wsRepyPlanList = new ArrayList<WsRepyPlan>();
		
		//查询去重复的合同号
		List<String> wsRepyPlnPactList = new ArrayList<String>();
		wsRepyPlnPactList = acLnRepayPlnSuspDao.getByBatchDisPact(acLnRepayPlnSuspBatch);
		
		AcLnRepayPlnSusp acLnRepayPlnSusp = new AcLnRepayPlnSusp();
		for(String pactNos:wsRepyPlnPactList){
			boolean passFlag = true;//是否通过校验
			//获取贷款主表信息
			AcLnMst acLnMst = new AcLnMst();
			acLnMst.setPactNo(pactNos);
			acLnMst.setBrNo(brNo);
			acLnMst = acLnMstDao.getByPactNo(acLnMst);
			//判断主文件不存在，状态为空都是放款失败数据，该合同对应的还款计划拒收
			if(acLnMst==null || acLnMst.getLoanSts()==null || acLnMst.getLoanSts().length()==0) {
				logger.info("合同号："+pactNos+"贷款主文件不存在");
				continue; 
			}
			
			WsRepyPlan wsRepyPlan = new WsRepyPlan();
			wsRepyPlan.setPactNo(pactNos);
			wsRepyPlan.setBrNo(brNo);
			wsRepyPlan.setBatchNo(acLnRepayPlnSuspBatch);
			//1、同一个合同中的每期应还本金之和 PRCP_AMT(待整合)应等于贷款金额
			double totalPrcpAmt = wsRepyPlanDao.getPrcpAmtCount(wsRepyPlan);
			if(totalPrcpAmt!=acLnMst.getLoanAmt()) {
				logger.info("合同号："+pactNos+"放款金额与还款计划应收本金总额不等");
				continue; 
			}
			
			//查询该合同下所有还款计划
			acLnRepayPlnSusp = new AcLnRepayPlnSusp();
			acLnRepayPlnSusp.setBatchNo(acLnRepayPlnSuspBatch);
			acLnRepayPlnSusp.setPactNo(pactNos);
			wsRepyPlanList = acLnRepayPlnSuspDao.getListByBatchAndPactno(acLnRepayPlnSusp);
			int perdNo = 1;
			for(WsRepyPlan wsRpl : wsRepyPlanList){
				//每一期的利息必须大于等于0
				if(wsRpl.getNormInt()<0){
					passFlag=false;
					logger.info("合同号："+pactNos+"上传的还款计划中存在利息小于0的信息");
					break;
				}
				
				//同一个合同的期次号必须是连续的。
				if(perdNo!=wsRpl.getCnt()){
					passFlag=false;
					logger.info("合同号："+pactNos+"上传的还款计划期次号不连续");
					break;
				}
				
				perdNo += 1;
				
//				//同一个合同的最大到期日=贷款主表的到期日end_date。
//				if(wsRepyPlanList.size()==wsRpl.getCnt()){
//					if(!wsRpl.getEndDate().equals(acLnMst.getMtrDt())){
//						passFlag=false;
//						break;
//					}
//				}
				
			}
			//判断是否通过校验，通过了则将还款计划插入ac_ln_repay_pln_susp
			if (passFlag) {
				acLnRepayPlnSusp.setBrNo(brNo);
				acLnRepayPlnSusp.setPactNo(pactNos);
				acLnRepayPlnSuspDao.delByPactnoAndBrno(acLnRepayPlnSusp);

				for (WsRepyPlan wsRpl : wsRepyPlanList) {
					acLnRepayPlnSusp = new AcLnRepayPlnSusp();
					acLnRepayPlnSusp.setWsId(wsRpl.getWsId());
					acLnRepayPlnSusp.setBatchNo(wsRpl.getBatchNo());
					acLnRepayPlnSusp.setBrNo(wsRpl.getBrNo());
					acLnRepayPlnSusp.setPactNo(wsRpl.getPactNo());
					acLnRepayPlnSusp.setCnt(wsRpl.getCnt());
					acLnRepayPlnSusp.setBegDate(wsRpl.getBegDate());
					acLnRepayPlnSusp.setEndDate(wsRpl.getEndDate());
					acLnRepayPlnSusp.setTotalAmt(wsRpl.getTotalAmt());
					acLnRepayPlnSusp.setPrcpAmt(wsRpl.getPrcpAmt());
					acLnRepayPlnSusp.setNormInt(wsRpl.getNormInt());
					acLnRepayPlnSusp.setSts(wsRpl.getSts());
					acLnRepayPlnSusp.setTxDate(wsRpl.getTxDate());
					acLnRepayPlnSusp.setDealSts("01");
					acLnRepayPlnSusp.setDealDesc("待处理");
					
					acLnRepayPlnSuspDao.insert(acLnRepayPlnSusp);
				}
				result = true; 
			}
		}
		return result;
	}
	
	//判断是否连续
	private boolean IsSeries(int[] num)
    {
		boolean b = true;
        for (int i = 0; i < num.length; i++)
        {
            if (num[i] != i + 1)
            {
                b = false;
                break;
            }
        }
        return b;
    }
	
	
	
	
	public AcLnRepayPlnSuspDao getAcLnRepayPlnSuspDao() {
		return acLnRepayPlnSuspDao;
	}

	public void setAcLnRepayPlnSuspDao(AcLnRepayPlnSuspDao acLnRepayPlnSuspDao) {
		this.acLnRepayPlnSuspDao = acLnRepayPlnSuspDao;
	}

	public AcLnMstDao getAcLnMstDao() {
		return acLnMstDao;
	}

	public void setAcLnMstDao(AcLnMstDao acLnMstDao) {
		this.acLnMstDao = acLnMstDao;
	}
}