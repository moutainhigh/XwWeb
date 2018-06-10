package app.creditapp.dev.entity;
import app.base.BaseDomain;
/**
* Title: PopParmConf.java
* Description:
* @version：1.0
**/
public class PopParmConf extends BaseDomain {
	
	private String scene_id;//场景编号
	private String col_name;//列表显示名称
	private String sql;//查询列表语句
	private String db_bean_rel;//数据库对应属性关系
	private String scene_desc;//场景描述
	private String query_name;//查询name值
	private String query_disName;//查询显示名称
	private String page_no;//页数
	private String dyn_or;//动态or
	private String dyn_and;//动态and
	private String orderby;//排序
	private String if_checkbox;//是否多选
	private String hidden_col;//隐藏列数
	private String if_query;//是否直接查询

	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String pageNo) {
		page_no = pageNo;
	}
	public String getQuery_name() {
		return query_name;
	}
	public void setQuery_name(String queryName) {
		query_name = queryName;
	}
	public String getQuery_disName() {
		return query_disName;
	}
	public void setQuery_disName(String queryDisName) {
		query_disName = queryDisName;
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
	 * @return 列表显示名称
	 */
	public String getCol_name() {
	 	return col_name;
	}
	/**
	 * @设置 列表显示名称
	 * @param col_name
	 */
	public void setCol_name(String col_name) {
	 	this.col_name = col_name;
	}
	/**
	 * @return 查询列表语句
	 */
	public String getSql() {
	 	return sql;
	}
	/**
	 * @设置 查询列表语句
	 * @param sql
	 */
	public void setSql(String sql) {
	 	this.sql = sql;
	}
	/**
	 * @return 数据库对应属性关系
	 */
	public String getDb_bean_rel() {
	 	return db_bean_rel;
	}
	/**
	 * @设置 数据库对应属性关系
	 * @param db_bean_rel
	 */
	public void setDb_bean_rel(String db_bean_rel) {
	 	this.db_bean_rel = db_bean_rel;
	}
	public String getScene_desc() {
		return scene_desc;
	}
	public void setScene_desc(String scene_desc) {
		this.scene_desc = scene_desc;
	}
	
	public String getDyn_and() {
		return dyn_and;
	}
	public void setDyn_and(String dynAnd) {
		dyn_and = dynAnd;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getDyn_or() {
		return dyn_or;
	}
	public String getIf_checkbox() {
		return if_checkbox;
	}
	public void setIf_checkbox(String ifCheckbox) {
		if_checkbox = ifCheckbox;
	}
	public String getIf_query() {
		return if_query;
	}
	public void setIf_query(String ifQuery) {
		if_query = ifQuery;
	}
	public void setDyn_or(String dynOr) {
		dyn_or = dynOr;
	}
	public String getHidden_col() {
		return hidden_col;
	}
	public void setHidden_col(String hiddenCol) {
		hidden_col = hiddenCol;
	}
}