package app.creditapp.approve.entity;
import app.base.BaseDomain;
/**
* Title: ApproveNote.java
* Description:
* @version：1.0
**/
public class ApproveNote extends BaseDomain {
	private String note_id;//审批通知书编号
	private String create_time;//业务申报日期
	private String br_no;//业务所属事业部
	private String br_name;//业务所属事业部名称
	private String group_no;//业务所属客户经理组
	private String group_name;//业务所属客户经理组名称
	private Double app_amt;//批准授信额度
	private String chk_time;//首次审查日
	private String app_idea;//审批意见
	private String lim_type;//限制性条件
	private String prod_no;//业务品种
	private String use_method;//使用方法
	private String ln_amt;//金额
	private String term_mon;//授信期限
	private String ln_use;//用途
	private String ln_rate;//利率
	private String filler;//备注
	private String op_no;//登记人
	private String tx_date;//登记日期
	private String up_time;//更新时间
	private String up_opno;//更新人员

	/**
	 * @return 审批通知书编号
	 */
	public String getNote_id() {
	 	return note_id;
	}
	/**
	 * @设置 审批通知书编号
	 * @param note_id
	 */
	public void setNote_id(String note_id) {
	 	this.note_id = note_id;
	}
	/**
	 * @return 业务申报日期
	 */
	public String getCreate_time() {
	 	return create_time;
	}
	/**
	 * @设置 业务申报日期
	 * @param create_time
	 */
	public void setCreate_time(String create_time) {
	 	this.create_time = create_time;
	}
	/**
	 * @return 业务所属事业部
	 */
	public String getBr_no() {
	 	return br_no;
	}
	/**
	 * @设置 业务所属事业部
	 * @param br_no
	 */
	public void setBr_no(String br_no) {
	 	this.br_no = br_no;
	}
	/**
	 * @return 业务所属客户经理组
	 */
	public String getGroup_no() {
	 	return group_no;
	}
	/**
	 * @设置 业务所属客户经理组
	 * @param group_no
	 */
	public void setGroup_no(String group_no) {
	 	this.group_no = group_no;
	}
	/**
	 * @return 批准授信额度
	 */
	public Double getApp_amt() {
	 	return app_amt;
	}
	/**
	 * @设置 批准授信额度
	 * @param app_amt
	 */
	public void setApp_amt(Double app_amt) {
	 	this.app_amt = app_amt;
	}
	/**
	 * @return 首次审查日
	 */
	public String getChk_time() {
	 	return chk_time;
	}
	/**
	 * @设置 首次审查日
	 * @param chk_time
	 */
	public void setChk_time(String chk_time) {
	 	this.chk_time = chk_time;
	}
	/**
	 * @return 审批意见
	 */
	public String getApp_idea() {
	 	return app_idea;
	}
	/**
	 * @设置 审批意见
	 * @param app_idea
	 */
	public void setApp_idea(String app_idea) {
	 	this.app_idea = app_idea;
	}
	/**
	 * @return 限制性条件
	 */
	public String getLim_type() {
	 	return lim_type;
	}
	/**
	 * @设置 限制性条件
	 * @param lim_type
	 */
	public void setLim_type(String lim_type) {
	 	this.lim_type = lim_type;
	}
	/**
	 * @return 业务品种
	 */
	public String getProd_no() {
	 	return prod_no;
	}
	/**
	 * @设置 业务品种
	 * @param prod_no
	 */
	public void setProd_no(String prod_no) {
	 	this.prod_no = prod_no;
	}
	/**
	 * @return 使用方法
	 */
	public String getUse_method() {
	 	return use_method;
	}
	/**
	 * @设置 使用方法
	 * @param use_method
	 */
	public void setUse_method(String use_method) {
	 	this.use_method = use_method;
	}
	/**
	 * @return 金额
	 */
	public String getLn_amt() {
	 	return ln_amt;
	}
	/**
	 * @设置 金额
	 * @param ln_amt
	 */
	public void setLn_amt(String ln_amt) {
	 	this.ln_amt = ln_amt;
	}
	/**
	 * @return 授信期限
	 */
	public String getTerm_mon() {
	 	return term_mon;
	}
	/**
	 * @设置 授信期限
	 * @param term_mon
	 */
	public void setTerm_mon(String term_mon) {
	 	this.term_mon = term_mon;
	}
	/**
	 * @return 用途
	 */
	public String getLn_use() {
	 	return ln_use;
	}
	/**
	 * @设置 用途
	 * @param ln_use
	 */
	public void setLn_use(String ln_use) {
	 	this.ln_use = ln_use;
	}
	/**
	 * @return 利率
	 */
	public String getLn_rate() {
	 	return ln_rate;
	}
	/**
	 * @设置 利率
	 * @param ln_rate
	 */
	public void setLn_rate(String ln_rate) {
	 	this.ln_rate = ln_rate;
	}
	/**
	 * @return 备注
	 */
	public String getFiller() {
	 	return filler;
	}
	/**
	 * @设置 备注
	 * @param filler
	 */
	public void setFiller(String filler) {
	 	this.filler = filler;
	}
	/**
	 * @return 登记人
	 */
	public String getOp_no() {
	 	return op_no;
	}
	/**
	 * @设置 登记人
	 * @param op_no
	 */
	public void setOp_no(String op_no) {
	 	this.op_no = op_no;
	}
	/**
	 * @return 登记日期
	 */
	public String getTx_date() {
	 	return tx_date;
	}
	/**
	 * @设置 登记日期
	 * @param tx_date
	 */
	public void setTx_date(String tx_date) {
	 	this.tx_date = tx_date;
	}
	/**
	 * @return 更新时间
	 */
	public String getUp_time() {
	 	return up_time;
	}
	/**
	 * @设置 更新时间
	 * @param up_time
	 */
	public void setUp_time(String up_time) {
	 	this.up_time = up_time;
	}
	/**
	 * @return 更新人员
	 */
	public String getUp_opno() {
	 	return up_opno;
	}
	/**
	 * @设置 更新人员
	 * @param up_opno
	 */
	public void setUp_opno(String up_opno) {
	 	this.up_opno = up_opno;
	}
	public String getBr_name() {
		return br_name;
	}
	public void setBr_name(String brName) {
		br_name = brName;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String groupName) {
		group_name = groupName;
	}
}