package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AcLoanLog.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcLoanLog extends accounting.domain.sys.CMISDomain {
	private String loanLogNo;//放款流水号
	private String traceNo;//主机流水号
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private double loanAmt;//放款金额
	private String xtAcNo;//信托账号
	private String loanAcNo;//放款账号
	private String loanAcType;//账户类型[11个人账户12企业账户]
	private String loanAcName;//账户户名
	private String loanBankCode;//开户银行代码
	private String loanBankProv;//开户银行所在省
	private String loanBankCity;//开户银行所在市
	private String loanBankSite;//开户银行网点
	private String loanSts;//放款状态[01-已发送,02-放款成功,03-放款失败]
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
	
	public String getLoanLogNo() {
		return loanLogNo;
	}
	public void setLoanLogNo(String loanLogNo) {
		this.loanLogNo = loanLogNo;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public double getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanSts() {
		return loanSts;
	}
	public void setLoanSts(String loanSts) {
		this.loanSts = loanSts;
	}
	public String getXtAcNo() {
		return xtAcNo;
	}
	public void setXtAcNo(String xtAcNo) {
		this.xtAcNo = xtAcNo;
	}
	public String getLoanAcNo() {
		return loanAcNo;
	}
	public void setLoanAcNo(String loanAcNo) {
		this.loanAcNo = loanAcNo;
	}
	public String getLoanAcType() {
		return loanAcType;
	}
	public void setLoanAcType(String loanAcType) {
		this.loanAcType = loanAcType;
	}
	public String getLoanBankCode() {
		return loanBankCode;
	}
	public void setLoanBankCode(String loanBankCode) {
		this.loanBankCode = loanBankCode;
	}
	public String getLoanBankProv() {
		return loanBankProv;
	}
	public void setLoanBankProv(String loanBankProv) {
		this.loanBankProv = loanBankProv;
	}
	public String getLoanBankCity() {
		return loanBankCity;
	}
	public void setLoanBankCity(String loanBankCity) {
		this.loanBankCity = loanBankCity;
	}
	public String getLoanBankSite() {
		return loanBankSite;
	}
	public void setLoanBankSite(String loanBankSite) {
		this.loanBankSite = loanBankSite;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getTxTime() {
		return txTime;
	}
	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	public String getUpDate() {
		return upDate;
	}
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
	public String getLoanAcName() {
		return loanAcName;
	}
	public void setLoanAcName(String loanAcName) {
		this.loanAcName = loanAcName;
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