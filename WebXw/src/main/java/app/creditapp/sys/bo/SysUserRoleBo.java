package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysUserRole;

/**
* Title: SysUserRoleBo.java
* Description:
**/
public interface SysUserRoleBo {

	public SysUserRole getById(SysUserRole sysUserRole) throws ServiceException;
	
	public SysUserRole getByRoleAndBrno(SysUserRole sysUserRole) throws ServiceException;

	public void del(SysUserRole sysUserRole) throws ServiceException;
	
//	public void delExistsRel(SysUserRole sysUserRole) throws ServiceException;
	
	public void insert(SysUserRole sysUserRole) throws ServiceException;

	public void update(SysUserRole sysUserRole) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysUserRole sysUserRole) throws ServiceException;
	
	public List<SysUserRole> getAllRoleNo(String login_id) throws ServiceException;

	//获取登录用户对应得事业部
	public String getBsNoByLoginId(String login_id) throws ServiceException;

	public void delByLoginId(SysUserRole sysUserRole)throws ServiceException;
}