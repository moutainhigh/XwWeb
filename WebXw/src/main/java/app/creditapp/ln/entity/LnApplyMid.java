package app.creditapp.ln.entity;
import java.util.List;

import app.base.BaseDomain;
import app.creditapp.ln.entity.LnAcctMid;
import app.creditapp.ln.entity.LnGageMid;
/**
* Title: LnApplyMid.java
* Description:
* @version：1.0
**/
public class LnApplyMid extends BaseDomain {
	private String batchNo;//批次号码
	private String appId;//申请ID
	private String txDate;//登记时间
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
	private Double appAmt;//申请金额
	private Double pactAmt;//合同金额
	private Double lnRate;//利率（月）
	private double lnRateYear;// 利率（年）
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
	private Double cardAmt;//贷记卡最低额度
	private String appSts;//申请状态[01未处理02已处理]
	private String chkRes;//筛查结果[01成功02黑名单客户03评级拒绝04借款人原因05其他原因]
	private String chkDesc;//筛查结果描述
	private String apprType;//审批类型
	private String prdtName;
	private String prePactNo;
	private String czPactNo;//查征流水号
	private String workSts; //工作状态
	private String rgappSts; //工作状态
	private String zdappSts; //工作状态
	private String chkSts; //工作状态
	private String brName;//合作机构名称
	private String projName;//产品名称
	//后期添加——wt
	private String paySts; //工作状态
	private String cardChn;//虚拟账户渠道方[]
	private String cifAreaName;//产品名称
	private String appAreaName;//产品名称
	private String loginid;//修改人员
	//zlc args
	private String args;//参数字段
	
