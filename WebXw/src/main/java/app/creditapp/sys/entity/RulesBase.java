package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: RulesBase.java
* Description:
* @version：1.0
**/
public class RulesBase extends BaseDomain {
	private String prdtNo;//产品号
	private String codeValue;//字段名称
	private String codeName;//字段名
	private String codeDesc;//字段描述
	private String codeSts;//字段值[01-准入|02-拒绝|03-实时]

	/**
	 * @return 产品号
	 */
	public String getPrdtNo() {
	 	return prdtNo;
	}
	/**
	 * @设置 产品号
	 * @param prdtNo
	 */
	public void setPrdtNo(String prdtNo) {
	 	this.prdtNo = prdtNo;
	}
	/**
	 * @return 字段名称
	 */
	public String getCodeValue() {
	 	return codeValue;
	}
	/**
	 * @设置 字段名称
	 * @param codeValue
	 */
	public void setCodeValue(String codeValue) {
	 	this.codeValue = codeValue;
	}
	/**
	 * @return 字段名
	 */
	public String getCodeName() {
	 	return codeName;
	}
	/**
	 * @设置 字段名
	 * @param codeName
	 */
	public void setCodeName(String codeName) {
	 	this.codeName = codeName;
	}
	/**
	 * @return 字段描述
	 */
	public String getCodeDesc() {
	 	return codeDesc;
	}
	/**
	 * @设置 字段描述
	 * @param codeDesc
	 */
	public void setCodeDesc(String codeDesc) {
	 	this.codeDesc = codeDesc;
	}
	/**
	 * @return 字段值[01-准入|02-拒绝|03-实时]
	 */
	public String getCodeSts() {
	 	return codeSts;
	}
	/**
	 * @设置 字段值[01-准入|02-拒绝|03-实时]
	 * @param codeSts
	 */
	public void setCodeSts(String codeSts) {
	 	this.codeSts = codeSts;
	}
}