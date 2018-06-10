package app.creditapp.inf.entity;
import app.base.BaseDomain;
/**
* Title: WsRepyPlan.java
* Description:
* @version：1.0  还款计划上传明细表
**/
public class WsRepyPlan extends BaseDomain {
	private String wsId;//接收数据编号
	private String batchNo;//批次号
	private String brNo;//合作机构编号
	private String pactNo;//合同号:合同号+期次为主键
	private Integer cnt;//期次
	private String begDate;//起始日期
	private String endDate;//截止日期
	private Double totalAmt;//期供金额:6=7+8
	private Double prcpAmt;//当期本金
	private Double normInt;//当期利息
	private String sts;//结清状态[01未结清02已结清]
	private String txDate;//进件接收日期
	private String brName;//合作机构名称
	/**
	 * @return 接收数据编号
	 */
	public String getWsId() {
	 	return wsId;
	}
	/**
	 * @设置 接收数据编号
	 * @param wsId
	 */
	public void setWsId(String wsId) {
	 	this.wsId = wsId;
	}
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
	 * @return 合作机构编号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 合同号:合同号+期次为主键
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同号:合同号+期次为主键
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 期次
	 */
	public Integer getCnt() {
	 	return cnt;
	}
	/**
	 * @设置 期次
	 * @param cnt
	 */
	public void setCnt(Integer cnt) {
	 	this.cnt = cnt;
	}
	/**
	 * @return 起始日期
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 起始日期
	 * @param begDate
	 */
	public void setBegDate(String begDate) {
	 	this.begDate = begDate;
	}
	/**
	 * @return 截止日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 截止日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 期供金额:6=7+8
	 */
	public Double getTotalAmt() {
	 	return totalAmt;
	}
	/**
	 * @设置 期供金额:6=7+8
	 * @param totalAmt
	 */
	public void setTotalAmt(Double totalAmt) {
	 	this.totalAmt = totalAmt;
	}
	/**
	 * @return 当期本金
	 */
	public Double getPrcpAmt() {
	 	return prcpAmt;
	}
	/**
	 * @设置 当期本金
	 * @param prcpAmt
	 */
	public void setPrcpAmt(Double prcpAmt) {
	 	this.prcpAmt = prcpAmt;
	}
	/**
	 * @return 当期利息
	 */
	public Double getNormInt() {
	 	return normInt;
	}
	/**
	 * @设置 当期利息
	 * @param normInt
	 */
	public void setNormInt(Double normInt) {
	 	this.normInt = normInt;
	}
	/**
	 * @return 结清状态[01未结清02已结清]
	 */
	public String getSts() {
	 	return sts;
	}
	/**
	 * @设置 结清状态[01未结清02已结清]
	 * @param sts
	 */
	public void setSts(String sts) {
	 	this.sts = sts;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
}