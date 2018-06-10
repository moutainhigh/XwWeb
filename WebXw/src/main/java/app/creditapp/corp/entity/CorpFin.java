package app.creditapp.corp.entity;
import app.base.BaseDomain;
/**
* Title: CorpFin.java
* Description:
* @version：1.0
**/
public class CorpFin extends BaseDomain {
	private Double saleIncome;//销售收入
	private Double syzqyVal;//所有者权益
	private Double loanAmt;//借款
	private Double debtTot;//总负债
	private Double assstTot;//总资产
	private Double floDebt;//流动负债
	private Double floAsset;//流动资产
	private Double cashSave;//现金和银行存款
	private String finId;//财务信息ID
	private String brName;//合作机构名称
	private String brNo;//合作机构编号
	private String upOpno;//修改人员
	private String upOpname;//修改人员名称
	private String upDate;//修改日期
	private String txDate;//登记日期
	private String opNo;//操作员
	private String opName;//操作员名称
	private String deptNo;//登记部门
	private String filler;//备注
	private String endDate;//财务报表截止日期
	private Double netCash;//经营活动产生净现金流
	private Double gaAddRate;//销售利润复合增长率(%)
	private Double siAddRate;//销售收入复合增长率(%)
	private Double assDebtRate;//资产负债率(%)
	private Double netAsset;//净资产规模(万元)
	private Double netGain;//净利润
	private Double incomeTax;//所得税
	private Double payInt;//利息支出
	private Double saleCost;//销售成本

