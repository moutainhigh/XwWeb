package  app.creditapp.sec.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sec.dao.SecurityAuditDao;
import app.creditapp.sec.entity.SecurityAudit;


/**
* Title: SecurityAuditDaoImpl.java
* Description:
**/

public class SecurityAuditDaoImpl extends BaseIbatisDao implements SecurityAuditDao {

	public void del(String id) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SecurityAudit.del", id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("删除失败");
		}
	}

	public List<SecurityAudit> findByPage(Map map) throws DAOException {
		List secAuditList = null;
		try {
			secAuditList = getSqlMapClientTemplate().queryForList(
					"SecurityAudit.findByPage", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return secAuditList;
	}

	public SecurityAudit getById(String id) throws DAOException {
		SecurityAudit secAudit = null;
		try {
			secAudit = (SecurityAudit) getSqlMapClientTemplate()
					.queryForObject("SecurityAudit.getById", id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return secAudit;
	}

	public int getCount(SecurityAudit secAudit) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SecurityAudit.findPage.count", secAudit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}

	public void insert(SecurityAudit secAudit) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SecurityAudit.insert",
					secAudit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("新增失败");
		}

	}

	public void update(SecurityAudit secAudit) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SecurityAudit.update",
					secAudit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("更新失败");
		}
	}
	
	public void updateIsUse(SecurityAudit secAudit) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SecurityAudit.updateIsUse",
					secAudit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("更新失败");
		}
	}
	/**Xl 安全审计密码校验规则
	 * @return List
	 */
	public List<SecurityAudit> getListForSec(SecurityAudit secAudit) throws DAOException {
		List secAuditList = null;
		try {
			secAuditList = getSqlMapClientTemplate().queryForList("SecurityAudit.getListForSec", secAudit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return secAuditList;
	}
	
	public List<SecurityAudit> findAuditListByCode(SecurityAudit secAudit) throws DAOException {
		List secAuditList = null;
		try {
			secAuditList = getSqlMapClientTemplate().queryForList("SecurityAudit.findAuditListByCode", secAudit);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return secAuditList;
	}
}
