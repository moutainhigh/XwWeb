package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016 list listAc（账户信息）
 * @描述   预审批结果查询[2002]--输出实体类list
 */
public class WsOut2002_1 {

	private String prePactNo; //预审批ID
	private String dealSts;//处理结果
	private String dealDesc;//处理描述
	
	
	public String getPrePactNo() {
		return prePactNo;
	}
	public void setPrePactNo(String prePactNo) {
		this.prePactNo = prePactNo;
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
