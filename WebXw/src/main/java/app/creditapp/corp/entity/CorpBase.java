package app.creditapp.corp.entity;
import app.base.BaseDomain;
/**
* Title: CorpBase.java
* Description:
* @version：1.0
**/
public class CorpBase extends BaseDomain {
	private String orgTerm;//组织机构代码证有效期限
	private String brNo;//合作机构编号
	private String brName;//合作机构名称
	private String brType;//"合作机构类型[01小额贷款公司02担保公司03典当公司04消费金融公司]"
	private String setupDate;//成立时间
	private String areaName;//行政区划
	private String cifArea;//行政区划代码
	private String license;//营业执照号
	private String licBegDate;//营业执照登记注册时间
	private String licEndDate;//营业执照到期时间
	private String regAddr;//营业执照注册地址
	private String regArea;//企业注册地址行政区划
	private String locTaxNo;//国税登记证号码
	private String cenTaxNo;//地税登记证号码
	private String orgNo;//组织机构代码
	private String screditNo;//社会信用代码证
	private String cardNo;//贷款证号[贷款卡编码]
	private String ifLnuse;//是否贷款运用方[0否1是]
	private Double totalAssets;//企业总资产
	private Double totalDebts;//企业总负债
	private Double regFund;//注册资本
	private Double factFund;//实收资本
	private String corpType;//企业类型[01-大型企业02-中型企业03-小型企业04-微小型企业05-事业单位06-全部类型]
	private String runMan;//经营者情况
	private String mainBus;//主营业务情况
	private String comAddr;//营业地址
	private String postCode;//邮政编码
	private String partner;//控制股东/实际控制人
	private String outGrade;//合作机构信用评级[外部评级]
	private String inGrade;//合作机构分类[信托]
	private String brSts;//启用停用标志[00-停用 01-启用]
	private String filler;//备注
	private String deptNo;//登记部门
	private String opNo;//操作员号
	private String opName;//操作员
	private String txDate;//登记日期
	private String upDate;//修改日期
	private String upOpno;//修改人员
	private String upOpname;//修改人员名称
	private String brAccType;//核算类型[A类、B类]
	private String sts;//核算类型[A类、B类]
	private String stateSts;//对公结算标志[01-个人 02-对公]
	private String loginid;//登录人员

	public String getBrAccType() {
		return brAccType;
	}
	public void setBrAccType(String brAccType) {
		this.brAccType = brAccType;
	}
	/**
	 * @return 组织机构代码证有效期限
	 */
	public String getBrNo() {
	 	return brNo;
	}
	public String getOrgTerm() {
		return orgTerm;
	}

