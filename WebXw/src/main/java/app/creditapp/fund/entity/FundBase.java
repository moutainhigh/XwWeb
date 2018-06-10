package app.creditapp.fund.entity;
import app.base.BaseDomain;
/**
* Title: FundBase.java
* Description:
* @version：1.0
**/
public class FundBase extends BaseDomain {
	private String fundNo;//资金编号
	private String fundName;//资金名称
	private String projNo;//项目编号
	private String projName;//项目名称
	private String curNo;//币种
	private Double fdInitialAmt;//资金初始规模
	private Double fdAmt;//资金当前规模
	private Double cashBal;//现金余额
	private Double rightBal;//债权余额
	private String begDate;//起始日期
	private String endDate;//结束日期
	private float financeRate;//融资利率
	private String repayType;//结息方式
	private String repayDay;//指定结息日
	private Integer yearDays;//年天数
	private String fdType;//资金性质
	private String reDeem;//赎买标志
	private String fdFlag;//生效标志	
	private String fdState;//资金状态
	private String packetSts;//封包标志
	private String packetDate;//封包时间
	private String tranSts;//转让标志
	private String tranDate;//转让时间
	private String filler;//备注
	private String opNo;//登记人员
	private String txDate;//登记日期
	private String upDate;//修改日期
	private String upOpno;//修改人员
	
	private String loginid;//操作员号
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
	 * @return 资金名称
	 */
	public String getFundName() {
	 	return fundName;
	}
	/**
	 * @设置 资金名称
	 * @param fundName
	 */
	public void setFundName(String fundName) {
	 	this.fundName = fundName;
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
	 * @return 项目名称
	 */
	public String getProjName() {
	 	return projName;
	}
	/**
	 * @设置 项目名称
	 * @param projName
	 */
	public void setProjName(String projName) {
	 	this.projName = projName;
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
	/**
	 * @return 资金初始规模
	 */
	public Double getFdInitialAmt() {
	 	return fdInitialAmt;
	}
	/**
	 * @设置 资金初始规模
	 * @param fdInitialAmt
	 */
	public void setFdInitialAmt(Double fdInitialAmt) {
	 	this.fdInitialAmt = fdInitialAmt;
	}
	/**
	 * @return 资金当前规模
	 */
	public Double getFdAmt() {
	 	return fdAmt;
	}
	/**
	 * @设置 资金当前规模
	 * @param fdAmt
	 */
	public void setFdAmt(Double fdAmt) {
	 	this.fdAmt = fdAmt;
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
	 * @return 结息方式
	 */
	public String getRepayType() {
	 	return repayType;
	}
	/**
	 * @设置 结息方式
	 * @param repayType
	 */
	public void setRepayType(String repayType) {
	 	this.repayType = repayType;
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
	 * @return 资金性质
	 */
	public String getFdType() {
	 	return fdType;
	}
	/**
	 * @设置 资金性质
	 * @param fdType
	 */
	public void setFdType(String fdType) {
	 	this.fdType = fdType;
	}
	/**
	 * @return 资金状态
	 */
	public String getFdState() {
	 	return fdState;
	}
	/**
	 * @设置 资金状态
	 * @param fdState
	 */
	public void setFdState(String fdState) {
	 	this.fdState = fdState;
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
	 * @return 登记人员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 登记人员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
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
	 * @设置 指定结息日
	 * @param SubpayType
	 */
	public String getRepayDay() {
		return repayDay;
	}
	public void setRepayDay(String repayDay) {
		this.repayDay = repayDay;
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
	 * @return 债权余额
	 */
	public Double getRightBal() {
	 	return rightBal;
	}
	/**
	 * @设置 债权余额
	 * @param rightBal
	 */
	public void setRightBal(Double rightBal) {
	 	this.rightBal = rightBal;
	}
	public String getReDeem() {
		return reDeem;
	}
	public void setReDeem(String reDeem) {
		this.reDeem = reDeem;
	}
	public String getFdFlag() {
		return fdFlag;
	}
	public void setFdFlag(String fdFlag) {
		this.fdFlag = fdFlag;
	}
	public String getPacketSts() {
		return packetSts;
	}
	public void setPacketSts(String packetSts) {
		this.packetSts = packetSts;
	}
	public String getPacketDate() {
		return packetDate;
	}
	public void setPacketDate(String packetDate) {
		this.packetDate = packetDate;
	}
	public String getTranSts() {
		return tranSts;
	}
	public void setTranSts(String tranSts) {
		this.tranSts = tranSts;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public void setFinanceRate(float financeRate) {
		this.financeRate = financeRate;
	}
}