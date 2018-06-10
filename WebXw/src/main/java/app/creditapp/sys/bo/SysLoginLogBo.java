package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysLoginLog;

/**
* Title: SysLoginLogBo.java
* Description:
**/

public interface SysLoginLogBo {
	  public SysLoginLog getById(String id) throws ServiceException;
	  public void del(String id) throws ServiceException;
	  public void insertOrUpdate(SysLoginLog sysloginlog) throws ServiceException;
	  public Ipage findByPage(Ipage ipg,SysLoginLog sysloginlog) throws ServiceException;
}
