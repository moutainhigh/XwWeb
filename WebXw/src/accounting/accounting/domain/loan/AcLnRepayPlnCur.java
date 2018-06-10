package accounting.domain.loan;
/**
* Title: AcLnPayPlnCur.java
* Description:
* @作者 su
* @日期 2018-3-20
* @version：1.0
**/
public class AcLnRepayPlnCur extends accounting.domain.sys.CMISDomain {
	private String loanNo;                  //借据号
	private String pactNo;					//合同号
	private String brNo;					//机构号
	private double ttlAmt;                  //总金额
	private int    perdNo;                  //当前期数
	private double prcpAmt;                 //本期金额
	private double normInt;               //当前期次应还利息
	private String wrkDt;                   //登记日期
	private String payDt;                   //应还日期
	private String endDt;                   //到期日期
	private double repayPrcpAmt;			//已还本金
	private double repayNormInt;			//已还利息
	private Double wvPrcpAmt;//减免本金
	private Double wvNormInt;//减免利息


	/**
	 * @return 返回 借据号
	 */
	public String getLoanNo() {
		return loanNo;
	}
	/**
	 * @设置 借据号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo=loanNo;
	}
	/**
	 * @return 返回 总金额
	 */
	public double getTtlAmt() {
		return ttlAmt;
	}
	/**
	 * @设置 总金额
	 * @param ttlAmt
	 */
	public void setTtlAmt(double ttlAmt) {
		this.ttlAmt=ttlAmt;
	}
	/**
	 * @return 返回 当前期数
	 */
	public int getPerdNo() {
		return perdNo;
	}
	/**
	 * @设置 当前期数
	 * @param perdNo
	 */
	public void setPerdNo(int perdNo) {
		this.perdNo=perdNo;
	}
	/**
	 * @return 返回 本期金额
	 */
	public double getPrcpAmt() {
		return prcpAmt;
	}
	/**
	 * @设置 本期金额
	 * @param prcpAmt
	 */
	public void setPrcpAmt(double prcpAmt) {
		this.prcpAmt=prcpAmt;
	}
	/**
	 * @return 返回 当前期次应还利息
	 */
	public double getNormInt() {
		return normInt;
	}
	/**
	 * @设置 当前期次应还利息
	 * @param normIntst
	 */
	public void setNormInt(double normInt) {
		this.normInt=normInt;
	}
	
	
	/**
	 * @return 返回 登记日期
	 */
	public String getWrkDt() {
		return wrkDt;
	}
	/**
	 * @设置 登记日期
	 * @param wrkDt
	 */
	public void setWrkDt(String wrkDt) {
		this.wrkDt=wrkDt;
	}
	/**
	 * @return 返回 开始归还日期
	 */
	public String getPayDt() {
		return payDt;
	}
	/**
	 * @设置 开始归还日期
	 * @param begDt
	 */
	public void setPayDt(String payDt) {
		this.payDt=payDt;
	}
	/**
	 * @return 返回 到期日期
	 */
	public String getEndDt() {
		return endDt;
	}
	/**
	 * @设置 到期日期
	 * @param endDt
	 */
	public void setEndDt(String endDt) {
		this.endDt=endDt;
	}
	/**
	 * 已还本金
	 * @return	已还本金
	 */
	public double getRepayPrcpAmt() {
		return repayPrcpAmt;
	}
	/**
	 * 已还本金
	 * @param repayPrcpAmt
	 */
	public void setRepayPrcpAmt(double repayPrcpAmt) {
		this.repayPrcpAmt = repayPrcpAmt;
	}
	/**
	 * 已还利息
	 * @return 已还利息
	 */
	public double getRepayNormInt() {
		return repayNormInt;
	}
	/**
	 * 已还利息
	 * @param repayNormInt
	 */
	public void setRepayNormInt(double repayNormInt) {
		this.repayNormInt = repayNormInt;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getPactNo() {
		return pactNo;
	}
	public void setPactNo(String pactNo) {
		this.pactNo = pactNo;
	}
	public Double getWvPrcpAmt() {
		return wvPrcpAmt;
	}
	public void setWvPrcpAmt(Double wvPrcpAmt) {
		this.wvPrcpAmt = wvPrcpAmt;
	}
	public Double getWvNormInt() {
		return wvNormInt;
	}
	public void setWvNormInt(Double wvNormInt) {
		this.wvNormInt = wvNormInt;
	}
	
}