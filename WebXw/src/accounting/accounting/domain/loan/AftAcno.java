package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AftAcno.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AftAcno extends accounting.domain.sys.CMISDomain {
	private String chgId;//变更ID
	private String opNo;//操作员
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String pactId;//合同ID
	private String pactNo;//合同号
	private String brNo;//合作机构
	private String oldAcType;//原还款账户类型
	private String oldAcNo;//原还款账号
	private String oldAcName;//原还款账号户名
	private String oldOpnCode;//原还款账号开户行
	private String newAcType;//新还款账户类型
	private String newAcNo;//新还款账号
	private String newAcName;//新还款账号户名
	private String newOpnCode;//新还款账号开户行
	private String chgSts;//状态[01待处理02已处理]
	private String traceNo;//主流水编号
	private String acUse;//账户用途[1扣款账户2放款账户]

	/**
	 * @return 变更ID
	 */
	public String getChgId() {
	 	return chgId;
	}
	/**
	 * @设置 变更ID
	 * @param chgId
	 */
	public void setChgId(String chgId) {
	 	this.chgId = chgId;
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
	 * @return 原还款账户类型
	 */
	public String getOldAcType() {
	 	return oldAcType;
	}
	/**
	 * @设置 原还款账户类型
	 * @param oldAcType
	 */
	public void setOldAcType(String oldAcType) {
	 	this.oldAcType = oldAcType;
	}
	/**
	 * @return 原还款账号
	 */
	public String getOldAcNo() {
	 	return oldAcNo;
	}
	/**
	 * @设置 原还款账号
	 * @param oldAcNo
	 */
	public void setOldAcNo(String oldAcNo) {
	 	this.oldAcNo = oldAcNo;
	}
	/**
	 * @return 原还款账号户名
	 */
	public String getOldAcName() {
	 	return oldAcName;
	}
	/**
	 * @设置 原还款账号户名
	 * @param oldAcName
	 */
	public void setOldAcName(String oldAcName) {
	 	this.oldAcName = oldAcName;
	}
	/**
	 * @return 原还款账号开户行
	 */
	public String getOldOpnCode() {
	 	return oldOpnCode;
	}
	/**
	 * @设置 原还款账号开户行
	 * @param oldOpnCode
	 */
	public void setOldOpnCode(String oldOpnCode) {
	 	this.oldOpnCode = oldOpnCode;
	}
	/**
	 * @return 新还款账户类型
	 */
	public String getNewAcType() {
	 	return newAcType;
	}
	/**
	 * @设置 新还款账户类型
	 * @param newAcType
	 */
	public void setNewAcType(String newAcType) {
	 	this.newAcType = newAcType;
	}
	/**
	 * @return 新还款账号
	 */
	public String getNewAcNo() {
	 	return newAcNo;
	}
	/**
	 * @设置 新还款账号
	 * @param newAcNo
	 */
	public void setNewAcNo(String newAcNo) {
	 	this.newAcNo = newAcNo;
	}
	/**
	 * @return 新还款账号户名
	 */
	public String getNewAcName() {
	 	return newAcName;
	}
	/**
	 * @设置 新还款账号户名
	 * @param newAcName
	 */
	public void setNewAcName(String newAcName) {
	 	this.newAcName = newAcName;
	}
	/**
	 * @return 新还款账号开户行
	 */
	public String getNewOpnCode() {
	 	return newOpnCode;
	}
	/**
	 * @设置 新还款账号开户行
	 * @param newOpnCode
	 */
	public void setNewOpnCode(String newOpnCode) {
	 	this.newOpnCode = newOpnCode;
	}
	/**
	 * @return 状态[01待处理02已处理]
	 */
	public String getChgSts() {
	 	return chgSts;
	}
	/**
	 * @设置 状态[01待处理02已处理]
	 * @param chgSts
	 */
	public void setChgSts(String chgSts) {
	 	this.chgSts = chgSts;
	}
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getAcUse() {
		return acUse;
	}
	public void setAcUse(String acUse) {
		this.acUse = acUse;
	}
}