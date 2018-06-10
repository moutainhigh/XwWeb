package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnApplyReg.java
* Description:
* @version：1.0
**/
public class LnApplyReg extends BaseDomain {
	private String batchNo;//批次号码
	private String appId;//申请ID
	private Double appAmt;//申请金额
	private Double pactAmt;//合同金额
	private Double lnRate;//利率（月）
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
	private String ifItp;//是否查征
	private String ifApp;//是否填写申请表
	private String ifId;//是否有身份证信息
	private String ifAuth;//是否有查证授权文件
	private String ifPact;//是否已签订合同
	private String ifFlag;//备用标志
	private String ifCar;//是否有车
	private String ifCarcred;//是否有按揭车贷
	private String ifRoom;//是否有房
	private String ifMort;//是否有按揭房贷
	private String ifCard;//是否有贷记卡
	private Integer cardAmt;//贷记卡最低额度
	private String appSts;//申请状态[02通过]
	private String splitSts;//拆分状态[01未拆分02已拆分]
	private String apprSts;//审批状态[01未审批02通过03否决]
	private String brNo;//合作机构号码
	private String pactNo;//合同编号
	private String appDate;//进件日期
	private String pactXt;//信托合同编号
	private String brAccType;//机构核算类型[A/B]
	private String cifName;//客户名称
	private String idType;//证件类型
	private String idNo;//证件号码
	private String cifType;//客户类型
	private String sex;//性别
	private String birthDay;//出生日期
	private String marriage;//婚姻状况
	private String children; //是否有子女
	private String edu;//最高学历
	private String degree;//最高学位
	private Double income;//月收入（元）
	private String commTel;//联系电话
	private String phoneNo;//手机号码
	private String commCode;//通讯邮编
	private String commAddr;//通讯地址
	private String cifArea;//户籍归属
	private String resTel;//住宅电话
	private String resCode;//住宅邮编
	private String resAddr;//住宅地址
	private String resSts;//居住状况
	private String workType;//职业
	private String corpName;//工作单位名称
	private String corpWay;//工作单位所属行业
	private String corpCode;//工作单位邮编
	private String corpAddr;//工作单位地址
	private String duty;//职务
	private String title;//职称
	private String workYear;//工作起始年份
	private String mateName;//配偶名称
	private String mateIdtype;//配偶证件类型
	private String mateIdno;//配偶证件号码
	private String mateWork;//配偶工作单位
	private String mateTel;//配偶联系电话
	private String projNo;//信托项目编号
	private String prdtNo;//产品号
	private String lnType;//贷款类型
	private String curNo;//申请币种
	
