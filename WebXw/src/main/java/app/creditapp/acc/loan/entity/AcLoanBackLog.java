package app.creditapp.acc.loan.entity;
import app.base.BaseDomain;
/**
* Title: AcLoanBackLog.java
* Description:
* @version：1.0
**/
public class AcLoanBackLog extends BaseDomain {
	
	private String backLogNo;//放款/扣款反馈流水号
	private String backCnt;//序号
	private String loanLogNo;//放款/扣款流水号
	private String backType;//反馈类型[01-放款，02-扣款,03-未决]
	private String backRes;//放款结果
	private String failCaus;//失败原因
	private String backDate;//反馈日期
	private String backSts;//反馈状态
	private String busOrderType;//业务订单交易类型
	private String busEntryType;//条目交易类型
	private String descCode;//描述代码
	
	private String traceNo;//交易流水
	private String uuid;//接口流水号
	private String status;//支付返回报文结果状态
	private String txTime;//接收数据时间
	private String upTime;//更新时间
	private String cardChn;//支付渠道
	/**
	 * @return 放款反馈流水号
	 */
	public String getBackLogNo() {
	 	return backLogNo;
	}
	/**
	 * @设置 放款反馈流水号
	 * @param backLogNo
	 */
	public void setBackLogNo(String backLogNo) {
	 	this.backLogNo = backLogNo;
	}
	/**
	 * @return 放款流水号
	 */
	public String getLoanLogNo() {
	 	return loanLogNo;
	}
	/**
	 * @设置 放款流水号
	 * @param loanLogNo
	 */
	public void setLoanLogNo(String loanLogNo) {
	 	this.loanLogNo = loanLogNo;
	}
	/**
	 * @return 放款结果[01-成功,02-失败]
	 */
	public String getBackRes() {
	 	return backRes;
	}
	/**
	 * @设置 放款结果[01-成功,02-失败,03-未决]
	 * @param backRes
	 */
	public void setBackRes(String backRes) {
	 	this.backRes = backRes;
	}
	/**
	 * @return 返回时间
	 */
	public String getBackDate() {
	 	return backDate;
	}
	/**
	 * @设置 返回时间
	 * @param backDate
	 */
	public void setBackDate(String backDate) {
	 	this.backDate = backDate;
	}
	/**
	 * @return 反馈状态[01-待处理,02-已处理]
	 */
	public String getBackSts() {
	 	return backSts;
	}
	/**
	 * @设置 反馈状态[01-待处理,02-已处理]
	 * @param backSts
	 */
	public void setBackSts(String backSts) {
	 	this.backSts = backSts;
	}
	public String getFailCaus() {
		return failCaus;
	}
	public void setFailCaus(String failCaus) {
		this.failCaus = failCaus;
	}
	public String getBackType() {
		return backType;
	}
	public void setBackType(String backType) {
		this.backType = backType;
	}
	public String getBackCnt() {
		return backCnt;
	}
	public void setBackCnt(String backCnt) {
		this.backCnt = backCnt;
	}
	public String getBusOrderType() {
		return busOrderType;
	}
	public void setBusOrderType(String busOrderType) {
		this.busOrderType = busOrderType;
	}
	public String getBusEntryType() {
		return busEntryType;
	}
	public void setBusEntryType(String busEntryType) {
		this.busEntryType = busEntryType;
	}
	public String getDescCode() {
		return descCode;
	}
	public void setDescCode(String descCode) {
		this.descCode = descCode;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTxTime() {
		return txTime;
	}
	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	public String getUpTime() {
		return upTime;
	}
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}
	public String getCardChn() {
		return cardChn;
	}
	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
	
}