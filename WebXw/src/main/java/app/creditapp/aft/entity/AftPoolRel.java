package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftPoolRel.java
* Description:
* @version：1.0
**/
public class AftPoolRel extends BaseDomain {
	private String poolId;//资产池编号
	private String pactId;//合同ID
	private String pactNo;//合同号
	private String brNo;//合作机构编号
	private String cifNo;//客户号
	private String cifName;//客户名称
	private Double dueAmt;//借据金额
	private Double bal;//借据余额
	private Double intst;//应收欠息
	private String begDate;//起始日期
	private String endDate;//到期日期
	private String fiveSts;//五级分类
	private String poolDate;//入池日期
	private String poolSts;//入池状态
	private String opNo;//操作员
	private String txDate;//登记日期
	private String upDate;//更新日期
	
	//新增
	private String brName;//合作机构名称
	private String fundNo;//资金编号
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
	 * @return 合作机构编号
	 */
	public String getBrNo() {
	 	return brNo;
	}
	/**
	 * @设置 合作机构编号
	 * @param brNo
	 */
	public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	}
	/**
	 * @return 客户号
	 */
	public String getCifNo() {
	 	return cifNo;
	}
	/**
	 * @设置 客户号
	 * @param cifNo
	 */
	public void setCifNo(String cifNo) {
	 	this.cifNo = cifNo;
	}
	/**
	 * @return 客户名称
	 */
	public String getCifName() {
	 	return cifName;
	}
	/**
	 * @设置 客户名称
	 * @param cifName
	 */
	public void setCifName(String cifName) {
	 	this.cifName = cifName;
	}
	/**
	 * @return 借据金额
	 */
	public Double getDueAmt() {
	 	return dueAmt;
	}
	/**
	 * @设置 借据金额
	 * @param dueAmt
	 */
	public void setDueAmt(Double dueAmt) {
	 	this.dueAmt = dueAmt;
	}
	/**
	 * @return 借据余额
	 */
	public Double getBal() {
	 	return bal;
	}
	/**
	 * @设置 借据余额
	 * @param bal
	 */
	public void setBal(Double bal) {
	 	this.bal = bal;
	}
	/**
	 * @return 应收欠息
	 */
	public Double getIntst() {
	 	return intst;
	}
	/**
	 * @设置 应收欠息
	 * @param intst
	 */
	public void setIntst(Double intst) {
	 	this.intst = intst;
	}
	/**
	 * @return 起始日期
	 */
	public String getBegDate() {
	 	return begDate;
	}
	/**
	 * @设置 起始日期
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
	 * @return 五级分类
	 */
	public String getFiveSts() {
	 	return fiveSts;
	}
	/**
	 * @设置 五级分类
	 * @param fiveSts
	 */
	public void setFiveSts(String fiveSts) {
	 	this.fiveSts = fiveSts;
	}
	/**
	 * @return 入池日期
	 */
	public String getPoolDate() {
	 	return poolDate;
	}
	/**
	 * @设置 入池日期
	 * @param poolDate
	 */
	public void setPoolDate(String poolDate) {
	 	this.poolDate = poolDate;
	}
	/**
	 * @return 入池状态
	 */
	public String getPoolSts() {
	 	return poolSts;
	}
	/**
	 * @设置 入池状态
	 * @param poolSts
	 */
	public void setPoolSts(String poolSts) {
	 	this.poolSts = poolSts;
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
	public String getPactId() {
		return pactId;
	}
	public void setPactId(String pactId) {
		this.pactId = pactId;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getBrName() {
		return brName;
	}
	public void setFundNo(String fundNo) {
		this.fundNo = fundNo;
	}
	public String getFundNo() {
		return fundNo;
	}
}