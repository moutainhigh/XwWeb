package app.creditapp.inf.entity;

import app.base.BaseDomain;


/**
 * @作者 DHCC-WANG
 * @日期 Jul 21, 2016
 * @描述   提前还款测算--输入实体类 ln_batch
 */
public class WsIn2806 extends BaseDomain{
	
	private String brNo; // 合作机构编号
	private String pactNo;// 合同号  主键
	private String payDate; // 还款日期  大于等于当前日期
	private String payType; // 还款类型  01-部分还款  02-全额还款
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
	public String getPayType() {
		return payType;
	}
	/**
	 * @param 还款类型  01-部分还款  02-全额还款
	 */
	public void setPayType(String payType) {
		this.payType = payType;
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
