package app.creditapp.inf.entity;
/**
 * @作者 DHCC-SONG
 * @日期 Jun 28, 2016
 * @描述   预审批单笔申请[2004] 输出参数
 */
public class WsOut2004 {
	
	private String prePactNo ;// 预审批ID
	private String dealSts;// 筛查结果[01成功02黑名单客户03评级拒绝04借款人原因05其他原因]
	private String dealDesc;// 筛查结果描述

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
