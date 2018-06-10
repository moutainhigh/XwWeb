package app.creditapp.inf.entity;



/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * list
 * @描述    进件处理结果查询---输出实体类
 */
public class WsOut2102 {
	
	private String pactNo; //合同号码
	private String dealSts;//处理结果
	private String dealDesc; //处理描述
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
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
