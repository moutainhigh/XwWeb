package  app.creditapp.sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.ScheduleTaskDao;
import app.creditapp.sys.entity.ScheduleTask;
/**
* Title: ScheduleTaskDaoImpl.java
* Description:
**/
public class ScheduleTaskDaoImpl extends BaseIbatisDao implements ScheduleTaskDao {

	public void del(ScheduleTask scheduleTask) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ScheduleTask.del", scheduleTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ScheduleTask> findByPage(ScheduleTask scheduleTask) throws DAOException {
		List scheduleTaskList = null;
		try {
			scheduleTaskList = getSqlMapClientTemplate().queryForList(
					"ScheduleTask.findByPage", scheduleTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return scheduleTaskList;
	}

	public ScheduleTask getById(ScheduleTask scheduleTask) throws DAOException {
		try {
			scheduleTask = (ScheduleTask) getSqlMapClientTemplate()
					.queryForObject("ScheduleTask.getById", scheduleTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return scheduleTask;
	}

	public int getCount(ScheduleTask scheduleTask) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ScheduleTask.findPage.count", scheduleTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ScheduleTask scheduleTask) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ScheduleTask.insert",
					scheduleTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ScheduleTask scheduleTask) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ScheduleTask.update",
					scheduleTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	/**
	 * 关联预设任务与定时策略
	 * @param seId
	 * @param tcId
	 * @throws DAOException
	 */
	public void updateTimeController(String seId, String tcId) throws DAOException {
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("seId", seId);
			map.put("tcId", tcId);
			getSqlMapClientTemplate().update("TimeController.updateTimeController",
					map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	/**
	 * 更新预设任务状态
	 */
	@Override
	public void updateTaskSts(ScheduleTask scheduleTask) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ScheduleTask.updateTaskSts",
					scheduleTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}

	@Override
	public List<ScheduleTask> findStartTask(ScheduleTask scheduleTask) throws DAOException {
		List scheduleTaskList = null;
		try {
			scheduleTaskList = getSqlMapClientTemplate().queryForList(
					"ScheduleTask.findStartTask",scheduleTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return scheduleTaskList;
	}
}