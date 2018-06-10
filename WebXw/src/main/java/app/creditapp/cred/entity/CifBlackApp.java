package app.creditapp.cred.entity;
import app.base.BaseDomain;
/**
* Title: CifBlackApp.java
* Description:
* @version：1.0
**/
public class CifBlackApp extends BaseDomain {
	private String blkId;//黑名单ID
	private String appId;//申请ID
	private String cifNo;//客户号
	private String cifName;//客户名称
	private String idType;//证件类型
	private String idNo;//证件号
	private String blkLevel;//黑名单等级[01拒绝级02预警级03提示级]
	private String appType;//申请类型[01进入02进出]
	private String appReason;//申请原因
	private String appSts;//申请状态[00未提交04审批中01同意02否决]
	private String txDate;//登记日期
	private String opNo;//操作员
	private String opName;//操作员

	/**
	 * @return 黑名单ID
	 */
	public String getBlkId() {
	 	return blkId;
	}
	/**
	 * @设置 黑名单ID
	 * @param blkId
	 */
	public void setBlkId(String blkId) {
	 	this.blkId = blkId;
	}
	/**
	 * @return 申请ID
	 */
	public String getAppId() {
	 	return appId;
	}
	/**
	 * @设置 申请ID
	 * @param appId
	 */
	public void setAppId(String appId) {
	 	this.appId = appId;
	}
	/**
	 * @return 客户号
	 */
	public String getCifNo() {
	 	return cifNo;
	}
	/**
	 * @设置 客户号
	 * @param cifNo
	 */
	public void setCifNo(String cifNo) {
	 	this.cifNo = cifNo;
	}
	/**
	 * @return 客户名称
	 */
	public String getCifName() {
	 	return cifName;
	}
	/**
	 * @设置 客户名称
	 * @param cifName
	 */
	public void setCifName(String cifName) {
	 	this.cifName = cifName;
	}
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
	 * @return 证件号
	 */
	public String getIdNo() {
	 	return idNo;
	}
	/**
	 * @设置 证件号
	 * @param idNo
	 */
	public void setIdNo(String idNo) {
	 	this.idNo = idNo;
	}
	/**
	 * @return 黑名单等级[01拒绝级02预警级03提示级]
	 */
	public String getBlkLevel() {
	 	return blkLevel;
	}
	/**
	 * @设置 黑名单等级[01拒绝级02预警级03提示级]
	 * @param blkLevel
	 */
	public void setBlkLevel(String blkLevel) {
	 	this.blkLevel = blkLevel;
	}
	/**
	 * @return 申请类型[01进入02进出]
	 */
	public String getAppType() {
	 	return appType;
	}
	/**
	 * @设置 申请类型[01进入02进出]
	 * @param appType
	 */
	public void setAppType(String appType) {
	 	this.appType = appType;
	}
	/**
	 * @return 申请原因
	 */
	public String getAppReason() {
	 	return appReason;
	}
	/**
	 * @设置 申请原因
	 * @param appReason
	 */
	public void setAppReason(String appReason) {
	 	this.appReason = appReason;
	}
	/**
	 * @return 申请状态[00未提交04审批中01同意02否决]
	 */
	public String getAppSts() {
	 	return appSts;
	}
	/**
	 * @设置 申请状态[00未提交04审批中01同意02否决]
	 * @param appSts
	 */
	public void setAppSts(String appSts) {
	 	this.appSts = appSts;
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
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
}