package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysLog.java
* Description:
* @version：1.0
**/

public class SysLog extends BaseDomain {
	private String  logId;//记录ID
	private String  opId;//操作主键
	private String  opDesc;//操作描述
	private String  opClass;//日志类名、方法名
	private String  logTime;//日志时间
	private String  opNo;//操作员号
	private String  logDate;//日志日期
	//新增字段
	private String userName;//操作员名称
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	public String getOpDesc() {
		return opDesc;
	}
	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}
	public String getOpClass() {
		return opClass;
	}
	public void setOpClass(String opClass) {
		this.opClass = opClass;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getOpNo() {
		return opNo;
	}
	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
}
