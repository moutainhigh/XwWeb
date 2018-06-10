package app.creditapp.inf.entity;

import app.base.BaseDomain;

/**
 * @作者 DHCC-WANG
 * @日期 Jul 21, 2016
 * list
 * @描述   提前还款测算---输出实体类
 */
public class WsOut2806 extends BaseDomain{
	
	private String brNo;     //合作机构号
	private String pactNo;   //合同号          主键
	private String payDate;  //还款日期
	private Double payAmt;   //还款金额        =5+10+11+12+13
	private Double payTotal; //欠款合计        =6+7+8+9
	private Double payAmt2;   //欠本
	private Double payInte;  //欠息
	private Double payOver;  //罚息
	private Double payFee;   //欠费用合计
	private Double curInte;  //归还利息        当期未出账单应收利息
	private Double curAmt;   //归还本金        =4-(5+10+12+13)
	private Double curFee;   //归还费用        期缴费
	private Double payDam;   //提前还款违约金
	private Double bal;      //剩余本金
	private Double premAmt;   //保费    （当期）
	private Double serAmt;   //服务费（当期）
	private Double feeTotal;//当期费用总额
	private Double totalAmt;//还款总额
	/**
	 * @return 合作机构号   
	 */
	public String getBrNo() {
		return brNo;
	}
	/**
	 * @param 合作机构号   
	 */
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	/**
	 * @return 合同号    主键       
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
	 * @return 还款日期    
	 */
	public String getPayDate() {
		return payDate;
	}
	/**
	 * @param 还款日期    
	 */
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	/**
	 * @return 还款金额   =5+10+11+12+13   
	 */
	public Double getPayAmt() {
		return payAmt;
	}
	/**
	 * @param 还款金额    =5+10+11+12+13   
	 */
	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}
	/**
	 * @return 欠款合计        =6+7+8+9       
	 */
	public Double getPayTotal() {
		return payTotal;
	}
	/**
	 * @param 欠款合计        =6+7+8+9       
	 */
	public void setPayTotal(Double payTotal) {
		this.payTotal = payTotal;
	}
	/**
	 * @return 欠本
	 */
	public Double getPayAmt2() {
		return payAmt2;
	}
	/**
	 * @param 欠本
	 */
	public void setPayAmt2(Double payAmt2) {
		this.payAmt2 = payAmt2;
	}
	/**
	 * @return 欠息
	 */
	public Double getPayInte() {
		return payInte;
	}
	/**
	 * @param 欠息
	 */
	public void setPayInte(Double payInte) {
		this.payInte = payInte;
	}
	/**
	 * @return 罚息
	 */
	public Double getPayOver() {
		return payOver;
	}
	/**
	 * @param 罚息
	 */
	public void setPayOver(Double payOver) {
		this.payOver = payOver;
	}
	/**
	 * @return 欠费用合计    
	 */
	public Double getPayFee() {
		return payFee;
	}
	/**
	 * @param 欠费用合计    
	 */
	public void setPayFee(Double payFee) {
		this.payFee = payFee;
	}
	/**
	 * @return 归还利息        当期未出账单应收利息
	 */
	public Double getCurInte() {
		return curInte;
	}
	/**
	 * @param 归还利息        当期未出账单应收利息
	 */
	public void setCurInte(Double curInte) {
		this.curInte = curInte;
	}
	/**
	 * @return 归还本金        =4-(5+10+12+13) 
	 */
	public Double getCurAmt() {
		return curAmt;
	}
	/**
	 * @param 归还本金        =4-(5+10+12+13) 
	 */
	public void setCurAmt(Double curAmt) {
		this.curAmt = curAmt;
	}
	/**
	 * @return 归还费用        期缴费
	 */
	public Double getCurFee() {
		return curFee;
	}
	/**
	 * @param 归还费用        期缴费
	 */
	public void setCurFee(Double curFee) {
		this.curFee = curFee;
	}
	/**
	 * @return 提前还款违约金
	 */
	public Double getPayDam() {
		return payDam;
	}
	/**
	 * @param 提前还款违约金
	 */
	public void setPayDam(Double payDam) {
		this.payDam = payDam;
	}
	/**
	 * @return 剩余本金
	 */
	public Double getBal() {
		return bal;
	}
	/**
	 * @param 剩余本金
	 */
	public void setBal(Double bal) {
		this.bal = bal;
	}
	public Double getPremAmt() {
		return premAmt;
	}
	public void setPremAmt(Double premAmt) {
		this.premAmt = premAmt;
	}
	public Double getSerAmt() {
		return serAmt;
	}
	public void setSerAmt(Double serAmt) {
		this.serAmt = serAmt;
	}
	public Double getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(Double feeTotal) {
		this.feeTotal = feeTotal;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	
}
