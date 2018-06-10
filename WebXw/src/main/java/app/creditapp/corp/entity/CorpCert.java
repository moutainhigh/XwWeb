package app.creditapp.corp.entity;
import app.base.BaseDomain;
/**
* Title: CorpCert.java
* Description:
* @version：1.0
**/
public class CorpCert extends BaseDomain {
	private String regBegDate;//发证日期
	private String certAudOrg;//发证机关
	private String certName;//资质证书名称
	private String certId;//资质编号
	private String brName;//合作机构名称
	private String brNo;//合作机构编号
	private String certLev;//资质等级
	private String regEndDate;//到期日期
	private String certDesc;//资质证书描述
	private String certImg;//资质证书图片
	private String filler;//备注
	private String certCode;//资质证书编号
	private String deptNo;//登记部门
	private String opNo;//操作员
	private String opName;//操作员名称
	private String upOpNo;//最后更新操作员
	private String upOpName;//最后更新操作员名称
	private String txDate;//登记日期
	private String upDate;//更新日期

	/**
	 * @return 发证日期
	 */
	public String getRegBegDate() {
	 	return regBegDate;
	}
	/**
	 * @设置 发证日期
	 * @param regBegDate
	 */
	public void setRegBegDate(String regBegDate) {
	 	this.regBegDate = regBegDate;
	}
	/**
	 * @return 发证机关
	 */
	public String getCertAudOrg() {
	 	return certAudOrg;
	}
	/**
	 * @设置 发证机关
	 * @param certAudOrg
	 */
	public void setCertAudOrg(String certAudOrg) {
	 	this.certAudOrg = certAudOrg;
	}
	/**
	 * @return 资质证书名称
	 */
	public String getCertName() {
	 	return certName;
	}
	/**
	 * @设置 资质证书名称
	 * @param certName
	 */
	public void setCertName(String certName) {
	 	this.certName = certName;
	}
	/**
	 * @return 资质编号
	 */
	public String getCertId() {
	 	return certId;
	}
	/**
	 * @设置 资质编号
	 * @param certId
	 */
	public void setCertId(String certId) {
	 	this.certId = certId;
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
	 * @return 资质等级
	 */
	public String getCertLev() {
	 	return certLev;
	}
	/**
	 * @设置 资质等级
	 * @param certLev
	 */
	public void setCertLev(String certLev) {
	 	this.certLev = certLev;
	}
	/**
	 * @return 到期日期
	 */
	public String getRegEndDate() {
	 	return regEndDate;
	}
	/**
	 * @设置 到期日期
	 * @param regEndDate
	 */
	public void setRegEndDate(String regEndDate) {
	 	this.regEndDate = regEndDate;
	}
	/**
	 * @return 资质证书描述
	 */
	public String getCertDesc() {
	 	return certDesc;
	}
	/**
	 * @设置 资质证书描述
	 * @param certDesc
	 */
	public void setCertDesc(String certDesc) {
	 	this.certDesc = certDesc;
	}
	/**
	 * @return 资质证书图片
	 */
	public String getCertImg() {
	 	return certImg;
	}
	/**
	 * @设置 资质证书图片
	 * @param certImg
	 */
	public void setCertImg(String certImg) {
	 	this.certImg = certImg;
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
	 * @return 资质证书编号
	 */
	public String getCertCode() {
	 	return certCode;
	}
	/**
	 * @设置 资质证书编号
	 * @param certCode
	 */
	public void setCertCode(String certCode) {
	 	this.certCode = certCode;
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
	 * @return 最后更新操作员
	 */
	public String getUpOpNo() {
	 	return upOpNo;
	}
	/**
	 * @设置 最后更新操作员
	 * @param upOpNo
	 */
	public void setUpOpNo(String upOpNo) {
	 	this.upOpNo = upOpNo;
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
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getUpOpName() {
		return upOpName;
	}
	public void setUpOpName(String upOpName) {
		this.upOpName = upOpName;
	}
}