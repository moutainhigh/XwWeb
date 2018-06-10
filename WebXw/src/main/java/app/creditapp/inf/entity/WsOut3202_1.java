package app.creditapp.inf.entity;

import java.util.List;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016 list
 * @描述   账户流水查询[3202] 输出信息
 */
public class WsOut3202_1{

	private String traceNo;//流水号
	private String pactNo;//合同号
	private String txCode;//交易码
	private String txDate;//交易日期
	private String txTime;//交易时间
	private double txAmt;//发生额
	private String memo;//摘要
	
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getTxCode() {
		return txCode;
	}
	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getTxTime() {
		return txTime;
	}
	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	public double getTxAmt() {
		return txAmt;
	}
	public void setTxAmt(double txAmt) {
		this.txAmt = txAmt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}
