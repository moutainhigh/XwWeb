package app.creditapp.acc.loan.entity;
import app.base.BaseDomain;
/**
* Title: AcDebit.java
* @version：1.0
**/
public class AcDebit extends BaseDomain {
	private String traceNo;//交易流水号
	private Integer traceCnt;//交易流水子序号
	private String debitNo;//批扣流水号
	private String txDt;//交易日
	private Integer dcNo;//分录流水号
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private String acctBankCde;//扣款帐号银行代码
	private String acNo;//扣款账号
	private String xtAcNo;//信托账号/合作机构账号
	private String curNo;//扣款币种
	private String debitType;//扣款类别[01-本利罚，02-费用]
	private Double atpyAmt;//应扣金额
	private Double loAmt;//应扣欠款金额
	private Double curAmt;//应扣本期金额
	private Double myFeeAmt;//自收费金额
	private Double otherFeeAmt;//代收费金额
	private Double repayAmt;//实还金额
	private String sts;//批扣状态
	private String createDt;//创建日期
	private String spoolDt;//生成文件日期
	private String rtnDt;//扣款返回日期
	private String rtnTime;//扣款返回时间
	private Integer rtnTraceNo;//核心处理流水号
	private Integer revseTraceNo;//批量冲正流水号
	private String errDesc;//失败原因
	private String acType;//账户类型[10-个人贷记卡账户 11-个人借记卡账户 12-企业账户]
	private String acName;//帐户户名
	private String bankCode;//开户银行代码
	private String bankProv;//开户银行所在省
	private String bankCity;//开户银行所在市
	private String bankSite;//开户银行网点
	private String cardChn;//支付渠道
	private String busEntryType;//条目交易类型
	private String idType;
	private String idNo;
	private String phoneNo;
	private String validDate;
	private String cvvNo;

