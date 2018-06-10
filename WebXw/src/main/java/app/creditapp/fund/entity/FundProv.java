package app.creditapp.fund.entity;

import app.base.BaseDomain;
/**
* Title: FundProv.java
* Description:
* @version：1.0
**/
public class FundProv extends BaseDomain {
	private String provNo;//计提编号
	private String provProjNo;//项目收益计提编号
	private String projNo;//项目编号
	private String fundNo;//资金编号
	private String fundName;//资金名称
	private String begDate;//起始日期
	private String endDate;//计算日期
	private Integer days;//存续天数
	private Double fdAmt;//资金规模
	private Float finRate;//融资利率
	private Double fineCost;//融资成本
	private Double payAmt;//受益人收益
	private Double finIncome;//融资报酬
	private String opNo;//计提人员
	private String txDate;//计提日期
	private String filler;//备注

	/**
	 * @return 计提编号
	 */
	public String getProvNo() {
	 	return provNo;
	}
	/**
	 * @设置 计提编号
	 * @param provNo
	 */
	public void setProvNo(String provNo) {
	 	this.provNo = provNo;
	}
	/**
	 * @return 项目收益计提编号
	 */
	public String getProvProjNo() {
	 	return provProjNo;
	}
	/**
	 * @设置 项目收益计提编号
	 * @param provProjNo
	 */
	public void setProvProjNo(String provProjNo) {
	 	this.provProjNo = provProjNo;
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
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
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
	 * @return 计算日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 计算日期
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
	 * @return 资金规模
	 */
	public Double getFdAmt() {
	 	return fdAmt;
	}
	/**
	 * @设置 资金规模
	 * @param fdAmt
	 */
	public void setFdAmt(Double fdAmt) {
	 	this.fdAmt = fdAmt;
	}
	/**
	 * @return 融资利率
	 */
	public Float getFinRate() {
	 	return finRate;
	}
	/**
	 * @设置 融资利率
	 * @param finRate
	 */
	public void setFinRate(Float finRate) {
	 	this.finRate = finRate;
	}
	/**
	 * @return 融资成本
	 */
	public Double getFineCost() {
	 	return fineCost;
	}
	/**
	 * @设置 融资成本
	 * @param fineCost
	 */
	public void setFineCost(Double fineCost) {
	 	this.fineCost = fineCost;
	}
	/**
	 * @return 受益人收益
	 */
	public Double getPayAmt() {
	 	return payAmt;
	}
	/**
	 * @设置 受益人收益
	 * @param payAmt
	 */
	public void setPayAmt(Double payAmt) {
	 	this.payAmt = payAmt;
	}
	/**
	 * @return 融资报酬
	 */
	public Double getFinIncome() {
	 	return finIncome;
	}
	/**
	 * @设置 融资报酬
	 * @param finIncome
	 */
	public void setFinIncome(Double finIncome) {
	 	this.finIncome = finIncome;
	}
	/**
	 * @return 计提人员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 计提人员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 计提日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 计提日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
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
}