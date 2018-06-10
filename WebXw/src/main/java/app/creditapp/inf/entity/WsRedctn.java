package app.creditapp.inf.entity;
import app.base.BaseDomain;
/**
* Title: WsRedctn.java
* Description:
* @version：1.0
**/
public class WsRedctn extends BaseDomain {
	private String wsId;//数据ID
	private String batchNo;//批次编号
	private String brNo;//合作机构编号
	private String pactNo;//合同号:主键
	private Double refAmt;//减免本金:没有填0
	private Double refInte;//减免利息:没有填0
	private Double refOver;//减免罚息:没有填0
	private Double refFee;//减免费用:没有填0
	private String txDate;//接收数据日期
	private String dealSts;//处理状态[01未处理02处理中03处理成功04处理失败]
	private String dealDesc;//处理说明
	private String brName;//合作机构名称

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
	 * @return 批次编号
	 */
	public String getBatchNo() {
	 	return batchNo;
	}
	/**
	 * @设置 批次编号
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
	 * @return 合同号:主键
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同号:主键
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 减免本金:没有填0
	 */
	public Double getRefAmt() {
	 	return refAmt;
	}
	/**
	 * @设置 减免本金:没有填0
	 * @param refAmt
	 */
	public void setRefAmt(Double refAmt) {
	 	this.refAmt = refAmt;
	}
	/**
	 * @return 减免利息:没有填0
	 */
	public Double getRefInte() {
	 	return refInte;
	}
	/**
	 * @设置 减免利息:没有填0
	 * @param refInte
	 */
	public void setRefInte(Double refInte) {
	 	this.refInte = refInte;
	}
	/**
	 * @return 减免罚息:没有填0
	 */
	public Double getRefOver() {
	 	return refOver;
	}
	/**
	 * @设置 减免罚息:没有填0
	 * @param refOver
	 */
	public void setRefOver(Double refOver) {
	 	this.refOver = refOver;
	}
	/**
	 * @return 减免费用:没有填0
	 */
	public Double getRefFee() {
	 	return refFee;
	}
	/**
	 * @设置 减免费用:没有填0
	 * @param refFee
	 */
	public void setRefFee(Double refFee) {
	 	this.refFee = refFee;
	}
	/**
	 * @return 接收数据日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 接收数据日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 处理状态[01未处理02处理中03处理成功04处理失败]
	 */
	public String getDealSts() {
	 	return dealSts;
	}
	/**
	 * @设置 处理状态[01未处理02处理中03处理成功04处理失败]
	 * @param dealSts
	 */
	public void setDealSts(String dealSts) {
	 	this.dealSts = dealSts;
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
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
}