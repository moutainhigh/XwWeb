package  app.creditapp.sys.bo;

import java.util.List;

import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.ScheduleTask;
import app.creditapp.sys.entity.TransContr;

/**
* Title: ScheduleTaskBo.java
* Description:
**/
public interface ScheduleTaskBo {

	public ScheduleTask getById(ScheduleTask scheduleTask) throws ServiceException;

	public void del(ScheduleTask scheduleTask) throws ServiceException;

	public void insert(ScheduleTask scheduleTask) throws ServiceException;

	public void update(ScheduleTask scheduleTask) throws ServiceException;
	
	public void updateTaskSts(ScheduleTask scheduleTask) throws ServiceException;
	
	public Ipage findByPage(Ipage ipg, ScheduleTask scheduleTask) throws ServiceException;
	/**
	 * 查询启用的预设任务
	 */
	public List<ScheduleTask> findStartTask() throws ServiceException;
	/**
	 * 关联预设任务与定时策略
	 * @param seId
	 * @param tcId
	 * @throws ServiceException
	 */
	public void updateTimeController(String seId, String tcId) throws ServiceException;
	/**
	 * @param timeController
	 * 需要传入的参数:预设任务ID,
	 * 					 任务名称,间隔时间,重复次数,开始时间
	 * 返回策略ID
	 */
	public Integer getTaskAndCon(TransContr transContr)throws ServiceException;
	/**
	 * 传入参数：策略ID
	 * @param tcId
	 */
	public void delController(Integer tcId)throws ServiceException;
	

}