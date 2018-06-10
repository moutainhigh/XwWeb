package app.creditapp.inf.entity;

import app.base.BaseDomain;
/**
* Title: WsRepyMes_Count.java
* Description:
* @version：1.0
**/
public class WsRepyMes_Count extends BaseDomain {
	
	private String brNo;//合作机构编号
	private String brName;//合作机构名称
	private String deal_sts;//扣款状态[]
	private String deal_desc;//扣款描述
	private Integer repay_count;//总量
	private Double repay_sum;//代扣费总额:代合作机构收取
	private String txDate;//扣款日期
	private String txTime;//开始时间
	private String endTime;//结束时间
	
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getDeal_sts() {
		return deal_sts;
	}
	public void setDeal_sts(String deal_sts) {
		this.deal_sts = deal_sts;
	}
	public String getDeal_desc() {
		return deal_desc;
	}
	public void setDeal_desc(String deal_desc) {
		this.deal_desc = deal_desc;
	}
	public Integer getRepay_count() {
		return repay_count;
	}
	public void setRepay_count(Integer repay_count) {
		this.repay_count = repay_count;
	}
	public Double getRepay_sum() {
		return repay_sum;
	}
	public void setRepay_sum(Double repay_sum) {
		this.repay_sum = repay_sum;
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
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}