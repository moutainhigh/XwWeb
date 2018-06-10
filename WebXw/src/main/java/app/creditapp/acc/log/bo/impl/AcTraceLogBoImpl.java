package  app.creditapp.acc.log.bo.impl;

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
import app.creditapp.acc.log.bo.AcTraceLogBo;
import app.creditapp.acc.log.dao.AcTraceLogDao;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.aft.entity.aftMessage.PasSubTypeEntity;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.AppWkfcfg;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.creditapp.wkf.entity.WkfParm;
import app.creditapp.wkfinterface.WkfInterface;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
/**
* Title: AcTraceLogBoImplImpl.java
* Description:
**/
public class AcTraceLogBoImpl extends BaseService implements AcTraceLogBo {

	private AcTraceLogDao acTraceLogDao;
    private FiterEngineInterface filterEngineInterface;
    
    private WkfApprovalUserBo wkfApprovalUserBo;
	private WkfInterface wkfInterface;

	public void del(AcTraceLog acTraceLog) throws ServiceException {
		try {
			acTraceLogDao.del(acTraceLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcTraceLog acTraceLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acTraceLogDao
					.getCountForRead(acTraceLog)});// 初始化分页类
			acTraceLog.setStartNumAndEndNum (ipg);
			ipg.setResult(acTraceLogDao.findByPage(acTraceLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public Ipage findByPageForTask(Ipage ipg, AcTraceLog acTraceLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acTraceLogDao
					.getCountForTask(acTraceLog) });// 初始化分页类
			acTraceLog.setStartNumAndEndNum(ipg);
			ipg.setResult(acTraceLogDao.findByPageForTask(acTraceLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcTraceLog getById(AcTraceLog acTraceLog) throws ServiceException {
		try {
			acTraceLog = acTraceLogDao.getById(acTraceLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acTraceLog;
	}
	
	public AcTraceLog getRecentTraceLog(AcTraceLog acTraceLog) throws ServiceException {
		try {
			acTraceLog = acTraceLogDao.getRecentTraceLog(acTraceLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acTraceLog;
	}

	public void insert(AcTraceLog acTraceLog) throws ServiceException {
		try {
			acTraceLogDao.insert(acTraceLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcTraceLog acTraceLog) throws ServiceException {
		try {
			acTraceLogDao.update(acTraceLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//接口ws3202校验
//	public ResponseParm getresponseParm(WsIn3202 wsIn3202)throws ServiceException{
//		ResponseParm responseParm = new ResponseParm();
//		ValidateLog _vlws3202;
//		responseParm.setRespCode("0000");
//		responseParm.setRespDesc("查询成功！");
//		try {
//			_vlws3202 = filterEngineInterface.createValidateLog("wsIn3202Id", wsIn3202, true);
//			if(!_vlws3202.isSuccess()){
//				//校验未通过设置返回码为0001
//				responseParm.setRespCode("9004");
//				responseParm.setRespDesc("数据校验完成，但输入数据格式存在错误！");
//			}else{
//				AcTraceLog wsIn3202forsearch = new AcTraceLog();
//				String brNo = wsIn3202.getBrNo();
//				String pactNo = wsIn3202.getPactNo();
//				String txdate = wsIn3202.getTxDate();
//				wsIn3202forsearch.setBrNo(brNo);
//				int count = acTraceLogDao.getCountforws3202(wsIn3202forsearch);
//				//判断是否存在该批次号
//				if(count == 0){
//					responseParm.setRespCode("1004");
//					responseParm.setRespDesc("查询记录不存在！");
//				}else{
//					wsIn3202forsearch.setPactNo(pactNo);
//					int countforPactNo = acTraceLogDao.getCountforws3202(wsIn3202forsearch);
//					if(countforPactNo == 0){
//						responseParm.setRespCode("1001");
//						responseParm.setRespDesc("合同号为：  "+ pactNo +"  的记录不存在！");
//					}else{
//						wsIn3202forsearch.setTxDt(txdate);
//						int countforTxdate = acTraceLogDao.getCountforws3202(wsIn3202forsearch);
//						if(countforTxdate == 0){
//							responseParm.setRespCode("2992");
//							responseParm.setRespDesc("日期为：  "+ txdate +"  的记录不存在！");
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return responseParm;
//	}
	//接口ws3202实现分页操作
	public Ipage findByPageforws3202(Ipage ipg, AcTraceLog acTraceLog)throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acTraceLogDao.getCountforws3202(acTraceLog) });// 初始化分页类
			acTraceLog.setStartNumAndEndNum (ipg);
			ipg.setResult(acTraceLogDao.getlistforws3202(acTraceLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
			}
			return ipg;
		}
	//获取符合条件的总数量ws3202
	public int getCountfor3202(AcTraceLog acTraceLog) throws ServiceException {
		int count = 0;
		try {
			count = acTraceLogDao.getCountforws3202(acTraceLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	//流程
	public String doSubmit(WkfParm parm,AcTraceLog acTraceLog, String opNo,
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
			appWkfcfg.setAppType("02");//审批类型
			keyID = wkfInterface.getWkfNo(appWkfcfg);//流程标示
			
			instanceId = wkfInterface.startProcess(keyID, parm,acTraceLog.getTraceNo(),opNo);
			//取taskId 任务编号 接口
			Task task = WFUtil.getTaskService().findTaskByProcessInstanceId(instanceId);
			if( task != null ) {
				taskId = task.getId();
				nextDesc = WFUtil.getTaskService().getNextTaskDesc(taskId);
				String[] nextRole = WFUtil.getTaskService().getNextTaskExecutor(taskId);//获取下一个流程节点角色
				String RoleNo = nextRole[1];
				SendMessageEntity sendMessageEntity=new SendMessageEntity();//发送消息
				String title="冲账审批";
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
				acTraceLog.setAppSts("02");
				acTraceLog.setProcessId(instanceId);
				acTraceLogDao.update(acTraceLog);
			}
		}catch(WorkflowException e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}catch(Exception e){
			throw new ServiceException(e.getMessage());
		}
		return nextDesc;
	}
	public AcTraceLogDao getAcTraceLogDao() {
		return acTraceLogDao;
	}

	public void setAcTraceLogDao(AcTraceLogDao acTraceLogDao) {
		this.acTraceLogDao = acTraceLogDao;
	}

	public FiterEngineInterface getFilterEngineInterface() {
		return filterEngineInterface;
	}

	public void setFilterEngineInterface(FiterEngineInterface filterEngineInterface) {
		this.filterEngineInterface = filterEngineInterface;
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
	
}