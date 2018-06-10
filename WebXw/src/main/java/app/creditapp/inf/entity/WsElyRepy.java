package app.creditapp.inf.entity;
import java.util.List;

import app.base.BaseDomain;
import app.creditapp.corp.entity.CorpBase;
/**
* Title: WsElyRepy.java
* Description:
* @version：1.0
**/
public class WsElyRepy extends accounting.domain.sys.CMISDomain {
	private String wsId;//接收数据ID
	private String batchNo;//批次编号
	private String brNo;//合作机构号
	private String pactNo;//合同号:主键
	private Double payTotal;//还款金额:=4+5+6+7
	private Double payAmt;//还款本金
	private Double payInte;//还款利息
	private Double payOver;//罚息
	private Double feeTotal;//费用
	private Double feeDam;//违约金
	private String dealSts;//处理结果[01未处理02处理中03处理成功04处理失败]
	private String dealDesc;//处理描述
	private String txDate;//接收数据日期
	
	private String debitType; // 还款类型  01-全部还款  02-部分还款
	private String onlinOff;//线上 01 线下 02
	
	private String brName;  //合作机构名称
	private String cifName;  //客户名称
	private String projName;  //项目名
	private String dueSts;   //结清状态
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getDebitType() {
		return debitType;
	}
	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}
	public String getOnlinOff() {
		return onlinOff;
	}
	public void setOnlinOff(String onlinOff) {
		this.onlinOff = onlinOff;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}

	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	/**
	 * @return 接收数据ID
	 */
	public String getWsId() {
	 	return wsId;
	}
	/**
	 * @设置 接收数据ID
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
	 * @return 合同号:主键
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同号:主键
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 还款金额:=4+5+6+7
	 */
	public Double getPayTotal() {
	 	return payTotal;
	}
	/**
	 * @设置 还款金额:=4+5+6+7
	 * @param payTotal
	 */
	public void setPayTotal(Double payTotal) {
	 	this.payTotal = payTotal;
	}
	/**
	 * @return 还款本金
	 */
	public Double getPayAmt() {
	 	return payAmt;
	}
	/**
	 * @设置 还款本金
	 * @param payAmt
	 */
	public void setPayAmt(Double payAmt) {
	 	this.payAmt = payAmt;
	}
	/**
	 * @return 还款利息
	 */
	public Double getPayInte() {
	 	return payInte;
	}
	/**
	 * @设置 还款利息
	 * @param payInte
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
	 * @设置 罚息
	 * @param payOver
	 */
	public void setPayOver(Double payOver) {
	 	this.payOver = payOver;
	}
	/**
	 * @return 费用
	 */
	public Double getFeeTotal() {
	 	return feeTotal;
	}
	/**
	 * @设置 费用
	 * @param feeTotal
	 */
	public void setFeeTotal(Double feeTotal) {
	 	this.feeTotal = feeTotal;
	}
	/**
	 * @return 违约金
	 */
	public Double getFeeDam() {
	 	return feeDam;
	}
	/**
	 * @设置 违约金
	 * @param feeDam
	 */
	public void setFeeDam(Double feeDam) {
	 	this.feeDam = feeDam;
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
	public String getDueSts() {
		return dueSts;
	}
	public void setDueSts(String dueSts) {
		this.dueSts = dueSts;
	}

}