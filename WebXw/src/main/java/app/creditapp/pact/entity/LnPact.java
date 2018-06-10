package app.creditapp.pact.entity;
import app.base.BaseDomain;
/**
* Title: LnPact.java
* Description:
* @version：1.0
**/
public class LnPact extends BaseDomain {
	private String pactNo;//合同编号
	private String brNo;//合作机构号
	private String batchNo;//批次号
	private String appId;//申请ID
	private String pactId;//合同ID
	private String cifNo;//客户号
	private String cifName;//客户名称
	private String prdtNo;//产品编号
	private String projNo;//项目编号
	private String lnType;//贷款类型
	private String curNo;//币种[CNY-人民币]
	private Double pactAmt;//合同金额
	private Double putAmt;//发放金额
	private Double lnRate;//月利率
	private Double overRate;//罚息利率
	private Double damRate;//提前还款违约比率
	private String begDate;//起始日期
	private String endDate;//到期日期
	private String termType;//期限类型
	private Integer termMon;//期限月
	private Integer termDay;//期限天
	private String repayType;//还款方式
	private String vouType;//担保方式
	private String payType;//扣款日类型
	private String payKind;//扣款日类别、还款频率
	private String payDay;//扣款日期
	private String payChn;//支付渠道
	private String ifCron;//是否定时
	private String cronTime;//定时时间
	private String apprType;//审批类型[01自动02人工批量审批03人工单笔审批]
	private String apprSts;//审批结果[00待审批01同意02否决03返回04退回]
	private String pactSts;//合同状态[01新增02待放款04已放款]
	private String recSts;//记录状态[01正常02删除]
	private String opNo;//信托管户操作员
	private String txDate;//登记日期
	private String txTime;//登记时间
	private String upTime;//更新时间
	private String pactXt;
	private String brAccType;
	private String payMent;
	private String processId;
	private String brName;//合作机构名称
	private String projName;//项目名称
	//PACT_XT
	//BR_ACC_TYPE
	//PAY_MENT
	//PROCESS_ID
	private String numAppr;//笔数
	private Double sumPactAmt;//总金额
	private Double averPactAmt;//平均金额
	private String taskId; // 任务编号
	private String url; // 任务地址
	private String apprBus; // 业务数据
	//催收管理 添加字段
	private String bal;//贷款余额
	private String normInt;//欠息
	private String prdtName;//产品名称
	
	private String opName;//操作员名称
	
	//工作流级联查询使用
	private String userId;//用户ID
	private String branchId;//机构号
	
