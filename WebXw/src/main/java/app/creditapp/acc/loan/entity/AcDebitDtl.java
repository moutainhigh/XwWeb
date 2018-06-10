package app.creditapp.acc.loan.entity;
import app.base.BaseDomain;
/**
* Title: AcDebitDtl.java
* Description:
* @version：1.0
**/
public class AcDebitDtl extends BaseDomain {
	private String traceNo;//主机流水号
	private String ddtlNo;//扣款明细流水号
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private String perdNo;//期数
	private Double ddtlPrcpAmt;//实扣本金
	private Double ddtlNormInt;//实扣利息
	private Double ddtlFineInt;//实扣罚息
	private Double ddtlFeeAmt;//实扣费用总计
	private Double ddtlAmt;//实扣总金额
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
	 * @return 扣款明细流水号
	 */
	public String getDdtlNo() {
	 	return ddtlNo;
	}
	/**
	 * @设置 扣款明细流水号
	 * @param ddtlNo
	 */
	public void setDdtlNo(String ddtlNo) {
	 	this.ddtlNo = ddtlNo;
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
	 * @return 期数
	 */
	public String getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 期数
	 * @param perdNo
	 */
	public void setPerdNo(String perdNo) {
	 	this.perdNo = perdNo;
	}
	/**
	 * @return 实扣本金
	 */
	public Double getDdtlPrcpAmt() {
	 	return ddtlPrcpAmt;
	}
	/**
	 * @设置 实扣本金
	 * @param ddtlPrcpAmt
	 */
	public void setDdtlPrcpAmt(Double ddtlPrcpAmt) {
	 	this.ddtlPrcpAmt = ddtlPrcpAmt;
	}
	/**
	 * @return 实扣利息
	 */
	public Double getDdtlNormInt() {
	 	return ddtlNormInt;
	}
	/**
	 * @设置 实扣利息
	 * @param ddtlNormInt
	 */
	public void setDdtlNormInt(Double ddtlNormInt) {
	 	this.ddtlNormInt = ddtlNormInt;
	}
	/**
	 * @return 实扣罚息
	 */
	public Double getDdtlFineInt() {
	 	return ddtlFineInt;
	}
	/**
	 * @设置 实扣罚息
	 * @param ddtlFineInt
	 */
	public void setDdtlFineInt(Double ddtlFineInt) {
	 	this.ddtlFineInt = ddtlFineInt;
	}
	/**
	 * @return 实扣费用总计
	 */
	public Double getDdtlFeeAmt() {
	 	return ddtlFeeAmt;
	}
	/**
	 * @设置 实扣费用总计
	 * @param ddtlFeeAmt
	 */
	public void setDdtlFeeAmt(Double ddtlFeeAmt) {
	 	this.ddtlFeeAmt = ddtlFeeAmt;
	}
	/**
	 * @return 实扣总金额
	 */
	public Double getDdtlAmt() {
	 	return ddtlAmt;
	}
	/**
	 * @设置 实扣总金额
	 * @param ddtlAmt
	 */
	public void setDdtlAmt(Double ddtlAmt) {
	 	this.ddtlAmt = ddtlAmt;
	}
	/**
	 * @return 扣款日
	 */
	public String getDdtlDate() {
	 	return ddtlDate;
	}
	/**
	 * @设置 扣款日
	 * @param ddtlDate
	 */
	public void setDdtlDate(String ddtlDate) {
	 	this.ddtlDate = ddtlDate;
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
	 * @return 扣款账号
	 */
	public String getDdtlAcNo() {
	 	return ddtlAcNo;
	}
	/**
	 * @设置 扣款账号
	 * @param ddtlAcNo
	 */
	public void setDdtlAcNo(String ddtlAcNo) {
	 	this.ddtlAcNo = ddtlAcNo;
	}
	/**
	 * @return 账户户名
	 */
	public String getDdtlAcName() {
	 	return ddtlAcName;
	}
	/**
	 * @设置 账户户名
	 * @param ddtlAcName
	 */
	public void setDdtlAcName(String ddtlAcName) {
	 	this.ddtlAcName = ddtlAcName;
	}
	/**
	 * @return 开户银行代码
	 */
	public String getDdtlBankCode() {
	 	return ddtlBankCode;
	}
	/**
	 * @设置 开户银行代码
	 * @param ddtlBankCode
	 */
	public void setDdtlBankCode(String ddtlBankCode) {
	 	this.ddtlBankCode = ddtlBankCode;
	}
	/**
	 * @return 开户银行所在省
	 */
	public String getDdtlBankProv() {
	 	return ddtlBankProv;
	}
	/**
	 * @设置 开户银行所在省
	 * @param ddtlBankProv
	 */
	public void setDdtlBankProv(String ddtlBankProv) {
	 	this.ddtlBankProv = ddtlBankProv;
	}
	/**
	 * @return 开户银行所在市
	 */
	public String getDdtlBankCity() {
	 	return ddtlBankCity;
	}
	/**
	 * @设置 开户银行所在市
	 * @param ddtlBankCity
	 */
	public void setDdtlBankCity(String ddtlBankCity) {
	 	this.ddtlBankCity = ddtlBankCity;
	}
	/**
	 * @return 开户银行网点
	 */
	public String getDdtlBankSite() {
	 	return ddtlBankSite;
	}
	/**
	 * @设置 开户银行网点
	 * @param ddtlBankSite
	 */
	public void setDdtlBankSite(String ddtlBankSite) {
	 	this.ddtlBankSite = ddtlBankSite;
	}
	/**
	 * @return 扣款状态
	 */
	public String getDdtlSts() {
	 	return ddtlSts;
	}
	/**
	 * @设置 扣款状态
	 * @param ddtlSts
	 */
	public void setDdtlSts(String ddtlSts) {
	 	this.ddtlSts = ddtlSts;
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
}