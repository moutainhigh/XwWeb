package  app.creditapp.acc.loan.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.loan.entity.AcLnPmLog;

/**
* Title: AcLnPmLogBo.java
* Description:
**/
public interface AcLnPmLogBo {

	public AcLnPmLog getById(AcLnPmLog acLnPmLog) throws ServiceException;

	public void del(AcLnPmLog acLnPmLog) throws ServiceException;

	public void insert(AcLnPmLog acLnPmLog) throws ServiceException;

	public void update(AcLnPmLog acLnPmLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLnPmLog acLnPmLog) throws ServiceException;

}