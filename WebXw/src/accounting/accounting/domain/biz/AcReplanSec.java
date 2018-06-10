package accounting.domain.biz;
import app.base.BaseDomain;
/**
* Title: AcReplanSec.java
* Description:
* @作者 su
* @日期 2018-4-25
* @version：1.0
**/
public class AcReplanSec extends accounting.domain.sys.CMISDomain {
	private String secId;//分段编号
	private String replanId;//还款方案编号
	private String formulaId;//还款计划编号
	private String repayType;//计息方式
	private Double capitalRate;//本金比例
	private String begRepayNo;//起始期次
	private String endRepayNo;//到期期次
	private String upDate;//更新时间
	private String secOrderNo;//分段次序

	/**
	 * @return 分段编号
	 */
	public String getSecId() {
	 	return secId;
	}
	/**
	 * @设置 分段编号
	 * @param secId
	 */
	public void setSecId(String secId) {
	 	this.secId = secId;
	}
	/**
	 * @return 还款方案编号
	 */
	public String getReplanId() {
	 	return replanId;
	}
	/**
	 * @设置 还款方案编号
	 * @param replanId
	 */
	public void setReplanId(String replanId) {
	 	this.replanId = replanId;
	}
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
	 * @return 计息方式
	 */
	public String getRepayType() {
	 	return repayType;
	}
	/**
	 * @设置 计息方式
	 * @param repayType
	 */
	public void setRepayType(String repayType) {
	 	this.repayType = repayType;
	}
	/**
	 * @return 本金比例
	 */
	public Double getCapitalRate() {
	 	return capitalRate;
	}
	/**
	 * @设置 本金比例
	 * @param capitalRate
	 */
	public void setCapitalRate(Double capitalRate) {
	 	this.capitalRate = capitalRate;
	}
	/**
	 * @return 起始期次
	 */
	public String getBegRepayNo() {
	 	return begRepayNo;
	}
	/**
	 * @设置 起始期次
	 * @param begRepayNo
	 */
	public void setBegRepayNo(String begRepayNo) {
	 	this.begRepayNo = begRepayNo;
	}
	/**
	 * @return 到期期次
	 */
	public String getEndRepayNo() {
	 	return endRepayNo;
	}
	/**
	 * @设置 到期期次
	 * @param endRepayNo
	 */
	public void setEndRepayNo(String endRepayNo) {
	 	this.endRepayNo = endRepayNo;
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
	public String getSecOrderNo() {
		return secOrderNo;
	}
	public void setSecOrderNo(String secOrderNo) {
		this.secOrderNo = secOrderNo;
	}
}