	private String prdtName;//申请币种
	private String czPactNo;//查征流水号
	private String workSts; //工作状态
	private String txdate;
	private String loginid;
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getTxdate() {
		return txdate;
	}
	public void setTxdate(String txdate) {
		this.txdate = txdate;
	}
	public String getWorkSts() {
		return workSts;
	}
	public void setWorkSts(String workSts) {
		this.workSts = workSts;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
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
	 * @return 是否查征
	 */
	public String getIfItp() {
	 	return ifItp;
	}
	/**
	 * @设置 是否查征
	 * @param ifItp
	 */
	public void setIfItp(String ifItp) {
	 	this.ifItp = ifItp;
	}
	/**
	 * @return 是否填写申请表
	 */
	public String getIfApp() {
	 	return ifApp;
	}
	/**
	 * @设置 是否填写申请表
	 * @param ifApp
	 */
	public void setIfApp(String ifApp) {
	 	this.ifApp = ifApp;
	}
	/**
	 * @return 是否有身份证信息
	 */
	public String getIfId() {
	 	return ifId;
	}
	/**
	 * @设置 是否有身份证信息
	 * @param ifId
	 */
	public void setIfId(String ifId) {
	 	this.ifId = ifId;
	}
	/**
	 * @return 是否有查证授权文件
	 */
	public String getIfAuth() {
	 	return ifAuth;
	}
	/**
	 * @设置 是否有查证授权文件
	 * @param ifAuth
	 */
	public void setIfAuth(String ifAuth) {
	 	this.ifAuth = ifAuth;
	}
	/**
	 * @return 是否已签订合同
	 */
	public String getIfPact() {
	 	return ifPact;
	}
	/**
	 * @设置 是否已签订合同
	 * @param ifPact
	 */
	public void setIfPact(String ifPact) {
	 	this.ifPact = ifPact;
	}
	/**
	 * @return 备用标志
	 */
	public String getIfFlag() {
	 	return ifFlag;
	}
	/**
	 * @设置 备用标志
	 * @param ifFlag
	 */
	public void setIfFlag(String ifFlag) {
	 	this.ifFlag = ifFlag;
	}
	/**
	 * @return 是否有车
	 */	
	public String getIfCar() {
		return ifCar;
	}
	/**
	 * @设置 是否有车
	 * @param ifCar
	 */
	public void setIfCar(String ifCar) {
		this.ifCar = ifCar;
	}
	/**
	 * @return 是否有按揭车贷
	 */
	public String getIfCarcred() {
		return ifCarcred;
	}
	/**
	 * @设置 是否有按揭车贷
	 * @param ifCarcred
	 */
	public void setIfCarcred(String ifCarcred) {
		this.ifCarcred = ifCarcred;
	}
	/**
	 * @return 是否有房
	 */
	public String getIfRoom() {
		return ifRoom;
	}
	/**
	 * @设置 是否有房
	 * @param ifRoom
	 */
	public void setIfRoom(String ifRoom) {
		this.ifRoom = ifRoom;
	}
	/**
	 * @return 是否有按揭房贷
	 */
	public String getIfMort() {
		return ifMort;
	}
	/**
	 * @设置 是否有按揭房贷
	 * @param ifMort
	 */
	public void setIfMort(String ifMort) {
		this.ifMort = ifMort;
	}
	/**
	 * @return 是否有贷记卡
	 */
	public String getIfCard() {
		return ifCard;
	}
	/**
	 * @设置 是否有贷记卡
	 * @param ifCard
	 */
	public void setIfCard(String ifCard) {
		this.ifCard = ifCard;
	}
	/**
	 * @return 贷记卡最低额度
	 */
	public Integer getCardAmt() {
		return cardAmt;
	}
	/**
	 * @设置 贷记卡最低额度
	 * @param ifCard
	 */
	public void setCardAmt(Integer cardAmt) {
		this.cardAmt = cardAmt;
	}
	/**
	 * @return 申请状态[02通过]
	 */
	public String getAppSts() {
	 	return appSts;
	}
	/**
	 * @设置 申请状态[02通过]
	 * @param appSts
	 */
	public void setAppSts(String appSts) {
	 	this.appSts = appSts;
	}
	/**
	 * @return 拆分状态[01未拆分02已拆分]
	 */
	public String getSplitSts() {
	 	return splitSts;
	}
	/**
	 * @设置 拆分状态[01未拆分02已拆分]
	 * @param splitSts
	 */
	public void setSplitSts(String splitSts) {
	 	this.splitSts = splitSts;
	}
	/**
	 * @return 审批状态[01未审批02通过03否决]
	 */
	public String getApprSts() {
	 	return apprSts;
	}
	/**
	 * @设置 审批状态[01未审批02通过03否决]
	 * @param apprSts
	 */
	public void setApprSts(String apprSts) {
	 	this.apprSts = apprSts;
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
	 * @return 证件号码
	 */
	public String getIdNo() {
	 	return idNo;
	}
	/**
	 * @设置 证件号码
	 * @param idNo
	 */
	public void setIdNo(String idNo) {
	 	this.idNo = idNo;
	}
	/**
	 * @return 客户类型
	 */
	public String getCifType() {
	 	return cifType;
	}
	/**
	 * @设置 客户类型
	 * @param cifType
	 */
	public void setCifType(String cifType) {
	 	this.cifType = cifType;
	}
	/**
	 * @return 性别
	 */
	public String getSex() {
	 	return sex;
	}
	/**
	 * @设置 性别
	 * @param sex
	 */
	public void setSex(String sex) {
	 	this.sex = sex;
	}
	/**
	 * @return 出生日期
	 */
	public String getBirthDay() {
	 	return birthDay;
	}
	/**
	 * @设置 出生日期
	 * @param birthDay
	 */
	public void setBirthDay(String birthDay) {
	 	this.birthDay = birthDay;
	}
	/**
	 * @return 婚姻状况
	 */
	public String getMarriage() {
	 	return marriage;
	}
	/**
	 * @设置  是否有子女
	 * @param children
	 */
	public void setChildren(String children) {
	 	this.children = children;
	}
	/**
	 * @return 是否有子女
	 */
	public String getChildren() {
	 	return children;
	}
	/**
	 * @设置 婚姻状况
	 * @param marriage
	 */
	public void setMarriage(String marriage) {
	 	this.marriage = marriage;
	}
	/**
	 * @return 最高学历
	 */
	public String getEdu() {
	 	return edu;
	}
	/**
	 * @设置 最高学历
	 * @param edu
	 */
	public void setEdu(String edu) {
	 	this.edu = edu;
	}
	/**
	 * @return 最高学位
	 */
	public String getDegree() {
	 	return degree;
	}
	/**
	 * @设置 最高学位
	 * @param degree
	 */
	public void setDegree(String degree) {
	 	this.degree = degree;
	}
	/**
	 * @return 月收入（元）
	 */
	public Double getIncome() {
	 	return income;
	}
	/**
	 * @设置 月收入（元）
	 * @param income
	 */
	public void setIncome(Double income) {
	 	this.income = income;
	}
	/**
	 * @return 联系电话
	 */
	public String getCommTel() {
	 	return commTel;
	}
	/**
	 * @设置 联系电话
	 * @param commTel
	 */
	public void setCommTel(String commTel) {
	 	this.commTel = commTel;
	}
	/**
	 * @return 手机号码
	 */
	public String getPhoneNo() {
	 	return phoneNo;
	}
	/**
	 * @设置 手机号码
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
	 	this.phoneNo = phoneNo;
	}
	/**
	 * @return 通讯邮编
	 */
	public String getCommCode() {
	 	return commCode;
	}
	/**
	 * @设置 通讯邮编
	 * @param commCode
	 */
	public void setCommCode(String commCode) {
	 	this.commCode = commCode;
	}
	/**
	 * @return 通讯地址
	 */
	public String getCommAddr() {
	 	return commAddr;
	}
	/**
	 * @设置 通讯地址
	 * @param commAddr
	 */
	public void setCommAddr(String commAddr) {
	 	this.commAddr = commAddr;
	}
	/**
	 * @return 户籍归属
	 */
	public String getCifArea() {
	 	return cifArea;
	}
	/**
	 * @设置 户籍归属
	 * @param cifArea
	 */
	public void setCifArea(String cifArea) {
	 	this.cifArea = cifArea;
	}
	/**
	 * @return 住宅电话
	 */
	public String getResTel() {
	 	return resTel;
	}
	/**
	 * @设置 住宅电话
	 * @param resTel
	 */
	public void setResTel(String resTel) {
	 	this.resTel = resTel;
	}
	/**
	 * @return 住宅邮编
	 */
	public String getResCode() {
	 	return resCode;
	}
	/**
	 * @设置 住宅邮编
	 * @param resCode
	 */
	public void setResCode(String resCode) {
	 	this.resCode = resCode;
	}
	/**
	 * @return 住宅地址
	 */
	public String getResAddr() {
	 	return resAddr;
	}
	/**
	 * @设置 住宅地址
	 * @param resAddr
	 */
	public void setResAddr(String resAddr) {
	 	this.resAddr = resAddr;
	}
	/**
	 * @return 居住状况
	 */
	public String getResSts() {
	 	return resSts;
	}
	/**
	 * @设置 居住状况
	 * @param resSts
	 */
	public void setResSts(String resSts) {
	 	this.resSts = resSts;
	}
	/**
	 * @return 职业
	 */
	public String getWorkType() {
	 	return workType;
	}
	/**
	 * @设置 职业
	 * @param workType
	 */
	public void setWorkType(String workType) {
	 	this.workType = workType;
	}
	/**
	 * @return 工作单位名称
	 */
	public String getCorpName() {
	 	return corpName;
	}
	/**
	 * @设置 工作单位名称
	 * @param corpName
	 */
	public void setCorpName(String corpName) {
	 	this.corpName = corpName;
	}
	/**
	 * @return 工作单位所属行业
	 */
	public String getCorpWay() {
	 	return corpWay;
	}
	/**
	 * @设置 工作单位所属行业
	 * @param corpWay
	 */
	public void setCorpWay(String corpWay) {
	 	this.corpWay = corpWay;
	}
	/**
	 * @return 工作单位邮编
	 */
	public String getCorpCode() {
	 	return corpCode;
	}
	/**
	 * @设置 工作单位邮编
	 * @param corpCode
	 */
	public void setCorpCode(String corpCode) {
	 	this.corpCode = corpCode;
	}
	/**
	 * @return 工作单位地址
	 */
	public String getCorpAddr() {
	 	return corpAddr;
	}
	/**
	 * @设置 工作单位地址
	 * @param corpAddr
	 */
	public void setCorpAddr(String corpAddr) {
	 	this.corpAddr = corpAddr;
	}
	/**
	 * @return 职务
	 */
	public String getDuty() {
	 	return duty;
	}
	/**
	 * @设置 职务
	 * @param duty
	 */
	public void setDuty(String duty) {
	 	this.duty = duty;
	}
	/**
	 * @return 职称
	 */
	public String getTitle() {
	 	return title;
	}
	/**
	 * @设置 职称
	 * @param title
	 */
	public void setTitle(String title) {
	 	this.title = title;
	}
	/**
	 * @return 工作起始年份
	 */
	public String getWorkYear() {
	 	return workYear;
	}
	/**
	 * @设置 工作起始年份
	 * @param workYear
	 */
	public void setWorkYear(String workYear) {
	 	this.workYear = workYear;
	}
	/**
	 * @return 配偶名称
	 */
	public String getMateName() {
	 	return mateName;
	}
	/**
	 * @设置 配偶名称
	 * @param mateName
	 */
	public void setMateName(String mateName) {
	 	this.mateName = mateName;
	}
	/**
	 * @return 配偶证件类型
	 */
	public String getMateIdtype() {
	 	return mateIdtype;
	}
	/**
	 * @设置 配偶证件类型
	 * @param mateIdtype
	 */
	public void setMateIdtype(String mateIdtype) {
	 	this.mateIdtype = mateIdtype;
	}
	/**
	 * @return 配偶证件号码
	 */
	public String getMateIdno() {
	 	return mateIdno;
	}
	/**
	 * @设置 配偶证件号码
	 * @param mateIdno
	 */
	public void setMateIdno(String mateIdno) {
	 	this.mateIdno = mateIdno;
	}
	/**
	 * @return 配偶工作单位
	 */
	public String getMateWork() {
	 	return mateWork;
	}
	/**
	 * @设置 配偶工作单位
	 * @param mateWork
	 */
	public void setMateWork(String mateWork) {
	 	this.mateWork = mateWork;
	}
	/**
	 * @return 配偶联系电话
	 */
	public String getMateTel() {
	 	return mateTel;
	}
	/**
	 * @设置 配偶联系电话
	 * @param mateTel
	 */
	public void setMateTel(String mateTel) {
	 	this.mateTel = mateTel;
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
	public String getCzPactNo() {
		return czPactNo;
	}
	public void setCzPactNo(String czPactNo) {
		this.czPactNo = czPactNo;
	}
}