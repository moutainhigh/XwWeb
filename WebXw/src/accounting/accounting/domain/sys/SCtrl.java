package accounting.domain.sys;

public class SCtrl {
	
	private String curPrcsDt;
	private String nextPrcsDt;
	private String lastPrcsDt;
	private String sysSts;
	private String newAccInd;
	
	
	
	
	public String getNewAccInd() {
		return newAccInd;
	}
	public void setNewAccInd(String newAccInd) {
		this.newAccInd = newAccInd;
	}
	public String getSysSts() {
		return sysSts;
	}
	public void setSysSts(String sysSts) {
		this.sysSts = sysSts;
	}
	public String getCurPrcsDt() {
		return curPrcsDt;
	}
	public void setCurPrcsDt(String curPrcsDt) {
		this.curPrcsDt = curPrcsDt;
	}
	public String getNextPrcsDt() {
		return nextPrcsDt;
	}
	public void setNextPrcsDt(String nextPrcsDt) {
		this.nextPrcsDt = nextPrcsDt;
	}
	public String getLastPrcsDt() {
		return lastPrcsDt;
	}
	public void setLastPrcsDt(String lastPrcsDt) {
		this.lastPrcsDt = lastPrcsDt;
	}
	
}
