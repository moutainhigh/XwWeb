package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysPath;

/**
* Title: SysPathBo.java
* Description:
**/
public interface SysPathBo {

	public SysPath getById(SysPath sysPath) throws ServiceException;

	public void del(SysPath sysPath) throws ServiceException;

	public void insert(SysPath sysPath) throws ServiceException;

	public void update(SysPath sysPath) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysPath sysPath) throws ServiceException;
	
	
	public List<SysPath> findList() throws ServiceException;
}