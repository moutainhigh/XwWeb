package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnApply.java
* Description:
* @version：1.0
**/
public class LnApply extends BaseDomain {
	private String batchNo;//批次号码
	private String appId;//申请ID
	private String brNo;//合作机构号码
	private String pactNo;//合同编号
	private String appDate;//进件日期
	private String pactXt;//信托合同编号
	private String brAccType;//机构核算类型[A/B]
	private String cifNo;//客户号码
	private String cifName;//客户名称
	private String projNo;//信托项目编号
	private String prdtNo;//产品号
	private String lnType;//贷款类型
	private String curNo;//申请币种
	private Double appAmt;//申请金额
	private Double pactAmt;//合同金额
	private Double lnRate;//利率（月）
	private Double lnRateYear;//利率（年）
	private Double overRate;//罚息利率（月）
	private Double damRate;//提前还款违约金比率
	private String appArea;//申请地点
	private String lnUse;//申请用途
	private String repayType;//还款方式
	private Integer termMon;//申请期限（月）
	private Integer termDay;//申请期限（日）
	private String vouType;//担保方式
	private String begDate;//放款日期
	private String endDate;//到期日期
	private String payType;//扣款日类型
	private String payKind;//扣款日类别、还款频率
	private Integer payDay;//扣款日
	private String feeType;//缴费方式
	private String payMent;//支付方式[01自助02受托]
	private Integer vouDays;//履行担保天数
	private Double feeTotal;//趸交费总额
	private Double feeRate;//手续费率
	private Double feeAmt;//手续费
	private Double srvRate;//服务费率
	private Double srvAmt;//服务费
	private Double vouRate;//担保费率
	private Double vouAmt;//担保费
	private Double feeAmt1;//费用一
	private Double feeAmt2;//费用二
	private Double feeAmt3;//费用三
	private Double feeAmt4;//费用四
	private Double feeAmt5;//费用五
	private String apprType;//审批类型[01自动]
	private String appSts;//申请状态[01未处理02通过03否决04产生合同]
	private String recSts;//记录状态[01正常02删除]
	private String opNo;//信托管户操作员
	private String txDate;//申请日期
	private String upDate;//更新日期

	private String czPactNo;//查征流水号
	private String resultId;//自动审批返回Id
	
