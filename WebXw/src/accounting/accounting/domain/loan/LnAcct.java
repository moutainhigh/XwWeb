package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AcLnAcct.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class LnAcct extends accounting.domain.sys.CMISDomain {
	private String appId;//申请ID
	private String cifNo;//客户号
	private String acUse;//账户用途[1扣款账户2放款账户]
	private Double acAmt;//放款金额
	private String acType;//账户类型[11个人账户12企业账户]
	private String acNo;//账户号码
	private String acName;//账户户名
	private String bankCode;//开户银行代码
	private String bankProv;//开户银行所在省
	private String bankCity;//开户银行所在市
	private String bankSite;//开户银行网点
	
	private String idType;//证件类型
	private String idNo;//证件号码
	private String phoneNo;//手机号码
	private String validDate;//信用卡有效期
	private String cvvNo;//银行卡CVV2码

	/**
	 * @return 申请ID
	 */
	public String getAppId() {
	 	return appId;
	}
	/**
	 * @设置 申请ID
	 * @param appId
	 */
	public void setAppId(String appId) {
	 	this.appId = appId;
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
	 * @return 账户用途[1扣款账户2放款账户]
	 */
	public String getAcUse() {
	 	return acUse;
	}
	/**
	 * @设置 账户用途[1扣款账户2放款账户]
	 * @param acUse
	 */
	public void setAcUse(String acUse) {
	 	this.acUse = acUse;
	}
	/**
	 * @return 放款金额
	 */
	public Double getAcAmt() {
	 	return acAmt;
	}
	/**
	 * @设置 放款金额
	 * @param acAmt
	 */
	public void setAcAmt(Double acAmt) {
	 	this.acAmt = acAmt;
	}
	/**
	 * @return 账户类型[11个人账户12企业账户]
	 */
	public String getAcType() {
	 	return acType;
	}
	/**
	 * @设置 账户类型[11个人账户12企业账户]
	 * @param acType
	 */
	public void setAcType(String acType) {
	 	this.acType = acType;
	}
	/**
	 * @return 账户号码
	 */
	public String getAcNo() {
	 	return acNo;
	}
	/**
	 * @设置 账户号码
	 * @param acNo
	 */
	public void setAcNo(String acNo) {
	 	this.acNo = acNo;
	}
	/**
	 * @return 账户户名
	 */
	public String getAcName() {
	 	return acName;
	}
	/**
	 * @设置 账户户名
	 * @param acName
	 */
	public void setAcName(String acName) {
	 	this.acName = acName;
	}
	/**
	 * @return 开户银行代码
	 */
	public String getBankCode() {
	 	return bankCode;
	}
	/**
	 * @设置 开户银行代码
	 * @param bankCode
	 */
	public void setBankCode(String bankCode) {
	 	this.bankCode = bankCode;
	}
	/**
	 * @return 开户银行所在省
	 */
	public String getBankProv() {
	 	return bankProv;
	}
	/**
	 * @设置 开户银行所在省
	 * @param bankProv
	 */
	public void setBankProv(String bankProv) {
	 	this.bankProv = bankProv;
	}
	/**
	 * @return 开户银行所在市
	 */
	public String getBankCity() {
	 	return bankCity;
	}
	/**
	 * @设置 开户银行所在市
	 * @param bankCity
	 */
	public void setBankCity(String bankCity) {
	 	this.bankCity = bankCity;
	}
	/**
	 * @return 开户银行网点
	 */
	public String getBankSite() {
	 	return bankSite;
	}
	/**
	 * @设置 开户银行网点
	 * @param bankSite
	 */
	public void setBankSite(String bankSite) {
	 	this.bankSite = bankSite;
	}
	public String getIdType() {
		return idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getValidDate() {
		return validDate;
	}
	public String getCvvNo() {
		return cvvNo;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public void setCvvNo(String cvvNo) {
		this.cvvNo = cvvNo;
	}
}