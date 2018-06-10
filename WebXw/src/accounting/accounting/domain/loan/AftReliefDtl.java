package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AftReliefDtl.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AftReliefDtl extends accounting.domain.sys.CMISDomain {
	private String perdNo;//期次号
	private String reliefType;//减免类型[01-本利罚,02-费用]
	private String traceNo;//日志主流水
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private Double refAmt;//减免本金
	private Double refIntst;//减免利息
	private Double refFine;//减免罚息
	private Double refFee;//减免费用
	private String txDt;//登记日期

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
	 * @return 减免类型[01-本利罚,02-费用]
	 */
	public String getReliefType() {
	 	return reliefType;
	}
	/**
	 * @设置 减免类型[01-本利罚,02-费用]
	 * @param reliefType
	 */
	public void setReliefType(String reliefType) {
	 	this.reliefType = reliefType;
	}
	/**
	 * @return 日志主流水
	 */
	public String getTraceNo() {
	 	return traceNo;
	}
	/**
	 * @设置 日志主流水
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
	 * @return 减免本金
	 */
	public Double getRefAmt() {
	 	return refAmt;
	}
	/**
	 * @设置 减免本金
	 * @param refAmt
	 */
	public void setRefAmt(Double refAmt) {
	 	this.refAmt = refAmt;
	}
	/**
	 * @return 减免利息
	 */
	public Double getRefIntst() {
	 	return refIntst;
	}
	/**
	 * @设置 减免利息
	 * @param refIntst
	 */
	public void setRefIntst(Double refIntst) {
	 	this.refIntst = refIntst;
	}
	/**
	 * @return 减免罚息
	 */
	public Double getRefFine() {
	 	return refFine;
	}
	/**
	 * @设置 减免罚息
	 * @param refFine
	 */
	public void setRefFine(Double refFine) {
	 	this.refFine = refFine;
	}
	/**
	 * @return 减免费用
	 */
	public Double getRefFee() {
	 	return refFee;
	}
	/**
	 * @设置 减免费用
	 * @param refFee
	 */
	public void setRefFee(Double refFee) {
	 	this.refFee = refFee;
	}
	/**
	 * @return 登记日期
	 */
	public String getTxDt() {
	 	return txDt;
	}
	/**
	 * @设置 登记日期
	 * @param txDt
	 */
	public void setTxDt(String txDt) {
	 	this.txDt = txDt;
	}
}