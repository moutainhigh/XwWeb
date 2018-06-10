package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundSplit.java
* Description:
* @version：1.0
**/
public class FundSplit extends BaseDomain {
	private String partNo;//方案编号
	private String partName;//方案编号
	private String fundNo;//资金编号
	private Integer partLevel;//方案档次	
	private Double partAmt;//方案规模
	private Float invRate;//收益率
	private String payType;//兑付方式
	private String begDate;//起始日期
	private String endDate;//结束日期
	private String txDate;//登记日期
	private String opNo;//登记人
	private Integer yearDays;//年天数
	private String payDay;//兑付日期
	
	private String query;//只读

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
	 * @return 方案名称
	 */
	public String getPartName() {
		return partName;
	}
	/**
	 * @设置 方案名称
	 * @param partName
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}
	/**
	 * @return 资金编号
	 */
	public Integer getPartLevel() {
	 	return partLevel;
	}
	/**
	 * @设置 资金编号
	 * @param fundNo
	 */
	public void setPartLevel(Integer partLevel) {
	 	this.partLevel = partLevel;
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
	 * @return 方案规模
	 */
	public Double getPartAmt() {
	 	return partAmt;
	}
	/**
	 * @设置 方案规模
	 * @param partAmt
	 */
	public void setPartAmt(Double partAmt) {
	 	this.partAmt = partAmt;
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
	 * @return 兑付方式
	 */
	public String getPayType() {
	 	return payType;
	}
	/**
	 * @设置 兑付方式
	 * @param payType
	 */
	public void setPayType(String payType) {
	 	this.payType = payType;
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
	 * @return 结束日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 结束日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
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
	/**
	 * @return 年天数
	 */
	public Integer getYearDays() {
	 	return yearDays;
	}
	/**
	 * @设置 年天数
	 * @param yearDays
	 */
	public void setYearDays(Integer yearDays) {
	 	this.yearDays = yearDays;
	}
	public String getPayDay() {
		return payDay;
	}
	public void setPayDay(String payDay) {
		this.payDay = payDay;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}