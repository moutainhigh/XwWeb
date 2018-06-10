package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: DailyEvent.java
* Description:
* @version：1.0
**/
public class DailyEvent extends BaseDomain {
	private String eventId;//事件ID，唯一
	private String title;//事件内容
	private String startTime;//开始时间（精确到分）
	private String endTime;//结束时间（精确到分）
	private String allDay;//是否为全天事件（0-否，1-是)
	private Integer repeat;//间隔天数
	private String repeatEndDate;//重复事件截止时间
	private String impLevel;//提醒类型
	private String url;//点击URL
	private String seId;//关联的预设任务ID
	private String taskId;//关联的策略ID
	private String eventdesc;//关联的策略ID
	private String argumentsStr;//参数集合
	
	private String userId;//关联的操作人
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getArgumentsStr() {
		return argumentsStr;
	}
	public void setArgumentsStr(String argumentsStr) {
		this.argumentsStr = argumentsStr;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getSeId() {
		return seId;
	}
	public void setSeId(String seId) {
		this.seId = seId;
	}
	/**
	 * @return 事件ID，唯一
	 */
	public String getEventId() {
	 	return eventId;
	}
	/**
	 * @设置 事件ID，唯一
	 * @param eventId
	 */
	public void setEventId(String eventId) {
	 	this.eventId = eventId;
	}
	/**
	 * @return 事件内容
	 */
	public String getTitle() {
	 	return title;
	}
	/**
	 * @设置 事件内容
	 * @param title
	 */
	public void setTitle(String title) {
	 	this.title = title;
	}
	/**
	 * @return 开始时间（精确到分）
	 */
	public String getStartTime() {
	 	return startTime;
	}
	/**
	 * @设置 开始时间（精确到分）
	 * @param startTime
	 */
	public void setStartTime(String startTime) {
	 	this.startTime = startTime;
	}
	/**
	 * @return 结束时间（精确到分）
	 */
	public String getEndTime() {
	 	return endTime;
	}
	/**
	 * @设置 结束时间（精确到分）
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
	 	this.endTime = endTime;
	}
	/**
	 * @return 是否为全天事件（0-否，1-是)
	 */
	public String getAllDay() {
	 	return allDay;
	}
	/**
	 * @设置 是否为全天事件（0-否，1-是)
	 * @param allDay
	 */
	public void setAllDay(String allDay) {
	 	this.allDay = allDay;
	}
	/**
	 * @return 间隔天数
	 */
	public Integer getRepeat() {
	 	return repeat;
	}
	/**
	 * @设置 间隔天数
	 * @param repeat
	 */
	public void setRepeat(Integer repeat) {
	 	this.repeat = repeat;
	}
	/**
	 * @return 重复事件截止时间
	 */
	public String getRepeatEndDate() {
	 	return repeatEndDate;
	}
	/**
	 * @设置 重复事件截止时间
	 * @param repeatEndDate
	 */
	public void setRepeatEndDate(String repeatEndDate) {
	 	this.repeatEndDate = repeatEndDate;
	}
	/**
	 * @return 提醒类型
	 */
	public String getImpLevel() {
	 	return impLevel;
	}
	/**
	 * @设置 提醒类型
	 * @param impLevel
	 */
	public void setImpLevel(String impLevel) {
	 	this.impLevel = impLevel;
	}
	/**
	 * @return 点击URL
	 */
	public String getUrl() {
	 	return url;
	}
	/**
	 * @设置 点击URL
	 * @param url
	 */
	public void setUrl(String url) {
	 	this.url = url;
	}
	public String getEventdesc() {
		return eventdesc;
	}
	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}
	
}