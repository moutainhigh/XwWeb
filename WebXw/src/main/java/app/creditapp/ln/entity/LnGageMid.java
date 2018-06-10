package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnGageMid.java
* Description:
* @version：1.0
**/
public class LnGageMid extends BaseDomain {
	private String appId;//申请ID
	private String gageCifname;//所有权人名称
	private String gageIdtype;//所有权人证件类型
	private String gageIdno;//所有权人证件号码
	private String gageType;//押品类型
	private String gageName;//押品名称
	private String gageDesc;//押品描述
	private Double gageValue;//押品评估价值
	private String gageLic;//权证号码
	private String gageLictype;//权证类型[01房产证02机动车登记证书]
	private String gageLicbr;//权证发放机构
	private String gageBegdate;//押品起始日
	private String gageEnddate;//押品到期日
	private Double gageAmt;//抵押金额
	private String pactNo;//合同号

	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
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
	 * @return 押品评估价值
	 */
	public Double getGageValue() {
	 	return gageValue;
	}
	/**
	 * @设置 押品评估价值
	 * @param gageValue
	 */
	public void setGageValue(Double gageValue) {
	 	this.gageValue = gageValue;
	}
	/**
	 * @return 权证号码
	 */
	public String getGageLic() {
	 	return gageLic;
	}
	/**
	 * @设置 权证号码
	 * @param gageLic
	 */
	public void setGageLic(String gageLic) {
	 	this.gageLic = gageLic;
	}
	/**
	 * @return 权证类型[01房产证02机动车登记证书]
	 */
	public String getGageLictype() {
	 	return gageLictype;
	}
	/**
	 * @设置 权证类型[01房产证02机动车登记证书]
	 * @param gageLictype
	 */
	public void setGageLictype(String gageLictype) {
	 	this.gageLictype = gageLictype;
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
}