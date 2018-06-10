package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnApprIdea.java
* Description:
* @version：1.0
**/
public class LnApprIdea extends BaseDomain {
	private String ideaId;//意见ID
	private String appId;//申请ID
	private String apprId;//审批批次ID
	private String apprType;//审批类型[01自动02人工]
	private String apprTime;//审批时间
	private String apprOpt;//审批项
	private String apprIdea;//审批意见[01同意02否决03返回04退回]
	private String apprDesc;//审批描述
	private String apprOp;//审批人
	private String apprRole;//审批角色
	private String apprKind;//审批种类[01贷款审批02冲账审批03黑名单审批04减免审批]
	
	private String batchNo;//批次号
	private String brNo;//机构号
	private String brName;//机构名称
	private String projName;//项目名称
	
	private String processId;
	private String taskId;
	private String apprRoleName;//审批角色名称
	private String userName;//审批用户
	private String url;
	
	//息费减免
	private Double loAmt;//欠本
	private Double loIntst;//欠息
	private Double refIntst;//减免利息
	private Double refAmt;//减免本金
	private String pactNo;//合同号
	private String cifNo;//客户号
	private String cifName;//客户名称
	
	//冲账 
	private String txDt;//交易日期
	private String txTime;//交易日期
	private String txCde;//交易代码
	
	
	
	private String idType;//
	private String idNo;//
	private String blkFlag;//
	/**
	 * @return 意见ID
	 */
	public String getIdeaId() {
	 	return ideaId;
	}
	/**
	 * @设置 意见ID
	 * @param ideaId
	 */
	public void setIdeaId(String ideaId) {
	 	this.ideaId = ideaId;
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
	 * @return 审批批次ID
	 */
	public String getApprId() {
		return apprId;
	}
	/**
	 * @设置 审批批次ID
	 * @param batchId
	 */
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	/**
	 * @return 审批类型[01自动02人工]
	 */
	public String getApprType() {
	 	return apprType;
	}
	/**
	 * @设置 审批类型[01自动02人工]
	 * @param apprType
	 */
	public void setApprType(String apprType) {
	 	this.apprType = apprType;
	}
	/**
	 * @return 审批时间
	 */
	public String getApprTime() {
	 	return apprTime;
	}
	/**
	 * @设置 审批时间
	 * @param apprTime
	 */
	public void setApprTime(String apprTime) {
	 	this.apprTime = apprTime;
	}
	/**
	 * @return 审批项
	 */
	public String getApprOpt() {
	 	return apprOpt;
	}
	/**
	 * @设置 审批项
	 * @param apprOpt
	 */
	public void setApprOpt(String apprOpt) {
	 	this.apprOpt = apprOpt;
	}
	/**
	 * @return 审批意见[01同意02否决03返回04退回]
	 */
	public String getApprIdea() {
	 	return apprIdea;
	}
	/**
	 * @设置 审批意见[01同意02否决03返回04退回]
	 * @param apprIdea
	 */
	public void setApprIdea(String apprIdea) {
	 	this.apprIdea = apprIdea;
	}
	/**
	 * @return 审批描述
	 */
	public String getApprDesc() {
	 	return apprDesc;
	}
	/**
	 * @设置 审批描述
	 * @param apprDesc
	 */
	public void setApprDesc(String apprDesc) {
	 	this.apprDesc = apprDesc;
	}
	/**
	 * @return 审批人
	 */
	public String getApprOp() {
	 	return apprOp;
	}
	/**
	 * @设置 审批人
	 * @param apprOp
	 */
	public void setApprOp(String apprOp) {
	 	this.apprOp = apprOp;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	/**
	 * @return 审批角色
	 */
	public String getApprRole() {
		return apprRole;
	}
	/**
	 * @设置 审批角色
	 * @param apprRole
	 */
	public void setApprRole(String apprRole) {
		this.apprRole = apprRole;
	}
	public String getApprKind() {
		return apprKind;
	}
	public void setApprKind(String apprKind) {
		this.apprKind = apprKind;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getApprRoleName() {
		return apprRoleName;
	}
	public void setApprRoleName(String apprRoleName) {
		this.apprRoleName = apprRoleName;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public Double getLoAmt() {
		return loAmt;
	}
	public void setLoAmt(Double loAmt) {
		this.loAmt = loAmt;
	}
	public Double getLoIntst() {
		return loIntst;
	}
	public void setLoIntst(Double loIntst) {
		this.loIntst = loIntst;
	}
	public Double getRefIntst() {
		return refIntst;
	}
	public void setRefIntst(Double refIntst) {
		this.refIntst = refIntst;
	}
	public Double getRefAmt() {
		return refAmt;
	}
	public void setRefAmt(Double refAmt) {
		this.refAmt = refAmt;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public String getTxDt() {
		return txDt;
	}
	public void setTxDt(String txDt) {
		this.txDt = txDt;
	}
	public String getTxTime() {
		return txTime;
	}
	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	public String getTxCde() {
		return txCde;
	}
	public void setTxCde(String txCde) {
		this.txCde = txCde;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getBlkFlag() {
		return blkFlag;
	}
	public void setBlkFlag(String blkFlag) {
		this.blkFlag = blkFlag;
	}
	
}