package app.base.quartz.entity;

public enum ScheduleJobStatus {
	STATUS_RUNNING("1"),STATUS_NOT_RUNNING("0"),CONCURRENT_IS("1"),CONCURRENT_NOT("0");
	
	private String value;
	private ScheduleJobStatus(String value){
		this.value = value;
	}
	
	public String value(){
		return this.value;
	}
	
	public boolean equals(String value){
		return this.value.equals(value);
	}
}
