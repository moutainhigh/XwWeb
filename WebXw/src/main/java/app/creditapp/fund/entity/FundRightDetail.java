package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundRightDetail.java
* Description:
* @version：1.0
**/
public class FundRightDetail extends BaseDomain {
	private String detailId;//资金明细流水号
	private String projNo;//项目编号
	private String buyFno;//买方资金编号
	private String saleFno;//卖方资金编号
	private Double txAmt;//交易债权金额
	private String txTime;//交易时间
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
	 * @return 项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	/**
	 * @return 买方资金编号
	 */
	public String getBuyFno() {
	 	return buyFno;
	}
	/**
	 * @设置 买方资金编号
	 * @param buyFno
	 */
	public void setBuyFno(String buyFno) {
	 	this.buyFno = buyFno;
	}
	/**
	 * @return 卖方资金编号
	 */
	public String getSaleFno() {
	 	return saleFno;
	}
	/**
	 * @设置 卖方资金编号
	 * @param saleFno
	 */
	public void setSaleFno(String saleFno) {
	 	this.saleFno = saleFno;
	}
	/**
	 * @return 交易债权金额
	 */
	public Double getTxAmt() {
	 	return txAmt;
	}
	/**
	 * @设置 交易债权金额
	 * @param txAmt
	 */
	public void setTxAmt(Double txAmt) {
	 	this.txAmt = txAmt;
	}
	/**
	 * @return 交易时间
	 */
	public String getTxTime() {
	 	return txTime;
	}
	/**
	 * @设置 交易时间
	 * @param txTime
	 */
	public void setTxTime(String txTime) {
	 	this.txTime = txTime;
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
}