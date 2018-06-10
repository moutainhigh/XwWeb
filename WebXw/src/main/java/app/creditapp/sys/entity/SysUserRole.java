package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysUserRel.java
* Description:
* @version：1.0
**/
public class SysUserRole extends BaseDomain {
	private String rec_id;//关系ID[系统自动生成]
	private String user_no;//用户编号
	private String role_no;//角色编号

	/**
	 * @return 关系ID[系统自动生成]
	 */
	public String getRec_id() {
	 	return rec_id;
	}
	/**
	 * @设置 关系ID[系统自动生成]
	 * @param rel_id
	 */
	public void setRec_id(String rec_id) {
	 	this.rec_id = rec_id;
	}
	/**
	 * @return 登录账号
	 */
	public String getUser_no() {
	 	return user_no;
	}
	/**
	 * @设置 登录账号
	 * @param login_id
	 */
	public void setUser_no(String user_no) {
	 	this.user_no = user_no;
	}
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
	
	
}