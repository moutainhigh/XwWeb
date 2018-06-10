package app.creditapp.inf.entity;

import java.util.List;

/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016 list
 * @描述 进件批量申请--输入实体类 LN_APPLY_MID
 */
public class WsIn2101_1 {
	
	private String pactNo;// 合同号码
	private String custName;// 客户名称
	private String idType;// 证件类型
	private String idNo;// 证件号码
	private String custType;// 客户类型
	private String sex;// 性别
	private String birth;// 出生日期
	private String marriage;// 婚姻状况
	private String children;// 是否有子女
	private String edu;// 最高学历
	private String degree;// 最高学位
	private String telNo;// 联系电话
	private String phoneNo;// 手机号码
	private String postCode;// 通讯邮编
	private String postAddr;// 通讯地址
	private String homeArea;// 户籍归属地
	private String homeTel;// 住宅电话
	private String homeCode;// 住宅邮编
	private String homeAddr;// 住宅地址
	private String homeSts;// 居住状况
	private double income;// 月收入（元）
	private String mateName;// 配偶名称
	private String mateIdtype;// 配偶证件类型
	private String mateIdno;// 配偶证件号码
	private String mateWork;// 配偶工作单位
	private String mateTel;// 配偶联系电话
	private String projNo;// 信托项目编号
	private String prdtNo;// 产品号
	private double pactAmt;// 合同金额
	private double feeTotal;// 趸交费总额
	private double lnRate;// 利率（月）
	private double lnRateYear;// 利率（年）
	private String appArea;// 申请地点
	private String appUse;// 申请用途
	private int    termMon;// 合同期限（月）
	private int    termDay;// 合同期限（日）
	private String vouType;// 担保方式
	private String endDate;// 到期日期
	private String payType;// 扣款日类型
	private Integer  payDay;// 扣款日期
	private String workType;// 职业
	private String workName;// 工作单位名称
	private String workWay;// 工作单位所属行业
	private String workCode;// 工作单位邮编
	private String workAddr;// 工作单位地址
	private String workDuty;// 职务
	private String workTitle;// 职称
	private String workYear;// 工作起始年份
	private String ifCar;// 是否有车
	private String ifCarCred;// 是否有按揭车贷
	private String ifRoom;// 是否有房
	private String ifMort;// 是否有按揭房贷
	private String ifCard;// 是否有贷记卡
	private double cardAmt;// 贷记卡最低额度
	private String ifApp;// 是否填写申请表
	private String ifId;// 是否有身份证信息
	private String ifPact;// 是否以签订合同
	private String cardChn;// 是否以签订合同
	
