package app.creditapp.corp.entity;
import app.base.BaseDomain;
/**
* Title: CorpCont.java
* Description:
* @version：1.0
**/
public class CorpCont extends BaseDomain {
	private String contIdno;//联系人证件号码
	private String contIdtyoe;//联系人证件类型
	private String contType;//联系人类型[01-法定代表人02-董事长03-总经理04-财务负责人05-技术负责人06-销售负责人07-其他]
	private String contName;//联系人名称
	private String contNo;//联系人编号
	private String brName;//合作机构名称
	private String brNo;//合作机构编号
	private String admTel;//联系电话
	private String edu;//学历[10-博士研究生15-硕士研究生20-大学本科30-大学专科和专科学校40-中等专业学校或中等技术学校50-技术学校60-高中70-初中80-小学90-文盲或半文盲99-未知]
	private String admAddr;//户籍地址
	private String country;//国别
	private String birthday;//出生日期
	private String duty;//职务[1-高级领导（行政级别局级及局级以上领导或大公司高级管理人员）2-中级领导（行政级别局级以下领导或大公司中级管理人员）3-一般员工4-其他9-未知]
	private String wkTitle;//职称[0-无1-高级2-中级3-初级9-未知]
	private String cifFax;//传真
	private String comAddr;//单位地址
	private String resume;//简历
	private String filer;//备注
	private String deptNo;//登记部门
	private String opNo;//操作员
	private String opName;//操作员名称
	private String txDate;//登记日期
	private String upDate;//修改日期
	private String upOpno;//修改人员
	private String upOpname;//修改人员名称
	private String commMail;//修改人员名称

