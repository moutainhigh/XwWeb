package app.creditapp.approve.entity;
import app.base.BaseDomain;
/**
* Title: ApproveTaskHis.java
* Description:
* @version：1.0
**/
public class ApproveTaskHis extends BaseDomain {
	private String flow_id;//流程编号
	private String task_id;//任务编号
	private String app_type;//审批类型
	private String app_no;//申请号
	private String role_no;//审批岗位
	private String approve_user;//审批人
	private String create_time;//创建时间
	private String end_time;//结束时间
	private String br_no;//机构
	private String approve_idea;//审批意见
	private String idea_desc;//审批意见描述
	private String task_type;//任务类型
	private String role_name;//角色名称
	private String cif_no;//客户号
	private String cif_name;//客户名称
	private String app_content;//审批内容
	private String signing_flag;//申请流程中会签岗位正在审批的标志
	private String flow_sts;//流程状态
	private String app_data;
	private String br_name;
	private String user_name;
	private String sxapp_no;
	private String cif_flag;
	private String table_name;
	private String col_name;
	private String last_approve_user;//上个审批人员

	/**
	 * @return 流程编号
	 */
	public String getFlow_id() {
	 	return flow_id;
	}
	/**
	 * @设置 流程编号
	 * @param flow_id
	 */
	public void setFlow_id(String flow_id) {
	 	this.flow_id = flow_id;
	}
	/**
	 * @return 任务编号
	 */
	public String getTask_id() {
	 	return task_id;
	}
	/**
	 * @设置 任务编号
	 * @param task_id
	 */
	public void setTask_id(String task_id) {
	 	this.task_id = task_id;
	}
	/**
	 * @return 审批类型
	 */
	public String getApp_type() {
	 	return app_type;
	}
	/**
	 * @设置 审批类型
	 * @param app_type
	 */
	public void setApp_type(String app_type) {
	 	this.app_type = app_type;
	}
	/**
	 * @return 申请号
	 */
	public String getApp_no() {
	 	return app_no;
	}
	/**
	 * @设置 申请号
	 * @param app_no
	 */
	public void setApp_no(String app_no) {
	 	this.app_no = app_no;
	}
	/**
	 * @return 审批岗位
	 */
	public String getRole_no() {
	 	return role_no;
	}
	/**
	 * @设置 审批岗位
	 * @param role_no
	 */
	public void setRole_no(String role_no) {
	 	this.role_no = role_no;
	}
	/**
	 * @return 审批人
	 */
	public String getApprove_user() {
	 	return approve_user;
	}
	/**
	 * @设置 审批人
	 * @param approve_user
	 */
	public void setApprove_user(String approve_user) {
	 	this.approve_user = approve_user;
	}
	/**
	 * @return 创建时间
	 */
	public String getCreate_time() {
	 	return create_time;
	}
	/**
	 * @设置 创建时间
	 * @param create_time
	 */
	public void setCreate_time(String create_time) {
	 	this.create_time = create_time;
	}
	/**
	 * @return 结束时间
	 */
	public String getEnd_time() {
	 	return end_time;
	}
	/**
	 * @设置 结束时间
	 * @param end_time
	 */
	public void setEnd_time(String end_time) {
	 	this.end_time = end_time;
	}
	/**
	 * @return 机构
	 */
	public String getBr_no() {
	 	return br_no;
	}
	/**
	 * @设置 机构
	 * @param br_no
	 */
	public void setBr_no(String br_no) {
	 	this.br_no = br_no;
	}
	/**
	 * @return 审批意见
	 */
	public String getApprove_idea() {
	 	return approve_idea;
	}
	/**
	 * @设置 审批意见
	 * @param approve_idea
	 */
	public void setApprove_idea(String approve_idea) {
	 	this.approve_idea = approve_idea;
	}
	/**
	 * @return 审批意见描述
	 */
	public String getIdea_desc() {
	 	return idea_desc;
	}
	/**
	 * @设置 审批意见描述
	 * @param idea_desc
	 */
	public void setIdea_desc(String idea_desc) {
	 	this.idea_desc = idea_desc;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getCif_no() {
		return cif_no;
	}
	public void setCif_no(String cif_no) {
		this.cif_no = cif_no;
	}
	public String getApp_data() {
		return app_data;
	}
	public void setApp_data(String appData) {
		app_data = appData;
	}
	public String getCif_name() {
		return cif_name;
	}
	public void setCif_name(String cif_name) {
		this.cif_name = cif_name;
	}
	public String getApp_content() {
		return app_content;
	}
	public String getBr_name() {
		return br_name;
	}
	public void setBr_name(String brName) {
		br_name = brName;
	}
	public void setApp_content(String app_content) {
		this.app_content = app_content;
	}
	public String getSigning_flag() {
		return signing_flag;
	}
	public String getFlow_sts() {
		return flow_sts;
	}
	public void setFlow_sts(String flowSts) {
		flow_sts = flowSts;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public void setSigning_flag(String signing_flag) {
		this.signing_flag = signing_flag;
	}
	public String getSxapp_no() {
		return sxapp_no;
	}
	public void setSxapp_no(String sxappNo) {
		sxapp_no = sxappNo;
	}
	public String getCif_flag() {
		return cif_flag;
	}
	public void setCif_flag(String cifFlag) {
		cif_flag = cifFlag;
	}
	public String getTable_name() {
		return table_name;
	}
	public String getCol_name() {
		return col_name;
	}
	public void setCol_name(String colName) {
		col_name = colName;
	}
	public void setTable_name(String tableName) {
		table_name = tableName;
	}
	public String getLast_approve_user() {
		return last_approve_user;
	}
	public void setLast_approve_user(String lastApproveUser) {
		last_approve_user = lastApproveUser;
	}
}