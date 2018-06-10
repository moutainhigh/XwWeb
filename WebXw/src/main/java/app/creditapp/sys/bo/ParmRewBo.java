package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.ParmRew;

/**
* Title: ParmRewBo.java
* Description:
**/
public interface ParmRewBo {

	public ParmRew getById(ParmRew parmRew) throws ServiceException;

	public void del(ParmRew parmRew) throws ServiceException;

	public void insert(ParmRew parmRew) throws ServiceException;

	public void update(ParmRew parmRew) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ParmRew parmRew) throws ServiceException;

}