package app.creditapp.approve.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.approve.bo.ApproveBlackBo;
import app.creditapp.cif.bo.CifBlackBo;
import app.creditapp.cif.entity.CifBlack;
import app.creditapp.cred.entity.CifBlackApp;
import app.creditapp.ln.entity.LnApprIdea;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.Result;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.util.User;
import app.util.toolkit.Ipage;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.Execution;
import com.dhcc.workflow.api.TaskQuery;
import com.dhcc.workflow.api.WorkflowException;
import com.dhcc.workflow.api.task.Task;

public class ApproveBlackAction extends BaseFormBean {

	private static final long serialVersionUID = 134654657454523L;
	private FormData formapprove3003;
	private FormService formService = new FormService();
	private String query;
	private LnApprIdea lnApprIdea;
	private String apprType;
	private String taskIds;
	private String brNo;
	private String batchNo;
	private String appId;
	private String cifNo;
	private String appType;
	private String url;
	private String taskId;
	private String apprDesc;
	private String cifName;
	private HttpServletRequest	request;
	private ApproveBlackBo approveBlackBo;

	private FormData formcred0005;
	private CifBlackApp cifBlackApp;
	List<WkfApprovalUser> wkfList;
	private List<Task> wkfTaskList;
	private WkfApprovalUserBo wkfApprovalUserBo;
	private CifBlack cifBlack;
	private CifBlackBo cifBlackBo;
	private List<CifBlack> cifBlackList;
	private List tabList;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ApproveBlackAction() {
		query = "";
	}
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		request= ServletActionContext.getRequest();
		formcred0005 = formService.getFormData("cred0005");
		cifBlack = new CifBlack();
		getFormValue(formcred0005);
		setObjValue(formcred0005, cifBlack);
		String userId = User.getLoginid(getHttpRequest());
		String branchId = User.getWfOrgNo(getHttpRequest());
		cifBlack.setUserId(userId);
		cifBlack.setBranchId(branchId);
		Ipage ipage = this.getIpage();
//		WkfApprovalUser wkfUser = new WkfApprovalUser();
//		wkfUser.setWkfUserName(userId);
//		wkfUser.setWkfBrNo(branchId);
//		TaskQuery query =null;
//		wkfList = wkfApprovalUserBo.getByIdAndBrNo(wkfUser);
//		query = WFUtil.getTaskService().createCustomQuery().candidate(userId, branchId); //工作流
//		query.appType(apprType);
//		
//		StringBuilder appValue_parm = new StringBuilder();
//		
//		if(null != cifBlackApp.getCifNo() && !"".equals(cifBlackApp.getCifNo())){
//			appValue_parm.append(cifBlackApp.getCifNo().trim());
//		}
//		if(null != cifBlackApp.getCifName() && !"".equals(cifBlackApp.getCifName())){
//			appValue_parm.append(cifBlackApp.getCifName().trim());
//		}
//		if(null != cifBlackApp.getIdNo() && !"".equals(cifBlackApp.getIdNo())){
//			appValue_parm.append(cifBlackApp.getIdNo().trim());
//		}
//		if( appValue_parm.length() > 0 ) {
//			query.appValue(appValue_parm.toString());
//		}
//		
//		ipage.initPageCounts(new Integer[] { (int) query.count() });
//		int firstResult = (ipage.getPageNo() - 1) * 1000;
//		query.approveByLast(false);
//		wkfTaskList = query.page(firstResult, 1000).orderDesc(TaskQuery.PROPERTY_DB_CREATEDATE).list();
//		List<CifBlack> cifBlackList = new ArrayList<CifBlack>();
//		for (int i = 0; i<wkfTaskList.size(); i++){
//			cifBlack = new CifBlack();
//			cifBlack.setBlkId(wkfTaskList.get(i).getAppId());//获取审批表里的appid
//			cifBlack = cifBlackBo.getById(cifBlack);	//通过appid去黑名单表里得到对象
//			if(cifBlack!=null){
//			cifBlack.setTaskId(wkfTaskList.get(i).getId());//把审批表里的id号赋给对象
//			cifBlack.setUrl(wkfTaskList.get(i).getExecutionId());//把审批表里的ExecutionId赋给对象，提交时会用到这个参数
//			cifBlackList.add(cifBlack);
//			}
//		}
		
		List cifBlackList = new ArrayList<CifBlack>();
		
		cifBlackList = (List) cifBlackBo.findByPageForTask(ipage, cifBlack).getResult();
		request.setAttribute("cifBlackList",cifBlackList);
		return "list";
	}
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formapprove3003 = formService.getFormData("approve3003");
		lnApprIdea = new LnApprIdea();
		lnApprIdea.setApprOp(User.getLoginid(this.getHttpRequest()));
		lnApprIdea.setApprTime(User.getSys_date(this.getHttpRequest()));	
		lnApprIdea.setTaskId(taskId);
		lnApprIdea.setUrl(url);
		lnApprIdea.setAppId(appId);
		lnApprIdea.setCifNo(cifNo);
		lnApprIdea.setCifName(cifName);
