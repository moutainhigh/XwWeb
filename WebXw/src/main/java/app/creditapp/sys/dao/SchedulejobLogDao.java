package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.SchedulejobLog;
/**
* Title: SchedulejobLogDao.java
* Description:
**/
public interface SchedulejobLogDao {

	public SchedulejobLog getById(SchedulejobLog schedulejobLog) throws DAOException;

	public void del(SchedulejobLog schedulejobLog) throws DAOException;

	public void insert(SchedulejobLog schedulejobLog) throws DAOException;

	public void update(SchedulejobLog schedulejobLog) throws DAOException;
	
	public int getCount(SchedulejobLog schedulejobLog) throws DAOException;

	public List<SchedulejobLog > findByPage(SchedulejobLog schedulejobLog) throws DAOException;
	
	public String getSchedulejobLogSeq() throws DAOException;

}