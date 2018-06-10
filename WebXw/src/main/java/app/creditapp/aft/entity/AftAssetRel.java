package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftAssetRel.java
* Description:
* @version：1.0
**/
public class AftAssetRel extends BaseDomain {
	private String brNo;//合作机构号
	private String pactNo;//合同编号
	private String pactId;//合同ID
	private String assId;//抵债物ID
	private String recId;//记录ID
	private Double assAmt;//抵债金额
	private String opNo;//操作员
	private String txDate;//登记日期
	private String upDate;//登记日期
	private String cifNo;//客户号
	private String cifName;//客户名称
	private String brName;//合作机构名称

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
	 * @return 抵债物ID
	 */
	public String getAssId() {
	 	return assId;
	}
	/**
	 * @设置 抵债物ID
	 * @param assId
	 */
	public void setAssId(String assId) {
	 	this.assId = assId;
	}
	/**
	 * @return 记录ID
	 */
	public String getRecId() {
	 	return recId;
	}
	/**
	 * @设置 记录ID
	 * @param recId
	 */
	public void setRecId(String recId) {
	 	this.recId = recId;
	}
	/**
	 * @return 抵债金额
	 */
	public Double getAssAmt() {
	 	return assAmt;
	}
	/**
	 * @设置 抵债金额
	 * @param assAmt
	 */
	public void setAssAmt(Double assAmt) {
	 	this.assAmt = assAmt;
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
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
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
}