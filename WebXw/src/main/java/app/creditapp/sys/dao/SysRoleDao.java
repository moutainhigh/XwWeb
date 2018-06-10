package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.entity.SysRoleButton;
import app.creditapp.sys.entity.SysButton;
import app.creditapp.sys.entity.SysRole;
import app.creditapp.sys.entity.SysRoleMenu;
/**
* Title: SysRoleDao.java
* Description:
**/
public interface SysRoleDao {

	public SysRole getById(SysRole sysRole) throws DAOException;

	public void del(SysRole sysRole) throws DAOException;

	public void insert(SysRole sysRole) throws DAOException;

	public void update(SysRole sysRole) throws DAOException;
	
	public int getCount(SysRole sysRole) throws DAOException;
	
	public int getfindByPageForPopCount(SysRole sysRole) throws DAOException;

	public List<SysRole > findByPage(SysRole sysRole) throws DAOException;
	
	public List<SysRole> findByPageForPop(SysRole sysRole) throws DAOException;
	
	public List<SysRoleMenu> checkJsp(String role_no) throws DAOException;
	
	public List<SysRoleButton> getButtonList(SysRoleButton sysRoleButton) throws DAOException;
	
	public List<SysButton> getMenuButton(String menu_no) throws DAOException;
	
	public void deleteButton(SysRoleButton sysRoleButton) throws DAOException;
	
	public void saveButton(SysRoleButton sysRoleButton)  throws DAOException;
	
	public int getCountByRoleNo(String role_no) throws ServiceException;

}