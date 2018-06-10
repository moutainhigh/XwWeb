package app.creditapp.approve.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.Execution;
import com.dhcc.workflow.api.WorkflowException;
import com.dhcc.workflow.api.task.Task;

import app.creditapp.acc.log.bo.AcTraceLogBo;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.approve.bo.ApproveAcBo;
import app.creditapp.ln.entity.LnApprIdea;
import app.creditapp.wkf.bo.WfTaskBo;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.Result;
import app.creditapp.wkf.entity.WfTask;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.util.User;
import app.util.toolkit.Ipage;

public class ApproveAcAction extends BaseFormBean {

	private static final long serialVersionUID = 134654657454523L;
	private FormData formapprove5005;
	private FormService formService = new FormService();
	private String query;
	private LnApprIdea lnApprIdea;
	private String apprType;
	private String taskIds;
	private String txCde;
	private String brNo;
	private String projNo;
	private String pactNo;
	private String loanNo;
	private String batchNo;
	private String appId;
	private String appType;
	private String url;
	private String traceNo;
	private String taskId;
	private String apprDesc;
	private String formSts;//判断查询form是否显示
	private HttpServletRequest	request;

	List<WkfApprovalUser> wkfList;
	private List<Task> wkfTaskList;
	private WkfApprovalUserBo wkfApprovalUserBo;
	
	private FormData formlog0003;
	private AcTraceLog acTraceLog;
	private AcTraceLogBo acTraceLogBo;
	private ApproveAcBo approveAcBo;
	private WfTaskBo wfTaskBo;
	
	private List tabList;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ApproveAcAction() {
		query = "";
	}
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		request= ServletActionContext.getRequest();
		formlog0003 = formService.getFormData("log0003");
		acTraceLog = new AcTraceLog();
		getFormValue(formlog0003);
		setObjValue(formlog0003, acTraceLog);
		String userId = User.getLoginid(getHttpRequest());
		String branchId = User.getWfOrgNo(getHttpRequest());
		acTraceLog.setUserId(userId);
		acTraceLog.setBranchId(branchId);
		Ipage ipage = this.getIpage();
//		WkfApprovalUser wkfUser = new WkfApprovalUser();
//		wkfUser.setWkfUserName(userId);
//		wkfUser.setWkfBrNo(branchId);
//		TaskQuery query =null;
//		wkfList = wkfApprovalUserBo.getByIdAndBrNo(wkfUser);
//		query = WFUtil.getTaskService().createCustomQuery().candidate(userId, branchId); //工作流
//		query.appType(apprType);//审批类型
//		StringBuilder appValue_parm = new StringBuilder();
//		
//		if(null != acTraceLog.getPactNo() && !"".equals(acTraceLog.getPactNo())){
//			appValue_parm.append(acTraceLog.getPactNo().trim());
//		}
//		if(null != acTraceLog.getBrName() && !"".equals(acTraceLog.getBrName())){
//			appValue_parm.append(acTraceLog.getBrName().trim());
//		}
//		if(null != acTraceLog.getCifName() && !"".equals(acTraceLog.getCifName())){
//			appValue_parm.append(acTraceLog.getCifName().trim());
//		}
//		if( appValue_parm.length() > 0 ) {
//			query.appValue(appValue_parm.toString());
//		}
//		
//		ipage.initPageCounts(new Integer[] { (int) query.count() });
//		int firstResult = (ipage.getPageNo() - 1) * 1000;
//		query.approveByLast(false);
//		wkfTaskList = query.page(firstResult, 1000).orderDesc(TaskQuery.PROPERTY_DB_CREATEDATE).list();
//		List acTraceLogList = new ArrayList<AcTraceLog>();
//		for (int i = 0; i<wkfTaskList.size(); i++){
//			acTraceLog = new AcTraceLog();
//			acTraceLog.setTraceNo(wkfTaskList.get(i).getAppId());//获取审批表里的appid
//			acTraceLog.setTraceCnt(1);//冲账查询需要TRACE_CNT 赋值1
//			acTraceLog = acTraceLogBo.getById(acTraceLog);	//通过appid去黑名单表里得到对象
//			
//			if(acTraceLog!=null){
//				acTraceLog.setTaskId(wkfTaskList.get(i).getId());//把审批表里的id号赋给对象
//				acTraceLog.setUrl(wkfTaskList.get(i).getExecutionId());//把审批表里的ExecutionId赋给对象，提交时会用到这个参数
//				acTraceLogList.add(acTraceLog);
//			}
//		}
		List acTraceLogList = new ArrayList<AcTraceLog>();
		
