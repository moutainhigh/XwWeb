package app.creditapp.sys.bo;

import app.base.ServiceException;

import app.creditapp.entity.SysGlobal;

/**
 * Title: SysAreaBo.java Description:
 * 
 **/
public interface SysGlobalBo {
	public SysGlobal getSysGlobal() throws ServiceException;

	public void updateSts(SysGlobal sysGlobal) throws ServiceException;
	
	public void updateDocSize(SysGlobal sysGlobal) throws ServiceException;
}
