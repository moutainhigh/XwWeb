package app.creditapp.inf.entity;

/**
 * @作者 DHCC-ZKX
 * @日期 July 18, 2016
 * list
 * @描述   还款计划上传--输入实体类
 */
public class WsIn2201_1 {
	private String pactNo;//合同号：合同号+期次为主键
	private int cnt;//期次
	private int begDate;//起始日期
	private int endDate;//截止日期
	private double totalAmt;//期供金额：=当期本金+当期利息
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
	public int getEndDate() {
		return endDate;
	}
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
	public double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
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
	public int getBegDate() {
		return begDate;
	}
	public void setBegDate(int begDate) {
		this.begDate = begDate;
	}
	
}
