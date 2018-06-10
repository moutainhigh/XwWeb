package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundProvSplitTerm.java
* Description:
* @version：1.0
**/
public class FundProvSplitTerm extends BaseDomain {
	private String provTermNo;//计提分配存续编号
	private String provProjNo;//项目收益计提编号
	private String projNo;//项目编号
	private String fundNo;//资金编号
	private String partNo;//分配方案编号
	private String termNo;//分配方案存续编号
	private Double termAmt;//分配方案存续金额
	private String begDate;//存续起始日期
	private String endDate;//存续到期日期
	private String jtBegDate;//计提起始日期
	private String jtEndDate;//计提到期日期
	private Integer days;//存续段在计提区间内天数
	private Integer yearDays;//年天数
	private Float invRate;//收益率
	private Double payAmt;//收益
	private Float financeRate;//融资利率
	private Double finCost;//融资成本	
	private Double finlIncome;//融资报酬
	private Float managerRate;//服务费率	

	private Double managerFee;//服务费
	private String opNo;//计提人员
	private String txDate;//计提日期
	private String filler;//备注

	/**
	 * @return 计提分配存续编号
	 */
	public String getProvTermNo() {
	 	return provTermNo;
	}
	/**
	 * @设置 计提分配存续编号
	 * @param provTermNo
	 */
	public void setProvTermNo(String provTermNo) {
	 	this.provTermNo = provTermNo;
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
	/**
	 * @return 分配方案编号
	 */
	public String getPartNo() {
	 	return partNo;
	}
	/**
	 * @设置 分配方案编号
	 * @param partNo
	 */
	public void setPartNo(String partNo) {
	 	this.partNo = partNo;
	}
	/**
	 * @return 分配方案存续编号
	 */
	public String getTermNo() {
	 	return termNo;
	}
	/**
	 * @设置 分配方案存续编号
	 * @param termNo
	 */
	public void setTermNo(String termNo) {
	 	this.termNo = termNo;
	}
	/**
	 * @return 分配方案存续金额
	 */
	public Double getTermAmt() {
	 	return termAmt;
	}
	/**
	 * @设置 分配方案存续金额
	 * @param termAmt
	 */
	public void setTermAmt(Double termAmt) {
	 	this.termAmt = termAmt;
	}
	/**
	 * @return 存续起始日期
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 存续起始日期
	 * @param begDate
	 */
	public void setBegDate(String begDate) {
	 	this.begDate = begDate;
	}
	/**
	 * @return 存续到期日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 存续到期日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 计提起始日期
	 */
	public String getJtBegDate() {
	 	return jtBegDate;
	}
	/**
	 * @设置 计提起始日期
	 * @param jtBegDate
	 */
	public void setJtBegDate(String jtBegDate) {
	 	this.jtBegDate = jtBegDate;
	}
	/**
	 * @return 计提到期日期
	 */
	public String getJtEndDate() {
	 	return jtEndDate;
	}
	/**
	 * @设置 计提到期日期
	 * @param jtEndDate
	 */
	public void setJtEndDate(String jtEndDate) {
	 	this.jtEndDate = jtEndDate;
	}
	/**
	 * @return 存续段在计提区间内天数
	 */
	public Integer getDays() {
	 	return days;
	}
	/**
	 * @设置 存续段在计提区间内天数
	 * @param days
	 */
	public void setDays(Integer days) {
	 	this.days = days;
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
	 * @return 融资报酬
	 */
	public Double getManagerFee() {
	 	return managerFee;
	}
	/**
	 * @设置 融资报酬
	 * @param managerFee
	 */
	public void setManagerFee(Double managerFee) {
	 	this.managerFee = managerFee;
	}
	/**
	 * @return 融资利率
	 */
	public Float getFinanceRate() {
	 	return financeRate;
	}
	/**
	 * @设置 融资利率
	 * @param financeRate
	 */
	public void setFinanceRate(Float financeRate) {
	 	this.financeRate = financeRate;
	}
	/**
	 * @return 收益
	 */
	public Double getPayAmt() {
	 	return payAmt;
	}
	/**
	 * @设置 收益
	 * @param payAmt
	 */
	public void setPayAmt(Double payAmt) {
	 	this.payAmt = payAmt;
	}
	/**
	 * @return 融资成本
	 */
	public Double getFinCost() {
	 	return finCost;
	}
	public Double getFinlIncome() {
		return finlIncome;
	}
	public void setFinlIncome(Double finlIncome) {
		this.finlIncome = finlIncome;
	}
	public Float getManagerRate() {
		return managerRate;
	}
	public void setManagerRate(Float managerRate) {
		this.managerRate = managerRate;
	}
	/**
	 * @设置 融资成本
	 * @param finIncome
	 */
	public void setFinCost(Double finCost) {
	 	this.finCost = finCost;
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