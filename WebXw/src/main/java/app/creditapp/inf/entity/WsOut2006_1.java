package app.creditapp.inf.entity;

import java.util.List;


/**
 * @作者 DHCC-LIU
 * @日期 Jun 23, 2016
 * @描述   预审批批次查询---输出实体类
 * <List>
 */
public class WsOut2006_1 {
	private String  appId;    //申请号
	private String  brNo;     //合作机构号
	private String  prePactNo;   //预审批ID
	private String  custName; //客户名称
	private String  idType;	  //证件类型
	private String  idNo;     //证件号码
	private String  custType; //客户类型
	private String  sex;	  //性别
	private String  birth;	  //出生日期
	private String  marriage; //婚姻状况
	private String  children; //是否有子女
	private String  edu;	  //最高学历
	private String  degree;	  //最高学位
	private String  telNo;	  //联系电话
	private String  phoneNo;  //手机号码
	private String  postCode; //通讯邮编
	private String  postAddr; //通讯地址
	private String  homeArea; //户籍归属地
	private String  homeTel;  //住宅电话
	private String  homeCode; //住宅邮编
	private String  homeAddr; //住宅地址
	private String  homeSts;  //居住状况
	private double  income;   //月收入（元）
	private String  mateName; //配偶信息
	private String  mateIdtype;//配偶证件类型
	private String  mateIdno;  //配偶证件号码
	private String  mateWork;  //配偶工作单位
	private String  mateTel;   //配偶联系电话
	private String  projNo;    //信托项目编号
	private String  prdtNo;    //产品号
	private double  pactAmt;   //合同金额
	private double  feeTotal;  //趸交费总额
	private double  lnRate;    //利率(月)
	private String  appArea;   //申请地点
	private String  appUse;    //申请用途
	private int     termMon;   //合同期限(月)
	private int     termDay;   //合同期限(日)
	private String  vouType;   //担保方式
	private String  endDate;   //到期日期
	private String  payType;   //扣款日类型
	private Integer payDay;	   //扣款日期
	private String  workType;  //职业
	private String  workName;  //工作单位名称
	private String  workWay;   //工作单位所属行业
	private String  workCode;  //工作单位邮编
	private String  workAddr;  //工作单位地址
	private String  workDuty;  //职务
	private String  workTitle; //职称
	private String  workYear;  //工作起始年份
	private String  ifCar;     //是否有车
	private String  ifCarCred; //是否有按揭贷款
	private String  ifRoom;    //是否有房
	private String  ifMort;    //是否有按揭房贷
	private String  ifCard;    //是否有贷记卡
	private double  cardAmt;   //贷记卡最低额度
	private String  ifApp;     //是否天填写申请表
	private String  ifId;      //是否有身份证信息
	private String  ifPact;    //是否已签订合同
	private String  appRersult;//处理结果
	private String  appDesc;   //处理说明
	private String workSts; //工作状态
	
	
	public String getWorkSts() {
		return workSts;
	}
	public void setWorkSts(String workSts) {
		this.workSts = workSts;
	}
	/*listGage*/
	private List<WsOut2006_1_1> listGage;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	

	public List<WsOut2006_1_1> getListGage() {
		return listGage;
	}

	public void setListGage(List<WsOut2006_1_1> listGage) {
		this.listGage = listGage;
	}

	public String getBrNo() {
		return brNo;
	}

	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}

	public String getPrePactNo() {
		return prePactNo;
	}

	public void setPrePactNo(String prePactNo) {
		this.prePactNo = prePactNo;
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

	public String getAppRersult() {
		return appRersult;
	}

	public void setAppRersult(String appRersult) {
		this.appRersult = appRersult;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	

	
}