	private String failCaus;
	private String debitMold;//还款类型
	private String loginid;//登录人员
	/**
	 * @return 交易流水号
	 */
	public String getTraceNo() {
	 	return traceNo;
	}
	/**
	 * @设置 交易流水号
	 * @param traceNo
	 */
	public void setTraceNo(String traceNo) {
	 	this.traceNo = traceNo;
	}
	/**
	 * @return 交易流水子序号
	 */
	public Integer getTraceCnt() {
	 	return traceCnt;
	}
	/**
	 * @设置 交易流水子序号
	 * @param traceCnt
	 */
	public void setTraceCnt(Integer traceCnt) {
	 	this.traceCnt = traceCnt;
	}
	/**
	 * @return 批扣流水号
	 */
	public String getDebitNo() {
	 	return debitNo;
	}
	/**
	 * @设置 批扣流水号
	 * @param debitNo
	 */
	public void setDebitNo(String debitNo) {
	 	this.debitNo = debitNo;
	}
	/**
	 * @return 交易日
	 */
	public String getTxDt() {
	 	return txDt;
	}
	/**
	 * @设置 交易日
	 * @param txDt
	 */
	public void setTxDt(String txDt) {
	 	this.txDt = txDt;
	}
	/**
	 * @return 分录流水号
	 */
	public Integer getDcNo() {
	 	return dcNo;
	}
	/**
	 * @设置 分录流水号
	 * @param dcNo
	 */
	public void setDcNo(Integer dcNo) {
	 	this.dcNo = dcNo;
	}
	/**
	 * @return 借据号
	 */
	public String getLoanNo() {
	 	return loanNo;
	}
	/**
	 * @设置 借据号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
	 	this.loanNo = loanNo;
	}
	/**
	 * @return 合同号
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同号
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 机构号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 扣款帐号银行代码
	 */
	public String getAcctBankCde() {
	 	return acctBankCde;
	}
	/**
	 * @设置 扣款帐号银行代码
	 * @param acctBankCde
	 */
	public void setAcctBankCde(String acctBankCde) {
	 	this.acctBankCde = acctBankCde;
	}
	/**
	 * @return 扣款账号
	 */
	public String getAcNo() {
	 	return acNo;
	}
	/**
	 * @设置 扣款账号
	 * @param acNo
	 */
	public void setAcNo(String acNo) {
	 	this.acNo = acNo;
	}
	/**
	 * @return 信托账号/合作机构账号
	 */
	public String getXtAcNo() {
	 	return xtAcNo;
	}
	/**
	 * @设置 信托账号/合作机构账号
	 * @param xtAcNo
	 */
	public void setXtAcNo(String xtAcNo) {
	 	this.xtAcNo = xtAcNo;
	}
	/**
	 * @return 扣款币种
	 */
	public String getCurNo() {
	 	return curNo;
	}
	/**
	 * @设置 扣款币种
	 * @param curNo
	 */
	public void setCurNo(String curNo) {
	 	this.curNo = curNo;
	}
	/**
	 * @return 扣款类别[01-本利罚，02-费用]
	 */
	public String getDebitType() {
	 	return debitType;
	}
	/**
	 * @设置 扣款类别[01-本利罚，02-费用]
	 * @param debitType
	 */
	public void setDebitType(String debitType) {
	 	this.debitType = debitType;
	}
	/**
	 * @return 应扣金额
	 */
	public Double getAtpyAmt() {
	 	return atpyAmt;
	}
	/**
	 * @设置 应扣金额
	 * @param atpyAmt
	 */
	public void setAtpyAmt(Double atpyAmt) {
	 	this.atpyAmt = atpyAmt;
	}
	/**
	 * @return 应扣欠款金额
	 */
	public Double getLoAmt() {
	 	return loAmt;
	}
	/**
	 * @设置 应扣欠款金额
	 * @param loAmt
	 */
	public void setLoAmt(Double loAmt) {
	 	this.loAmt = loAmt;
	}
	/**
	 * @return 应扣本期金额
	 */
	public Double getCurAmt() {
	 	return curAmt;
	}
	/**
	 * @设置 应扣本期金额
	 * @param curAmt
	 */
	public void setCurAmt(Double curAmt) {
	 	this.curAmt = curAmt;
	}
	/**
	 * @return 实还金额
	 */
	public Double getRepayAmt() {
	 	return repayAmt;
	}
	/**
	 * @设置 实还金额
	 * @param repayAmt
	 */
	public void setRepayAmt(Double repayAmt) {
	 	this.repayAmt = repayAmt;
	}
	/**
	 * @return 批扣状态
	 */
	public String getSts() {
	 	return sts;
	}
	/**
	 * @设置 批扣状态
	 * @param sts
	 */
	public void setSts(String sts) {
	 	this.sts = sts;
	}
	/**
	 * @return 创建日期
	 */
	public String getCreateDt() {
	 	return createDt;
	}
	/**
	 * @设置 创建日期
	 * @param createDt
	 */
	public void setCreateDt(String createDt) {
	 	this.createDt = createDt;
	}
	/**
	 * @return 生成文件日期
	 */
	public String getSpoolDt() {
	 	return spoolDt;
	}
	/**
	 * @设置 生成文件日期
	 * @param spoolDt
	 */
	public void setSpoolDt(String spoolDt) {
	 	this.spoolDt = spoolDt;
	}
	/**
	 * @return 扣款返回日期
	 */
	public String getRtnDt() {
	 	return rtnDt;
	}
	/**
	 * @设置 扣款返回日期
	 * @param rtnDt
	 */
	public void setRtnDt(String rtnDt) {
	 	this.rtnDt = rtnDt;
	}
	/**
	 * @return 扣款返回时间
	 */
	public String getRtnTime() {
	 	return rtnTime;
	}
	/**
	 * @设置 扣款返回时间
	 * @param rtnTime
	 */
	public void setRtnTime(String rtnTime) {
	 	this.rtnTime = rtnTime;
	}
	/**
	 * @return 核心处理流水号
	 */
	public Integer getRtnTraceNo() {
	 	return rtnTraceNo;
	}
	/**
	 * @设置 核心处理流水号
	 * @param rtnTraceNo
	 */
	public void setRtnTraceNo(Integer rtnTraceNo) {
	 	this.rtnTraceNo = rtnTraceNo;
	}
	/**
	 * @return 批量冲正流水号
	 */
	public Integer getRevseTraceNo() {
	 	return revseTraceNo;
	}
	/**
	 * @设置 批量冲正流水号
	 * @param revseTraceNo
	 */
	public void setRevseTraceNo(Integer revseTraceNo) {
	 	this.revseTraceNo = revseTraceNo;
	}
	/**
	 * @return 失败原因
	 */
	public String getErrDesc() {
	 	return errDesc;
	}
	/**
	 * @设置 失败原因
	 * @param errDesc
	 */
	public void setErrDesc(String errDesc) {
	 	this.errDesc = errDesc;
	}
	public Double getMyFeeAmt() {
		return myFeeAmt;
	}
	public Double getOtherFeeAmt() {
		return otherFeeAmt;
	}
	public void setMyFeeAmt(Double myFeeAmt) {
		this.myFeeAmt = myFeeAmt;
	}
	public void setOtherFeeAmt(Double otherFeeAmt) {
		this.otherFeeAmt = otherFeeAmt;
	}
	public String getAcName() {
		return acName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public String getBankProv() {
		return bankProv;
	}
	public String getBankCity() {
		return bankCity;
	}
	public String getBankSite() {
		return bankSite;
	}
	public void setAcName(String acName) {
		this.acName = acName;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public void setBankProv(String bankProv) {
		this.bankProv = bankProv;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public void setBankSite(String bankSite) {
		this.bankSite = bankSite;
	}
	public String getAcType() {
		return acType;
	}
	public void setAcType(String acType) {
		this.acType = acType;
	}
	public String getCardChn() {
		return cardChn;
	}
	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
	public String getBusEntryType() {
		return busEntryType;
	}
	public void setBusEntryType(String busEntryType) {
		this.busEntryType = busEntryType;
	}
	public String getIdType() {
		return idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getValidDate() {
		return validDate;
	}
	public String getCvvNo() {
		return cvvNo;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public void setCvvNo(String cvvNo) {
		this.cvvNo = cvvNo;
	}
	public String getFailCaus() {
		return failCaus;
	}
	public void setFailCaus(String failCaus) {
		this.failCaus = failCaus;
	}
	public String getDebitMold() {
		return debitMold;
	}
	public void setDebitMold(String debitMold) {
		this.debitMold = debitMold;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
}