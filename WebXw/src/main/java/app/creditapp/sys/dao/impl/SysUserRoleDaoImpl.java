package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysUserRoleDao;
import app.creditapp.sys.entity.SysUserRole;
/**
* Title: SysUserRoleDaoImpl.java
* Description:
**/
public class SysUserRoleDaoImpl extends BaseIbatisDao implements SysUserRoleDao {

	public void del(SysUserRole sysUserRole) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysUserRole.del", sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void delByLoginId(SysUserRole sysUserRole) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysUserRole.delByLoginId", sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysUserRole> findByPage(SysUserRole sysUserRole) throws DAOException {
		List sysUserRoleList = null;
		try {
			sysUserRoleList = getSqlMapClientTemplate().queryForList(
					"SysUserRole.findByPage", sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysUserRoleList;
	}

	public SysUserRole getById(SysUserRole sysUserRole) throws DAOException {
		try {
			sysUserRole = (SysUserRole) getSqlMapClientTemplate()
					.queryForObject("SysUserRole.getById", sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysUserRole;
	}
	
	public String getRoleNo(String op_no) throws DAOException {
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUser_no(op_no);
		String role_no ="";
		try {
			role_no = (String) getSqlMapClientTemplate()
					.queryForObject("SysUserRole.getRoleNo", sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return role_no;
	}
	
	public SysUserRole getByRoleAndBrno(SysUserRole sysUserRole) throws DAOException {
		try {
			sysUserRole = (SysUserRole) getSqlMapClientTemplate()
			.queryForObject("SysUserRole.getByRoleAndBrno", sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysUserRole;
	}

	public int getCount(SysUserRole sysUserRole) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysUserRole.findPage.count", sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysUserRole sysUserRole) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysUserRole.insert",
					sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysUserRole sysUserRole) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysUserRole.update",
					sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public List<SysUserRole> getAllRoleNo(String login_id) throws DAOException {
		List sysUserRoleList = null;
		try {
			sysUserRoleList = getSqlMapClientTemplate().queryForList(
					"SysUserRole.getAllRoleNO", login_id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysUserRoleList;
	}

	public void delExistsRel(SysUserRole sysUserRole) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysUserRole.delByRole", sysUserRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}
}