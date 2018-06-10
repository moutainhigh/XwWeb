package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnDue.java
* Description:
* @version：1.0
**/
public class LnDue extends BaseDomain {
	private String upTime;//更新时间
	private String txTime;//登记时间
	private String txDate;//登记日期
	private String opNo;//信托管户操作员
	private String recSts;//记录状态[01正常02删除]
	private String dueSts;//借据状态[01未入账02正常03逾期04呆滞05呆滞07结清08回购]
	private String apprSts;//审批结果[00待分账01通过02否决03发回]
	private String fiveSts;//五级分类状态
	private String fiveDate;//五级分类日期
	private Double feeAmt5;//费用五
	private Double feeAmt4;//费用四
	private Double feeAmt3;//费用三
	private Double feeAmt2;//费用二
	private Double feeAmt1;//费用一
	private Double vouAmt;//担保费
	private Double vouRate;//担保费率
	private Double srvAmt;//服务费
	private Double srvRate;//服务费率
	private Double feeAmt;//手续费
	private Double feeRate;//手续费率
	private String payMent;//支付方式[01自主02受托]
	private String payChn;//支付渠道
	private String payDay;//扣款日期
	private String payKind;//扣款日类别、还款频率
	private String payType;//扣款日类型
	private String vouType;//担保方式
	private Integer repayCycle;//还款周期
	private String repayType;//还款方式
	private Integer termDay;//期限天
	private Integer termMon;//期限月
	private String termType;//期限类型
	private String endDate;//最后到期日（含展期）
	private String mtrDate;//原到期日期
	private String begDate;//起始日期
	private Double damRate;//提前还款违约比率
	private Double overRate;//罚息利率
	private Double lnRate;//月利率
	private Double lnRateYear;//月利率
	private Double bal;//贷款余额
	private Double dueAmt;//借据金额
	private String curNo;//币种[CNY-人民币]
	private String lnType;//贷款类型
	private String fundNo;//资金编号
	private String projNo;//项目编号
	private String prdtNo;//信达产品号
	private String cifName;//客户名称
	private String cifNo;//客户号码
	private String brAccType;//机构核算类型[A/B]
	private String pactXt;//信托合同编号
	private String pactNo;//合同编号
	private String brNo;//合作机构号
	private String batchNo;//批次号
	private String appId;//申请ID
	private String pactId;//合同ID
	private String dueNo;//借据号
	
	private String loginid;//操作员号
	private String projName;//项目名称
	private String brName;//合作机构名称
	private String prdtName;//产品名称
	private Double normInt;//欠息 
	private String tranSts;//转让状态
	private String tranDate;//转让日期
	private String poolId;//资产池编号
	
	private String chkFlag;//回访标志，使用函数FUN_GETAFTSPOT（brno，pactno）取得
	
	private Double pactAmt;//
	
	
	
