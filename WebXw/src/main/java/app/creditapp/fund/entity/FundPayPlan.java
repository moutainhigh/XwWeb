package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundPayPlan.java
* Description:
* @version：1.0
**/
public class FundPayPlan extends BaseDomain {
	private String payPlanNo;//兑付编号
	private String fundNo;//资金编号	
	private String partNo;//方案编号
	private Integer totalIssue;//总期数
	private Double totalAmt;//总金额
	private Integer payIssue;//兑付期次
	private String payDate;//兑付日期
	private Double recPayAmt;//应兑金额
	private Double payAmt;//实兑金额
	private Double unpayAmt;//未兑金额
	private String filler;//备注
	private String warnDate;//提前提醒工作日
	
	private String fdType;//
	
	private String query;//只读 

	/**
	 * @return 兑付编号
	 */
	public String getPayPlanNo() {
	 	return payPlanNo;
	}
	/**
	 * @设置 兑付编号
	 * @param payPlanNo
	 */
	public void setPayPlanNo(String payPlanNo) {
	 	this.payPlanNo = payPlanNo;
	}
	/**
	 * @return 资金编号
	 */
	public String getFundNo() {
	 	return fundNo;
	}
	/**
	 * @设置 资金编号
	 * @param payPlanNo
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
	 * @return 兑付期次
	 */
	public Integer getPayIssue() {
	 	return payIssue;
	}
	/**
	 * @设置 兑付期次
	 * @param payIssue
	 */
	public void setPayIssue(Integer payIssue) {
	 	this.payIssue = payIssue;
	}
	/**
	 * @return 兑付日期
	 */
	public String getPayDate() {
	 	return payDate;
	}
	/**
	 * @设置 兑付日期
	 * @param payDate
	 */
	public void setPayDate(String payDate) {
	 	this.payDate = payDate;
	}
	/**
	 * @return 应兑金额
	 */
	public Double getRecPayAmt() {
	 	return recPayAmt;
	}
	/**
	 * @设置 应兑金额
	 * @param recPayAmt
	 */
	public void setRecPayAmt(Double recPayAmt) {
	 	this.recPayAmt = recPayAmt;
	}
	/**
	 * @return 实兑金额
	 */
	public Double getPayAmt() {
	 	return payAmt;
	}
	/**
	 * @设置 实兑金额
	 * @param payAmt
	 */
	public void setPayAmt(Double payAmt) {
	 	this.payAmt = payAmt;
	}
	/**
	 * @return 未兑金额
	 */
	public Double getUnpayAmt() {
	 	return unpayAmt;
	}
	/**
	 * @设置 未兑金额
	 * @param unpayAmt
	 */
	public void setUnpayAmt(Double unpayAmt) {
	 	this.unpayAmt = unpayAmt;
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
	 * @return 提前提醒工作日
	 */
	public String getWarnDate() {
	 	return warnDate;
	}
	/**
	 * @设置 提前提醒工作日
	 * @param warnDate
	 */
	public void setWarnDate(String warnDate) {
	 	this.warnDate = warnDate;
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
}