package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftRewReal.java
* Description:
* @version：1.0
**/
public class AftRewReal extends BaseDomain {
	private String rewType;//预警类型[01预警02消息]
	private String rewId;//预警ID
	private String rewNo;//预警编号
	private Double rewValue;//预警值
	private String rewDate;//预警产生日期
	private String projNo;//项目编号
	private String fundNo;//资金编号
	private String dueNo;//借据号
	private String brNo;//合作机构
	private String pactNo;//合同号
	private Double rewAmt;//预警金额
	private String rewCont;//预警内容
	private String acptNo;//接收人
	private String dealRest;//处理结果[01未处理02已处理]
	private String rewSts;//预警状态[01预警02不预警]
	private String txDate;//登记日期
	private String upDate;//更新日期
	
	private String rewName;//预警编号
	private String projName;//项目编号
	private String brName;//预警编号
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
	 * @return 资金编号
	 */
	public String getFundNo() {
	 	return fundNo;
	}
	/**
	 * @设置 资金编号
	 * @param fundNo
	 */
	public void setFundNo(String fundNo) {
	 	this.fundNo = fundNo;
	}
	/**
	 * @return 借据号
	 */
	public String getDueNo() {
	 	return dueNo;
	}
	/**
	 * @设置 借据号
	 * @param dueNo
	 */
	public void setDueNo(String dueNo) {
	 	this.dueNo = dueNo;
	}
	/**
	 * @return 合作机构
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 合同号
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同号
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 预警金额
	 */
	public Double getRewAmt() {
	 	return rewAmt;
	}
	/**
	 * @设置 预警金额
	 * @param rewAmt
	 */
	public void setRewAmt(Double rewAmt) {
	 	this.rewAmt = rewAmt;
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
	/**
	 * @return the rewName
	 */
	public String getRewName() {
		return rewName;
	}
	/**
	 * @param rewName the rewName to set
	 */
	public void setRewName(String rewName) {
		this.rewName = rewName;
	}
	/**
	 * @return the projName
	 */
	public String getProjName() {
		return projName;
	}
	/**
	 * @param projName the projName to set
	 */
	public void setProjName(String projName) {
		this.projName = projName;
	}
	/**
	 * @return the brName
	 */
	public String getBrName() {
		return brName;
	}
	/**
	 * @param brName the brName to set
	 */
	public void setBrName(String brName) {
		this.brName = brName;
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