	public Double getLnRateYear() {
		return lnRateYear;
	}
	public void setLnRateYear(Double lnRateYear) {
		this.lnRateYear = lnRateYear;
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
	 * @return 借据状态[01未入账02正常03逾期04呆滞05呆滞07结清08回购]
	 */
	public String getDueSts() {
	 	return dueSts;
	}
	/**
	 * @设置 借据状态[01未入账02正常03逾期04呆滞05呆滞07结清08回购]
	 * @param dueSts
	 */
	public void setDueSts(String dueSts) {
	 	this.dueSts = dueSts;
	}
	/**
	 * @return 审批结果[00待分账01通过02否决03发回]
	 */
	public String getApprSts() {
	 	return apprSts;
	}
	/**
	 * @设置 审批结果[00待分账01通过02否决03发回]
	 * @param apprSts
	 */
	public void setApprSts(String apprSts) {
	 	this.apprSts = apprSts;
	}
	/**
	 * @return 五级分类状态
	 */
	public String getFiveSts() {
	 	return fiveSts;
	}
	/**
	 * @设置 五级分类状态
	 * @param fiveSts
	 */
	public void setFiveSts(String fiveSts) {
	 	this.fiveSts = fiveSts;
	}
	/**
	 * @return 五级分类日期
	 */
	public String getFiveDate() {
	 	return fiveDate;
	}
	/**
	 * @设置 五级分类日期
	 * @param fiveDate
	 */
	public void setFiveDate(String fiveDate) {
	 	this.fiveDate = fiveDate;
	}
	/**
	 * @return 费用五
	 */
	public Double getFeeAmt5() {
	 	return feeAmt5;
	}
	/**
	 * @设置 费用五
	 * @param feeAmt5
	 */
	public void setFeeAmt5(Double feeAmt5) {
	 	this.feeAmt5 = feeAmt5;
	}
	/**
	 * @return 费用四
	 */
	public Double getFeeAmt4() {
	 	return feeAmt4;
	}
	/**
	 * @设置 费用四
	 * @param feeAmt4
	 */
	public void setFeeAmt4(Double feeAmt4) {
	 	this.feeAmt4 = feeAmt4;
	}
	/**
	 * @return 费用三
	 */
	public Double getFeeAmt3() {
	 	return feeAmt3;
	}
	/**
	 * @设置 费用三
	 * @param feeAmt3
	 */
	public void setFeeAmt3(Double feeAmt3) {
	 	this.feeAmt3 = feeAmt3;
	}
	/**
	 * @return 费用二
	 */
	public Double getFeeAmt2() {
	 	return feeAmt2;
	}
	/**
	 * @设置 费用二
	 * @param feeAmt2
	 */
	public void setFeeAmt2(Double feeAmt2) {
	 	this.feeAmt2 = feeAmt2;
	}
	/**
	 * @return 费用一
	 */
	public Double getFeeAmt1() {
	 	return feeAmt1;
	}
	/**
	 * @设置 费用一
	 * @param feeAmt1
	 */
	public void setFeeAmt1(Double feeAmt1) {
	 	this.feeAmt1 = feeAmt1;
	}
	/**
	 * @return 担保费
	 */
	public Double getVouAmt() {
	 	return vouAmt;
	}
	/**
	 * @设置 担保费
	 * @param vouAmt
	 */
	public void setVouAmt(Double vouAmt) {
	 	this.vouAmt = vouAmt;
	}
	/**
	 * @return 担保费率
	 */
	public Double getVouRate() {
	 	return vouRate;
	}
	/**
	 * @设置 担保费率
	 * @param vouRate
	 */
	public void setVouRate(Double vouRate) {
	 	this.vouRate = vouRate;
	}
	/**
	 * @return 服务费
	 */
	public Double getSrvAmt() {
	 	return srvAmt;
	}
	/**
	 * @设置 服务费
	 * @param srvAmt
	 */
	public void setSrvAmt(Double srvAmt) {
	 	this.srvAmt = srvAmt;
	}
	/**
	 * @return 服务费率
	 */
	public Double getSrvRate() {
	 	return srvRate;
	}
	/**
	 * @设置 服务费率
	 * @param srvRate
	 */
	public void setSrvRate(Double srvRate) {
	 	this.srvRate = srvRate;
	}
	/**
	 * @return 手续费
	 */
	public Double getFeeAmt() {
	 	return feeAmt;
	}
	/**
	 * @设置 手续费
	 * @param feeAmt
	 */
	public void setFeeAmt(Double feeAmt) {
	 	this.feeAmt = feeAmt;
	}
	/**
	 * @return 手续费率
	 */
	public Double getFeeRate() {
	 	return feeRate;
	}
	/**
	 * @设置 手续费率
	 * @param feeRate
	 */
	public void setFeeRate(Double feeRate) {
	 	this.feeRate = feeRate;
	}
	/**
	 * @return 支付方式[01自主02受托]
	 */
	public String getPayMent() {
	 	return payMent;
	}
	/**
	 * @设置 支付方式[01自主02受托]
	 * @param payMent
	 */
	public void setPayMent(String payMent) {
	 	this.payMent = payMent;
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
	 * @return 还款周期
	 */
	public Integer getRepayCycle() {
	 	return repayCycle;
	}
	/**
	 * @设置 还款周期
	 * @param repayCycle
	 */
	public void setRepayCycle(Integer repayCycle) {
	 	this.repayCycle = repayCycle;
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
	 * @return 最后到期日（含展期）
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 最后到期日（含展期）
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 原到期日期
	 */
	public String getMtrDate() {
	 	return mtrDate;
	}
	/**
	 * @设置 原到期日期
	 * @param mtrDate
	 */
	public void setMtrDate(String mtrDate) {
	 	this.mtrDate = mtrDate;
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
	 * @return 贷款余额
	 */
	public Double getBal() {
	 	return bal;
	}
	/**
	 * @设置 贷款余额
	 * @param bal
	 */
	public void setBal(Double bal) {
	 	this.bal = bal;
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
	 * @return 资金编号
	 */
	public String getFundNo() {
	 	return fundNo;
	}
	/**
	 * @设置 资金编号
	 * @param fundNo
	 */
	public void setFundNo(String fundNo) {
	 	this.fundNo = fundNo;
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
	 * @return 信达产品号
	 */
	public String getPrdtNo() {
	 	return prdtNo;
	}
	/**
	 * @设置 信达产品号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
	 	this.prdtNo = prdtNo;
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
	 * @return 机构核算类型[A/B]
	 */
	public String getBrAccType() {
	 	return brAccType;
	}
	/**
	 * @设置 机构核算类型[A/B]
	 * @param brAccType
	 */
	public void setBrAccType(String brAccType) {
	 	this.brAccType = brAccType;
	}
	/**
	 * @return 信托合同编号
	 */
	public String getPactXt() {
	 	return pactXt;
	}
	/**
	 * @设置 信托合同编号
	 * @param pactXt
	 */
	public void setPactXt(String pactXt) {
	 	this.pactXt = pactXt;
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
	 * @return 借据号
	 */
	public String getDueNo() {
	 	return dueNo;
	}
	/**
	 * @设置 借据号
	 * @param dueNo
	 */
	public void setDueNo(String dueNo) {
	 	this.dueNo = dueNo;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public void setNormInt(Double normInt) {
		this.normInt = normInt;
	}
	public Double getNormInt() {
		return normInt;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public Double getPactAmt() {
		return pactAmt;
	}
	public void setPactAmt(Double pactAmt) {
		this.pactAmt = pactAmt;
	}
	public String getTranSts() {
		return tranSts;
	}
	public void setTranSts(String tranSts) {
		this.tranSts = tranSts;
	}
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}
	public String getPoolId() {
		return poolId;
	}
	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}
	/**
	 * @return the chkFlag
	 */
	public String getChkFlag() {
		return chkFlag;
	}
	/**
	 * @param chkFlag the chkFlag to set
	 */
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}
	
	
}