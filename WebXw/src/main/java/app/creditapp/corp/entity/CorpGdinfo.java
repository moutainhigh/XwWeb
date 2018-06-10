package app.creditapp.corp.entity;
import app.base.BaseDomain;
/**
* Title: CorpGdinfo.java
* Description:
* @version：1.0
**/
public class CorpGdinfo extends BaseDomain {
	private Double invAmt;//入股总金额
	private String curNo;//币种
	private String gdName;//股东名称
	private String gdId;//股东信息ID
	private String brName;//合作机构名称
	private String brNo;//合作机构编号
	private String gdType;//股东类型[01法人02自然人]
	private String stkcNo;//股权证编号
	private String gdIdType;//股东证件类型
	private String gdIdNo;//证件号（身份证、组织机构代码或者统一社会信用代码证）
	private String invDate;//入股日期
	private Double cashAmt;//现金金额
	private Double objAmt;//实物金额
	private Double invRate;//股东持股比例
	private String credNo;//贷款卡编码
	private String license;//营业执照号
	private String ctlFlag;//实际控制人/第一大股东标志
	private String phone;//联系电话
	private String address;//通讯地址
	private String filer;//备注
	private String deptNo;//登记部门
	private String opNo;//操作员
	private String opName;//操作员名称
	private String txDate;//登记日期
	private String upDate;//修改日期
	private String upOpno;//修改人员
	private String upOpname;//修改人员名称

	/**
	 * @return 入股总金额
	 */
	public Double getInvAmt() {
	 	return invAmt;
	}
	/**
	 * @设置 入股总金额
	 * @param invAmt
	 */
	public void setInvAmt(Double invAmt) {
	 	this.invAmt = invAmt;
	}
	/**
	 * @return 币种
	 */
	public String getCurNo() {
	 	return curNo;
	}
	/**
	 * @设置 币种
	 * @param curNo
	 */
	public void setCurNo(String curNo) {
	 	this.curNo = curNo;
	}
	/**
	 * @return 股东名称
	 */
	public String getGdName() {
	 	return gdName;
	}
	/**
	 * @设置 股东名称
	 * @param gdName
	 */
	public void setGdName(String gdName) {
	 	this.gdName = gdName;
	}
	/**
	 * @return 股东信息ID
	 */
	public String getGdId() {
	 	return gdId;
	}
	/**
	 * @设置 股东信息ID
	 * @param gdId
	 */
	public void setGdId(String gdId) {
	 	this.gdId = gdId;
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
	 * @return 股东类型[01法人02自然人]
	 */
	public String getGdType() {
	 	return gdType;
	}
	/**
	 * @设置 股东类型[01法人02自然人]
	 * @param gdType
	 */
	public void setGdType(String gdType) {
	 	this.gdType = gdType;
	}
	/**
	 * @return 股权证编号
	 */
	public String getStkcNo() {
	 	return stkcNo;
	}
	/**
	 * @设置 股权证编号
	 * @param stkcNo
	 */
	public void setStkcNo(String stkcNo) {
	 	this.stkcNo = stkcNo;
	}
	/**
	 * @return 股东证件类型
	 */
	public String getGdIdType() {
	 	return gdIdType;
	}
	/**
	 * @设置 股东证件类型
	 * @param gdIdType
	 */
	public void setGdIdType(String gdIdType) {
	 	this.gdIdType = gdIdType;
	}
	/**
	 * @return 证件号（身份证、组织机构代码或者统一社会信用代码证）
	 */
	public String getGdIdNo() {
	 	return gdIdNo;
	}
	/**
	 * @设置 证件号（身份证、组织机构代码或者统一社会信用代码证）
	 * @param gdIdNo
	 */
	public void setGdIdNo(String gdIdNo) {
	 	this.gdIdNo = gdIdNo;
	}
	/**
	 * @return 入股日期
	 */
	public String getInvDate() {
	 	return invDate;
	}
	/**
	 * @设置 入股日期
	 * @param invDate
	 */
	public void setInvDate(String invDate) {
	 	this.invDate = invDate;
	}
	/**
	 * @return 现金金额
	 */
	public Double getCashAmt() {
	 	return cashAmt;
	}
	/**
	 * @设置 现金金额
	 * @param cashAmt
	 */
	public void setCashAmt(Double cashAmt) {
	 	this.cashAmt = cashAmt;
	}
	/**
	 * @return 实物金额
	 */
	public Double getObjAmt() {
	 	return objAmt;
	}
	/**
	 * @设置 实物金额
	 * @param objAmt
	 */
	public void setObjAmt(Double objAmt) {
	 	this.objAmt = objAmt;
	}
	/**
	 * @return 股东持股比例
	 */
	public Double getInvRate() {
	 	return invRate;
	}
	/**
	 * @设置 股东持股比例
	 * @param invRate
	 */
	public void setInvRate(Double invRate) {
	 	this.invRate = invRate;
	}
	/**
	 * @return 贷款卡编码
	 */
	public String getCredNo() {
	 	return credNo;
	}
	/**
	 * @设置 贷款卡编码
	 * @param credNo
	 */
	public void setCredNo(String credNo) {
	 	this.credNo = credNo;
	}
	/**
	 * @return 营业执照号
	 */
	public String getLicense() {
	 	return license;
	}
	/**
	 * @设置 营业执照号
	 * @param license
	 */
	public void setLicense(String license) {
	 	this.license = license;
	}
	/**
	 * @return 实际控制人/第一大股东标志
	 */
	public String getCtlFlag() {
	 	return ctlFlag;
	}
	/**
	 * @设置 实际控制人/第一大股东标志
	 * @param ctlFlag
	 */
	public void setCtlFlag(String ctlFlag) {
	 	this.ctlFlag = ctlFlag;
	}
	/**
	 * @return 联系电话
	 */
	public String getPhone() {
	 	return phone;
	}
	/**
	 * @设置 联系电话
	 * @param phone
	 */
	public void setPhone(String phone) {
	 	this.phone = phone;
	}
	/**
	 * @return 通讯地址
	 */
	public String getAddress() {
	 	return address;
	}
	/**
	 * @设置 通讯地址
	 * @param address
	 */
	public void setAddress(String address) {
	 	this.address = address;
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
}