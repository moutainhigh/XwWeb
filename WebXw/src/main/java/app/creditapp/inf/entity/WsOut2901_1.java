package app.creditapp.inf.entity;


/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述   息费减免申请[2901] 输出信息
 */
public class WsOut2901_1 {
	

	private String pactNo;//合同号
	private String dealDesc;// 错误描述
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getDealDesc() {
		return dealDesc;
	}
	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}
	
}
