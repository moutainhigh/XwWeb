package app.creditapp.sys.entity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import app.base.BaseDomain;
/**
* Title: ScheduleTask.java
* Description:
* @version：1.0
**/
public class ScheduleTask extends BaseDomain {
	private Integer seId;//定时任务预设ID
	private String opNo;//操作员ID
	private String createTime;//创建时间
	private String updateTime;//修改时间
	private String jobName;//任务名称
	private String jobGroup;//任务分组
	private String jobStatus;//任务启动状态 1-启动；0-未启动
	private String description;//备注说明
	private String isConcurrent;//任务是否有状态
	private String springId;//调用的springID
	private String methodName;//调用的方法名称
	private String beanClass;//调用类的全路径地址
	private String argumentsStr;//参数集合
	private String opName;//操作员ID
	private String tcId;//策略ID
	/**
	 * methodName中可能会使用的参数列表
	 */
	private Object[] arguments;
	
	private String templateName;//根据模板名字获取需要传输的模板名称
	
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	/**
	 * @return 定时任务预设ID
	 */
	public Integer getSeId() {
	 	return seId;
	}
	/**
	 * @设置 定时任务预设ID
	 * @param seId
	 */
	public void setSeId(Integer seId) {
	 	this.seId = seId;
	}
	/**
	 * @return 操作员ID
	 */
	public String getOpNo() {
	 	return opNo;
	}
	/**
	 * @设置 操作员ID
	 * @param opNo
	 */
	public void setOpNo(String opNo) {
	 	this.opNo = opNo;
	}
	/**
	 * @return 创建时间
	 */
	public String getCreateTime() {
	 	return createTime;
	}
	/**
	 * @设置 创建时间
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
	 	this.createTime = createTime;
	}
	/**
	 * @return 修改时间
	 */
	public String getUpdateTime() {
	 	return updateTime;
	}
	/**
	 * @设置 修改时间
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
	 	this.updateTime = updateTime;
	}
	/**
	 * @return 任务名称
	 */
	public String getJobName() {
	 	return jobName;
	}
	/**
	 * @设置 任务名称
	 * @param jobName
	 */
	public void setJobName(String jobName) {
	 	this.jobName = jobName;
	}
	/**
	 * @return 任务分组
	 */
	public String getJobGroup() {
	 	return jobGroup;
	}
	/**
	 * @设置 任务分组
	 * @param jobGroup
	 */
	public void setJobGroup(String jobGroup) {
	 	this.jobGroup = jobGroup;
	}
	/**
	 * @return 任务启动状态 1-启动；0-未启动
	 */
	public String getJobStatus() {
	 	return jobStatus;
	}
	/**
	 * @设置 任务启动状态 1-启动；0-未启动
	 * @param jobStatus
	 */
	public void setJobStatus(String jobStatus) {
	 	this.jobStatus = jobStatus;
	}
	/**
	 * @return 备注说明
	 */
	public String getDescription() {
	 	return description;
	}
	/**
	 * @设置 备注说明
	 * @param description
	 */
	public void setDescription(String description) {
	 	this.description = description;
	}
	/**
	 * @return 任务是否有状态
	 */
	public String getIsConcurrent() {
	 	return isConcurrent;
	}
	/**
	 * @设置 任务是否有状态
	 * @param isConcurrent
	 */
	public void setIsConcurrent(String isConcurrent) {
	 	this.isConcurrent = isConcurrent;
	}
	/**
	 * @return 调用的springID
	 */
	public String getSpringId() {
	 	return springId;
	}
	/**
	 * @设置 调用的springID
	 * @param springId
	 */
	public void setSpringId(String springId) {
	 	this.springId = springId;
	}
	/**
	 * @return 调用的方法名称
	 */
	public String getMethodName() {
	 	return methodName;
	}
	/**
	 * @设置 调用的方法名称
	 * @param methodName
	 */
	public void setMethodName(String methodName) {
	 	this.methodName = methodName;
	}
	/**
	 * @return 调用类的全路径地址
	 */
	public String getBeanClass() {
	 	return beanClass;
	}
	/**
	 * @设置 调用类的全路径地址
	 * @param beanClass
	 */
	public void setBeanClass(String beanClass) {
	 	this.beanClass = beanClass;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getTcId() {
		return tcId;
	}
	public void setTcId(String tcId) {
		this.tcId = tcId;
	}
	public String getArgumentsStr() {
		return argumentsStr;
	}
	public void setArgumentsStr(String argumentsStr) {
		this.argumentsStr = argumentsStr;
	}
	
	public Object[] getArguments() {
		if(this.arguments!=null && this.arguments.length != 0)
			return this.arguments;
		else if(this.argumentsStr!=null && !this.argumentsStr.isEmpty()){
			String[] initArgsArray = argumentsStr.split(",");
			arguments = new Object[initArgsArray.length];
			for(int index = 0; index<initArgsArray.length; index++){
				if(initArgsArray[index].indexOf(":") != -1){//存在冒号，即可反射该对象类型
					String[] secArgsArray = initArgsArray[index].split(":");
						try {
							if(secArgsArray[0].equalsIgnoreCase("String")){
								arguments[index] =secArgsArray[1];
							}else if( secArgsArray[0].equalsIgnoreCase("Integer")
								|| secArgsArray[0].equalsIgnoreCase("Double")
								||secArgsArray[0].equalsIgnoreCase("Float")
								||secArgsArray[0].equalsIgnoreCase("Long")
								||secArgsArray[0].equalsIgnoreCase("Short")
								||secArgsArray[0].equalsIgnoreCase("Boolean")){
								secArgsArray[0] = secArgsArray[0].substring(0, 1).toUpperCase() + secArgsArray[0].substring(1).toLowerCase();
								Class<?> clazz = Class.forName("java.lang."+secArgsArray[0]);
								Method valueOfMethod = clazz.getDeclaredMethod("valueOf", String.class);
								arguments[index] = valueOfMethod.invoke(clazz,secArgsArray[1] );
							}else{
								arguments[index] = Class.forName(secArgsArray[0]).newInstance();
							}
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							e.printStackTrace();
						}
				}else{//若不存在冒号，则根据值的格式判断出对应的类型
					if(initArgsArray[index].startsWith("\"")){
						arguments[index] = initArgsArray[index].replaceAll("\"", "");
					}else if(initArgsArray[index].indexOf(".") != -1){
						arguments[index] = Double.valueOf(initArgsArray[index]);
					}else if(initArgsArray[index].equalsIgnoreCase("true") || initArgsArray[index].equalsIgnoreCase("false")){
						arguments[index] = Boolean.valueOf(initArgsArray[index]);
					}else {
						arguments[index] = Integer.valueOf(initArgsArray[index]);
					}
				}
			}
			return arguments;
		}else{
			return null;
		}
	}
	
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
}