//		lnPactList = new ArrayList<LnPact>();
//		for(int i = 0; i<lnPactList.size();i ++){
//			lnApprIdea.setTaskId(lnPactList.get(i).getTaskId());
//		}
		getObjValue(formapprove3003, lnApprIdea);
//		request.setAttribute("lnApprIdea",lnApprIdea);
		return "success";
	}

	public String doSubmit() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		formapprove3003 = formService.getFormData("approve3003");
		formcred0005 = formService.getFormData("cred0005");
		getFormValue(formapprove3003);
		lnApprIdea = new LnApprIdea();
		cifBlack = new CifBlack();
		setObjValue(formapprove3003, lnApprIdea);
		lnApprIdea.setApprType("02");
		cifBlack.setTaskId(lnApprIdea.getTaskId());
		Result result = null;
		try {
			Task task = WFUtil.getTaskService().getTask(taskId);
			String appId = task.getAppId();
			cifBlack.setBlkId(appId);
			cifBlack = cifBlackBo.getById(cifBlack);
			result = approveBlackBo.doCommit(taskId, cifBlack.getBlkId(),
					lnApprIdea.getUrl(),
					User.getLoginid(this.getHttpRequest()), lnApprIdea,
					lnApprIdea.getApprIdea(), lnApprIdea.getApprDesc(), "",
					User.getLoginid(this.getHttpRequest()), "", User
							.getWkfUserRoleNames(getHttpRequest()), "");
		} catch (WorkflowException e) {
			return "input";
		} catch (Exception e) {
			// result = ExceptionHandler.handleException(e, result);
			return "input";
		}
		// 提交完成之后不管审批同意与否之后的节点
		if(cifBlack.getBlkId()!=null){
		String nextNode = "";
		Execution execution = WFUtil.getExecutionService()
				.findExecutionByAppId(cifBlack.getBlkId());
		if (execution != null) {
			String instanceId = execution.getProcessInstance().getId();
			List<Task> taskList = WFUtil.getTaskService()
					.findTasksByProcessInstanceId(instanceId);
			if (taskList.size() > 0) {
				nextNode = taskList.get(taskList.size() - 1).getDescription();
			}
		} else {
			nextNode = "END";
		}
		if(nextNode.equals("END")){
			this.addActionMessage("审批结束" );
		}else{
			this.addActionMessage("提交成功,下一审批角色" + nextNode);
		}
		}else{
			this.addActionMessage("已提交，不能重复提交" );
		}
//		return findByPage();
		return "list";
	}
	public String getTab() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		tabList = new ArrayList();
		String[] tab = null;
		
		tab = new String[2];
		tab[0] = "客户信息";
		tab[1] = "CifPersInfAction_getById.action?cifNo=" + cifNo+ "&query=query";
		tabList.add(tab);
		
		
		tab = new String[2];
		tab[0] = "合同信息";
		tab[1] = "LnPactAction_listQuotaForCif.action?cifNo=" + cifNo+ "&query=query";
		tabList.add(tab);
		

		tab = new String[2];
			tab[0] = "审批信息";
			tab[1] = "ApproveBlackActionInput.action?appId=" + appId
					+ "&url=" + url + "&taskId=" + taskId + "&query=" + query
					+ "&cifNo="+cifNo+"&cifName="+cifName;
			tabList.add(tab);
		return "tab";
	}
	public LnApprIdea getLnApprIdea() {
		return lnApprIdea;
	}

	public void setLnApprIdea(LnApprIdea lnApprIdea) {
		this.lnApprIdea = lnApprIdea;
	}

	public void setFormapprove3003(FormData formapprove3003) {
		this.formapprove3003 = formapprove3003;
	}

	public FormData getFormapprove3003() {
		return formapprove3003;
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

	public FormData getFormcred0005() {
		return formcred0005;
	}

	public void setFormcred0005(FormData formcred0005) {
		this.formcred0005 = formcred0005;
	}

	public CifBlackApp getCifBlackApp() {
		return cifBlackApp;
	}

	public void setCifBlackApp(CifBlackApp cifBlackApp) {
		this.cifBlackApp = cifBlackApp;
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

	public CifBlack getCifBlack() {
		return cifBlack;
	}

	public void setCifBlack(CifBlack cifBlack) {
		this.cifBlack = cifBlack;
	}

	public CifBlackBo getCifBlackBo() {
		return cifBlackBo;
	}

	public void setCifBlackBo(CifBlackBo cifBlackBo) {
		this.cifBlackBo = cifBlackBo;
	}

	public List<CifBlack> getCifBlackList() {
		return cifBlackList;
	}

	public void setCifBlackList(List<CifBlack> cifBlackList) {
		this.cifBlackList = cifBlackList;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public ApproveBlackBo getApproveBlackBo() {
		return approveBlackBo;
	}

	public void setApproveBlackBo(ApproveBlackBo approveBlackBo) {
		this.approveBlackBo = approveBlackBo;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getCifNo() {
		return cifNo;
	}

	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}

	public List getTabList() {
		return tabList;
	}

	public void setTabList(List tabList) {
		this.tabList = tabList;
	}

	public String getCifName() {
		return cifName;
	}

	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
}
