package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.SysCache;
/**
* Title: SysCacheDao.java
* Description:
**/
public interface SysCacheDao {

	public SysCache getById(SysCache sysCache) throws DAOException;

	public void del(SysCache sysCache) throws DAOException;

	public void insert(SysCache sysCache) throws DAOException;

	public void update(SysCache sysCache) throws DAOException;
	
	public int getCount(SysCache sysCache) throws DAOException;

	public List<SysCache > findByPage(SysCache sysCache) throws DAOException;

	public List<SysCache> getSysCache() throws DAOException;

}