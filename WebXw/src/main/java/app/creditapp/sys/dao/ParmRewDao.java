package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.ParmRew;
/**
* Title: ParmRewDao.java
* Description:
**/
public interface ParmRewDao {

	public ParmRew getById(ParmRew parmRew) throws DAOException;

	public void del(ParmRew parmRew) throws DAOException;

	public void insert(ParmRew parmRew) throws DAOException;

	public void update(ParmRew parmRew) throws DAOException;
	
	public int getCount(ParmRew parmRew) throws DAOException;

	public List<ParmRew > findByPage(ParmRew parmRew) throws DAOException;

}