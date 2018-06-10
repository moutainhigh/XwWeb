package app.creditapp.cif.entity;
import app.base.BaseDomain;
/**
* Title: CifPersCareer.java
* Description:
* @version：1.0
**/
public class CifPersCareer extends BaseDomain {
	private String recId;//记录ID
	private String cifName;//客户名称
	private String cifNo;//客户号码
	private String workType;//职业
	private String corpName;//工作单位名称
	private String corpWay;//工作单位所属行业
	private String corpCode;//工作单位邮编
	private String corpAddr;//工作单位地址
	private String duty;//职务
	private String title;//职称
	private String workYear;//工作起始年份
	private String brNo;//合作机构号码
	private String brName;//合作机构名称
	private String recSts;//记录状态[01正常 02删除]
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String opNo;//操作员
	private String opName;//操作员名称
	private String workSts; //工作状态
	
	
	public String getWorkSts() {
		return workSts;
	}
	public void setWorkSts(String workSts) {
		this.workSts = workSts;
	}
	/**
	 * @return 记录ID
	 */
	public String getRecId() {
	 	return recId;
	}
	/**
	 * @设置 记录ID
	 * @param recId
	 */
	public void setRecId(String recId) {
	 	this.recId = recId;
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
	 * @return 记录状态[01正常 02删除]
	 */
	public String getRecSts() {
	 	return recSts;
	}
	/**
	 * @设置 记录状态[01正常 02删除]
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
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
}