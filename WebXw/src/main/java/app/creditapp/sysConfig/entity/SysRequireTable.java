package app.creditapp.sysConfig.entity;

/**
* Title: RequireLogTable.java
* Description:
* @version：1.0
**/

public class SysRequireTable   {
	private String  tableCode;//表
	private String  tableName;//表名称

	/**
	 * @return 表
	 */
	 public String getTableCode() {
	 	return tableCode;
	 }
	 /**
	 * @设置 表
	 * @param tableCode
	 */
	 public void setTableCode(String tableCode) {
	 	this.tableCode = tableCode;
	 }
	/**
	 * @return 表名称
	 */
	 public String getTableName() {
	 	return tableName;
	 }
	 /**
	 * @设置 表名称
	 * @param tableName
	 */
	 public void setTableName(String tableName) {
	 	this.tableName = tableName;
	 }
}
