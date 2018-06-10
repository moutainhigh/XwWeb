package app.creditapp.sysConfig.entity;

/**
* Title: TableUpdateRecord.java
* Description:
* @version：1.0
**/

public class SysUpdateRecord   {
	private String  id;//id
	private String  tableName;//table_name
	private String  modifyTime;//modify_time
	private String  modifyRecord;//modify_record
	private String  modifyUserNo;//modify_user_no
	private String 	tableComment;
	private String  user_name;
	

	/**
	 * @return id
	 */
	 public String getId() {
	 	return id;
	 }
	 /**
	 * @设置 id
	 * @param id
	 */
	 public void setId(String id) {
	 	this.id = id;
	 }
	/**
	 * @return table_name
	 */
	 public String getTableName() {
	 	return tableName;
	 }
	 /**
	 * @设置 table_name
	 * @param tableName
	 */
	 public void setTableName(String tableName) {
	 	this.tableName = tableName;
	 }
	/**
	 * @return modify_time
	 */
	 public String getModifyTime() {
	 	return modifyTime;
	 }
	 /**
	 * @设置 modify_time
	 * @param modifyTime
	 */
	 public void setModifyTime(String modifyTime) {
	 	this.modifyTime = modifyTime;
	 }
	/**
	 * @return modify_record
	 */
	 public String getModifyRecord() {
	 	return modifyRecord;
	 }
	 /**
	 * @设置 modify_record
	 * @param modifyRecord
	 */
	 public void setModifyRecord(String modifyRecord) {
	 	this.modifyRecord = modifyRecord;
	 }
	/**
	 * @return modify_user_no
	 */
	 public String getModifyUserNo() {
	 	return modifyUserNo;
	 }
	 /**
	 * @设置 modify_user_no
	 * @param modifyUserNo
	 */
	 public void setModifyUserNo(String modifyUserNo) {
	 	this.modifyUserNo = modifyUserNo;
	 }
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	 
	 
}