	public Double getLnRateYear() {
		return lnRateYear;
	}
	public void setLnRateYear(Double lnRateYear) {
		this.lnRateYear = lnRateYear;
	}
	/**
	 * @return 批次号码
	 */
	public String getBatchNo() {
	 	return batchNo;
	}
	/**
	 * @设置 批次号码
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
	 * @return 合作机构号码
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构号码
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
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
	 * @return 进件日期
	 */
	public String getAppDate() {
	 	return appDate;
	}
	/**
	 * @设置 进件日期
	 * @param appDate
	 */
	public void setAppDate(String appDate) {
	 	this.appDate = appDate;
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
	 * @return 信托项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 信托项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	/**
	 * @return 产品号
	 */
	public String getPrdtNo() {
	 	return prdtNo;
	}
	/**
	 * @设置 产品号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
	 	this.prdtNo = prdtNo;
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
	 * @return 申请币种
	 */
	public String getCurNo() {
	 	return curNo;
	}
	/**
	 * @设置 申请币种
	 * @param curNo
	 */
	public void setCurNo(String curNo) {
	 	this.curNo = curNo;
	}
	/**
	 * @return 申请金额
	 */
	public Double getAppAmt() {
	 	return appAmt;
	}
	/**
	 * @设置 申请金额
	 * @param appAmt
	 */
	public void setAppAmt(Double appAmt) {
	 	this.appAmt = appAmt;
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
	 * @return 利率（月）
	 */
	public Double getLnRate() {
	 	return lnRate;
	}
	/**
	 * @设置 利率（月）
	 * @param lnRate
	 */
	public void setLnRate(Double lnRate) {
	 	this.lnRate = lnRate;
	}
	/**
	 * @return 罚息利率（月）
	 */
	public Double getOverRate() {
	 	return overRate;
	}
	/**
	 * @设置 罚息利率（月）
	 * @param overRate
	 */
	public void setOverRate(Double overRate) {
	 	this.overRate = overRate;
	}
	/**
	 * @return 提前还款违约金比率
	 */
	public Double getDamRate() {
	 	return damRate;
	}
	/**
	 * @设置 提前还款违约金比率
	 * @param damRate
	 */
	public void setDamRate(Double damRate) {
	 	this.damRate = damRate;
	}
	/**
	 * @return 申请地点
	 */
	public String getAppArea() {
	 	return appArea;
	}
	/**
	 * @设置 申请地点
	 * @param appArea
	 */
	public void setAppArea(String appArea) {
	 	this.appArea = appArea;
	}
	/**
	 * @return 申请用途
	 */
	public String getLnUse() {
	 	return lnUse;
	}
	/**
	 * @设置 申请用途
	 * @param lnUse
	 */
	public void setLnUse(String lnUse) {
	 	this.lnUse = lnUse;
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
	 * @return 申请期限（月）
	 */
	public Integer getTermMon() {
	 	return termMon;
	}
	/**
	 * @设置 申请期限（月）
	 * @param termMon
	 */
	public void setTermMon(Integer termMon) {
	 	this.termMon = termMon;
	}
	/**
	 * @return 申请期限（日）
	 */
	public Integer getTermDay() {
	 	return termDay;
	}
	/**
	 * @设置 申请期限（日）
	 * @param termDay
	 */
	public void setTermDay(Integer termDay) {
	 	this.termDay = termDay;
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
	 * @return 放款日期
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 放款日期
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
	 * @return 扣款日
	 */
	public Integer getPayDay() {
	 	return payDay;
	}
	/**
	 * @设置 扣款日
	 * @param payDay
	 */
	public void setPayDay(Integer payDay) {
	 	this.payDay = payDay;
	}
	/**
	 * @return 缴费方式
	 */
	public String getFeeType() {
	 	return feeType;
	}
	/**
	 * @设置 缴费方式
	 * @param feeType
	 */
	public void setFeeType(String feeType) {
	 	this.feeType = feeType;
	}
	/**
	 * @return 支付方式[01自助02受托]
	 */
	public String getPayMent() {
	 	return payMent;
	}
	/**
	 * @设置 支付方式[01自助02受托]
	 * @param payMent
	 */
	public void setPayMent(String payMent) {
	 	this.payMent = payMent;
	}
	/**
	 * @return 履行担保天数
	 */
	public Integer getVouDays() {
	 	return vouDays;
	}
	/**
	 * @设置 履行担保天数
	 * @param vouDays
	 */
	public void setVouDays(Integer vouDays) {
	 	this.vouDays = vouDays;
	}
	/**
	 * @return 趸交费总额
	 */
	public Double getFeeTotal() {
	 	return feeTotal;
	}
	/**
	 * @设置 趸交费总额
	 * @param feeTotal
	 */
	public void setFeeTotal(Double feeTotal) {
	 	this.feeTotal = feeTotal;
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
	 * @return 审批类型[01自动]
	 */
	public String getApprType() {
	 	return apprType;
	}
	/**
	 * @设置 审批类型[01自动]
	 * @param apprType
	 */
	public void setApprType(String apprType) {
	 	this.apprType = apprType;
	}
	/**
	 * @return 申请状态[01未处理02通过03否决04产生合同]
	 */
	public String getAppSts() {
	 	return appSts;
	}
	/**
	 * @设置 申请状态[01未处理02通过03否决04产生合同]
	 * @param appSts
	 */
	public void setAppSts(String appSts) {
	 	this.appSts = appSts;
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
	 * @return 申请日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 申请日期
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
	public String getCzPactNo() {
		return czPactNo;
	}
	public void setCzPactNo(String czPactNo) {
		this.czPactNo = czPactNo;
	}
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
}