package app.creditapp.acc.loan.entity;
import app.base.BaseDomain;
/**
* Title: AcDebitSusp.java
* Description:
* @version：1.0
**/
public class AcDebitSusp extends BaseDomain {
	private String wsId;//接收数据ID
	private String batchNo;//批次编号
	private String brNo;//合作机构编号
	private String pactNo;//合同编号
	private String acName;//账户姓名
	private String acNo;//还款账号
	private String opnCode;//银行代码
	private String opnName;//开户行
	private Double payOver;//应收罚息
	private Double feeRec;//应收代扣费总额
	private Double repayTotal;//扣款金额:11=12+13
	private Double repayAmt;//扣款本金
	private Double repayInte;//扣款利息
	private Double repayOver;//扣款罚息
	private Double feeTotal;//代扣费总额:代合作机构收取
	private String serialNo;//扣款流水号
	private String dealSts;//扣款状态[01未处理02处理中03处理成功04处理失败]
	private String dealDesc;//扣款描述
	private String txDate;//接收数据日期
	private String brName;//机构名称
	private String cardChn;//支付渠道
	private String txCde;//交易代码


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
	 * @return 合作机构编号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
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
	 * @return 账户姓名
	 */
	public String getAcName() {
	 	return acName;
	}
	/**
	 * @设置 账户姓名
	 * @param acName
	 */
	public void setAcName(String acName) {
	 	this.acName = acName;
	}
	/**
	 * @return 还款账号
	 */
	public String getAcNo() {
	 	return acNo;
	}
	/**
	 * @设置 还款账号
	 * @param acNo
	 */
	public void setAcNo(String acNo) {
	 	this.acNo = acNo;
	}
	/**
	 * @return 银行代码
	 */
	public String getOpnCode() {
	 	return opnCode;
	}
	/**
	 * @设置 银行代码
	 * @param opnCode
	 */
	public void setOpnCode(String opnCode) {
	 	this.opnCode = opnCode;
	}
	/**
	 * @return 开户行
	 */
	public String getOpnName() {
	 	return opnName;
	}
	/**
	 * @设置 开户行
	 * @param opnName
	 */
	public void setOpnName(String opnName) {
	 	this.opnName = opnName;
	}
	/**
	 * @return 应收罚息
	 */
	public Double getPayOver() {
	 	return payOver;
	}
	/**
	 * @设置 应收罚息
	 * @param payOver
	 */
	public void setPayOver(Double payOver) {
	 	this.payOver = payOver;
	}
	/**
	 * @return 应收代扣费总额
	 */
	public Double getFeeRec() {
	 	return feeRec;
	}
	/**
	 * @设置 应收代扣费总额
	 * @param feeRec
	 */
	public void setFeeRec(Double feeRec) {
	 	this.feeRec = feeRec;
	}
	/**
	 * @return 扣款金额:11=12+13
	 */
	public Double getRepayTotal() {
	 	return repayTotal;
	}
	/**
	 * @设置 扣款金额:11=12+13
	 * @param repayTotal
	 */
	public void setRepayTotal(Double repayTotal) {
	 	this.repayTotal = repayTotal;
	}
	/**
	 * @return 扣款本金
	 */
	public Double getRepayAmt() {
	 	return repayAmt;
	}
	/**
	 * @设置 扣款本金
	 * @param repayAmt
	 */
	public void setRepayAmt(Double repayAmt) {
	 	this.repayAmt = repayAmt;
	}
	/**
	 * @return 扣款利息
	 */
	public Double getRepayInte() {
	 	return repayInte;
	}
	/**
	 * @设置 扣款利息
	 * @param repayInte
	 */
	public void setRepayInte(Double repayInte) {
	 	this.repayInte = repayInte;
	}
	/**
	 * @return 扣款罚息
	 */
	public Double getRepayOver() {
	 	return repayOver;
	}
	/**
	 * @设置 扣款罚息
	 * @param repayOver
	 */
	public void setRepayOver(Double repayOver) {
	 	this.repayOver = repayOver;
	}
	/**
	 * @return 代扣费总额:代合作机构收取
	 */
	public Double getFeeTotal() {
	 	return feeTotal;
	}
	/**
	 * @设置 代扣费总额:代合作机构收取
	 * @param feeTotal
	 */
	public void setFeeTotal(Double feeTotal) {
	 	this.feeTotal = feeTotal;
	}
	/**
	 * @return 扣款流水号
	 */
	public String getSerialNo() {
	 	return serialNo;
	}
	/**
	 * @设置 扣款流水号
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo) {
	 	this.serialNo = serialNo;
	}
	/**
	 * @return 扣款状态[01未处理02处理中03处理成功04处理失败]
	 */
	public String getDealSts() {
	 	return dealSts;
	}
	/**
	 * @设置 扣款状态[01未处理02处理中03处理成功04处理失败]
	 * @param dealSts
	 */
	public void setDealSts(String dealSts) {
	 	this.dealSts = dealSts;
	}
	/**
	 * @return 扣款描述
	 */
	public String getDealDesc() {
	 	return dealDesc;
	}
	/**
	 * @设置 扣款描述
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
	public String getCardChn() {
		return cardChn;
	}
	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
	public String getTxCde() {
		return txCde;
	}
	public void setTxCde(String txCde) {
		this.txCde = txCde;
	}
}