package app.creditapp.gage.entity;
import app.base.BaseDomain;
/**
* Title: LnGage.java
* Description:
* @version：1.0
**/
public class LnGage extends BaseDomain {
	private String upDate;//更新日期
	private String txDate;//申请日期
	private String opNo;//管户操作员号
	private String brNo;//合作机构号码
	private String gageSts;//押品状态[01新增02入库03解押04转抵债]
	private String gateDete;//下次检测日期
	private Double evalValue;//评估价值
	private String evalOrg;//评估机构
	private String evalDate;//评估日期
	private String evalType;//评估方法
	private Double gageAmt;//抵押金额
	private String gageOutdate;//解押日期
	private String gageEnddate;//押品到期日
	private String gageBegdate;//押品起始日
	private String gageLicbr;//权证发放机构
	private String gageLic;//他项权证号码
	private Double gageValue;//认定价值
	private String gageDesc;//押品描述
	private String gageName;//押品名称
	private String gageType;//押品类型
	private String gageIdno;//所有权人证件号码
	private String gageIdtype;//所有权人证件类型
	private String gageCifname;//所有权人名称
	private String gageId;//押品ID
	private String appId;//申请ID
	private String cifNo;//客户号
	private String gageLictype;//权证类型
	private String cifName;//客户名称
	private String brName;//合作机构名称
	private String opName;//操作员名称
	
