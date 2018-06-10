package  app.creditapp.wkf.dao.impl;
import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.wkf.dao.WkfApprovalRoleDao;
import app.creditapp.wkf.entity.WkfApprovalRole;
/**
* Title: WkfApprovalRoleDaoImpl.java
* Description:
**/
public class WkfApprovalRoleDaoImpl extends BaseIbatisDao implements WkfApprovalRoleDao {

	public void del(WkfApprovalRole wkfApprovalRole) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WkfApprovalRole.del", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<WkfApprovalRole> findByPage(WkfApprovalRole wkfApprovalRole) throws DAOException {
		List<WkfApprovalRole> wkfApprovalRoleList = null;
		try {
			wkfApprovalRoleList = getSqlMapClientTemplate().queryForList("WkfApprovalRole.findByPage", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalRoleList;
	}

	public WkfApprovalRole getById(WkfApprovalRole wkfApprovalRole) throws DAOException {
		try {
			wkfApprovalRole = (WkfApprovalRole) getSqlMapClientTemplate()
					.queryForObject("WkfApprovalRole.getById", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalRole;
	}

	@SuppressWarnings("unchecked")
	public List<WkfApprovalRole> getAllList(WkfApprovalRole wkfApprovalRole) throws DAOException {
		List<WkfApprovalRole> wkfApprovalRoleList = null;
		try {
			wkfApprovalRoleList = getSqlMapClientTemplate().queryForList("WkfApprovalRole.getAllList",wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalRoleList;
	}
	public int getCount(WkfApprovalRole wkfApprovalRole) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WkfApprovalRole.findPage.count", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(WkfApprovalRole wkfApprovalRole) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WkfApprovalRole.insert",
					wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(WkfApprovalRole wkfApprovalRole) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WkfApprovalRole.update",
					wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<WkfApprovalRole> findByPagePop(WkfApprovalRole wkfApprovalRole)
			throws DAOException {
		List<WkfApprovalRole> wkfApprovalRoleList = null;
		try {
			wkfApprovalRoleList = getSqlMapClientTemplate().queryForList("WkfApprovalRole.findByPagePop", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalRoleList;
	}
	
	public List<WkfApprovalRole> findWkfApprovalRole(WkfApprovalRole wkfApprovalRole)
			throws DAOException {
		List<WkfApprovalRole> wkfApprovalRoleList = null;
		try {
			wkfApprovalRoleList = getSqlMapClientTemplate().queryForList("WkfApprovalRole.findRoleList", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalRoleList;
		}
	public int getCountPop(WkfApprovalRole wkfApprovalRole) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WkfApprovalRole.findPage.countPop", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public List<WkfApprovalRole> findByPageTwoPop(
			WkfApprovalRole wkfApprovalRole) throws DAOException {
		List<WkfApprovalRole> wkfApprovalRoleList = null;
		try {
			wkfApprovalRoleList = getSqlMapClientTemplate().queryForList("WkfApprovalRole.findByPageTwoPop", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalRoleList;
	}

	public int getCountTwoPop(WkfApprovalRole wkfApprovalRole)
			throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WkfApprovalRole.findPage.countPopTwo", wkfApprovalRole);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
}