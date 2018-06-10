package app.creditapp.acc.chg.entity;
import app.base.BaseDomain;
/**
* Title: AftRelief.java
* Description:
* @version：1.0
**/
public class AftRelief extends BaseDomain {
	private String refId;//减免ID
	private Double loFee;//欠费用
	private Double refAmt;//减免本金
	private Double refIntst;//减免利息
	private Double refFine;//减免罚息
	private Double refFee;//减免费用
	private String refSts;//状态
	private String opNo;//操作员
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String traceNo;//核算主流水号
	private String pactId;//合同ID
	private String pactNo;//合同号
	private String brNo;//机构号
	private String cifNo;//客户号
	private String cifName;//客户名称
	private Double dueAmt;//借据金额
	private Double loIntst;//欠息
	private Double loAmt;//欠本
	private Double loFine;//欠罚息
	private String appSts;//申请状态
	private String processId;//工作流ID
	private String brName;//机构名称
	private String opName;//机构名称
	private String taskId;//审批流程ID
	private String url;//流程地址
	
	//工作流级联查询使用
	private String userId;//用户ID
	private String branchId;//机构号
	
	//工作流批量审批关联查询任务ID
	private String dbid;//任务编号
	/**
	 * @return 减免ID
	 */
	public String getRefId() {
	 	return refId;
	}
	/**
	 * @设置 减免ID
	 * @param refId
	 */
	public void setRefId(String refId) {
	 	this.refId = refId;
	}
	/**
	 * @return 欠费用
	 */
	public Double getLoFee() {
	 	return loFee;
	}
	/**
	 * @设置 欠费用
	 * @param loFee
	 */
	public void setLoFee(Double loFee) {
	 	this.loFee = loFee;
	}
	/**
	 * @return 减免本金
	 */
	public Double getRefAmt() {
	 	return refAmt;
	}
	/**
	 * @设置 减免本金
	 * @param refAmt
	 */
	public void setRefAmt(Double refAmt) {
	 	this.refAmt = refAmt;
	}
	/**
	 * @return 减免利息
	 */
	public Double getRefIntst() {
	 	return refIntst;
	}
	/**
	 * @设置 减免利息
	 * @param refIntst
	 */
	public void setRefIntst(Double refIntst) {
	 	this.refIntst = refIntst;
	}
	/**
	 * @return 减免罚息
	 */
	public Double getRefFine() {
	 	return refFine;
	}
	/**
	 * @设置 减免罚息
	 * @param refFine
	 */
	public void setRefFine(Double refFine) {
	 	this.refFine = refFine;
	}
	/**
	 * @return 减免费用
	 */
	public Double getRefFee() {
	 	return refFee;
	}
	/**
	 * @设置 减免费用
	 * @param refFee
	 */
	public void setRefFee(Double refFee) {
	 	this.refFee = refFee;
	}
	/**
	 * @return 状态
	 */
	public String getRefSts() {
	 	return refSts;
	}
	/**
	 * @设置 状态
	 * @param refSts
	 */
	public void setRefSts(String refSts) {
	 	this.refSts = refSts;
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
	 * @return 借据金额
	 */
	public Double getDueAmt() {
	 	return dueAmt;
	}
	/**
	 * @设置 借据金额
	 * @param dueAmt
	 */
	public void setDueAmt(Double dueAmt) {
	 	this.dueAmt = dueAmt;
	}
	/**
	 * @return 欠息
	 */
	public Double getLoIntst() {
	 	return loIntst;
	}
	/**
	 * @设置 欠息
	 * @param loIntst
	 */
	public void setLoIntst(Double loIntst) {
	 	this.loIntst = loIntst;
	}
	/**
	 * @return 欠本
	 */
	public Double getLoAmt() {
	 	return loAmt;
	}
	/**
	 * @设置 欠本
	 * @param loAmt
	 */
	public void setLoAmt(Double loAmt) {
	 	this.loAmt = loAmt;
	}
	/**
	 * @return 欠罚息
	 */
	public Double getLoFine() {
	 	return loFine;
	}
	/**
	 * @设置 欠罚息
	 * @param loFine
	 */
	public void setLoFine(Double loFine) {
	 	this.loFine = loFine;
	}
	public String getAppSts() {
		return appSts;
	}
	public void setAppSts(String appSts) {
		this.appSts = appSts;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
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
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getOpName() {
		return opName;
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