package accounting.interf.loan;

import java.util.List;

import accounting.domain.sys.AfferentDomain;
import accounting.domain.fee.AcChrgLog;

public class GrantLoan extends AfferentDomain{
	private String authNo;			//授权编号
	private String loanNo;			//借据号
	private String traceNo;
	private int traceCnt;
	private double feeAmt;
	private List<AcChrgLog> acChrgLogList;
	

	/**授权编号
	 * @return  authNo 授权编号
	 */
	public String getAuthNo() {
		return authNo;
	}

	/**授权编号
	 * @param authNo  授权编号
	 */
	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}
	/**
	 * 手续费
	 * @return 手续费
	 */
	public double getFeeAmt() {
		return feeAmt;
	}
	/**
	 * 手续费
	 * @param feeAmt
	 */
	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}
	/**
	 * 手续费List
	 * @return 手续费List
	 */
	public List<AcChrgLog> getAcChrgLogList() {
		return acChrgLogList;
	}
	/**
	 * 手续费List
	 * @param acChrgLogList
	 */
	public void setAcChrgLogList(List<AcChrgLog> acChrgLogList) {
		this.acChrgLogList = acChrgLogList;
	}

	/**
	 * 借据号
	 * @return 借据号
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

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public int getTraceCnt() {
		return traceCnt;
	}

	public void setTraceCnt(int traceCnt) {
		this.traceCnt = traceCnt;
	}
	
	
}
