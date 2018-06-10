package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: OperatorModelMenu.java
* Description:
* @version：1.0
**/
public class OperatorModelMenu extends BaseDomain {
	private String menu_no;//菜单编号
	private String menu_name;//菜单名称
	private String menu_url;//菜单url
	private String menu_parent;//父菜单编号
	private String menu_lvl;//菜单级别
	private String menu_stats;//菜单状态 启用1，停用0
	private Integer group_seq;//组内序号
	private String cif_type;//所属客户类型
	private String last_url;//上一请求URL地址

	/**
	 * @return 菜单编号
	 */
	public String getMenu_no() {
	 	return menu_no;
	}
	/**
	 * @设置 菜单编号
	 * @param menu_no
	 */
	public void setMenu_no(String menu_no) {
	 	this.menu_no = menu_no;
	}
	/**
	 * @return 菜单名称
	 */
	public String getMenu_name() {
	 	return menu_name;
	}
	/**
	 * @设置 菜单名称
	 * @param menu_name
	 */
	public void setMenu_name(String menu_name) {
	 	this.menu_name = menu_name;
	}
	/**
	 * @return 菜单url
	 */
	public String getMenu_url() {
	 	return menu_url;
	}
	/**
	 * @设置 菜单url
	 * @param menu_url
	 */
	public void setMenu_url(String menu_url) {
	 	this.menu_url = menu_url;
	}
	/**
	 * @return 父菜单编号
	 */
	public String getMenu_parent() {
	 	return menu_parent;
	}
	/**
	 * @设置 父菜单编号
	 * @param menu_parent
	 */
	public void setMenu_parent(String menu_parent) {
	 	this.menu_parent = menu_parent;
	}
	/**
	 * @return 菜单级别
	 */
	public String getMenu_lvl() {
	 	return menu_lvl;
	}
	/**
	 * @设置 菜单级别
	 * @param menu_lvl
	 */
	public void setMenu_lvl(String menu_lvl) {
	 	this.menu_lvl = menu_lvl;
	}
	/**
	 * @return 菜单状态 启用1，停用0
	 */
	public String getMenu_stats() {
	 	return menu_stats;
	}
	/**
	 * @设置 菜单状态 启用1，停用0
	 * @param menu_stats
	 */
	public void setMenu_stats(String menu_stats) {
	 	this.menu_stats = menu_stats;
	}
	/**
	 * @return 组内序号
	 */
	public Integer getGroup_seq() {
	 	return group_seq;
	}
	/**
	 * @设置 组内序号
	 * @param group_seq
	 */
	public void setGroup_seq(Integer group_seq) {
	 	this.group_seq = group_seq;
	}
	/**
	 * @return 所属客户类型
	 */
	public String getCif_type() {
	 	return cif_type;
	}
	/**
	 * @设置 所属客户类型
	 * @param cif_type
	 */
	public void setCif_type(String cif_type) {
	 	this.cif_type = cif_type;
	}
	/**
	 * @return 上一请求URL地址
	 */
	public String getLast_url() {
	 	return last_url;
	}
	/**
	 * @设置 上一请求URL地址
	 * @param last_url
	 */
	public void setLast_url(String last_url) {
	 	this.last_url = last_url;
	}
}