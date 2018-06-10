package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sys.bo.SysUserBo;
import app.creditapp.sys.bo.SysUserRoleBo;
import app.creditapp.sys.dao.SysUserDao;
import app.creditapp.sys.dao.SysUserRoleDao;
import app.creditapp.sys.entity.SysUser;
import app.creditapp.sys.entity.SysUserRole;
import app.util.toolkit.Ipage;
/**
* Title: SysUserRoleBoImplImpl.java
* Description:
**/
public class SysUserRoleBoImpl extends BaseService implements SysUserRoleBo {

	private SysUserRoleDao sysUserRoleDao;
	private SysUserDao sysUserDao; 

	public void del(SysUserRole sysUserRole) throws ServiceException {
		try {
			sysUserRoleDao.del(sysUserRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void delByLoginId(SysUserRole sysUserRole) throws ServiceException {
		try {
			sysUserRoleDao.delByLoginId(sysUserRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}


	public Ipage findByPage(Ipage ipg, SysUserRole sysUserRole)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysUserRoleDao
					.getCount(sysUserRole) });// 初始化分页类
			sysUserRole.setStartNumAndEndNum (ipg);
			ipg.setResult(sysUserRoleDao.findByPage(sysUserRole));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysUserRole getById(SysUserRole sysUserRole) throws ServiceException {
		try {
			sysUserRole = sysUserRoleDao.getById(sysUserRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysUserRole;
	}
	
	public SysUserRole getByRoleAndBrno(SysUserRole sysUserRole) throws ServiceException {
		try {
			sysUserRole = sysUserRoleDao.getByRoleAndBrno(sysUserRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysUserRole;
	}

	public void insert(SysUserRole sysUserRole) throws ServiceException {
		try {
			String arr[] = sysUserRole.getRole_no().split("@");
			for(int i=0; i<arr.length; i++){
				sysUserRole.setRole_no(arr[i]);
				sysUserRoleDao.delExistsRel(sysUserRole);
				sysUserRoleDao.insert(sysUserRole);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysUserRole sysUserRole) throws ServiceException {
		try {
			sysUserRoleDao.update(sysUserRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<SysUserRole> getAllRoleNo(String login_id) throws ServiceException {
		List<SysUserRole> list=null;
		try {
			list = sysUserRoleDao.getAllRoleNo(login_id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	//获取登录用户对应得事业部
	public String getBsNoByLoginId(String user_no) throws ServiceException {
		String bs_no = "";
		try {
			SysUser sysUser = new SysUser();
			sysUser.setUser_no(user_no);
			sysUser = sysUserDao.getByLoginId(sysUser);
//			bs_no = sysUser.getBs_no();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return bs_no;
	}
	public SysUserRoleDao getSysUserRoleDao() {
		return sysUserRoleDao;
	}

	public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
}