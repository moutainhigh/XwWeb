package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftReverse.java
* Description:
* @version：1.0
**/
public class AftReverse extends BaseDomain {
	private String reveId;//冲账ID[AFT_CHG_SEQ]
	private String traceNo;//业务流水号
	private String txCde;//交易码
	private String cifNo;//客户号
	private String cifName;//客户名称
	private String dueNo;//借据号
	private String brNo;//合作机构号
	private String pactNo;//合同编号
	private Double reveAmt;//冲账金额
	private String reveReason;//冲账原因
	private String reveDate;//冲账日期
	private String reveSts;//冲账状态 01未提交 02审批中 03通过 04否决
	private String opNo;//操作员编号
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String opName;//操作员名称
	private String brName;//合作机构名称

	/**
	 * @return 冲账ID[AFT_CHG_SEQ]
	 */
	public String getReveId() {
	 	return reveId;
	}
	/**
	 * @设置 冲账ID[AFT_CHG_SEQ]
	 * @param reveId
	 */
	public void setReveId(String reveId) {
	 	this.reveId = reveId;
	}
	/**
	 * @return 业务流水号
	 */
	public String getTraceNo() {
	 	return traceNo;
	}
	/**
	 * @设置 业务流水号
	 * @param traceNo
	 */
	public void setTraceNo(String traceNo) {
	 	this.traceNo = traceNo;
	}
	/**
	 * @return 交易码
	 */
	public String getTxCde() {
	 	return txCde;
	}
	/**
	 * @设置 交易码
	 * @param txCde
	 */
	public void setTxCde(String txCde) {
	 	this.txCde = txCde;
	}
	/**
	 * @return 客户号
	 */
	public String getCifNo() {
	 	return cifNo;
	}
	/**
	 * @设置 客户号
	 * @param cifNo
	 */
	public void setCifNo(String cifNo) {
	 	this.cifNo = cifNo;
	}
	/**
	 * @return 客户名称
	 */
	public String getCifName() {
	 	return cifName;
	}
	/**
	 * @设置 客户名称
	 * @param cifName
	 */
	public void setCifName(String cifName) {
	 	this.cifName = cifName;
	}
	/**
	 * @return 借据号
	 */
	public String getDueNo() {
	 	return dueNo;
	}
	/**
	 * @设置 借据号
	 * @param dueNo
	 */
	public void setDueNo(String dueNo) {
	 	this.dueNo = dueNo;
	}
	/**
	 * @return 合作机构号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 合同编号
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同编号
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 冲账金额
	 */
	public Double getReveAmt() {
	 	return reveAmt;
	}
	/**
	 * @设置 冲账金额
	 * @param reveAmt
	 */
	public void setReveAmt(Double reveAmt) {
	 	this.reveAmt = reveAmt;
	}
	/**
	 * @return 冲账原因
	 */
	public String getReveReason() {
	 	return reveReason;
	}
	/**
	 * @设置 冲账原因
	 * @param reveReason
	 */
	public void setReveReason(String reveReason) {
	 	this.reveReason = reveReason;
	}
	/**
	 * @return 冲账日期
	 */
	public String getReveDate() {
	 	return reveDate;
	}
	/**
	 * @设置 冲账日期
	 * @param reveDate
	 */
	public void setReveDate(String reveDate) {
	 	this.reveDate = reveDate;
	}
	/**
	 * @return 冲账状态 01未提交 02审批中 03通过 04否决
	 */
	public String getReveSts() {
	 	return reveSts;
	}
	/**
	 * @设置 冲账状态 01未提交 02审批中 03通过 04否决
	 * @param reveSts
	 */
	public void setReveSts(String reveSts) {
	 	this.reveSts = reveSts;
	}
	/**
	 * @return op_no
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 op_no
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return tx_date
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 tx_date
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return up_date
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 up_date
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
}