	/**
	 * @return 合作机构编号
	 */
	public void setOrgTerm(String orgTerm) {
		this.orgTerm = orgTerm;
	}
	/**
	 * @设置 合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
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
	 * @return "合作机构类型[01小额贷款公司02担保公司03典当公司04消费金融公司]"
	 */
	public String getBrType() {
	 	return brType;
	}
	/**
	 * @设置 "合作机构类型[01小额贷款公司02担保公司03典当公司04消费金融公司]"
	 * @param brType
	 */
	public void setBrType(String brType) {
	 	this.brType = brType;
	}
	/**
	 * @return 成立时间
	 */
	public String getSetupDate() {
	 	return setupDate;
	}
	/**
	 * @设置 成立时间
	 * @param setupDate
	 */
	public void setSetupDate(String setupDate) {
	 	this.setupDate = setupDate;
	}
	/**
	 * @return 行政区划
	 */
	public String getAreaName() {
	 	return areaName;
	}
	/**
	 * @设置 行政区划
	 * @param areaName
	 */
	public void setAreaName(String areaName) {
	 	this.areaName = areaName;
	}
	/**
	 * @return 行政区划代码
	 */
	public String getCifArea() {
	 	return cifArea;
	}
	/**
	 * @设置 行政区划代码
	 * @param cifArea
	 */
	public void setCifArea(String cifArea) {
	 	this.cifArea = cifArea;
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
	 * @return 营业执照登记注册时间
	 */
	public String getLicBegDate() {
	 	return licBegDate;
	}
	/**
	 * @设置 营业执照登记注册时间
	 * @param licBegDate
	 */
	public void setLicBegDate(String licBegDate) {
	 	this.licBegDate = licBegDate;
	}
	/**
	 * @return 营业执照到期时间
	 */
	public String getLicEndDate() {
	 	return licEndDate;
	}
	/**
	 * @设置 营业执照到期时间
	 * @param licEndDate
	 */
	public void setLicEndDate(String licEndDate) {
	 	this.licEndDate = licEndDate;
	}
	/**
	 * @return 营业执照注册地址
	 */
	public String getRegAddr() {
	 	return regAddr;
	}
	/**
	 * @设置 营业执照注册地址
	 * @param regAddr
	 */
	public void setRegAddr(String regAddr) {
	 	this.regAddr = regAddr;
	}
	/**
	 * @return 企业注册地址行政区划
	 */
	public String getRegArea() {
	 	return regArea;
	}
	/**
	 * @设置 企业注册地址行政区划
	 * @param regArea
	 */
	public void setRegArea(String regArea) {
	 	this.regArea = regArea;
	}
	/**
	 * @return 国税登记证号码
	 */
	public String getLocTaxNo() {
	 	return locTaxNo;
	}
	/**
	 * @设置 国税登记证号码
	 * @param locTaxNo
	 */
	public void setLocTaxNo(String locTaxNo) {
	 	this.locTaxNo = locTaxNo;
	}
	/**
	 * @return 地税登记证号码
	 */
	public String getCenTaxNo() {
	 	return cenTaxNo;
	}
	/**
	 * @设置 地税登记证号码
	 * @param cenTaxNo
	 */
	public void setCenTaxNo(String cenTaxNo) {
	 	this.cenTaxNo = cenTaxNo;
	}
	/**
	 * @return 组织机构代码
	 */
	public String getOrgNo() {
	 	return orgNo;
	}
	/**
	 * @设置 组织机构代码
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
	 	this.orgNo = orgNo;
	}
	/**
	 * @return 社会信用代码证
	 */
	public String getScreditNo() {
	 	return screditNo;
	}
	/**
	 * @设置 社会信用代码证
	 * @param screditNo
	 */
	public void setScreditNo(String screditNo) {
	 	this.screditNo = screditNo;
	}
	/**
	 * @return 贷款证号[贷款卡编码]
	 */
	public String getCardNo() {
	 	return cardNo;
	}
	/**
	 * @设置 贷款证号[贷款卡编码]
	 * @param cardNo
	 */
	public void setCardNo(String cardNo) {
	 	this.cardNo = cardNo;
	}
	/**
	 * @return 是否贷款运用方[0否1是]
	 */
	public String getIfLnuse() {
	 	return ifLnuse;
	}
	/**
	 * @设置 是否贷款运用方[0否1是]
	 * @param sfdkyyf
	 */
	public void setIfLnuse(String ifLnuse) {
	 	this.ifLnuse = ifLnuse;
	}
	/**
	 * @return 企业总资产
	 */
	public Double getTotalAssets() {
	 	return totalAssets;
	}
	/**
	 * @设置 企业总资产
	 * @param totalAssets
	 */
	public void setTotalAssets(Double totalAssets) {
	 	this.totalAssets = totalAssets;
	}
	/**
	 * @return 企业总负债
	 */
	public Double getTotalDebts() {
	 	return totalDebts;
	}
	/**
	 * @设置 企业总负债
	 * @param totalDebts
	 */
	public void setTotalDebts(Double totalDebts) {
	 	this.totalDebts = totalDebts;
	}
	/**
	 * @return 注册资本
	 */
	public Double getRegFund() {
	 	return regFund;
	}
	/**
	 * @设置 注册资本
	 * @param regFund
	 */
	public void setRegFund(Double regFund) {
	 	this.regFund = regFund;
	}
	/**
	 * @return 实收资本
	 */
	public Double getFactFund() {
	 	return factFund;
	}
	/**
	 * @设置 实收资本
	 * @param factFund
	 */
	public void setFactFund(Double factFund) {
	 	this.factFund = factFund;
	}
	/**
	 * @return 企业类型[01-大型企业02-中型企业03-小型企业04-微小型企业05-事业单位06-全部类型]
	 */
	public String getCorpType() {
	 	return corpType;
	}
	/**
	 * @设置 企业类型[01-大型企业02-中型企业03-小型企业04-微小型企业05-事业单位06-全部类型]
	 * @param corpType
	 */
	public void setCorpType(String corpType) {
	 	this.corpType = corpType;
	}
	/**
	 * @return 经营者情况
	 */
	public String getRunMan() {
	 	return runMan;
	}
	/**
	 * @设置 经营者情况
	 * @param runMan
	 */
	public void setRunMan(String runMan) {
	 	this.runMan = runMan;
	}
	/**
	 * @return 主营业务情况
	 */
	public String getMainBus() {
	 	return mainBus;
	}
	/**
	 * @设置 主营业务情况
	 * @param mainBus
	 */
	public void setMainBus(String mainBus) {
	 	this.mainBus = mainBus;
	}
	/**
	 * @return 营业地址
	 */
	public String getComAddr() {
	 	return comAddr;
	}
	/**
	 * @设置 营业地址
	 * @param comAddr
	 */
	public void setComAddr(String comAddr) {
	 	this.comAddr = comAddr;
	}
	/**
	 * @return 邮政编码
	 */
	public String getPostCode() {
	 	return postCode;
	}
	/**
	 * @设置 邮政编码
	 * @param postCode
	 */
	public void setPostCode(String postCode) {
	 	this.postCode = postCode;
	}
	/**
	 * @return 控制股东/实际控制人
	 */
	public String getPartner() {
	 	return partner;
	}
	/**
	 * @设置 控制股东/实际控制人
	 * @param partner
	 */
	public void setPartner(String partner) {
	 	this.partner = partner;
	}
	/**
	 * @return 合作机构信用评级[外部评级]
	 */
	public String getOutGrade() {
	 	return outGrade;
	}
	/**
	 * @设置 合作机构信用评级[外部评级]
	 * @param outGrade
	 */
	public void setOutGrade(String outGrade) {
	 	this.outGrade = outGrade;
	}
	/**
	 * @return 合作机构分类[信托]
	 */
	public String getInGrade() {
	 	return inGrade;
	}
	/**
	 * @设置 合作机构分类[信托]
	 * @param inGrade
	 */
	public void setInGrade(String inGrade) {
	 	this.inGrade = inGrade;
	}
	/**
	 * @return 启用停用标志[00-停用 01-启用]
	 */
	public String getBrSts() {
	 	return brSts;
	}
	/**
	 * @设置 启用停用标志[00-停用 01-启用]
	 * @param brSts
	 */
	public void setBrSts(String brSts) {
	 	this.brSts = brSts;
	}
	/**
	 * @return 备注
	 */
	public String getFiller() {
	 	return filler;
	}
	/**
	 * @设置 备注
	 * @param filler
	 */
	public void setFiller(String filler) {
	 	this.filler = filler;
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
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	/**
	 * @return the stateSts
	 */
	public String getStateSts() {
		return stateSts;
	}
	/**
	 * @param stateSts the stateSts to set
	 */
	public void setStateSts(String stateSts) {
		this.stateSts = stateSts;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
}