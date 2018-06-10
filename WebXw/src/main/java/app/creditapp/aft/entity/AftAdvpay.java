package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftAdvpay.java
* Description:
* @version：1.0
**/
public class AftAdvpay extends BaseDomain {
	private String repId;//还款ID
	private String pactId;//合同ID
	private String pactNo;//合同编号
	private String brNo;//合作机构
	private Double pactAmt;//合同金额
	private Double bal;//合同余额
	private Double intst;//应收欠息
	private Double payAmt;//还款金额
	private String acType;//还款账户类型
	private String acNo;//还款账户号
	private String acName;//还款账户户名
	private String acBankname;//还款账户开户行
	private String repReason;//提前还款原因
	private String repType;//还款类型[01全部还款02部分还款]
	private String repSts;//还款状态
	private String opNo;//操作员
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String payDate;//还款日期
	private String repayType;//还款类型[01-期数不变，缩短期供 02-期供不变，缩短期数]
	private String endDate;//到期日期
	private String brName;//合作机构名称
	private String opName;//操作员名称
	private String traceNo;//主流水编号
	private String onlinOff;//线上 01 线下 02
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	/**
	 * @return 还款ID
	 */
	public String getRepId() {
	 	return repId;
	}
	/**
	 * @设置 还款ID
	 * @param repId
	 */
	public void setRepId(String repId) {
	 	this.repId = repId;
	}
	/**
	 * @return 合同ID
	 */
	public String getPactId() {
	 	return pactId;
	}
	/**
	 * @设置 合同ID
	 * @param pactId
	 */
	public void setPactId(String pactId) {
	 	this.pactId = pactId;
	}
	/**
	 * @return 合同编号
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同编号
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 合作机构
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 合同金额
	 */
	public Double getPactAmt() {
	 	return pactAmt;
	}
	/**
	 * @设置 合同金额
	 * @param pactAmt
	 */
	public void setPactAmt(Double pactAmt) {
	 	this.pactAmt = pactAmt;
	}
	/**
	 * @return 合同余额
	 */
	public Double getBal() {
	 	return bal;
	}
	/**
	 * @设置 合同余额
	 * @param bal
	 */
	public void setBal(Double bal) {
	 	this.bal = bal;
	}
	/**
	 * @return 应收欠息
	 */
	public Double getIntst() {
	 	return intst;
	}
	/**
	 * @设置 应收欠息
	 * @param intst
	 */
	public void setIntst(Double intst) {
	 	this.intst = intst;
	}
	/**
	 * @return 还款金额
	 */
	public Double getPayAmt() {
	 	return payAmt;
	}
	/**
	 * @设置 还款金额
	 * @param payAmt
	 */
	public void setPayAmt(Double payAmt) {
	 	this.payAmt = payAmt;
	}
	/**
	 * @return 还款账户类型
	 */
	public String getAcType() {
	 	return acType;
	}
	/**
	 * @设置 还款账户类型
	 * @param acType
	 */
	public void setAcType(String acType) {
	 	this.acType = acType;
	}
	/**
	 * @return 还款账户号
	 */
	public String getAcNo() {
	 	return acNo;
	}
	/**
	 * @设置 还款账户号
	 * @param acNo
	 */
	public void setAcNo(String acNo) {
	 	this.acNo = acNo;
	}
	/**
	 * @return 还款账户户名
	 */
	public String getAcName() {
	 	return acName;
	}
	/**
	 * @设置 还款账户户名
	 * @param acName
	 */
	public void setAcName(String acName) {
	 	this.acName = acName;
	}
	/**
	 * @return 还款账户开户行
	 */
	public String getAcBankname() {
	 	return acBankname;
	}
	/**
	 * @设置 还款账户开户行
	 * @param acBankname
	 */
	public void setAcBankname(String acBankname) {
	 	this.acBankname = acBankname;
	}
	/**
	 * @return 提前还款原因
	 */
	public String getRepReason() {
	 	return repReason;
	}
	/**
	 * @设置 提前还款原因
	 * @param repReason
	 */
	public void setRepReason(String repReason) {
	 	this.repReason = repReason;
	}
	/**
	 * @return 还款类型[01全部还款02部分还款]
	 */
	public String getRepType() {
	 	return repType;
	}
	/**
	 * @设置 还款类型[01全部还款02部分还款]
	 * @param repType
	 */
	public void setRepType(String repType) {
	 	this.repType = repType;
	}
	/**
	 * @return 还款状态
	 */
	public String getRepSts() {
	 	return repSts;
	}
	/**
	 * @设置 还款状态
	 * @param repSts
	 */
	public void setRepSts(String repSts) {
	 	this.repSts = repSts;
	}
	/**
	 * @return 操作员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 操作员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 登记日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 登记日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 更新日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 更新日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getRepayType() {
		return repayType;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getOnlinOff() {
		return onlinOff;
	}
	public void setOnlinOff(String onlinOff) {
		this.onlinOff = onlinOff;
	}
}