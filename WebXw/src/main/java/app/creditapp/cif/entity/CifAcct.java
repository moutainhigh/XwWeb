package app.creditapp.cif.entity;
import app.base.BaseDomain;
/**
* Title: CifAcct.java
* Description:
* @version：1.0
**/
public class CifAcct extends BaseDomain {
	private String appId;//申请ID
	private String cifNo;//客户号码
	private String acNo;//账户号码
	private String acName;//账户名
	private String acType;//账户类型
	private String acUse;//使用类型[01还款02发放]
	private String opnCode;//开户银行代码
	private String opnNo;//开户银行号码
	private String opnName;//开户银行名称
	private String opnProv;//开户银行所在省
	private String opnCity;//开户银行所在市
	private String acSts;//账户状态[01正常02注销03挂失]

	private String idType;   //账户户名
	private String idNo;   //账户户名
	private String phoneNo;   //账户户名
	private String validDate; //合同号
	private String cvvNo; //合同号
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
	 * @return 客户号码
	 */
	public String getCifNo() {
	 	return cifNo;
	}
	/**
	 * @设置 客户号码
	 * @param cifNo
	 */
	public void setCifNo(String cifNo) {
	 	this.cifNo = cifNo;
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
	 * @return 账户名
	 */
	public String getAcName() {
	 	return acName;
	}
	/**
	 * @设置 账户名
	 * @param acName
	 */
	public void setAcName(String acName) {
	 	this.acName = acName;
	}
	/**
	 * @return 账户类型
	 */
	public String getAcType() {
	 	return acType;
	}
	/**
	 * @设置 账户类型
	 * @param acType
	 */
	public void setAcType(String acType) {
	 	this.acType = acType;
	}
	/**
	 * @return 使用类型[01还款02发放]
	 */
	public String getAcUse() {
	 	return acUse;
	}
	/**
	 * @设置 使用类型[01还款02发放]
	 * @param acUse
	 */
	public void setAcUse(String acUse) {
	 	this.acUse = acUse;
	}
	/**
	 * @return 开户银行代码
	 */
	public String getOpnCode() {
	 	return opnCode;
	}
	/**
	 * @设置 开户银行代码
	 * @param opnCode
	 */
	public void setOpnCode(String opnCode) {
	 	this.opnCode = opnCode;
	}
	/**
	 * @return 开户银行号码
	 */
	public String getOpnNo() {
	 	return opnNo;
	}
	/**
	 * @设置 开户银行号码
	 * @param opnNo
	 */
	public void setOpnNo(String opnNo) {
	 	this.opnNo = opnNo;
	}
	/**
	 * @return 开户银行名称
	 */
	public String getOpnName() {
	 	return opnName;
	}
	/**
	 * @设置 开户银行名称
	 * @param opnName
	 */
	public void setOpnName(String opnName) {
	 	this.opnName = opnName;
	}
	/**
	 * @return 开户银行所在省
	 */
	public String getOpnProv() {
	 	return opnProv;
	}
	/**
	 * @设置 开户银行所在省
	 * @param opnProv
	 */
	public void setOpnProv(String opnProv) {
	 	this.opnProv = opnProv;
	}
	/**
	 * @return 开户银行所在市
	 */
	public String getOpnCity() {
	 	return opnCity;
	}
	/**
	 * @设置 开户银行所在市
	 * @param opnCity
	 */
	public void setOpnCity(String opnCity) {
	 	this.opnCity = opnCity;
	}
	/**
	 * @return 账户状态[01正常02注销03挂失]
	 */
	public String getAcSts() {
	 	return acSts;
	}
	/**
	 * @设置 账户状态[01正常02注销03挂失]
	 * @param acSts
	 */
	public void setAcSts(String acSts) {
	 	this.acSts = acSts;
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
}