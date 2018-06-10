package app.creditapp.ln.entity;

import app.base.BaseDomain;

/**
 * @title 放款统计
 * @author supenghui
 */
public class LoanCount extends BaseDomain{
//	private String loanCountNo;
	private String batchNo;  //批次号
	private String brNo;     //合作机构号
	private String pactNo;   //合同号
	private String cifName;  //客户名
	private String upDate;   //更新时间
	private String curNo;    //币种
	private String loanAmt;  //放款金额
	private String loanSts;  //放款状态
	private String loanBankCode;  //开户银行代码
	private String loanAcNo;  //放款账号
	private String cardChn;    //支付渠道
	private String projNo;    //项目号
	private String begDt;    //起始日期
	private String endDt;    //终止日期
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	//	public String getLoanCountNo() {
//		return loanCountNo;
//	}
//	public void setLoanCountNo(String loanCountNo) {
//		this.loanCountNo = loanCountNo;
//	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public String getUpDate() {
		return upDate;
	}
	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}
	public String getCurNo() {
		return curNo;
	}
	public void setCurNo(String curNo) {
		this.curNo = curNo;
	}
	public String getLoanSts() {
		return loanSts;
	}
	public void setLoanSts(String loanSts) {
		this.loanSts = loanSts;
	}
	public String getLoanBankCode() {
		return loanBankCode;
	}
	public void setLoanBankCode(String loanBankCode) {
		this.loanBankCode = loanBankCode;
	}
	public String getLoanAcNo() {
		return loanAcNo;
	}
	public void setLoanAcNo(String loanAcNo) {
		this.loanAcNo = loanAcNo;
	}
	public String getCardChn() {
		return cardChn;
	}
	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getBegDt() {
		return begDt;
	}
	public void setBegDt(String begDt) {
		this.begDt = begDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	
	

}
