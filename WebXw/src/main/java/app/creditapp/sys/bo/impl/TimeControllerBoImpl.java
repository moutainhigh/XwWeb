package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.TimeControllerBo;
import app.creditapp.sys.dao.TimeControllerDao;
import app.creditapp.sys.entity.ScheduleTask;
import app.creditapp.sys.entity.TimeController;
/**
* Title: TimeControllerBoImplImpl.java
* Description:
**/
public class TimeControllerBoImpl extends BaseService implements TimeControllerBo {

	private TimeControllerDao timeControllerDao;

	public void del(TimeController timeController) throws ServiceException {
		try {
			timeControllerDao.del(timeController);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, TimeController timeController)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) timeControllerDao
					.getCount(timeController) });// 初始化分页类
			timeController.setStartNumAndEndNum (ipg);
			ipg.setResult(timeControllerDao.findByPage(timeController));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	@Override
	public List<TimeController> getByStId(TimeController timeController)
			throws ServiceException {
		List timeControllerList = null;
		timeControllerList = timeControllerDao.getByStId(timeController);
		return timeControllerList;
	}
	
	@Override
	public List<TimeController> findStart() throws ServiceException {
		List timeControllerList = null;
		timeControllerList = timeControllerDao.findStart();
		return timeControllerList;
	}
	
	
	public TimeController getById(TimeController timeController) throws ServiceException {
		try {
			timeController = timeControllerDao.getById(timeController);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return timeController;
	}

	public void insert(TimeController timeController) throws ServiceException {
		try {
			timeControllerDao.insert(timeController);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(TimeController timeController) throws ServiceException {
		try {
			timeControllerDao.update(timeController);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	 * 更新定时策略状态
	 */
	@Override
	public void updateTimeSts(TimeController timeController)
			throws ServiceException {
		try {
			timeControllerDao.updateTimeSts(timeController);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public void startTimeSts(TimeController timeController)
			throws ServiceException {
		try {
			timeControllerDao.startTimeSts(timeController);
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
			timeControllerDao.updateTimeController(seId, tcId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	public TimeControllerDao getTimeControllerDao() {
		return timeControllerDao;
	}

	public void setTimeControllerDao(TimeControllerDao timeControllerDao) {
		this.timeControllerDao = timeControllerDao;
	}


}