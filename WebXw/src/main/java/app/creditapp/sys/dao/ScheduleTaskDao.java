package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.sys.entity.ScheduleTask;
/**
* Title: ScheduleTaskDao.java
* Description:
**/
public interface ScheduleTaskDao {

	public ScheduleTask getById(ScheduleTask scheduleTask) throws DAOException;

	public void del(ScheduleTask scheduleTask) throws DAOException;

	public void insert(ScheduleTask scheduleTask) throws DAOException;

	public void update(ScheduleTask scheduleTask) throws DAOException;
	
	public void updateTaskSts(ScheduleTask scheduleTask) throws DAOException;
	
	public int getCount(ScheduleTask scheduleTask) throws DAOException;

	public List<ScheduleTask > findByPage(ScheduleTask scheduleTask) throws DAOException;
	/**
	 * 关联预设任务与定时策略
	 * @param seId
	 * @param tcId
	 * @throws DAOException
	 */
	public void updateTimeController(String seId, String tcId) throws DAOException;
	
	public List<ScheduleTask> findStartTask(ScheduleTask scheduleTask) throws DAOException;

}