package app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.SysUserLog;

/**
 * Title: SysUserLogDao.java Description:
 * 
 **/
public interface SysUserLogDao {

	public SysUserLog getById(SysUserLog sysUserLog) throws DAOException;

	public void del(SysUserLog sysUserLog) throws DAOException;

	public void insert(SysUserLog sysUserLog) throws DAOException;

	public void update(SysUserLog sysUserLog) throws DAOException;

	public int getCount(SysUserLog sysUserLog) throws DAOException;

	public List<SysUserLog> findByPage(SysUserLog sysUserLog) throws DAOException;

	public void userLogin(SysUserLog sysUserLog) throws DAOException;

	public void userLogout(SysUserLog sysUserLog) throws DAOException;

}