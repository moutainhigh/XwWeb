package app.creditapp.wkf.entity;

import app.base.BaseDomain;

public class WKfTaskTemp extends BaseDomain {
	
	
	private String appType;//审批类型
	private String wkfRole;//审批角色
	private String cif_no;//客户号
	private String cif_name;//客户名
	private String appValue;//业务数据
	private String appValueScript;//业务数据浮动框显示
	private String appSts;//审批状态
	private String appIdea;//审批意见
	private String taskDesc;//当前审批任务节点描述
	private String appId;//申请号
	private String executionId;
	private String id_no;
	private String begDate;
	private String endDate;
	private String process_id;
	private String taskId;
	
	private  String br_no;
	
	
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
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
	public String getWkfRole() {
		return wkfRole;
	}
	public void setWkfRole(String wkfRole) {
		this.wkfRole = wkfRole;
	}
	public String getAppValue() {
		return appValue;
	}
	public void setAppValue(String appValue) {
		this.appValue = appValue;
	}
	public String getAppValueScript() {
		return appValueScript;
	}
	public void setAppValueScript(String appValueScript) {
		this.appValueScript = appValueScript;
	}
	public String getAppSts() {
		return appSts;
	}
	public void setAppSts(String appSts) {
		this.appSts = appSts;
	}
	public String getAppIdea() {
		return appIdea;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setAppIdea(String appIdea) {
		this.appIdea = appIdea;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getCif_no() {
		return cif_no;
	}
	public void setCif_no(String cif_no) {
		this.cif_no = cif_no;
	}
	public String getProcess_id() {
		return process_id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public void setProcess_id(String processId) {
		process_id = processId;
	}
	public String getCif_name() {
		return cif_name;
	}
	public void setCif_name(String cif_name) {
		this.cif_name = cif_name;
	}
	public String getBr_no() {
		return br_no;
	}
	public void setBr_no(String brNo) {
		br_no = brNo;
	}
	
	
	
	
	
	
}
