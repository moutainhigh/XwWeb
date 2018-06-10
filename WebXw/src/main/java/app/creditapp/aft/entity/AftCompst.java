package app.creditapp.aft.entity;
import app.base.BaseDomain;
/**
* Title: AftCompst.java
* Description:
* @version：1.0
**/
public class AftCompst extends accounting.domain.sys.CMISDomain {
	private String compstId;//代偿编号
	private String loanNo;//借据号
	private String pactNo;//合同号
	private String brNo;//机构号
	private String projNo;//项目编号
	private String cifNo;//客户号
	private String cifName;//客户名
	private Integer perdNo;//期次号
	private Double prcpAmt;//应收本金
	private Double normInt;//应收利息
	private Double fineInt;//应收罚息
	private Double repayPrcpAmt;//实收本金
	private Double repayNormInt;//实收利息
	private Double repayFineInt;//实收罚息
	private String txDate;//登记日期
	private String txTime;//登记时间
	private String compstSts;//代偿状态[01-待代偿,02-已代偿]
	private String lstcpstDt;//上次代偿日
	private String compstDt;//代偿日期
	
	private String prdtNo;//产品号
	private String prdtName;//产品名称
	private String projName; //项目名称
	private String loginid;//登录人员
	
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	/**
	 * @return 代偿编号
	 */
	public String getCompstId() {
	 	return compstId;
	}
	/**
	 * @设置 代偿编号
	 * @param compstId
	 */
	public void setCompstId(String compstId) {
	 	this.compstId = compstId;
	}
	/**
	 * @return 借据号
	 */
	public String getLoanNo() {
	 	return loanNo;
	}
	/**
	 * @设置 借据号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
	 	this.loanNo = loanNo;
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
	 * @return 客户名
	 */
	public String getCifName() {
	 	return cifName;
	}
	/**
	 * @设置 客户名
	 * @param cifName
	 */
	public void setCifName(String cifName) {
	 	this.cifName = cifName;
	}
	/**
	 * @return 期次号
	 */
	public Integer getPerdNo() {
	 	return perdNo;
	}
	/**
	 * @设置 期次号
	 * @param perdNo
	 */
	public void setPerdNo(Integer perdNo) {
	 	this.perdNo = perdNo;
	}
	/**
	 * @return 应收本金
	 */
	public Double getPrcpAmt() {
	 	return prcpAmt;
	}
	/**
	 * @设置 应收本金
	 * @param prcpAmt
	 */
	public void setPrcpAmt(Double prcpAmt) {
	 	this.prcpAmt = prcpAmt;
	}
	/**
	 * @return 应收利息
	 */
	public Double getNormInt() {
	 	return normInt;
	}
	/**
	 * @设置 应收利息
	 * @param normInt
	 */
	public void setNormInt(Double normInt) {
	 	this.normInt = normInt;
	}
	/**
	 * @return 应收罚息
	 */
	public Double getFineInt() {
	 	return fineInt;
	}
	/**
	 * @设置 应收罚息
	 * @param fineInt
	 */
	public void setFineInt(Double fineInt) {
	 	this.fineInt = fineInt;
	}
	/**
	 * @return 实收本金
	 */
	public Double getRepayPrcpAmt() {
	 	return repayPrcpAmt;
	}
	/**
	 * @设置 实收本金
	 * @param repayPrcpAmt
	 */
	public void setRepayPrcpAmt(Double repayPrcpAmt) {
	 	this.repayPrcpAmt = repayPrcpAmt;
	}
	/**
	 * @return 实收利息
	 */
	public Double getRepayNormInt() {
	 	return repayNormInt;
	}
	/**
	 * @设置 实收利息
	 * @param repayNormInt
	 */
	public void setRepayNormInt(Double repayNormInt) {
	 	this.repayNormInt = repayNormInt;
	}
	/**
	 * @return 实收罚息
	 */
	public Double getRepayFineInt() {
	 	return repayFineInt;
	}
	/**
	 * @设置 实收罚息
	 * @param repayFineInt
	 */
	public void setRepayFineInt(Double repayFineInt) {
	 	this.repayFineInt = repayFineInt;
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
	 * @return 登记时间
	 */
	public String getTxTime() {
	 	return txTime;
	}
	/**
	 * @设置 登记时间
	 * @param txTime
	 */
	public void setTxTime(String txTime) {
	 	this.txTime = txTime;
	}
	/**
	 * @return 代偿状态[01-待代偿,02-已代偿]
	 */
	public String getCompstSts() {
	 	return compstSts;
	}
	/**
	 * @设置 代偿状态[01-待代偿,02-已代偿]
	 * @param compstSts
	 */
	public void setCompstSts(String compstSts) {
	 	this.compstSts = compstSts;
	}
	/**
	 * @return 代偿日期
	 */
	public String getCompstDt() {
	 	return compstDt;
	}
	/**
	 * @设置 代偿日期
	 * @param compstDt
	 */
	public void setCompstDt(String compstDt) {
	 	this.compstDt = compstDt;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getPrdtNo() {
		return prdtNo;
	}
	public void setPrdtNo(String prdtNo) {
		this.prdtNo = prdtNo;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getLstcpstDt() {
		return lstcpstDt;
	}
	public void setLstcpstDt(String lstcpstDt) {
		this.lstcpstDt = lstcpstDt;
	}
}