package  app.creditapp.sys.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.entity.SysRoleButton;
import app.creditapp.sys.entity.SysButton;
import app.creditapp.sys.dao.SysRoleDao;
import app.creditapp.sys.entity.SysRole;
import app.creditapp.sys.entity.SysRoleMenu;
/**
* Title: SysRoleDaoImpl.java
* Description:
**/
public class SysRoleDaoImpl extends BaseIbatisDao implements SysRoleDao {

	public void del(SysRole sysRole) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysRole.del", sysRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysRole> findByPage(SysRole sysRole) throws DAOException {
		List sysRoleList = null;
		try {
			sysRoleList = getSqlMapClientTemplate().queryForList(
					"SysRole.findByPage", sysRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysRoleList;
	}
	public List<SysRole> findByPageForPop(SysRole sysRole) throws DAOException{
		List sysRoleList = null;
		try {
			sysRoleList = getSqlMapClientTemplate().queryForList(
					"SysRole.findByPageForPop", sysRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysRoleList;
	}
	public SysRole getById(SysRole sysRole) throws DAOException {
		try {
			sysRole = (SysRole) getSqlMapClientTemplate()
					.queryForObject("SysRole.getById", sysRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysRole;
	}

	public int getCount(SysRole sysRole) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysRole.findPage.count", sysRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getfindByPageForPopCount(SysRole sysRole) throws DAOException{
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysRole.findByPageForPop.count", sysRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountByRoleNo(String role_no) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysRole.getCountByRoleNo.count", role_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public void insert(SysRole sysRole) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysRole.insert",
					sysRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysRole sysRole) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysRole.update",
					sysRole);
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

	public void deleteButton(SysRoleButton sysRoleButton) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysRoleButton.deleteButton",sysRoleButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("…æ≥˝ ß∞‹");
		}
	}
	public List<SysRoleButton> getButtonList(SysRoleButton sysRoleButton)
			throws DAOException {
		List<SysRoleButton> List = null;
		try {
			List = getSqlMapClientTemplate().queryForList("SysRoleButton.getButtonList",sysRoleButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("≤È—Ø∞¥≈• ß∞‹");
		}
		return List;
	}

	public List<SysButton> getMenuButton(String menu_no) throws DAOException {
		List<SysButton> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysButton.getMenuButton",menu_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("≤È—Ø∞¥≈• ß∞‹");
		}
		return lst;
	}

	public void saveButton(SysRoleButton sysRoleButton) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysRoleButton.saveButton",sysRoleButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("–¬‘ˆ ß∞‹");
		}
	}
}