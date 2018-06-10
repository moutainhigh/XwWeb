package app.creditapp.wkf.entity;

import com.dhcc.workflow.identity.app.IAppRole;

public class AppRole implements IAppRole {
	private String roleId;
	private String roleName;
	private String branchId;
	private String level;
	public AppRole() {
	}

	public AppRole(String branchId, String roleId, String roleName) {
		this.branchId = branchId;
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getLevel() {
		// TODO Auto-generated method stub
		return level;
	}
	
	public void setLevel(String level){
		this.level=level;
	}
}
