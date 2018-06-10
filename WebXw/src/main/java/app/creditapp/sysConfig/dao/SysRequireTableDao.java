package  app.creditapp.sysConfig.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sysConfig.entity.SysRequireTable;
import app.creditapp.sysConfig.entity.TableInfo;

/**
* Title: RequireLogTableDao.java
* Description:
**/

public interface SysRequireTableDao {

	public SysRequireTable getById(SysRequireTable sysRequireTable) throws DAOException;

	public void del(SysRequireTable sysRequireTable) throws DAOException;

	public void insert(SysRequireTable sysRequireTable) throws DAOException;

	public void update(SysRequireTable sysRequireTable) throws DAOException;
	
	public int getCount(SysRequireTable sysRequireTable) throws DAOException;

	public List<SysRequireTable > findByPage(Map map) throws DAOException;
	
	public List<SysRequireTable> getAll() throws DAOException;
	
	public List<TableInfo> getAllTable(TableInfo tableInfo) throws DAOException;
	
	public List<TableInfo> getAllTableNotExistsDoc(TableInfo tableInfo) throws DAOException;

}
