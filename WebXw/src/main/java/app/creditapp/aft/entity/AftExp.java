package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftExp.java
* Description:
* @version：1.0
**/
public class AftExp extends BaseDomain {
	private String expId;//展期ID
	private String pactId;//合同ID
	private String pactNo;//合同号
	private String brNo;//合作机构
	private String begDate;//展期起始日
	private String endDate;//展期到期日
	private Double expRate;//展期利率
	private Double expAmt;//展期金额
	private String expReason;//展期原因
	private String expSts;//展期状态[01待处理02待展期03已展期04展期失败]
	private String opNo;//操作员
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String traceNo;//主文件流水号
	private String brName;//合作机构名称
	private String opName;//操作员名称

	/**
	 * @return 展期ID
	 */
	public String getExpId() {
	 	return expId;
	}
	/**
	 * @设置 展期ID
	 * @param expId
	 */
	public void setExpId(String expId) {
	 	this.expId = expId;
	}
	/**
	 * @return 合同ID
	 */
	public String getPactId() {
	 	return pactId;
	}
	/**
	 * @设置 合同ID
	 * @param pactId
	 */
	public void setPactId(String pactId) {
	 	this.pactId = pactId;
	}
	/**
	 * @return 合同号
	 */
	public String getPactNo() {
	 	return pactNo;
	}
	/**
	 * @设置 合同号
	 * @param pactNo
	 */
	public void setPactNo(String pactNo) {
	 	this.pactNo = pactNo;
	}
	/**
	 * @return 合作机构
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 展期起始日
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 展期起始日
	 * @param begDate
	 */
	public void setBegDate(String begDate) {
	 	this.begDate = begDate;
	}
	/**
	 * @return 展期到期日
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 展期到期日
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 展期利率
	 */
	public Double getExpRate() {
	 	return expRate;
	}
	/**
	 * @设置 展期利率
	 * @param expRate
	 */
	public void setExpRate(Double expRate) {
	 	this.expRate = expRate;
	}
	/**
	 * @return 展期金额
	 */
	public Double getExpAmt() {
	 	return expAmt;
	}
	/**
	 * @设置 展期金额
	 * @param expAmt
	 */
	public void setExpAmt(Double expAmt) {
	 	this.expAmt = expAmt;
	}
	/**
	 * @return 展期原因
	 */
	public String getExpReason() {
	 	return expReason;
	}
	/**
	 * @设置 展期原因
	 * @param expReason
	 */
	public void setExpReason(String expReason) {
	 	this.expReason = expReason;
	}
	/**
	 * @return 展期状态[01待处理02待展期03已展期04展期失败]
	 */
	public String getExpSts() {
	 	return expSts;
	}
	/**
	 * @设置 展期状态[01待处理02待展期03已展期04展期失败]
	 * @param expSts
	 */
	public void setExpSts(String expSts) {
	 	this.expSts = expSts;
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
	public String getTraceNo() {
		return traceNo;
	}
	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
}