package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundSplitTerm.java
* Description:
* @version：1.0
**/
public class FundSplitTerm extends BaseDomain {
	private String termNo;//存续编号
	private String fundNo;//资金编号
	private String partNo;//方案编号
	private Double termAmt;//规模
	private Float invRate;//收益率
	private String begDate;//起始日期
	private String endDate;//结束日期
	private Integer days;//存续天数
	private String filler;//备注
	private String curNo;//币种

	/**
	 * @return 存续编号
	 */
	public String getTermNo() {
	 	return termNo;
	}
	/**
	 * @设置 存续编号
	 * @param termNo
	 */
	public void setTermNo(String termNo) {
	 	this.termNo = termNo;
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
	 * @return 规模
	 */
	public Double getTermAmt() {
	 	return termAmt;
	}
	/**
	 * @设置 规模
	 * @param termAmt
	 */
	public void setTermAmt(Double termAmt) {
	 	this.termAmt = termAmt;
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
	 * @return 存续天数
	 */
	public Integer getDays() {
	 	return days;
	}
	/**
	 * @设置 存续天数
	 * @param days
	 */
	public void setDays(Integer days) {
	 	this.days = days;
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
	/**
	 * @return 币种
	 */
	public String getCurNo() {
	 	return curNo;
	}
	/**
	 * @设置 币种
	 * @param curNo
	 */
	public void setCurNo(String curNo) {
	 	this.curNo = curNo;
	}
}