package accounting.interf.loan;

import java.util.Map;

import accounting.domain.sys.AfferentDomain;

public class CalFee extends AfferentDomain {

	private String traceNo;		//流水号
	private int traceCnt=1;		//流水序号  
	private String loanNo;		//借据号
	private String txCode;		//交易码
	private double txAmt;		//本次交易本金
	private String curNo;		//币种
	private String saveInd;		//是否保存标志
	private String prdtNo;		//信贷产品编号

	
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	public String getTxCode() {
		return txCode;
	}
	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}
	public String getCurNo() {
		return curNo;
	}
	public void setCurNo(String curNo) {
		this.curNo = curNo;
	}
	public double getTxAmt() {
		return txAmt;
	}
	public void setTxAmt(double txAmt) {
		this.txAmt = txAmt;
	}
	public String getSaveInd() {
		return saveInd;
	}
	public void setSaveInd(String saveInd) {
		this.saveInd = saveInd;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public int getTraceCnt() {
		return traceCnt;
	}
	public void setTraceCnt(int traceCnt) {
		this.traceCnt = traceCnt;
	}
	public String getPrdtNo() {
		return prdtNo;
	}
	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}
	
}
