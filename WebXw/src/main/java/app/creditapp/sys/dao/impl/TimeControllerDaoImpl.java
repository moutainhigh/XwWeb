package  app.creditapp.sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.TimeControllerDao;
import app.creditapp.sys.entity.TimeController;
/**
* Title: TimeControllerDaoImpl.java
* Description:
**/
public class TimeControllerDaoImpl extends BaseIbatisDao implements TimeControllerDao {

	public void del(TimeController timeController) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("TimeController.del", timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<TimeController> findByPage(TimeController timeController) throws DAOException {
		List timeControllerList = null;
		try {
			timeControllerList = getSqlMapClientTemplate().queryForList(
					"TimeController.findByPage", timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return timeControllerList;	
	}
	@Override
	public List<TimeController> getByStId(TimeController timeController)
			throws DAOException {
		List timeControllerList = null;
		try {
			timeControllerList = getSqlMapClientTemplate().queryForList(
					"TimeController.getByStId", timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return timeControllerList;	//
	}
	public TimeController getById(TimeController timeController) throws DAOException {
		try {
			timeController = (TimeController) getSqlMapClientTemplate()
					.queryForObject("TimeController.getById", timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return timeController;
	}

	public int getCount(TimeController timeController) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"TimeController.findPage.count", timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public int insert(TimeController timeController) throws DAOException {
		int tcId = -1;
		try {
			tcId = (Integer) getSqlMapClientTemplate().queryForObject("TimeController.getTcId");
			timeController.setTcId(tcId);
			getSqlMapClientTemplate().insert("TimeController.insert",
					timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return tcId;
	}

	public void update(TimeController timeController) throws DAOException {
		try {
			getSqlMapClientTemplate().update("TimeController.update",
					timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void updateTimeSts(TimeController timeController) throws DAOException {
		try {
			getSqlMapClientTemplate().update("TimeController.updateTimeSts",
					timeController);
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
			getSqlMapClientTemplate().update("ScheduleTask.updateTimeController",
					map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	@Override
	public void startTimeSts(TimeController timeController) throws DAOException {
		try {
			getSqlMapClientTemplate().update("TimeController.startTimeSts",
					timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<TimeController> findStart() throws DAOException {//
		List timeControllerList = null;
		try {
			timeControllerList = getSqlMapClientTemplate().queryForList(
					"TimeController.getStart");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return timeControllerList;	//
	}

	@Override
	public TimeController findGroupAndName(TimeController timeController)
			throws DAOException {
		try {
			timeController = (TimeController) getSqlMapClientTemplate()
					.queryForObject("TimeController.findGroupAndName", timeController);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return timeController;
	}

	
}