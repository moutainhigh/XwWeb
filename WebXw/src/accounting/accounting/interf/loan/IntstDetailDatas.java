package accounting.interf.loan;

import accounting.domain.sys.AfferentDomain ;

public class IntstDetailDatas extends AfferentDomain {
	
	//主文件直接取值
	private double prcpAmt ;								//本金
	private double normInt ;								//利息
	private double fineInt ;								//罚息
	
	private double repayAmt;						//收到还款总金额
	
	private double curInt;							//当期利息
	private double remPrcpAmt;						//提前还本金额
	private double curPrcpAmt;	//还当期金额
	
	private double feeAmt;							//费用
	private double loFeeAmt;						//欠费
	
	
	/**
	 * @return feeAmt
	 */
	public double getFeeAmt() {
		return feeAmt;
	}
	/**
	 * @param feeAmt
	 */
	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}

	/**
	 * @return  本金
	 */
	public double getPrcpAmt() {
		return prcpAmt ;
	}
	/**
	 * @设置  本金
	 * @param 本金
	 */
	
	public void setPrcpAmt(double prcpAmt) {
		this.prcpAmt = prcpAmt ;
	}

	/**
	 * @return  收到还款总金额
	 */
	public double getRepayAmt() {
		return repayAmt;
	}
	/**
	 * @设置  收到还款总金额
	 * @param 收到还款总金额 
	 */
	
	public void setRepayAmt(double repayAmt) {
		this.repayAmt = repayAmt;
	}
	/**
	 * @return  当期利息
	 */
	public double getCurInt() {
		return curInt;
	}
	/**
	 * @设置 当期利息
	 * @param 当期利息
	 */
	
	public void setCurInt(double curInt) {
		this.curInt = curInt;
	}
	/**
	 * @return  提前还本金额
	 */
	public double getRemPrcpAmt() {
		return remPrcpAmt;
	}
	/**
	 * @设置  提前还本金额
	 * @param 提前还本金额 
	 */
	
	public void setRemPrcpAmt(double remPrcpAmt) {
		this.remPrcpAmt = remPrcpAmt;
	}
	public double getFineInt() {
		return fineInt;
	}
	public void setFineInt(double fineInt) {
		this.fineInt = fineInt;
	}
	public double getNormInt() {
		return normInt;
	}
	public void setNormInt(double normInt) {
		this.normInt = normInt;
	}
	public double getLoFeeAmt() {
		return loFeeAmt;
	}
	public void setLoFeeAmt(double loFeeAmt) {
		this.loFeeAmt = loFeeAmt;
	}
	public double getCurPrcpAmt() {
		return curPrcpAmt;
	}
	public void setCurPrcpAmt(double curPrcpAmt) {
		this.curPrcpAmt = curPrcpAmt;
	}
	
	
	
}
