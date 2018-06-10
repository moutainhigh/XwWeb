package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.TimeController;
/**
* Title: TimeControllerDao.java
* Description:
**/
public interface TimeControllerDao {

	public TimeController getById(TimeController timeController) throws DAOException;

	public void del(TimeController timeController) throws DAOException;

	public int insert(TimeController timeController) throws DAOException;

	public void update(TimeController timeController) throws DAOException;
	
	public int getCount(TimeController timeController) throws DAOException;

	public void updateTimeSts(TimeController timeController) throws DAOException ;
	
	public void startTimeSts(TimeController timeController) throws DAOException ;

	public List<TimeController > findByPage(TimeController timeController) throws DAOException;

	public List<TimeController > getByStId(TimeController timeController) throws DAOException;

	/**
	 * 关联预设任务与定时策略
	 * @param seId
	 * @param tcId
	 * @throws DAOException
	 */
	public void updateTimeController(String seId, String tcId) throws DAOException;
	
	public List<TimeController > findStart() throws DAOException;

	public TimeController findGroupAndName(TimeController timeController) throws DAOException;
}