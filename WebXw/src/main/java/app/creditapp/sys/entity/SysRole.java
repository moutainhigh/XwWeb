package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysRole.java
* Description:
* @version：1.0
**/
public class SysRole extends BaseDomain {
	private String role_no;//角色编号
	private String role_name;//角色名称
	private String role_type;//角色类型[0业务角色1审批角色2管理员]
	private String role_sts;//角色状态[02无效01有效]
	private String role_pro;//角色性质

	/**
	 * @return 角色编号
	 */
	public String getRole_no() {
	 	return role_no;
	}
	/**
	 * @设置 角色编号
	 * @param role_no
	 */
	public void setRole_no(String role_no) {
	 	this.role_no = role_no;
	}
	/**
	 * @return 角色名称
	 */
	public String getRole_name() {
	 	return role_name;
	}
	/**
	 * @设置 角色名称
	 * @param role_name
	 */
	public void setRole_name(String role_name) {
	 	this.role_name = role_name;
	}
	/**
	 * @return 角色类型[0业务角色1审批角色2管理员]
	 */
	public String getRole_type() {
	 	return role_type;
	}
	/**
	 * @设置 角色类型[0业务角色1审批角色2管理员]
	 * @param role_type
	 */
	public void setRole_type(String role_type) {
	 	this.role_type = role_type;
	}
	/**
	 * @return 角色状态[0无效1有效]
	 */
	public String getRole_sts() {
	 	return role_sts;
	}
	/**
	 * @设置 角色状态[0无效1有效]
	 * @param role_sts
	 */
	public void setRole_sts(String role_sts) {
	 	this.role_sts = role_sts;
	}
	public String getRole_pro() {
		return role_pro;
	}
	public void setRole_pro(String rolePro) {
		role_pro = rolePro;
	}
	
}