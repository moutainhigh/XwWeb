package  app.creditapp.pac.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.pac.entity.PacBase;
/**
* Title: PacBaseDao.java
* Description:
**/
public interface PacBaseDao {

	public PacBase getById(PacBase pacBase) throws DAOException;

	public void del(PacBase pacBase) throws DAOException;

	public void insert(PacBase pacBase) throws DAOException;

	public void update(PacBase pacBase) throws DAOException;
	
	public int getCount(PacBase pacBase) throws DAOException;

	public List<PacBase > findByPage(PacBase pacBase) throws DAOException;

	public List<PacBase> listPacBase(PacBase pacBase) throws DAOException;

	/**
	 * @方法说明:
	 * @param pacId
	 * @return  void
	 * @修改说明:
	 */
	public void updateDownCnt(String pacId) throws DAOException;

	/**
	 * @param pacBase
	 * @return
	 */
	public PacBase getLastPacBase(PacBase pacBase) throws DAOException;
	
	public List<PacBase> getyn(PacBase pacBase) throws DAOException;
	
	public int getPacCount(PacBase pacBase) throws DAOException;

}