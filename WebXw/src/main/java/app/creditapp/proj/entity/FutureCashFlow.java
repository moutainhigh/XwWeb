package app.creditapp.proj.entity;
import app.base.BaseDomain;
/**
* Title: ProjAcct.java
* Description:
* @version：1.0
**/
public class FutureCashFlow extends BaseDomain {
	private String projName;//项目名称
	private String projNo;//项目编号[外键]
	private String beginDate;//开始日期
	private String endDate;//截止日期
	private Double inAmtAvg;//日期入款	
	private Double outAmtAvg;//日期出款	
	private Double inAmt;//入款	
	private Double outAmt;//出款	
	private Double restAmt;//剩款		
	private String txDate;//登记日期
	private String opNo;//操作员
	private String CalCulType;//交易类型
	private int userDays; //所用天数
	private Double bal;//余额
	public Double getBal() {
		return bal;
	}
	public void setBal(Double bal) {
		this.bal = bal;
	}
	private Double userAmt;//所用金额
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Double getInAmtAvg() {
		return inAmtAvg;
	}
	public void setInAmtAvg(Double inAmtAvg) {
		this.inAmtAvg = inAmtAvg;
	}
	public Double getOutAmtAvg() {
		return outAmtAvg;
	}
	public void setOutAmtAvg(Double outAmtAvg) {
		this.outAmtAvg = outAmtAvg;
	}
	public Double getInAmt() {
		return inAmt;
	}
	public void setInAmt(Double inAmt) {
		this.inAmt = inAmt;
	}
	public Double getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(Double outAmt) {
		this.outAmt = outAmt;
	}
	public Double getRestAmt() {
		return restAmt;
	}
	public void setRestAmt(Double restAmt) {
		this.restAmt = restAmt;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getOpNo() {
		return opNo;
	}
	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}
	public String getCalCulType() {
		return CalCulType;
	}
	public void setCalCulType(String calCulType) {
		CalCulType = calCulType;
	}
	public int getUserDays() {
		return userDays;
	}
	public void setUserDays(int userDays) {
		this.userDays = userDays;
	}

	public Double getUserAmt() {
		return userAmt;
	}
	public void setUserAmt(Double userAmt) {
		this.userAmt = userAmt;
	}

}