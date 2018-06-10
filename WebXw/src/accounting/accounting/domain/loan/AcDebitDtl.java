package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AcDebitDtl.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcDebitDtl extends accounting.domain.sys.CMISDomain {
	private String traceNo;//主机流水号
	private String ddtlNo;//扣款明细流水号
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private String perdNo;//期次号
	private double ddtlPrcpAmt;//实扣本金
	private double ddtlNormInt;//实扣利息
	private double ddtlFineInt;//实扣罚息
	private double ddtlFeeAmt;//实扣费用总计
	private double ddtlAmt;//实扣总金额
	private String ddtlDate;//扣款日
	private String xtAcNo;//信托账号
	private String ddtlAcNo;//扣款账号
	private String ddtlAcName;//账户户名
	private String ddtlBankCode;//开户银行代码
	private String ddtlBankProv;//开户银行所在省
	private String ddtlBankCity;//开户银行所在市
	private String ddtlBankSite;//开户银行网点
	private String ddtlSts;//扣款状态
	private String txDate;//登记日期
	private String txTime;//登记时间
	private String upDate;//更新时间
	
	public String getTraceNo() {
		return traceNo;
	}
	public String getDdtlNo() {
		return ddtlNo;
	}
	public String getLoanNo() {
		return loanNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public double getDdtlPrcpAmt() {
		return ddtlPrcpAmt;
	}
	public double getDdtlNormInt() {
		return ddtlNormInt;
	}
	public double getDdtlFineInt() {
		return ddtlFineInt;
	}
	public double getDdtlFeeAmt() {
		return ddtlFeeAmt;
	}
	public double getDdtlAmt() {
		return ddtlAmt;
	}
	public String getXtAcNo() {
		return xtAcNo;
	}
	public String getDdtlAcNo() {
		return ddtlAcNo;
	}
	public String getDdtlAcName() {
		return ddtlAcName;
	}
	public String getDdtlBankCode() {
		return ddtlBankCode;
	}
	public String getDdtlBankProv() {
		return ddtlBankProv;
	}
	public String getDdtlBankCity() {
		return ddtlBankCity;
	}
	public String getDdtlBankSite() {
		return ddtlBankSite;
	}
	public String getDdtlSts() {
		return ddtlSts;
	}
	public String getTxDate() {
		return txDate;
	}
	public String getTxTime() {
		return txTime;
	}
	public String getUpDate() {
		return upDate;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public void setDdtlNo(String ddtlNo) {
		this.ddtlNo = ddtlNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public void setDdtlPrcpAmt(double ddtlPrcpAmt) {
		this.ddtlPrcpAmt = ddtlPrcpAmt;
	}
	public void setDdtlNormInt(double ddtlNormInt) {
		this.ddtlNormInt = ddtlNormInt;
	}
	public void setDdtlFineInt(double ddtlFineInt) {
		this.ddtlFineInt = ddtlFineInt;
	}
	public void setDdtlFeeAmt(double ddtlFeeAmt) {
		this.ddtlFeeAmt = ddtlFeeAmt;
	}
	public void setDdtlAmt(double ddtlAmt) {
		this.ddtlAmt = ddtlAmt;
	}
	public void setXtAcNo(String xtAcNo) {
		this.xtAcNo = xtAcNo;
	}
	public void setDdtlAcNo(String ddtlAcNo) {
		this.ddtlAcNo = ddtlAcNo;
	}
	public void setDdtlAcName(String ddtlAcName) {
		this.ddtlAcName = ddtlAcName;
	}
	public void setDdtlBankCode(String ddtlBankCode) {
		this.ddtlBankCode = ddtlBankCode;
	}
	public void setDdtlBankProv(String ddtlBankProv) {
		this.ddtlBankProv = ddtlBankProv;
	}
	public void setDdtlBankCity(String ddtlBankCity) {
		this.ddtlBankCity = ddtlBankCity;
	}
	public void setDdtlBankSite(String ddtlBankSite) {
		this.ddtlBankSite = ddtlBankSite;
	}
	public void setDdtlSts(String ddtlSts) {
		this.ddtlSts = ddtlSts;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
	public String getDdtlDate() {
		return ddtlDate;
	}
	public void setDdtlDate(String ddtlDate) {
		this.ddtlDate = ddtlDate;
	}
	public String getPerdNo() {
		return perdNo;
	}
	public void setPerdNo(String perdNo) {
		this.perdNo = perdNo;
	}
}