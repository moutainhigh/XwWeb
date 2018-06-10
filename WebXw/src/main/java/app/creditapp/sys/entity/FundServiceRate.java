package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: FundServiceRate.java
* Description:
* @version：1.0
**/
public class FundServiceRate extends BaseDomain {
	private String serviceRateId;//id
	private String projNo;//项目编号
	private String projName;//项目名称
	private String stepNo;//阶梯编号
	private Double minAmt;//阶梯放贷规模下线
	private Double maxAmt;//阶梯放贷规模上线
	private Float svRate;//服务费率
	private String opNo;//登记人员
	private String opName;//登记人员名称
	private String txDate;//登记日期
	private String filler;//备注

	/**
	 * @return 项目编号
	 */
	public String getProjNo() {
	 	return projNo;
	}
	/**
	 * @设置 项目编号
	 * @param projNo
	 */
	public void setProjNo(String projNo) {
	 	this.projNo = projNo;
	}
	/**
	 * @return 项目名称
	 */
	public String getProjName() {
	 	return projName;
	}
	/**
	 * @设置 项目名称
	 * @param projName
	 */
	public void setProjName(String projName) {
	 	this.projName = projName;
	}
	/**
	 * @return 阶梯编号
	 */
	public String getStepNo() {
	 	return stepNo;
	}
	/**
	 * @设置 阶梯编号
	 * @param stepNo
	 */
	public void setStepNo(String stepNo) {
	 	this.stepNo = stepNo;
	}
	/**
	 * @return 阶梯放贷规模下线
	 */
	public Double getMinAmt() {
	 	return minAmt;
	}
	/**
	 * @设置 阶梯放贷规模下线
	 * @param minAmt
	 */
	public void setMinAmt(Double minAmt) {
	 	this.minAmt = minAmt;
	}
	/**
	 * @return 阶梯放贷规模上线
	 */
	public Double getMaxAmt() {
	 	return maxAmt;
	}
	/**
	 * @设置 阶梯放贷规模上线
	 * @param maxAmt
	 */
	public void setMaxAmt(Double maxAmt) {
	 	this.maxAmt = maxAmt;
	}
	/**
	 * @return 服务费率
	 */
	public Float getSvRate() {
	 	return svRate;
	}
	/**
	 * @设置 服务费率
	 * @param svRate
	 */
	public void setSvRate(Float svRate) {
	 	this.svRate = svRate;
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
	 * @return 备注
	 */
	public String getFiller() {
	 	return filler;
	}
	/**
	 * @设置 备注
	 * @param filler
	 */
	public void setFiller(String filler) {
	 	this.filler = filler;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getServiceRateId() {
		return serviceRateId;
	}
	public void setServiceRateId(String serviceRateId) {
		this.serviceRateId = serviceRateId;
	}
}