package accounting.domain.biz;

import java.util.Map;

/**
 * 还款交易中用于保存方法间传递数据的对象
 * 
 *
 */
public class LoanRepay {

	private Map<String, Double> dcMap ; 	//<金额类型， 金额>
	private double remainAmt ;		//可扣款金额
	private double repayedAmt ;		//当次总扣款金额
	private boolean batchInd ;		//日终调用标识
	
	/**
	 * 
	 * @return <金额类型， 金额>
	 */
	public Map<String, Double> getDcMap() {
		return dcMap;
	}
	
	/**
	 * 设置 <金额类型， 金额>
	 * @param dcMap
	 */
	public void setDcMap(Map<String, Double> dcMap) {
		this.dcMap = dcMap;
	}
	
	/**
	 * @return  可扣款金额
	 */
	public double getRemainAmt() {
		return remainAmt;
	}
	
	/**
	 * 
	 * @param remainAmt 可扣款金额
	 */
	public void setRemainAmt(double remainAmt) {
		this.remainAmt = remainAmt;
	}

	/**
	 * @return  当次总还款金额
	 */
	public double getRepayedAmt() {
		return repayedAmt;
	}

	/**
	 * 
	 * @param remainAmt 当次总还款金额
	 */
	public void setRepayedAmt(double repayedAmt) {
		this.repayedAmt = repayedAmt;
	}

	/**
	 * 
	 * @return 日终调用标识
	 */
	public boolean getBatchInd() {
		return batchInd;
	}

	/**
	 * 
	 * @param batchInd 日终调用标识
	 */
	public void setBatchInd(boolean batchInd) {
		this.batchInd = batchInd;
	}
	
}
