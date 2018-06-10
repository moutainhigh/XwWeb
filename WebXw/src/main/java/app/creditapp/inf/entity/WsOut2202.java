package app.creditapp.inf.entity;

/**
 * @作者 DHCC-ZKX
 * @日期 July 18, 2016 list
 * @描述 还款计划单笔查询--输出实体类
 */
public class WsOut2202 {
	private String pactNo;// 合同号：合同号+期次为主键
	private int cnt;// 期次
	private int endDate;// 账单日
	private double totalAmt;// 期供金额
	private double prcpAmt;// 当期本金
	private double normInt;// 当期利息
	private String sts;// 本期状态：01未结清 02已结清
	private double fineInt;// 罚息
	private double repayPrcpAmt;// 已还本金
	private double repayNormInt;// 已还利息
	private double repayFineInt;// 已还罚息
	private double wvPrcpAmt;// 减免本金
	private double wvNormInt;// 减免利息
	private double wvFineInt;// 减免罚息
	private String prcpSts;// 本金结清状态
	private String brName; //合作机构名称
	
	
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
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	public double getFineInt() {
		return fineInt;
	}
	public void setFineInt(double fineInt) {
		this.fineInt = fineInt;
	}
	public double getRepayPrcpAmt() {
		return repayPrcpAmt;
	}
	public void setRepayPrcpAmt(double repayPrcpAmt) {
		this.repayPrcpAmt = repayPrcpAmt;
	}
	public double getRepayNormInt() {
		return repayNormInt;
	}
	public void setRepayNormInt(double repayNormInt) {
		this.repayNormInt = repayNormInt;
	}
	public double getRepayFineInt() {
		return repayFineInt;
	}
	public void setRepayFineInt(double repayFineInt) {
		this.repayFineInt = repayFineInt;
	}
	public double getWvPrcpAmt() {
		return wvPrcpAmt;
	}
	public void setWvPrcpAmt(double wvPrcpAmt) {
		this.wvPrcpAmt = wvPrcpAmt;
	}
	public double getWvNormInt() {
		return wvNormInt;
	}
	public void setWvNormInt(double wvNormInt) {
		this.wvNormInt = wvNormInt;
	}
	public double getWvFineInt() {
		return wvFineInt;
	}
	public void setWvFineInt(double wvFineInt) {
		this.wvFineInt = wvFineInt;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getPrcpSts() {
		return prcpSts;
	}
	public void setPrcpSts(String prcpSts) {
		this.prcpSts = prcpSts;
	}

	
	
}
