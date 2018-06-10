package app.creditapp.aft.manager;

public class ManagerEntity {
	private String projNo;
	private String relId;
	private ParmRewType parmRewType;
	
	public String getRelId() {
		return relId;
	}
	public void setRelId(String relId) {
		this.relId = relId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public ParmRewType getParmRewType() {
		return parmRewType;
	}
	public void setParmRewType(ParmRewType parmRewType) {
		this.parmRewType = parmRewType;
	}
}