	/**
	 * @return 销售收入
	 */
	public Double getSaleIncome() {
	 	return saleIncome;
	}
	/**
	 * @设置 销售收入
	 * @param sale income
	 */
	public void setSalIncome(Double saleIncome) {
	 	this.saleIncome = saleIncome;
	}
	/**
	 * @return 所有者权益
	 */
	public Double getSyzqyVal() {
	 	return syzqyVal;
	}
	/**
	 * @设置 所有者权益
	 * @param syzqyVal
	 */
	public void setSyzqyVal(Double syzqyVal) {
	 	this.syzqyVal = syzqyVal;
	}
	/**
	 * @return 借款
	 */
	public Double getLoanAmt() {
	 	return loanAmt;
	}
	/**
	 * @设置 借款
	 * @param loanAmt
	 */
	public void setLoanAmt(Double loanAmt) {
	 	this.loanAmt = loanAmt;
	}
	/**
	 * @return 总负债
	 */
	public Double getDebtTot() {
	 	return debtTot;
	}
	/**
	 * @设置 总负债
	 * @param debtTot
	 */
	public void setDebtTot(Double debtTot) {
	 	this.debtTot = debtTot;
	}
	/**
	 * @return 总资产
	 */
	public Double getAssstTot() {
	 	return assstTot;
	}
	/**
	 * @设置 总资产
	 * @param assstTot
	 */
	public void setAssstTot(Double assstTot) {
	 	this.assstTot = assstTot;
	}
	/**
	 * @return 流动负债
	 */
	public Double getFloDebt() {
	 	return floDebt;
	}
	/**
	 * @设置 流动负债
	 * @param floDebt
	 */
	public void setFloDebt(Double floDebt) {
	 	this.floDebt = floDebt;
	}
	/**
	 * @return 流动资产
	 */
	public Double getFloAsset() {
	 	return floAsset;
	}
	/**
	 * @设置 流动资产
	 * @param floAsset
	 */
	public void setFloAsset(Double floAsset) {
	 	this.floAsset = floAsset;
	}
	/**
	 * @return 现金和银行存款
	 */
	public Double getCashSave() {
	 	return cashSave;
	}
	/**
	 * @设置 现金和银行存款
	 * @param cashSave
	 */
	public void setCashSave(Double cashSave) {
	 	this.cashSave = cashSave;
	}
	/**
	 * @return 财务信息ID
	 */
	public String getFinId() {
	 	return finId;
	}
	/**
	 * @设置 财务信息ID
	 * @param finId
	 */
	public void setFinId(String finId) {
	 	this.finId = finId;
	}
	/**
	 * @return 合作机构名称
	 */
	public String getBrName() {
	 	return brName;
	}
	/**
	 * @设置 合作机构名称
	 * @param brName
	 */
	public void setBrName(String brName) {
	 	this.brName = brName;
	}
	/**
	 * @return 合作机构编号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 修改人员
	 */
	public String getUpOpno() {
	 	return upOpno;
	}
	/**
	 * @设置 修改人员
	 * @param upOpno
	 */
	public void setUpOpno(String upOpno) {
	 	this.upOpno = upOpno;
	}
	/**
	 * @return 修改日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 修改日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
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
	 * @return 操作员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 操作员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 登记部门
	 */
	public String getDeptNo() {
	 	return deptNo;
	}
	/**
	 * @设置 登记部门
	 * @param deptNo
	 */
	public void setDeptNo(String deptNo) {
	 	this.deptNo = deptNo;
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
	 * @return 财务报表截止日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 财务报表截止日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 经营活动产生净现金流
	 */
	public Double getNetCash() {
	 	return netCash;
	}
	/**
	 * @设置 经营活动产生净现金流
	 * @param netCash
	 */
	public void setNetCash(Double netCash) {
	 	this.netCash = netCash;
	}
	/**
	 * @return 销售利润复合增长率(%)
	 */
	public Double getGaAddRate() {
	 	return gaAddRate;
	}
	/**
	 * @设置 销售利润复合增长率(%)
	 * @param gaAddRate
	 */
	public void setGaAddRate(Double gaAddRate) {
	 	this.gaAddRate = gaAddRate;
	}
	/**
	 * @return 销售收入复合增长率(%)
	 */
	public Double getSiAddRate() {
	 	return siAddRate;
	}
	/**
	 * @设置 销售收入复合增长率(%)
	 * @param siAddRate
	 */
	public void setSiAddRate(Double siAddRate) {
	 	this.siAddRate = siAddRate;
	}
	/**
	 * @return 资产负债率(%)
	 */
	public Double getAssDebtRate() {
	 	return assDebtRate;
	}
	/**
	 * @设置 资产负债率(%)
	 * @param assDebtRate
	 */
	public void setAssDebtRate(Double assDebtRate) {
	 	this.assDebtRate = assDebtRate;
	}
	/**
	 * @return 净资产规模(万元)
	 */
	public Double getNetAsset() {
	 	return netAsset;
	}
	/**
	 * @设置 净资产规模(万元)
	 * @param netAsset
	 */
	public void setNetAsset(Double netAsset) {
	 	this.netAsset = netAsset;
	}
	/**
	 * @return 净利润
	 */
	public Double getNetGain() {
	 	return netGain;
	}
	/**
	 * @设置 净利润
	 * @param netGain
	 */
	public void setNetGain(Double netGain) {
	 	this.netGain = netGain;
	}
	/**
	 * @return 所得税
	 */
	public Double getIncomeTax() {
	 	return incomeTax;
	}
	/**
	 * @设置 所得税
	 * @param incomeTax
	 */
	public void setIncomeTax(Double incomeTax) {
	 	this.incomeTax = incomeTax;
	}
	/**
	 * @return 利息支出
	 */
	public Double getPayInt() {
	 	return payInt;
	}
	/**
	 * @设置 利息支出
	 * @param payInt
	 */
	public void setPayInt(Double payInt) {
	 	this.payInt = payInt;
	}
	/**
	 * @return 销售成本
	 */
	public Double getSaleCost() {
	 	return saleCost;
	}
	/**
	 * @设置 销售成本
	 * @param saleCost
	 */
	public void setSaleCost(Double saleCost) {
	 	this.saleCost = saleCost;
	}
	public String getUpOpname() {
		return upOpname;
	}
	public void setUpOpname(String upOpname) {
		this.upOpname = upOpname;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public void setSaleIncome(Double saleIncome) {
		this.saleIncome = saleIncome;
	}
}