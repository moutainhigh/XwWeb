package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述      息费减免结果查询[2902] 输出 信息 list
 */
public class WsOut2902_1 {
	
	private String pactNo;// 合同号
	private double refAmt;// 减免本金
	private double refInte;// 减免利息
	private double refOver;//减免罚息
	private double refFee;// 减免费用
	private String dealSts;//处理状态
	private String dealDesc;//处理说明
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public double getRefAmt() {
		return refAmt;
	}
	public void setRefAmt(double refAmt) {
		this.refAmt = refAmt;
	}
	public double getRefInte() {
		return refInte;
	}
	public void setRefInte(double refInte) {
		this.refInte = refInte;
	}
	public double getRefOver() {
		return refOver;
	}
	public void setRefOver(double refOver) {
		this.refOver = refOver;
	}
	public double getRefFee() {
		return refFee;
	}
	public void setRefFee(double refFee) {
		this.refFee = refFee;
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
