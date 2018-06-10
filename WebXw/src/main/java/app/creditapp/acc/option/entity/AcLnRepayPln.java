package app.creditapp.acc.option.entity;
import accounting.plat.util.NumberUtil;
import app.base.BaseDomain;
/**
* Title: AcLnRepayPln.java
* @version：1.0
**/
public class AcLnRepayPln extends BaseDomain {
	private String pactNo;//合同号
	private String brNo;//机构号
	private String brName;//机构号
	private String loanNo;//借据号
	private Integer perdNo;//期号
	private String payDt;//应还款日期
	private Double instmAmt;//期供金额
	private Double prcpAmt;//本金
	private Double normInt;//正常利息
	private Double fineInt;//罚息
	private Double bal;//本金余额
	private Double repayPrcpAmt;//已还本金
	private Double repayNormInt;//已还正常利息
	private Double repayFineInt;//已还罚息
	private Double lnRate;//执行利率
	private Double overRate;//逾期利率
	private String overInd;//逾期标志
	private String lstPayDt;//最近还款日
	private Double wvPrcpAmt;//减免本金
	private Double wvNormInt;//减免正常利息
	private Double wvFineInt;//减免罚息
	private String prcpSts;//本金状态
	private String intSts;//利息状态
	private String setlSts;//结清标志
	private String fineIntDt;//上次结罚息日期
	private String ppErInd;//当期还款记录是否为主动还款
	private String ifAdj;//本期是否为手动调整

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
	 * @return 期号
	 */
	public Integer getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 期号
	 * @param perdNo
	 */
	public void setPerdNo(Integer perdNo) {
	 	this.perdNo = perdNo;
	}
	/**
	 * @return 应还款日期
	 */
	public String getPayDt() {
	 	return payDt;
	}
	/**
	 * @设置 应还款日期
	 * @param payDt
	 */
	public void setPayDt(String payDt) {
	 	this.payDt = payDt;
	}
	/**
	 * @return 期供金额
	 */
	public Double getInstmAmt() {
	 	return instmAmt;
	}
	/**
	 * @设置 期供金额
	 * @param instmAmt
	 */
	public void setInstmAmt(Double instmAmt) {
	 	this.instmAmt = instmAmt;
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
	 * @return 正常利息
	 */
	public Double getNormInt() {
	 	return normInt;
	}
	/**
	 * @设置 正常利息
	 * @param normInt
	 */
	public void setNormInt(Double normInt) {
	 	this.normInt = normInt;
	}
	/**
	 * @return 罚息
	 */
	public Double getFineInt() {
	 	return fineInt;
	}
	/**
	 * @设置 罚息
	 * @param fineInt
	 */
	public void setFineInt(Double fineInt) {
	 	this.fineInt = fineInt;
	}
	/**
	 * @return 本金余额
	 */
	public Double getBal() {
	 	return bal;
	}
	/**
	 * @设置 本金余额
	 * @param bal
	 */
	public void setBal(Double bal) {
	 	this.bal = bal;
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
	 * @return 已还正常利息
	 */
	public Double getRepayNormInt() {
	 	return repayNormInt;
	}
	/**
	 * @设置 已还正常利息
	 * @param repayNormInt
	 */
	public void setRepayNormInt(Double repayNormInt) {
	 	this.repayNormInt = repayNormInt;
	}
	/**
	 * @return 已还罚息
	 */
	public Double getRepayFineInt() {
	 	return repayFineInt;
	}
	/**
	 * @设置 已还罚息
	 * @param repayFineInt
	 */
	public void setRepayFineInt(Double repayFineInt) {
	 	this.repayFineInt = repayFineInt;
	}
	/**
	 * @return 执行利率
	 */
	public Double getLnRate() {
	 	return lnRate;
	}
	/**
	 * @设置 执行利率
	 * @param lnRate
	 */
	public void setLnRate(Double lnRate) {
	 	this.lnRate = lnRate;
	}
	/**
	 * @return 逾期利率
	 */
	public Double getOverRate() {
	 	return overRate;
	}
	/**
	 * @设置 逾期利率
	 * @param overRate
	 */
	public void setOverRate(Double overRate) {
	 	this.overRate = overRate;
	}
	/**
	 * @return 逾期标志
	 */
	public String getOverInd() {
	 	return overInd;
	}
	/**
	 * @设置 逾期标志
	 * @param overInd
	 */
	public void setOverInd(String overInd) {
	 	this.overInd = overInd;
	}
	/**
	 * @return 最近还款日
	 */
	public String getLstPayDt() {
	 	return lstPayDt;
	}
	/**
	 * @设置 最近还款日
	 * @param lstPayDt
	 */
	public void setLstPayDt(String lstPayDt) {
	 	this.lstPayDt = lstPayDt;
	}
	/**
	 * @return 减免本金
	 */
	public Double getWvPrcpAmt() {
	 	return wvPrcpAmt;
	}
	/**
	 * @设置 减免本金
	 * @param wvPrcpAmt
	 */
	public void setWvPrcpAmt(Double wvPrcpAmt) {
	 	this.wvPrcpAmt = wvPrcpAmt;
	}
	/**
	 * @return 减免正常利息
	 */
	public Double getWvNormInt() {
	 	return wvNormInt;
	}
	/**
	 * @设置 减免正常利息
	 * @param wvNormInt
	 */
	public void setWvNormInt(Double wvNormInt) {
	 	this.wvNormInt = wvNormInt;
	}
	/**
	 * @return 减免罚息
	 */
	public Double getWvFineInt() {
	 	return wvFineInt;
	}
	/**
	 * @设置 减免罚息
	 * @param wvFineInt
	 */
	public void setWvFineInt(Double wvFineInt) {
	 	this.wvFineInt = wvFineInt;
	}
	/**
	 * @return 本金状态
	 */
	public String getPrcpSts() {
	 	return prcpSts;
	}
	/**
	 * @设置 本金状态
	 * @param prcpSts
	 */
	public void setPrcpSts(String prcpSts) {
	 	this.prcpSts = prcpSts;
	}
	/**
	 * @return 利息状态
	 */
	public String getIntSts() {
	 	return intSts;
	}
	/**
	 * @设置 利息状态
	 * @param intSts
	 */
	public void setIntSts(String intSts) {
	 	this.intSts = intSts;
	}
	/**
	 * @return 结清标志
	 */
	public String getSetlSts() {
	 	return setlSts;
	}
	/**
	 * @设置 结清标志
	 * @param setlSts
	 */
	public void setSetlSts(String setlSts) {
	 	this.setlSts = setlSts;
	}
	/**
	 * @return 上次结罚息日期
	 */
	public String getFineIntDt() {
	 	return fineIntDt;
	}
	/**
	 * @设置 上次结罚息日期
	 * @param fineIntDt
	 */
	public void setFineIntDt(String fineIntDt) {
	 	this.fineIntDt = fineIntDt;
	}
	/**
	 * @return 当期还款记录是否为主动还款
	 */
	public String getPpErInd() {
	 	return ppErInd;
	}
	/**
	 * @设置 当期还款记录是否为主动还款
	 * @param ppErInd
	 */
	public void setPpErInd(String ppErInd) {
	 	this.ppErInd = ppErInd;
	}
	/**
	 * @return 本期是否为手动调整
	 */
	public String getIfAdj() {
	 	return ifAdj;
	}
	/**
	 * @设置 本期是否为手动调整
	 * @param ifAdj
	 */
	public void setIfAdj(String ifAdj) {
	 	this.ifAdj = ifAdj;
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
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public Double getAmt(){
		return NumberUtil.amtAdd(NumberUtil.amtAdd(this.prcpAmt, this.normInt), this.fineInt);
	}
}