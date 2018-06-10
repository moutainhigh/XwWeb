package app.creditapp.corp.entity;
import app.base.BaseDomain;
/**
* Title: CorpAcct.java
* Description:
* @version：1.0
**/
public class CorpAcct extends BaseDomain {
	private String upOpno;//修改人员
	private String upOpname;//修改人员名称
	private String upDate;//修改日期
	private String txDate;//登记日期
	private String opNo;//操作员
	private String opName;//操作员名称
	private String deptNo;//登记部门
	private String filler;//备注
	private String areaid;//地区ID
	private String city;//开户所在地市名
	private String province;//开户所在地省名
	private String exchange;//联行号
	private String orgNo;//机构号
	private String opnAddr;//开户所在地
	private String acName;//帐户名称
	private String opnAcno;//帐户编号
	private String opnBank;//开户银行
	private String acctId;//账户信息ID
	private String brName;//合作机构名称
	private String brNo;//合作机构编号
	private String acctUse;//账户类型
	private String acctBal;//账户余额
	private String sendSts;//是否开户[01未开户02已开户03--]CARD_STS
	
	private String acType;//结算账户类型
	private String idType;//证件类型
	private String idNo;//证件号码
	private String phoneNo;//手机号码
	private String validDate;//信用卡有效期
	private String cvvNo;//银行卡CVV2码
	private String acctNoJs;//结算银行卡号
	private String acctNameJs;//结算银行卡账户名称
	
	private String corpAcctType;//只在新增保存时传值使用
	private String projNo;//只在新增保存时传值使用
	private String projName;//只在新增保存时传值使用
	private String projAcNo;//只在项目查询对应机构费用账户时使用
	private String corpAcctSts;
	
	public String getSendSts() {
		return sendSts;
	}
	public void setSendSts(String sendSts) {
		this.sendSts = sendSts;
	}

