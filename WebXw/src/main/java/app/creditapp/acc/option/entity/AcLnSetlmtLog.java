package app.creditapp.acc.option.entity;
import app.base.BaseDomain;
/**
* Title: AcLnSetlmtLog.java
* Description:
* @version：1.0
**/
public class AcLnSetlmtLog extends BaseDomain {
	private String traceNo;//交易流水号
	private Double remPrcpAmt;//提前还本金额
	private Double curInt;//归还当期利息
	private Double feeAmt;//费用
	private String txDt;//交易日期
	private String cancelInd;//撤销冲正标志
	private String cancelTraceNo;//撤销冲正流水号
	private String cancelDt;//冲正日期
	private String cancelUsrId;//冲正用户
	private String memo;//备注
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private Double recvAmt;//提前还款金额
	private String recvDt;//提前还款日期
	private Double prcpAmt;//归还欠本金额
	private Double normInt;//归还正常利息金额
	private Double fineInt;//归还罚息
	private Double damAmt;//归还提前还款违约金
	private String repayType;//还款类型[01-期数不变，缩短期供 02-期供不变，缩短期数]
	private String endDate;//到期日期
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
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
	 * @return 提前还本金额
	 */
	public Double getRemPrcpAmt() {
	 	return remPrcpAmt;
	}
	/**
	 * @设置 提前还本金额
	 * @param remPrcpAmt
	 */
	public void setRemPrcpAmt(Double remPrcpAmt) {
	 	this.remPrcpAmt = remPrcpAmt;
	}
	/**
	 * @return 归还当期利息
	 */
	public Double getCurInt() {
	 	return curInt;
	}
	/**
	 * @设置 归还当期利息
	 * @param curInt
	 */
	public void setCurInt(Double curInt) {
	 	this.curInt = curInt;
	}
	/**
	 * @return 费用
	 */
	public Double getFeeAmt() {
	 	return feeAmt;
	}
	/**
	 * @设置 费用
	 * @param feeAmt
	 */
	public void setFeeAmt(Double feeAmt) {
	 	this.feeAmt = feeAmt;
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
	/**
	 * @设置 撤销冲正标志
	 * @param cancelInd
	 */
	public void setCancelInd(String cancelInd) {
	 	this.cancelInd = cancelInd;
	}
	/**
	 * @return 撤销冲正流水号
	 */
	public String getCancelTraceNo() {
	 	return cancelTraceNo;
	}
	/**
	 * @设置 撤销冲正流水号
	 * @param cancelTraceNo
	 */
	public void setCancelTraceNo(String cancelTraceNo) {
	 	this.cancelTraceNo = cancelTraceNo;
	}
	/**
	 * @return 冲正日期
	 */
	public String getCancelDt() {
	 	return cancelDt;
	}
	/**
	 * @设置 冲正日期
	 * @param cancelDt
	 */
	public void setCancelDt(String cancelDt) {
	 	this.cancelDt = cancelDt;
	}
	/**
	 * @return 冲正用户
	 */
	public String getCancelUsrId() {
	 	return cancelUsrId;
	}
	/**
	 * @设置 冲正用户
	 * @param cancelUsrId
	 */
	public void setCancelUsrId(String cancelUsrId) {
	 	this.cancelUsrId = cancelUsrId;
	}
	/**
	 * @return 备注
	 */
	public String getMemo() {
	 	return memo;
	}
	/**
	 * @设置 备注
	 * @param memo
	 */
	public void setMemo(String memo) {
	 	this.memo = memo;
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
	 * @return 提前还款金额
	 */
	public Double getRecvAmt() {
	 	return recvAmt;
	}
	/**
	 * @设置 提前还款金额
	 * @param recvAmt
	 */
	public void setRecvAmt(Double recvAmt) {
	 	this.recvAmt = recvAmt;
	}
	/**
	 * @return 提前还款日期
	 */
	public String getRecvDt() {
	 	return recvDt;
	}
	/**
	 * @设置 提前还款日期
	 * @param recvDt
	 */
	public void setRecvDt(String recvDt) {
	 	this.recvDt = recvDt;
	}
	/**
	 * @return 归还欠本金额
	 */
	public Double getPrcpAmt() {
	 	return prcpAmt;
	}
	/**
	 * @设置 归还欠本金额
	 * @param prcpAmt
	 */
	public void setPrcpAmt(Double prcpAmt) {
	 	this.prcpAmt = prcpAmt;
	}
	/**
	 * @return 归还正常利息金额
	 */
	public Double getNormInt() {
	 	return normInt;
	}
	/**
	 * @设置 归还正常利息金额
	 * @param normInt
	 */
	public void setNormInt(Double normInt) {
	 	this.normInt = normInt;
	}
	/**
	 * @return 归还罚息
	 */
	public Double getFineInt() {
	 	return fineInt;
	}
	/**
	 * @设置 归还罚息
	 * @param fineInt
	 */
	public void setFineInt(Double fineInt) {
	 	this.fineInt = fineInt;
	}
	/**
	 * @return 归还提前还款违约金
	 */
	public Double getDamAmt() {
	 	return damAmt;
	}
	/**
	 * @设置 归还提前还款违约金
	 * @param damAmt
	 */
	public void setDamAmt(Double damAmt) {
	 	this.damAmt = damAmt;
	}
	public String getRepayType() {
		return repayType;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}