	//添加
	private String appId;//申请ID
	private String batchNo;//批次号码
	private String brNo; //合作机构号码
	private String appDate;//进件日期
	private String pactXt;//信托合同编号
	private String brAccType;//机构核算类型[A/B]
	private String lnType;//贷款类型
	private String curNo;//申请币种
	private String appAmt;//申请金额
	private String overRate;//罚息利率
	private String damRate;//提前还款违约金比率
	private String repayType;//还款方式
	private String begDate;//放款日期
	private String payKind;//扣款日类别、还款频率
	private String feeType;//缴费方式
	private String payMent;//支付方式[01自助02受托]
	private String vouDays;//履行担保天数
	private String feeRate;//手续费率
	private String feeAmt;//手续费
	private String srvRate;//服务费率
	private String srvAmt;//服务费
	private String vouRate;//担保费率
	private double vouAmt;//担保费
	private String feeAmt1;//手续费1
	private String feeAmt2;//手续费2
	private String feeAmt3;//手续费3
	private String feeAmt4;//手续费4
	private String feeAmt5;//手续费5
	private String ifItp;//是否查征
	private String ifAuth;//是否有查证授权文件
	private String ifFlag;//备用标志
	private String appSts;//申请状态[01未处理02已处理]
	private String chkRes;//筛查结果[00未筛查01成功02文件错误03字段错误04黑名单客户05评级拒绝06查征拒绝07重复作废08配偶信息不完整09合作机构编号无效10信托项目编号无效，或者与合作机构编号不符11产品号不存在或者无效12不存在押品信息13押品抵押价值不足14账户信息不完整15账户放款金额总和不等于合同金额99其他错误]
	private String chkDesc;//筛查结果描述
	private String txDate;//登记时间
	private String prePactNo;//登记时间
	private String czPactNo;//登记时间
	private String workSts; //工作状态
	private String args; //参数
	
	
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
	private List<WsIn2101_1_1> listAc; //listAc
	private List<WsIn2101_1_2> listGage; //listGage
	private List<WsIn2101_1_3> listCom; //listCom
	private List<WsIn2101_1_4> listRel; //listRel
	
	
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
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
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
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
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPostAddr() {
		return postAddr;
	}
	public void setPostAddr(String postAddr) {
		this.postAddr = postAddr;
	}
	public String getHomeArea() {
		return homeArea;
	}
	public void setHomeArea(String homeArea) {
		this.homeArea = homeArea;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public String getHomeCode() {
		return homeCode;
	}
	public void setHomeCode(String homeCode) {
		this.homeCode = homeCode;
	}
	public String getHomeAddr() {
		return homeAddr;
	}
	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}
	public String getHomeSts() {
		return homeSts;
	}
	public void setHomeSts(String homeSts) {
		this.homeSts = homeSts;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
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
	public double getPactAmt() {
		return pactAmt;
	}
	public void setPactAmt(double pactAmt) {
		this.pactAmt = pactAmt;
	}
	public double getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
	}
	public double getLnRate() {
		return lnRate;
	}
	public void setLnRate(double lnRate) {
		this.lnRate = lnRate;
	}
	public String getAppArea() {
		return appArea;
	}
	public void setAppArea(String appArea) {
		this.appArea = appArea;
	}
	public String getAppUse() {
		return appUse;
	}
	public void setAppUse(String appUse) {
		this.appUse = appUse;
	}
	public int getTermMon() {
		return termMon;
	}
	public void setTermMon(int termMon) {
		this.termMon = termMon;
	}
	public int getTermDay() {
		return termDay;
	}
	public void setTermDay(int termDay) {
		this.termDay = termDay;
	}
	public String getVouType() {
		return vouType;
	}
	public void setVouType(String vouType) {
		this.vouType = vouType;
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
	
	public Integer getPayDay() {
		return payDay;
	}
	public void setPayDay(Integer payDay) {
		this.payDay = payDay;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getWorkWay() {
		return workWay;
	}
	public void setWorkWay(String workWay) {
		this.workWay = workWay;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getWorkAddr() {
		return workAddr;
	}
	public void setWorkAddr(String workAddr) {
		this.workAddr = workAddr;
	}
	public String getWorkDuty() {
		return workDuty;
	}
	public void setWorkDuty(String workDuty) {
		this.workDuty = workDuty;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getIfCar() {
		return ifCar;
	}
	public void setIfCar(String ifCar) {
		this.ifCar = ifCar;
	}
	public String getIfCarCred() {
		return ifCarCred;
	}
	public void setIfCarCred(String ifCarCred) {
		this.ifCarCred = ifCarCred;
	}
	public String getIfRoom() {
		return ifRoom;
	}
	public void setIfRoom(String ifRoom) {
		this.ifRoom = ifRoom;
	}
	public String getIfMort() {
		return ifMort;
	}
	public void setIfMort(String ifMort) {
		this.ifMort = ifMort;
	}
	public String getIfCard() {
		return ifCard;
	}
	public void setIfCard(String ifCard) {
		this.ifCard = ifCard;
	}
	public double getCardAmt() {
		return cardAmt;
	}
	public void setCardAmt(double cardAmt) {
		this.cardAmt = cardAmt;
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
	public String getIfPact() {
		return ifPact;
	}
	public void setIfPact(String ifPact) {
		this.ifPact = ifPact;
	}
	public List<WsIn2101_1_1> getListAc() {
		return listAc;
	}
	public void setListAc(List<WsIn2101_1_1> listAc) {
		this.listAc = listAc;
	}
	public List<WsIn2101_1_2> getListGage() {
		return listGage;
	}
	public void setListGage(List<WsIn2101_1_2> listGage) {
		this.listGage = listGage;
	}
	public List<WsIn2101_1_3> getListCom() {
		return listCom;
	}
	public void setListCom(List<WsIn2101_1_3> listCom) {
		this.listCom = listCom;
	}
	public List<WsIn2101_1_4> getListRel() {
		return listRel;
	}
	public void setListRel(List<WsIn2101_1_4> listRel) {
		this.listRel = listRel;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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
	public String getAppAmt() {
		return appAmt;
	}
	public void setAppAmt(String appAmt) {
		this.appAmt = appAmt;
	}
	public String getOverRate() {
		return overRate;
	}
	public void setOverRate(String overRate) {
		this.overRate = overRate;
	}
	public String getDamRate() {
		return damRate;
	}
	public void setDamRate(String damRate) {
		this.damRate = damRate;
	}
	public String getRepayType() {
		return repayType;
	}
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getPayKind() {
		return payKind;
	}
	public void setPayKind(String payKind) {
		this.payKind = payKind;
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
	public String getVouDays() {
		return vouDays;
	}
	public void setVouDays(String vouDays) {
		this.vouDays = vouDays;
	}
	public String getFeeRate() {
		return feeRate;
	}
	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}
	public String getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getSrvRate() {
		return srvRate;
	}
	public void setSrvRate(String srvRate) {
		this.srvRate = srvRate;
	}
	public String getSrvAmt() {
		return srvAmt;
	}
	public void setSrvAmt(String srvAmt) {
		this.srvAmt = srvAmt;
	}
	public String getVouRate() {
		return vouRate;
	}
	public void setVouRate(String vouRate) {
		this.vouRate = vouRate;
	}
	
	public double getVouAmt() {
		return vouAmt;
	}
	public void setVouAmt(double vouAmt) {
		this.vouAmt = vouAmt;
	}
	public String getFeeAmt1() {
		return feeAmt1;
	}
	public void setFeeAmt1(String feeAmt1) {
		this.feeAmt1 = feeAmt1;
	}
	public String getFeeAmt2() {
		return feeAmt2;
	}
	public void setFeeAmt2(String feeAmt2) {
		this.feeAmt2 = feeAmt2;
	}
	public String getFeeAmt3() {
		return feeAmt3;
	}
	public void setFeeAmt3(String feeAmt3) {
		this.feeAmt3 = feeAmt3;
	}
	public String getFeeAmt4() {
		return feeAmt4;
	}
	public void setFeeAmt4(String feeAmt4) {
		this.feeAmt4 = feeAmt4;
	}
	public String getFeeAmt5() {
		return feeAmt5;
	}
	public void setFeeAmt5(String feeAmt5) {
		this.feeAmt5 = feeAmt5;
	}
	public String getIfItp() {
		return ifItp;
	}
	public void setIfItp(String ifItp) {
		this.ifItp = ifItp;
	}
	public String getIfAuth() {
		return ifAuth;
	}
	public void setIfAuth(String ifAuth) {
		this.ifAuth = ifAuth;
	}
	public String getIfFlag() {
		return ifFlag;
	}
	public void setIfFlag(String ifFlag) {
		this.ifFlag = ifFlag;
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
	public String getTxDate() {
		return txDate;
	}
	public void setTxDate(String txDate) {
		this.txDate = txDate;
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
	public String getCardChn() {
		return cardChn;
	}
	public void setCardChn(String cardChn) {
		this.cardChn = cardChn;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}

	
}
