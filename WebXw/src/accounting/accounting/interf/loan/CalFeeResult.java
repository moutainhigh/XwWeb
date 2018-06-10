package accounting.interf.loan;

import java.util.List;

import accounting.domain.fee.AcChrgLog;

public class CalFeeResult {

	private double feeAmt;
	private List<AcChrgLog> acChrgLogList;
	private int traceCnt;
	
	
	public double getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(double feeAmt) {
		this.feeAmt = feeAmt;
	}
	public List<AcChrgLog> getAcChrgLogList() {
		return acChrgLogList;
	}
	public void setAcChrgLogList(List<AcChrgLog> acChrgLogList) {
		this.acChrgLogList = acChrgLogList;
	}
	public int getTraceCnt() {
		return traceCnt;
	}
	public void setTraceCnt(int traceCnt) {
		this.traceCnt = traceCnt;
	}
	
	
	
}
