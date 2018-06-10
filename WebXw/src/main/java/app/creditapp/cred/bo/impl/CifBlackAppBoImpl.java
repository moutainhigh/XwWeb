package  app.creditapp.cred.bo.impl;

import java.util.List;

import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.WorkflowException;
import com.dhcc.workflow.api.task.Task;
import com.dhcc.workflow.pvm.internal.task.TaskConstants;

import app.base.BaseService;
import app.base.ServiceException;
import app.base.httpclient.entity.SendMessageEntity;
import app.base.httpclient.entity.SendMessageType;
import app.base.httpclient.work.SendMessageMain;
import app.util.AppConstant;
import app.util.toolkit.Ipage;
import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;
import app.creditapp.cif.dao.CifBlackDao;
import app.creditapp.cif.entity.CifBlack;
import app.creditapp.cred.bo.CifBlackAppBo;
import app.creditapp.cred.dao.CifBlackAppDao;
import app.creditapp.cred.entity.CifBlackApp;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.AppWkfcfg;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.creditapp.wkf.entity.WkfParm;
import app.creditapp.wkfinterface.WkfInterface;
/**
* Title: CifBlackAppBoImplImpl.java
* Description:
**/
public class CifBlackAppBoImpl extends BaseService implements CifBlackAppBo {

	private CifBlackAppDao cifBlackAppDao;
	private WkfApprovalUserBo wkfApprovalUserBo;
	private WkfInterface wkfInterface;
	private CifBlack cifBlack;
	private CifBlackDao cifBlackDao;

	public void del(CifBlackApp cifBlackApp) throws ServiceException {
		try {
			cifBlackAppDao.del(cifBlackApp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CifBlackApp cifBlackApp)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifBlackAppDao
					.getCount(cifBlackApp) });// 初始化分页类
			cifBlackApp.setStartNumAndEndNum (ipg);
			ipg.setResult(cifBlackAppDao.findByPage(cifBlackApp));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CifBlackApp getById(CifBlackApp cifBlackApp) throws ServiceException {
		try {
			cifBlackApp = cifBlackAppDao.getById(cifBlackApp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifBlackApp;
	}

	public void insert(CifBlackApp cifBlackApp) throws ServiceException {
		try {
			/**
			 * 新增黑名单申请ID信息,黑名单ID的ID号dou是序列
			 */
			cifBlackApp.setAppId(cifBlackAppDao.getAppKey());
			cifBlackAppDao.insert(cifBlackApp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CifBlackApp cifBlackApp) throws ServiceException {
		try {
			cifBlackAppDao.update(cifBlackApp);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public String doSubmit(WkfParm parm,CifBlackApp cifBlackApp, String opNo,
			String brNo) throws ServiceException {
		String keyID="";
		String taskId = "";
		String nextDesc ="";
		String if_risk_part="0";
		try {
			String instanceId = null;
			WkfApprovalUser wkfApprovalUser = new WkfApprovalUser();
			wkfApprovalUser.setWkfUserName(opNo);
			List<WkfApprovalUser> wauList = wkfApprovalUserBo.getAllList(wkfApprovalUser);
			
			AppWkfcfg appWkfcfg = new AppWkfcfg();//流程图
			appWkfcfg.setAppType("03");//审批类型
			keyID = wkfInterface.getWkfNo(appWkfcfg);//流程标示
			
			instanceId = wkfInterface.startProcess(keyID, parm,cifBlackApp.getBlkId(),opNo);
			//取taskId 任务编号 接口
			Task task = WFUtil.getTaskService().findTaskByProcessInstanceId(instanceId);
			if( task != null ) {
				taskId = task.getId();
				nextDesc = WFUtil.getTaskService().getNextTaskDesc(taskId);
				String[] nextRole = WFUtil.getTaskService().getNextTaskExecutor(taskId);//获取下一个流程节点角色
				String RoleNo = nextRole[1];
				SendMessageEntity sendMessageEntity=new SendMessageEntity();//发送消息
				String title="黑名单";
	            String contet="有待审批业务";
		    	sendMessageEntity.setTitle(title);
		    	sendMessageEntity.setContet(contet);
		    	sendMessageEntity.setGroupNo(RoleNo);
		    	sendMessageEntity.setSendType(SendMessageType.MESSAGE);
		    	sendMessageEntity.setPasSubTypeEntity(PasSubTypeEntity.RewFundMessage);
		    	SendMessageMain.sendMessage(sendMessageEntity);	
			}
			//客户经理默认同意提交，如果工作流程中，不显示这个节点，退回就退回不到客户经理。成功返回流程ID
			String state = WFUtil.getTaskService().takeComplete(taskId,TaskConstants.PASS,"同意",null,null,opNo);
			if (instanceId != null) {
				// 更改审批状态
				cifBlackApp.setAppSts("02");
				cifBlackAppDao.update(cifBlackApp);
				cifBlack = new CifBlack();
				cifBlack.setBlkId(cifBlackApp.getBlkId());
				cifBlack = cifBlackDao.getById(cifBlack);
				cifBlack.setBlkSts("02");
				cifBlack.setProcessId(instanceId);
				cifBlackDao.update(cifBlack);
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
	public CifBlackAppDao getCifBlackAppDao() {
		return cifBlackAppDao;
	}

	public void setCifBlackAppDao(CifBlackAppDao cifBlackAppDao) {
		this.cifBlackAppDao = cifBlackAppDao;
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

	public CifBlack getCifBlack() {
		return cifBlack;
	}

	public void setCifBlack(CifBlack cifBlack) {
		this.cifBlack = cifBlack;
	}

	public CifBlackDao getCifBlackDao() {
		return cifBlackDao;
	}

	public void setCifBlackDao(CifBlackDao cifBlackDao) {
		this.cifBlackDao = cifBlackDao;
	}
}