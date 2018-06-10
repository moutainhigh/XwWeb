package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysLoginLog.java
* Description:
* @version：1.0
**/

public class SysLoginLog extends BaseDomain {
	private String  sessionId;//sessionid
	private String  brNo;//机构号
	private String  opNo;//操作员号
	private String  opName;//操作员姓名
	private String  loginDate;//登录日期
	private String  loginTime;//登录时间
	private String  loginIp;//客户端ip地址
	private String  logoutTime;//退出时间
	private String  osName;//客户端操作系统名称
	private String  osVersion;//客户端操作系统版本
	private String  ieName;//客户端浏览器名称
	private String  ieVersion;//客户端浏览器版本

	public SysLoginLog(){
	}
	
	public SysLoginLog(String userinfo){
		String[] a = userinfo.split("\\|");
		sessionId = a[0];
		brNo = a[1];
		opNo = a[2];
		opName = a[3];
		loginDate = a[4];
		loginTime = a[5];
		loginIp = a[6];
		logoutTime = "";
		osName = a[7];
		osVersion = a[8];
		ieName = a[9].substring(0,a[9].indexOf("("));
		ieVersion = a[10].substring(0,a[10].indexOf("("));
	}
	/**
	 * @return sessionid
	 */
	 public String getSessionId() {
	 	return sessionId;
	 }
	 /**
	 * @设置 sessionid
	 * @param sessionId
	 */
	 public void setSessionId(String sessionId) {
	 	this.sessionId = sessionId;
	 }
	/**
	 * @return 机构号
	 */
	 public String getBrNo() {
	 	return brNo;
	 }
	 /**
	 * @设置 机构号
	 * @param brNo
	 */
	 public void setBrNo(String brNo) {
	 	this.brNo = brNo;
	 }
	/**
	 * @return 操作员号
	 */
	 public String getOpNo() {
	 	return opNo;
	 }
	 /**
	 * @设置 操作员号
	 * @param opNo
	 */
	 public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	 }
	/**
	 * @return 操作员姓名
	 */
	 public String getOpName() {
	 	return opName;
	 }
	 /**
	 * @设置 操作员姓名
	 * @param opName
	 */
	 public void setOpName(String opName) {
	 	this.opName = opName;
	 }
	/**
	 * @return 登录日期
	 */
	 public String getLoginDate() {
	 	return loginDate;
	 }
	 /**
	 * @设置 登录日期
	 * @param loginDate
	 */
	 public void setLoginDate(String loginDate) {
	 	this.loginDate = loginDate;
	 }
	/**
	 * @return 登录时间
	 */
	 public String getLoginTime() {
	 	return loginTime;
	 }
	 /**
	 * @设置 登录时间
	 * @param loginTime
	 */
	 public void setLoginTime(String loginTime) {
	 	this.loginTime = loginTime;
	 }
	/**
	 * @return 客户端ip地址
	 */
	 public String getLoginIp() {
	 	return loginIp;
	 }
	 /**
	 * @设置 客户端ip地址
	 * @param loginIp
	 */
	 public void setLoginIp(String loginIp) {
	 	this.loginIp = loginIp;
	 }
	/**
	 * @return 退出时间
	 */
	 public String getLogoutTime() {
	 	return logoutTime;
	 }
	 /**
	 * @设置 退出时间
	 * @param logoutTime
	 */
	 public void setLogoutTime(String logoutTime) {
	 	this.logoutTime = logoutTime;
	 }
	/**
	 * @return 客户端操作系统名称
	 */
	 public String getOsName() {
	 	return osName;
	 }
	 /**
	 * @设置 客户端操作系统名称
	 * @param osName
	 */
	 public void setOsName(String osName) {
	 	this.osName = osName;
	 }
	/**
	 * @return 客户端操作系统版本
	 */
	 public String getOsVersion() {
	 	return osVersion;
	 }
	 /**
	 * @设置 客户端操作系统版本
	 * @param osVersion
	 */
	 public void setOsVersion(String osVersion) {
	 	this.osVersion = osVersion;
	 }
	/**
	 * @return 客户端浏览器名称
	 */
	 public String getIeName() {
	 	return ieName;
	 }
	 /**
	 * @设置 客户端浏览器名称
	 * @param ieName
	 */
	 public void setIeName(String ieName) {
	 	this.ieName = ieName;
	 }
	/**
	 * @return 客户端浏览器版本
	 */
	 public String getIeVersion() {
	 	return ieVersion;
	 }
	 /**
	 * @设置 客户端浏览器版本
	 * @param ieVersion
	 */
	 public void setIeVersion(String ieVersion) {
	 	this.ieVersion = ieVersion;
	 }
}
