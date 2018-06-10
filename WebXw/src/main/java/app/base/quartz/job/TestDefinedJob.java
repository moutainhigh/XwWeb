package app.base.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import app.base.quartz.entity.ScheduleJob;

public class TestDefinedJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("testJob");
		System.out.println("ÈÎÎñÃû³Æ = [" + scheduleJob.getJobName() + "]"+new Date().toString());
	
	}

}
