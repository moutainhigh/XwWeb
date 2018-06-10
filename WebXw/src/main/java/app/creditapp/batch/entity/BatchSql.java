package app.creditapp.batch.entity;
import app.base.BaseDomain;
/**
* Title: BatchSql.java
* Description:
* @version：1.0
**/
public class BatchSql extends BaseDomain {
	private String node_no;//节点号
	private String sql;//sql语句
	private String sql_desc;//sql描述
	private Integer sql_lev;//优先级
	private String id;//优先级

	/**
	 * @return 节点号
	 */
	public String getNode_no() {
	 	return node_no;
	}
	/**
	 * @设置 节点号
	 * @param node_no
	 */
	public void setNode_no(String node_no) {
	 	this.node_no = node_no;
	}
	/**
	 * @return sql语句
	 */
	public String getSql() {
	 	return sql;
	}
	/**
	 * @设置 sql语句
	 * @param sql
	 */
	public void setSql(String sql) {
	 	this.sql = sql;
	}
	/**
	 * @return sql描述
	 */
	public String getSql_desc() {
	 	return sql_desc;
	}
	/**
	 * @设置 sql描述
	 * @param sql_desc
	 */
	public void setSql_desc(String sql_desc) {
	 	this.sql_desc = sql_desc;
	}
	/**
	 * @return 优先级
	 */
	public Integer getSql_lev() {
	 	return sql_lev;
	}
	/**
	 * @设置 优先级
	 * @param sql_lev
	 */
	public void setSql_lev(Integer sql_lev) {
	 	this.sql_lev = sql_lev;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}