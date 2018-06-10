package app.creditapp.proj.entity;
import app.base.BaseDomain;
/**
* Title: ProjBase.java
* Description:
* @version：1.0
**/
public class ProjBase extends BaseDomain {
	private String projSts;//项目状态[01正常运行02结束]
	private String incDisFre;//收益分配频度[00:周01:旬02:月度03:季度04:年度05:到期一次付清06:其他]
	private String incDisWay;//收益分配方式[00:其他01：到期还本付息02:分期付息03:分期还本]
	private Double projAmt;//预计项目规模（元）
	private String projStr;//项目结构属性[取值00:结构化01:非结构化]
	private String projNatu;//项目性质[01:单一02:集合]
	private String brName;//合作机构名称
	private String brNo;//合作机构编号
	private String projName;//项目名称
	private String projNo;//项目编号
	private String mpactNo;//主合同编号
	private String assType;//资产类型[项目属性为：财产信托、综合信托时必填]
	private String keepBkno;//保管行编码[项目性质为：集合时必填]
	private String secPayWay;//保障基金缴纳方式[取值01:自有财产承担02:信托财产承担03:第三方承担04:不缴纳]
	private String secRepayWay;//保障基金返还方式[取值01:自营代垫02:保障基金返还后分配]
	private String quitWay;//退出方式[01:收益权转让 信托到期分配 02：赎回 收益权转让 信托到期分配 03：无]
	private String proFloat;//信托受益权流动性安排[01受益权转让、开放日赎回02受益权转让、信托到期分配]
	private String infdisWay;//信息披露方式[01信托公告 02邮寄 03不披露 04无]
	private String funType;//功能分类[01:融资类02:投资类03:事务类]
	private float payRate;//信托合同约定信托报酬率(%)
	private String payRateDesc;//信托合同约定信托报酬率(描述)
	private Double othFee;//除信托报酬外其他费用金额
	private float othRate;//其他年化费用率（%）
	private String prepubBeg;//预计发行开始日期
	private String setupDate;//项目成立日期
	private String prepubEnd;//预计发行结束日期
	private String begDate;//项目生效日期
	private String setupCond;//成立条件说明
	private String endDate;//项目预计结束日期
	private String ifObo;//是否分笔到账[0-否1-是]
	private Integer projTerm;//项目期限
	private String termUnit;//期限单位
	private String termDesc;//期限说明
	private String filler;//备注
	private String deptNo;//登记部门
	private String opNo;//操作员
	private String opName;//操作员名称
	private String txDate;//登记日期
	private String upDate;//修改日期
	private String upOpno;//修改人员
	private String upOpname;//修改人员名称
	private String bankAcntNo;//专户账号
	
