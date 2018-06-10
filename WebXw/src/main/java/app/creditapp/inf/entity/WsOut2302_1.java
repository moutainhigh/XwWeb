package app.creditapp.inf.entity;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述   扣款结果查询[2302]list 输出结果
 */
public class WsOut2302_1 {
	
	private String pactNo;//合同号
	private String serialNo;//扣款流水号	
	private double repayTotal;//	实扣金额
	private double repayAmt;//实扣本金
	private double repayInte;//实扣利息
	private double repayOver;//实扣罚息
	private double feeTotal;//实扣费总额
	private String dealSts;//扣款状态
	private String dealDesc;//扣款描述
	
	//数据库中有，而接口文档中没有的字段
	private String batchNo;//批次号
	
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public double getRepayTotal() {
		return repayTotal;
	}
	public void setRepayTotal(double repayTotal) {
		this.repayTotal = repayTotal;
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
	public double getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	
	
	
}
