package app.creditapp.acc.chg.entity;
import app.base.BaseDomain;
/**
* Title: AcRepydayChga.java
* Description:
* @version：1.0
**/
public class AcRepydayChga extends BaseDomain {
	private String batchNo;//批次号
	private String pactNo;//合同号
	private String brNo;//机构号
	private Integer newPayDay;//新扣款日
	private String chgReason;//变更原因
	private String chgaSts;//变更状态

	/**
	 * @return 批次号
	 */
	public String getBatchNo() {
	 	return batchNo;
	}
	/**
	 * @设置 批次号
	 * @param batchNo
	 */
	public void setBatchNo(String batchNo) {
	 	this.batchNo = batchNo;
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
	 * @return 新扣款日
	 */
	public Integer getNewPayDay() {
	 	return newPayDay;
	}
	/**
	 * @设置 新扣款日
	 * @param newPayDay
	 */
	public void setNewPayDay(Integer newPayDay) {
	 	this.newPayDay = newPayDay;
	}
	/**
	 * @return 变更原因
	 */
	public String getChgReason() {
	 	return chgReason;
	}
	/**
	 * @设置 变更原因
	 * @param chgReason
	 */
	public void setChgReason(String chgReason) {
	 	this.chgReason = chgReason;
	}
	/**
	 * @return 变更状态
	 */
	public String getChgaSts() {
	 	return chgaSts;
	}
	/**
	 * @设置 变更状态
	 * @param chgaSts
	 */
	public void setChgaSts(String chgaSts) {
	 	this.chgaSts = chgaSts;
	}
}