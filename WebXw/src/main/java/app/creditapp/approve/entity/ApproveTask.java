package app.creditapp.approve.entity;
import app.base.BaseDomain;
/**
* Title: ApproveTask.java
* Description:
* @version：1.0
**/
public class ApproveTask extends BaseDomain {
	
	private String flow_id;//流程编号
	private String task_id;//任务编号
	private String app_type;//审批类型
	private String app_no;//申请号
	private String role_no;//审批岗位
	private String approve_user;//审批人
	private String create_time;//创建时间
	private String br_no;//事业部
	private String bs_no;//金融机构
	private String task_sts;//任务状态
	private String task_type;//任务类型
	private String task_desc;//任务描述
	private String role_name;//名称
	private String cif_no;//客户号
	private String cif_name;//客户名称
	private String app_content;//审批内容
	private String app_data;//审批业务数据
	private String user_name;//
	private String last_approve_user;//上个审批人员
	/*******传参数******/
	private String app_idea;
	private String idea_desc;
	private String mainbr_no;
	private String sxapp_no;

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
	public String getTask_sts() {
		return task_sts;
	}
	public void setTask_sts(String task_sts) {
		this.task_sts = task_sts;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getTask_desc() {
		return task_desc;
	}
	public void setTask_desc(String task_desc) {
		this.task_desc = task_desc;
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
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
	public void setApp_content(String app_content) {
		this.app_content = app_content;
	}
	public String getApp_idea() {
		return app_idea;
	}
	public void setApp_idea(String app_idea) {
		this.app_idea = app_idea;
	}
	public String getIdea_desc() {
		return idea_desc;
	}
	public String getApp_data() {
		return app_data;
	}
	public void setApp_data(String appData) {
		app_data = appData;
	}
	public void setIdea_desc(String idea_desc) {
		this.idea_desc = idea_desc;
	}
	public String getMainbr_no() {
		return mainbr_no;
	}
	public String getLast_approve_user() {
		return last_approve_user;
	}
	public void setLast_approve_user(String lastApproveUser) {
		last_approve_user = lastApproveUser;
	}
	public void setMainbr_no(String mainbrNo) {
		mainbr_no = mainbrNo;
	}
	public String getSxapp_no() {
		return sxapp_no;
	}
	public void setSxapp_no(String sxappNo) {
		sxapp_no = sxappNo;
	}
	public String getBs_no() {
		return bs_no;
	}
	public void setBs_no(String bsNo) {
		bs_no = bsNo;
	}
}