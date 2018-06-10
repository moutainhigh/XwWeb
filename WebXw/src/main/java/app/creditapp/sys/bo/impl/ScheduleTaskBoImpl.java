package  app.creditapp.sys.bo.impl;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;

import app.base.BaseService;
import app.base.ServiceException;
import app.base.quartz.entity.ConExpTransType;
import app.base.quartz.entity.ScheduleJob;
import app.base.quartz.taskUtil.QuartzTaskWork;
import app.base.quartz.taskUtil.TimeToCronUtil;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.ScheduleTaskBo;
import app.creditapp.sys.dao.ScheduleTaskDao;
import app.creditapp.sys.dao.TimeControllerDao;
import app.creditapp.sys.entity.ScheduleTask;
import app.creditapp.sys.entity.TimeController;
import app.creditapp.sys.entity.TransContr;
/**
* Title: ScheduleTaskBoImplImpl.java
* Description:
**/
public class ScheduleTaskBoImpl extends BaseService implements ScheduleTaskBo {

	private ScheduleTaskDao scheduleTaskDao;
	private ScheduleTask scheduleTask;
	private TimeController timeController;
	private TimeControllerDao timeControllerDao;
	private ScheduleJob scheduleJob;
	private QuartzTaskWork taskScheduleUtil;
	public void del(ScheduleTask scheduleTask) throws ServiceException {
		try {
			scheduleTaskDao.del(scheduleTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ScheduleTask scheduleTask)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) scheduleTaskDao
					.getCount(scheduleTask) });// 初始化分页类
			scheduleTask.setStartNumAndEndNum (ipg);
			ipg.setResult(scheduleTaskDao.findByPage(scheduleTask));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public ScheduleTask getById(ScheduleTask scheduleTask) throws ServiceException {
		try {
			scheduleTask = scheduleTaskDao.getById(scheduleTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return scheduleTask;
	}

	public void insert(ScheduleTask scheduleTask) throws ServiceException {
		try {
			scheduleTaskDao.insert(scheduleTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ScheduleTask scheduleTask) throws ServiceException {
		try {
			scheduleTaskDao.update(scheduleTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	 * 关联预设任务与定时策略
	 */
	@Override
	public void updateTimeController(String seId, String tcId)
			throws ServiceException {
		try {
			scheduleTaskDao.updateTimeController(seId, tcId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	/**
	 * 更新预设任务状态
	 */
	@Override
	public void updateTaskSts(ScheduleTask scheduleTask)
			throws ServiceException {
		try {
			scheduleTaskDao.updateTaskSts(scheduleTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	/**
	 * 查询启用的预设任务
	 */
	@Override
	public List<ScheduleTask> findStartTask() throws ServiceException {
		List<ScheduleTask> scheduleTaskList = null;
		scheduleTask = new ScheduleTask();
		try {
			scheduleTask.setJobStatus("1");
			scheduleTaskList = scheduleTaskDao.findStartTask(scheduleTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return scheduleTaskList;
	}
	/**
	 * @param timeController
	 * 需要传入的参数:预设任务ID,
	 * 					 任务名称,间隔时间,重复次数,开始时间
	 * 返回策略ID
	 */
	public Integer getTaskAndCon(TransContr transContr){
		
		timeController = new TimeController();
		timeController.setStId(transContr.getSeId());
		timeController.setTcName(transContr.getTcName());
		timeController.setRepeatCount(transContr.getRepeatCount());
		timeController.setRepeatInterval(toRepeatInterval(transContr.getRepeatTime()));
		timeController.setStartTime(transContr.getStartTime());
		timeController.setEndTime(transContr.getEndTime());
		timeController.setTriggerType("1");
		timeController.setTcState("1");
		int tcId = timeControllerDao.insert(timeController);//将策略存入数据库
		//if(tcId == -1)
		
		scheduleTask = new ScheduleTask();
		scheduleTask.setSeId(transContr.getSeId());
		scheduleTask = scheduleTaskDao.getById(scheduleTask);//查询预设任务信息
		
		scheduleJob = new ScheduleJob();
		scheduleJob.setJobGroup(scheduleTask.getJobGroup());
		scheduleJob.setBeanClass(scheduleTask.getBeanClass());
		scheduleJob.setSpringId(scheduleTask.getSpringId());
		scheduleJob.setMethodName(scheduleTask.getMethodName());
//		scheduleJob.setJobName(String.valueOf(tcId));
		scheduleJob.setJobName(scheduleTask.getJobName());
		scheduleJob.setTriggerType("1");//间隔模式
		scheduleJob.setJobStatus("1");//启动状态
		scheduleJob.setRepeatCount(timeController.getRepeatCount());
		scheduleJob.setRepeatInterval(timeController.getRepeatInterval());
		scheduleJob.setStartTime(timeController.getStartTime());
		scheduleJob.setEndTime(timeController.getEndTime());
		scheduleJob.setArgumentsStr(transContr.getArgumentsStr());
		try {
			taskScheduleUtil.addOrUpdateJob(scheduleJob);
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return timeController.getTcId();
	}
	/**
	 * 删除策略
	 * 传入参数：策略ID
	 * @param tcId
	 */
	public void delController(Integer tcId){
		timeController = new TimeController();
		timeController.setTcId(tcId);
		timeController = timeControllerDao.findGroupAndName(timeController);//查询策略名称和任务分组
		
		scheduleJob = new ScheduleJob();
		scheduleJob.setJobGroup(timeController.getJobGroup());
		scheduleJob.setJobName(String.valueOf(tcId));
		try {
			timeController.setTcId(tcId);
			taskScheduleUtil.deleteTaskJob(scheduleJob);//停止任务
			timeControllerDao.del(timeController);//删除策略
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 转换为间隔时间
	 * @param long1
	 * @return
	 */
	public Long toRepeatInterval(Integer value){
		int intervalArgs[] = new int[7];
		intervalArgs[0] = value;
		return TimeToCronUtil.intervalToCron(intervalArgs);
	}
	public ScheduleTaskDao getScheduleTaskDao() {
		return scheduleTaskDao;
	}

	public void setScheduleTaskDao(ScheduleTaskDao scheduleTaskDao) {
		this.scheduleTaskDao = scheduleTaskDao;
	}

	public ScheduleTask getScheduleTask() {
		return scheduleTask;
	}

	public void setScheduleTask(ScheduleTask scheduleTask) {
		this.scheduleTask = scheduleTask;
	}

	public TimeController getTimeController() {
		return timeController;
	}

	public void setTimeController(TimeController timeController) {
		this.timeController = timeController;
	}
	public TimeControllerDao getTimeControllerDao() {
		return timeControllerDao;
	}

	public void setTimeControllerDao(TimeControllerDao timeControllerDao) {
		this.timeControllerDao = timeControllerDao;
	}

	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}

	public void setScheduleJob(ScheduleJob scheduleJob) {
		this.scheduleJob = scheduleJob;
	}

	public QuartzTaskWork getTaskScheduleUtil() {
		return taskScheduleUtil;
	}

	public void setTaskScheduleUtil(QuartzTaskWork taskScheduleUtil) {
		this.taskScheduleUtil = taskScheduleUtil;
	}
	 public static void main(String[] args) {
//			System.out.println(TimeToCronUtil.intervalToCron(2,3,12,4,56,3));
//	    	String[] arrays = {"1",null,null,null,null,null,null};//每个月3日5点12分
//	    	System.out.println(toRepeatInterval(1));
//	    	String[] arrays = {"3","5","12"};//每个月3日5点12分
//	    	String rs = TimeToCronUtil.transArrayToStr(arrays);
//	    	System.out.println(rs);
		}
	
}