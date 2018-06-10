package accounting.domain.fee;
/**
* Title: AcChrgLog.java
* Description:
* @作者 su
* @日期 2018-4-25
* @version：1.0
**/
public class AcChrgLog extends accounting.domain.sys.CMISDomain {
	private String chrgId;//费用ID
	private String traceNo;//流水号
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private String feeParmId;//费用定义编号
	private String feeKind;//费用类型
	private String perdNo;//期次号
	private Double chrgAmt;//费用金额
	private Double repayChrgAmt;//累计实收金额
	private Double wvChrgAmt;//减免费用
	private String chrgSts;//费用状态
	private String txDate;//登记日期
	private String txTime;//登记时间
	private String upDate;//更新时间
	private Double payChrgAmt;//实付费用
	private String feeType;//费用类型
	
	
	
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
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
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
	/**
	 * @return 流水号
	 */
	public String getTraceNo() {
	 	return traceNo;
	}
	/**
	 * @设置 流水号
	 * @param traceNo
	 */
	public void setTraceNo(String traceNo) {
	 	this.traceNo = traceNo;
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
	 * @return 费用定义编号
	 */
	public String getFeeParmId() {
	 	return feeParmId;
	}
	/**
	 * @设置 费用定义编号
	 * @param feeParmId
	 */
	public void setFeeParmId(String feeParmId) {
	 	this.feeParmId = feeParmId;
	}
	/**
	 * @return 期次号
	 */
	public String getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 期次号
	 * @param perdNo
	 */
	public void setPerdNo(String perdNo) {
	 	this.perdNo = perdNo;
	}
	/**
	 * @return 费用金额
	 */
	public Double getChrgAmt() {
	 	return chrgAmt;
	}
	/**
	 * @设置 费用金额
	 * @param chrgAmt
	 */
	public void setChrgAmt(Double chrgAmt) {
	 	this.chrgAmt = chrgAmt;
	}
	/**
	 * @return 累计实收金额
	 */
	public Double getRepayChrgAmt() {
	 	return repayChrgAmt;
	}
	/**
	 * @设置 累计实收金额
	 * @param repayChrgAmt
	 */
	public void setRepayChrgAmt(Double repayChrgAmt) {
	 	this.repayChrgAmt = repayChrgAmt;
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
	public String getFeeKind() {
		return feeKind;
	}
	public void setFeeKind(String feeKind) {
		this.feeKind = feeKind;
	}
	public Double getWvChrgAmt() {
		return wvChrgAmt;
	}
	public void setWvChrgAmt(Double wvChrgAmt) {
		this.wvChrgAmt = wvChrgAmt;
	}
	public String getChrgId() {
		return chrgId;
	}
	public void setChrgId(String chrgId) {
		this.chrgId = chrgId;
	}
	public Double getPayChrgAmt() {
		return payChrgAmt;
	}
	public void setPayChrgAmt(Double payChrgAmt) {
		this.payChrgAmt = payChrgAmt;
	}
}