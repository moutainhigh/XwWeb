package accounting.domain.ws;
/**
* Title: WsAcnoChg.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class WsAcnoChg extends accounting.domain.sys.CMISDomain {
	private String idType;//证件类型
	private String idNo;//证件号码
	private String phoneNo;//手机号码
	private String validDate;//信用卡有效期四位数字,前两位 为年份，后两位为月份
	private String cvvNo;//信用卡使用验证码CVN2(即“后三码”)
	private String newBanksite;//新开户网点
	private String wsId;//数据ID
	private String batchNo;//批次编号
	private String brNo;//合作机构号
	private String pactNo;//合同号
	private String acUse;//账户用途[1扣款账户 2放款账户]
	private String newAcno;//新还款账号
	private String newAcname;//新还款账户名
	private String newAcbankno;//新还款账户开户行号
	private String newAcbankname;//新还款账户开户行名称
	private String chgReason;//变更原因
	private String ifAuth;//是否有扣款授权书:1-是0-否
	private String dealSts;//处理结果[01未处理02处理中03处理成功04处理失败]
	private String dealDesc;//处理描述
	private String txDate;//接收数据日期
	private String oldAcno;//原还款账号
	private String acType;//账户类型

	/**
	 * @return 证件类型
	 */
	public String getIdType() {
	 	return idType;
	}
	/**
	 * @设置 证件类型
	 * @param idType
	 */
	public void setIdType(String idType) {
	 	this.idType = idType;
	}
	/**
	 * @return 证件号码
	 */
	public String getIdNo() {
	 	return idNo;
	}
	/**
	 * @设置 证件号码
	 * @param idNo
	 */
	public void setIdNo(String idNo) {
	 	this.idNo = idNo;
	}
	/**
	 * @return 手机号码
	 */
	public String getPhoneNo() {
	 	return phoneNo;
	}
	/**
	 * @设置 手机号码
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
	 	this.phoneNo = phoneNo;
	}
	/**
	 * @return 信用卡有效期四位数字,前两位 为年份，后两位为月份
	 */
	public String getValidDate() {
	 	return validDate;
	}
	/**
	 * @设置 信用卡有效期四位数字,前两位 为年份，后两位为月份
	 * @param validDate
	 */
	public void setValidDate(String validDate) {
	 	this.validDate = validDate;
	}
	/**
	 * @return 信用卡使用验证码CVN2(即“后三码”)
	 */
	public String getCvvNo() {
	 	return cvvNo;
	}
	/**
	 * @设置 信用卡使用验证码CVN2(即“后三码”)
	 * @param cvvNo
	 */
	public void setCvvNo(String cvvNo) {
	 	this.cvvNo = cvvNo;
	}
	/**
	 * @return 新开户网点
	 */
	public String getNewBanksite() {
	 	return newBanksite;
	}
	/**
	 * @设置 新开户网点
	 * @param newBanksite
	 */
	public void setNewBanksite(String newBanksite) {
	 	this.newBanksite = newBanksite;
	}
	/**
	 * @return 数据ID
	 */
	public String getWsId() {
	 	return wsId;
	}
	/**
	 * @设置 数据ID
	 * @param wsId
	 */
	public void setWsId(String wsId) {
	 	this.wsId = wsId;
	}
	/**
	 * @return 批次编号
	 */
	public String getBatchNo() {
	 	return batchNo;
	}
	/**
	 * @设置 批次编号
	 * @param batchNo
	 */
	public void setBatchNo(String batchNo) {
	 	this.batchNo = batchNo;
	}
	/**
	 * @return 合作机构号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
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
	 * @return 账户用途[1扣款账户 2放款账户]
	 */
	public String getAcUse() {
	 	return acUse;
	}
	/**
	 * @设置 账户用途[1扣款账户 2放款账户]
	 * @param acUse
	 */
	public void setAcUse(String acUse) {
	 	this.acUse = acUse;
	}
	/**
	 * @return 新还款账号
	 */
	public String getNewAcno() {
	 	return newAcno;
	}
	/**
	 * @设置 新还款账号
	 * @param newAcno
	 */
	public void setNewAcno(String newAcno) {
	 	this.newAcno = newAcno;
	}
	/**
	 * @return 新还款账户名
	 */
	public String getNewAcname() {
	 	return newAcname;
	}
	/**
	 * @设置 新还款账户名
	 * @param newAcname
	 */
	public void setNewAcname(String newAcname) {
	 	this.newAcname = newAcname;
	}
	/**
	 * @return 新还款账户开户行号
	 */
	public String getNewAcbankno() {
	 	return newAcbankno;
	}
	/**
	 * @设置 新还款账户开户行号
	 * @param newAcbankno
	 */
	public void setNewAcbankno(String newAcbankno) {
	 	this.newAcbankno = newAcbankno;
	}
	/**
	 * @return 新还款账户开户行名称
	 */
	public String getNewAcbankname() {
	 	return newAcbankname;
	}
	/**
	 * @设置 新还款账户开户行名称
	 * @param newAcbankname
	 */
	public void setNewAcbankname(String newAcbankname) {
	 	this.newAcbankname = newAcbankname;
	}
	/**
	 * @return 变更原因
	 */
	public String getChgReason() {
	 	return chgReason;
	}
	/**
	 * @设置 变更原因
	 * @param chgReason
	 */
	public void setChgReason(String chgReason) {
	 	this.chgReason = chgReason;
	}
	/**
	 * @return 是否有扣款授权书:1-是0-否
	 */
	public String getIfAuth() {
	 	return ifAuth;
	}
	/**
	 * @设置 是否有扣款授权书:1-是0-否
	 * @param ifAuth
	 */
	public void setIfAuth(String ifAuth) {
	 	this.ifAuth = ifAuth;
	}
	/**
	 * @return 处理结果[01未处理02处理中03处理成功04处理失败]
	 */
	public String getDealSts() {
	 	return dealSts;
	}
	/**
	 * @设置 处理结果[01未处理02处理中03处理成功04处理失败]
	 * @param dealSts
	 */
	public void setDealSts(String dealSts) {
	 	this.dealSts = dealSts;
	}
	/**
	 * @return 处理描述
	 */
	public String getDealDesc() {
	 	return dealDesc;
	}
	/**
	 * @设置 处理描述
	 * @param dealDesc
	 */
	public void setDealDesc(String dealDesc) {
	 	this.dealDesc = dealDesc;
	}
	/**
	 * @return 接收数据日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 接收数据日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 原还款账号
	 */
	public String getOldAcno() {
	 	return oldAcno;
	}
	/**
	 * @设置 原还款账号
	 * @param oldAcno
	 */
	public void setOldAcno(String oldAcno) {
	 	this.oldAcno = oldAcno;
	}
	/**
	 * @return 账户类型
	 */
	public String getAcType() {
	 	return acType;
	}
	/**
	 * @设置 账户类型
	 * @param acType
	 */
	public void setAcType(String acType) {
	 	this.acType = acType;
	}
}