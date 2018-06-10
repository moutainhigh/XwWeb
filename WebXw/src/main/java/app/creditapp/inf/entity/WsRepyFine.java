package app.creditapp.inf.entity;
import app.base.BaseDomain;
/**
* Title: WsRepyFine.java
* Description:
* @version：1.0
**/
public class WsRepyFine extends BaseDomain {
	private String wsId;//接收数据ID
	private String brNo;//机构号
	private String pactNo;//合同号
	private Integer cnt;//期次
	private Double txPayOver;//应收罚息

	/**
	 * @return 接收数据ID
	 */
	public String getWsId() {
	 	return wsId;
	}
	/**
	 * @设置 接收数据ID
	 * @param wsId
	 */
	public void setWsId(String wsId) {
	 	this.wsId = wsId;
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
	 * @return 应收罚息
	 */
	public Double getTxPayOver() {
	 	return txPayOver;
	}
	/**
	 * @设置 应收罚息
	 * @param txPayOver
	 */
	public void setTxPayOver(Double txPayOver) {
	 	this.txPayOver = txPayOver;
	}
}