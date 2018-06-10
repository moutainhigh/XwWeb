package  app.util.syslog.dao;
import java.util.List;

import app.base.DAOException;
import app.util.syslog.entity.SysException;
/**
* Title: SysExceptionDao.java
* Description:
**/
public interface SysExceptionDao {

	public SysException getById(SysException sysException) throws DAOException;

	public void del(SysException sysException) throws DAOException;

	public void insert(SysException sysException) throws DAOException;

	public void update(SysException sysException) throws DAOException;
	
	public int getCount(SysException sysException) throws DAOException;

	public List<SysException > findByPage(SysException sysException) throws DAOException;

}