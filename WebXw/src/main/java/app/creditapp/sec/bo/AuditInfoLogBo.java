package  app.creditapp.sec.bo;

import app.base.ServiceException;
import app.creditapp.sec.entity.AuditInfoLog;
import app.util.toolkit.Ipage;


/**
* Title: AuditInfoLogBo.java
* Description:
**/

public interface AuditInfoLogBo {

	public AuditInfoLog getById(long id) throws ServiceException;

	public void del(long id) throws ServiceException;

	public void insert(AuditInfoLog auditInfoLog) throws ServiceException;

	public void update(AuditInfoLog auditInfoLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AuditInfoLog auditInfoLog) throws ServiceException;

	public int getCount(AuditInfoLog auditInfoLog) throws ServiceException;
}
