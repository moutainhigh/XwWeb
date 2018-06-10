package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysOrg;

/**
* Title: SysOrgBo.java
* Description:
**/
public interface SysOrgBo {

	public SysOrg getById(SysOrg sysOrg) throws ServiceException;

	public void del(SysOrg sysOrg) throws ServiceException;

	public void insert(SysOrg sysOrg) throws ServiceException;

	public void update(SysOrg sysOrg) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysOrg sysOrg) throws ServiceException;
	
	public String getBrnoChildren(String orgNo,String deepLev)throws ServiceException;


}