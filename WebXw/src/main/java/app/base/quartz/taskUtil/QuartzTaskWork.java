package app.base.quartz.taskUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import app.base.quartz.entity.ScheduleJob;
import app.base.quartz.entity.ScheduleJobStatus;
import app.base.quartz.ports.TaskScheduleUtil;

/**
 * 定时工作配置
 * 1.系统启动时初始化，获取数据库中的数据，
 * 2.分析数据，转换为taskJob，包括时间表达式
 * 3.将job加入scheduler中，启动
 * 4.提供对外接口修改job值
 *
 */
public class QuartzTaskWork implements ApplicationListener<ContextRefreshedEvent> ,TaskScheduleUtil{
	private SchedulerFactory schedulerFactoryBean;
	
	public void setSchedulerFactoryBean(SchedulerFactory schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
	}
	
	private ScheduleJob createTestJob(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		ScheduleJob job = new ScheduleJob();
		job.setJobId(new Date().getTime());
		job.setCreateTime(sdf.format(new Date()));
		job.setCronExpression("0 */15 * * * ?");
		job.setJobName("testJob");
		job.setJobGroup("1_g_job");
		job.setJobStatus("1");
		job.setDescription("测试自定义定时任务");
		job.setBeanClass("app.base.quartz.job.test.DoMyWork");
		job.setSpringId("domywork");
		job.setMethodName("doWork");
//		job.setArguments(new Object[]{"程序员",8});
		job.setArguments(null);
		job.setStartTime(sdf.format(new Date()));
		job.setTriggerType("2");
		job.setRepeatInterval(10000 * 1L);
		job.setRepeatCount(10);
		return job;
	}

	private List<ScheduleJob> initTaskWorkList(){
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		//TODO 测试成功后改为从数据库取值，不再使用测试方法
		//jobList.add(createTestJob());
		return jobList;
	}
	
	private void addScheduleWork(List<ScheduleJob> jobList) throws SchedulerException{
		for(ScheduleJob job:jobList){
			try {
				addOrUpdateJob(job);
			} catch (Exception e) {
				//TODO 抛出异常后输出
				e.printStackTrace();
			}
		}
	}
	
	public void addOrUpdateJob(ScheduleJob job) throws SchedulerException, ParseException, ClassNotFoundException {
		if(job == null || !(ScheduleJobStatus.STATUS_RUNNING.equals(job.getJobStatus())))return;
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		Trigger trigger = (Trigger)scheduler.getTrigger(job.getJobName(), job.getJobGroup());
		if(trigger == null){//新增
			JobDetail jobDetail = new JobDetail(job.getJobName(),  job.getJobGroup(), DoQuartzWork.class);
			jobDetail.getJobDataMap().put("scheduleJob", job);
			if("1".equals(job.getTriggerType())){
				 scheduler.scheduleJob(jobDetail, optSimpleTrigger(null,job));  
			}else{
				scheduler.scheduleJob(jobDetail, optConTrigger(null,job));  
			}
		}else{
			if(trigger instanceof SimpleTrigger){
				SimpleTrigger simpleTrigger = optSimpleTrigger((SimpleTrigger) scheduler.getTrigger( job.getJobName(), job.getJobGroup()),job);
				scheduler.rescheduleJob(job.getJobName(), job.getJobGroup(), simpleTrigger);
			}else if(trigger instanceof CronTrigger){
				CronTrigger contrigger = optConTrigger((CronTrigger) scheduler.getTrigger( job.getJobName(), job.getJobGroup()),job);
				scheduler.rescheduleJob(job.getJobName(), job.getJobGroup(), contrigger);
			}
		}
	}
	
	private CronTrigger optConTrigger(CronTrigger trigger,ScheduleJob job) throws SchedulerException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if (trigger == null) {
			JobDetail jobDetail = new JobDetail(job.getJobName(),  job.getJobGroup(), DoQuartzWork.class);
			jobDetail.getJobDataMap().put("scheduleJob", job);
			trigger = new CronTrigger(job.getJobName(), job.getJobGroup());// 触发器名,触发器组  
            trigger.setCronExpression(job.getCronExpression());// 触发器时间设定  
            
//            trigger.setStartTime(sdf.parse(job.getStartTime()));
            trigger.setStartTime(new Date(new Date().getTime()+1000));
            if(job.getEndTime()!=null)trigger.setEndTime(sdf.parse(job.getEndTime()));
            return trigger;
		}else{
			trigger.setCronExpression(job.getCronExpression());
			trigger.setStartTime(sdf.parse(job.getStartTime()));
            if(job.getEndTime()!=null)trigger.setEndTime(sdf.parse(job.getEndTime()));
            // 按新的trigger重新设置job执行
            return trigger;
		}
	}
	
	private SimpleTrigger optSimpleTrigger(SimpleTrigger trigger,ScheduleJob job) throws SchedulerException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if (trigger == null) {
			trigger = new SimpleTrigger(job.getJobName(), job.getJobGroup());// 触发器名,触发器组
			if (job.getRepeatCount() > 0) trigger.setRepeatCount(job.getRepeatCount());// 触发次数
			if (job.getRepeatInterval() > 0) trigger.setRepeatInterval(job.getRepeatInterval());// 触发间隔
			trigger.setStartTime(sdf.parse(job.getStartTime()));
			if (job.getEndTime() != null) trigger.setEndTime(sdf.parse(job.getEndTime()));
			return trigger;
		} else {
			if (job.getRepeatCount() > 0) trigger.setRepeatCount(job.getRepeatCount());// 触发次数
			if (job.getRepeatInterval() > 0) trigger.setRepeatInterval(job.getRepeatInterval());// 触发间隔
			trigger.setStartTime(sdf.parse(job.getStartTime()));
			if (job.getEndTime() != null) trigger.setEndTime(sdf.parse(job.getEndTime()));
			return trigger;
		}
	}
	
	
	public void startQuartzTaskWork() throws SchedulerException{
		addScheduleWork(initTaskWorkList());
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		if (!scheduler.isShutdown()) {  
			scheduler.start();  
        }
	}
	
	@Override
	public boolean deleteTaskJob(String jobName,String groupName) throws SchedulerException{
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.pauseTrigger(jobName, groupName);// 停止触发器  
			scheduler.unscheduleJob(jobName, groupName);// 移除触发器  
			scheduler.deleteJob(jobName, groupName);// 删除任务  
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteTaskJob(ScheduleJob job) throws SchedulerException{
		return deleteTaskJob(job.getJobName(), job.getJobGroup());
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	}

	@Override
	public boolean startQuartzTask() throws SchedulerException {
		try {
			 //其他 机器跑项目的时候  注释掉定时 触发器
			startQuartzTaskWork();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
