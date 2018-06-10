package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.SysRoleMenu;
/**
* Title: SysRoleMenuDao.java
* Description:
**/
public interface SysRoleMenuDao {

	public SysRoleMenu getById(SysRoleMenu sysRoleMenu) throws DAOException;

	public void del(SysRoleMenu sysRoleMenu) throws DAOException;

	public void insert(SysRoleMenu sysRoleMenu) throws DAOException;

	public void update(SysRoleMenu sysRoleMenu) throws DAOException;
	
	public int getCount(SysRoleMenu sysRoleMenu) throws DAOException;

	public List<SysRoleMenu > findByPage(SysRoleMenu sysRoleMenu) throws DAOException;
	
	public void delRoleMenuByRoleNo(String roleNo) throws DAOException;
	
	public void saveAndUpdate(SysRoleMenu sysRoleMenu) throws DAOException;
	
	public List<SysRoleMenu> checkJsp(String role_no) throws DAOException;

}