	/**
	 * @return 修改人员
	 */
	public String getUpOpno() {
	 	return upOpno;
	}
	/**
	 * @设置 修改人员
	 * @param upOpno
	 */
	public void setUpOpno(String upOpno) {
	 	this.upOpno = upOpno;
	}
	/**
	 * @return 修改日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 修改日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
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
	 * @return 操作员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 操作员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 登记部门
	 */
	public String getDeptNo() {
	 	return deptNo;
	}
	/**
	 * @设置 登记部门
	 * @param deptNo
	 */
	public void setDeptNo(String deptNo) {
	 	this.deptNo = deptNo;
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
	 * @return 地区ID
	 */
	public String getAreaid() {
	 	return areaid;
	}
	/**
	 * @设置 地区ID
	 * @param areaid
	 */
	public void setAreaid(String areaid) {
	 	this.areaid = areaid;
	}
	/**
	 * @return 开户所在地市名
	 */
	public String getCity() {
	 	return city;
	}
	/**
	 * @设置 开户所在地市名
	 * @param city
	 */
	public void setCity(String city) {
	 	this.city = city;
	}
	/**
	 * @return 开户所在地省名
	 */
	public String getProvince() {
	 	return province;
	}
	/**
	 * @设置 开户所在地省名
	 * @param province
	 */
	public void setProvince(String province) {
	 	this.province = province;
	}
	/**
	 * @return 联行号
	 */
	public String getExchange() {
	 	return exchange;
	}
	/**
	 * @设置 联行号
	 * @param exchange
	 */
	public void setExchange(String exchange) {
	 	this.exchange = exchange;
	}
	/**
	 * @return 机构号
	 */
	public String getOrgNo() {
	 	return orgNo;
	}
	/**
	 * @设置 机构号
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
	 	this.orgNo = orgNo;
	}
	/**
	 * @return 开户所在地
	 */
	public String getOpnAddr() {
	 	return opnAddr;
	}
	/**
	 * @设置 开户所在地
	 * @param opnAddr
	 */
	public void setOpnAddr(String opnAddr) {
	 	this.opnAddr = opnAddr;
	}
	/**
	 * @return 帐户名称
	 */
	public String getAcName() {
	 	return acName;
	}
	/**
	 * @设置 帐户名称
	 * @param acName
	 */
	public void setAcName(String acName) {
	 	this.acName = acName;
	}
	/**
	 * @return 帐户编号
	 */
	public String getOpnAcno() {
	 	return opnAcno;
	}
	/**
	 * @设置 帐户编号
	 * @param opnAcno
	 */
	public void setOpnAcno(String opnAcno) {
	 	this.opnAcno = opnAcno;
	}
	/**
	 * @return 开户银行
	 */
	public String getOpnBank() {
	 	return opnBank;
	}
	/**
	 * @设置 开户银行
	 * @param opnBank
	 */
	public void setOpnBank(String opnBank) {
	 	this.opnBank = opnBank;
	}
	/**
	 * @return 账户信息ID
	 */
	public String getAcctId() {
	 	return acctId;
	}
	/**
	 * @设置 账户信息ID
	 * @param acctId
	 */
	public void setAcctId(String acctId) {
	 	this.acctId = acctId;
	}
	/**
	 * @return 合作机构名称
	 */
	public String getBrName() {
	 	return brName;
	}
	/**
	 * @设置 合作机构名称
	 * @param brName
	 */
	public void setBrName(String brName) {
	 	this.brName = brName;
	}
	/**
	 * @return 合作机构编号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	public String getUpOpname() {
		return upOpname;
	}
	public void setUpOpname(String upOpname) {
		this.upOpname = upOpname;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getAcctUse() {
		return acctUse;
	}
	public void setAcctUse(String acctUse) {
		this.acctUse = acctUse;
	}
	/**
	 * @return the acctBal
	 */
	public String getAcctBal() {
		return acctBal;
	}
	/**
	 * @param acctBal the acctBal to set
	 */
	public void setAcctBal(String acctBal) {
		this.acctBal = acctBal;
	}
	/**
	 * @return the corpAcctType
	 */
	public String getCorpAcctType() {
		return corpAcctType;
	}
	/**
	 * @param corpAcctType the corpAcctType to set
	 */
	public void setCorpAcctType(String corpAcctType) {
		this.corpAcctType = corpAcctType;
	}
	/**
	 * @return the projNo
	 */
	public String getProjNo() {
		return projNo;
	}
	/**
	 * @param projNo the projNo to set
	 */
	public void setProjNo(String projNo) {
		this.projNo = projNo;
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
	 * @return the projAcNo
	 */
	public String getProjAcNo() {
		return projAcNo;
	}
	/**
	 * @param projAcNo the projAcNo to set
	 */
	public void setProjAcNo(String projAcNo) {
		this.projAcNo = projAcNo;
	}
	/**
	 * @return the corpAcctSts
	 */
	public String getCorpAcctSts() {
		return corpAcctSts;
	}
	/**
	 * @param corpAcctSts the corpAcctSts to set
	 */
	public void setCorpAcctSts(String corpAcctSts) {
		this.corpAcctSts = corpAcctSts;
	}
	/**
	 * @return the acType
	 */
	public String getAcType() {
		return acType;
	}
	/**
	 * @param acType the acType to set
	 */
	public void setAcType(String acType) {
		this.acType = acType;
	}
	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}
	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}
	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the validDate
	 */
	public String getValidDate() {
		return validDate;
	}
	/**
	 * @param validDate the validDate to set
	 */
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	/**
	 * @return the cvvNo
	 */
	public String getCvvNo() {
		return cvvNo;
	}
	/**
	 * @param cvvNo the cvvNo to set
	 */
	public void setCvvNo(String cvvNo) {
		this.cvvNo = cvvNo;
	}
	/**
	 * @return the acctNoJs
	 */
	public String getAcctNoJs() {
		return acctNoJs;
	}
	/**
	 * @param acctNoJs the acctNoJs to set
	 */
	public void setAcctNoJs(String acctNoJs) {
		this.acctNoJs = acctNoJs;
	}
	/**
	 * @return the acctNameJs
	 */
	public String getAcctNameJs() {
		return acctNameJs;
	}
	/**
	 * @param acctNameJs the acctNameJs to set
	 */
	public void setAcctNameJs(String acctNameJs) {
		this.acctNameJs = acctNameJs;
	}
	
}