	public double getLnRateYear() {
		return lnRateYear;
	}
	public void setLnRateYear(double lnRateYear) {
		this.lnRateYear = lnRateYear;
	}
	public String getWorkSts() {
		return workSts;
	}
	public void setWorkSts(String workSts) {
		this.workSts = workSts;
	}
	private List<LnAcctMid>  acctMidList;  //  进件账户信息
	private List<LnGageMid>  gageMidList;  //  预进件押品信息
	
	
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
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
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
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
	public String getCifType() {
		return cifType;
	}
	public void setCifType(String cifType) {
		this.cifType = cifType;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
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
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public String getCommTel() {
		return commTel;
	}
	public void setCommTel(String commTel) {
		this.commTel = commTel;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCommCode() {
		return commCode;
	}
	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}
	public String getCommAddr() {
		return commAddr;
	}
	public void setCommAddr(String commAddr) {
		this.commAddr = commAddr;
	}
	public String getCifArea() {
		return cifArea;
	}
	public void setCifArea(String cifArea) {
		this.cifArea = cifArea;
	}
	public String getResTel() {
		return resTel;
	}
	public void setResTel(String resTel) {
		this.resTel = resTel;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResAddr() {
		return resAddr;
	}
	public void setResAddr(String resAddr) {
		this.resAddr = resAddr;
	}
	public String getResSts() {
		return resSts;
	}
	public void setResSts(String resSts) {
		this.resSts = resSts;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpWay() {
		return corpWay;
	}
	public void setCorpWay(String corpWay) {
		this.corpWay = corpWay;
	}
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public String getCorpAddr() {
		return corpAddr;
	}
	public void setCorpAddr(String corpAddr) {
		this.corpAddr = corpAddr;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getMateName() {
		return mateName;
	}
	public void setMateName(String mateName) {
		this.mateName = mateName;
	}
	public String getMateIdtype() {
		return mateIdtype;
	}
	public void setMateIdtype(String mateIdtype) {
		this.mateIdtype = mateIdtype;
	}
	public String getMateIdno() {
		return mateIdno;
	}
	public void setMateIdno(String mateIdno) {
		this.mateIdno = mateIdno;
	}
	public String getMateWork() {
		return mateWork;
	}
	public void setMateWork(String mateWork) {
		this.mateWork = mateWork;
	}
	public String getMateTel() {
		return mateTel;
	}
	public void setMateTel(String mateTel) {
		this.mateTel = mateTel;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getPrdtNo() {
		return prdtNo;
	}
	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}
	public String getLnType() {
		return lnType;
	}
	public void setLnType(String lnType) {
		this.lnType = lnType;
	}
	public String getCurNo() {
		return curNo;
	}
	public void setCurNo(String curNo) {
		this.curNo = curNo;
	}
	public Double getAppAmt() {
		return appAmt;
	}
	public void setAppAmt(Double appAmt) {
		this.appAmt = appAmt;
	}
	public Double getPactAmt() {
		return pactAmt;
	}
	public void setPactAmt(Double pactAmt) {
		this.pactAmt = pactAmt;
	}
	public Double getLnRate() {
		return lnRate;
	}
	public void setLnRate(Double lnRate) {
		this.lnRate = lnRate;
	}
	public Double getOverRate() {
		return overRate;
	}
	public void setOverRate(Double overRate) {
		this.overRate = overRate;
	}
	public Double getDamRate() {
		return damRate;
	}
	public void setDamRate(Double damRate) {
		this.damRate = damRate;
	}
	public String getAppArea() {
		return appArea;
	}
	public void setAppArea(String appArea) {
		this.appArea = appArea;
	}
	public String getLnUse() {
		return lnUse;
	}
	public void setLnUse(String lnUse) {
		this.lnUse = lnUse;
	}
	public String getRepayType() {
		return repayType;
	}
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	public Integer getTermMon() {
		return termMon;
	}
	public void setTermMon(Integer termMon) {
		this.termMon = termMon;
	}
	public Integer getTermDay() {
		return termDay;
	}
	public void setTermDay(Integer termDay) {
		this.termDay = termDay;
	}
	public String getVouType() {
		return vouType;
	}
	public void setVouType(String vouType) {
		this.vouType = vouType;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayKind() {
		return payKind;
	}
	public void setPayKind(String payKind) {
		this.payKind = payKind;
	}
	public Integer getPayDay() {
		return payDay;
	}
	public void setPayDay(Integer payDay) {
		this.payDay = payDay;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getPayMent() {
		return payMent;
	}
	public void setPayMent(String payMent) {
		this.payMent = payMent;
	}
	public Integer getVouDays() {
		return vouDays;
	}
	public void setVouDays(Integer vouDays) {
		this.vouDays = vouDays;
	}
	public Double getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(Double feeTotal) {
		this.feeTotal = feeTotal;
	}
	public Double getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(Double feeRate) {
		this.feeRate = feeRate;
	}
	public Double getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(Double feeAmt) {
		this.feeAmt = feeAmt;
	}
	public Double getSrvRate() {
		return srvRate;
	}
	public void setSrvRate(Double srvRate) {
		this.srvRate = srvRate;
	}
	public Double getSrvAmt() {
		return srvAmt;
	}
	public void setSrvAmt(Double srvAmt) {
		this.srvAmt = srvAmt;
	}
	public Double getVouRate() {
		return vouRate;
	}
	public void setVouRate(Double vouRate) {
		this.vouRate = vouRate;
	}
	public Double getVouAmt() {
		return vouAmt;
	}
	public void setVouAmt(Double vouAmt) {
		this.vouAmt = vouAmt;
	}
	public Double getFeeAmt1() {
		return feeAmt1;
	}
	public void setFeeAmt1(Double feeAmt1) {
		this.feeAmt1 = feeAmt1;
	}
	public Double getFeeAmt2() {
		return feeAmt2;
	}
	public void setFeeAmt2(Double feeAmt2) {
		this.feeAmt2 = feeAmt2;
	}
	public Double getFeeAmt3() {
		return feeAmt3;
	}
	public void setFeeAmt3(Double feeAmt3) {
		this.feeAmt3 = feeAmt3;
	}
	public Double getFeeAmt4() {
		return feeAmt4;
	}
	public void setFeeAmt4(Double feeAmt4) {
		this.feeAmt4 = feeAmt4;
	}
	public Double getFeeAmt5() {
		return feeAmt5;
	}
	public void setFeeAmt5(Double feeAmt5) {
		this.feeAmt5 = feeAmt5;
	}
	public String getIfItp() {
		return ifItp;
	}
	public void setIfItp(String ifItp) {
		this.ifItp = ifItp;
	}
	public String getIfApp() {
		return ifApp;
	}
	public void setIfApp(String ifApp) {
		this.ifApp = ifApp;
	}
	public String getIfId() {
		return ifId;
	}
	public void setIfId(String ifId) {
		this.ifId = ifId;
	}
	public String getIfAuth() {
		return ifAuth;
	}
	public void setIfAuth(String ifAuth) {
		this.ifAuth = ifAuth;
	}
	public String getIfPact() {
		return ifPact;
	}
	public void setIfPact(String ifPact) {
		this.ifPact = ifPact;
	}
	public String getIfFlag() {
		return ifFlag;
	}
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
	public Double getCardAmt() {
		return cardAmt;
	}
	public void setCardAmt(Double cardAmt) {
		this.cardAmt = cardAmt;
	}
	public String getAppSts() {
		return appSts;
	}
	public void setAppSts(String appSts) {
		this.appSts = appSts;
	}
	public String getChkRes() {
		return chkRes;
	}
	public void setChkRes(String chkRes) {
		this.chkRes = chkRes;
	}
	public String getChkDesc() {
		return chkDesc;
	}
	public void setChkDesc(String chkDesc) {
		this.chkDesc = chkDesc;
	}
	public List<LnAcctMid> getAcctMidList() {
		return acctMidList;
	}
	public void setAcctMidList(List<LnAcctMid> acctMidList) {
		this.acctMidList = acctMidList;
	}
	public List<LnGageMid> getGageMidList() {
		return gageMidList;
	}
	public void setGageMidList(List<LnGageMid> gageMidList) {
		this.gageMidList = gageMidList;
	}
	public String getApprType() {
		return apprType;
	}
	public void setApprType(String apprType) {
		this.apprType = apprType;
	}
	public String getPrePactNo() {
		return prePactNo;
	}
	public void setPrePactNo(String prePactNo) {
		this.prePactNo = prePactNo;
	}
	public String getCzPactNo() {
		return czPactNo;
	}
	public void setCzPactNo(String czPactNo) {
		this.czPactNo = czPactNo;
	}
	public String getRgappSts() {
		return rgappSts;
	}
	public void setRgappSts(String rgappSts) {
		this.rgappSts = rgappSts;
	}
	public String getZdappSts() {
		return zdappSts;
	}
	public void setZdappSts(String zdappSts) {
		this.zdappSts = zdappSts;
	}
	public String getChkSts() {
		return chkSts;
	}
	public void setChkSts(String chkSts) {
		this.chkSts = chkSts;
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
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getCardChn() {
		return cardChn;
	}
	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
	public String getCifAreaName() {
		return cifAreaName;
	}
	public void setCifAreaName(String cifAreaName) {
		this.cifAreaName = cifAreaName;
	}
	public String getAppAreaName() {
		return appAreaName;
	}
	public void setAppAreaName(String appAreaName) {
		this.appAreaName = appAreaName;
	}
	/**
	 * @return the paySts
	 */
	public String getPaySts() {
		return paySts;
	}
	/**
	 * @param paySts the paySts to set
	 */
	public void setPaySts(String paySts) {
		this.paySts = paySts;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	

}