package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftAssetPool.java
* Description:
* @version：1.0
**/
public class AftAssetPool extends BaseDomain {
	private String poolId;//资产池编号
	private String poolName;//资产池名称
	private String poolDesc;//资产池概况
	private Integer poolCnt;//关联贷款数
	private Double poolAmt;//资产池总额
	private Double totalAmt;//本金总额
	private String begDate;//生效日期
	private String endDate;//到期日期
	private String poolSts;//资产池状态[01-未入池02-已入池]
	private String opNo;//登记人
	private String txDate;//登记日期
	private String upDate;//更新日期
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	/**
	 * @return 资产池编号
	 */
	public String getPoolId() {
	 	return poolId;
	}
	/**
	 * @设置 资产池编号
	 * @param poolId
	 */
	public void setPoolId(String poolId) {
	 	this.poolId = poolId;
	}
	/**
	 * @return 资产池名称
	 */
	public String getPoolName() {
	 	return poolName;
	}
	/**
	 * @设置 资产池名称
	 * @param poolName
	 */
	public void setPoolName(String poolName) {
	 	this.poolName = poolName;
	}
	/**
	 * @return 资产池概况
	 */
	public String getPoolDesc() {
	 	return poolDesc;
	}
	/**
	 * @设置 资产池概况
	 * @param poolDesc
	 */
	public void setPoolDesc(String poolDesc) {
	 	this.poolDesc = poolDesc;
	}
	/**
	 * @return 关联贷款数
	 */
	public Integer getPoolCnt() {
	 	return poolCnt;
	}
	/**
	 * @设置 关联贷款数
	 * @param poolCnt
	 */
	public void setPoolCnt(Integer poolCnt) {
	 	this.poolCnt = poolCnt;
	}
	/**
	 * @return 资产池总额
	 */
	public Double getPoolAmt() {
	 	return poolAmt;
	}
	/**
	 * @设置 资产池总额
	 * @param poolAmt
	 */
	public void setPoolAmt(Double poolAmt) {
	 	this.poolAmt = poolAmt;
	}
	/**
	 * @return 本金总额
	 */
	public Double getTotalAmt() {
	 	return totalAmt;
	}
	/**
	 * @设置 本金总额
	 * @param totalAmt
	 */
	public void setTotalAmt(Double totalAmt) {
	 	this.totalAmt = totalAmt;
	}
	/**
	 * @return 生效日期
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 生效日期
	 * @param begDate
	 */
	public void setBegDate(String begDate) {
	 	this.begDate = begDate;
	}
	/**
	 * @return 到期日期
	 */
	public String getEndDate() {
	 	return endDate;
	}
	/**
	 * @设置 到期日期
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
	 	this.endDate = endDate;
	}
	/**
	 * @return 资产池状态[01-未入池02-已入池]
	 */
	public String getPoolSts() {
	 	return poolSts;
	}
	/**
	 * @设置 资产池状态[01-未入池02-已入池]
	 * @param poolSts
	 */
	public void setPoolSts(String poolSts) {
	 	this.poolSts = poolSts;
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
}