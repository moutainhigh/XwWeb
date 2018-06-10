package  app.creditapp.sys.dao;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sys.entity.SysLog;
/**
* Title: SysLogDao.java
* Description:
**/

public interface SysLogDao {
	
	  public SysLog getByOp_id(String op_id) throws DAOException;
	  public void delete(String op_id) throws DAOException;
	  public void insert(SysLog syslog) throws DAOException;
	  public void update(SysLog syslog) throws DAOException;
	  public int getCount(SysLog syslog) throws DAOException;
	  public List<SysLog> findByPage(Map map) throws DAOException;
}
