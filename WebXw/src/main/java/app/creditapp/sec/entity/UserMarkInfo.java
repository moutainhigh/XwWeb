package app.creditapp.sec.entity;

/**
* Title: UserMarkInfo.java
* Description:
* @version：1.0
**/

public class UserMarkInfo implements java.io.Serializable{
	private String  userId;//用户代码
	private String  passwordUpdateTime;//密码修改时间
	private int  visitTimes;//登陆次数
	private int  loginErrorTimes;//登陆错误次数
	private String  lastSignInTime;//上次登陆时间
	private String  lastSignOutTime;//上次退出时间
	private String  currentSignInTime;//本次登陆时间
	private String  passwordState;//密码状态代码
	private String  passwordMessege;//密码状态内容

	/**
	 * @return 用户代码
	 */
	 public String getUserId() {
	 	return userId;
	 }
	 /**
	 * @设置 用户代码
	 * @param userId
	 */
	 public void setUserId(String userId) {
	 	this.userId = userId;
	 }
	/**
	 * @return 密码修改时间
	 */
	 public String getPasswordUpdateTime() {
	 	return passwordUpdateTime;
	 }
	 /**
	 * @设置 密码修改时间
	 * @param passwordUpdateTime
	 */
	 public void setPasswordUpdateTime(String passwordUpdateTime) {
	 	this.passwordUpdateTime = passwordUpdateTime;
	 }
	/**
	 * @return 登陆次数
	 */
	 public int getVisitTimes() {
	 	return visitTimes;
	 }
	 /**
	 * @设置 登陆次数
	 * @param visitTimes
	 */
	 public void setVisitTimes(int visitTimes) {
	 	this.visitTimes = visitTimes;
	 }
	/**
	 * @return 登陆错误次数
	 */
	 public int getLoginErrorTimes() {
	 	return loginErrorTimes;
	 }
	 /**
	 * @设置 登陆错误次数
	 * @param loginErrorTimes
	 */
	 public void setLoginErrorTimes(int loginErrorTimes) {
	 	this.loginErrorTimes = loginErrorTimes;
	 }
	/**
	 * @return 上次登陆时间
	 */
	 public String getLastSignInTime() {
	 	return lastSignInTime;
	 }
	 /**
	 * @设置 上次登陆时间
	 * @param lastSignInTime
	 */
	 public void setLastSignInTime(String lastSignInTime) {
	 	this.lastSignInTime = lastSignInTime;
	 }
	/**
	 * @return 上次退出时间
	 */
	 public String getLastSignOutTime() {
	 	return lastSignOutTime;
	 }
	 /**
	 * @设置 上次退出时间
	 * @param lastSignOutTime
	 */
	 public void setLastSignOutTime(String lastSignOutTime) {
	 	this.lastSignOutTime = lastSignOutTime;
	 }
	/**
	 * @return 本次登陆时间
	 */
	 public String getCurrentSignInTime() {
	 	return currentSignInTime;
	 }
	 /**
	 * @设置 本次登陆时间
	 * @param currentSignInTime
	 */
	 public void setCurrentSignInTime(String currentSignInTime) {
	 	this.currentSignInTime = currentSignInTime;
	 }
	/**
	 * @return 密码状态代码
	 */
	 public String getPasswordState() {
	 	return passwordState;
	 }
	 /**
	 * @设置 密码状态代码
	 * @param passwordState
	 */
	 public void setPasswordState(String passwordState) {
	 	this.passwordState = passwordState;
	 }
	/**
	 * @return 密码状态内容
	 */
	 public String getPasswordMessege() {
	 	return passwordMessege;
	 }
	 /**
	 * @设置 密码状态内容
	 * @param passwordMessege
	 */
	 public void setPasswordMessege(String passwordMessege) {
	 	this.passwordMessege = passwordMessege;
	 }
}
