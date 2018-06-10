package accounting.domain.biz;
import app.base.BaseDomain;
/**
* Title: AccReplanParm.java
* Description:
* @作者 su
* @日期 2018-4-25
* @version：1.0
**/
public class AcReplanParm extends accounting.domain.sys.CMISDomain {
	private String replanId;//方案编号
	private String replanName;//方案名称
	private String termType;//还款周期
	private Integer frequency;//还款频率
	private String extendNext;//首期不足延至
	private Integer secNo;//分段数
	private String opNo;//操作员
	private String brNo;//机构号
	private String upDate;//更新时间
	private String replanSts;//方案状态
	private String ifLoanInt;//是否放款日扣息
	private String intType;//整数化方法
	private Integer intUnit;//整数化单位类型
	private String holidIfExt;//节假日是否顺延

	public String getReplanSts() {
		return replanSts;
	}
	public void setReplanSts(String replanSts) {
		this.replanSts = replanSts;
	}
	/**
	 * @return 方案编号
	 */
	public String getReplanId() {
	 	return replanId;
	}
	/**
	 * @设置 方案编号
	 * @param replanId
	 */
	public void setReplanId(String replanId) {
	 	this.replanId = replanId;
	}
	/**
	 * @return 方案名称
	 */
	public String getReplanName() {
	 	return replanName;
	}
	/**
	 * @设置 方案名称
	 * @param replanName
	 */
	public void setReplanName(String replanName) {
	 	this.replanName = replanName;
	}
	/**
	 * @return 还款周期
	 */
	public String getTermType() {
	 	return termType;
	}
	/**
	 * @设置 还款周期
	 * @param termType
	 */
	public void setTermType(String termType) {
	 	this.termType = termType;
	}
	/**
	 * @return 还款频率
	 */
	public Integer getFrequency() {
	 	return frequency;
	}
	/**
	 * @设置 还款频率
	 * @param frequency
	 */
	public void setFrequency(Integer frequency) {
	 	this.frequency = frequency;
	}
	/**
	 * @return 首期不足延至
	 */
	public String getExtendNext() {
	 	return extendNext;
	}
	/**
	 * @设置 首期不足延至
	 * @param extendNext
	 */
	public void setExtendNext(String extendNext) {
	 	this.extendNext = extendNext;
	}
	/**
	 * @return 分段数
	 */
	public Integer getSecNo() {
	 	return secNo;
	}
	/**
	 * @设置 分段数
	 * @param secNo
	 */
	public void setSecNo(Integer secNo) {
	 	this.secNo = secNo;
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
	public String getIfLoanInt() {
		return ifLoanInt;
	}
	public void setIfLoanInt(String ifLoanInt) {
		this.ifLoanInt = ifLoanInt;
	}
	public String getIntType() {
		return intType;
	}
	public void setIntType(String intType) {
		this.intType = intType;
	}
	public Integer getIntUnit() {
		return intUnit;
	}
	public void setIntUnit(Integer intUnit) {
		this.intUnit = intUnit;
	}
	public String getHolidIfExt() {
		return holidIfExt;
	}
	public void setHolidIfExt(String holidIfExt) {
		this.holidIfExt = holidIfExt;
	}
}