package  app.creditapp.sys.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysRoleMenuBo;
import app.creditapp.sys.dao.SysRoleMenuDao;
import app.creditapp.sys.entity.SysRoleMenu;
/**
* Title: SysRoleMenuBoImplImpl.java
* Description:
**/
public class SysRoleMenuBoImpl extends BaseService implements SysRoleMenuBo {

	private SysRoleMenuDao sysRoleMenuDao;

	public void del(SysRoleMenu sysRoleMenu) throws ServiceException {
		try {
			sysRoleMenuDao.del(sysRoleMenu);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysRoleMenu sysRoleMenu)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysRoleMenuDao
					.getCount(sysRoleMenu) });// 初始化分页类
			sysRoleMenu.setStartNumAndEndNum (ipg);
			ipg.setResult(sysRoleMenuDao.findByPage(sysRoleMenu));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysRoleMenu getById(SysRoleMenu sysRoleMenu) throws ServiceException {
		try {
			sysRoleMenu = sysRoleMenuDao.getById(sysRoleMenu);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysRoleMenu;
	}

	public void insert(SysRoleMenu sysRoleMenu) throws ServiceException {
		try {
			sysRoleMenuDao.insert(sysRoleMenu);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysRoleMenu sysRoleMenu) throws ServiceException {
		try {
			sysRoleMenuDao.update(sysRoleMenu);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public SysRoleMenuDao getSysRoleMenuDao() {
		return sysRoleMenuDao;
	}

	public void setSysRoleMenuDao(SysRoleMenuDao sysRoleMenuDao) {
		this.sysRoleMenuDao = sysRoleMenuDao;
	}

	/**
	 * @方法说明:
	 * @param menus
	 * @param roleNo
	 * @throws ServiceException 
	 * @see app.creditapp.sys.bo.SysRoleMenuBo#saveAndUpdate(java.lang.String, java.lang.String)
	 * @修改说明:
	 */
	public String saveAndUpdate(String menus, String role_no)
			throws ServiceException {
		String logo = null;
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		String[] menusArr = menus.split(",");
		sysRoleMenu.setRole_no(role_no);
		sysRoleMenuDao.delRoleMenuByRoleNo(role_no);
		for (int i = 1; i < menusArr.length; i++) {
			sysRoleMenu.setMenu_no(menusArr[i]);
			sysRoleMenuDao.saveAndUpdate(sysRoleMenu);
		}
		logo = "1";
		return logo;
	}
}