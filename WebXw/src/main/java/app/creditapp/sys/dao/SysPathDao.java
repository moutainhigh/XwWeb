package  app.creditapp.sys.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.sys.entity.SysPath;
/**
* Title: SysPathDao.java
* Description:
**/
public interface SysPathDao {

	public SysPath getById(SysPath sysPath) throws DAOException;

	public void del(SysPath sysPath) throws DAOException;

	public void insert(SysPath sysPath) throws DAOException;

	public void update(SysPath sysPath) throws DAOException;
	
	public int getCount(SysPath sysPath) throws DAOException;

	public List<SysPath> findByPage(SysPath sysPath) throws DAOException;

	public List<SysPath> findList() throws DAOException;
}