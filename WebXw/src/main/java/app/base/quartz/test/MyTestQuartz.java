package app.base.quartz.test;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyTestQuartz implements Job {

	@Override
	public void execute(JobExecutionContext jobExeCon) throws JobExecutionException {
		System.out.println(jobExeCon.getJobRunTime()); 
        System.out.println(jobExeCon.hashCode()); 
        System.out.println(jobExeCon.getTrigger().getGroup()); 
        System.out.println(jobExeCon.getTrigger().getName()); 
        System.out.println("Hello world"); 
	}
	public void test(){ 
		System.out.println("xxxxxxxxxxxxxxxxxxxxx"+new Date().getTime()); 
	} 
}
