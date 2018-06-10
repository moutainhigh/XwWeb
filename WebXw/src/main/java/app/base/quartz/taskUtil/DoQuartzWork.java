package app.base.quartz.taskUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import app.base.SourceTemplate;
import app.base.quartz.entity.ScheduleJob;
import app.creditapp.sys.bo.SchedulejobLogBo;
import app.creditapp.sys.bo.impl.SchedulejobLogBoImpl;
import app.creditapp.sys.entity.SchedulejobLog;
import app.util.User;

public class DoQuartzWork implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		SchedulejobLogBo schedulejobLogBo = new SchedulejobLogBoImpl();
		SchedulejobLog schedulejobLog = new SchedulejobLog();
		schedulejobLog.setArgumentsstr(scheduleJob.getArgumentsStr());
		schedulejobLog.setBeanClass(scheduleJob.getBeanClass());
		schedulejobLog.setCreateTime(User.getTime());
		schedulejobLog.setJobDescription(scheduleJob.getDescription());
		schedulejobLog.setJobName(scheduleJob.getJobName());
		schedulejobLog.setMethodName(scheduleJob.getMethodName());
		schedulejobLog.setSpringId(scheduleJob.getSpringId());
		schedulejobLogBo.insert(schedulejobLog);
		System.out.println("执行定时任务  = [ 任务名称：" + scheduleJob.getJobName() + "]");
		System.out.println("执行定时任务  = [ 任务描述：" + scheduleJob.getDescription() + "]");
		System.out.println("执行定时任务  = [ 执行时间：" + new Date().toString() + "]");
		System.out.println("执行定时任务  = [ 执行对象：" + scheduleJob.getBeanClass() + "]");
		System.out.println("执行定时任务  = [ 执行方法：" + scheduleJob.getMethodName() + "]");
		System.out.println("=================任务执行开始==========================");
		try {
			doJobWork(scheduleJob);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void doJobWork(ScheduleJob scheduleJob) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException{
		Object obj =  SourceTemplate.getSpringContextInstance().getBean(scheduleJob.getSpringId());
		if(scheduleJob.getArguments()==null || scheduleJob.getArguments().length==0){//无参数时直接调用该方法
			Method workMethod = obj.getClass().getDeclaredMethod(scheduleJob.getMethodName());
			workMethod.invoke(obj);
		}else{
			//有参数时遍历方法名，并判断参数列表数量与参数数量是否相等
			//PS：参数的顺序非常重要！
			Method[] workMethods = obj.getClass().getMethods();
			for(Method method:workMethods){
				if(scheduleJob.getMethodName().equals(method.getName()) && (scheduleJob.getArguments().length == method.getParameterTypes().length )){
					method.invoke(obj, scheduleJob.getArguments());
					break;
				}
			}
		}
		System.out.println("=================任务执行结束==========================");
	}
	
	public void test(String sdaa,int dda,double dasdas){
		
	}
	
}
