package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AcFeeParm.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcFeeParm extends accounting.domain.sys.CMISDomain {
	private String feeParmId;//费用编号
	private String feeParmName;//费用名称
	private String feeKind;//费用类型
	private String feeScenes;//费用场景
	private Double feeMax;//费用最大值
	private Double feeMin;//费用最小值
	private String feeFormId;//费用公式编号
	private String feeSts;//费用状态
	private String opNo;//登记人
	private String txDate;//登记日期
	private String upDate;//更新日期
	
	private String rateType;//费率用途 01 服务费 02 保费 
	private String acquRate;//费率获取方式:1进件 2费率公式配置

	/**
	 * @return 费用编号
	 */
	public String getFeeParmId() {
	 	return feeParmId;
	}
	/**
	 * @设置 费用编号
	 * @param feeParmId
	 */
	public void setFeeParmId(String feeParmId) {
	 	this.feeParmId = feeParmId;
	}
	/**
	 * @return 费用名称
	 */
	public String getFeeParmName() {
	 	return feeParmName;
	}
	/**
	 * @设置 费用名称
	 * @param feeParmName
	 */
	public void setFeeParmName(String feeParmName) {
	 	this.feeParmName = feeParmName;
	}
	/**
	 * @return 费用场景
	 */
	public String getFeeScenes() {
	 	return feeScenes;
	}
	/**
	 * @设置 费用场景
	 * @param feeScenes
	 */
	public void setFeeScenes(String feeScenes) {
	 	this.feeScenes = feeScenes;
	}
	/**
	 * @return 费用最大值
	 */
	public Double getFeeMax() {
	 	return feeMax;
	}
	/**
	 * @设置 费用最大值
	 * @param feeMax
	 */
	public void setFeeMax(Double feeMax) {
	 	this.feeMax = feeMax;
	}
	/**
	 * @return 费用最小值
	 */
	public Double getFeeMin() {
	 	return feeMin;
	}
	/**
	 * @设置 费用最小值
	 * @param feeMin
	 */
	public void setFeeMin(Double feeMin) {
	 	this.feeMin = feeMin;
	}
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
	 * @return 费用状态
	 */
	public String getFeeSts() {
	 	return feeSts;
	}
	/**
	 * @设置 费用状态
	 * @param feeSts
	 */
	public void setFeeSts(String feeSts) {
	 	this.feeSts = feeSts;
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
	public String getFeeKind() {
		return feeKind;
	}
	public void setFeeKind(String feeKind) {
		this.feeKind = feeKind;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getAcquRate() {
		return acquRate;
	}
	public void setAcquRate(String acquRate) {
		this.acquRate = acquRate;
	}
}