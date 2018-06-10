package app.creditapp.ln.entity;
import app.base.BaseDomain;
/**
* Title: LnCom.java
* Description:
* @version：1.0
**/
public class LnCom extends BaseDomain {
	private String appId;//申请ID
	private String comName;//名称
	private String comIdtype;//证件类型
	private String comIdno;//证件号
	private String comTel;//联系电话

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
	 * @return 名称
	 */
	public String getComName() {
	 	return comName;
	}
	/**
	 * @设置 名称
	 * @param comName
	 */
	public void setComName(String comName) {
	 	this.comName = comName;
	}
	/**
	 * @return 证件类型
	 */
	public String getComIdtype() {
	 	return comIdtype;
	}
	/**
	 * @设置 证件类型
	 * @param comIdtype
	 */
	public void setComIdtype(String comIdtype) {
	 	this.comIdtype = comIdtype;
	}
	/**
	 * @return 证件号
	 */
	public String getComIdno() {
	 	return comIdno;
	}
	/**
	 * @设置 证件号
	 * @param comIdno
	 */
	public void setComIdno(String comIdno) {
	 	this.comIdno = comIdno;
	}
	/**
	 * @return 联系电话
	 */
	public String getComTel() {
	 	return comTel;
	}
	/**
	 * @设置 联系电话
	 * @param comTel
	 */
	public void setComTel(String comTel) {
	 	this.comTel = comTel;
	}
}