package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: RptFundDaily.java
* Description:
* @version：1.0
**/
public class RptFundDaily extends BaseDomain {
	private String rptDate;//报表日期
	private String rptType;//报表类型
	private String projNo;//项目编号
	private String fundNo;//资金编号
	private String fdType;//资金类型
	private Double fdAmt;//资金规模
	private Double cashBal;//现金余额
	private Double rightBal;//债权金额
	private Double addAmt;//累计新增
	private Double payAmt;//累计兑付

	/**
	 * @return 报表日期
	 */
	public String getRptDate() {
	 	return rptDate;
	}
	/**
	 * @设置 报表日期
	 * @param rptDate
	 */
	public void setRptDate(String rptDate) {
	 	this.rptDate = rptDate;
	}
	/**
	 * @return 报表类型
	 */
	public String getRptType() {
	 	return rptType;
	}
	/**
	 * @设置 报表类型
	 * @param rptType
	 */
	public void setRptType(String rptType) {
	 	this.rptType = rptType;
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
	/**
	 * @return 资金类型
	 */
	public String getFdType() {
	 	return fdType;
	}
	/**
	 * @设置 资金类型
	 * @param fdType
	 */
	public void setFdType(String fdType) {
	 	this.fdType = fdType;
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
	 * @return 现金余额
	 */
	public Double getCashBal() {
	 	return cashBal;
	}
	/**
	 * @设置 现金余额
	 * @param cashBal
	 */
	public void setCashBal(Double cashBal) {
	 	this.cashBal = cashBal;
	}
	/**
	 * @return 债权金额
	 */
	public Double getRightBal() {
	 	return rightBal;
	}
	/**
	 * @设置 债权金额
	 * @param rightBal
	 */
	public void setRightBal(Double rightBal) {
	 	this.rightBal = rightBal;
	}
	/**
	 * @return 累计新增
	 */
	public Double getAddAmt() {
	 	return addAmt;
	}
	/**
	 * @设置 累计新增
	 * @param addAmt
	 */
	public void setAddAmt(Double addAmt) {
	 	this.addAmt = addAmt;
	}
	/**
	 * @return 累计兑付
	 */
	public Double getPayAmt() {
	 	return payAmt;
	}
	/**
	 * @设置 累计兑付
	 * @param payAmt
	 */
	public void setPayAmt(Double payAmt) {
	 	this.payAmt = payAmt;
	}
}