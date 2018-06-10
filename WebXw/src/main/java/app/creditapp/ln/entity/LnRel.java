package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnRel.java
* Description:
* @version：1.0
**/
public class LnRel extends BaseDomain {
	private String appId;//申请ID
	private String relName;//关联人名称
	private String relIdtype;//关联人证件
	private String relIdno;//关联人证件号
	private String relTel;//关联人联系电话

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
	 * @return 关联人名称
	 */
	public String getRelName() {
	 	return relName;
	}
	/**
	 * @设置 关联人名称
	 * @param relName
	 */
	public void setRelName(String relName) {
	 	this.relName = relName;
	}
	/**
	 * @return 关联人证件
	 */
	public String getRelIdtype() {
	 	return relIdtype;
	}
	/**
	 * @设置 关联人证件
	 * @param relIdtype
	 */
	public void setRelIdtype(String relIdtype) {
	 	this.relIdtype = relIdtype;
	}
	/**
	 * @return 关联人证件号
	 */
	public String getRelIdno() {
	 	return relIdno;
	}
	/**
	 * @设置 关联人证件号
	 * @param relIdno
	 */
	public void setRelIdno(String relIdno) {
	 	this.relIdno = relIdno;
	}
	/**
	 * @return 关联人联系电话
	 */
	public String getRelTel() {
	 	return relTel;
	}
	/**
	 * @设置 关联人联系电话
	 * @param relTel
	 */
	public void setRelTel(String relTel) {
	 	this.relTel = relTel;
	}
}