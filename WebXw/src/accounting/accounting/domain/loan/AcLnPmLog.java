package accounting.domain.loan;

/**
 * Title: AcLnPmLog.java Description:
 * 
* @作者 su
* @日期 2018-3-20
 * @version：1.0
 **/
public class AcLnPmLog extends accounting.domain.sys.CMISDomain {
	private String loanNo; // 借据号
	private String pactNo; //合同号
	private String brNo;   //机构号
	private String traceNo; // 交易流水号
	private String perdNo; // 期号
	private String txDt; // 交易日期
	private double repayPrcpAmt; // 本期还本金额
	private double repayNormInt; // 本期还利息金额
	private double repayFineInt;
	private double repayFeeAmt;//本期还费用
	private String prcpSts; // 本金状态
	private String intSts; // 利息状态
	private String chrgSts;//费用状态
	private String cancelInd; 		// 撤销冲正标志
	private long cancelTraceNo; 	// 撤销冲正交易号
	private String cancelDt; 		// 撤销冲正撤销交易日期
	private String chrgId;			//费用表ID
	private String comPst;			//正常，代偿

	
	public double getRepayFineInt() {
		return repayFineInt;
	}

	public void setRepayFineInt(double repayFineInt) {
		this.repayFineInt = repayFineInt;
	}


	/**
	 * @return 返回 借据号
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
	 * @return 返回 交易流水号
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
	 * @return 返回 期号
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
	 * @return 返回 交易日期
	 */
	public String getTxDt() {
		return txDt;
	}

	/**
	 * @设置 交易日期
	 * @param txDate
	 */
	public void setTxDt(String txDt) {
		this.txDt = txDt;
	}

	/**
	 * @return repayPrcpAmt
	 */
	public double getRepayPrcpAmt() {
		return repayPrcpAmt;
	}

	/**
	 * @param repayPrcpAmt
	 */
	public void setRepayPrcpAmt(double repayPrcpAmt) {
		this.repayPrcpAmt = repayPrcpAmt;
	}

	/**
	 * @return repayNormIntst
	 */
	public double getRepayNormInt() {
		return repayNormInt;
	}

	/**
	 * @param repayNormIntst
	 */
	public void setRepayNormInt(double repayNormInt) {
		this.repayNormInt = repayNormInt;
	}


	/**
	 * @return the revseRolInd
	 */
	public String getCancelInd() {
		return cancelInd;
	}

	/**
	 * @param revseRolInd
	 *            the revseRolInd to set
	 */
	public void setCancelInd(String cancelInd) {
		this.cancelInd = cancelInd;
	}

	/**
	 * @return 返回 本金状态
	 */
	public String getPrcpSts() {
		return prcpSts;
	}

	/**
	 * @设置 本金状态
	 * @param prcpState
	 */
	public void setPrcpSts(String prcpSts) {
		this.prcpSts = prcpSts;
	}

	/**
	 * @return 返回 利息状态
	 */
	public String getIntSts() {
		return intSts;
	}

	/**
	 * @设置 利息状态
	 * @param intstState
	 */
	public void setIntSts(String intSts) {
		this.intSts = intSts;
	}

	/**
	 * @return the cancelTraceNo
	 */
	public long getCancelTraceNo() {
		return cancelTraceNo;
	}

	/**
	 * @param cancelTraceNo
	 *            the cancelTraceNo to set
	 */
	public void setCancelTraceNo(long cancelTraceNo) {
		this.cancelTraceNo = cancelTraceNo;
	}

	/**
	 * @return 返回 撤销冲正日期
	 */
	public String getCancelDt() {
		return cancelDt;
	}
	/**
	 * @设置 撤销冲正日期
	 * @param usrId
	 */
	public void setCancelDt(String cancelDt) {
		this.cancelDt = cancelDt;
	}

	public double getRepayFeeAmt() {
		return repayFeeAmt;
	}

	public void setRepayFeeAmt(double repayFeeAmt) {
		this.repayFeeAmt = repayFeeAmt;
	}

	public String getPactNo() {
		return pactNo;
	}

	public String getBrNo() {
		return brNo;
	}

	public String getChrgSts() {
		return chrgSts;
	}

	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public void setChrgSts(String chrgSts) {
		this.chrgSts = chrgSts;
	}

	public String getChrgId() {
		return chrgId;
	}

	public void setChrgId(String chrgId) {
		this.chrgId = chrgId;
	}

	public String getComPst() {
		return comPst;
	}

	public void setComPst(String comPst) {
		this.comPst = comPst;
	}

}