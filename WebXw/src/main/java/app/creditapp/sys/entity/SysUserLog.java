package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SysUserLog.java
* Description:
* @version：1.0
**/
public class SysUserLog extends BaseDomain {
	private String userId;//操作员号
	private String loginTime;//登录时间
	private String logoutTime;//退出时间
	private String lastTime;//上次登录时间
	private Integer loginCnt;//登录次数
	private Integer passError;//密码错误次数
	private String loginIp;//登录IP
	private String sessionId;//SESSIONID
	private String clientInfo;//终端信息
	//新增字段
	private String userName;//操作员名称
	/**
	 * @return 操作员号
	 */
	public String getUserId() {
	 	return userId;
	}
	/**
	 * @设置 操作员号
	 * @param userId
	 */
	public void setUserId(String userId) {
	 	this.userId = userId;
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
	 * @return 上次登录时间
	 */
	public String getLastTime() {
	 	return lastTime;
	}
	/**
	 * @设置 上次登录时间
	 * @param lastTime
	 */
	public void setLastTime(String lastTime) {
	 	this.lastTime = lastTime;
	}
	/**
	 * @return 登录次数
	 */
	public Integer getLoginCnt() {
	 	return loginCnt;
	}
	/**
	 * @设置 登录次数
	 * @param loginCnt
	 */
	public void setLoginCnt(Integer loginCnt) {
	 	this.loginCnt = loginCnt;
	}
	/**
	 * @return 密码错误次数
	 */
	public Integer getPassError() {
	 	return passError;
	}
	/**
	 * @设置 密码错误次数
	 * @param passError
	 */
	public void setPassError(Integer passError) {
	 	this.passError = passError;
	}
	/**
	 * @return 登录IP
	 */
	public String getLoginIp() {
	 	return loginIp;
	}
	/**
	 * @设置 登录IP
	 * @param loginIp
	 */
	public void setLoginIp(String loginIp) {
	 	this.loginIp = loginIp;
	}
	/**
	 * @return SESSIONID
	 */
	public String getSessionId() {
	 	return sessionId;
	}
	/**
	 * @设置 SESSIONID
	 * @param sessionId
	 */
	public void setSessionId(String sessionId) {
	 	this.sessionId = sessionId;
	}
	/**
	 * @return 终端信息
	 */
	public String getClientInfo() {
	 	return clientInfo;
	}
	/**
	 * @设置 终端信息
	 * @param clientInfo
	 */
	public void setClientInfo(String clientInfo) {
	 	this.clientInfo = clientInfo;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
}