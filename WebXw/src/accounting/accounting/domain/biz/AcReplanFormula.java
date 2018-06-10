package accounting.domain.biz;
import app.base.BaseDomain;
/**
* Title: AccReplanFormula.java
* Description:
* @作者 su
* @日期 2018-4-25
* @version：1.0
**/
public class AcReplanFormula extends accounting.domain.sys.CMISDomain {
	private String formulaId;//还款计划编号
	private String formulaName;//还款计划名称
	private String customEle;//自定义要素
	private String payCapDes;//应收本金公式描述
	private String payCap;//应收本金公式
	private String payInteDes;//应收利息公式描述
	private String payInte;//应收利息公式
	private String opNo;//操作员
	private String brNo;//机构号
	private String upDate;//更新时间

	/**
	 * @return 还款计划编号
	 */
	public String getFormulaId() {
	 	return formulaId;
	}
	/**
	 * @设置 还款计划编号
	 * @param formulaId
	 */
	public void setFormulaId(String formulaId) {
	 	this.formulaId = formulaId;
	}
	/**
	 * @return 还款计划名称
	 */
	public String getFormulaName() {
	 	return formulaName;
	}
	/**
	 * @设置 还款计划名称
	 * @param formulaName
	 */
	public void setFormulaName(String formulaName) {
	 	this.formulaName = formulaName;
	}
	/**
	 * @return 自定义要素
	 */
	public String getCustomEle() {
	 	return customEle;
	}
	/**
	 * @设置 自定义要素
	 * @param customEle
	 */
	public void setCustomEle(String customEle) {
	 	this.customEle = customEle;
	}
	/**
	 * @return 应收本金公式描述
	 */
	public String getPayCapDes() {
	 	return payCapDes;
	}
	/**
	 * @设置 应收本金公式描述
	 * @param payCapDes
	 */
	public void setPayCapDes(String payCapDes) {
	 	this.payCapDes = payCapDes;
	}
	/**
	 * @return 应收本金公式
	 */
	public String getPayCap() {
	 	return payCap;
	}
	/**
	 * @设置 应收本金公式
	 * @param payCap
	 */
	public void setPayCap(String payCap) {
	 	this.payCap = payCap;
	}
	/**
	 * @return 应收利息公式描述
	 */
	public String getPayInteDes() {
	 	return payInteDes;
	}
	/**
	 * @设置 应收利息公式描述
	 * @param payInteDes
	 */
	public void setPayInteDes(String payInteDes) {
	 	this.payInteDes = payInteDes;
	}
	/**
	 * @return 应收利息公式
	 */
	public String getPayInte() {
	 	return payInte;
	}
	/**
	 * @设置 应收利息公式
	 * @param payInte
	 */
	public void setPayInte(String payInte) {
	 	this.payInte = payInte;
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
	 * @return 机构号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 机构号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 更新时间
	 */
	public String getUpDate() {
	 	return upDate;
	}
	/**
	 * @设置 更新时间
	 * @param upDate
	 */
	public void setUpDate(String upDate) {
	 	this.upDate = upDate;
	}
}