	/**
	 * @return 联系人证件号码
	 */
	public String getContIdno() {
	 	return contIdno;
	}
	/**
	 * @设置 联系人证件号码
	 * @param contIdno
	 */
	public void setContIdno(String contIdno) {
	 	this.contIdno = contIdno;
	}
	/**
	 * @return 联系人证件类型
	 */
	public String getContIdtyoe() {
	 	return contIdtyoe;
	}
	/**
	 * @设置 联系人证件类型
	 * @param contIdtyoe
	 */
	public void setContIdtyoe(String contIdtyoe) {
	 	this.contIdtyoe = contIdtyoe;
	}
	/**
	 * @return 联系人类型[01-法定代表人02-董事长03-总经理04-财务负责人05-技术负责人06-销售负责人07-其他]
	 */
	public String getContType() {
	 	return contType;
	}
	/**
	 * @设置 联系人类型[01-法定代表人02-董事长03-总经理04-财务负责人05-技术负责人06-销售负责人07-其他]
	 * @param contType
	 */
	public void setContType(String contType) {
	 	this.contType = contType;
	}
	/**
	 * @return 联系人名称
	 */
	public String getContName() {
	 	return contName;
	}
	/**
	 * @设置 联系人名称
	 * @param contName
	 */
	public void setContName(String contName) {
	 	this.contName = contName;
	}
	/**
	 * @return 联系人编号
	 */
	public String getContNo() {
	 	return contNo;
	}
	/**
	 * @设置 联系人编号
	 * @param contNo
	 */
	public void setContNo(String contNo) {
	 	this.contNo = contNo;
	}
	/**
	 * @return 合作机构名称
	 */
	public String getBrName() {
	 	return brName;
	}
	/**
	 * @设置 合作机构名称
	 * @param brName
	 */
	public void setBrName(String brName) {
	 	this.brName = brName;
	}
	/**
	 * @return 合作机构编号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 联系电话
	 */
	public String getAdmTel() {
	 	return admTel;
	}
	/**
	 * @设置 联系电话
	 * @param admTel
	 */
	public void setAdmTel(String admTel) {
	 	this.admTel = admTel;
	}
	/**
	 * @return 学历[10-博士研究生15-硕士研究生20-大学本科30-大学专科和专科学校40-中等专业学校或中等技术学校50-技术学校60-高中70-初中80-小学90-文盲或半文盲99-未知]
	 */
	public String getEdu() {
	 	return edu;
	}
	/**
	 * @设置 学历[10-博士研究生15-硕士研究生20-大学本科30-大学专科和专科学校40-中等专业学校或中等技术学校50-技术学校60-高中70-初中80-小学90-文盲或半文盲99-未知]
	 * @param edu
	 */
	public void setEdu(String edu) {
	 	this.edu = edu;
	}
	/**
	 * @return 户籍地址
	 */
	public String getAdmAddr() {
	 	return admAddr;
	}
	/**
	 * @设置 户籍地址
	 * @param admAddr
	 */
	public void setAdmAddr(String admAddr) {
	 	this.admAddr = admAddr;
	}
	/**
	 * @return 国别
	 */
	public String getCountry() {
	 	return country;
	}
	/**
	 * @设置 国别
	 * @param country
	 */
	public void setCountry(String country) {
	 	this.country = country;
	}
	/**
	 * @return 出生日期
	 */
	public String getBirthday() {
	 	return birthday;
	}
	/**
	 * @设置 出生日期
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
	 	this.birthday = birthday;
	}
	/**
	 * @return 职务[1-高级领导（行政级别局级及局级以上领导或大公司高级管理人员）2-中级领导（行政级别局级以下领导或大公司中级管理人员）3-一般员工4-其他9-未知]
	 */
	public String getDuty() {
	 	return duty;
	}
	/**
	 * @设置 职务[1-高级领导（行政级别局级及局级以上领导或大公司高级管理人员）2-中级领导（行政级别局级以下领导或大公司中级管理人员）3-一般员工4-其他9-未知]
	 * @param duty
	 */
	public void setDuty(String duty) {
	 	this.duty = duty;
	}
	/**
	 * @return 职称[0-无1-高级2-中级3-初级9-未知]
	 */
	public String getWkTitle() {
	 	return wkTitle;
	}
	/**
	 * @设置 职称[0-无1-高级2-中级3-初级9-未知]
	 * @param wkTitle
	 */
	public void setWkTitle(String wkTitle) {
	 	this.wkTitle = wkTitle;
	}
	/**
	 * @return 传真
	 */
	public String getCifFax() {
	 	return cifFax;
	}
	/**
	 * @设置 传真
	 * @param cifFax
	 */
	public void setCifFax(String cifFax) {
	 	this.cifFax = cifFax;
	}
	/**
	 * @return 单位地址
	 */
	public String getComAddr() {
	 	return comAddr;
	}
	/**
	 * @设置 单位地址
	 * @param comAddr
	 */
	public void setComAddr(String comAddr) {
	 	this.comAddr = comAddr;
	}
	/**
	 * @return 简历
	 */
	public String getResume() {
	 	return resume;
	}
	/**
	 * @设置 简历
	 * @param resume
	 */
	public void setResume(String resume) {
	 	this.resume = resume;
	}
	/**
	 * @return 备注
	 */
	public String getFiler() {
	 	return filer;
	}
	/**
	 * @设置 备注
	 * @param filer
	 */
	public void setFiler(String filer) {
	 	this.filer = filer;
	}
	/**
	 * @return 登记部门
	 */
	public String getDeptNo() {
	 	return deptNo;
	}
	/**
	 * @设置 登记部门
	 * @param deptNo
	 */
	public void setDeptNo(String deptNo) {
	 	this.deptNo = deptNo;
	}
	/**
	 * @return 操作员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 操作员
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
	 * @return 修改日期
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 修改日期
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
	/**
	 * @return 修改人员
	 */
	public String getUpOpno() {
	 	return upOpno;
	}
	/**
	 * @设置 修改人员
	 * @param upOpno
	 */
	public void setUpOpno(String upOpno) {
	 	this.upOpno = upOpno;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getUpOpname() {
		return upOpname;
	}
	public void setUpOpname(String upOpname) {
		this.upOpname = upOpname;
	}
	public String getCommMail() {
		return commMail;
	}
	public void setCommMail(String commMail) {
		this.commMail = commMail;
	}
}