package app.creditapp.approve.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import app.creditapp.acc.chg.bo.AftReliefBo;
import app.creditapp.acc.chg.entity.AftRelief;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.approve.bo.ApproveAftBo;
import app.creditapp.ln.entity.LnApprIdea;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.Result;
import app.creditapp.wkf.entity.WKfTaskTemp;
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

public class ApproveAftAction extends BaseFormBean {

	private static final long serialVersionUID = 134654657454523L;
	private FormData formapprove4004;
	private FormService formService = new FormService();
	private String query;
	private LnApprIdea lnApprIdea;
	private String apprType;
	private String taskIds;
	private String brNo;
	private String pactNo;
	private String batchNo;
	private String appId;
	private String appType;
	private String url;
	private String taskId;
	private String apprDesc;
	private HttpServletRequest	request;

	List<WkfApprovalUser> wkfList;
	private List<Task> wkfTaskList;
	private WkfApprovalUserBo wkfApprovalUserBo;
	
	private FormData formaftrel0003;
	private AftRelief aftRelief;
	private AftReliefBo aftReliefBo;
	private ApproveAftBo approveAftBo;
	
	private List tabList;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ApproveAftAction() {
		query = "";
	}
	public String findByPage() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		request= ServletActionContext.getRequest();
		formaftrel0003 = formService.getFormData("aftrel0003");
		aftRelief = new AftRelief();
		getFormValue(formaftrel0003);
		setObjValue(formaftrel0003, aftRelief);
		String userId = User.getLoginid(getHttpRequest());
		String branchId = User.getWfOrgNo(getHttpRequest());
		aftRelief.setUserId(userId);
		aftRelief.setBranchId(branchId);
		Ipage ipage = this.getIpage();
//		WkfApprovalUser wkfUser = new WkfApprovalUser();
//		wkfUser.setWkfUserName(userId);
//		wkfUser.setWkfBrNo(branchId);
//		TaskQuery query =null;
//		wkfList = wkfApprovalUserBo.getByIdAndBrNo(wkfUser);
//		query = WFUtil.getTaskService().createCustomQuery().candidate(userId, branchId); //工作流
//		query.appType(apprType);
//		StringBuilder appValue_parm = new StringBuilder();
//		
//		if(null != aftRelief.getPactNo() && !"".equals(aftRelief.getPactNo())){
//			appValue_parm.append(aftRelief.getPactNo().trim());
//		}
//		if(null != aftRelief.getBrName() && !"".equals(aftRelief.getBrName())){
//			appValue_parm.append(aftRelief.getBrName().trim());
//		}
//		if(null != aftRelief.getCifName() && !"".equals(aftRelief.getCifName())){
//			appValue_parm.append(aftRelief.getCifName().trim());
//		}
//		if( appValue_parm.length() > 0 ) {
//			query.appValue(appValue_parm.toString());
//		}
//		
////		ipage.initPageCounts(new Integer[] { (int) query.count() });
//		int firstResult = (ipage.getPageNo() - 1) * 1000;
//		query.approveByLast(false);
//		wkfTaskList = query.page(firstResult, 1000).orderDesc(TaskQuery.PROPERTY_DB_CREATEDATE).list();
//		List aftReliefList = new ArrayList<AftRelief>();
//		int count = 0;
//		for (int i = 0; i<wkfTaskList.size(); i++){
//			aftRelief = new AftRelief();
//			aftRelief.setRefId(wkfTaskList.get(i).getAppId());//获取审批表里的appid
//			aftRelief = aftReliefBo.getById(aftRelief);	//通过appid去黑名单表里得到对象
//			
//			if(aftRelief!=null){
//				aftRelief.setTaskId(wkfTaskList.get(i).getId());//把审批表里的id号赋给对象
//				aftRelief.setUrl(wkfTaskList.get(i).getExecutionId());//把审批表里的ExecutionId赋给对象，提交时会用到这个参数
//				aftReliefList.add(aftRelief);
//				count += 1;
//			}
//		}
//		ipage.initPageCounts(new Integer[]{count});
		
		List aftReliefList = new ArrayList<AftRelief>();
		