	public String getCifNo() {
		return cifNo;
	}
	public void setCifNo(String cifNo) {
		this.cifNo = cifNo;
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
	 * @return 申请日期
	 */
	public String getTxDate() {
	 	return txDate;
	}
	/**
	 * @设置 申请日期
	 * @param txDate
	 */
	public void setTxDate(String txDate) {
	 	this.txDate = txDate;
	}
	/**
	 * @return 管户操作员号
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 管户操作员号
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
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
	 * @return 押品状态[01新增02入库03解押04转抵债]
	 */
	public String getGageSts() {
	 	return gageSts;
	}
	/**
	 * @设置 押品状态[01新增02入库03解押04转抵债]
	 * @param gageSts
	 */
	public void setGageSts(String gageSts) {
	 	this.gageSts = gageSts;
	}
	/**
	 * @return 下次检测日期
	 */
	public String getGateDete() {
	 	return gateDete;
	}
	/**
	 * @设置 下次检测日期
	 * @param gateDete
	 */
	public void setGateDete(String gateDete) {
	 	this.gateDete = gateDete;
	}
	/**
	 * @return 评估价值
	 */
	public Double getEvalValue() {
	 	return evalValue;
	}
	/**
	 * @设置 评估价值
	 * @param evalValue
	 */
	public void setEvalValue(Double evalValue) {
	 	this.evalValue = evalValue;
	}
	/**
	 * @return 评估机构
	 */
	public String getEvalOrg() {
	 	return evalOrg;
	}
	/**
	 * @设置 评估机构
	 * @param evalOrg
	 */
	public void setEvalOrg(String evalOrg) {
	 	this.evalOrg = evalOrg;
	}
	/**
	 * @return 评估日期
	 */
	public String getEvalDate() {
	 	return evalDate;
	}
	/**
	 * @设置 评估日期
	 * @param evalDate
	 */
	public void setEvalDate(String evalDate) {
	 	this.evalDate = evalDate;
	}
	/**
	 * @return 评估方法
	 */
	public String getEvalType() {
	 	return evalType;
	}
	/**
	 * @设置 评估方法
	 * @param evalType
	 */
	public void setEvalType(String evalType) {
	 	this.evalType = evalType;
	}
	/**
	 * @return 抵押金额
	 */
	public Double getGageAmt() {
	 	return gageAmt;
	}
	/**
	 * @设置 抵押金额
	 * @param gageAmt
	 */
	public void setGageAmt(Double gageAmt) {
	 	this.gageAmt = gageAmt;
	}
	/**
	 * @return 解押日期
	 */
	public String getGageOutdate() {
	 	return gageOutdate;
	}
	/**
	 * @设置 解押日期
	 * @param gageOutdate
	 */
	public void setGageOutdate(String gageOutdate) {
	 	this.gageOutdate = gageOutdate;
	}
	/**
	 * @return 押品到期日
	 */
	public String getGageEnddate() {
	 	return gageEnddate;
	}
	/**
	 * @设置 押品到期日
	 * @param gageEnddate
	 */
	public void setGageEnddate(String gageEnddate) {
	 	this.gageEnddate = gageEnddate;
	}
	/**
	 * @return 押品起始日
	 */
	public String getGageBegdate() {
	 	return gageBegdate;
	}
	/**
	 * @设置 押品起始日
	 * @param gageBegdate
	 */
	public void setGageBegdate(String gageBegdate) {
	 	this.gageBegdate = gageBegdate;
	}
	/**
	 * @return 权证发放机构
	 */
	public String getGageLicbr() {
	 	return gageLicbr;
	}
	/**
	 * @设置 权证发放机构
	 * @param gageLicbr
	 */
	public void setGageLicbr(String gageLicbr) {
	 	this.gageLicbr = gageLicbr;
	}
	/**
	 * @return 他项权证号码
	 */
	public String getGageLic() {
	 	return gageLic;
	}
	/**
	 * @设置 他项权证号码
	 * @param gageLic
	 */
	public void setGageLic(String gageLic) {
	 	this.gageLic = gageLic;
	}
	/**
	 * @return 认定价值
	 */
	public Double getGageValue() {
	 	return gageValue;
	}
	/**
	 * @设置 认定价值
	 * @param gageValue
	 */
	public void setGageValue(Double gageValue) {
	 	this.gageValue = gageValue;
	}
	/**
	 * @return 押品描述
	 */
	public String getGageDesc() {
	 	return gageDesc;
	}
	/**
	 * @设置 押品描述
	 * @param gageDesc
	 */
	public void setGageDesc(String gageDesc) {
	 	this.gageDesc = gageDesc;
	}
	/**
	 * @return 押品名称
	 */
	public String getGageName() {
	 	return gageName;
	}
	/**
	 * @设置 押品名称
	 * @param gageName
	 */
	public void setGageName(String gageName) {
	 	this.gageName = gageName;
	}
	/**
	 * @return 押品类型
	 */
	public String getGageType() {
	 	return gageType;
	}
	/**
	 * @设置 押品类型
	 * @param gageType
	 */
	public void setGageType(String gageType) {
	 	this.gageType = gageType;
	}
	/**
	 * @return 所有权人证件号码
	 */
	public String getGageIdno() {
	 	return gageIdno;
	}
	/**
	 * @设置 所有权人证件号码
	 * @param gageIdno
	 */
	public void setGageIdno(String gageIdno) {
	 	this.gageIdno = gageIdno;
	}
	/**
	 * @return 所有权人证件类型
	 */
	public String getGageIdtype() {
	 	return gageIdtype;
	}
	/**
	 * @设置 所有权人证件类型
	 * @param gageIdtype
	 */
	public void setGageIdtype(String gageIdtype) {
	 	this.gageIdtype = gageIdtype;
	}
	/**
	 * @return 所有权人名称
	 */
	public String getGageCifname() {
	 	return gageCifname;
	}
	/**
	 * @设置 所有权人名称
	 * @param gageCifname
	 */
	public void setGageCifname(String gageCifname) {
	 	this.gageCifname = gageCifname;
	}
	/**
	 * @return 押品ID
	 */
	public String getGageId() {
	 	return gageId;
	}
	/**
	 * @设置 押品ID
	 * @param gageId
	 */
	public void setGageId(String gageId) {
	 	this.gageId = gageId;
	}
	/**
	 * @return 申请ID
	 */
	public String getAppId() {
	 	return appId;
	}
	/**
	 * @设置 申请ID
	 * @param appId
	 */
	public void setAppId(String appId) {
	 	this.appId = appId;
	}
	public String getGageLictype() {
		return gageLictype;
	}
	public void setGageLictype(String gageLictype) {
		this.gageLictype = gageLictype;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
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