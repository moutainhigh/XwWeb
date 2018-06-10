package accounting.domain.loan;

/**
 * Title: AcLnSetlmtLog.java Description:
 * 
* @作者 su
* @日期 2018-3-20
 * @version：1.0
 **/
public class AcLnSetlmtLog extends accounting.domain.sys.CMISDomain {
	private Integer acId; 		// 账户ID
	private Integer acSeq; 		// 账户序号
	private String loanNo; 		// 借据号
	private String traceNo; 	// 交易流水号
	private long dcNo; 			// 会计分录流水号
	private String txDt; 		// 交易日期
	private String loanTyp; 	// 处理业务类型
	private String setlMode; 	// 还款模式
	private String setlTyp; 	// 还款类型
	private double recvAmt; 	// 收到金额
	private double prcpAmt; 	// 归还欠本金额
	private double normInt; 	// 归还正常利息金额
	private double overInt; 	// 归还逾期利息金额
	private double cmpdInt; 	// 归还复利金额
	private double remPrcpAmt; 	// 提前还本金额
	private double curInt; 		// 归还当期利息金额
	private String cancelInd;	// 冲正撤销标志
	private String cancelTraceNo;	// 冲正撤销流水号
	private String cancelDt; 	// 冲正撤销交易日期
	private String cancelUsrId;	// 冲正撤销用户
	private String fdrpIntstOpt; 	// 利息计算到
	private String fdrpIntstBase;	// 利息计算基础
	private String memo; 		// 备注
	private double feeAmtCredit;// 费用(外部)
	private double feeAmtAcc ;	// 费用(核算)
	private String repayAdvOpt ;// 调整选项
	
	private double damageAmt;    // 违约金  
	private double compenAmt;	// 补偿金
 
	

	/**
	 * @return 返回 账户ID
	 */
	public Integer getAcId() {
		return acId;
	}

	/**
	 * @设置 账户ID
	 * @param acId
	 */
	public void setAcId(Integer acId) {
		this.acId = acId;
	}

	/**
	 * @return 返回 账户序号
	 */
	public Integer getAcSeq() {
		return acSeq;
	}

