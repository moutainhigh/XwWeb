package app.creditapp.acc.entity;
import app.base.BaseDomain;
/**
* Title: WsRepyFee.java
* Description:
* @version：1.0
**/
public class WsRepyFee extends BaseDomain {
	private String wsId;//接收数据ID
	private String brNo;//机构号
	private String pactNo;//合同号
	private Integer cnt;//期次
	private String feeType;//费用类型[01服务费 02保费]
	private String feeKind;//费用类型[01 自收  02代收]
	private Double feeAmt;//费用金额

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
	 * @return 费用类型[1保费 2服务费]
	 */
	public String getFeeType() {
	 	return feeType;
	}
	/**
	 * @设置 费用类型[1保费 2服务费]
	 * @param feeType
	 */
	public void setFeeType(String feeType) {
	 	this.feeType = feeType;
	}
	/**
	 * @return 费用金额
	 */
	public Double getFeeAmt() {
	 	return feeAmt;
	}
	/**
	 * @设置 费用金额
	 * @param feeAmt
	 */
	public void setFeeAmt(Double feeAmt) {
	 	this.feeAmt = feeAmt;
	}
	public String getFeeKind() {
		return feeKind;
	}
	public void setFeeKind(String feeKind) {
		this.feeKind = feeKind;
	}
}