	private String id;//修改人员
	private String managers;//修改人员
	private String loginid;//登录人员
	private String vipFlag;//标记
	private String projId;//软通项目编号
	private String brNoSts;
	private String projCode;//软通项目代码
	private String tenantNo;//租户号
	/**
	 * @return 项目状态[01正常运行02结束]
	 */
	public String getProjSts() {
	 	return projSts;
	}
	/**
	 * @设置 项目状态[01正常运行02结束]
	 * @param projSts
	 */
	public void setProjSts(String projSts) {
	 	this.projSts = projSts;
	}
	/**
	 * @return 收益分配频度[00:周01:旬02:月度03:季度04:年度05:到期一次付清06:其他]
	 */
	public String getIncDisFre() {
	 	return incDisFre;
	}
	/**
	 * @设置 收益分配频度[00:周01:旬02:月度03:季度04:年度05:到期一次付清06:其他]
	 * @param incDisFre
	 */
	public void setIncDisFre(String incDisFre) {
	 	this.incDisFre = incDisFre;
	}
	/**
	 * @return 收益分配方式[00:其他01：到期还本付息02:分期付息03:分期还本]
	 */
	public String getIncDisWay() {
	 	return incDisWay;
	}
	/**
	 * @设置 收益分配方式[00:其他01：到期还本付息02:分期付息03:分期还本]
	 * @param incDisWay
	 */
	public void setIncDisWay(String incDisWay) {
	 	this.incDisWay = incDisWay;
	}
	/**
	 * @return 预计项目规模（元）
	 */
	public Double getProjAmt() {
	 	return projAmt;
	}
	/**
	 * @设置 预计项目规模（元）
	 * @param projAmt
	 */
	public void setProjAmt(Double projAmt) {
	 	this.projAmt = projAmt;
	}
	/**
	 * @return 项目结构属性[取值00:结构化01:非结构化]
	 */
	public String getProjStr() {
	 	return projStr;
	}
	/**
	 * @设置 项目结构属性[取值00:结构化01:非结构化]
	 * @param projStr
	 */
	public void setProjStr(String projStr) {
	 	this.projStr = projStr;
	}
	/**
	 * @return 项目性质[01:单一02:集合]
	 */
	public String getProjNatu() {
	 	return projNatu;
	}
	/**
	 * @设置 项目性质[01:单一02:集合]
	 * @param projNatu
	 */
	public void setProjNatu(String projNatu) {
	 	this.projNatu = projNatu;
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
	 * @return 项目名称
	 */
	public String getProjName() {
	 	return projName;
	}
	/**
	 * @设置 项目名称
	 * @param projName
	 */
	public void setProjName(String projName) {
	 	this.projName = projName;
	}
	/**
	 * @return 项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	/**
	 * @return 主合同编号
	 */
	public String getMpactNo() {
	 	return mpactNo;
	}
	/**
	 * @设置 主合同编号
	 * @param mpactNo
	 */
	public void setMpactNo(String mpactNo) {
	 	this.mpactNo = mpactNo;
	}
	/**
	 * @return 资产类型[项目属性为：财产信托、综合信托时必填]
	 */
	public String getAssType() {
	 	return assType;
	}
	/**
	 * @设置 资产类型[项目属性为：财产信托、综合信托时必填]
	 * @param assType
	 */
	public void setAssType(String assType) {
	 	this.assType = assType;
	}
	/**
	 * @return 保管行编码[项目性质为：集合时必填]
	 */
	public String getKeepBkno() {
	 	return keepBkno;
	}
	/**
	 * @设置 保管行编码[项目性质为：集合时必填]
	 * @param keepBkno
	 */
	public void setKeepBkno(String keepBkno) {
	 	this.keepBkno = keepBkno;
	}
	/**
	 * @return 保障基金缴纳方式[取值01:自有财产承担02:信托财产承担03:第三方承担04:不缴纳]
	 */
	public String getSecPayWay() {
	 	return secPayWay;
	}
	/**
	 * @设置 保障基金缴纳方式[取值01:自有财产承担02:信托财产承担03:第三方承担04:不缴纳]
	 * @param secPayWay
	 */
	public void setSecPayWay(String secPayWay) {
	 	this.secPayWay = secPayWay;
	}
	/**
	 * @return 保障基金返还方式[取值01:自营代垫02:保障基金返还后分配]
	 */
	public String getSecRepayWay() {
	 	return secRepayWay;
	}
	/**
	 * @设置 保障基金返还方式[取值01:自营代垫02:保障基金返还后分配]
	 * @param secRepayWay
	 */
	public void setSecRepayWay(String secRepayWay) {
	 	this.secRepayWay = secRepayWay;
	}
	/**
	 * @return 退出方式[01:收益权转让 信托到期分配 02：赎回 收益权转让 信托到期分配 03：无]
	 */
	public String getQuitWay() {
	 	return quitWay;
	}
	/**
	 * @设置 退出方式[01:收益权转让 信托到期分配 02：赎回 收益权转让 信托到期分配 03：无]
	 * @param quitWay
	 */
	public void setQuitWay(String quitWay) {
	 	this.quitWay = quitWay;
	}
	/**
	 * @return 信托受益权流动性安排[01受益权转让、开放日赎回02受益权转让、信托到期分配]
	 */
	public String getProFloat() {
	 	return proFloat;
	}
	/**
	 * @设置 信托受益权流动性安排[01受益权转让、开放日赎回02受益权转让、信托到期分配]
	 * @param proFloat
	 */
	public void setProFloat(String proFloat) {
	 	this.proFloat = proFloat;
	}
	/**
	 * @return 信息披露方式[01信托公告 02邮寄 03不披露 04无]
	 */
	public String getInfdisWay() {
	 	return infdisWay;
	}
	/**
	 * @设置 信息披露方式[01信托公告 02邮寄 03不披露 04无]
	 * @param infdisWay
	 */
	public void setInfdisWay(String infdisWay) {
	 	this.infdisWay = infdisWay;
	}
	/**
	 * @return 功能分类[01:融资类02:投资类03:事务类]
	 */
	public String getFunType() {
	 	return funType;
	}
	/**
	 * @设置 功能分类[01:融资类02:投资类03:事务类]
	 * @param funType
	 */
	public void setFunType(String funType) {
	 	this.funType = funType;
	}
	/**
	 * @return 信托合同约定信托报酬率(%)
	 */
	public Float getPayRate() {
	 	return payRate;
	}
	/**
	 * @设置 信托合同约定信托报酬率(%)
	 * @param payRate
	 */
	public void setPayRate(Float payRate) {
	 	this.payRate = payRate;
	}
	/**
	 * @return 信托合同约定信托报酬率(描述)
	 */
	public String getPayRateDesc() {
	 	return payRateDesc;
	}
	/**
	 * @设置 信托合同约定信托报酬率(描述)
	 * @param payRateDesc
	 */
	public void setPayRateDesc(String payRateDesc) {
	 	this.payRateDesc = payRateDesc;
	}
	/**
	 * @return 除信托报酬外其他费用金额
	 */
	public Double getOthFee() {
	 	return othFee;
	}
	/**
	 * @设置 除信托报酬外其他费用金额
	 * @param othFee
	 */
	public void setOthFee(Double othFee) {
	 	this.othFee = othFee;
	}
	/**
	 * @return 其他年化费用率（%）
	 */
	public Float getOthRate() {
	 	return othRate;
	}
	/**
	 * @设置 其他年化费用率（%）
	 * @param othRate
	 */
	public void setOthRate(Float othRate) {
	 	this.othRate = othRate;
	}
	/**
	 * @return 预计发行开始日期
	 */
	public String getPrepubBeg() {
	 	return prepubBeg;
	}
	/**
	 * @设置 预计发行开始日期
	 * @param prepubBeg
	 */
	public void setPrepubBeg(String prepubBeg) {
	 	this.prepubBeg = prepubBeg;
	}
	/**
	 * @return 项目成立日期
	 */
	public String getSetupDate() {
	 	return setupDate;
	}
	/**
	 * @设置 项目成立日期
	 * @param setupDate
	 */
	public void setSetupDate(String setupDate) {
	 	this.setupDate = setupDate;
	}
	/**
	 * @return 预计发行结束日期
	 */
	public String getPrepubEnd() {
	 	return prepubEnd;
	}
	/**
	 * @设置 预计发行结束日期
	 * @param prepubEnd
	 */
	public void setPrepubEnd(String prepubEnd) {
	 	this.prepubEnd = prepubEnd;
	}
	/**
	 * @return 项目生效日期
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 项目生效日期
	 * @param begDate
	 */
	public void setBegDate(String begDate) {
	 	this.begDate = begDate;
	}
	/**
	 * @return 成立条件说明
	 */
	public String getSetupCond() {
	 	return setupCond;
	}
	/**
	 * @设置 成立条件说明
	 * @param setupCond
	 */
	public void setSetupCond(String setupCond) {
	 	this.setupCond = setupCond;
	}
	/**
	 * @return 项目预计结束日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 项目预计结束日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 是否分笔到账[0-否1-是]
	 */
	public String getIfObo() {
	 	return ifObo;
	}
	/**
	 * @设置 是否分笔到账[0-否1-是]
	 * @param ifObo
	 */
	public void setIfObo(String ifObo) {
	 	this.ifObo = ifObo;
	}
	/**
	 * @return 项目期限
	 */
	public Integer getProjTerm() {
	 	return projTerm;
	}
	/**
	 * @设置 项目期限
	 * @param projTerm
	 */
	public void setProjTerm(Integer projTerm) {
	 	this.projTerm = projTerm;
	}
	/**
	 * @return 期限单位
	 */
	public String getTermUnit() {
	 	return termUnit;
	}
	/**
	 * @设置 期限单位
	 * @param termUnit
	 */
	public void setTermUnit(String termUnit) {
	 	this.termUnit = termUnit;
	}
	/**
	 * @return 期限说明
	 */
	public String getTermDesc() {
	 	return termDesc;
	}
	/**
	 * @设置 期限说明
	 * @param termDesc
	 */
	public void setTermDesc(String termDesc) {
	 	this.termDesc = termDesc;
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
	public void setPayRate(float payRate) {
		this.payRate = payRate;
	}
	public void setOthRate(float othRate) {
		this.othRate = othRate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManagers() {
		return managers;
	}
	public void setManagers(String managers) {
		this.managers = managers;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getVipFlag() {
		return vipFlag;
	}
	public void setVipFlag(String vipFlag) {
		this.vipFlag = vipFlag;
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
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getBankAcntNo() {
		return bankAcntNo;
	}
	public void setBankAcntNo(String bankAcntNo) {
		this.bankAcntNo = bankAcntNo;
	}
	public String getBrNoSts() {
		return brNoSts;
	}
	public void setBrNoSts(String brNoSts) {
		this.brNoSts = brNoSts;
	}
	public String getProjCode() {
		return projCode;
	}
	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	public String getTenantNo() {
		return tenantNo;
	}
	public void setTenantNo(String tenantNo) {
		this.tenantNo = tenantNo;
	}
	
	
}