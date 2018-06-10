package app.creditapp.inf.entity;
import app.base.BaseDomain;
/**
* Title: WsElyRepyBatch.java
* Description:
* @version：1.0
**/
public class WsElyRepyBatch extends BaseDomain {
	private String batchNo;//批次编号
	private String brNo;//合作机构号
	private Integer dataCnt;//记录数
	private String txTime;//批次接收数据时间

	private String brName;//合作机构名称
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
	 * @return 记录数
	 */
	public Integer getDataCnt() {
	 	return dataCnt;
	}
	/**
	 * @设置 记录数
	 * @param dataCnt
	 */
	public void setDataCnt(Integer dataCnt) {
	 	this.dataCnt = dataCnt;
	}
	/**
	 * @return 批次接收数据时间
	 */
	public String getTxTime() {
	 	return txTime;
	}
	/**
	 * @设置 批次接收数据时间
	 * @param txTime
	 */
	public void setTxTime(String txTime) {
	 	this.txTime = txTime;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getBrName() {
		return brName;
	}
}