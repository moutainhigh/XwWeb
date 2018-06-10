package app.util.syslog.bo;

import app.base.ServiceException;
import app.util.syslog.entity.SysException;
import app.util.toolkit.Ipage;

public interface SysExceptionBo {
	public void insertNewExceptionLog(String msg, Throwable throwException);

	public void insertNewExceptionLog(Exception exception);

	public void insertNewExceptionLog(Exception exception, String userId);
	
	public void insertNewWorkExceptionLog(Exception exception, String appId);
	
	public SysException getById(SysException sysException) throws ServiceException;

	public void del(SysException sysException) throws ServiceException;

	public void insert(SysException sysException) throws ServiceException;

	public void update(SysException sysException) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysException sysException) throws ServiceException;

}
