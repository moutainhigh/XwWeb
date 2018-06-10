package app.creditapp.inf.entity;
/**
 * @作者 DHCC-ZKX
 * @日期 July 26, 2016
 * @描述   合同信息查询--输出实体类list
 */
public class WsOut2105_1 {

	private String pactNo;//合同号
	private String custName;//借款人名称
	private String prdtNo;//产品编号
	private String projNo;//项目编号
	private double pactAmt;//合同金额
	private double bal;//合同余额
	private String begDate;//放款日期
	private String endDate;//到期日期
	private String expMtrDt;//展期到期日
	private double expRate;//展期利率
	private String repayDay;//还款日
	private double lnRate;//月利率
	private double lnRateYear;//年利率
	private String lnSts;//合同状态[01-正常 02-待逾期 03-逾期 04-结清 05-已回购 06-合同取消]
	
	
	public double getLnRateYear() {
		return lnRateYear;
	}
	public void setLnRateYear(double lnRateYear) {
		this.lnRateYear = lnRateYear;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPrdtNo() {
		return prdtNo;
	}
	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExpMtrDt() {
		return expMtrDt;
	}
	public void setExpMtrDt(String expMtrDt) {
		this.expMtrDt = expMtrDt;
	}
	public String getRepayDay() {
		return repayDay;
	}
	public void setRepayDay(String repayDay) {
		this.repayDay = repayDay;
	}
	public String getLnSts() {
		return lnSts;
	}
	public void setLnSts(String lnSts) {
		this.lnSts = lnSts;
	}
	public double getPactAmt() {
		return pactAmt;
	}
	public void setPactAmt(double pactAmt) {
		this.pactAmt = pactAmt;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	public double getExpRate() {
		return expRate;
	}
	public void setExpRate(double expRate) {
		this.expRate = expRate;
	}
	public double getLnRate() {
		return lnRate;
	}
	public void setLnRate(double lnRate) {
		this.lnRate = lnRate;
	}

}