		acTraceLogList = (List) acTraceLogBo.findByPageForTask(ipage, acTraceLog).getResult();

//		Collections.sort(acTraceLogList, new CompList());
		request.setAttribute("acTraceLogList",acTraceLogList);
		return "list";
	}
	class CompList implements Comparator<AcTraceLog>{
		@Override
		public int compare(AcTraceLog o1, AcTraceLog o2) {
			return o1.getTraceNo().compareTo(o2.getTraceNo());
		}
	}
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formapprove5005 = formService.getFormData("approve5005");
		lnApprIdea = new LnApprIdea();
		lnApprIdea.setApprOp(User.getLoginid(this.getHttpRequest()));
		lnApprIdea.setApprTime(User.getSys_date(this.getHttpRequest()));	
		lnApprIdea.setTaskId(taskId);
		lnApprIdea.setUrl(url);
		lnApprIdea.setAppId(appId);
		acTraceLog = new AcTraceLog();
		acTraceLog.setTraceNo(appId);
		acTraceLog.setTraceCnt(1);
		acTraceLog=acTraceLogBo.getById(acTraceLog);
		lnApprIdea.setTxCde(acTraceLog.getTxCde());
		lnApprIdea.setTxDt(acTraceLog.getTxDt());
		lnApprIdea.setTxTime(acTraceLog.getTxTime());
		lnApprIdea.setPactNo(acTraceLog.getPactNo());
		lnApprIdea.setBrNo(acTraceLog.getBrNo());
		lnApprIdea.setBrName(acTraceLog.getBrName());
		getObjValue(formapprove5005, lnApprIdea);
		return "success";
	}

	public String doSubmit() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formapprove5005 = formService.getFormData("approve5005");
		getFormValue(formapprove5005);
		lnApprIdea = new LnApprIdea();
		acTraceLog = new AcTraceLog();
		setObjValue(formapprove5005, lnApprIdea);
		lnApprIdea.setApprType("02");
		lnApprIdea.setTaskId(lnApprIdea.getTaskId());
		Result result = null;
		try {
			WfTask wfTask = new WfTask();
			wfTask.setTaskId(taskId);
			wfTask = wfTaskBo.findTaskByTaskId(wfTask);
//			Task task = WFUtil.getTaskService().getTask(taskId);
			String appId = wfTask.getTaskAppId();
			acTraceLog.setTraceNo(appId);
			acTraceLog.setTraceCnt(1);
			acTraceLog = acTraceLogBo.getById(acTraceLog);
			result = approveAcBo.doCommit(taskId, acTraceLog.getTraceNo(), lnApprIdea.getUrl(),
					User.getLoginid(this.getHttpRequest()), lnApprIdea, lnApprIdea.getApprIdea(),
					lnApprIdea.getApprDesc(), "", User.getLoginid(this.getHttpRequest()), "",
					User.getWkfUserRoleNames(getHttpRequest()), "");
		} catch (WorkflowException e) {
			return "input";
		} catch (Exception e) {
			// result = ExceptionHandler.handleException(e, result);
			return "input";
		}
		// 提交完成之后不管审批同意与否之后的节点
		if (acTraceLog.getTraceNo() != null) {
			String nextNode = "";
			Execution execution = WFUtil.getExecutionService().findExecutionByAppId(acTraceLog.getTraceNo());
			if (execution != null) {
				String instanceId = execution.getProcessInstance().getId();
				List<Task> taskList = WFUtil.getTaskService().findTasksByProcessInstanceId(instanceId);
				if (taskList.size() > 0) {
					nextNode = taskList.get(taskList.size() - 1).getDescription();
				}
			} else {
				nextNode = "END";
			}
			if (nextNode.equals("END")) {
				this.addActionMessage("审批结束");
			} else {
				this.addActionMessage("提交成功,下一审批角色" + nextNode);
			}
		} else {
			this.addActionMessage("已提交，不能重复提交");
		}
		return "list";
	}
	
	public String getTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		
			tab = new String[2];
			tab[0] = "合同信息";
			tab[1] = "LnPactAction_getById.action?pactNo=" + pactNo+"&query=query&formSts=1";
			tabList.add(tab);
		
		
		if(!txCde.equals("LNUP")){
			tab = new String[2];
			tab[0] = "还款计划";
			tab[1] = "AcLnRepayPlnAction_findByPage.action?pactNo=" + pactNo;
			tabList.add(tab);
		}

		if(txCde.equals("LNRV")){
			tab = new String[2];
			tab[0] = "欠款信息";
			tab[1] = "AcLnLoAction_findByPage.action?pactNo=" + pactNo;
			tabList.add(tab);
			tab = new String[2];
			tab[0] = "费用信息";
			tab[1] = "AcChrgLogAction_findByPageForRead.action?pactNo=" + pactNo+ "&formSts=1";
			tabList.add(tab);
			tab = new String[2];
			tab[0] = "减免信息";
			tab[1] = "AftReliefAction_findByPage.action?pactNo=" + pactNo;
			tabList.add(tab);
		}
		if(txCde.equals("LNUP")){
			tab = new String[2];
			tab[0] = "账号信息";
			tab[1] = "LnAcctAction_listQuotaForLn.action?appId=" + appId+ "&formSts=1";
			tabList.add(tab);
			tab = new String[2];
			tab[0] = "账号变更信息";
			tab[1] = "AftAcnoAction_findByPage.action?pactNo=" + pactNo+ "&formSts=1";
			tabList.add(tab);
			}
		if(txCde.equals("CGPD")){
			tab = new String[2];
			tab[0] = "还款日变更信息";
			tab[1] = "AftAcnoAction_getByIdForTrace.action?traceNo=" + appId+ "&query=query";
			tabList.add(tab);
		}
		if(txCde.equals("LNAD")||txCde.equals("LNC4")){
			tab = new String[2];
			tab[0] = "还款明细";
			tab[1] = "AcLnPmLogAction_getById.action?traceNo=" + appId+ "&query=query";
			tabList.add(tab);
		}
		if(txCde.equals("LNAD")){
			tab = new String[2];
			tab[0] = "提前还款明细";
			tab[1] = "AftAdvpayAction_getById.action?pactNo=" + pactNo+ "&query=query";
			tabList.add(tab);
		}
			tab = new String[2];
			tab[0] = "审批信息";
			tab[1] = "ApproveAcActionInput.action?appId=" + appId+ "&url="+ url+ "&taskId="+ taskId;
			tabList.add(tab);
		
		
		return "tab";
	}
	public LnApprIdea getLnApprIdea() {
		return lnApprIdea;
	}

	public void setLnApprIdea(LnApprIdea lnApprIdea) {
		this.lnApprIdea = lnApprIdea;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getApprType() {
		return apprType;
	}

	public void setApprType(String apprType) {
		this.apprType = apprType;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getApprDesc() {
		return apprDesc;
	}

	public void setApprDesc(String apprDesc) {
		this.apprDesc = apprDesc;
	}

	public List<WkfApprovalUser> getWkfList() {
		return wkfList;
	}

	public void setWkfList(List<WkfApprovalUser> wkfList) {
		this.wkfList = wkfList;
	}

	public List<Task> getWkfTaskList() {
		return wkfTaskList;
	}

	public void setWkfTaskList(List<Task> wkfTaskList) {
		this.wkfTaskList = wkfTaskList;
	}

	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}

	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getPactNo() {
		return pactNo;
	}

	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}

	public FormData getFormlog0003() {
		return formlog0003;
	}

	public void setFormlog0003(FormData formlog0003) {
		this.formlog0003 = formlog0003;
	}

	public FormData getFormapprove5005() {
		return formapprove5005;
	}

	public void setFormapprove5005(FormData formapprove5005) {
		this.formapprove5005 = formapprove5005;
	}

	public AcTraceLog getAcTraceLog() {
		return acTraceLog;
	}

	public void setAcTraceLog(AcTraceLog acTraceLog) {
		this.acTraceLog = acTraceLog;
	}

	public AcTraceLogBo getAcTraceLogBo() {
		return acTraceLogBo;
	}

	public void setAcTraceLogBo(AcTraceLogBo acTraceLogBo) {
		this.acTraceLogBo = acTraceLogBo;
	}

	public ApproveAcBo getApproveAcBo() {
		return approveAcBo;
	}

	public void setApproveAcBo(ApproveAcBo approveAcBo) {
		this.approveAcBo = approveAcBo;
	}

	public List getTabList() {
		return tabList;
	}

	public void setTabList(List tabList) {
		this.tabList = tabList;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getTxCde() {
		return txCde;
	}

	public void setTxCde(String txCde) {
		this.txCde = txCde;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getFormSts() {
		return formSts;
	}

	public void setFormSts(String formSts) {
		this.formSts = formSts;
	}

	public WfTaskBo getWfTaskBo() {
		return wfTaskBo;
	}

	public void setWfTaskBo(WfTaskBo wfTaskBo) {
		this.wfTaskBo = wfTaskBo;
	}

}
