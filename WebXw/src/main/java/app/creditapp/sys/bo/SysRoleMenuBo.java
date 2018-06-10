package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysRoleMenu;

/**
* Title: SysRoleMenuBo.java
* Description:
**/
public interface SysRoleMenuBo {

	public SysRoleMenu getById(SysRoleMenu sysRoleMenu) throws ServiceException;

	public void del(SysRoleMenu sysRoleMenu) throws ServiceException;

	public void insert(SysRoleMenu sysRoleMenu) throws ServiceException;

	public void update(SysRoleMenu sysRoleMenu) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysRoleMenu sysRoleMenu) throws ServiceException;

	public String saveAndUpdate(String menus, String role_no) throws ServiceException;

}