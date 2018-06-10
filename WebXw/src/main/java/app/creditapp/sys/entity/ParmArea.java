package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: ParmArea.java
* Description:
* @version：1.0
**/
public class ParmArea extends BaseDomain {
	private String areaNo;//行政区划编号
	private String areaName;//行政区划名称
	private String areaLev;//行政级别
	private String areaUp;//上级行政区号
	private String postCode;//邮政编码
	private String areaSts;//状态

	/**
	 * @return 行政区划编号
	 */
	public String getAreaNo() {
	 	return areaNo;
	}
	/**
	 * @设置 行政区划编号
	 * @param areaNo
	 */
	public void setAreaNo(String areaNo) {
	 	this.areaNo = areaNo;
	}
	/**
	 * @return 行政区划名称
	 */
	public String getAreaName() {
	 	return areaName;
	}
	/**
	 * @设置 行政区划名称
	 * @param areaName
	 */
	public void setAreaName(String areaName) {
	 	this.areaName = areaName;
	}
	/**
	 * @return 行政级别
	 */
	public String getAreaLev() {
	 	return areaLev;
	}
	/**
	 * @设置 行政级别
	 * @param areaLev
	 */
	public void setAreaLev(String areaLev) {
	 	this.areaLev = areaLev;
	}
	/**
	 * @return 上级行政区号
	 */
	public String getAreaUp() {
	 	return areaUp;
	}
	/**
	 * @设置 上级行政区号
	 * @param areaUp
	 */
	public void setAreaUp(String areaUp) {
	 	this.areaUp = areaUp;
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
	 * @return 状态
	 */
	public String getAreaSts() {
	 	return areaSts;
	}
	/**
	 * @设置 状态
	 * @param areaSts
	 */
	public void setAreaSts(String areaSts) {
	 	this.areaSts = areaSts;
	}
}