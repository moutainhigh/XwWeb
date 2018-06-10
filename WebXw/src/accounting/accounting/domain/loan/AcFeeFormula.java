package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AcFeeFormula.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcFeeFormula extends accounting.domain.sys.CMISDomain {
	private String feeFormId;//费用公式编号
	private String feeFormName;//费用公式名称
	private String frId;//费率编号
	private String feeFormulaDes;//费用公式描述
	private String feeFormula;//费用公式

	/**
	 * @return 费用公式编号
	 */
	public String getFeeFormId() {
	 	return feeFormId;
	}
	/**
	 * @设置 费用公式编号
	 * @param feeFormId
	 */
	public void setFeeFormId(String feeFormId) {
	 	this.feeFormId = feeFormId;
	}
	/**
	 * @return 费用公式名称
	 */
	public String getFeeFormName() {
	 	return feeFormName;
	}
	/**
	 * @设置 费用公式名称
	 * @param feeFormName
	 */
	public void setFeeFormName(String feeFormName) {
	 	this.feeFormName = feeFormName;
	}
	/**
	 * @return 费用公式描述
	 */
	public String getFeeFormulaDes() {
	 	return feeFormulaDes;
	}
	/**
	 * @设置 费用公式描述
	 * @param feeFormulaDes
	 */
	public void setFeeFormulaDes(String feeFormulaDes) {
	 	this.feeFormulaDes = feeFormulaDes;
	}
	/**
	 * @return 费用公式
	 */
	public String getFeeFormula() {
	 	return feeFormula;
	}
	public String getFrId() {
		return frId;
	}
	public void setFrId(String frId) {
		this.frId = frId;
	}
	/**
	 * @设置 费用公式
	 * @param feeFormula
	 */
	public void setFeeFormula(String feeFormula) {
	 	this.feeFormula = feeFormula;
	}
}