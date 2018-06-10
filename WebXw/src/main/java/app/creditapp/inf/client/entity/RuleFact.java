package app.creditapp.inf.client.entity;

public class RuleFact {
	
	//客户评级条件
	private String cifName;//客户名称
	private int ageScore;
	private int cifNameScore;//客户名称得分
	private int score;//客户评级得分

	
	private String cifArea;//户籍归属
	private String appArea;//申请地点
	private String chkRes;//筛查结果[01成功02黑名单客户03评级拒绝04借款人原因05其他原因]
	private String phoneNo;//手机号
	private String resTel;//电话号

	//人工审批策略
	private String appType;//审批类型
	private Double appAmt;//申请金额
	private String vouType;//担保方式
	private String workYear;//工作起始年份
	
	//自动审批条件
	private String prdtNo;//性别
	private int age;//年龄--根据生日计算
	private String sex;//性别
	private String marriage;//婚姻状况
	private String children;//婚姻状况
	private String edu;//学历
	private String cifType;//从业类型
	private Double income;//最低月收入
	private String ifBendi;//是否本地户籍
	private String ifCar;//是否有车
	private String ifCarcred;//是否有车
	private String ifPhone;//是否有联系电话
	private String ifWork;//是否有联系电话
	private String ifRoom;//是否有fang
	private String ifMort;//是否有fang
	private String ifBlack;//是否有fang
	private String ifCard;//是否有fang
	private Double cardAmt;//是否有fang
	//自动审批结果
	private String ageRes;//年龄
	private String sexRes;//性别
	private String marriageRes;//婚姻状况
	private String eduRes;//学历
	private String cifTypeRes;//从业类型
	private String incomeRes;//最低月收入
	private String ifBendiRes;//是否本地户籍 appArea cifArea
	private String ifCarRes;//是否有车
	private String ifPhoneRes;//是否有联系电话
	private String ifWorkRes;//是否有联系电话
	private String ifRoomRes;//是否有联系电话
	private String ifBlackRes;//是否有fang
	private String cardAmtRes;//贷记卡最低额度
	private String ifCardRes;//是否有车结果
	private String ifMortRes;//是否有按揭房贷结果
	private String ifCarcredRes;//是否有按揭车贷结果
	private String childrenRes;//是否有子女结果
	private String workYearRes;//工作起始年份结果
	private String appRes;//自动审批结果
	
	//zlc 
	private String arg1;//参数字段1
	private String arg2;//参数字段2
	private String arg3;//参数字段3
	private String arg4;//参数字段4
	private String arg5;//参数字段5
	private String arg6;//参数字段6
	private String arg7;//参数字段7
	private String arg8;//参数字段8
	private String arg9;//参数字段9
	private String arg10;//参数字段10
	private String arg11;//参数字段11
	private String arg12;//参数字段12
	private String arg13;//参数字段13
	private String arg14;//参数字段14
	private String arg15;//参数字段15
	private String arg16;//参数字段16
	private String arg17;//参数字段17
	private String arg18;//参数字段18
	private String arg19;//参数字段19
	private String arg20;//参数字段20
	private String arg21;//参数字段21
	private String arg22;//参数字段22
	private String arg23;//参数字段23
	private String arg24;//参数字段24
	private String arg25;//参数字段25
	private String arg26;//参数字段26
	private String arg27;//参数字段27
	private String arg28;//参数字段28
	private String arg29;//参数字段29
	private String arg30;//参数字段30
	private String arg31;//参数字段31
	private String arg32;//参数字段32
	private String arg33;//参数字段33
	private String arg34;//参数字段34
	private String arg35;//参数字段35
	private String arg36;//参数字段36
	private String arg37;//参数字段37
	private String arg38;//参数字段38
	private String arg39;//参数字段39
	private String arg40;//参数字段40
	private String arg41;//参数字段41
	private String arg42;//参数字段42
	private String arg43;//参数字段43
	private String arg44;//参数字段44
	private String arg45;//参数字段45
	private String arg46;//参数字段46
	private String arg47;//参数字段47
	private String arg48;//参数字段48
	private String arg49;//参数字段49		

	private String arg1Res;//参数字段1
	private String arg2Res;//参数字段2
	private String arg3Res;//参数字段3
	private String arg4Res;//参数字段4
	private String arg5Res;//参数字段5
	private String arg6Res;//参数字段6
	private String arg7Res;//参数字段7
	private String arg8Res;//参数字段8
	private String arg9Res;//参数字段9
	private String arg10Res;//参数字段10
	private String arg11Res;//参数字段11
	private String arg12Res;//参数字段12
	private String arg13Res;//参数字段13
	private String arg14Res;//参数字段14
	private String arg15Res;//参数字段15
	private String arg16Res;//参数字段16
	private String arg17Res;//参数字段17
	private String arg18Res;//参数字段18
	private String arg19Res;//参数字段19
	private String arg20Res;//参数字段20
	private String arg21Res;//参数字段21
	private String arg22Res;//参数字段22
	private String arg23Res;//参数字段23
	private String arg24Res;//参数字段24
	private String arg25Res;//参数字段25
	private String arg26Res;//参数字段26
	private String arg27Res;//参数字段27
	private String arg28Res;//参数字段28
	private String arg29Res;//参数字段29
	private String arg30Res;//参数字段30
	private String arg31Res;//参数字段31
	private String arg32Res;//参数字段32
	private String arg33Res;//参数字段33
	private String arg34Res;//参数字段34
	private String arg35Res;//参数字段35
	private String arg36Res;//参数字段36
	private String arg37Res;//参数字段37
	private String arg38Res;//参数字段38
	private String arg39Res;//参数字段39
	private String arg40Res;//参数字段40
	private String arg41Res;//参数字段41
	private String arg42Res;//参数字段42
	private String arg43Res;//参数字段43
	private String arg44Res;//参数字段44
	private String arg45Res;//参数字段45
	private String arg46Res;//参数字段46
	private String arg47Res;//参数字段47
	private String arg48Res;//参数字段48
	private String arg49Res;//参数字段49	
	
