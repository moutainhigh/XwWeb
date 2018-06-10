package app.creditapp.wkf.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.core.domain.screen.FormData;
import com.core.service.screen.FormService;
import com.core.struts.ActionContext;
import com.core.struts.BaseFormBean;
import com.dhcc.workflow.WF;
import com.dhcc.workflow.WFUtil;
import com.dhcc.workflow.api.TaskQuery;
import com.dhcc.workflow.api.history.HistoryProcessInstance;
import com.dhcc.workflow.api.history.HistoryTask;
import com.dhcc.workflow.api.history.HistoryTaskQuery;
import com.dhcc.workflow.api.model.Transition;
import com.dhcc.workflow.api.task.Task;
import com.dhcc.workflow.pvm.internal.history.model.HistoryTaskImpl;
import com.dhcc.workflow.pvm.internal.task.TaskConstants;
import com.dhcc.workflow.pvm.internal.util.StringUtil;

import app.creditapp.pact.bo.LnPactBo;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.wkf.AppConstant;
import app.creditapp.wkf.bo.WfTaskBo;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.entity.WKfTaskTemp;
import app.creditapp.wkf.entity.WfTask;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.util.User;
import app.util.toolkit.Ipage;

public class TaskAction extends BaseFormBean {
	
	private static final long serialVersionUID = 8659398196134479613L;
	
	private List<Task> taskList;
	private List<LnPact> lnPactList;
	private LnPact lnPact;
	private LnPactBo lnPactBo;
	private WfTaskBo wfTaskBo;
	private List<HistoryTask> historyTaskList;
	private List<WKfTaskTemp> wkfTaskTempList = new ArrayList<WKfTaskTemp>();
	private FormData formwkf0015;
	private FormData formwkf0021;
	private FormData formwkft1001;
	private String query;
	private String taskName;
	private String assignee;
	private String formURL;
	private String taskId;
	private String taskIds;//    审批按钮 拼接所有任务编号
	private String processIds;//    
	private String abc;//    
	private String executionId;
	private String userId;
	private String isBatchFlag;
	private String appId;
	private String cifNo;
	private String cifName;
	private String appType;
	private String wkfRole;
	private String endDate;
	private String begDate;
	private String hisDate;
	private String appSts;
	private String returnflag;
	private FormService formService = new FormService();
	
	private int tpageNo;			//所在页数
	private int tpageSize;		//每页数据条数
	private String isEditReturn;//返回标志
	private String tappNo;
	private String tcifNo;
	private String tcifName;

	private WkfApprovalUserBo wkfApprovalUserBo; 

	private String batchNo;
	private String projNo;
	private String lnType;
	private String brNo;
	private String apprType;
	private String url;
	private String appIds;
	public TaskAction() {
		query = "";
		isEditReturn = "0";
		taskIds = "";

	}

	

	public String findByPage() throws Exception {
		formwkf0015 = formService.getFormData("wkf0015");
		
		/**page start**/
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		
		TaskQuery query = WFUtil.getTaskService().createQuery();
		if( !(StringUtil.isEmpty(taskName)) ) {
			query = query.activityName(taskName);
		}
		
		if( !(StringUtil.isEmpty(assignee)) ) { 
			query = query.assignee(assignee);
		}
		
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		taskList= query.page(firstResult, ipage.getPageSize()).orderAsc(TaskQuery.PROPERTY_CREATEDATE).list();
		ipage.setResult(taskList);
		String userId = User.getRoleno(getHttpRequest());
		String branchId = User.getBrno(getHttpRequest());
		getHttpRequest().setAttribute("userId", userId);
		getHttpRequest().setAttribute("branchId", branchId);

		return WF.PARAM_TASK_LIST;
	}
	
	public String rollback() throws Exception {
		String rollbackTo = getHttpRequest().getParameter(WF.PARAM_TASK_TRANSITION);
		WFUtil.getTaskService().rollback(taskId, "退回",rollbackTo);
		this.addActionMessage("操作成功！");
		return listMyTask();
	}

	public String choosePerson()throws Exception {
		HttpServletRequest request = getHttpRequest();
		String op_no = User.getRoleno(getHttpRequest());
		//WFUtil.getTaskService().takeComplete(taskId, null, null, null, null, op_no);
		//WFUtil.getTaskService().take(taskId, op_no);
		String transition = request.getParameter(WF.PARAM_TASK_TRANSITION);
		//WkfApprovalUserAction_findApproveUserByPage.action?wkfRoleNo=203&wkfBrNo=01021
		WFUtil.getTaskService().complete(taskId, TaskConstants.PASS, "同意", transition, op_no);
		return null;
	}
	
	public String recall() throws Exception {
		boolean result = false;
		String op_no = User.getRoleno(getHttpRequest());
		try {
			result = WFUtil.getTaskService().recall(taskId,op_no);
			if(result){
				this.addActionMessage("召回成功！");
			}else{
				this.addActionError("召回失败!");
			}
		} catch(Exception e) {
			e.printStackTrace();
			String errorMsg = e.getMessage();
			if( errorMsg.length() < 30) {
			this.addActionError(this.getText(errorMsg));
			}
		}
		
		return mySendedTask();
	}

