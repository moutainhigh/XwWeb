package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundSplitDetail.java
* Description:
* @version：1.0
**/
public class FundSplitDetail extends BaseDomain {
	private String partId;//方案流水ID
	private String partNo;//方案编号
	private Double bfTxAmt;//发生前金额
	private Double txAmt;//发生金额
	private Double afTxAmt;//发生后金额
	private Float invRate;//收益率
	private String tradeType;//交易类型
	private String txDate;//登记日期
	private String opNo;//登记人

	/**
	 * @return 方案流水ID
	 */
	public String getPartId() {
	 	return partId;
	}
	/**
	 * @设置 方案流水ID
	 * @param partId
	 */
	public void setPartId(String partId) {
	 	this.partId = partId;
	}
	/**
	 * @return 方案编号
	 */
	public String getPartNo() {
	 	return partNo;
	}
	/**
	 * @设置 方案编号
	 * @param partNo
	 */
	public void setPartNo(String partNo) {
	 	this.partNo = partNo;
	}
	/**
	 * @return 发生前金额
	 */
	public Double getBfTxAmt() {
	 	return bfTxAmt;
	}
	/**
	 * @设置 发生前金额
	 * @param bfTxAmt
	 */
	public void setBfTxAmt(Double bfTxAmt) {
	 	this.bfTxAmt = bfTxAmt;
	}
	/**
	 * @return 发生金额
	 */
	public Double getTxAmt() {
	 	return txAmt;
	}
	/**
	 * @设置 发生金额
	 * @param txAmt
	 */
	public void setTxAmt(Double txAmt) {
	 	this.txAmt = txAmt;
	}
	/**
	 * @return 发生后金额
	 */
	public Double getAfTxAmt() {
	 	return afTxAmt;
	}
	/**
	 * @设置 发生后金额
	 * @param afTxAmt
	 */
	public void setAfTxAmt(Double afTxAmt) {
	 	this.afTxAmt = afTxAmt;
	}
	/**
	 * @return 收益率
	 */
	public Float getInvRate() {
	 	return invRate;
	}
	/**
	 * @设置 收益率
	 * @param invRate
	 */
	public void setInvRate(Float invRate) {
	 	this.invRate = invRate;
	}
	/**
	 * @return 交易类型
	 */
	public String getTradeType() {
	 	return tradeType;
	}
	/**
	 * @设置 交易类型
	 * @param tradeType
	 */
	public void setTradeType(String tradeType) {
	 	this.tradeType = tradeType;
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
	 * @return 登记人
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 登记人
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
}