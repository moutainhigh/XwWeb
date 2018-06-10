package app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysLog;

/**
 * Title: SysLogBo.java Description:
 **/

public interface SysLogBo {
	public SysLog getByOp_id(String op_id) throws ServiceException;

	public void delete(String op_id) throws ServiceException;

	public void insertOrUpdate(SysLog syslog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysLog syslog) throws ServiceException;
}
