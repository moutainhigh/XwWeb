package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundProvProj.java
* Description:
* @version：1.0
**/
public class FundProvProj extends BaseDomain {
	private String provProjNo;//项目收益计提编号
	private String projNo;//项目编号
	private String projName;//项目名称
	private String begDate;//起始日期
	private String endDate;//计算日期
	private Double finCost;//融资总成本
	private Double payAmt;//受益人总收益
	private Double finlIncome;//融资总报酬
	private Double managerFee;//项目管理费
	private Double serviceFee;//放贷服务费
	private Double drawingAmt;//计提总金额
	private String opNo;//计提人员
	private String txDate;//计提日期
	private String filler;//备注
// 为获取RPT_PRDT_DAILY中产品号跟累计金额
	private String prdtNo;//产品号
	private Double amtTot;//累计金额
	private Double maxAmt;//阶梯放贷规模上线
	private Float offRate;//产品折扣率
	private String nowDate;//当前日期
	
	private String loginid;//修改人员

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
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
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
	 * @return 融资总成本
	 */
	public Double getFinCost() {
	 	return finCost;
	}
	/**
	 * @设置 融资总成本
	 * @param finCost
	 */
	public void setFinCost(Double finCost) {
	 	this.finCost = finCost;
	}
	/**
	 * @return 受益人总收益
	 */
	public Double getPayAmt() {
	 	return payAmt;
	}
	/**
	 * @设置 受益人总收益
	 * @param payAmt
	 */
	public void setPayAmt(Double payAmt) {
	 	this.payAmt = payAmt;
	}
	/**
	 * @return 融资总报酬
	 */
	public Double getFinlIncome() {
	 	return finlIncome;
	}
	/**
	 * @设置 融资总报酬
	 * @param finlIncome
	 */
	public void setFinlIncome(Double finlIncome) {
	 	this.finlIncome = finlIncome;
	}
	/**
	 * @return 项目管理费
	 */
	public Double getManagerFee() {
	 	return managerFee;
	}
	/**
	 * @设置 项目管理费
	 * @param managerFee
	 */
	public void setManagerFee(Double managerFee) {
	 	this.managerFee = managerFee;
	}
	/**
	 * @return 放贷服务费
	 */
	public Double getServiceFee() {
	 	return serviceFee;
	}
	/**
	 * @设置 放贷服务费
	 * @param serviceFee
	 */
	public void setServiceFee(Double serviceFee) {
	 	this.serviceFee = serviceFee;
	}
	/**
	 * @return 计提总金额
	 */
	public Double getDrawingAmt() {
	 	return drawingAmt;
	}
	/**
	 * @设置 计提总金额
	 * @param drawingAmt
	 */
	public void setDrawingAmt(Double drawingAmt) {
	 	this.drawingAmt = drawingAmt;
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
	/**
	 * 
	 * @作者 DHCC-ZLC
	 * @日期 2016-6-23
	 * @方法说明：
	 * @返回参数 String
	 */
	public String getPrdtNo() {
		return prdtNo;
	}
	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}
	public Double getAmtTot() {
		return amtTot;
	}
	public void setAmtTot(Double amtTot) {
		this.amtTot = amtTot;
	}
	public Double getMaxAmt() {
		return maxAmt;
	}
	public void setMaxAmt(Double maxAmt) {
		this.maxAmt = maxAmt;
	}
	public Float getOffRate() {
		return offRate;
	}
	public void setOffRate(Float offRate) {
		this.offRate = offRate;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
}