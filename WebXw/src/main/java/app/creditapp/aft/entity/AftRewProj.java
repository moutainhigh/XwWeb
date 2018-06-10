package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftRewProj.java
* Description:
* @version：1.0
**/
public class AftRewProj extends BaseDomain {
	private String rewType;//预警类型[01预警02消息]
	private String rewId;//预警ID
	private String rewNo;//预警编号
	private Double rewValue;//预警值
	private String rewDate;//预警产生日期
	private String projNo;//项目编号
	private String projName;//项目名称
	private String projNatu;//项目性质
	private Double projAmt;//项目规模
	private String setupDate;//成立日期
	private String endDate;//结束日期
	private Double projBal;//专户余额
	private Double overRate;//贷款逾期率
	private String rewCont;//预警内容
	private String acptNo;//接收人
	private String dealRest;//处理结果[01未处理02已处理]
	private String rewSts;//预警状态[01预警02不预警]
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String rewName;//预警名称

	private String loginid;//登录操作员 控制权限
	/**
	 * @return 预警类型[01预警02消息]
	 */
	public String getRewType() {
	 	return rewType;
	}
	/**
	 * @设置 预警类型[01预警02消息]
	 * @param rewType
	 */
	public void setRewType(String rewType) {
	 	this.rewType = rewType;
	}
	/**
	 * @return 预警ID
	 */
	public String getRewId() {
	 	return rewId;
	}
	/**
	 * @设置 预警ID
	 * @param rewId
	 */
	public void setRewId(String rewId) {
	 	this.rewId = rewId;
	}
	/**
	 * @return 预警编号
	 */
	public String getRewNo() {
	 	return rewNo;
	}
	/**
	 * @设置 预警编号
	 * @param rewNo
	 */
	public void setRewNo(String rewNo) {
	 	this.rewNo = rewNo;
	}
	/**
	 * @return 预警值
	 */
	public Double getRewValue() {
	 	return rewValue;
	}
	/**
	 * @设置 预警值
	 * @param rewValue
	 */
	public void setRewValue(Double rewValue) {
	 	this.rewValue = rewValue;
	}
	/**
	 * @return 预警产生日期
	 */
	public String getRewDate() {
	 	return rewDate;
	}
	/**
	 * @设置 预警产生日期
	 * @param rewDate
	 */
	public void setRewDate(String rewDate) {
	 	this.rewDate = rewDate;
	}
	/**
	 * @return 项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	/**
	 * @return 项目名称
	 */
	public String getProjName() {
	 	return projName;
	}
	/**
	 * @设置 项目名称
	 * @param projName
	 */
	public void setProjName(String projName) {
	 	this.projName = projName;
	}
	/**
	 * @return 项目性质
	 */
	public String getProjNatu() {
	 	return projNatu;
	}
	/**
	 * @设置 项目性质
	 * @param projNatu
	 */
	public void setProjNatu(String projNatu) {
	 	this.projNatu = projNatu;
	}
	/**
	 * @return 项目规模
	 */
	public Double getProjAmt() {
	 	return projAmt;
	}
	/**
	 * @设置 项目规模
	 * @param projAmt
	 */
	public void setProjAmt(Double projAmt) {
	 	this.projAmt = projAmt;
	}
	/**
	 * @return 成立日期
	 */
	public String getSetupDate() {
	 	return setupDate;
	}
	/**
	 * @设置 成立日期
	 * @param setupDate
	 */
	public void setSetupDate(String setupDate) {
	 	this.setupDate = setupDate;
	}
	/**
	 * @return 结束日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 专户余额
	 */
	public Double getProjBal() {
	 	return projBal;
	}
	/**
	 * @设置 专户余额
	 * @param projBal
	 */
	public void setProjBal(Double projBal) {
	 	this.projBal = projBal;
	}
	/**
	 * @return 贷款逾期率
	 */
	public Double getOverRate() {
	 	return overRate;
	}
	/**
	 * @设置 贷款逾期率
	 * @param overRate
	 */
	public void setOverRate(Double overRate) {
	 	this.overRate = overRate;
	}
	/**
	 * @return 预警内容
	 */
	public String getRewCont() {
	 	return rewCont;
	}
	/**
	 * @设置 预警内容
	 * @param rewCont
	 */
	public void setRewCont(String rewCont) {
	 	this.rewCont = rewCont;
	}
	/**
	 * @return 接收人
	 */
	public String getAcptNo() {
	 	return acptNo;
	}
	/**
	 * @设置 接收人
	 * @param acptNo
	 */
	public void setAcptNo(String acptNo) {
	 	this.acptNo = acptNo;
	}
	/**
	 * @return 处理结果[01未处理02已处理]
	 */
	public String getDealRest() {
	 	return dealRest;
	}
	/**
	 * @设置 处理结果[01未处理02已处理]
	 * @param dealRest
	 */
	public void setDealRest(String dealRest) {
	 	this.dealRest = dealRest;
	}
	/**
	 * @return 预警状态[01预警02不预警]
	 */
	public String getRewSts() {
	 	return rewSts;
	}
	/**
	 * @设置 预警状态[01预警02不预警]
	 * @param rewSts
	 */
	public void setRewSts(String rewSts) {
	 	this.rewSts = rewSts;
	}
	/**
	 * @return 登记日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 登记日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 更新日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 更新日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
	public String getRewName() {
		return rewName;
	}
	public void setRewName(String rewName) {
		this.rewName = rewName;
	}
	/**
	 * @return the loginid
	 */
	public String getLoginid() {
		return loginid;
	}
	/**
	 * @param loginid the loginid to set
	 */
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
}