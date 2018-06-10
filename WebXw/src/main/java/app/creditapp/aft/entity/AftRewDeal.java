package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftRewDeal.java
* Description:
* @version：1.0
**/
public class AftRewDeal extends BaseDomain {
	private String rewId;//预警ID
	private String recId;//记录ID
	private String dealDate;//处理日期
	private String dealType;//处理方式
	private String dealDesc;//处理说明
	private String dealRest;//处理结果
	private String opNo;//处理人
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String opName;//操作员名称
	private String rewName;//预警名称
	
	private String rewFlag;//预警标志 01业务预警 02项目预警 03资金预警

	/**
	 * @return 预警ID
	 */
	public String getRewId() {
	 	return rewId;
	}
	/**
	 * @设置 预警ID
	 * @param rewId
	 */
	public void setRewId(String rewId) {
	 	this.rewId = rewId;
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
	 * @return 处理日期
	 */
	public String getDealDate() {
	 	return dealDate;
	}
	/**
	 * @设置 处理日期
	 * @param dealDate
	 */
	public void setDealDate(String dealDate) {
	 	this.dealDate = dealDate;
	}
	/**
	 * @return 处理方式
	 */
	public String getDealType() {
	 	return dealType;
	}
	/**
	 * @设置 处理方式
	 * @param dealType
	 */
	public void setDealType(String dealType) {
	 	this.dealType = dealType;
	}
	/**
	 * @return 处理说明
	 */
	public String getDealDesc() {
	 	return dealDesc;
	}
	/**
	 * @设置 处理说明
	 * @param dealDesc
	 */
	public void setDealDesc(String dealDesc) {
	 	this.dealDesc = dealDesc;
	}
	/**
	 * @return 处理结果
	 */
	public String getDealRest() {
	 	return dealRest;
	}
	/**
	 * @设置 处理结果
	 * @param dealRest
	 */
	public void setDealRest(String dealRest) {
	 	this.dealRest = dealRest;
	}
	/**
	 * @return 处理人
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 处理人
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
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getRewName() {
		return rewName;
	}
	public void setRewName(String rewName) {
		this.rewName = rewName;
	}
	public String getRewFlag() {
		return rewFlag;
	}
	public void setRewFlag(String rewFlag) {
		this.rewFlag = rewFlag;
	}
}