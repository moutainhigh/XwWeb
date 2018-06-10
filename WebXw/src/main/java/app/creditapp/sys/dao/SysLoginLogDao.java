package  app.creditapp.sys.dao;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sys.entity.SysLoginLog;
/**
* Title: SysLoginLogDao.java
* Description:
**/

public interface SysLoginLogDao {
	  public SysLoginLog getById(String id) throws DAOException;
	  public void del(String id) throws DAOException;
	  public void insert(SysLoginLog sysloginlog) throws DAOException;
	  public void update(SysLoginLog sysloginlog) throws DAOException;
	  public int getCount(SysLoginLog sysloginlog) throws DAOException;
	  public int getCount(String session_id) throws DAOException;//重载，用于登录的时候查询日志表中是否有
	                                                              //该SESSION_ID的记录
	  public List<SysLoginLog> findByPage(Map map) throws DAOException;
}
