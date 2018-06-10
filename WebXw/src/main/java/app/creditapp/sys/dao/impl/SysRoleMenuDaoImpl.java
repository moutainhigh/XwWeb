package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysRoleMenuDao;
import app.creditapp.sys.entity.SysRoleMenu;
/**
* Title: SysRoleMenuDaoImpl.java
* Description:
**/
public class SysRoleMenuDaoImpl extends BaseIbatisDao implements SysRoleMenuDao {

	public void del(SysRoleMenu sysRoleMenu) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysRoleMenu.del", sysRoleMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysRoleMenu> findByPage(SysRoleMenu sysRoleMenu) throws DAOException {
		List sysRoleMenuList = null;
		try {
			sysRoleMenuList = getSqlMapClientTemplate().queryForList(
					"SysRoleMenu.findByPage", sysRoleMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysRoleMenuList;
	}

	public SysRoleMenu getById(SysRoleMenu sysRoleMenu) throws DAOException {
		try {
			sysRoleMenu = (SysRoleMenu) getSqlMapClientTemplate()
					.queryForObject("SysRoleMenu.getById", sysRoleMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysRoleMenu;
	}

	public int getCount(SysRoleMenu sysRoleMenu) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysRoleMenu.findPage.count", sysRoleMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysRoleMenu sysRoleMenu) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysRoleMenu.insert",
					sysRoleMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysRoleMenu sysRoleMenu) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysRoleMenu.update",
					sysRoleMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public void delRoleMenuByRoleNo(String roleNo) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysRoleMenu.delRoleMenuByRoleNo", roleNo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public void saveAndUpdate(SysRoleMenu sysRoleMenu) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysRoleMenu.saveAndUpdate",
					sysRoleMenu);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysRoleMenu> checkJsp(String role_no) throws DAOException {
		List<SysRoleMenu> rolelist = null;
		try {
			rolelist = getSqlMapClientTemplate().queryForList("SysRoleMenu.checkjsp",role_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("rolenoºØ∫œ≤È—Ø ß∞‹");
		}
		return rolelist;
	}
}