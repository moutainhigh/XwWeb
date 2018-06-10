package app.creditapp.inf.entity;


/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016 list
 * @描述  欠款信息查询[3203] 输出信息
 */
public class WsOut3203_1{

	private String pactNo;//合同号
	private String cnt;//欠款期次
	private String payDate;//应还日期
	private double payAmt;//欠本金额
	private double payInte;//欠正常利息
	private double payOver;//罚息
	private double repayAmt;//已归还本金金额
	private double repayInte;//已归还正常利息
	private double repayOver;//已归还罚息金额
	private double refAmt;//减免本金
	private double refInte;//减免正常利息
	private double refOver;//减免罚息
	private String amtSts;//本金状态
	private String inteSts;//利息状态
	private String lnSts;//结清状态
	private String overDate;//转逾日期
	private String fineDate;//上次结罚息日期
	private String finDate;//结清日期
	
	
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
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
	public double getRepayAmt() {
		return repayAmt;
	}
	public void setRepayAmt(double repayAmt) {
		this.repayAmt = repayAmt;
	}
	public double getRepayInte() {
		return repayInte;
	}
	public void setRepayInte(double repayInte) {
		this.repayInte = repayInte;
	}
	public double getRepayOver() {
		return repayOver;
	}
	public void setRepayOver(double repayOver) {
		this.repayOver = repayOver;
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
	public String getAmtSts() {
		return amtSts;
	}
	public void setAmtSts(String amtSts) {
		this.amtSts = amtSts;
	}
	public String getInteSts() {
		return inteSts;
	}
	public void setInteSts(String inteSts) {
		this.inteSts = inteSts;
	}
	public String getLnSts() {
		return lnSts;
	}
	public void setLnSts(String lnSts) {
		this.lnSts = lnSts;
	}
	public String getOverDate() {
		return overDate;
	}
	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}
	public String getFineDate() {
		return fineDate;
	}
	public void setFineDate(String fineDate) {
		this.fineDate = fineDate;
	}
	public String getFinDate() {
		return finDate;
	}
	public void setFinDate(String finDate) {
		this.finDate = finDate;
	}
	
	
}
