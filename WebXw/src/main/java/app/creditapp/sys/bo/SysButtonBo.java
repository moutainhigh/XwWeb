package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysButton;

/**
* Title: SysButtonBo.java
* Description:
**/
public interface SysButtonBo {

	public SysButton getById(SysButton sysButton) throws ServiceException;

	public void del(SysButton sysButton) throws ServiceException;

	public void insert(SysButton sysButton) throws ServiceException;

	public void update(SysButton sysButton) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysButton sysButton) throws ServiceException;
	
	public List<SysButton> findAllByMenu(SysButton sysButton) throws ServiceException;

}