	public String myProxyTask() throws Exception  {
		String userId = User.getRoleno(getHttpRequest());
		String branchId = User.getBrno(getHttpRequest());
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		
		TaskQuery query = WFUtil.getTaskService().createProxyTaskQuery().candidate(userId, branchId);
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		taskList = query.page(firstResult, ipage.getPageSize()).orderAsc(TaskQuery.PROPERTY_CREATEDATE).list();
		ipage.setResult(taskList);

		return WF.PARAM_MYPROXY_TASK;
	}

	public String reAssign() throws Exception 
	{
		String userId = getHttpRequest().getParameter(WF.PARAM_TASKQUERY_USERID);
		WFUtil.getTaskService().assign(taskId, userId);
		this.addActionMessage("操作成功！");
		return findByPage();
	}
	
	public String selectTransition() throws Exception {
		List<Transition> list = WFUtil.getTaskService().getTransitions(taskId);
		getHttpRequest().setAttribute(WF.PARAM_LIST_NAME, list);
		
		return WF.PARAM_SELECT_TRANSITION;
	}

	public String selectBackTransition() throws Exception {
		HttpServletRequest request = getHttpRequest();
		List<Transition> list = WFUtil.getTaskService().getBackTransitions(taskId);
		request.setAttribute(WF.PARAM_LIST_NAME, list);
		
		return WF.PARAM_SELECT_BACKTRANSITION;
	}	
	
	public String refuseTask() throws Exception {
		String userid = User.getRoleno(getHttpRequest());
		WFUtil.getTaskService().refuse(taskId, "否决", userid);
		this.addActionMessage("操作成功！");
		return findByPage();
	}
	
	public String refuse() throws Exception {
		String userid = User.getRoleno(getHttpRequest());
		WFUtil.getTaskService().refuse(taskId, "否决", userid);
		this.addActionMessage("操作成功！");
		return listMyTask();
	}

	public String mySendedTask() throws Exception  {
		String userId = User.getRoleno(getHttpRequest());
		/**page start**/
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		
		TaskQuery query = WFUtil.getTaskService().createQuery().sendedUser(userId);
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		taskList = query.page(firstResult, ipage.getPageSize()).orderAsc(TaskQuery.PROPERTY_CREATEDATE).list();
		ipage.setResult(taskList);
		/**page end**/

		return WF.PARAM_MYSENDED_TASK;
	}
	
	/**
	 * 已报备的贷款
	 * 
	 * @return
	 * @throws Exception
	 */
	public String myBaobeiTask() throws Exception{
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formwkft1001 = formService.getFormData("wkft1001");
//		String userId = User.getRoleno(getHttpRequest());
//		String branchId = User.getBrno(getHttpRequest());
		String state="";
		getFormValue(formwkft1001);
		WKfTaskTemp wKfTask = new WKfTaskTemp();
		setObjValue(formwkft1001,wKfTask);
		/**page start**/
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		HistoryTaskQuery query = WFUtil.getHistoryService().createHistoryTaskQuery().appType("8").groupByAppId();
		if(wKfTask.getBegDate()!=null&&wKfTask.getEndDate()!=null){
			query.ended(wKfTask.getBegDate(), wKfTask.getEndDate());
		}
		if(wKfTask.getAppType()!=null&&!"".equals(wKfTask.getAppType())){
			query.appType(wKfTask.getAppType());
		}
		if(wKfTask.getCif_no()!=null&&!"".equals(wKfTask.getCif_no())){
			query.appValue(wKfTask.getCif_no());
		}
		if(wKfTask.getCif_name()!=null&&!"".equals(wKfTask.getCif_name())){
			query.appValue(wKfTask.getCif_name());
		}
		
		appSts = wKfTask.getAppSts();
		if(appSts!=null&&!appSts.equals("")){
			if("1".equals(appSts)){
				state="active";
				query.state(state);
			}else if("4".equals(appSts)){
				state="completed";
				query.state(state);
			}else if("3".equals(appSts)){
				state="refused";
				query.state(state);
			}else if("2".equals(appSts)){
				state="sendbacked";
				query.state(state);
			}
		}
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		historyTaskList = query.page(firstResult, ipage.getPageSize()).orderAsc(HistoryTaskQuery.PROPERTY_DB_CREATETIME).list();
		WKfTaskTemp wKfTaskTemp = null;
		for(int i=0;i<historyTaskList.size();i++){
			wKfTaskTemp= new WKfTaskTemp();
			HistoryTaskImpl histtask = (HistoryTaskImpl)historyTaskList.get(i);
			wKfTaskTemp.setAppIdea(histtask.getApproveIdea());//审批意见
			wKfTaskTemp.setAppValue(histtask.getAppValue());//业务数据
			wKfTaskTemp.setAppValueScript(histtask.getAppValueScript());//业务数据浮动
			wKfTaskTemp.setWkfRole(histtask.getDescription());//审批角色
			wKfTaskTemp.setAppType(histtask.getAppType());//审批类型
			wKfTaskTemp.setAppId(histtask.getAppId());//申请号
			wKfTaskTemp.setCif_no(histtask.getAppValue().split(":")[1].split(",")[0]);
			wKfTaskTemp.setCif_name(histtask.getAppValue().split(":")[2].split(",")[0]);
			wKfTaskTemp.setExecutionId(histtask.getExecutionId());//实例Id
			List<HistoryProcessInstance> hpi = WFUtil.getHistoryService().createHistoryProcessInstanceQuery().processInstanceId(histtask.getExecutionId()).list();
			//wKfTaskTemp.setAppSts(hpi.getState());//审批状态
			if(hpi==null||hpi.size()==0){
				wKfTaskTemp.setAppSts("ended");//审批状态
			}else{
				wKfTaskTemp.setAppSts(hpi.get(0).getState());
			}
			wkfTaskTempList.add(wKfTaskTemp);
		}
//			ipage.setResult(wkfTaskTempList);
//			String dateOptions="";
//			dateOptions = getDateOptions();
//			this.changeFormProperty(formwkft1001, "hisDate", "para", dateOptions);
			getObjValue(formwkft1001,wKfTask);

			return WF.PARAM_MYCOMMITED_TASK;
	}
	
	
	
	
	/**
	 * 已办理任务列表查询
	 * @return
	 * @throws Exception
	 */
	public String myCommitedTask() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(), ServletActionContext.getResponse());
		formwkft1001 = formService.getFormData("wkft1001");
		String userId = User.getLoginid(getHttpRequest());
