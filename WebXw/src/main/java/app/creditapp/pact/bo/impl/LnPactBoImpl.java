package app.creditapp.pact.bo.impl;

import java.util.List;
import java.util.Map;

import com.dhcc.workflow.api.WorkflowException;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.aft.dao.AftAssetRelDao;
import app.creditapp.aft.entity.AftAssetRel;
import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.dao.LnPactDao;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.wkf.bo.WfTaskBo;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.AppWkfcfg;
import app.creditapp.wkf.entity.WfTask;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.creditapp.wkf.entity.WkfParm;
import app.creditapp.wkfinterface.WkfInterface;
import app.util.AppConstant;
import app.util.toolkit.Ipage;

/**
 * Title: LnPactBoImplImpl.java Description:
 * 
 **/
public class LnPactBoImpl extends BaseService implements LnPactBo {

	private LnPactDao lnPactDao;
	private AftAssetRelDao aftAssetRelDao;
	private WkfApprovalUserBo wkfApprovalUserBo;
	private WfTaskBo wfTaskBo;
	private WkfInterface wkfInterface;

	public void del(LnPact lnPact) throws ServiceException {
		try {
			lnPactDao.del(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnPact lnPact) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnPactDao
					.getCount(lnPact) });// 初始化分页类
			lnPact.setStartNumAndEndNum(ipg);
			ipg.setResult(lnPactDao.findByPage(lnPact));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public Ipage findByPageForTask(Ipage ipg, LnPact lnPact) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnPactDao
					.getCountForTask(lnPact) });// 初始化分页类
			lnPact.setStartNumAndEndNum(ipg);
			ipg.setResult(lnPactDao.findByPageForTask(lnPact));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public Ipage findByPageForList(Ipage ipg, LnPact lnPact) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnPactDao
					.getCountForList(lnPact) });// 初始化分页类
			lnPact.setStartNumAndEndNum(ipg);
			ipg.setResult(lnPactDao.findByPageForList(lnPact));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public List<LnPact> findByPageForAppr(LnPact lnPact) throws ServiceException {
		List<LnPact>  lnPactList;
		try {
			lnPactList =  lnPactDao.findByPageForAppr(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> findByPageForBatchNo(LnPact lnPact) throws ServiceException {
		List<LnPact>  lnPactList;
		try {
			lnPactList =  lnPactDao.findByPageForBatchNo(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> findByPageAppr(LnPact lnPact) throws ServiceException {
		List<LnPact>  lnPactList = null;
		try {
			//lnPactList =  lnPactDao.findByPageAppr(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPactList;
	}
	
	public String doSubmitUpdate(WkfParm parm,LnPact lnPact, String opNo,
			String brNo) throws ServiceException {
		String keyID="";
		String taskId = "";
		String nextDesc ="描述";
		String if_risk_part="0";
		try {
			String instanceId = null;
			WkfApprovalUser wkfApprovalUser = new WkfApprovalUser();
			wkfApprovalUser.setWkfUserName(opNo);
			List<WkfApprovalUser> wauList = wkfApprovalUserBo.getAllList(wkfApprovalUser);
			
			AppWkfcfg appWkfcfg = new AppWkfcfg();
			appWkfcfg.setAppType("01");//审批类型
			appWkfcfg.setBrNo("00000"); // 设置合作机构编号
			keyID = wkfInterface.getWkfNo(appWkfcfg);
			
//			instanceId = wkfInterface.startProcess(keyID, parm,lnPact.getAppId(),opNo);
			WfTask wfTask = new WfTask();
			wfTask.setTaskAppId(lnPact.getAppId());
			wfTask = wfTaskBo.findTaskByAppId(wfTask);
			instanceId = wfTask.getTaskExcutionId();
			
			//客户经理默认同意提交，如果工作流程中，不显示这个节点，退回就退回不到客户经理。成功返回流程ID
//			String state = WFUtil.getTaskService().takeComplete(taskId,TaskConstants.PASS,"同意",null,null,opNo);
			if (instanceId != null) {
				// 更改审批状态
				lnPact.setApprSts(AppConstant.LN_APPR_PENDING);
				lnPact.setProcessId(instanceId);
				lnPactDao.update(lnPact);
			}
		}catch(WorkflowException e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return nextDesc;
	}
	public Ipage findByPageForRel(Ipage ipg, LnPact lnPact) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnPactDao
					.getCountForRel(lnPact) });// 初始化分页类
			lnPact.setStartNumAndEndNum(ipg);
			ipg.setResult(lnPactDao.findByPageForRel(lnPact));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	

	public LnPact getById(LnPact lnPact) throws ServiceException {
		try {
			lnPact = lnPactDao.getById(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPact;
	}
	public LnPact getByIdForAppId(LnPact lnPact) throws ServiceException {
		try {
			lnPact = lnPactDao.getByIdForAppId(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPact;
	}

	public List<LnPact> getByIdForApp(LnPact lnPact, Ipage ipage, String userId,String branchId) throws ServiceException {
		List<LnPact> lnPactList;
		try {
			// Ipage ipage = this.getIpage();
			WkfApprovalUser wkfUser = new WkfApprovalUser();
			wkfUser.setWkfUserName(userId);
			wkfUser.setWkfBrNo(branchId);
			lnPact.setUserId(userId);
			lnPact.setBranchId(branchId);

			lnPactList = lnPactDao.getByIdForApp(lnPact);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return lnPactList;
	}
	public List<LnPact> getByIdForBatchNo(LnPact lnPact) throws ServiceException {
		List<LnPact> lnPactList;
		try {
			lnPactList = lnPactDao.getByIdForBatchNo(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPactList;
	}
	
	public List<LnPact> getWfTaskList(LnPact lnPact) throws ServiceException {
		List<LnPact> lnPactList;
		try {
			lnPactList = lnPactDao.getWfTaskList(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPactList;
	}
	public LnPact getByIdForAppr(LnPact lnPact) throws ServiceException {
		try {
			lnPact = lnPactDao.getByIdForAppr(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPact;
	}
	public LnPact getByIdForAppAndBatch(LnPact lnPact) throws ServiceException {
		try {
			lnPact = lnPactDao.getByIdForAppAndBatch(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPact;
	}
	public LnPact getByIdForList(LnPact lnPact) throws ServiceException {
		try {
			lnPact = lnPactDao.getByIdForList(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPact;
	}



	
	public LnPact getByAppId(LnPact lnPact) throws ServiceException{
		try {
			lnPact = lnPactDao.getByAppId(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPact;
	}
	

	public LnPact getPactNoAndBrNo(LnPact lnPact) throws ServiceException {
		try {
			lnPact = lnPactDao.getPactNoAndBrNo(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPact;
	}
	public void insert(LnPact lnPact) throws ServiceException {
		try {
			lnPactDao.insert(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnPact lnPact) throws ServiceException {
		try {
			lnPactDao.update(lnPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPageQuotaForCif(Ipage ipage, LnPact lnPact)
			throws ServiceException {  
		try {
			ipage.initPageCounts(new Integer[] { (Integer) lnPactDao
					.getCountQuotaForCif(lnPact) });// 初始化分页类
			lnPact.setStartNumAndEndNum(ipage);
			ipage.setResult(lnPactDao.findByPageQuotaForCif(lnPact));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
	public void doReplace(Map<String, String> map) throws ServiceException {
		try {
			String assId = map.get("assId");
			// 更新申请信息
			AftAssetRel aftAssetRel = new AftAssetRel();
			aftAssetRel.setAssId(assId);
			aftAssetRelDao.updateForLn(aftAssetRel);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}
	
	public List<LnPact> findListToWorkE() throws ServiceException{
		List<LnPact> lnPactList;
		try {
			lnPactList =  lnPactDao.findListToWorkE();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnPactList;
	}
	
	public LnPactDao getLnPactDao() {
		return lnPactDao;
	}

	public void setLnPactDao(LnPactDao lnPactDao) {
		this.lnPactDao = lnPactDao;
	}

	public AftAssetRelDao getAftAssetRelDao() {
		return aftAssetRelDao;
	}

	public void setAftAssetRelDao(AftAssetRelDao aftAssetRelDao) {
		this.aftAssetRelDao = aftAssetRelDao;
	}

	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}

	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}

	public WkfInterface getWkfInterface() {
		return wkfInterface;
	}

	public void setWkfInterface(WkfInterface wkfInterface) {
		this.wkfInterface = wkfInterface;
	}

	public WfTaskBo getWfTaskBo() {
		return wfTaskBo;
	}

	public void setWfTaskBo(WfTaskBo wfTaskBo) {
		this.wfTaskBo = wfTaskBo;
	}

}