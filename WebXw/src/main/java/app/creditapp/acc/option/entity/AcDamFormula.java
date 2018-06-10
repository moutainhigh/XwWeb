package app.creditapp.acc.option.entity;
import app.base.BaseDomain;
/**
* Title: AcDamFormula.java
* Description:
* @version：1.0
**/
public class AcDamFormula extends BaseDomain {
	private String damId;//提前还款违约金公式编号
	private String damName;//提前还款违约金公式名称
	private String damFormulaDes;//提前还款违约金公式描述
	private String damFormula;//提前还款违约金公式
	private String frId;//费率编号
	private String damSts;//状态[0-失效;1-生效]
	private String opNo;//登记人
	private String txDate;//登记日期
	private String upDate;//更新日期
	
	private String feeMethod;//提前还款费用收取方式 01 全期 02 天数比例 03 无费用


	/**
	 * @return 提前还款违约金公式编号
	 */
	public String getDamId() {
	 	return damId;
	}
	/**
	 * @设置 提前还款违约金公式编号
	 * @param damId
	 */
	public void setDamId(String damId) {
	 	this.damId = damId;
	}
	/**
	 * @return 提前还款违约金公式名称
	 */
	public String getDamName() {
	 	return damName;
	}
	/**
	 * @设置 提前还款违约金公式名称
	 * @param damName
	 */
	public void setDamName(String damName) {
	 	this.damName = damName;
	}
	/**
	 * @return 提前还款违约金公式描述
	 */
	public String getDamFormulaDes() {
	 	return damFormulaDes;
	}
	/**
	 * @设置 提前还款违约金公式描述
	 * @param damFormulaDes
	 */
	public void setDamFormulaDes(String damFormulaDes) {
	 	this.damFormulaDes = damFormulaDes;
	}
	/**
	 * @return 提前还款违约金公式
	 */
	public String getDamFormula() {
	 	return damFormula;
	}
	/**
	 * @设置 提前还款违约金公式
	 * @param damFormula
	 */
	public void setDamFormula(String damFormula) {
	 	this.damFormula = damFormula;
	}
	/**
	 * @return 费率编号
	 */
	public String getFrId() {
	 	return frId;
	}
	/**
	 * @设置 费率编号
	 * @param frId
	 */
	public void setFrId(String frId) {
	 	this.frId = frId;
	}
	/**
	 * @return 状态[0-失效;1-生效]
	 */
	public String getDamSts() {
	 	return damSts;
	}
	/**
	 * @设置 状态[0-失效;1-生效]
	 * @param damSts
	 */
	public void setDamSts(String damSts) {
	 	this.damSts = damSts;
	}
	/**
	 * @return 登记人
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 登记人
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
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
	public String getFeeMethod() {
		return feeMethod;
	}
	public void setFeeMethod(String feeMethod) {
		this.feeMethod = feeMethod;
	}
}