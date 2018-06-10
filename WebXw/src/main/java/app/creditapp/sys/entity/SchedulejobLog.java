package app.creditapp.sys.entity;
import app.base.BaseDomain;
/**
* Title: SchedulejobLog.java
* Description:
* @version：1.0
**/
public class SchedulejobLog extends BaseDomain {
	private String logId;//log_id
	private String createTime;//执行时间，精确到秒:YYYYMMDD HH24:MI:SS
	private String jobName;//执行任务名称
	private String jobDescription;//执行任务简要描述
	private String beanClass;//执行的方法详细路径
	private String methodName;//执行方法名称
	private String springId;//执行的spring名称
	private String argumentsstr;//执行的参数集合

	/**
	 * @return log_id
	 */
	public String getLogId() {
	 	return logId;
	}
	/**
	 * @设置 log_id
	 * @param logId
	 */
	public void setLogId(String logId) {
	 	this.logId = logId;
	}
	/**
	 * @return 执行时间，精确到秒:YYYYMMDD HH24:MI:SS
	 */
	public String getCreateTime() {
	 	return createTime;
	}
	/**
	 * @设置 执行时间，精确到秒:YYYYMMDD HH24:MI:SS
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
	 	this.createTime = createTime;
	}
	/**
	 * @return 执行任务名称
	 */
	public String getJobName() {
	 	return jobName;
	}
	/**
	 * @设置 执行任务名称
	 * @param jobName
	 */
	public void setJobName(String jobName) {
	 	this.jobName = jobName;
	}
	/**
	 * @return 执行任务简要描述
	 */
	public String getJobDescription() {
	 	return jobDescription;
	}
	/**
	 * @设置 执行任务简要描述
	 * @param jobDescription
	 */
	public void setJobDescription(String jobDescription) {
	 	this.jobDescription = jobDescription;
	}
	/**
	 * @return 执行的方法详细路径
	 */
	public String getBeanClass() {
	 	return beanClass;
	}
	/**
	 * @设置 执行的方法详细路径
	 * @param beanClass
	 */
	public void setBeanClass(String beanClass) {
	 	this.beanClass = beanClass;
	}
	/**
	 * @return 执行方法名称
	 */
	public String getMethodName() {
	 	return methodName;
	}
	/**
	 * @设置 执行方法名称
	 * @param methodName
	 */
	public void setMethodName(String methodName) {
	 	this.methodName = methodName;
	}
	/**
	 * @return 执行的spring名称
	 */
	public String getSpringId() {
	 	return springId;
	}
	/**
	 * @设置 执行的spring名称
	 * @param springId
	 */
	public void setSpringId(String springId) {
	 	this.springId = springId;
	}
	/**
	 * @return 执行的参数集合
	 */
	public String getArgumentsstr() {
	 	return argumentsstr;
	}
	/**
	 * @设置 执行的参数集合
	 * @param argumentsstr
	 */
	public void setArgumentsstr(String argumentsstr) {
	 	this.argumentsstr = argumentsstr;
	}
}