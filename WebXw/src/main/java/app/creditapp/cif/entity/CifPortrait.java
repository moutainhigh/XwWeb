package app.creditapp.cif.entity;
import app.base.BaseDomain;
/**
* Title: CifPortrait.java
* Description:
* @version：1.0
**/
public class CifPortrait extends BaseDomain {
	private String cifNo;//客户号码
	private String cifName;//客户名称
	private String idNo;//证件号码
	private String idType;//证件类型
	private String sex;//性别
	private String birthDay;//出生日期
	private String marriage;//婚姻状况
	private String edu;//最高学历
	private Double income;//月收入（元）
	private String grade;//信用等级、得分
	private String creditDesc;//信用状况
	private String workType;//职业
	private String ifCar;//是否有车
	private String ifRoom;//是否有房
	private String ifDc;//是否代偿
	private String ifHg;//是否回购
	private String prtDesc;//画像描述-结论
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String cifGroup;//客户群体
	
	private Integer repdNum;//逾期次数
	private Double gradePersent;//客户评分百分比
	private Integer cifBlacknum;//黑名单
	private String cifType;//客户类型
	private String lnDue;//借据号
	private String resTel;//住宅电话
	private String resAddr;//住宅地址
	private int version;//视图版本
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
	 * @return 信用等级、得分
	 */
	public String getGrade() {
	 	return grade;
	}
	/**
	 * @设置 信用等级、得分
	 * @param grade
	 */
	public void setGrade(String grade) {
	 	this.grade = grade;
	}
	/**
	 * @return 信用状况
	 */
	public String getCreditDesc() {
	 	return creditDesc;
	}
	/**
	 * @设置 信用状况
	 * @param creditDesc
	 */
	public void setCreditDesc(String creditDesc) {
	 	this.creditDesc = creditDesc;
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
	 * @return 是否代偿
	 */
	public String getIfDc() {
	 	return ifDc;
	}
	/**
	 * @设置 是否代偿
	 * @param ifDc
	 */
	public void setIfDc(String ifDc) {
	 	this.ifDc = ifDc;
	}
	/**
	 * @return 是否回购
	 */
	public String getIfHg() {
	 	return ifHg;
	}
	/**
	 * @设置 是否回购
	 * @param ifHg
	 */
	public void setIfHg(String ifHg) {
	 	this.ifHg = ifHg;
	}
	/**
	 * @return 画像描述-结论
	 */
	public String getPrtDesc() {
	 	return prtDesc;
	}
	/**
	 * @设置 画像描述-结论
	 * @param prtDesc
	 */
	public void setPrtDesc(String prtDesc) {
	 	this.prtDesc = prtDesc;
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
	public String getCifGroup() {
		return cifGroup;
	}
	public void setCifGroup(String cifGroup) {
		this.cifGroup = cifGroup;
	}
	public Integer getRepdNum() {
		return repdNum;
	}
	public void setRepdNum(Integer repdNum) {
		this.repdNum = repdNum;
	}
	public Double getGradePersent() {
		return gradePersent;
	}
	public void setGradePersent(Double gradePersent) {
		this.gradePersent = gradePersent;
	}
	public Integer getCifBlacknum() {
		return cifBlacknum;
	}
	public void setCifBlacknum(Integer cifBlacknum) {
		this.cifBlacknum = cifBlacknum;
	}
	public String getCifType() {
		return cifType;
	}
	public void setCifType(String cifType) {
		this.cifType = cifType;
	}
	public String getLnDue() {
		return lnDue;
	}
	public void setLnDue(String lnDue) {
		this.lnDue = lnDue;
	}
	public String getResTel() {
		return resTel;
	}
	public void setResTel(String resTel) {
		this.resTel = resTel;
	}
	public String getResAddr() {
		return resAddr;
	}
	public void setResAddr(String resAddr) {
		this.resAddr = resAddr;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}

}