	/**
	 * @设置 账户序号
	 * @param acSeq
	 */
	public void setAcSeq(Integer acSeq) {
		this.acSeq = acSeq;
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
	 * @return 会计分录流水号
	 */
	public long getDcNo() {
		return dcNo;
	}

	/**
	 * @param 会计分录流水号
	 */
	public void setDcNo(long dcNo) {
		this.dcNo = dcNo;
	}

	/**
	 * @return 返回 交易日期
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
	 * @return 返回 处理业务类型
	 */
	public String getLoanTyp() {
		return loanTyp;
	}

	/**
	 * @设置 处理业务类型
	 * @param loanTyp
	 */
	public void setLoanTyp(String loanTyp) {
		this.loanTyp = loanTyp;
	}

	/**
	 * @return 返回 还款模式
	 */
	public String getSetlMode() {
		return setlMode;
	}

	/**
	 * @设置 还款模式
	 * @param setlMode
	 */
	public void setSetlMode(String setlMode) {
		this.setlMode = setlMode;
	}

	/**
	 * @return 返回 还款类型
	 */
	public String getSetlTyp() {
		return setlTyp;
	}

	/**
	 * @设置 还款类型
	 * @param setlTyp
	 */
	public void setSetlTyp(String setlTyp) {
		this.setlTyp = setlTyp;
	}

	/**
	 * @return 返回 收到金额
	 */
	public double getRecvAmt() {
		return recvAmt;
	}

	/**
	 * @设置 收到金额
	 * @param recvAmt
	 */
	public void setRecvAmt(double recvAmt) {
		this.recvAmt = recvAmt;
	}

	/**
	 * @return 返回 归还欠本金额
	 */
	public double getPrcpAmt() {
		return prcpAmt;
	}

	/**
	 * @设置 归还欠本金额
	 * @param prcpAmt
	 */
	public void setPrcpAmt(double prcpAmt) {
		this.prcpAmt = prcpAmt;
	}

	/**
	 * @return 返回 归还正常利息金额
	 */
	public double getNormInt() {
		return normInt;
	}

	/**
	 * @设置 归还正常利息金额
	 * @param normIntst
	 */
	public void setNormInt(double normInt) {
		this.normInt = normInt;
	}

	/**
	 * @return 返回 归还逾期利息金额
	 */
	public double getOverInt() {
		return overInt;
	}

	/**
	 * @设置 归还逾期利息金额
	 * @param overIntst
	 */
	public void setOverInt(double overInt) {
		this.overInt = overInt;
	}

	/**
	 * @return 返回 归还复利金额
	 */
	public double getCmpdInt() {
		return cmpdInt;
	}

	/**
	 * @设置 归还复利金额
	 * @param cmpdIntst
	 */
	public void setCmpdInt(double cmpdInt) {
		this.cmpdInt = cmpdInt;
	}

	/**
	 * @return 返回 提前还本金额
	 */
	public double getRemPrcpAmt() {
		return remPrcpAmt;
	}

	/**
	 * @设置 提前还本金额
	 * @param remPrcpPaym
	 */
	public void setRemPrcpAmt(double remPrcpAmt) {
		this.remPrcpAmt = remPrcpAmt;
	}

	/**
	 * @return 返回 当期利息
	 */
	public double getCurInt() {
		return curInt;
	}

	/**
	 * @设置 当期利息
	 * @param curIntst
	 */
	public void setCurInt(double curInt) {
		this.curInt = curInt;
	}

	/**
	 * @return 返回 冲正撤销标志
	 */
	public String getCancelInd() {
		return cancelInd;
	}

	/**
	 * @设置 冲正撤销标志
	 * @param revseRolInd
	 */
	public void setCancelInd(String cancelInd) {
		this.cancelInd = cancelInd;
	}

	/**
	 * @return 返回 冲正撤销流水号
	 */
	public String getCancelTraceNo() {
		return cancelTraceNo;
	}

	/**
	 * @设置 冲正撤销流水号
	 * @param cancelTracdeNo
	 */
	public void setCancelTraceNo(String cancelTraceNo) {
		this.cancelTraceNo = cancelTraceNo;
	}

	/**
	 * @return 返回 冲正撤销交易日期
	 */
	public String getCancelDt() {
		return cancelDt;
	}

	/**
	 * @设置 冲正撤销交易日期
	 * @param cancelDt
	 */
	public void setCancelDt(String cancelDt) {
		this.cancelDt = cancelDt;
	}

	/**
	 * @return 返回 冲正撤销用户
	 */
	public String getCancelUsrId() {
		return cancelUsrId;
	}

	/**
	 * @设置 冲正撤销用户
	 * @param cancelUsrId
	 */
	public void setCancelUsrId(String cancelUsrId) {
		this.cancelUsrId = cancelUsrId;
	}

	
	/**
	 * 提前还款当前利息计算到
	 * @return 提前还款当前利息计算到
	 */
	public String getFdrpIntstOpt() {
		return fdrpIntstOpt;
	}
	/**
	 * 提前还款当前利息计算到
	 * @param fdrpIntstOpt
	 */
	public void setFdrpIntstOpt(String fdrpIntstOpt) {
		this.fdrpIntstOpt = fdrpIntstOpt;
	}
	/**
	 * 提前还款利息计算基础
	 * @return 提前还款利息计算基础
	 */
	public String getFdrpIntstBase() {
		return fdrpIntstBase;
	}

	/**
	 * 提前还款利息计算基础 
	 * @param fdrpIntstBase
	 */
	public void setFdrpIntstBase(String fdrpIntstBase) {
		this.fdrpIntstBase = fdrpIntstBase;
	}

	/**
	 * @return 返回 备注
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
	 * @return 费用(外部)
	 */
	public double getFeeAmtCredit() {
		return feeAmtCredit;
	}

	/**
	 * @param 费用(外部)
	 */
	public void setFeeAmtCredit(double feeAmtCredit) {
		this.feeAmtCredit = feeAmtCredit;
	}
	/**
	 * @return 费用(核算)
	 */
	public double getFeeAmtAcc() {
		return feeAmtAcc;
	}
	/**
	 * @param 费用(核算)
	 */
	public void setFeeAmtAcc(double feeAmtAcc) {
		this.feeAmtAcc = feeAmtAcc;
	}
	/**
	 * @return 调整选项
	 */
	public String getRepayAdvOpt() {
		return repayAdvOpt ;
	}
	/**
	 * @param 调整选项
	 */
	public void setRepayAdvOpt(String repayAdvOpt) {
		this.repayAdvOpt = repayAdvOpt;
	}
	/**
	 * 违约金
	 * @return 违约金
	 */
	public double getDamageAmt() {
		return damageAmt;
	}
	/**
	 * 违约金
	 * @param damagAmt
	 */
	public void setDamageAmt(double damageAmt) {
		this.damageAmt = damageAmt;
	}
	/**
	 * 补偿金
	 * @return 补偿金
	 */
	public double getCompenAmt() {
		return compenAmt;
	}
	/**
	 * 补偿金
	 * @param compenAmt 
	 */
	public void setCompenAmt(double compenAmt) {
		this.compenAmt = compenAmt;
	}

}