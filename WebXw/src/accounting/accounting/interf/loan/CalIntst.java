/**
 * 
 */
package accounting.interf.loan;

import accounting.domain.sys.AfferentDomain;

/**   
 *    
 * @项目名称：nxnxnew   
 * @类名称：Intst   
 * @类描述：   
 * 
 *    
 */

public class CalIntst extends AfferentDomain {
	private String loanNo;	    //借据号
	private String setlMode;	//还款模式
	private double remAmt;      //还款金额
	private String fdrpIntstOpt;//利息计算到
	private String fdrpIntBase; //利息计算基础
	
	
	
	/**
	 * 还款本金
	 * @return 还款本金
	 */
	public double getRemAmt() {
		return remAmt;
	}
	/**
	 * 还款本金
	 * @param remAmt
	 */
	public void setRemAmt(double remAmt) {
		this.remAmt = remAmt;
	}
	
	/**
	 * 借据号
	 * @return the loanNo
	 */
	public String getLoanNo() {
		return loanNo;
	}
	/**
	 * 借据号
	 * @param loanNo
	 */
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	/**
	 * 还款模式
	 * @return 还款模式
	 */
	public String getSetlMode() {
		return setlMode;
	}
	/**
	 * 还款模式
	 * @param setlMode
	 */
	public void setSetlMode(String setlMode) {
		this.setlMode = setlMode;
	}
	/**
	 * 提前还款利息计算到
	 * @return 提前还款利息计算到
	 */
	public String getFdrpIntstOpt() {
		return fdrpIntstOpt;
	}
	/**
	 * 提前还款利息计算到
	 * @param fdrpIntstOpt
	 */
	public void setFdrpIntstOpt(String fdrpIntstOpt) {
		this.fdrpIntstOpt = fdrpIntstOpt;
	}
	/**
	 * 提前还款利息计算基础
	 * @return 提前还款利息计算基础
	 */
	public String getFdrpIntBase() {
		return fdrpIntBase;
	}
	/**
	 * 提前还款利息计算基础
	 * @param fdrpIntBase
	 */
	public void setFdrpIntBase(String fdrpIntBase) {
		this.fdrpIntBase = fdrpIntBase;
	}
	
	
}
