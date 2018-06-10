package app.creditapp.wkf.entity;

import com.dhcc.workflow.identity.app.IAppBranch;

public class AppBranch implements IAppBranch {
	private String branchId;
	private String branchName;
	private String parentId;
	private String uptwo;
	
	public AppBranch() {
	}

	public AppBranch(String branchId, String branchName) {
		this.branchId = branchId;
		this.branchName = branchName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUptwo() {
		return uptwo;
	}

	public void setUptwo(String uptwo) {
		this.uptwo = uptwo;
	}

	public String getUpFive() {
		return null;
	}

	public String getUpFour() {
		return null;
	}

	public String getUpOne() {
		return parentId;
	}

	public String getUpThree() {
		return null;
	}

	public String getUpTwo() {
		return null;
	}

}
