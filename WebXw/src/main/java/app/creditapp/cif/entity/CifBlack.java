package app.creditapp.cif.entity;
import app.base.BaseDomain;
/**
* Title: CifBlack.java
* Description:
* @version：1.0
**/
public class CifBlack extends BaseDomain {
	private String opNo;//修改人员
	private String upDate;//修改日期
	private String txDate;//登记日期
	private String blkId;//黑名单ID
	private String cifNo;//客户号
	private String cifName;//客户名称
	private String idType;//证件类型
	private String idNo;//证件号
	private String blkSource;//数据来源[01手工进入02批量进入03ODS过渡]
	private String blkLevel;//黑名单等级[01拒绝级02预警级03提示级]
	private String blkDate;//进入阶段
	private String blkCause;//进入原因
	private String blkSts;//申请状态
	private String blkFlag;//状态[01有效02失效]
	private String processId;//流程实例号
	private String taskId;//审批流程ID
	private String url;//流程地址
	
	
	private String opName;//修改人员
	private String apprType;//审批种类
	
	//工作流级联查询使用
	private String userId;//用户ID
	private String branchId;//机构号
	
	//工作流批量审批关联查询任务ID
	private String dbid;//任务编号
	
	public String getOpName(){
		return opName;
	}
	
	public void setOpname(String opName){
		this.opName = opName;
	}
	/**
	 * @return 修改日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 修改日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
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
	 * @return 数据来源[01手工进入02批量进入03ODS过渡]
	 */
	public String getBlkSource() {
	 	return blkSource;
	}
	/**
	 * @设置 数据来源[01手工进入02批量进入03ODS过渡]
	 * @param blkSource
	 */
	public void setBlkSource(String blkSource) {
	 	this.blkSource = blkSource;
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
	 * @return 进入原因
	 */
	public String getBlkCause() {
	 	return blkCause;
	}
	/**
	 * @设置 进入原因
	 * @param blkCause
	 */
	public void setBlkCause(String blkCause) {
	 	this.blkCause = blkCause;
	}
	/**
	 * @return 提交状态[01未提交02未审批03否决04生效]
	 */
	public String getBlkDate() {
	 	return blkDate;
	}
	/**
	 * @设置 提交状态[01未提交02未审批03否决04生效]
	 * @param blkSts
	 */
	public void setBlkDate(String blkDate) {
	 	this.blkDate = blkDate;
	}
	/**
	 * @return 进入标识[01进入02移除]
	 */
	public String getBlkFlag() {
	 	return blkFlag;
	}
	/**
	 * @设置 进入标识[01进入02移除]
	 * @param blkFlag
	 */
	public void setBlkFlag(String blkFlag) {
	 	this.blkFlag = blkFlag;
	}
	/**
	 * @return 信托管户经理号
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 信托管户经理号
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	public String getBlkSts() {
		return blkSts;
	}
	public void setBlkSts(String blkSts) {
		this.blkSts = blkSts;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getApprType() {
		return apprType;
	}

	public void setApprType(String apprType) {
		this.apprType = apprType;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public String getUserId() {
		return userId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
}