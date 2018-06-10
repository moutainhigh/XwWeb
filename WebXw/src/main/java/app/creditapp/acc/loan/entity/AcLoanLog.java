package app.creditapp.acc.loan.entity;
import app.base.BaseDomain;
/**
* Title: AcLoanLog.java
* Description:
* @version：1.0
**/
public class AcLoanLog extends BaseDomain {
	private String loanLogNo;//放款流水号
	private String traceNo;//主机流水号
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private Double loanAmt;//放款金额
	private String xtAcNo;//信托账号
	private String loanAcNo;//放款账号
	private String loanAcType;//账户类型[11个人账户12企业账户]
	private String loanAcName;//账户户名
	private String loanBankCode;//开户银行代码
	private String loanBankProv;//开户银行所在省
	private String loanBankCity;//开户银行所在市
	private String loanBankSite;//开户银行网点
	private String loanSts;//放款状态[01-待处理,02-已发送,03-放款成功,04-放款失败]
	private String txDate;//登记日期
	private String txTime;//登记时间
	private String upDate;//更新时间
	private String cardChn;//支付渠道
	private String busEntryType;//条目交易类型
	private String idType;
	private String idNo;
	private String phoneNo;
	private String validDate;
	private String cvvNo;

	/**
	 * @return 放款流水号
	 */
	public String getLoanLogNo() {
	 	return loanLogNo;
	}
	/**
	 * @设置 放款流水号
	 * @param loanLogNo
	 */
	public void setLoanLogNo(String loanLogNo) {
	 	this.loanLogNo = loanLogNo;
	}
	/**
	 * @return 主机流水号
	 */
	public String getTraceNo() {
	 	return traceNo;
	}
	/**
	 * @设置 主机流水号
	 * @param traceNo
	 */
	public void setTraceNo(String traceNo) {
	 	this.traceNo = traceNo;
	}
	/**
	 * @return 借据号
	 */
	public String getLoanNo() {
	 	return loanNo;
	}
	/**
	 * @设置 借据号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
	 	this.loanNo = loanNo;
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
	 * @return 机构号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 放款金额
	 */
	public Double getLoanAmt() {
	 	return loanAmt;
	}
	/**
	 * @设置 放款金额
	 * @param loanAmt
	 */
	public void setLoanAmt(Double loanAmt) {
	 	this.loanAmt = loanAmt;
	}
	/**
	 * @return 信托账号
	 */
	public String getXtAcNo() {
	 	return xtAcNo;
	}
	/**
	 * @设置 信托账号
	 * @param xtAcNo
	 */
	public void setXtAcNo(String xtAcNo) {
	 	this.xtAcNo = xtAcNo;
	}
	/**
	 * @return 放款账号
	 */
	public String getLoanAcNo() {
	 	return loanAcNo;
	}
	/**
	 * @设置 放款账号
	 * @param loanAcNo
	 */
	public void setLoanAcNo(String loanAcNo) {
	 	this.loanAcNo = loanAcNo;
	}
	/**
	 * @return 账户类型[11个人账户12企业账户]
	 */
	public String getLoanAcType() {
	 	return loanAcType;
	}
	/**
	 * @设置 账户类型[11个人账户12企业账户]
	 * @param loanAcType
	 */
	public void setLoanAcType(String loanAcType) {
	 	this.loanAcType = loanAcType;
	}
	/**
	 * @return 账户户名
	 */
	public String getLoanAcName() {
	 	return loanAcName;
	}
	/**
	 * @设置 账户户名
	 * @param loanAcName
	 */
	public void setLoanAcName(String loanAcName) {
	 	this.loanAcName = loanAcName;
	}
	/**
	 * @return 开户银行代码
	 */
	public String getLoanBankCode() {
	 	return loanBankCode;
	}
	/**
	 * @设置 开户银行代码
	 * @param loanBankCode
	 */
	public void setLoanBankCode(String loanBankCode) {
	 	this.loanBankCode = loanBankCode;
	}
	/**
	 * @return 开户银行所在省
	 */
	public String getLoanBankProv() {
	 	return loanBankProv;
	}
	/**
	 * @设置 开户银行所在省
	 * @param loanBankProv
	 */
	public void setLoanBankProv(String loanBankProv) {
	 	this.loanBankProv = loanBankProv;
	}
	/**
	 * @return 开户银行所在市
	 */
	public String getLoanBankCity() {
	 	return loanBankCity;
	}
	/**
	 * @设置 开户银行所在市
	 * @param loanBankCity
	 */
	public void setLoanBankCity(String loanBankCity) {
	 	this.loanBankCity = loanBankCity;
	}
	/**
	 * @return 开户银行网点
	 */
	public String getLoanBankSite() {
	 	return loanBankSite;
	}
	/**
	 * @设置 开户银行网点
	 * @param loanBankSite
	 */
	public void setLoanBankSite(String loanBankSite) {
	 	this.loanBankSite = loanBankSite;
	}
	/**
	 * @return 放款状态[01-待处理,02-已发送,03-放款成功,04-放款失败]
	 */
	public String getLoanSts() {
	 	return loanSts;
	}
	/**
	 * @设置 放款状态[01-待处理,02-已发送,03-放款成功,04-放款失败]
	 * @param loanSts
	 */
	public void setLoanSts(String loanSts) {
	 	this.loanSts = loanSts;
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
	 * @return 登记时间
	 */
	public String getTxTime() {
	 	return txTime;
	}
	/**
	 * @设置 登记时间
	 * @param txTime
	 */
	public void setTxTime(String txTime) {
	 	this.txTime = txTime;
	}
	/**
	 * @return 更新时间
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 更新时间
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
	public String getCardChn() {
		return cardChn;
	}
	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
	public String getBusEntryType() {
		return busEntryType;
	}
	public void setBusEntryType(String busEntryType) {
		this.busEntryType = busEntryType;
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