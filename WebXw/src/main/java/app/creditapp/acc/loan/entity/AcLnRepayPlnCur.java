package app.creditapp.acc.loan.entity;
import accounting.plat.util.NumberUtil;
import app.base.BaseDomain;
/**
* Title: AcLnRepayPlnCur.java
* Description:
* @version：1.0
**/
public class AcLnRepayPlnCur extends BaseDomain {
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private Double ttlAmt;//总金额
	private Integer perdNo;//当前期数
	private Double prcpAmt;//本金
	private Double normInt;//利息
	private String wrkDt;//登记日期
	private String payDt;//应还日期
	private String endDt;//到期日期
	private Double repayPrcpAmt;//已还本金
	private Double repayNormInt;//已还利息
	private Double wvPrcpAmt;//减免本金
	private Double wvNormInt;//减免利息

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
	 * @return 总金额
	 */
	public Double getTtlAmt() {
	 	return ttlAmt;
	}
	/**
	 * @设置 总金额
	 * @param ttlAmt
	 */
	public void setTtlAmt(Double ttlAmt) {
	 	this.ttlAmt = ttlAmt;
	}
	/**
	 * @return 当前期数
	 */
	public Integer getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 当前期数
	 * @param perdNo
	 */
	public void setPerdNo(Integer perdNo) {
	 	this.perdNo = perdNo;
	}
	/**
	 * @return 本金
	 */
	public Double getPrcpAmt() {
	 	return prcpAmt;
	}
	/**
	 * @设置 本金
	 * @param prcpAmt
	 */
	public void setPrcpAmt(Double prcpAmt) {
	 	this.prcpAmt = prcpAmt;
	}
	/**
	 * @return 利息
	 */
	public Double getNormInt() {
	 	return normInt;
	}
	/**
	 * @设置 利息
	 * @param normInt
	 */
	public void setNormInt(Double normInt) {
	 	this.normInt = normInt;
	}
	/**
	 * @return 登记日期
	 */
	public String getWrkDt() {
	 	return wrkDt;
	}
	/**
	 * @设置 登记日期
	 * @param wrkDt
	 */
	public void setWrkDt(String wrkDt) {
	 	this.wrkDt = wrkDt;
	}
	/**
	 * @return 应还日期
	 */
	public String getPayDt() {
	 	return payDt;
	}
	/**
	 * @设置 应还日期
	 * @param payDt
	 */
	public void setPayDt(String payDt) {
	 	this.payDt = payDt;
	}
	/**
	 * @return 到期日期
	 */
	public String getEndDt() {
	 	return endDt;
	}
	/**
	 * @设置 到期日期
	 * @param endDt
	 */
	public void setEndDt(String endDt) {
	 	this.endDt = endDt;
	}
	/**
	 * @return 已还本金
	 */
	public Double getRepayPrcpAmt() {
	 	return repayPrcpAmt;
	}
	/**
	 * @设置 已还本金
	 * @param repayPrcpAmt
	 */
	public void setRepayPrcpAmt(Double repayPrcpAmt) {
	 	this.repayPrcpAmt = repayPrcpAmt;
	}
	/**
	 * @return 已还利息
	 */
	public Double getRepayNormInt() {
	 	return repayNormInt;
	}
	/**
	 * @设置 已还利息
	 * @param repayNormInt
	 */
	public void setRepayNormInt(Double repayNormInt) {
	 	this.repayNormInt = repayNormInt;
	}
	public Double getWvPrcpAmt() {
		return wvPrcpAmt;
	}
	public void setWvPrcpAmt(Double wvPrcpAmt) {
		this.wvPrcpAmt = wvPrcpAmt;
	}
	public Double getWvNormInt() {
		return wvNormInt;
	}
	public void setWvNormInt(Double wvNormInt) {
		this.wvNormInt = wvNormInt;
	}
	public Double getAmt(){
		return NumberUtil.amtAdd(this.prcpAmt, this.normInt);
	}
}