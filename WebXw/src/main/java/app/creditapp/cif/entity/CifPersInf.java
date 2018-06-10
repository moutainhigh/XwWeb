package app.creditapp.cif.entity;
import app.base.BaseDomain;
/**
* Title: CifPers.java
* Description:
* @version：1.0
**/
public class CifPersInf extends BaseDomain {
	private String gradeDate;//评级日期
	private String email;//EMAIL
	private String idNo;//证件号码
	private String idType;//证件类型
	private String cifName;//客户名称
	private String cifNo;//客户号码
	private String cifType;//客户类型
	private String sex;//性别
	private String birthDay;//出生日期
	private String marriage;//婚姻状况
	private String edu;//最高学历
	private String degree;//最高学位
	private String commTel;//联系电话
	private String phoneNo;//手机号码
	private String commCode;//通讯邮编
	private String commAddr;//通讯地址
	private String cifArea;//户籍归属
	private String resTel;//住宅电话
	private String resCode;//住宅邮编
	private String resAddr;//住宅地址
	private String resSts;//居住状况
	private Double income;//月收入（元）
	private String mateName;//配偶名称
	private String mateIdtype;//配偶证件类型
	private String mateIdno;//配偶证件号码
	private String mateWork;//配偶工作单位
	private String mateTel;//配偶联系电话
	private String grade;//信用等级
	private String brNo;//合作机构号码
	private String recSts;//记录状态[01正常02删除]
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String opNo;//信托管户经理号
	private String brName;//机构名称
	
	private String areaName;//户籍归属

	private String loginid;//登录人员
	
	/**
	 * @return 评级日期
	 */
	public String getGradeDate() {
	 	return gradeDate;
	}
	/**
	 * @设置 评级日期
	 * @param gradeDate
	 */
	public void setGradeDate(String gradeDate) {
	 	this.gradeDate = gradeDate;
	}
	/**
	 * @return EMAIL
	 */
	public String getEmail() {
	 	return email;
	}
	/**
	 * @设置 EMAIL
	 * @param email
	 */
	public void setEmail(String email) {
	 	this.email = email;
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
	 * @return 信用等级
	 */
	public String getGrade() {
	 	return grade;
	}
	/**
	 * @设置 信用等级
	 * @param grade
	 */
	public void setGrade(String grade) {
	 	this.grade = grade;
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
	 * @return 信托管户经理号
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 信托管户经理号
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	
}