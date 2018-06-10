package app.creditapp.sys.entity;

public class TransContr {

	private Integer seId;//预设任务ID
	private String tcName;//任务名称
	private Integer repeatTime;//间隔时间
	private Integer repeatCount;//重复次数
	private String startTime;//开始时间
	private String endTime;//结束时间
	
	private String argumentsStr;//参数集合
	
	public String getArgumentsStr() {
		return argumentsStr;
	}
	public void setArgumentsStr(String argumentsStr) {
		this.argumentsStr = argumentsStr;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getSeId() {
		return seId;
	}
	public void setSeId(Integer seId) {
		this.seId = seId;
	}
	public String getTcName() {
		return tcName;
	}
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public Integer getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Integer getRepeatTime() {
		return repeatTime;
	}
	public void setRepeatTime(Integer repeatTime) {
		this.repeatTime = repeatTime;
	}

}