		aftReliefList = (List) aftReliefBo.findByPageForTask(ipage, aftRelief).getResult();
		request.setAttribute("aftReliefList",aftReliefList);
		return "list";
	}
	public String input() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formapprove4004 = formService.getFormData("approve4004");
		lnApprIdea = new LnApprIdea();
		lnApprIdea.setApprOp(User.getLoginid(this.getHttpRequest()));
		lnApprIdea.setApprTime(User.getSys_date(this.getHttpRequest()));	
		lnApprIdea.setTaskId(taskId);
		lnApprIdea.setUrl(url);
		lnApprIdea.setAppId(appId);
		aftRelief = new AftRelief();
		aftRelief.setRefId(appId);
		aftRelief=aftReliefBo.getById(aftRelief);
		lnApprIdea.setLoAmt(aftRelief.getLoAmt());
		lnApprIdea.setLoIntst(aftRelief.getLoIntst());
		lnApprIdea.setCifNo(aftRelief.getCifNo());
		lnApprIdea.setCifName(aftRelief.getCifName());
		lnApprIdea.setRefIntst(aftRelief.getRefIntst());
		lnApprIdea.setRefAmt(aftRelief.getRefAmt());
		lnApprIdea.setPactNo(aftRelief.getPactNo());
		lnApprIdea.setBrNo(aftRelief.getBrNo());
		lnApprIdea.setBrName(aftRelief.getBrName());
//		lnPactList = new ArrayList<LnPact>();
//		for(int i = 0; i<lnPactList.size();i ++){
//			lnApprIdea.setTaskId(lnPactList.get(i).getTaskId());
//		}
		getObjValue(formapprove4004, lnApprIdea);
//		request.setAttribute("lnApprIdea",lnApprIdea);
		return "success";
	}

	public String doSubmit() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formapprove4004 = formService.getFormData("approve4004");
		getFormValue(formapprove4004);
		lnApprIdea = new LnApprIdea();
		aftRelief = new AftRelief();
		setObjValue(formapprove4004, lnApprIdea);
		lnApprIdea.setApprType("02");
		lnApprIdea.setTaskId(lnApprIdea.getTaskId());
		Result result = null;
		try{
			Task task = WFUtil.getTaskService().getTask(taskId);
			String appId= task.getAppId();
			aftRelief.setRefId(appId);
			aftRelief = aftReliefBo.getById(aftRelief);
			result= approveAftBo.doCommit(taskId,aftRelief.getRefId(),lnApprIdea.getUrl(), User.getLoginid(this.getHttpRequest()),lnApprIdea,
					lnApprIdea.getApprIdea(),lnApprIdea.getApprDesc(),
					"",
					User.getLoginid(this.getHttpRequest()),"",User.getWkfUserRoleNames(getHttpRequest())
					, "");
	}catch(WorkflowException e){
		return "input";
	}catch(Exception e){
		//result = ExceptionHandler.handleException(e, result);
		return "input";
	}
	// 提交完成之后不管审批同意与否之后的节点
	if(aftRelief.getRefId()!=null){
	String nextNode = "";
	Execution execution = WFUtil.getExecutionService()
			.findExecutionByAppId(aftRelief.getRefId());
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
		
			tab = new String[2];
			tab[0] = "还款计划";
			tab[1] = "AcLnRepayPlnAction_findByPage.action?pactNo=" + pactNo+"&query=query";
			tabList.add(tab);
			
			tab = new String[2];
			tab[0] = "欠款信息";
			tab[1] = "AcLnLoAction_findByPage.action?pactNo=" + pactNo+"&query=query";
			tabList.add(tab);
			
			tab = new String[2];
			tab[0] = "费用信息";
			tab[1] = "AcChrgLogAction_findByPageForRead.action?pactNo=" + pactNo;
			tabList.add(tab);
			
			tab = new String[2];
			tab[0] = "审批信息";
			tab[1] = "ApproveAftActionInput.action?appId=" + appId+ "&url="+ url+ "&taskId="+ taskId;
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

	public FormData getFormaftrel0003() {
		return formaftrel0003;
	}

	public void setFormaftrel0003(FormData formaftrel0003) {
		this.formaftrel0003 = formaftrel0003;
	}

	public AftRelief getAftRelief() {
		return aftRelief;
	}

	public void setAftRelief(AftRelief aftRelief) {
		this.aftRelief = aftRelief;
	}

	public AftReliefBo getAftReliefBo() {
		return aftReliefBo;
	}

	public void setAftReliefBo(AftReliefBo aftReliefBo) {
		this.aftReliefBo = aftReliefBo;
	}

	public ApproveAftBo getApproveAftBo() {
		return approveAftBo;
	}

	public void setApproveAftBo(ApproveAftBo approveAftBo) {
		this.approveAftBo = approveAftBo;
	}

	public FormData getFormapprove4004() {
		return formapprove4004;
	}

	public void setFormapprove4004(FormData formapprove4004) {
		this.formapprove4004 = formapprove4004;
	}

	public String getPactNo() {
		return pactNo;
	}

	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}

	public List getTabList() {
		return tabList;
	}

	public void setTabList(List tabList) {
		this.tabList = tabList;
	}

}
