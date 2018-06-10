package  app.creditapp.acc.option.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcLnSetlmtLog;

/**
* Title: AcLnSetlmtLogBo.java
* Description:
**/
public interface AcLnSetlmtLogBo {

	public AcLnSetlmtLog getById(AcLnSetlmtLog acLnSetlmtLog) throws ServiceException;

	public void del(AcLnSetlmtLog acLnSetlmtLog) throws ServiceException;

	public void insert(AcLnSetlmtLog acLnSetlmtLog) throws ServiceException;

	public void update(AcLnSetlmtLog acLnSetlmtLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLnSetlmtLog acLnSetlmtLog) throws ServiceException;

}