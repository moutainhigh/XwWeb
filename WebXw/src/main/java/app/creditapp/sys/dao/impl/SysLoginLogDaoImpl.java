package  app.creditapp.sys.dao.impl;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysLoginLogDao;
import app.creditapp.sys.entity.SysLoginLog;
/**
* Title: SysLoginLogDaoImpl.java
* Description:
**/

public class SysLoginLogDaoImpl extends BaseIbatisDao implements SysLoginLogDao {
	/*
	 * (non-Javadoc)
	 * @see app.creditapp.dao.SysLoginLogDAO#del(java.lang.String)
	 */
public void del(String id) throws DAOException {
    try {
      getSqlMapClientTemplate().delete("SysLoginLog.del", id);
    }catch (Exception e) {
      log.error(e);
      throw new DAOException("删除失败");
    }
  }
/*
 * (non-Javadoc)
 * @see app.creditapp.dao.SysLoginLogDAO#findByPage(java.util.Map)
 */
  public List<SysLoginLog> findByPage(Map map) throws DAOException {
    List sysloginloglist = null;
    try {
      sysloginloglist =  getSqlMapClientTemplate().queryForList("SysLoginLog.findByPage",map);
    } catch (Exception e) {
      log.error(e);
      throw new DAOException("分页查询失败");
    }
    return sysloginloglist;
  }
  /*
   * (non-Javadoc)
   * @see app.creditapp.dao.SysLoginLogDAO#getById(java.lang.String)
   */
  public SysLoginLog getById(String id) throws DAOException {
    SysLoginLog sysloginlog = null;
    try {
      sysloginlog = (SysLoginLog)getSqlMapClientTemplate().queryForObject("SysLoginLog.getById", id);
    }catch (Exception e) {
      log.error(e);
      throw new DAOException("查询失败");
    }
    return sysloginlog;
  }
  /*
   * (non-Javadoc)
   * @see app.creditapp.dao.SysLoginLogDAO#getCount(app.creditapp.entity.SysLoginLog)
   */
  public int getCount(SysLoginLog sysloginlog) throws DAOException {
    
    int count = 0;
    try {
      count = (Integer)getSqlMapClientTemplate().queryForObject("SysLoginLog.findPage.count",sysloginlog);
    } catch (Exception e) {
      log.error(e);
      throw new DAOException("查询统计失败");
    }
    return count;
  }
  /*
   * @overload 用于登陆的时候查询是否有该SessionID在历史记录表中
   * (non-Javadoc)
   * @see app.creditapp.dao.SysLoginLogDAO#getCount(java.lang.String)
   */
  public int getCount(String sessionid) throws DAOException {
	    
	    int count = 0;
	    try {
	    	SysLoginLog sysloginlog = new SysLoginLog();
	    	sysloginlog.setSessionId(sessionid);
	      count = (Integer)getSqlMapClientTemplate().queryForObject("SysLoginLog.findPage.count",sysloginlog);
	    } catch (Exception e) {
	      log.error(e);
	      throw new DAOException("查询统计失败");
	    }
	    return count;
	  }
  /*
   * (non-Javadoc)
   * @see app.creditapp.dao.SysLoginLogDAO#insert(app.creditapp.entity.SysLoginLog)
   */
  public void insert(SysLoginLog sysloginlog) throws DAOException {
    try {
      getSqlMapClientTemplate().insert("SysLoginLog.insert", sysloginlog);
    }catch (Exception e) {
      log.error(e);
      throw new DAOException("新增失败");
    }
    
  }
  /*
   * (non-Javadoc)
   * @see app.creditapp.dao.SysLoginLogDAO#update(app.creditapp.entity.SysLoginLog)
   */
  public void update(SysLoginLog sysloginlog) throws DAOException {
    try {
      getSqlMapClientTemplate().update("SysLoginLog.update", sysloginlog);
    } catch (Exception e) {
      log.error(e);
      throw new DAOException("更新失败");
    }    
  }
  }
