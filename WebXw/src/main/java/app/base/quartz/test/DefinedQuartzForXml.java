package app.base.quartz.test;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class DefinedQuartzForXml {
	public void testToSay(){
		System.out.println("xxxxxxxxxxxxxxxxxxxxx"+new Date().getTime()); 
	}
	
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		SimpleTrigger trigger = new SimpleTrigger("myTrigger", "myGroup");
        trigger.setPriority(1);
        JobDetail jobDetail = new JobDetail("myJob", "myJobGroup", MyJob4.class);
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

        Thread.currentThread().sleep(1000);
        System.out.println(scheduler.getCurrentlyExecutingJobs().size());
	}
}
