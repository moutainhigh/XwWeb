package app.base.quartz.ports;

import java.text.ParseException;

import org.quartz.SchedulerException;

import app.base.quartz.entity.ScheduleJob;

public interface TaskScheduleUtil {
	public void addOrUpdateJob(ScheduleJob job) throws SchedulerException, ParseException, ClassNotFoundException;
	public boolean deleteTaskJob(ScheduleJob job) throws SchedulerException;
	public boolean deleteTaskJob(String jobName,String groupName) throws SchedulerException;
	public boolean startQuartzTask() throws SchedulerException;
}
