package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AftPayday.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AftPayday extends accounting.domain.sys.CMISDomain {
	private String chgId;//变更ID
	private String pactId;//合同ID
	private String pactNo;//合同号
	private String brNo;//合作机构
	private String oldPayday;//原扣款日
	private String newPayday;//新扣款日
	private String chgSts;//状态[01待处理02已处理]
	private String opNo;//操作员
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String traceNo;//核算主流水

	/**
	 * @return 变更ID
	 */
	public String getChgId() {
	 	return chgId;
	}
	/**
	 * @设置 变更ID
	 * @param chgId
	 */
	public void setChgId(String chgId) {
	 	this.chgId = chgId;
	}
	/**
	 * @return 合同ID
	 */
	public String getPactId() {
	 	return pactId;
	}
	/**
	 * @设置 合同ID
	 * @param pactId
	 */
	public void setPactId(String pactId) {
	 	this.pactId = pactId;
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
	 * @return 合作机构
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 原扣款日
	 */
	public String getOldPayday() {
	 	return oldPayday;
	}
	/**
	 * @设置 原扣款日
	 * @param oldPayday
	 */
	public void setOldPayday(String oldPayday) {
	 	this.oldPayday = oldPayday;
	}
	/**
	 * @return 新扣款日
	 */
	public String getNewPayday() {
	 	return newPayday;
	}
	/**
	 * @设置 新扣款日
	 * @param newPayday
	 */
	public void setNewPayday(String newPayday) {
	 	this.newPayday = newPayday;
	}
	/**
	 * @return 状态[01待处理02已处理]
	 */
	public String getChgSts() {
	 	return chgSts;
	}
	/**
	 * @设置 状态[01待处理02已处理]
	 * @param chgSts
	 */
	public void setChgSts(String chgSts) {
	 	this.chgSts = chgSts;
	}
	/**
	 * @return 操作员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 操作员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 登记日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 登记日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 更新日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 更新日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
}