package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.entity.SysRoleButton;
import app.creditapp.sys.entity.SysButton;
import app.creditapp.sys.entity.SysRole;

/**
* Title: SysRoleBo.java
* Description:
**/
public interface SysRoleBo {

	public SysRole getById(SysRole sysRole) throws ServiceException;

	public void del(SysRole sysRole) throws ServiceException;

	public void insert(SysRole sysRole) throws ServiceException;

	public void update(SysRole sysRole) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysRole sysRole) throws ServiceException;
	
	public Ipage findByPageForPop(Ipage ipg, SysRole sysRole) throws ServiceException;
	
	public String checkJsp(String role_no) throws ServiceException;
	
	public List<SysButton> getMenuButton(String menu_no) throws ServiceException;
	
	public List<SysRoleButton> getButtonList(String role_no,String menu_no) throws ServiceException;
	
	public String saveButton(String role_no,String menu_no,String butlist) throws ServiceException;
 
	public String getAllJsonMenu() throws ServiceException;

	public String getAllMenuByRoleNo(String role_no) throws ServiceException;
	
	public int getCountByRoleNo(String role_no) throws ServiceException;

}