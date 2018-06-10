package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SchedulejobLog;

/**
* Title: SchedulejobLogBo.java
* Description:
**/
public interface SchedulejobLogBo {

	public SchedulejobLog getById(SchedulejobLog schedulejobLog) throws ServiceException;

	public void del(SchedulejobLog schedulejobLog) throws ServiceException;

	public void insert(SchedulejobLog schedulejobLog) throws ServiceException;

	public void update(SchedulejobLog schedulejobLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SchedulejobLog schedulejobLog) throws ServiceException;

}