//		String branchId = User.getBrno(getHttpRequest());
		String state="";
		getFormValue(formwkft1001);
		WKfTaskTemp wKfTask = new WKfTaskTemp();
		setObjValue(formwkft1001,wKfTask);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		/**page start**/
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		
		HistoryTaskQuery query = WFUtil.getHistoryService().createHistoryTaskQuery().assignee(userId).commited().groupByAppId();
		if(wKfTask.getBegDate()!=null&&wKfTask.getEndDate()!=null){
			query.ended(wKfTask.getBegDate(), wKfTask.getEndDate());
		}
		if(wKfTask.getAppType()!=null&&!"".equals(wKfTask.getAppType())){
			query.appType(wKfTask.getAppType());
		}
		if(wKfTask.getCif_no()!=null&&!"".equals(wKfTask.getCif_no())){
			query.appValue(wKfTask.getCif_no());
		}
		if(wKfTask.getCif_name()!=null&&!"".equals(wKfTask.getCif_name())){
			query.appValue(wKfTask.getCif_name());
		}
		
		appSts = wKfTask.getAppSts();
		if(appSts!=null&&!appSts.equals("")){
			if("1".equals(appSts)){
				state="active";
				query.state(state);
			}else if("4".equals(appSts)){
				state="completed";
				query.state(state);
			}else if("3".equals(appSts)){
				state="refused";
				query.state(state);
			}else if("2".equals(appSts)){
				state="sendbacked";
				query.state(state);
			}
		}
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		historyTaskList = query.page(firstResult, ipage.getPageSize()).orderAsc(HistoryTaskQuery.PROPERTY_DB_CREATETIME).list();
		WKfTaskTemp wKfTaskTemp = null;
		for(int i=0;i<historyTaskList.size();i++){
			wKfTaskTemp= new WKfTaskTemp();
			HistoryTaskImpl histtask = (HistoryTaskImpl)historyTaskList.get(i);
			wKfTaskTemp.setAppIdea(histtask.getApproveIdea().split("#")[0]);//审批意见
			wKfTaskTemp.setAppValue(histtask.getAppValue());//业务数据
			wKfTaskTemp.setAppValueScript(histtask.getAppValueScript());//业务数据浮动
			wKfTaskTemp.setWkfRole(histtask.getDescription());//审批角色
			wKfTaskTemp.setAppType(histtask.getAppType());//审批类型
			wKfTaskTemp.setAppId(histtask.getAppId());//申请号
			wKfTaskTemp.setEndDate(format.format(histtask.getEndTime()));
			wKfTaskTemp.setCif_no(histtask.getAppValue().split(":")[1].split(",")[0]);
			wKfTaskTemp.setCif_name(histtask.getAppValue().split(":")[2].split(",")[0]);
			wKfTaskTemp.setExecutionId(histtask.getExecutionId());//实例Id
			List<HistoryProcessInstance> hpi = WFUtil.getHistoryService().createHistoryProcessInstanceQuery().processInstanceId(histtask.getExecutionId()).list();
			//wKfTaskTemp.setAppSts(hpi.getState());//审批状态
			if(hpi==null||hpi.size()==0){
				wKfTaskTemp.setAppSts("ended");//审批状态
			}else{
				wKfTaskTemp.setAppSts(hpi.get(0).getState());
			}
			wkfTaskTempList.add(wKfTaskTemp);
		}
		ipage.setResult(wkfTaskTempList);
		getObjValue(formwkft1001,wKfTask);
		return WF.PARAM_MYCOMMITED_TASK;
	}
