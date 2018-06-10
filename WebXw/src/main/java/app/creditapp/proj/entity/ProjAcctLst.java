package app.creditapp.proj.entity;
import app.base.BaseDomain;
/**
* Title: ProjAcctLst.java
* Description:
* @version：1.0
**/
public class ProjAcctLst extends BaseDomain {
	private String txTime;//交易时间[hhmmss]
	private String txDate;//交易时间[YYYYMMDD]
	private String txDirect;//交易方向[01-借02-贷]
	private Double txAmt;//交易金额
	private String txAcctName;//交易账户名称
	private String txOpnBkno;//交易开户行行号
	private String txOpnBank;//交易账户开户名称
	private String txAcctNo;//交易账户账号
	private String lstId;//流水号[主键]
	private String acctId;//账户ID[外键]

	/**
	 * @return 交易时间[hhmmss]
	 */
	public String getTxTime() {
	 	return txTime;
	}
	/**
	 * @设置 交易时间[hhmmss]
	 * @param txTime
	 */
	public void setTxTime(String txTime) {
	 	this.txTime = txTime;
	}
	/**
	 * @return 交易时间[YYYYMMDD]
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 交易时间[YYYYMMDD]
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 交易方向[01-借02-贷]
	 */
	public String getTxDirect() {
	 	return txDirect;
	}
	/**
	 * @设置 交易方向[01-借02-贷]
	 * @param txDirect
	 */
	public void setTxDirect(String txDirect) {
	 	this.txDirect = txDirect;
	}
	/**
	 * @return 交易金额
	 */
	public Double getTxAmt() {
	 	return txAmt;
	}
	/**
	 * @设置 交易金额
	 * @param txAmt
	 */
	public void setTxAmt(Double txAmt) {
	 	this.txAmt = txAmt;
	}
	/**
	 * @return 交易账户名称
	 */
	public String getTxAcctName() {
	 	return txAcctName;
	}
	/**
	 * @设置 交易账户名称
	 * @param txAcctName
	 */
	public void setTxAcctName(String txAcctName) {
	 	this.txAcctName = txAcctName;
	}
	/**
	 * @return 交易开户行行号
	 */
	public String getTxOpnBkno() {
	 	return txOpnBkno;
	}
	/**
	 * @设置 交易开户行行号
	 * @param txOpnBkno
	 */
	public void setTxOpnBkno(String txOpnBkno) {
	 	this.txOpnBkno = txOpnBkno;
	}
	/**
	 * @return 交易账户开户名称
	 */
	public String getTxOpnBank() {
	 	return txOpnBank;
	}
	/**
	 * @设置 交易账户开户名称
	 * @param txOpnBank
	 */
	public void setTxOpnBank(String txOpnBank) {
	 	this.txOpnBank = txOpnBank;
	}
	/**
	 * @return 交易账户账号
	 */
	public String getTxAcctNo() {
	 	return txAcctNo;
	}
	/**
	 * @设置 交易账户账号
	 * @param txAcctNo
	 */
	public void setTxAcctNo(String txAcctNo) {
	 	this.txAcctNo = txAcctNo;
	}
	/**
	 * @return 流水号[主键]
	 */
	public String getLstId() {
	 	return lstId;
	}
	/**
	 * @设置 流水号[主键]
	 * @param lstId
	 */
	public void setLstId(String lstId) {
	 	this.lstId = lstId;
	}
	/**
	 * @return 账户ID[外键]
	 */
	public String getAcctId() {
	 	return acctId;
	}
	/**
	 * @设置 账户ID[外键]
	 * @param acctId
	 */
	public void setAcctId(String acctId) {
	 	this.acctId = acctId;
	}
}