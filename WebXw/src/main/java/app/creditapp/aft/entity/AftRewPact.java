package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftRewPact.java
* Description:
* @version：1.0
**/
public class AftRewPact extends BaseDomain {
	private String rewNo;//预警编号
	private String rewType;//预警类型[01预警02消息]
	private String rewId;//预警ID
	private String rewDate;//预警产生日期
	private Double rewValue;//预警值
	private String projNo;//项目编号
	private String brNo;//合作机构号
	private String pactId;//合同ID
	private String pactNo;//合同编号
	private String cifNo;//客户号
	private String cifName;//客户名称
	private String prdtNo;//产品号
	private Double pactAmt;//合同金额
	private Double bal;//合同余额/冲账金额
	private Double intst;//欠息
	private String begDate;//起始日期/交易日期
	private String endDate;//到期日期
	private String relId;//关联业务ID/押品ID
	private String rewCont;//预警内容
	private String acptNo;//接收人
	private String dealRest;//处理结果[01未处理02已处理]
	private String rewSts;//预警状态[01预警02不预警]
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String rewName;//预警名称
	private String prdtName;//产品名称
	private String brName;//合作机构名称
	private String projName;//项目名称
	
	private String backId;
	private String collWay;
	private String lastDate;
	private String collResult;
	
	
	private int sumCount;//汇总时使用，根据项目汇总时统计总数
	private String loginid;//登录操作员 控制权限
	
	public int getSumCount() {
		return sumCount;
	}
	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
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
	 * @return 合作机构号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 合同ID
	 */
	public String getPactId() {
	 	return pactId;
	}
	/**
	 * @设置 合同ID
	 * @param pactId
	 */
	public void setPactId(String pactId) {
	 	this.pactId = pactId;
	}
	/**
	 * @return 合同编号
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同编号
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 客户号
	 */
	public String getCifNo() {
	 	return cifNo;
	}
	/**
	 * @设置 客户号
	 * @param cifNo
	 */
	public void setCifNo(String cifNo) {
	 	this.cifNo = cifNo;
	}
	/**
	 * @return 客户名称
	 */
	public String getCifName() {
	 	return cifName;
	}
	/**
	 * @设置 客户名称
	 * @param cifName
	 */
	public void setCifName(String cifName) {
	 	this.cifName = cifName;
	}
	/**
	 * @return 产品号
	 */
	public String getPrdtNo() {
	 	return prdtNo;
	}
	/**
	 * @设置 产品号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
	 	this.prdtNo = prdtNo;
	}
	/**
	 * @return 合同金额
	 */
	public Double getPactAmt() {
	 	return pactAmt;
	}
	/**
	 * @设置 合同金额
	 * @param pactAmt
	 */
	public void setPactAmt(Double pactAmt) {
	 	this.pactAmt = pactAmt;
	}
	/**
	 * @return 合同余额/冲账金额
	 */
	public Double getBal() {
	 	return bal;
	}
	/**
	 * @设置 合同余额/冲账金额
	 * @param bal
	 */
	public void setBal(Double bal) {
	 	this.bal = bal;
	}
	/**
	 * @return 欠息
	 */
	public Double getIntst() {
	 	return intst;
	}
	/**
	 * @设置 欠息
	 * @param intst
	 */
	public void setIntst(Double intst) {
	 	this.intst = intst;
	}
	/**
	 * @return 起始日期/交易日期
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 起始日期/交易日期
	 * @param begDate
	 */
	public void setBegDate(String begDate) {
	 	this.begDate = begDate;
	}
	/**
	 * @return 到期日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 到期日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 关联业务ID/押品ID
	 */
	public String getRelId() {
	 	return relId;
	}
	/**
	 * @设置 关联业务ID/押品ID
	 * @param relId
	 */
	public void setRelId(String relId) {
	 	this.relId = relId;
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
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
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
	/**
	 * @return the collWay
	 */
	public String getCollWay() {
		return collWay;
	}
	/**
	 * @param collWay the collWay to set
	 */
	public void setCollWay(String collWay) {
		this.collWay = collWay;
	}

	/**
	 * @return the lastDate
	 */
	public String getLastDate() {
		return lastDate;
	}
	/**
	 * @param lastDate the lastDate to set
	 */
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	/**
	 * @return the collResult
	 */
	public String getCollResult() {
		return collResult;
	}
	/**
	 * @param collResult the collResult to set
	 */
	public void setCollResult(String collResult) {
		this.collResult = collResult;
	}
	/**
	 * @return the backId
	 */
	public String getBackId() {
		return backId;
	}
	/**
	 * @param backId the backId to set
	 */
	public void setBackId(String backId) {
		this.backId = backId;
	}
}