package app.creditapp.inf.entity;

/**
 * @作者 DHCC-ZKX
 * @日期 July 22, 2016 
 * @描述   B类提前还款批量申请申请[2804] 请求数据 list
 */
public class WsIn2804_1 {
	
	private String pactNo;//合同号 [主键]
	private double payTotal;//还款金额[=4+5+6+7+8]
	private double payAmt;//还款本金
	private double payInte;//还款利息
	private double payOver;//罚息
	private double feeTotal;//费用
	private double feeDam	;//违约金
	
	private String dealSts;//处理状态
	private String dealDesc;//处理描述
	
	public WsIn2804_1() {
	
		dealSts = "01";
		dealDesc = "";
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
	public double getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(double payAmt) {
		this.payAmt = payAmt;
	}
	public double getPayInte() {
		return payInte;
	}
	public void setPayInte(double payInte) {
		this.payInte = payInte;
	}
	public double getPayOver() {
		return payOver;
	}
	public void setPayOver(double payOver) {
		this.payOver = payOver;
	}
	public double getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
	}
	public double getFeeDam() {
		return feeDam;
	}
	public void setFeeDam(double feeDam) {
		this.feeDam = feeDam;
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
	
}
