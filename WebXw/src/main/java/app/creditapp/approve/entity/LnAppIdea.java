package app.creditapp.approve.entity;

import app.base.BaseDomain;

/**
 * 功能描述：审批意见表 表名：LN_APP_IDEA
 * 
 * @see
 * @修改日志：
 * 
 */
public class LnAppIdea extends BaseDomain implements java.io.Serializable {
	public LnAppIdea() {
	}
	
	private String ideaId; // 意见ID
	private String appId; // 申请ID
	private String apprId; // 审批批次ID
	private String apprType; // 审批类型[01自动02人工]
	private String apprTime; // 审批时间
	private String apprOpt; // 审批项
	private String apprIdea; // 审批意见[01同意02否决03返回04退回]
	private String apprDesc; // 审批描述
	private String apprOp; // 审批人
	
	//贷款审批
	private String batchNo;  //批次号
	private String brNo;     //机构编号
	private String projNo;   //项目编号
	private String lnType;   //贷款性质
	private String numAppr;   //笔数
	private String sumPactAmt;  //汇总金额
	private String averPactAmt;  //平均金额
	
	
	
	
	
	public String getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(String ideaId) {
		this.ideaId = ideaId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getApprId() {
		return apprId;
	}
	public void setApprId(String apprId) {
		this.apprId = apprId;
	}
	public String getApprType() {
		return apprType;
	}
	public void setApprType(String apprType) {
		this.apprType = apprType;
	}
	public String getApprTime() {
		return apprTime;
	}
	public void setApprTime(String apprTime) {
		this.apprTime = apprTime;
	}
	public String getApprOpt() {
		return apprOpt;
	}
	public void setApprOpt(String apprOpt) {
		this.apprOpt = apprOpt;
	}
	public String getApprIdea() {
		return apprIdea;
	}
	public void setApprIdea(String apprIdea) {
		this.apprIdea = apprIdea;
	}
	public String getApprDesc() {
		return apprDesc;
	}
	public void setApprDesc(String apprDesc) {
		this.apprDesc = apprDesc;
	}
	public String getApprOp() {
		return apprOp;
	}
	public void setApprOp(String apprOp) {
		this.apprOp = apprOp;
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
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getLnType() {
		return lnType;
	}
	public void setLnType(String lnType) {
		this.lnType = lnType;
	}
	public String getNumAppr() {
		return numAppr;
	}
	public void setNumAppr(String numAppr) {
		this.numAppr = numAppr;
	}
	public String getSumPactAmt() {
		return sumPactAmt;
	}
	public void setSumPactAmt(String sumPactAmt) {
		this.sumPactAmt = sumPactAmt;
	}
	public String getAverPactAmt() {
		return averPactAmt;
	}
	public void setAverPactAmt(String averPactAmt) {
		this.averPactAmt = averPactAmt;
	}
}