	//合作机构评级
	private Double bldkl;  //不良贷款率
	private Double bldklScore;  //不良贷款得分
	private Double dkdcl;  //贷款代偿率
	private Double dkdclScore;  //贷款代偿得分
	private Double dkhgl;  //贷款回购率
	private Double dkhglScore;  //贷款回购得分
	private Double dkqxbz;  //贷款期限比重
	private Double dkqxbzScore;  //贷款期限得分
	private Double dksxl;  //贷款收息率
	private Double dksxlScore;  //贷款收息得分
	private Double dydkbz;  //单一贷款比重
	private Double dydkbzScore;  //单一比重得分
	private Double gddkbz;  //股东贷款比重
	private Double gddkbzScore;  //股东比重得分
	private Double jzdkbz;  //建筑贷款比重
	private Double jzdkbzScore;  //建筑比重得分
	private Double lfzzl;  //累放增长率
	private Double lfzzlScore;  //累放增长得分
	private Double qsdkbz;  //前十贷款比重
	private Double qsdkbzScore;  //前十比重得分
	private Double sndkbz;  //三农贷款比重
	private Double sndkbzScore;  //三农比重得分
	private Double sszbl;  //损失准备率
	private Double sszblScore;  //损失准备得分
	private Double xwdkbz;  //小薇贷款比重
	private Double xwdkbzScore;  //小薇比重得分
	private Double xzkhl;  //新增客户率
	private Double xzkhlScore;  //新增客户得分
	private Double yezzl;  //余额增长率
	private Double yezzlScore;  //余额增长得分
	private Double zczb;  //注册资本
	private Double zczbScore;  //注册资本得分
	private Double zzkg;  //增资扩股
	private Double zzkgScore;  //增资扩股得分
	private Double ndgyzc;  //年度固有资产
	private Double ndgyzcScore;  //年度资产得分 
	
	
	public Double getCardAmt() {
		return cardAmt;
	}
	public void setCardAmt(Double cardAmt) {
		this.cardAmt = cardAmt;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	public int getAgeScore() {
		return ageScore;
	}
	public void setAgeScore(int ageScore) {
		this.ageScore = ageScore;
	}
	public int getCifNameScore() {
		return cifNameScore;
	}
	public void setCifNameScore(int cifNameScore) {
		this.cifNameScore = cifNameScore;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getCifArea() {
		return cifArea;
	}
	public void setCifArea(String cifArea) {
		this.cifArea = cifArea;
	}
	public String getAppArea() {
		return appArea;
	}
	public void setAppArea(String appArea) {
		this.appArea = appArea;
	}
	public String getChkRes() {
		return chkRes;
	}
	public void setChkRes(String chkRes) {
		this.chkRes = chkRes;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getResTel() {
		return resTel;
	}
	public void setResTel(String resTel) {
		this.resTel = resTel;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public Double getAppAmt() {
		return appAmt;
	}
	public void setAppAmt(Double appAmt) {
		this.appAmt = appAmt;
	}
	public String getVouType() {
		return vouType;
	}
	public void setVouType(String vouType) {
		this.vouType = vouType;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getCifType() {
		return cifType;
	}
	public void setCifType(String cifType) {
		this.cifType = cifType;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}

	public String getIfBendi() {
		return ifBendi;
	}
	public void setIfBendi(String ifBendi) {
		this.ifBendi = ifBendi;
	}
	public String getIfBendiRes() {
		return ifBendiRes;
	}
	public void setIfBendiRes(String ifBendiRes) {
		this.ifBendiRes = ifBendiRes;
	}
	public String getIfCar() {
		return ifCar;
	}
	public void setIfCar(String ifCar) {
		this.ifCar = ifCar;
	}
	public String getIfPhone() {
		return ifPhone;
	}
	public void setIfPhone(String ifPhone) {
		this.ifPhone = ifPhone;
	}
	public String getAgeRes() {
		return ageRes;
	}
	public void setAgeRes(String ageRes) {
		this.ageRes = ageRes;
	}
	public String getSexRes() {
		return sexRes;
	}
	public void setSexRes(String sexRes) {
		this.sexRes = sexRes;
	}
	public String getMarriageRes() {
		return marriageRes;
	}
	public void setMarriageRes(String marriageRes) {
		this.marriageRes = marriageRes;
	}
	public String getEduRes() {
		return eduRes;
	}
	public void setEduRes(String eduRes) {
		this.eduRes = eduRes;
	}
	public String getCifTypeRes() {
		return cifTypeRes;
	}
	public void setCifTypeRes(String cifTypeRes) {
		this.cifTypeRes = cifTypeRes;
	}
	public String getIncomeRes() {
		return incomeRes;
	}
	public void setIncomeRes(String incomeRes) {
		this.incomeRes = incomeRes;
	}
	public String getIfCarRes() {
		return ifCarRes;
	}
	public void setIfCarRes(String ifCarRes) {
		this.ifCarRes = ifCarRes;
	}
	public String getAppRes() {
		return appRes;
	}
	public void setAppRes(String appRes) {
		this.appRes = appRes;
	}
	public Double getBldkl() {
		return bldkl;
	}
	public void setBldkl(Double bldkl) {
		this.bldkl = bldkl;
	}
	public Double getBldklScore() {
		return bldklScore;
	}
	public void setBldklScore(Double bldklScore) {
		this.bldklScore = bldklScore;
	}
	public Double getDkdcl() {
		return dkdcl;
	}
	public void setDkdcl(Double dkdcl) {
		this.dkdcl = dkdcl;
	}
	public Double getDkdclScore() {
		return dkdclScore;
	}
	public void setDkdclScore(Double dkdclScore) {
		this.dkdclScore = dkdclScore;
	}
	public Double getDkhgl() {
		return dkhgl;
	}
	public void setDkhgl(Double dkhgl) {
		this.dkhgl = dkhgl;
	}
	public Double getDkhglScore() {
		return dkhglScore;
	}
	public void setDkhglScore(Double dkhglScore) {
		this.dkhglScore = dkhglScore;
	}
	public Double getDkqxbz() {
		return dkqxbz;
	}
	public void setDkqxbz(Double dkqxbz) {
		this.dkqxbz = dkqxbz;
	}
	public Double getDkqxbzScore() {
		return dkqxbzScore;
	}
	public void setDkqxbzScore(Double dkqxbzScore) {
		this.dkqxbzScore = dkqxbzScore;
	}
	public Double getDksxl() {
		return dksxl;
	}
	public void setDksxl(Double dksxl) {
		this.dksxl = dksxl;
	}
	public Double getDksxlScore() {
		return dksxlScore;
	}
	public void setDksxlScore(Double dksxlScore) {
		this.dksxlScore = dksxlScore;
	}
	public Double getDydkbz() {
		return dydkbz;
	}
	public void setDydkbz(Double dydkbz) {
		this.dydkbz = dydkbz;
	}
	public Double getDydkbzScore() {
		return dydkbzScore;
	}
	public void setDydkbzScore(Double dydkbzScore) {
		this.dydkbzScore = dydkbzScore;
	}
	public Double getGddkbz() {
		return gddkbz;
	}
	public void setGddkbz(Double gddkbz) {
		this.gddkbz = gddkbz;
	}
	public Double getGddkbzScore() {
		return gddkbzScore;
	}
	public void setGddkbzScore(Double gddkbzScore) {
		this.gddkbzScore = gddkbzScore;
	}
	public Double getJzdkbz() {
		return jzdkbz;
	}
	public void setJzdkbz(Double jzdkbz) {
		this.jzdkbz = jzdkbz;
	}
	public Double getJzdkbzScore() {
		return jzdkbzScore;
	}
	public void setJzdkbzScore(Double jzdkbzScore) {
		this.jzdkbzScore = jzdkbzScore;
	}
	public Double getLfzzl() {
		return lfzzl;
	}
	public void setLfzzl(Double lfzzl) {
		this.lfzzl = lfzzl;
	}
	public Double getLfzzlScore() {
		return lfzzlScore;
	}
	public void setLfzzlScore(Double lfzzlScore) {
		this.lfzzlScore = lfzzlScore;
	}
	public Double getQsdkbz() {
		return qsdkbz;
	}
	public void setQsdkbz(Double qsdkbz) {
		this.qsdkbz = qsdkbz;
	}
	public Double getQsdkbzScore() {
		return qsdkbzScore;
	}
	public void setQsdkbzScore(Double qsdkbzScore) {
		this.qsdkbzScore = qsdkbzScore;
	}
	public Double getSndkbz() {
		return sndkbz;
	}
	public void setSndkbz(Double sndkbz) {
		this.sndkbz = sndkbz;
	}
	public Double getSndkbzScore() {
		return sndkbzScore;
	}
	public void setSndkbzScore(Double sndkbzScore) {
		this.sndkbzScore = sndkbzScore;
	}
	public Double getSszbl() {
		return sszbl;
	}
	public void setSszbl(Double sszbl) {
		this.sszbl = sszbl;
	}
	public Double getSszblScore() {
		return sszblScore;
	}
	public void setSszblScore(Double sszblScore) {
		this.sszblScore = sszblScore;
	}
	public Double getXwdkbz() {
		return xwdkbz;
	}
	public void setXwdkbz(Double xwdkbz) {
		this.xwdkbz = xwdkbz;
	}
	public Double getXwdkbzScore() {
		return xwdkbzScore;
	}
	public void setXwdkbzScore(Double xwdkbzScore) {
		this.xwdkbzScore = xwdkbzScore;
	}
	public Double getXzkhl() {
		return xzkhl;
	}
	public void setXzkhl(Double xzkhl) {
		this.xzkhl = xzkhl;
	}
	public Double getXzkhlScore() {
		return xzkhlScore;
	}
	public void setXzkhlScore(Double xzkhlScore) {
		this.xzkhlScore = xzkhlScore;
	}
	public String getIfCard() {
		return ifCard;
	}
	public void setIfCard(String ifCard) {
		this.ifCard = ifCard;
	}
	public Double getYezzl() {
		return yezzl;
	}
	public void setYezzl(Double yezzl) {
		this.yezzl = yezzl;
	}
	public Double getYezzlScore() {
		return yezzlScore;
	}
	public void setYezzlScore(Double yezzlScore) {
		this.yezzlScore = yezzlScore;
	}
	public Double getZczb() {
		return zczb;
	}
	public void setZczb(Double zczb) {
		this.zczb = zczb;
	}
	public Double getZczbScore() {
		return zczbScore;
	}
	public void setZczbScore(Double zczbScore) {
		this.zczbScore = zczbScore;
	}
	public Double getZzkg() {
		return zzkg;
	}
	public void setZzkg(Double zzkg) {
		this.zzkg = zzkg;
	}
	public Double getZzkgScore() {
		return zzkgScore;
	}
	public void setZzkgScore(Double zzkgScore) {
		this.zzkgScore = zzkgScore;
	}
	public Double getNdgyzc() {
		return ndgyzc;
	}
	public void setNdgyzc(Double ndgyzc) {
		this.ndgyzc = ndgyzc;
	}
	public Double getNdgyzcScore() {
		return ndgyzcScore;
	}
	public void setNdgyzcScore(Double ndgyzcScore) {
		this.ndgyzcScore = ndgyzcScore;
	}
	public String getIfWork() {
		return ifWork;
	}
	public void setIfWork(String ifWork) {
		this.ifWork = ifWork;
	}
	public String getIfRoom() {
		return ifRoom;
	}
	public void setIfRoom(String ifRoom) {
		this.ifRoom = ifRoom;
	}
	public String getIfPhoneRes() {
		return ifPhoneRes;
	}
	public void setIfPhoneRes(String ifPhoneRes) {
		this.ifPhoneRes = ifPhoneRes;
	}
	public String getIfRoomRes() {
		return ifRoomRes;
	}
	public void setIfRoomRes(String ifRoomRes) {
		this.ifRoomRes = ifRoomRes;
	}
	public String getIfBlack() {
		return ifBlack;
	}
	public void setIfBlack(String ifBlack) {
		this.ifBlack = ifBlack;
	}
	public String getIfBlackRes() {
		return ifBlackRes;
	}
	public void setIfBlackRes(String ifBlackRes) {
		this.ifBlackRes = ifBlackRes;
	}
	public String getIfWorkRes() {
		return ifWorkRes;
	}
	public void setIfWorkRes(String ifWorkRes) {
		this.ifWorkRes = ifWorkRes;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
	}
	public String getIfCarcred() {
		return ifCarcred;
	}
	public void setIfCarcred(String ifCarcred) {
		this.ifCarcred = ifCarcred;
	}
	public String getIfMort() {
		return ifMort;
	}
	public void setIfMort(String ifMort) {
		this.ifMort = ifMort;
	}
	public String getPrdtNo() {
		return prdtNo;
	}
	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}
	/**
	 * @return the cardAmtRes
	 */
	public String getCardAmtRes() {
		return cardAmtRes;
	}
	/**
	 * @param cardAmtRes the cardAmtRes to set
	 */
	public void setCardAmtRes(String cardAmtRes) {
		this.cardAmtRes = cardAmtRes;
	}
	/**
	 * @return the ifCardRes
	 */
	public String getIfCardRes() {
		return ifCardRes;
	}
	/**
	 * @param ifCardRes the ifCardRes to set
	 */
	public void setIfCardRes(String ifCardRes) {
		this.ifCardRes = ifCardRes;
	}
	/**
	 * @return the ifMortRes
	 */
	public String getIfMortRes() {
		return ifMortRes;
	}
	/**
	 * @param ifMortRes the ifMortRes to set
	 */
	public void setIfMortRes(String ifMortRes) {
		this.ifMortRes = ifMortRes;
	}
	/**
	 * @return the ifCarcredRes
	 */
	public String getIfCarcredRes() {
		return ifCarcredRes;
	}
	/**
	 * @param ifCarcredRes the ifCarcredRes to set
	 */
	public void setIfCarcredRes(String ifCarcredRes) {
		this.ifCarcredRes = ifCarcredRes;
	}
	/**
	 * @return the childrenRes
	 */
	public String getChildrenRes() {
		return childrenRes;
	}
	/**
	 * @param childrenRes the childrenRes to set
	 */
	public void setChildrenRes(String childrenRes) {
		this.childrenRes = childrenRes;
	}
	/**
	 * @return the workYearRes
	 */
	public String getWorkYearRes() {
		return workYearRes;
	}
	/**
	 * @param workYearRes the workYearRes to set
	 */
	public void setWorkYearRes(String workYearRes) {
		this.workYearRes = workYearRes;
	}
	public String getarg1() {
		return arg1;
	}
	public void setarg1(String arg1) {
		this.arg1 = arg1;
	}
	public String getarg2() {
		return arg2;
	}
	public void setarg2(String arg2) {
		this.arg2 = arg2;
	}
	public String getarg3() {
		return arg3;
	}
	public void setarg3(String arg3) {
		this.arg3 = arg3;
	}
	public String getarg4() {
		return arg4;
	}
	public void setarg4(String arg4) {
		this.arg4 = arg4;
	}
	public String getarg5() {
		return arg5;
	}
	public void setarg5(String arg5) {
		this.arg5 = arg5;
	}
	public String getarg6() {
		return arg6;
	}
	public void setarg6(String arg6) {
		this.arg6 = arg6;
	}
	public String getarg7() {
		return arg7;
	}
	public void setarg7(String arg7) {
		this.arg7 = arg7;
	}
	public String getarg8() {
		return arg8;
	}
	public void setarg8(String arg8) {
		this.arg8 = arg8;
	}
	public String getarg9() {
		return arg9;
	}
	public void setarg9(String arg9) {
		this.arg9 = arg9;
	}
	public String getarg10() {
		return arg10;
	}
	public void setarg10(String arg10) {
		this.arg10 = arg10;
	}
	public String getarg11() {
		return arg11;
	}
	public void setarg11(String arg11) {
		this.arg11 = arg11;
	}
	public String getarg12() {
		return arg12;
	}
	public void setarg12(String arg12) {
		this.arg12 = arg12;
	}
	public String getarg13() {
		return arg13;
	}
	public void setarg13(String arg13) {
		this.arg13 = arg13;
	}
	public String getarg14() {
		return arg14;
	}
	public void setarg14(String arg14) {
		this.arg14 = arg14;
	}
	public String getarg15() {
		return arg15;
	}
	public void setarg15(String arg15) {
		this.arg15 = arg15;
	}
	public String getarg16() {
		return arg16;
	}
	public void setarg16(String arg16) {
		this.arg16 = arg16;
	}
	public String getarg17() {
		return arg17;
	}
	public void setarg17(String arg17) {
		this.arg17 = arg17;
	}
	public String getarg18() {
		return arg18;
	}
	public void setarg18(String arg18) {
		this.arg18 = arg18;
	}
	public String getarg19() {
		return arg19;
	}
	public void setarg19(String arg19) {
		this.arg19 = arg19;
	}
	public String getarg20() {
		return arg20;
	}
	public void setarg20(String arg20) {
		this.arg20 = arg20;
	}
	public String getarg21() {
		return arg21;
	}
	public void setarg21(String arg21) {
		this.arg21 = arg21;
	}
	public String getarg22() {
		return arg22;
	}
	public void setarg22(String arg22) {
		this.arg22 = arg22;
	}
	public String getarg23() {
		return arg23;
	}
	public void setarg23(String arg23) {
		this.arg23 = arg23;
	}
	public String getarg24() {
		return arg24;
	}
	public void setarg24(String arg24) {
		this.arg24 = arg24;
	}
	public String getarg25() {
		return arg25;
	}
	public void setarg25(String arg25) {
		this.arg25 = arg25;
	}
	public String getarg26() {
		return arg26;
	}
	public void setarg26(String arg26) {
		this.arg26 = arg26;
	}
	public String getarg27() {
		return arg27;
	}
	public void setarg27(String arg27) {
		this.arg27 = arg27;
	}
	public String getarg28() {
		return arg28;
	}
	public void setarg28(String arg28) {
		this.arg28 = arg28;
	}
	public String getarg29() {
		return arg29;
	}
	public void setarg29(String arg29) {
		this.arg29 = arg29;
	}
	public String getarg30() {
		return arg30;
	}
	public void setarg30(String arg30) {
		this.arg30 = arg30;
	}
	public String getarg31() {
		return arg31;
	}
	public void setarg31(String arg31) {
		this.arg31 = arg31;
	}
	public String getarg32() {
		return arg32;
	}
	public void setarg32(String arg32) {
		this.arg32 = arg32;
	}
	public String getarg33() {
		return arg33;
	}
	public void setarg33(String arg33) {
		this.arg33 = arg33;
	}
	public String getarg34() {
		return arg34;
	}
	public void setarg34(String arg34) {
		this.arg34 = arg34;
	}
	public String getarg35() {
		return arg35;
	}
	public void setarg35(String arg35) {
		this.arg35 = arg35;
	}
	public String getarg36() {
		return arg36;
	}
	public void setarg36(String arg36) {
		this.arg36 = arg36;
	}
	public String getarg37() {
		return arg37;
	}
	public void setarg37(String arg37) {
		this.arg37 = arg37;
	}
	public String getarg38() {
		return arg38;
	}
	public void setarg38(String arg38) {
		this.arg38 = arg38;
	}
	public String getarg39() {
		return arg39;
	}
	public void setarg39(String arg39) {
		this.arg39 = arg39;
	}
	public String getarg40() {
		return arg40;
	}
	public void setarg40(String arg40) {
		this.arg40 = arg40;
	}
	public String getarg41() {
		return arg41;
	}
	public void setarg41(String arg41) {
		this.arg41 = arg41;
	}
	public String getarg42() {
		return arg42;
	}
	public void setarg42(String arg42) {
		this.arg42 = arg42;
	}
	public String getarg43() {
		return arg43;
	}
	public void setarg43(String arg43) {
		this.arg43 = arg43;
	}
	public String getarg44() {
		return arg44;
	}
	public void setarg44(String arg44) {
		this.arg44 = arg44;
	}
	public String getarg45() {
		return arg45;
	}
	public void setarg45(String arg45) {
		this.arg45 = arg45;
	}
	public String getarg46() {
		return arg46;
	}
	public void setarg46(String arg46) {
		this.arg46 = arg46;
	}
	public String getarg47() {
		return arg47;
	}
	public void setarg47(String arg47) {
		this.arg47 = arg47;
	}
	public String getarg48() {
		return arg48;
	}
	public void setarg48(String arg48) {
		this.arg48 = arg48;
	}
	public String getarg49() {
		return arg49;
	}
	public void setarg49(String arg49) {
		this.arg49 = arg49;
	}
	public String getArg1() {
		return arg1;
	}
	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}
	public String getArg2() {
		return arg2;
	}
	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}
	public String getArg3() {
		return arg3;
	}
	public void setArg3(String arg3) {
		this.arg3 = arg3;
	}
	public String getArg4() {
		return arg4;
	}
	public void setArg4(String arg4) {
		this.arg4 = arg4;
	}
	public String getArg5() {
		return arg5;
	}
	public void setArg5(String arg5) {
		this.arg5 = arg5;
	}
	public String getArg6() {
		return arg6;
	}
	public void setArg6(String arg6) {
		this.arg6 = arg6;
	}
	public String getArg7() {
		return arg7;
	}
	public void setArg7(String arg7) {
		this.arg7 = arg7;
	}
	public String getArg8() {
		return arg8;
	}
	public void setArg8(String arg8) {
		this.arg8 = arg8;
	}
	public String getArg9() {
		return arg9;
	}
	public void setArg9(String arg9) {
		this.arg9 = arg9;
	}
	public String getArg10() {
		return arg10;
	}
	public void setArg10(String arg10) {
		this.arg10 = arg10;
	}
	public String getArg11() {
		return arg11;
	}
	public void setArg11(String arg11) {
		this.arg11 = arg11;
	}
	public String getArg12() {
		return arg12;
	}
	public void setArg12(String arg12) {
		this.arg12 = arg12;
	}
	public String getArg13() {
		return arg13;
	}
	public void setArg13(String arg13) {
		this.arg13 = arg13;
	}
	public String getArg14() {
		return arg14;
	}
	public void setArg14(String arg14) {
		this.arg14 = arg14;
	}
	public String getArg15() {
		return arg15;
	}
	public void setArg15(String arg15) {
		this.arg15 = arg15;
	}
	public String getArg16() {
		return arg16;
	}
	public void setArg16(String arg16) {
		this.arg16 = arg16;
	}
	public String getArg17() {
		return arg17;
	}
	public void setArg17(String arg17) {
		this.arg17 = arg17;
	}
	public String getArg18() {
		return arg18;
	}
	public void setArg18(String arg18) {
		this.arg18 = arg18;
	}
	public String getArg19() {
		return arg19;
	}
	public void setArg19(String arg19) {
		this.arg19 = arg19;
	}
	public String getArg20() {
		return arg20;
	}
	public void setArg20(String arg20) {
		this.arg20 = arg20;
	}
	public String getArg21() {
		return arg21;
	}
	public void setArg21(String arg21) {
		this.arg21 = arg21;
	}
	public String getArg22() {
		return arg22;
	}
	public void setArg22(String arg22) {
		this.arg22 = arg22;
	}
	public String getArg23() {
		return arg23;
	}
	public void setArg23(String arg23) {
		this.arg23 = arg23;
	}
	public String getArg24() {
		return arg24;
	}
	public void setArg24(String arg24) {
		this.arg24 = arg24;
	}
	public String getArg25() {
		return arg25;
	}
	public void setArg25(String arg25) {
		this.arg25 = arg25;
	}
	public String getArg26() {
		return arg26;
	}
	public void setArg26(String arg26) {
		this.arg26 = arg26;
	}
	public String getArg27() {
		return arg27;
	}
	public void setArg27(String arg27) {
		this.arg27 = arg27;
	}
	public String getArg28() {
		return arg28;
	}
	public void setArg28(String arg28) {
		this.arg28 = arg28;
	}
	public String getArg29() {
		return arg29;
	}
	public void setArg29(String arg29) {
		this.arg29 = arg29;
	}
	public String getArg30() {
		return arg30;
	}
	public void setArg30(String arg30) {
		this.arg30 = arg30;
	}
	public String getArg31() {
		return arg31;
	}
	public void setArg31(String arg31) {
		this.arg31 = arg31;
	}
	public String getArg32() {
		return arg32;
	}
	public void setArg32(String arg32) {
		this.arg32 = arg32;
	}
	public String getArg33() {
		return arg33;
	}
	public void setArg33(String arg33) {
		this.arg33 = arg33;
	}
	public String getArg34() {
		return arg34;
	}
	public void setArg34(String arg34) {
		this.arg34 = arg34;
	}
	public String getArg35() {
		return arg35;
	}
	public void setArg35(String arg35) {
		this.arg35 = arg35;
	}
	public String getArg36() {
		return arg36;
	}
	public void setArg36(String arg36) {
		this.arg36 = arg36;
	}
	public String getArg37() {
		return arg37;
	}
	public void setArg37(String arg37) {
		this.arg37 = arg37;
	}
	public String getArg38() {
		return arg38;
	}
	public void setArg38(String arg38) {
		this.arg38 = arg38;
	}
	public String getArg39() {
		return arg39;
	}
	public void setArg39(String arg39) {
		this.arg39 = arg39;
	}
	public String getArg40() {
		return arg40;
	}
	public void setArg40(String arg40) {
		this.arg40 = arg40;
	}
	public String getArg41() {
		return arg41;
	}
	public void setArg41(String arg41) {
		this.arg41 = arg41;
	}
	public String getArg42() {
		return arg42;
	}
	public void setArg42(String arg42) {
		this.arg42 = arg42;
	}
	public String getArg43() {
		return arg43;
	}
	public void setArg43(String arg43) {
		this.arg43 = arg43;
	}
	public String getArg44() {
		return arg44;
	}
	public void setArg44(String arg44) {
		this.arg44 = arg44;
	}
	public String getArg45() {
		return arg45;
	}
	public void setArg45(String arg45) {
		this.arg45 = arg45;
	}
	public String getArg46() {
		return arg46;
	}
	public void setArg46(String arg46) {
		this.arg46 = arg46;
	}
	public String getArg47() {
		return arg47;
	}
	public void setArg47(String arg47) {
		this.arg47 = arg47;
	}
	public String getArg48() {
		return arg48;
	}
	public void setArg48(String arg48) {
		this.arg48 = arg48;
	}
	public String getArg49() {
		return arg49;
	}
	public void setArg49(String arg49) {
		this.arg49 = arg49;
	}
	public String getArg1Res() {
		return arg1Res;
	}
	public void setArg1Res(String arg1Res) {
		this.arg1Res = arg1Res;
	}
	public String getArg2Res() {
		return arg2Res;
	}
	public void setArg2Res(String arg2Res) {
		this.arg2Res = arg2Res;
	}
	public String getArg3Res() {
		return arg3Res;
	}
	public void setArg3Res(String arg3Res) {
		this.arg3Res = arg3Res;
	}
	public String getArg4Res() {
		return arg4Res;
	}
	public void setArg4Res(String arg4Res) {
		this.arg4Res = arg4Res;
	}
	public String getArg5Res() {
		return arg5Res;
	}
	public void setArg5Res(String arg5Res) {
		this.arg5Res = arg5Res;
	}
	public String getArg6Res() {
		return arg6Res;
	}
	public void setArg6Res(String arg6Res) {
		this.arg6Res = arg6Res;
	}
	public String getArg7Res() {
		return arg7Res;
	}
	public void setArg7Res(String arg7Res) {
		this.arg7Res = arg7Res;
	}
	public String getArg8Res() {
		return arg8Res;
	}
	public void setArg8Res(String arg8Res) {
		this.arg8Res = arg8Res;
	}
	public String getArg9Res() {
		return arg9Res;
	}
	public void setArg9Res(String arg9Res) {
		this.arg9Res = arg9Res;
	}
	public String getArg10Res() {
		return arg10Res;
	}
	public void setArg10Res(String arg10Res) {
		this.arg10Res = arg10Res;
	}
	public String getArg11Res() {
		return arg11Res;
	}
	public void setArg11Res(String arg11Res) {
		this.arg11Res = arg11Res;
	}
	public String getArg12Res() {
		return arg12Res;
	}
	public void setArg12Res(String arg12Res) {
		this.arg12Res = arg12Res;
	}
	public String getArg13Res() {
		return arg13Res;
	}
	public void setArg13Res(String arg13Res) {
		this.arg13Res = arg13Res;
	}
	public String getArg14Res() {
		return arg14Res;
	}
	public void setArg14Res(String arg14Res) {
		this.arg14Res = arg14Res;
	}
	public String getArg15Res() {
		return arg15Res;
	}
	public void setArg15Res(String arg15Res) {
		this.arg15Res = arg15Res;
	}
	public String getArg16Res() {
		return arg16Res;
	}
	public void setArg16Res(String arg16Res) {
		this.arg16Res = arg16Res;
	}
	public String getArg17Res() {
		return arg17Res;
	}
	public void setArg17Res(String arg17Res) {
		this.arg17Res = arg17Res;
	}
	public String getArg18Res() {
		return arg18Res;
	}
	public void setArg18Res(String arg18Res) {
		this.arg18Res = arg18Res;
	}
	public String getArg19Res() {
		return arg19Res;
	}
	public void setArg19Res(String arg19Res) {
		this.arg19Res = arg19Res;
	}
	public String getArg20Res() {
		return arg20Res;
	}
	public void setArg20Res(String arg20Res) {
		this.arg20Res = arg20Res;
	}
	public String getArg21Res() {
		return arg21Res;
	}
	public void setArg21Res(String arg21Res) {
		this.arg21Res = arg21Res;
	}
	public String getArg22Res() {
		return arg22Res;
	}
	public void setArg22Res(String arg22Res) {
		this.arg22Res = arg22Res;
	}
	public String getArg23Res() {
		return arg23Res;
	}
	public void setArg23Res(String arg23Res) {
		this.arg23Res = arg23Res;
	}
	public String getArg24Res() {
		return arg24Res;
	}
	public void setArg24Res(String arg24Res) {
		this.arg24Res = arg24Res;
	}
	public String getArg25Res() {
		return arg25Res;
	}
	public void setArg25Res(String arg25Res) {
		this.arg25Res = arg25Res;
	}
	public String getArg26Res() {
		return arg26Res;
	}
	public void setArg26Res(String arg26Res) {
		this.arg26Res = arg26Res;
	}
	public String getArg27Res() {
		return arg27Res;
	}
	public void setArg27Res(String arg27Res) {
		this.arg27Res = arg27Res;
	}
	public String getArg28Res() {
		return arg28Res;
	}
	public void setArg28Res(String arg28Res) {
		this.arg28Res = arg28Res;
	}
	public String getArg29Res() {
		return arg29Res;
	}
	public void setArg29Res(String arg29Res) {
		this.arg29Res = arg29Res;
	}
	public String getArg30Res() {
		return arg30Res;
	}
	public void setArg30Res(String arg30Res) {
		this.arg30Res = arg30Res;
	}
	public String getArg31Res() {
		return arg31Res;
	}
	public void setArg31Res(String arg31Res) {
		this.arg31Res = arg31Res;
	}
	public String getArg32Res() {
		return arg32Res;
	}
	public void setArg32Res(String arg32Res) {
		this.arg32Res = arg32Res;
	}
	public String getArg33Res() {
		return arg33Res;
	}
	public void setArg33Res(String arg33Res) {
		this.arg33Res = arg33Res;
	}
	public String getArg34Res() {
		return arg34Res;
	}
	public void setArg34Res(String arg34Res) {
		this.arg34Res = arg34Res;
	}
	public String getArg35Res() {
		return arg35Res;
	}
	public void setArg35Res(String arg35Res) {
		this.arg35Res = arg35Res;
	}
	public String getArg36Res() {
		return arg36Res;
	}
	public void setArg36Res(String arg36Res) {
		this.arg36Res = arg36Res;
	}
	public String getArg37Res() {
		return arg37Res;
	}
	public void setArg37Res(String arg37Res) {
		this.arg37Res = arg37Res;
	}
	public String getArg38Res() {
		return arg38Res;
	}
	public void setArg38Res(String arg38Res) {
		this.arg38Res = arg38Res;
	}
	public String getArg39Res() {
		return arg39Res;
	}
	public void setArg39Res(String arg39Res) {
		this.arg39Res = arg39Res;
	}
	public String getArg40Res() {
		return arg40Res;
	}
	public void setArg40Res(String arg40Res) {
		this.arg40Res = arg40Res;
	}
	public String getArg41Res() {
		return arg41Res;
	}
	public void setArg41Res(String arg41Res) {
		this.arg41Res = arg41Res;
	}
	public String getArg42Res() {
		return arg42Res;
	}
	public void setArg42Res(String arg42Res) {
		this.arg42Res = arg42Res;
	}
	public String getArg43Res() {
		return arg43Res;
	}
	public void setArg43Res(String arg43Res) {
		this.arg43Res = arg43Res;
	}
	public String getArg44Res() {
		return arg44Res;
	}
	public void setArg44Res(String arg44Res) {
		this.arg44Res = arg44Res;
	}
	public String getArg45Res() {
		return arg45Res;
	}
	public void setArg45Res(String arg45Res) {
		this.arg45Res = arg45Res;
	}
	public String getArg46Res() {
		return arg46Res;
	}
	public void setArg46Res(String arg46Res) {
		this.arg46Res = arg46Res;
	}
	public String getArg47Res() {
		return arg47Res;
	}
	public void setArg47Res(String arg47Res) {
		this.arg47Res = arg47Res;
	}
	public String getArg48Res() {
		return arg48Res;
	}
	public void setArg48Res(String arg48Res) {
		this.arg48Res = arg48Res;
	}
	public String getArg49Res() {
		return arg49Res;
	}
	public void setArg49Res(String arg49Res) {
		this.arg49Res = arg49Res;
	}
}
