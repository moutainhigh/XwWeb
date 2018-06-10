package app.creditapp.inf.entity;
/**
 * A类提前还款批量申请[2803]
 *请求数据list实体类
 */
public class WsIn2803_1 {

	private String pactNo;//合同号[主键]
	private double payTotal;//还款金额
	
	private String dealSts;//处理状态
	private String dealDesc;//处理描述
	
	public WsIn2803_1() {
	
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
