package app.creditapp.dev.entity;
import app.base.BaseDomain;
/**
* Title: TreeConf.java
* Description:
* @version：1.0
**/
public class TreeConf extends BaseDomain {
	private String tri_lev;//节点触发级别
	private String select_type;//选择模式[1单选2复选]
	private String sql;//查询SQL语句
	private String scene_desc;//场景描述
	private String scene_id;//场景编号
	private String params;//参数
	private String tri_func;//触发方法

	/**
	 * @return 节点触发级别
	 */
	public String getTri_lev() {
	 	return tri_lev;
	}
	/**
	 * @设置 节点触发级别
	 * @param tri_lev
	 */
	public void setTri_lev(String tri_lev) {
	 	this.tri_lev = tri_lev;
	}
	/**
	 * @return 选择模式[1单选2复选]
	 */
	public String getSelect_type() {
	 	return select_type;
	}
	/**
	 * @设置 选择模式[1单选2复选]
	 * @param select_type
	 */
	public void setSelect_type(String select_type) {
	 	this.select_type = select_type;
	}
	/**
	 * @return 查询SQL语句
	 */
	public String getSql() {
	 	return sql;
	}
	/**
	 * @设置 查询SQL语句
	 * @param sql
	 */
	public void setSql(String sql) {
	 	this.sql = sql;
	}
	/**
	 * @return 场景描述
	 */
	public String getScene_desc() {
	 	return scene_desc;
	}
	/**
	 * @设置 场景描述
	 * @param scene_desc
	 */
	public void setScene_desc(String scene_desc) {
	 	this.scene_desc = scene_desc;
	}
	/**
	 * @return 场景编号
	 */
	public String getScene_id() {
	 	return scene_id;
	}
	/**
	 * @设置 场景编号
	 * @param scene_id
	 */
	public void setScene_id(String scene_id) {
	 	this.scene_id = scene_id;
	}
	/**
	 * @return 参数
	 */
	public String getParams() {
	 	return params;
	}
	/**
	 * @设置 参数
	 * @param params
	 */
	public void setParams(String params) {
	 	this.params = params;
	}
	/**
	 * @return 触发方法
	 */
	public String getTri_func() {
	 	return tri_func;
	}
	/**
	 * @设置 触发方法
	 * @param tri_func
	 */
	public void setTri_func(String tri_func) {
	 	this.tri_func = tri_func;
	}
}