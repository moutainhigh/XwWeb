package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysOrg.java
* Description:
* @version：1.0
**/
public class SysOrg extends BaseDomain {
	private String orgDisname;//机构简称
	private String orgName;//机构名称
	private String orgNo;//机构/部门编号
	private String supNo;//上级机构号
	private String orgType;//机构类型[01部门02公司]
	private Integer orgLev;//机构等级
	private String orgChannel;//通讯渠道号(与外围系统通讯渠道号码)
	private String orgFinno;//金融机构代码
	private String orgSts;//系统状态[01正常02停用]

	/**
	 * @return 机构简称
	 */
	public String getOrgDisname() {
	 	return orgDisname;
	}
	/**
	 * @设置 机构简称
	 * @param orgDisname
	 */
	public void setOrgDisname(String orgDisname) {
	 	this.orgDisname = orgDisname;
	}
	/**
	 * @return 机构名称
	 */
	public String getOrgName() {
	 	return orgName;
	}
	/**
	 * @设置 机构名称
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
	 	this.orgName = orgName;
	}
	/**
	 * @return 机构/部门编号
	 */
	public String getOrgNo() {
	 	return orgNo;
	}
	/**
	 * @设置 机构/部门编号
	 * @param orgNo
	 */
	public void setOrgNo(String orgNo) {
	 	this.orgNo = orgNo;
	}
	/**
	 * @return 上级机构号
	 */
	public String getSupNo() {
	 	return supNo;
	}
	/**
	 * @设置 上级机构号
	 * @param supNo
	 */
	public void setSupNo(String supNo) {
	 	this.supNo = supNo;
	}
	/**
	 * @return 机构类型[01部门02公司]
	 */
	public String getOrgType() {
	 	return orgType;
	}
	/**
	 * @设置 机构类型[01部门02公司]
	 * @param orgType
	 */
	public void setOrgType(String orgType) {
	 	this.orgType = orgType;
	}
	/**
	 * @return 机构等级
	 */
	public Integer getOrgLev() {
	 	return orgLev;
	}
	/**
	 * @设置 机构等级
	 * @param orgLev
	 */
	public void setOrgLev(Integer orgLev) {
	 	this.orgLev = orgLev;
	}
	/**
	 * @return 通讯渠道号(与外围系统通讯渠道号码)
	 */
	public String getOrgChannel() {
	 	return orgChannel;
	}
	/**
	 * @设置 通讯渠道号(与外围系统通讯渠道号码)
	 * @param orgChannel
	 */
	public void setOrgChannel(String orgChannel) {
	 	this.orgChannel = orgChannel;
	}
	/**
	 * @return 金融机构代码
	 */
	public String getOrgFinno() {
	 	return orgFinno;
	}
	/**
	 * @设置 金融机构代码
	 * @param orgFinno
	 */
	public void setOrgFinno(String orgFinno) {
	 	this.orgFinno = orgFinno;
	}
	/**
	 * @return 系统状态[01正常02停用]
	 */
	public String getOrgSts() {
	 	return orgSts;
	}
	/**
	 * @设置 系统状态[01正常02停用]
	 * @param orgSts
	 */
	public void setOrgSts(String orgSts) {
	 	this.orgSts = orgSts;
	}
}