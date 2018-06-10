package app.creditapp.acc.log.entity;
import app.base.BaseDomain;
/**
* Title: AcLnIntstLog.java
* Description:
* @version：1.0
**/
public class AcLnIntstLog extends BaseDomain {
	private Integer perdNo;//期次号
	private String loanNo;//借据号
	private String traceNo;//核算主流水号
	private String pactNo;//合同号
	private String brNo;//机构号
	private Double normInt;//正常利息
	private Double lstFineInt;//原罚息
	private Double fineInt;//罚息
	private String lstIntDt;//上次结罚息日期
	private String txDt;//交易日期
	private String loginid;//登录人员
	private String brName;//机构名称-用于页面显示
	
	
	
	
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	/**
	 * @return 期次号
	 */
	public Integer getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 期次号
	 * @param perdNo
	 */
	public void setPerdNo(Integer perdNo) {
	 	this.perdNo = perdNo;
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
	 * @return 核算主流水号
	 */
	public String getTraceNo() {
	 	return traceNo;
	}
	/**
	 * @设置 核算主流水号
	 * @param traceNo
	 */
	public void setTraceNo(String traceNo) {
	 	this.traceNo = traceNo;
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
	 * @return 正常利息
	 */
	public Double getNormInt() {
	 	return normInt;
	}
	/**
	 * @设置 正常利息
	 * @param normInt
	 */
	public void setNormInt(Double normInt) {
	 	this.normInt = normInt;
	}
	/**
	 * @return 原罚息
	 */
	public Double getLstFineInt() {
	 	return lstFineInt;
	}
	/**
	 * @设置 原罚息
	 * @param lstFineInt
	 */
	public void setLstFineInt(Double lstFineInt) {
	 	this.lstFineInt = lstFineInt;
	}
	/**
	 * @return 罚息
	 */
	public Double getFineInt() {
	 	return fineInt;
	}
	/**
	 * @设置 罚息
	 * @param fineInt
	 */
	public void setFineInt(Double fineInt) {
	 	this.fineInt = fineInt;
	}
	/**
	 * @return 上次结罚息日期
	 */
	public String getLstIntDt() {
	 	return lstIntDt;
	}
	/**
	 * @设置 上次结罚息日期
	 * @param lstIntDt
	 */
	public void setLstIntDt(String lstIntDt) {
	 	this.lstIntDt = lstIntDt;
	}
	/**
	 * @return 交易日期
	 */
	public String getTxDt() {
	 	return txDt;
	}
	/**
	 * @设置 交易日期
	 * @param txDt
	 */
	public void setTxDt(String txDt) {
	 	this.txDt = txDt;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
}