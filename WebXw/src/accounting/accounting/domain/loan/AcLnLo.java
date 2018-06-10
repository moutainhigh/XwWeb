package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AcLnLo.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcLnLo extends accounting.domain.sys.CMISDomain {
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private Integer perdNo;//欠款期次
	private String payDt;//应还日期
	private Double prcpAmt;//欠款本金
	private Double normInt;//欠利息
	private Double fineInt;//欠罚息
	private Double overRate;//逾期利率
	private Double repayPrcpAmt;//已归还本金金额
	private Double repayNormInt;//已归还正常利息
	private Double repayFineInt;//已归还罚息金额
	private Double wvPrcpAmt;//减免本金
	private Double wvNormInt;//减免正常利息
	private Double wvFineInt;//减免罚息
	private String prcpSts;//本金状态
	private String intSts;//利息状态
	private String setlSts;//是否还清标志
	private String overDt;//转逾日期
	private String fineIntDt;//上次结罚息日期
	private String mac;//mac
	private String nextIntDt;//下一次结息日期
	private String setlDt;//结清日期

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
	 * @return 欠款期次
	 */
	public Integer getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 欠款期次
	 * @param perdNo
	 */
	public void setPerdNo(Integer perdNo) {
	 	this.perdNo = perdNo;
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
	 * @return 欠款本金
	 */
	public Double getPrcpAmt() {
	 	return prcpAmt;
	}
	/**
	 * @设置 欠款本金
	 * @param prcpAmt
	 */
	public void setPrcpAmt(Double prcpAmt) {
	 	this.prcpAmt = prcpAmt;
	}
	/**
	 * @return 欠利息
	 */
	public Double getNormInt() {
	 	return normInt;
	}
	/**
	 * @设置 欠利息
	 * @param normInt
	 */
	public void setNormInt(Double normInt) {
	 	this.normInt = normInt;
	}
	/**
	 * @return 欠罚息
	 */
	public Double getFineInt() {
	 	return fineInt;
	}
	/**
	 * @设置 欠罚息
	 * @param fineInt
	 */
	public void setFineInt(Double fineInt) {
	 	this.fineInt = fineInt;
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
	 * @return 已归还本金金额
	 */
	public Double getRepayPrcpAmt() {
	 	return repayPrcpAmt;
	}
	/**
	 * @设置 已归还本金金额
	 * @param repayPrcpAmt
	 */
	public void setRepayPrcpAmt(Double repayPrcpAmt) {
	 	this.repayPrcpAmt = repayPrcpAmt;
	}
	/**
	 * @return 已归还正常利息
	 */
	public Double getRepayNormInt() {
	 	return repayNormInt;
	}
	/**
	 * @设置 已归还正常利息
	 * @param repayNormInt
	 */
	public void setRepayNormInt(Double repayNormInt) {
	 	this.repayNormInt = repayNormInt;
	}
	/**
	 * @return 已归还罚息金额
	 */
	public Double getRepayFineInt() {
	 	return repayFineInt;
	}
	/**
	 * @设置 已归还罚息金额
	 * @param repayFineInt
	 */
	public void setRepayFineInt(Double repayFineInt) {
	 	this.repayFineInt = repayFineInt;
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
	 * @return 是否还清标志
	 */
	public String getSetlSts() {
	 	return setlSts;
	}
	/**
	 * @设置 是否还清标志
	 * @param setlSts
	 */
	public void setSetlSts(String setlSts) {
	 	this.setlSts = setlSts;
	}
	/**
	 * @return 转逾日期
	 */
	public String getOverDt() {
	 	return overDt;
	}
	/**
	 * @设置 转逾日期
	 * @param overDt
	 */
	public void setOverDt(String overDt) {
	 	this.overDt = overDt;
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
	 * @return mac
	 */
	public String getMac() {
	 	return mac;
	}
	/**
	 * @设置 mac
	 * @param mac
	 */
	public void setMac(String mac) {
	 	this.mac = mac;
	}
	/**
	 * @return 下一次结息日期
	 */
	public String getNextIntDt() {
	 	return nextIntDt;
	}
	/**
	 * @设置 下一次结息日期
	 * @param nextIntDt
	 */
	public void setNextIntDt(String nextIntDt) {
	 	this.nextIntDt = nextIntDt;
	}
	/**
	 * @return 结清日期
	 */
	public String getSetlDt() {
	 	return setlDt;
	}
	/**
	 * @设置 结清日期
	 * @param setlDt
	 */
	public void setSetlDt(String setlDt) {
	 	this.setlDt = setlDt;
	}
}