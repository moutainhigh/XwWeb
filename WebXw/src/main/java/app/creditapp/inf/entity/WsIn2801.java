package app.creditapp.inf.entity;

/**
 * A类提前还款单笔申请[2801]
 *请求数据实体类
 */
public class WsIn2801 {
	private String brNo;//	合作机构号
	private String pactNo;//	合同号  : 主键
	private double payTotal;//	还款金额
	private String onlinOff;//线上 01 线下 02
	private String debitType; // 还款类型  01-部分还款  02-全额还款
	private double payAmt;//	还本金额
	
	private String dealSts;//处理状态
	private String dealDesc;//处理描述
	
	public WsIn2801() {
	
		dealSts = "01";
		dealDesc = "";
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
	public double getPayTotal() {
		return payTotal;
	}
	public void setPayTotal(double payTotal) {
		this.payTotal = payTotal;
	}


	public String getDealSts() {
		return dealSts;
	}


	public void setDealSts(String dealSts) {
		this.dealSts = dealSts;
	}


	public String getDealDesc() {
		return dealDesc;
	}


	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}


	public String getOnlinOff() {
		return onlinOff;
	}


	public void setOnlinOff(String onlinOff) {
		this.onlinOff = onlinOff;
	}


	public String getDebitType() {
		return debitType;
	}


	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}


	public double getPayAmt() {
		return payAmt;
	}


	public void setPayAmt(double payAmt) {
		this.payAmt = payAmt;
	}
	
}
