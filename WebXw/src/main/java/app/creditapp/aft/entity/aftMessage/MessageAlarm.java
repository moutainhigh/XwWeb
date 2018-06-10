package app.creditapp.aft.entity.aftMessage;

import java.util.List;

import app.creditapp.aft.entity.AftRewFund;
import app.creditapp.aft.entity.AftRewPact;
import app.creditapp.aft.entity.AftRewProj;
import app.creditapp.aft.entity.AftRewReal;

public class MessageAlarm{
	
    public MessageAlarm(List<MessageTask> messageTaskList,List<AftRewPact> rewPactList,
			List<AftRewProj> rewProjList, List<AftRewFund> rewFundList, List<AftRewReal> rewRealList) {
		super();
		this.rewPactList = rewPactList; 
		this.rewProjList = rewProjList;
		this.rewFundList = rewFundList;
		this.rewRealList = rewRealList;
		this.messageTaskList = messageTaskList;
		int mCount = 0;
		int aCount = 0;
		for(AftRewPact pact:rewPactList){
			if("01".equals(pact.getRewType()))mCount++;
			if("02".equals(pact.getRewType()))aCount++;
		}
		for(AftRewProj pact:rewProjList){
			if("01".equals(pact.getRewType()))mCount++;
			if("02".equals(pact.getRewType()))aCount++;
		}
		for(AftRewFund pact:rewFundList){
			if("01".equals(pact.getRewType()))mCount++;
			if("02".equals(pact.getRewType()))aCount++;
		}
		for(AftRewReal pact:rewRealList){
			if("01".equals(pact.getRewType()))mCount++;
			if("02".equals(pact.getRewType()))aCount++;
		}
		this.messageCount = String.valueOf(mCount);
		this.alarmCount = String.valueOf(aCount);
		
		int taskSumCount = 0;
		for(MessageTask task:messageTaskList){
			taskSumCount += task.getTaskCount();
		}
		this.taskCount = String.valueOf(taskSumCount);
		this.sumCount = mCount + aCount + taskSumCount;
	}
	private List<AftRewPact> rewPactList;
	private List<AftRewProj> rewProjList;
	private List<AftRewFund> rewFundList;
	private List<AftRewReal> rewRealList;
	private List<MessageTask> messageTaskList; //301,待办任务-待审批任务
	
	private String messageCount;
	private String alarmCount;
	private String taskCount;
	private int sumCount;
	
	public List<MessageTask> getMessageTaskList() {
		return messageTaskList;
	}
	public void setMessageTaskList(List<MessageTask> messageTaskList) {
		this.messageTaskList = messageTaskList;
	}
	public String getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(String taskCount) {
		this.taskCount = taskCount;
	}
	public int getSumCount() {
		return sumCount;
	}
	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}
	public String getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(String messageCount) {
		this.messageCount = messageCount;
	}
	public String getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(String alarmCount) {
		this.alarmCount = alarmCount;
	}
	public List<AftRewPact> getRewPactList() {
		return rewPactList;
	}
	public void setRewPactList(List<AftRewPact> rewPactList) {
		this.rewPactList = rewPactList;
	}
	public List<AftRewProj> getRewProjList() {
		return rewProjList;
	}
	public void setRewProjList(List<AftRewProj> rewProjList) {
		this.rewProjList = rewProjList;
	}
	public List<AftRewFund> getRewFundList() {
		return rewFundList;
	}
	public void setRewFundList(List<AftRewFund> rewFundList) {
		this.rewFundList = rewFundList;
	}
	/**
	 * @return the rewRealList
	 */
	public List<AftRewReal> getRewRealList() {
		return rewRealList;
	}
	/**
	 * @param rewRealList the rewRealList to set
	 */
	public void setRewRealList(List<AftRewReal> rewRealList) {
		this.rewRealList = rewRealList;
	}
	
}