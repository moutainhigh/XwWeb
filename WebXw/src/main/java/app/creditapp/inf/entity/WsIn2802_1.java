package app.creditapp.inf.entity;

/**
 * @作者 DHCC-ZKX
 * @日期 July 22, 2016
 * listPlan
 * @描述   B类提前还款申请[2802] 请求数据 
 */
public class WsIn2802_1 {
	private String pactNo;//合同号：合同号+期次为主键
	private int cnt;//期次
	private int begDt;//起始日期
	private int endDt;//截止日期
	private double instmAmt;//期供金额：=当期本金+当期利息
	private double prcpAmt;//当期本金
	private double normInt;//当期利息
	//数据库中有，请求数据中没有的
	private String brNo;//合作机构编号
	private String batchNo;//批次号
	
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getBegDt() {
		return begDt;
	}
	public void setBegDt(int begDt) {
		this.begDt = begDt;
	}
	public int getEndDt() {
		return endDt;
	}
	public void setEndDt(int endDt) {
		this.endDt = endDt;
	}
	public double getInstmAmt() {
		return instmAmt;
	}
	public void setInstmAmt(double instmAmt) {
		this.instmAmt = instmAmt;
	}
	public double getPrcpAmt() {
		return prcpAmt;
	}
	public void setPrcpAmt(double prcpAmt) {
		this.prcpAmt = prcpAmt;
	}
	public double getNormInt() {
		return normInt;
	}
	public void setNormInt(double normInt) {
		this.normInt = normInt;
	}
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
	
}
