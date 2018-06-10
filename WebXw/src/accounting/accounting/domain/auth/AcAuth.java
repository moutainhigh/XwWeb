package accounting.domain.auth;

import accounting.domain.sys.CMISDomain;

/**
* Title: AcAuth.java
* Description:
* @作者 su
* @日期 2018-4-25
* @version：1.0
**/
public class AcAuth  extends CMISDomain  {
	private String authNo;                  //  授权编号
	private String loanNo;                  //  借据号
	private String cifNo;                   //  客户号
	private String cifName;                 //  客户名称
	private double txAmt;                   //  交易金额
	private String curNo;                   //  币种
	private String typ;                     //  业务类型
	private String txBrNo;                  //  交易机构
	private String txDt;                    //  交易日期
	private String finBrNo ;				//	账务机构
	private String txUsrId;                 //  交易柜员号
	private String sts;                     //	授权状态


	/**
	 * @return 返回   授权编号
	 */
	public String getAuthNo() {
		return authNo;
	}
	/**
	 * @设置   授权编号
	 * @param authNo
	 */
	public void setAuthNo(String authNo) {
		this.authNo=authNo;
	}
	/**
	 * @return 返回   借据号
	 */
	public String getLoanNo() {
		return loanNo;
	}
	/**
	 * @设置   借据号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo=loanNo;
	}
	/**
	 * @return 返回   客户号
	 */
	public String getCifNo() {
		return cifNo;
	}
	/**
	 * @设置   客户号
	 * @param cifNo
	 */
	public void setCifNo(String cifNo) {
		this.cifNo=cifNo;
	}
	/**
	 * @return 返回   交易金额
	 */
	public double getTxAmt() {
		return txAmt;
	}
	/**
	 * @设置   交易金额
	 * @param txAmt
	 */
	public void setTxAmt(double txAmt) {
		this.txAmt=txAmt;
	}
	/**
	 * @return 返回   币种
	 */
	public String getCurNo() {
		return curNo;
	}
	/**
	 * @设置   币种
	 * @param curNo
	 */
	public void setCurNo(String curNo) {
		this.curNo=curNo;
	}
	/**
	 * @return 返回   业务类型
	 */
	public String getTyp() {
		return typ;
	}
	/**
	 * @设置   业务类型
	 * @param typ
	 */
	public void setTyp(String typ) {
		this.typ=typ;
	}
	/**
	 * @return 返回   交易机构
	 */
	public String getTxBrNo() {
		return txBrNo;
	}
	/**
	 * @设置   交易机构
	 * @param txBrNo
	 */
	public void setTxBrNo(String txBrNo) {
		this.txBrNo=txBrNo;
	}
	/**
	 * @return 返回   账务机构
	 */
	public String getFinBrNo() {
		return finBrNo;
	}
	/**
	 * @设置   账务机构
	 * @param finBrNo
	 */
	public void setFinBrNo(String finBrNo) {
		this.finBrNo=finBrNo;
	}
	/**
	 * @return 返回   交易日期
	 */
	public String getTxDt() {
		return txDt;
	}
	/**
	 * @设置   交易日期
	 * @param txDt
	 */
	public void setTxDt(String txDt) {
		this.txDt=txDt;
	}
	/**
	 * @return 返回   交易柜员号
	 */
	public String getTxUsrId() {
		return txUsrId;
	}
	/**
	 * @设置   交易柜员号
	 * @param txUsrId
	 */
	public void setTxUsrId(String txUsrId) {
		this.txUsrId=txUsrId;
	}
	/**
	 * @return 返回 	授权状态
	 */
	public String getSts() {
		return sts;
	}
	/**
	 * @设置 	授权状态
	 * @param sts
	 */
	public void setSts(String sts) {
		this.sts=sts;
	}
	public String getCifName() {
		return cifName;
	}
	public void setCifName(String cifName) {
		this.cifName = cifName;
	}
	
}