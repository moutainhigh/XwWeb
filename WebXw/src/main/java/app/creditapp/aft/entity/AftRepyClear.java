package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftRepyClear.java
* Description:
* @version：1.0
**/
public class AftRepyClear extends BaseDomain {
	private String pactNo;//合同号
	private String brNo;//机构号
	private String clearId;//提前清贷申请id
	private String wsId;//数据ID
	private int perdNo;//期次号
	private String payDt;//应还款日期
	private String clearSts;//处理状态[01-待处理,02-处理成功,03-处理失败]
	private String dealDesc;//结果描述
	private String txDate;//登记日期
	private String upDate;//更新日期

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
	 * @return 提前清贷申请id
	 */
	public String getClearId() {
	 	return clearId;
	}
	/**
	 * @设置 提前清贷申请id
	 * @param clearId
	 */
	public void setClearId(String clearId) {
	 	this.clearId = clearId;
	}
	/**
	 * @return 数据ID
	 */
	public String getWsId() {
	 	return wsId;
	}
	/**
	 * @设置 数据ID
	 * @param wsId
	 */
	public void setWsId(String wsId) {
	 	this.wsId = wsId;
	}
	/**
	 * @return 期次号
	 */
	public int getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 期次号
	 * @param perdNo
	 */
	public void setPerdNo(int perdNo) {
	 	this.perdNo = perdNo;
	}
	/**
	 * @return 处理状态[01-待处理,02-处理成功,03-处理失败]
	 */
	public String getClearSts() {
	 	return clearSts;
	}
	/**
	 * @设置 处理状态[01-待处理,02-处理成功,03-处理失败]
	 * @param clearSts
	 */
	public void setClearSts(String clearSts) {
	 	this.clearSts = clearSts;
	}
	/**
	 * @return 结果描述
	 */
	public String getDealDesc() {
	 	return dealDesc;
	}
	/**
	 * @设置 结果描述
	 * @param dealDesc
	 */
	public void setDealDesc(String dealDesc) {
	 	this.dealDesc = dealDesc;
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
	public String getPayDt() {
		return payDt;
	}
	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}
}