	//工作流批量审批关联查询任务ID、签收状态
	private String dbid;//任务编号
	private String signState;//签收状态
	
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	private String loginid;//操作员号
	private String dueSts;//代偿/回购状态
	/**
	 * @return 合同编号
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	public String getDueSts() {
		return dueSts;
	}
	public void setDueSts(String dueSts) {
		this.dueSts = dueSts;
	}
	public String getPactXt() {
		return pactXt;
	}
	public void setPactXt(String pactXt) {
		this.pactXt = pactXt;
	}
	public String getBrAccType() {
		return brAccType;
	}
	public void setBrAccType(String brAccType) {
		this.brAccType = brAccType;
	}
	public String getPayMent() {
		return payMent;
	}
	public void setPayMent(String payMent) {
		this.payMent = payMent;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * @设置 合同编号
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
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
	 * @return 批次号
	 */
	public String getBatchNo() {
	 	return batchNo;
	}
	/**
	 * @设置 批次号
	 * @param batchNo
	 */
	public void setBatchNo(String batchNo) {
	 	this.batchNo = batchNo;
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
	 * @return 客户号码
	 */
	public String getCifNo() {
	 	return cifNo;
	}
	/**
	 * @设置 客户号码
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
	 * @return 产品编号
	 */
	public String getPrdtNo() {
	 	return prdtNo;
	}
	/**
	 * @设置 产品编号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
	 	this.prdtNo = prdtNo;
	}
	/**
	 * @return 项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	/**
	 * @return 贷款类型
	 */
	public String getLnType() {
	 	return lnType;
	}
	/**
	 * @设置 贷款类型
	 * @param lnType
	 */
	public void setLnType(String lnType) {
	 	this.lnType = lnType;
	}
	/**
	 * @return 币种[CNY-人民币]
	 */
	public String getCurNo() {
	 	return curNo;
	}
	/**
	 * @设置 币种[CNY-人民币]
	 * @param curNo
	 */
	public void setCurNo(String curNo) {
	 	this.curNo = curNo;
	}
	/**
	 * @return 合同金额
	 */
	public Double getPactAmt() {
	 	return pactAmt;
	}
	/**
	 * @设置 合同金额
	 * @param pactAmt
	 */
	public void setPactAmt(Double pactAmt) {
	 	this.pactAmt = pactAmt;
	}
	/**
	 * @return 发放金额
	 */
	public Double getPutAmt() {
	 	return putAmt;
	}
	/**
	 * @设置 发放金额
	 * @param putAmt
	 */
	public void setPutAmt(Double putAmt) {
	 	this.putAmt = putAmt;
	}
	/**
	 * @return 月利率
	 */
	public Double getLnRate() {
	 	return lnRate;
	}
	/**
	 * @设置 月利率
	 * @param lnRate
	 */
	public void setLnRate(Double lnRate) {
	 	this.lnRate = lnRate;
	}
	/**
	 * @return 罚息利率
	 */
	public Double getOverRate() {
	 	return overRate;
	}
	/**
	 * @设置 罚息利率
	 * @param overRate
	 */
	public void setOverRate(Double overRate) {
	 	this.overRate = overRate;
	}
	/**
	 * @return 提前还款违约比率
	 */
	public Double getDamRate() {
	 	return damRate;
	}
	/**
	 * @设置 提前还款违约比率
	 * @param damRate
	 */
	public void setDamRate(Double damRate) {
	 	this.damRate = damRate;
	}
	/**
	 * @return 起始日期
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 起始日期
	 * @param begDate
	 */
	public void setBegDate(String begDate) {
	 	this.begDate = begDate;
	}
	/**
	 * @return 到期日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 到期日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 期限类型
	 */
	public String getTermType() {
	 	return termType;
	}
	/**
	 * @设置 期限类型
	 * @param termType
	 */
	public void setTermType(String termType) {
	 	this.termType = termType;
	}
	/**
	 * @return 期限月
	 */
	public Integer getTermMon() {
	 	return termMon;
	}
	/**
	 * @设置 期限月
	 * @param termMon
	 */
	public void setTermMon(Integer termMon) {
	 	this.termMon = termMon;
	}
	/**
	 * @return 期限天
	 */
	public Integer getTermDay() {
	 	return termDay;
	}
	/**
	 * @设置 期限天
	 * @param termDay
	 */
	public void setTermDay(Integer termDay) {
	 	this.termDay = termDay;
	}
	/**
	 * @return 还款方式
	 */
	public String getRepayType() {
	 	return repayType;
	}
	/**
	 * @设置 还款方式
	 * @param repayType
	 */
	public void setRepayType(String repayType) {
	 	this.repayType = repayType;
	}
	/**
	 * @return 担保方式
	 */
	public String getVouType() {
	 	return vouType;
	}
	/**
	 * @设置 担保方式
	 * @param vouType
	 */
	public void setVouType(String vouType) {
	 	this.vouType = vouType;
	}
	/**
	 * @return 扣款日类型
	 */
	public String getPayType() {
	 	return payType;
	}
	/**
	 * @设置 扣款日类型
	 * @param payType
	 */
	public void setPayType(String payType) {
	 	this.payType = payType;
	}
	/**
	 * @return 扣款日类别、还款频率
	 */
	public String getPayKind() {
	 	return payKind;
	}
	/**
	 * @设置 扣款日类别、还款频率
	 * @param payKind
	 */
	public void setPayKind(String payKind) {
	 	this.payKind = payKind;
	}
	/**
	 * @return 扣款日期
	 */
	public String getPayDay() {
	 	return payDay;
	}
	/**
	 * @设置 扣款日期
	 * @param payDay
	 */
	public void setPayDay(String payDay) {
	 	this.payDay = payDay;
	}
	/**
	 * @return 支付渠道
	 */
	public String getPayChn() {
	 	return payChn;
	}
	/**
	 * @设置 支付渠道
	 * @param payChn
	 */
	public void setPayChn(String payChn) {
	 	this.payChn = payChn;
	}
	/**
	 * @return 是否定时
	 */
	public String getIfCron() {
	 	return ifCron;
	}
	/**
	 * @设置 是否定时
	 * @param ifCron
	 */
	public void setIfCron(String ifCron) {
	 	this.ifCron = ifCron;
	}
	/**
	 * @return 定时时间
	 */
	public String getCronTime() {
	 	return cronTime;
	}
	/**
	 * @设置 定时时间
	 * @param cronTime
	 */
	public void setCronTime(String cronTime) {
	 	this.cronTime = cronTime;
	}
	/**
	 * @return 审批类型[01自动02人工批量审批03人工单笔审批]
	 */
	public String getApprType() {
	 	return apprType;
	}
	/**
	 * @设置 审批类型[01自动02人工批量审批03人工单笔审批]
	 * @param approveType
	 */
	public void setApprType(String apprType) {
	 	this.apprType = apprType;
	}
	/**
	 * @return 审批结果[00待审批01同意02否决03返回04退回]
	 */
	public String getApprSts() {
	 	return apprSts;
	}
	/**
	 * @设置 审批结果[00待审批01同意02否决03返回04退回]
	 * @param approveSts
	 */
	public void setApprSts(String apprSts) {
	 	this.apprSts = apprSts;
	}
	/**
	 * @return 合同状态[01新增02待放款04已放款]
	 */
	public String getPactSts() {
	 	return pactSts;
	}
	/**
	 * @设置 合同状态[01新增02待放款04已放款]
	 * @param pactSts
	 */
	public void setPactSts(String pactSts) {
	 	this.pactSts = pactSts;
	}
	/**
	 * @return 记录状态[01正常02删除]
	 */
	public String getRecSts() {
	 	return recSts;
	}
	/**
	 * @设置 记录状态[01正常02删除]
	 * @param recSts
	 */
	public void setRecSts(String recSts) {
	 	this.recSts = recSts;
	}
	/**
	 * @return 信托管户操作员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 信托管户操作员
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
	 * @return 登记时间
	 */
	public String getTxTime() {
	 	return txTime;
	}
	/**
	 * @设置 登记时间
	 * @param txTime
	 */
	public void setTxTime(String txTime) {
	 	this.txTime = txTime;
	}
	/**
	 * @return 更新时间
	 */
	public String getUpTime() {
	 	return upTime;
	}
	/**
	 * @设置 更新时间
	 * @param upTime
	 */
	public void setUpTime(String upTime) {
	 	this.upTime = upTime;
	}
	public Double getSumPactAmt() {
		return sumPactAmt;
	}
	public void setSumPactAmt(Double sumPactAmt) {
		this.sumPactAmt = sumPactAmt;
	}
	public Double getAverPactAmt() {
		return averPactAmt;
	}
	public void setAverPactAmt(Double averPactAmt) {
		this.averPactAmt = averPactAmt;
	}
	public String getNumAppr() {
		return numAppr;
	}
	public void setNumAppr(String numAppr) {
		this.numAppr = numAppr;
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
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getBal() {
		return bal;
	}
	public void setBal(String bal) {
		this.bal = bal;
	}
	public String getNormInt() {
		return normInt;
	}
	public void setNormInt(String normInt) {
		this.normInt = normInt;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getApprBus() {
		return apprBus;
	}
	public void setApprBus(String apprBus) {
		this.apprBus = apprBus;
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
	public String getSignState() {
		return signState;
	}
	public void setSignState(String signState) {
		this.signState = signState;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	
}