package app.creditapp.wkf.entity;

import app.base.BaseDomain;

/*
 * ÈÎÎñ±í
 */
public class WfTask extends BaseDomain{
	private String taskId; //DBID_
	private String taskClass;  //CLASS_
	private String taskDbversion; //DBVERSION_
	private String taskState;  //STATE_
	private String taskSignState; //SIGN_STATE_
	private String taskAssignee;  //ASSIGNEE_   USER_ID
	private String taskExcutionId;  //EXECUTION_ID_  PROCESS_ID
	private String taskBranch;   //BRANCH_
	private String taskAppId;    //APP_ID_
	private String taskAppType;   //APP_TYPE_
	private String taskApproveUrl; //APPROVE_URL_
	private String taskApproveByLast;  //APPROVE_BYLAST_
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskClass() {
		return taskClass;
	}
	public void setTaskClass(String taskClass) {
		this.taskClass = taskClass;
	}
	public String getTaskDbversion() {
		return taskDbversion;
	}
	public void setTaskDbversion(String taskDbversion) {
		this.taskDbversion = taskDbversion;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public String getTaskSignState() {
		return taskSignState;
	}
	public void setTaskSignState(String taskSignState) {
		this.taskSignState = taskSignState;
	}
	public String getTaskAssignee() {
		return taskAssignee;
	}
	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}
	public String getTaskExcutionId() {
		return taskExcutionId;
	}
	public void setTaskExcutionId(String taskExcutionId) {
		this.taskExcutionId = taskExcutionId;
	}
	public String getTaskBranch() {
		return taskBranch;
	}
	public void setTaskBranch(String taskBranch) {
		this.taskBranch = taskBranch;
	}
	public String getTaskAppId() {
		return taskAppId;
	}
	public void setTaskAppId(String taskAppId) {
		this.taskAppId = taskAppId;
	}
	public String getTaskAppType() {
		return taskAppType;
	}
	public void setTaskAppType(String taskAppType) {
		this.taskAppType = taskAppType;
	}
	public String getTaskApproveUrl() {
		return taskApproveUrl;
	}
	public void setTaskApproveUrl(String taskApproveUrl) {
		this.taskApproveUrl = taskApproveUrl;
	}
	public String getTaskApproveByLast() {
		return taskApproveByLast;
	}
	public void setTaskApproveByLast(String taskApproveByLast) {
		this.taskApproveByLast = taskApproveByLast;
	}
	
	

}
