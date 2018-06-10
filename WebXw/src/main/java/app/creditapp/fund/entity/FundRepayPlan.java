package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundRepayPlan.java
* Description:
* @version：1.0
**/
public class FundRepayPlan extends BaseDomain {
	private String repayPlanNo;//清算计划编号
	private String fundNo;//资金编号
	private Integer totalIssue;//总期数
	private Double totalAmt;//总金额
	private Integer repayIssue;//还款期次
	private String repayDate;//应还日期
	private Double repayAmt;//应付金额
	private Double payAmt;//财富分配
	private Double cashManager;//现金管理
	private Double unrepayAmt;//未付金额
	private String filler;//备注
	
	private String query;//只读

	/**
	 * @return 清算计划编号
	 */
	public String getRepayPlanNo() {
	 	return repayPlanNo;
	}
	/**
	 * @设置 清算计划编号
	 * @param repayPlanNo
	 */
	public void setRepayPlanNo(String repayPlanNo) {
	 	this.repayPlanNo = repayPlanNo;
	}
	/**
	 * @return 存续编号
	 */
	public String getFundNo() {
	 	return fundNo;
	}
	/**
	 * @设置 存续编号
	 * @param termNo
	 */
	public void setFundNo(String fundNo) {
	 	this.fundNo = fundNo;
	}
	/**
	 * @return 总期数
	 */
	public Integer getTotalIssue() {
	 	return totalIssue;
	}
	/**
	 * @设置 总期数
	 * @param totalIssue
	 */
	public void setTotalIssue(Integer totalIssue) {
	 	this.totalIssue = totalIssue;
	}
	/**
	 * @return 总金额
	 */
	public Double getTotalAmt() {
	 	return totalAmt;
	}
	/**
	 * @设置 总金额
	 * @param totalAmt
	 */
	public void setTotalAmt(Double totalAmt) {
	 	this.totalAmt = totalAmt;
	}
	/**
	 * @return 还款期次
	 */
	public Integer getRepayIssue() {
	 	return repayIssue;
	}
	/**
	 * @设置 还款期次
	 * @param repayIssue
	 */
	public void setRepayIssue(Integer repayIssue) {
	 	this.repayIssue = repayIssue;
	}
	/**
	 * @return 应还日期
	 */
	public String getRepayDate() {
	 	return repayDate;
	}
	/**
	 * @设置 应还日期
	 * @param repayDate
	 */
	public void setRepayDate(String repayDate) {
	 	this.repayDate = repayDate;
	}
	/**
	 * @return 应付金额
	 */
	public Double getRepayAmt() {
	 	return repayAmt;
	}
	/**
	 * @设置 应付金额
	 * @param repayAmt
	 */
	public void setRepayAmt(Double repayAmt) {
	 	this.repayAmt = repayAmt;
	}
	/**
	 * @return 财富分配
	 */
	public Double getPayAmt() {
	 	return payAmt;
	}
	/**
	 * @设置 财富分配
	 * @param payAmt
	 */
	public void setPayAmt(Double payAmt) {
	 	this.payAmt = payAmt;
	}
	/**
	 * @return 现金管理
	 */
	public Double getCashManager() {
	 	return cashManager;
	}
	/**
	 * @设置 现金管理
	 * @param cashManager
	 */
	public void setCashManager(Double cashManager) {
	 	this.cashManager = cashManager;
	}
	/**
	 * @return 未付金额
	 */
	public Double getUnrepayAmt() {
	 	return unrepayAmt;
	}
	/**
	 * @设置 未付金额
	 * @param unrepayAmt
	 */
	public void setUnrepayAmt(Double unrepayAmt) {
	 	this.unrepayAmt = unrepayAmt;
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