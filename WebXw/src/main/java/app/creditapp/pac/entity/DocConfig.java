package app.creditapp.pac.entity;
import app.base.BaseDomain;
/**
* Title: DocConfig.java
* Description:
* @version：1.0
**/
public class DocConfig extends BaseDomain {
	private String ifStart;//是否启用1：是2：否
	private String ifSign;//是否需要签章1：是2：否
	private String docTypeName;//文档类型名称
	private String docTypeNo;//文档类型编号
	private String docTypeId;//文档类型ID
	private String txDate;//登记日期
	private String opNo;//登记人员
	private String upDate;//更新日期
	private String upOpno;//修改人员

	/**
	 * @return 是否启用1：是2：否
	 */
	public String getIfStart() {
	 	return ifStart;
	}
	/**
	 * @设置 是否启用1：是2：否
	 * @param ifStart
	 */
	public void setIfStart(String ifStart) {
	 	this.ifStart = ifStart;
	}
	/**
	 * @return 是否需要签章1：是2：否
	 */
	public String getIfSign() {
	 	return ifSign;
	}
	/**
	 * @设置 是否需要签章1：是2：否
	 * @param ifSign
	 */
	public void setIfSign(String ifSign) {
	 	this.ifSign = ifSign;
	}
	/**
	 * @return 文档类型名称
	 */
	public String getDocTypeName() {
	 	return docTypeName;
	}
	/**
	 * @设置 文档类型名称
	 * @param docTypeName
	 */
	public void setDocTypeName(String docTypeName) {
	 	this.docTypeName = docTypeName;
	}
	/**
	 * @return 文档类型编号
	 */
	public String getDocTypeNo() {
	 	return docTypeNo;
	}
	/**
	 * @设置 文档类型编号
	 * @param docTypeNo
	 */
	public void setDocTypeNo(String docTypeNo) {
	 	this.docTypeNo = docTypeNo;
	}
	/**
	 * @return 文档类型ID
	 */
	public String getDocTypeId() {
	 	return docTypeId;
	}
	/**
	 * @设置 文档类型ID
	 * @param docTypeId
	 */
	public void setDocTypeId(String docTypeId) {
	 	this.docTypeId = docTypeId;
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
	 * @return 登记人员
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 登记人员
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
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
}