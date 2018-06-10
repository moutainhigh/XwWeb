package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.sys.entity.SysUserRole;
/**
* Title: SysUserRelDao.java
* Description:
**/
public interface SysUserRoleDao {

	public SysUserRole getById(SysUserRole sysUserRole) throws DAOException;
	
	public String getRoleNo(String op_no) throws DAOException;
	
	public SysUserRole getByRoleAndBrno(SysUserRole sysUserRole) throws DAOException;

	public void del(SysUserRole sysUserRole) throws DAOException;
	
	public void delExistsRel(SysUserRole sysUserRole) throws DAOException;

	public void insert(SysUserRole sysUserRole) throws DAOException;

	public void update(SysUserRole sysUserRole) throws DAOException;
	
	public int getCount(SysUserRole sysUserRole) throws DAOException;

	public List<SysUserRole > findByPage(SysUserRole sysUserRole) throws DAOException;
	
	public List<SysUserRole> getAllRoleNo(String login_id) throws ServiceException;

	public void delByLoginId(SysUserRole sysUserRole)throws ServiceException;

}