package  app.creditapp.sys.bo;

import java.util.List;

import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.ScheduleTask;
import app.creditapp.sys.entity.TimeController;

/**
* Title: TimeControllerBo.java
* Description:
**/
public interface TimeControllerBo {

	public TimeController getById(TimeController timeController) throws ServiceException;

	public void del(TimeController timeController) throws ServiceException;

	public void insert(TimeController timeController) throws ServiceException;

	public void update(TimeController timeController) throws ServiceException;
	
	public void updateTimeSts(TimeController timeController) throws ServiceException;
	
	public void startTimeSts(TimeController timeController) throws ServiceException;

	public Ipage findByPage(Ipage ipg, TimeController timeController) throws ServiceException;

	public List<TimeController> getByStId(TimeController timeController) throws ServiceException;
	/**
	 * 关联预设任务与定时策略
	 * @param seId
	 * @param tcId
	 * @throws ServiceException
	 */
	public void updateTimeController(String seId, String tcId) throws ServiceException;
	
	public List<TimeController > findStart() throws ServiceException;
}