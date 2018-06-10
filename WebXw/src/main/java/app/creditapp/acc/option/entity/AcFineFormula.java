package app.creditapp.acc.option.entity;
import app.base.BaseDomain;
/**
* Title: AcFineFormula.java
* Description:
* @version：1.0
**/
public class AcFineFormula extends BaseDomain {
	private String opNo;//登记人
	private String txDate;//登记日期
	private String upDate;//更新时间
	private String fineId;//罚息公式编号
	private String fineName;//罚息公式名称
	private String fineFormulaDes;//罚息公式描述
	private String fineFormula;//fine_formula
	private String fineSts;//罚息公式状态
	private double overRate;//逾期利率

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
	/**
	 * @return 罚息公式编号
	 */
	public String getFineId() {
	 	return fineId;
	}
	/**
	 * @设置 罚息公式编号
	 * @param fineId
	 */
	public void setFineId(String fineId) {
	 	this.fineId = fineId;
	}
	/**
	 * @return 罚息公式名称
	 */
	public String getFineName() {
	 	return fineName;
	}
	/**
	 * @设置 罚息公式名称
	 * @param fineName
	 */
	public void setFineName(String fineName) {
	 	this.fineName = fineName;
	}
	/**
	 * @return 罚息公式描述
	 */
	public String getFineFormulaDes() {
	 	return fineFormulaDes;
	}
	/**
	 * @设置 罚息公式描述
	 * @param fineFormulaDes
	 */
	public void setFineFormulaDes(String fineFormulaDes) {
	 	this.fineFormulaDes = fineFormulaDes;
	}
	/**
	 * @return fine_formula
	 */
	public String getFineFormula() {
	 	return fineFormula;
	}
	/**
	 * @设置 fine_formula
	 * @param fineFormula
	 */
	public void setFineFormula(String fineFormula) {
	 	this.fineFormula = fineFormula;
	}
	public double getOverRate() {
		return overRate;
	}
	public void setOverRate(double overRate) {
		this.overRate = overRate;
	}
	/**
	 * @return 罚息公式状态
	 */
	public String getFineSts() {
	 	return fineSts;
	}
	/**
	 * @设置 罚息公式状态
	 * @param fineSts
	 */
	public void setFineSts(String fineSts) {
	 	this.fineSts = fineSts;
	}
}