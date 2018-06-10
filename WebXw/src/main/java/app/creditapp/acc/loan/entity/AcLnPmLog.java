package app.creditapp.acc.loan.entity;
import app.base.BaseDomain;
/**
* Title: AcLnPmLog.java
* Description:
* @version：1.0
**/
public class AcLnPmLog extends BaseDomain {
	private String perdNo;//期号
	private String loanNo;//借据号
	private String traceNo;//交易流水号
	private Integer cancelTraceNo;//撤销冲正流水号
	private String cancelDt;//撤销冲正日期
	private String pactNo;//合同号
	private String brNo;//机构号
	private Double repayPrcpAmt;//还本金额
	private Double repayNormInt;//还利息金额
	private Double repayFineInt;//还罚息
	private Double repayFeeAmt;//还费用
	private String prcpSts;//本金状态
	private String intSts;//利息状态
	private String chrgSts;//费用状态
	private String txDt;//交易日期
	private String cancelInd;//撤销冲正标志
	private String chrgId;//费用表ID
	private String cifName;//客户名称
	private String brName;//合作机构名称
	private double dueAmt;//贷款金额
	private double bal;//贷款余额
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	/**
	 * @return 期号
	 */
	public String getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 期号
	 * @param perdNo
	 */
	public void setPerdNo(String perdNo) {
	 	this.perdNo = perdNo;
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
	 * @return 交易流水号
	 */
	public String getTraceNo() {
	 	return traceNo;
	}
	/**
	 * @设置 交易流水号
	 * @param traceNo
	 */
	public void setTraceNo(String traceNo) {
	 	this.traceNo = traceNo;
	}
	/**
	 * @return 撤销冲正流水号
	 */
	public Integer getCancelTraceNo() {
	 	return cancelTraceNo;
	}
	/**
	 * @设置 撤销冲正流水号
	 * @param cancelTraceNo
	 */
	public void setCancelTraceNo(Integer cancelTraceNo) {
	 	this.cancelTraceNo = cancelTraceNo;
	}
	/**
	 * @return 撤销冲正日期
	 */
	public String getCancelDt() {
	 	return cancelDt;
	}
	/**
	 * @设置 撤销冲正日期
	 * @param cancelDt
	 */
	public void setCancelDt(String cancelDt) {
	 	this.cancelDt = cancelDt;
	}
	/**
	 * @return 撤销冲正用户
	 */
	
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
	 * @return 还本金额
	 */
	public Double getRepayPrcpAmt() {
	 	return repayPrcpAmt;
	}
	/**
	 * @设置 还本金额
	 * @param repayPrcpAmt
	 */
	public void setRepayPrcpAmt(Double repayPrcpAmt) {
	 	this.repayPrcpAmt = repayPrcpAmt;
	}
	/**
	 * @return 还利息金额
	 */
	public Double getRepayNormInt() {
	 	return repayNormInt;
	}
	/**
	 * @设置 还利息金额
	 * @param repayNormInt
	 */
	public void setRepayNormInt(Double repayNormInt) {
	 	this.repayNormInt = repayNormInt;
	}
	/**
	 * @return 还罚息
	 */
	public Double getRepayFineInt() {
	 	return repayFineInt;
	}
	/**
	 * @设置 还罚息
	 * @param repayFineInt
	 */
	public void setRepayFineInt(Double repayFineInt) {
	 	this.repayFineInt = repayFineInt;
	}
	/**
	 * @return 还费用
	 */
	public Double getRepayFeeAmt() {
	 	return repayFeeAmt;
	}
	/**
	 * @设置 还费用
	 * @param repayFeeAmt
	 */
	public void setRepayFeeAmt(Double repayFeeAmt) {
	 	this.repayFeeAmt = repayFeeAmt;
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
	 * @return 费用状态
	 */
	public String getChrgSts() {
	 	return chrgSts;
	}
	/**
	 * @设置 费用状态
	 * @param chrgSts
	 */
	public void setChrgSts(String chrgSts) {
	 	this.chrgSts = chrgSts;
	}
	/**
	 * @return 交易日期
	 */
	public String getTxDt() {
	 	return txDt;
	}
	/**
	 * @设置 交易日期
	 * @param txDt
	 */
	public void setTxDt(String txDt) {
	 	this.txDt = txDt;
	}
	/**
	 * @return 撤销冲正标志
	 */
	public String getCancelInd() {
	 	return cancelInd;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public double getDueAmt() {
		return dueAmt;
	}
	public void setDueAmt(double dueAmt) {
		this.dueAmt = dueAmt;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	/**
	 * @设置 撤销冲正标志
	 * @param cancelInd
	 */
	public void setCancelInd(String cancelInd) {
	 	this.cancelInd = cancelInd;
	}
	public String getChrgId() {
		return chrgId;
	}
	public void setChrgId(String chrgId) {
		this.chrgId = chrgId;
	}

}