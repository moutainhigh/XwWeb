package accounting.interf.loan;

import java.util.List;

import accounting.domain.loan.AcLoanBackLog;

public class GrantLoanBackResult {
	
	private String uuid;
	private List<AcLoanBackLog> acLoanBackLogList;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<AcLoanBackLog> getAcLoanBackLogList() {
		return acLoanBackLogList;
	}
	public void setAcLoanBackLogList(List<AcLoanBackLog> acLoanBackLogList) {
		this.acLoanBackLogList = acLoanBackLogList;
	}
	
	

}
