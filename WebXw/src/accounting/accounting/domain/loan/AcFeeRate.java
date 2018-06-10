package accounting.domain.loan;
import app.base.BaseDomain;
/**
* Title: AcFeeRate.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcFeeRate extends accounting.domain.sys.CMISDomain {
	private String feeId;//费率编号
	private String feeName;//费率名称
	private Integer minMon;//期限下线
	private Integer maxMon;//期限上限
	private Double feeRate;//费率
	private String frId;//费率定义编号


	/**
	 * @return 费率编号
	 */
	public String getFeeId() {
	 	return feeId;
	}
	/**
	 * @设置 费率编号
	 * @param feeId
	 */
	public void setFeeId(String feeId) {
	 	this.feeId = feeId;
	}
	/**
	 * @return 费率名称
	 */
	public String getFeeName() {
	 	return feeName;
	}
	/**
	 * @设置 费率名称
	 * @param feeName
	 */
	public void setFeeName(String feeName) {
	 	this.feeName = feeName;
	}
	/**
	 * @return 期限下线
	 */
	public Integer getMinMon() {
	 	return minMon;
	}
	/**
	 * @设置 期限下线
	 * @param minMon
	 */
	public void setMinMon(Integer minMon) {
	 	this.minMon = minMon;
	}
	/**
	 * @return 期限上限
	 */
	public Integer getMaxMon() {
	 	return maxMon;
	}
	/**
	 * @设置 期限上限
	 * @param maxMon
	 */
	public void setMaxMon(Integer maxMon) {
	 	this.maxMon = maxMon;
	}
	/**
	 * @return 费率
	 */
	public Double getFeeRate() {
	 	return feeRate;
	}
	/**
	 * @设置 费率
	 * @param feeRate
	 */
	public void setFeeRate(Double feeRate) {
	 	this.feeRate = feeRate;
	}
	public String getFrId() {
		return frId;
	}
	public void setFrId(String frId) {
		this.frId = frId;
	}
}