//	/**
//	 * 已办理任务历史日期动态赋值
//	 * @return
//	 */
//	public String getDateOptions(){
//		String options="1-近三个月|2-近六个月|3-近一年|4-历史查询|";
//		return options;
//	}
////	/**
//	 * 已办理任务审批类型动态赋值
//	 * @return
//
//	public String getAppTypeOptions(){
//		String options="-|1-贷款审批|2-评级审批|3-授信审批|4-风险分类审批|";
//		return options;
//	}
	/**
	 * 已办理任务审批角色动态赋值
	 * @param userId
	 * @param branchId
	 * @return
	 */
	public String getAppRoleOptions(String userId,String branchId){
		String options = "";
		WkfApprovalUser wkfApprovalUser = new WkfApprovalUser();
		wkfApprovalUser.setWkfUserName(userId);
		wkfApprovalUser.setWkfBrNo(branchId);
		List<WkfApprovalUser> wkfUserList = wkfApprovalUserBo.getByIdAndBrNo(wkfApprovalUser);
		for(int i=0;i<wkfUserList.size();i++){
			options +=wkfUserList.get(i).getWkfRoleNo()+"-"+wkfUserList.get(i).getWkfRoleName()+"|";
		}
		return "-请选择|"+options;
	}
	/**
	 * 已办任务条件查询
	 * @return
	 * @throws Exception
	
	public String findTaskByCifNo() throws Exception{
		String pageNo = this.getEadis_page();
		String userId = User.getTlrno(getHttpRequest());
		String branchId = User.getBrno(getHttpRequest());
		String sysDate="";
		String threeMon="";
		String sixMon="";
		String year ="";
		String state="";
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		//对于历史日期的判断1-近三个月查询2-近六个月3-近一年4-历史查询
		if(hisDate!=null&&hisDate!=""){
			sysDate = User.getSys_date(this.getHttpRequest());
			threeMon = (Integer.parseInt(sysDate.substring(0,6))-3)+""+sysDate.substring(6,8);
		    sixMon = (Integer.parseInt(sysDate.substring(0,6))-6)+""+sysDate.substring(6,8);
			year = (Integer.parseInt(sysDate.substring(0,6))-100)+""+sysDate.substring(6,8);
		}
		//选择历史查询之后起始日期和结束日期的获取转换成20121212格式
		if(begDate!=null&&!begDate.equals("")&&endDate!=null&&!endDate.equals("")){
			begDate = begDate.split("-")[0]+begDate.split("-")[1]+begDate.split("-")[2];
			endDate = endDate.split("-")[0]+endDate.split("-")[1]+endDate.split("-")[2];
		}
		
		/******** 已办任务条件查询条件项：1、客户基本信息2、审批类型3、审批角色4、历史日期(起始日期、结束日期)*****
		HistoryTaskQuery query =null;
		formwkft1001 = formService.getFormData("wkft1001");
		//各个条件项的增加
		if(cifNo!=null&&!cifNo.equals("")||begDate!=null&&!begDate.equals("")||endDate!=null&&!endDate.equals("")||hisDate!=null&&!hisDate.equals("")||appSts!=null&&!appSts.equals("")){
			String cifVal="客户号:"+cifNo;
			query= WFUtil.getHistoryService().createHistoryTaskQuery().assignee(userId).commited().groupByAppId().appValue(cifVal);
			if(appType!=null&&!"".equals(appType)){
				query.appType(appType);
			}
			if(hisDate!=null&&hisDate!=""){
				sysDate = User.getSys_date(this.getHttpRequest());
				threeMon = (Integer.parseInt(sysDate.substring(0,6))-3)+""+sysDate.substring(6,8);
			    sixMon = (Integer.parseInt(sysDate.substring(0,6))-6)+""+sysDate.substring(6,8);
				year = (Integer.parseInt(sysDate.substring(0,6))-100)+""+sysDate.substring(6,8);
				if(hisDate.equals("1")){
					// 日期区间查询有点问题  系统日期不是当前时间故而查询有点问题
					//query.ended(threeMon, sysDate);
				}else if(hisDate.equals("2")){
					//query.ended(sixMon, sysDate);
				}else if(hisDate.equals("3")){
					//query.ended(year, sysDate);
				}
			}
			if(appSts!=null&&!appSts.equals("")){
				if("1".equals(appSts)){
					state="active";
					query.state(state);
				}else if("2".equals(appSts)){
					state="completed";
					query.state(state);
				}else if("3".equals(appSts)){
					state="refused";
					query.state(state);
				}else if("4".equals(appSts)){
					state="sendbacked";
					query.state(state);
				}
			}
		}else{
			query = WFUtil.getHistoryService().createHistoryTaskQuery().appType(appType).assignee(userId).commited().groupByAppId();
		}
		/**列表的分页显示，新建实体类WKfTaskTemp用来重新封装HistoryTask，目的为了获取一些原本HistoryTask类中没有的属性
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		historyTaskList = query.page(firstResult, ipage.getPageSize()).orderAsc(HistoryTaskQuery.PROPERTY_DB_CREATETIME).list();
		for(int i=0;i<historyTaskList.size();i++){
			WKfTaskTemp wKfTaskTemp = new WKfTaskTemp();
			HistoryTaskImpl histtask = (HistoryTaskImpl)historyTaskList.get(i);
			wKfTaskTemp.setAppIdea(histtask.getApproveIdea().split("#")[0]);//审批意见
			
			wKfTaskTemp.setAppValue(histtask.getAppValue());//业务数据
			wKfTaskTemp.setAppValueScript(histtask.getAppValueScript());//业务数据浮动
			wKfTaskTemp.setWkfRole(histtask.getDescription());//审批角色
			wKfTaskTemp.setAppType(histtask.getAppType());//审批类型
			wKfTaskTemp.setAppId(histtask.getAppId());//申请号
			wKfTaskTemp.setCif_no(histtask.getAppValue().split(":")[1].split(",")[0]);
			wKfTaskTemp.setCif_name(histtask.getAppValue().split(":")[2].split(",")[0]);
			wKfTaskTemp.setExecutionId(histtask.getExecutionId());//实例Id
			List<HistoryProcessInstance> hpi = WFUtil.getHistoryService().createHistoryProcessInstanceQuery().processInstanceId(histtask.getExecutionId()).list();
			//wKfTaskTemp.setAppSts(hpi.getState());//审批状态
			if(hpi==null||hpi.size()==0){
				wKfTaskTemp.setAppSts("ended");//审批状态
			}else{
				wKfTaskTemp.setAppSts(hpi.get(0).getState());
			}
			wkfTaskTempList.add(wKfTaskTemp);
		}
		
		
		ipage.setResult(wkfTaskTempList);
		
		String options = "0-|1-审批中|2-审批通过|3-审批否决|4-发回重审|";
		//options = getAppRoleOptions(userId,branchId);
		this.changeFormProperty(formwkft1001, "appSts", "para", options);
		
		String dateOptions="";
		dateOptions = getDateOptions();
		this.changeFormProperty(formwkft1001, "hisDate", "para", dateOptions);
		
		String appTypeOptions="";
		appTypeOptions = getAppTypeOptions();
		this.changeFormProperty(formwkft1001, "app_type", "para", appTypeOptions);
		return WF.PARAM_MYCOMMITED_TASK;
	}
	 */
	public String openTaskForm() throws Exception {
		String url = WFUtil.getTaskService().getTaskURL(taskId, formURL);
		try {
			getHttpResponse().sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String openApproveUrl() throws Exception {
//		TaskService service = WFUtil.getTaskService();
		String userId = User.getLoginid(getHttpRequest());
		String branchId = User.getBrno(getHttpRequest());
		Ipage ipage = this.getIpage();
//		TaskQuery query =null;
//		query = WFUtil.getTaskService().createCustomQuery().candidate(userId, branchId); //工作流
//		ipage.initPageCounts(new Integer[] { (int) query.count() });
//		int firstResult = (ipage.getPageNo() - 1) * 10000;
//		query.approveByLast(false);
//		
//		 List<Task> wkfTaskList = query.page(firstResult, 10000).orderDesc(TaskQuery.PROPERTY_DB_CREATEDATE).list();
//		 List lnPactList = new ArrayList<LnPact>();
//			for (int i = 0; i<wkfTaskList.size(); i++){
//				Task task = wkfTaskList.get(i);
//				lnPact = new LnPact();
//				lnPact.setAppId(task.getAppId());//查单笔
//				lnPact.setBrNo(brNo);
//				lnPact.setProjNo(projNo);
//				lnPact.setLnType(lnType);
//				lnPact = lnPactBo.getByIdForAppAndBatch(lnPact);
//				
//				if(lnPact!=null){
//					lnPact.setProcessId(task.getExecutionId());
//					lnPact.setUrl(task.getApproveUrl());
//					//Task task = WFUtil.getTaskService().findTaskByProcessInstanceId(lnPact.getProcessId());
//					String taskId = task.getId();
//					String processId = task.getExecutionId();
//					if(task.getSignState().equals("unsigned")){//判断任务是否签收，未签收 调用签收方法自动签收
//						WFUtil.getTaskService().take(taskId, userId);
//					}
//					taskIds = taskIds + "@" + taskId;
//					processIds = processIds + "@" + processId;
//					lnPact.setTaskId(taskIds);
//					lnPact.setProcessId(processIds);
//					lnPact.setApprType("02");
//					url = task.getApproveUrl();
//					apprType = "02";
//					lnPactList.add(lnPact);
//				}
//			}

		lnPact = new LnPact();
		lnPact.setBrNo(brNo);
		lnPact.setProjNo(projNo);
		lnPact.setLnType(lnType);
		lnPact.setUserId(userId);
		lnPact.setBranchId(branchId);
		ipage.setPageSize(10000);
		List<LnPact> lnPactList = lnPactBo.getWfTaskList(lnPact);
		
		
		for(LnPact lnPact : lnPactList){
//			Task task = WFUtil.getTaskService().findTaskByProcessInstanceId(lnPact.getProcessId());
			String taskId = lnPact.getDbid();
			if(lnPact.getSignState().equals("unsigned")){//判断任务是否签收，未签收 调用签收方法自动签收
				WFUtil.getTaskService().take(taskId, userId);
			}
			appIds = appIds + "@" + lnPact.getAppId();
		}
		
		String targetUrl = null;
		try {
			url = "{context}/ApproveLoanActionInput.action" + "?lnType=" + lnType+"&brNo="+brNo+"&projNo="+projNo+"&appIds="+"";
			targetUrl = url.toString();
			if(targetUrl.startsWith("/cms")){
				targetUrl = targetUrl.replaceAll("/cms", getHttpRequest().getContextPath());
			}else{
				targetUrl = targetUrl.replaceAll("\\{context\\}", getHttpRequest().getContextPath());
			}
			getHttpResponse().sendRedirect(targetUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public String openApproveUrlForList() throws Exception {
	
		String userId = User.getLoginid(getHttpRequest());
		String s =appIds;
		String[] array = s.split("@");
//		List lnPactList = new ArrayList<LnPact>();
		for(int i=1;i<array.length;i++){
			lnPact = new LnPact();
			lnPact.setAppId(array[i]);
			lnPact.setLnType(lnType);
			lnPact.setProjNo(projNo);
			lnPact.setBrNo(brNo);
			lnPact = lnPactBo.getByIdForList(lnPact);
			if(lnPact!=null){
				WfTask wfTask = new WfTask();
				wfTask.setTaskExcutionId(lnPact.getProcessId());
				wfTask = wfTaskBo.findTaskByProcessId(wfTask);
				String taskId = wfTask.getTaskId();
				String processId = wfTask.getTaskExcutionId();
//				if(wfTask.getTaskSignState().equals("unsigned")){//判断任务是否签收，未签收 调用签收方法自动签收
//					WFUtil.getTaskService().take(taskId, userId);
//				}
				taskIds = taskIds + "@" + taskId;
				processIds = processIds + "@" + processId;
				lnPact.setTaskId(taskIds);
				lnPact.setProcessId(processIds);
				lnPact.setApprType("02");
				url = wfTask.getTaskApproveUrl();
				apprType = "02";
//				lnPactList.add(lnPact);
			}
		}
//		String worngUrl="";
		String targetUrl = null;
		try {
			url = "{context}/ApproveLoanActionInput.action" + "?lnType=" + lnType+"&brNo="+brNo+"&projNo="+projNo+"&appIds="+appIds+"&taskIds="+taskIds+"&processIds="+processIds;
			targetUrl = url.toString();
			if(targetUrl.startsWith("/cms")){
				targetUrl = targetUrl.replaceAll("/cms", getHttpRequest().getContextPath());
			}else{
				targetUrl = targetUrl.replaceAll("\\{context\\}", getHttpRequest().getContextPath());
			}
			getHttpResponse().sendRedirect(targetUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	public String openApproveUrlForListt() throws Exception {
//		TaskService service = WFUtil.getTaskService();
		String userId = User.getLoginid(getHttpRequest());
//		String branchId = User.getBrno(getHttpRequest());
//		String branchId = "00000";
//		Ipage ipage = this.getIpage();
//		TaskQuery query =null;
//		query = WFUtil.getTaskService().createCustomQuery().candidate(userId, branchId); //工作流
//		ipage.initPageCounts(new Integer[] { (int) query.count() });
//		int firstResult = (ipage.getPageNo() - 1) * 10000;
//		query.approveByLast(false);
////		
//		 List<Task> wkfTaskList = query.page(firstResult, 10000).orderDesc(TaskQuery.PROPERTY_DB_CREATEDATE).list();
		appIds = "@"+appIds;
		String s =appIds;
		String[] array = s.split("@");
//		List lnPactList = new ArrayList<LnPact>();
		for(int i=1;i<array.length;i++){
			lnPact = new LnPact();
			lnPact.setAppId(array[i]);
			lnPact.setLnType(lnType);
			lnPact.setProjNo(projNo);
			lnPact.setBrNo(brNo);
			lnPact = lnPactBo.getByIdForList(lnPact);
			if(lnPact!=null){
				Task task = WFUtil.getTaskService().findTaskByProcessInstanceId(lnPact.getProcessId());
				String taskId = task.getId();
				String processId = task.getExecutionId();
				if(task.getSignState().equals("unsigned")){//判断任务是否签收，未签收 调用签收方法自动签收
					WFUtil.getTaskService().take(taskId, userId);
				}
				taskIds = taskIds + "@" + taskId;
				processIds = processIds + "@" + processId;
				lnPact.setTaskId(taskIds);
				lnPact.setProcessId(processIds);
				lnPact.setApprType("02");
				url = task.getApproveUrl();
				apprType = "02";
//				lnPactList.add(lnPact);
			}
		}
//		String worngUrl="";
		String targetUrl = null;
		try {
			url = "{context}/ApproveLoanActionInputForGo.action" + "?lnType=" + lnType+"&brNo="+brNo+"&projNo="+projNo+"&appIds="+appIds+"&taskIds="+taskIds+"&processIds="+processIds+"&abc="+abc;
			targetUrl = url.toString();
			if(targetUrl.startsWith("/cms")){
				targetUrl = targetUrl.replaceAll("/cms", getHttpRequest().getContextPath());
			}else{
				targetUrl = targetUrl.replaceAll("\\{context\\}", getHttpRequest().getContextPath());
			}
			getHttpResponse().sendRedirect(targetUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	public String resumeTask() throws Exception {
		WFUtil.getTaskService().resume(taskId);
		this.addActionMessage("操作成功！");
		return findByPage();
	}
	
	public String suspendTask() throws Exception {
		WFUtil.getTaskService().suspend(taskId);
		this.addActionMessage("操作成功！");
		return findByPage();
	}

	public String complete() throws Exception {
		HttpServletRequest request = getHttpRequest();
		String userid = User.getRoleno(request);
		String transition = request.getParameter(WF.PARAM_TASK_TRANSITION);
		WFUtil.getTaskService().complete(taskId, TaskConstants.PASS, "同意", transition, userid);
		this.addActionMessage("操作成功！");
		return listMyTask();
	}
	
	public String completeByNextUser() throws Exception {
		HttpServletRequest request = getHttpRequest();
		String transition = request.getParameter(WF.PARAM_TASK_TRANSITION);
		String nextAssignee = request.getParameter(WF.PARAM_TASK_NEXTUSER);
		WFUtil.getTaskService().complete(taskId, TaskConstants.PASS, "同意", transition, nextAssignee);
		this.addActionMessage("操作成功！");
		return listMyTask();
	}	

	public String take() throws Exception {
		String userid = User.getLoginid(getHttpRequest());
		WFUtil.getTaskService().take(taskId, userid);
		if("1".equals(isBatchFlag)){
			return "batchList";
		}
		return "myList";
	}
	
	public String untake() throws Exception {
		WFUtil.getTaskService().untake(taskId);
		if("1".equals(isBatchFlag)){
			return "batchList";
		}
		return "myList";
	}
	
	public String listMyTask() throws Exception {
		HttpServletRequest request = getHttpRequest();
//		String userid = User.getRoleno(request);
		String branchId = User.getBrno(request);
		List<Task> list = null;
		
		if( !StringUtil.isEmpty(userId) ) {
			list = WFUtil.getTaskService().findPersonalTasks(userId);
		} 
		
		if( list == null ) {
			list = WFUtil.getTaskService().findGroupTasks(userId, branchId);
		} else {
			list.addAll(WFUtil.getTaskService().findGroupTasks(userId, branchId));
		}
		request.setAttribute(WF.PARAM_LIST_NAME, list);

		return WF.PARAM_MYTASK_LIST;
	}
	
	public String listMyTaskByUser() throws Exception {
		HttpServletRequest request = getHttpRequest();
//		String userid = User.getRoleno(request);
		if( StringUtil.isEmpty(userId) ) {
			return null;
		}
		
		/**page start**/
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		TaskQuery query = WFUtil.getTaskService().createQuery().assignee(userId);
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		List<Task> list = query.page(firstResult, ipage.getPageSize())
									.orderAsc(TaskQuery.PROPERTY_CREATEDATE)
									.list();
		ipage.setResult(list);
		request.setAttribute(WF.PARAM_LIST_NAME, list);
		
		String appMsg = request.getParameter(AppConstant.PARAM_MSG);
		if( !StringUtil.isEmpty(appMsg) ) {
			this.addActionMessage(getText(appMsg));
		}
		return WF.PARAM_MYTASK_LIST_USER;
	}

	public String listMyTaskByGroup() throws Exception {
		ActionContext.initialize(ServletActionContext.getRequest(),ServletActionContext.getResponse());
		String userId =User.getRoleno(getHttpRequest());
		if( StringUtil.isEmpty(userId)) {
			return null;
		}
		
		/**page start**/
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		
		String branchId = User.getBrno(getHttpRequest());
		TaskQuery query = null;
		if (branchId == null || branchId.length() == 0 ) {
			query = WFUtil.getTaskService().createQuery().candidate(userId);
		} else {
			query = WFUtil.getTaskService().createQuery().candidate(userId, branchId);
		}
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		taskList = query.page(firstResult, ipage.getPageSize()).orderAsc(TaskQuery.PROPERTY_CREATEDATE).list();
		ipage.setResult(taskList);
		return WF.PARAM_MYTASK_LIST_GROUP;
	}	

	public String delete() throws Exception {
		WFUtil.getTaskService().delete(taskId);
		this.addActionMessage("删除成功！");
		return findByPage();
	}
	
	public String approveIdea() throws Exception {
		/**page start**/
//		String tlrno =  User.getLoginid(this.getHttpRequest());
//		TaskDwr taskdwr = new TaskDwr();
//		int signType =  taskdwr.getSignType(tlrno);
//		getHttpRequest().setAttribute("signType", signType);
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		HistoryTaskQuery query = WFUtil.getHistoryService().createHistoryTaskQuery().hasResult();
		query = query.appId(appId);
		
		//在这获取到审批类型，在显示审批意见时候判断
		//String pid = WFUtil.getExecutionService().findExecutionByAppId(appId).getProcessDefinitionId();
		//String pid = WFUtil.getTaskService().findTasksByProcessInstanceId(pid).getAppType();//appType都是一样的所以任意取一个
		//String apptype = WFUtil.getHistoryService().;
		//System.out.println(apptype);
		
		String appType=this.getHttpRequest().getParameter("appType");
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		historyTaskList = query.page(firstResult, ipage.getPageSize())
									.orderDesc(HistoryTaskQuery.PROPERTY_DB_ENDTIME)
									.list();
		this.getHttpRequest().setAttribute("historyTaskList", historyTaskList);
		this.getHttpRequest().setAttribute("appType", appType);
		this.getHttpRequest().setAttribute("returnflag", returnflag);
		/**page end**/
		return "approveIdea";		
	}
	
	public String approvePath() throws Exception {
		/**page start**/
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		String processInstanceId = WF.getProcessInstanceId(executionId);
		HistoryTaskQuery query = WFUtil.getHistoryService().createHistoryTaskQuery().hasResult().groupByAppId().groupByAppId();
		query = query.executionId(processInstanceId);
		
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		historyTaskList = query.page(firstResult, ipage.getPageSize())
									.orderDesc(HistoryTaskQuery.PROPERTY_ID)
									.list();
		ipage.setResult(historyTaskList);
		/**page end**/
		return "approvePath";		
	}
	
	public String approveIdea1() throws Exception {
		HttpServletRequest request = getHttpRequest();
		/**page start**/
		String pageNo = this.getEadis_page();
		int currentPage = 1;
		if( !(StringUtil.isEmpty(pageNo)) ) {
			currentPage = Integer.parseInt(pageNo);
		}
		String executionId = request.getParameter(WF.PARAM_EXECUTION_ID);
		String processInstanceId = WF.getProcessInstanceId(executionId);
		HistoryTaskQuery query = WFUtil.getHistoryService().createHistoryTaskQuery().hasResult().groupByAppId().groupByAppId();
		query = query.executionId(processInstanceId);
		
		Ipage ipage = this.getIpage();
		ipage.initPageCounts(new Integer[] { (int) query.count()});
		int firstResult = (currentPage-1) * ipage.getPageSize();
		List<HistoryTask> list = query.page(firstResult, ipage.getPageSize())
									.orderDesc(HistoryTaskQuery.PROPERTY_DB_ENDTIME)
									.list();
		ipage.setResult(list);
		request.setAttribute(WF.PARAM_LIST_NAME, list);
		/**page end**/
		request.setAttribute(WF.PARAM_EXECUTION_ID, executionId);
		
		return "approveIdea";
		
	}

	public FormData getFormwkf0015() {
		return formwkf0015;
	}

	public void setFormwkf0015(FormData formwkf0015) {
		this.formwkf0015 = formwkf0015;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public List<HistoryTask> getHistoryTaskList() {
		return historyTaskList;
	}

	public void setHistoryTaskList(List<HistoryTask> historyTaskList) {
		this.historyTaskList = historyTaskList;
	}

	public String getFormURL() {
		return formURL;
	}

	public void setFormURL(String formURL) {
		this.formURL = formURL;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public FormData getFormwkf0021() {
		return formwkf0021;
	}

	public void setFormwkf0021(FormData formwkf0021) {
		this.formwkf0021 = formwkf0021;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getCifNo() {
		return cifNo;
	}

	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getBegDate() {
		return begDate;
	}



	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}



	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}

	public String getCifName() {
		return cifName;
	}

	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public int getTpageNo() {
		return tpageNo;
	}

	public void setTpageNo(int tpageNo) {
		this.tpageNo = tpageNo;
	}

	public String getHisDate() {
		return hisDate;
	}



	public void setHisDate(String hisDate) {
		this.hisDate = hisDate;
	}



	public int getTpageSize() {
		return tpageSize;
	}

	public void setTpageSize(int tpageSize) {
		this.tpageSize = tpageSize;
	}

	public String getIsEditReturn() {
		return isEditReturn;
	}

	public void setIsEditReturn(String isEditReturn) {
		this.isEditReturn = isEditReturn;
	}

	public String getTappNo() {
		return tappNo;
	}

	public void setTappNo(String tappNo) {
		this.tappNo = tappNo;
	}

	public String getTcifNo() {
		return tcifNo;
	}

	public void setTcifNo(String tcifNo) {
		this.tcifNo = tcifNo;
	}

	public String getTcifName() {
		return tcifName;
	}

	public void setTcifName(String tcifName) {
		this.tcifName = tcifName;
	}



	public FormData getFormwkft1001() {
		return formwkft1001;
	}



	public void setFormwkft1001(FormData formwkft1001) {
		this.formwkft1001 = formwkft1001;
	}



	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}



	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}



	public String getWkfRole() {
		return wkfRole;
	}



	public void setWkfRole(String wkfRole) {
		this.wkfRole = wkfRole;
	}



	public List<WKfTaskTemp> getWkfTaskTempList() {
		return wkfTaskTempList;
	}



	public void setWkfTaskTempList(List<WKfTaskTemp> wkfTaskTempList) {
		this.wkfTaskTempList = wkfTaskTempList;
	}



	public String getAppSts() {
		return appSts;
	}



	public void setAppSts(String appSts) {
		this.appSts = appSts;
	}



	public String getReturnflag() {
		return returnflag;
	}



	public void setReturnflag(String returnflag) {
		this.returnflag = returnflag;
	}



	public String getIsBatchFlag() {
		return isBatchFlag;
	}



	public void setIsBatchFlag(String isBatchFlag) {
		this.isBatchFlag = isBatchFlag;
	}



	public List<LnPact> getLnPactList() {
		return lnPactList;
	}



	public void setLnPactList(List<LnPact> lnPactList) {
		this.lnPactList = lnPactList;
	}



	public LnPact getLnPact() {
		return lnPact;
	}



	public void setLnPact(LnPact lnPact) {
		this.lnPact = lnPact;
	}



	public LnPactBo getLnPactBo() {
		return lnPactBo;
	}



	public void setLnPactBo(LnPactBo lnPactBo) {
		this.lnPactBo = lnPactBo;
	}



	public String getBatchNo() {
		return batchNo;
	}



	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}



	public String getApprType() {
		return apprType;
	}



	public void setApprType(String apprType) {
		this.apprType = apprType;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getTaskIds() {
		return taskIds;
	}



	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}



	public String getProjNo() {
		return projNo;
	}



	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}



	public String getLnType() {
		return lnType;
	}



	public void setLnType(String lnType) {
		this.lnType = lnType;
	}



	public String getBrNo() {
		return brNo;
	}



	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}



	public String getAppIds() {
		return appIds;
	}



	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}



	public String getProcessIds() {
		return processIds;
	}



	public void setProcessIds(String processIds) {
		this.processIds = processIds;
	}



	public String getAbc() {
		return abc;
	}



	public void setAbc(String abc) {
		this.abc = abc;
	}



	public WfTaskBo getWfTaskBo() {
		return wfTaskBo;
	}



	public void setWfTaskBo(WfTaskBo wfTaskBo) {
		this.wfTaskBo = wfTaskBo;
	}


}
