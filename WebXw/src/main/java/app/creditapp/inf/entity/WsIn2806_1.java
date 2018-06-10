package app.creditapp.inf.entity;

import app.base.BaseDomain;


/**
 * @作者 su
 * @日期 Jan 3, 2018
 * @描述   线上提前还款测算
 */
public class WsIn2806_1 extends BaseDomain{
	
	private String brNo; // 合作机构编号
	private String pactNo;// 合同号  主键
	private String payDate; // 还款日期  大于等于当前日期
	private String repType; // 还款类型  01-部分还款  02-全额还款
	private Double payAmt; // 还款金额
	
	/**
	 * @return 合作机构编号
	 */
	public String getBrNo() {
		return brNo;
	}
	/**
	 * @param 合作机构编号
	 */
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	/**
	 * @return 合同号  主键
	 */
	public String getPactNo() {
		return pactNo;
	}
	/**
	 * @param 合同号  主键
	 */
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	/**
	 * @return 还款日期  大于等于当前日期
	 */
	public String getPayDate() {
		return payDate;
	}
	/**
	 * @param 还款日期  大于等于当前日期
	 */
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	/**
	 * @return 还款类型  01-部分还款  02-全额还款
	 */
	public String getRepType() {
		return repType;
	}
	/**
	 * @param 还款类型  01-部分还款  02-全额还款
	 */
	public void setRepType(String repType) {
		this.repType = repType;
	}
	/**
	 * @return 还款金额
	 */
	public Double getPayAmt() {
		return payAmt;
	}
	/**
	 * @param 还款金额
	 */
	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}
	
}
