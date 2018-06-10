package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * listAc
 * @描述   进件批量申请--输入实体类 LN_ACCT_MID
 */
public class WsIn2101_1_1 {
	
	
	private String acUse;    //账户用途
	private double acAmt;    //放款金额
	private String acType;   //账户类型
	private String acNo; 	 //账户号
	private String acName;   //账户户名
	private String idType;   //账户户名
	private String idNo;   //账户户名
	private String phoneNo;   //账户户名
	private String bankCode; //银行代码
	private String bankProv; //账户开户省
	private String bankCity; //账户开户市 
	private String pactNo; //合同号
	private String payType; //合同号
	private String validDate; //合同号
	private String cvvNo; //合同号
	
	//添加
	private String appId;
	private String bankSite;
	
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getAcUse() {
		return acUse;
	}
	public void setAcUse(String acUse) {
		this.acUse = acUse;
	}
	public double getAcAmt() {
		return acAmt;
	}
	public void setAcAmt(double acAmt) {
		this.acAmt = acAmt;
	}
	public String getAcType() {
		return acType;
	}
	public void setAcType(String acType) {
		this.acType = acType;
	}
	public String getAcNo() {
		return acNo;
	}
	public void setAcNo(String acNo) {
		this.acNo = acNo;
	}
	public String getAcName() {
		return acName;
	}
	public void setAcName(String acName) {
		this.acName = acName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankProv() {
		return bankProv;
	}
	public void setBankProv(String bankProv) {
		this.bankProv = bankProv;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getBankSite() {
		return bankSite;
	}
	public void setBankSite(String bankSite) {
		this.bankSite = bankSite;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
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
