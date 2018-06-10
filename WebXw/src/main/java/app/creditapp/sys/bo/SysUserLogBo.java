package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysUserLog;

/**
* Title: SysUserLogBo.java
* Description:
**/
public interface SysUserLogBo {

	public SysUserLog getById(SysUserLog sysUserLog) throws ServiceException;

	public void del(SysUserLog sysUserLog) throws ServiceException;

	public void insert(SysUserLog sysUserLog) throws ServiceException;

	public void update(SysUserLog sysUserLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysUserLog sysUserLog) throws ServiceException;
	
	public void userLogin(SysUserLog sysUserLog) throws ServiceException;
	
	public void userLogout(SysUserLog sysUserLog) throws ServiceException;

}