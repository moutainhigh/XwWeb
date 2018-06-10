package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: TimeController.java
* Description:
* @version：1.0
**/
public class TimeController extends BaseDomain {
	private Integer tcId;//ID序列
	private String tcName;//策略名称
	private String tcState;//定时任务是否已经启动 1-启动 0-未启动
	private String cronExpression;//时间表达式
	private String startTime;//开始时间
	private String endTime;//结束时间
	private Integer repeatCount;//重复次数(最大999)
	private Long repeatInterval;//间隔时间
	private String timingtime;//用于显示的定时模式
	private String triggerType;//1-simpleTrigger;2-CronTrigger
	private String repeatIntTime;//未处理的间隔时间
	private String jobMode;//job_mode
	private String week;//按周定时
	private Integer stId;//对应的预设任务ID
	private String jobName;//对应的预设任务名称
	
	
	private String jobGroup;//任务分组
	private String isConcurrent;//任务是否有状态
	private String springId;//调用的springID
	private String methodName;//调用的方法名称
	private String beanClass;//调用类的全路径地址
	private String argumentsStr;//参数集合

	/**
	 * @return ID序列
	 */
	public Integer getTcId() {
	 	return tcId;
	}
	/**
	 * @设置 ID序列
	 * @param tcId
	 */
	public void setTcId(Integer tcId) {
	 	this.tcId = tcId;
	}
	public String getTcName() {
		return tcName;
	}
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	/**
	 * @return 定时任务是否已经启动 1-启动 0-未启动
	 */
	public String getTcState() {
	 	return tcState;
	}
	/**
	 * @设置 定时任务是否已经启动 1-启动 0-未启动
	 * @param tcState
	 */
	public void setTcState(String tcState) {
	 	this.tcState = tcState;
	}
	/**
	 * @return 时间表达式
	 */
	public String getCronExpression() {
	 	return cronExpression;
	}
	/**
	 * @设置 时间表达式
	 * @param cronExpression
	 */
	public void setCronExpression(String cronExpression) {
	 	this.cronExpression = cronExpression;
	}
	/**
	 * @return 开始时间
	 */
	public String getStartTime() {
	 	return startTime;
	}
	/**
	 * @设置 开始时间
	 * @param startTime
	 */
	public void setStartTime(String startTime) {
	 	this.startTime = startTime;
	}
	/**
	 * @return 结束时间
	 */
	public String getEndTime() {
	 	return endTime;
	}
	/**
	 * @设置 结束时间
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
	 	this.endTime = endTime;
	}
	/**
	 * @return 重复次数(最大999)
	 */
	public Integer getRepeatCount() {
	 	return repeatCount;
	}
	/**
	 * @设置 重复次数(最大999)
	 * @param repeatCount
	 */
	public void setRepeatCount(Integer repeatCount) {
	 	this.repeatCount = repeatCount;
	}
	/**
	 * @return 间隔时间
	 */
	
	public void setRepeatInterval(Long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	/**
	 * @设置 间隔时间
	 * @param repeatInterval
	 */
	public Long getRepeatInterval() {
		return repeatInterval;
	}
	/**
	 * @return 用于显示的定时模式
	 */
	public String getTimingtime() {
	 	return timingtime;
	}
	/**
	 * @设置 用于显示的定时模式
	 * @param timingtime
	 */
	public void setTimingtime(String timingtime) {
	 	this.timingtime = timingtime;
	}
	/**
	 * @return 1-simpleTrigger;2-CronTrigger
	 */
	public String getTriggerType() {
	 	return triggerType;
	}
	/**
	 * @设置 1-simpleTrigger;2-CronTrigger
	 * @param triggerType
	 */
	public void setTriggerType(String triggerType) {
	 	this.triggerType = triggerType;
	}
	/**
	 * @return 未处理的间隔时间
	 */
	public String getRepeatIntTime() {
		return repeatIntTime;
	}
	
	public void setRepeatIntTime(String repeatIntTime) {
		this.repeatIntTime = repeatIntTime;
	}
	/**
	 * @return job_mode
	 */
	public String getJobMode() {
	 	return jobMode;
	}
	
	/**
	 * @设置 job_mode
	 * @param jobMode
	 */
	public void setJobMode(String jobMode) {
	 	this.jobMode = jobMode;
	}
	/**
	 * @return 按周定时
	 */
	public String getWeek() {
	 	return week;
	}
	/**
	 * @设置 按周定时
	 * @param week
	 */
	public void setWeek(String week) {
	 	this.week = week;
	}
	
	/**
	 * @return 对应的预设任务ID
	 */
	public Integer getStId() {
	 	return stId;
	}
	/**
	 * @设置 对应的预设任务ID
	 * @param stId
	 */
	public void setStId(Integer stId) {
	 	this.stId = stId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getIsConcurrent() {
		return isConcurrent;
	}
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	public String getSpringId() {
		return springId;
	}
	public void setSpringId(String springId) {
		this.springId = springId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public String getArgumentsStr() {
		return argumentsStr;
	}
	public void setArgumentsStr(String argumentsStr) {
		this.argumentsStr = argumentsStr;
	}
}