package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysRoleMenu.java
* Description:
* @version：1.0
**/
public class SysRoleMenu extends BaseDomain {
	private String rec_id;//主键
	private String role_no;//角色号
	private String menu_no;//菜单号

	/**
	 * @return 主键
	 */
	public String getRec_id() {
	 	return rec_id;
	}
	/**
	 * @设置 主键
	 * @param id
	 */
	public void setRec_id(String rec_id) {
	 	this.rec_id = rec_id;
	}
	/**
	 * @return 角色号
	 */
	public String getRole_no() {
	 	return role_no;
	}
	/**
	 * @设置 角色号
	 * @param role_no
	 */
	public void setRole_no(String role_no) {
	 	this.role_no = role_no;
	}
	/**
	 * @return 菜单号
	 */
	public String getMenu_no() {
	 	return menu_no;
	}
	/**
	 * @设置 菜单号
	 * @param menu_no
	 */
	public void setMenu_no(String menu_no) {
	 	this.menu_no = menu_no;
	}
}