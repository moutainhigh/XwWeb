package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundDetail.java
* Description:
* @version：1.0
**/
public class FundDetail extends BaseDomain {
	private String detailId;//资金明细流水号
	private String fundNo;//资金编号
	private String tradeType;//交易类型
	private String fdType;//资金类型
	private Double txAmt;//发生金额
	private String termNo;//存续编号**
	private String txDate;//交易日期
	private String opNo;//登记人员
	private String filler;//备注
	
	private String query;//只读
	/**
	 * @return 资金明细流水号
	 */
	public String getDetailId() {
	 	return detailId;
	}
	/**
	 * @设置 资金明细流水号
	 * @param detailId
	 */
	public void setDetailId(String detailId) {
	 	this.detailId = detailId;
	}
	/**
	 * @return 资金编号
	 */
	public String getFundNo() {
	 	return fundNo;
	}
	/**
	 * @设置 资金编号
	 * @param fundNo
	 */
	public void setFundNo(String fundNo) {
	 	this.fundNo = fundNo;
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
	 * @return 交易日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 交易日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 登记人员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 登记人员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 备注
	 */
	public String getFiller() {
	 	return filler;
	}
	/**
	 * @设置 备注
	 * @param filler
	 */
	public void setFiller(String filler) {
	 	this.filler = filler;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getFdType() {
		return fdType;
	}
	public void setFdType(String fdType) {
		this.fdType = fdType;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

}