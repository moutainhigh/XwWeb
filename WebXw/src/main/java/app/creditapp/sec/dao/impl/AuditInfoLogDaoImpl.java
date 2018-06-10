package  app.creditapp.sec.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sec.dao.AuditInfoLogDao;
import app.creditapp.sec.entity.AuditInfoLog;


/**
* Title: AuditInfoLogDaoImpl.java
* Description:
**/

public class AuditInfoLogDaoImpl extends BaseIbatisDao implements AuditInfoLogDao {

	public void del(long id) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AuditInfoLog.del", id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("É¾³ýÊ§°Ü");
		}
	}

	public List<AuditInfoLog> findByPage(Map map) throws DAOException {
		List auditInfoLogList = null;
		try {
			auditInfoLogList = getSqlMapClientTemplate().queryForList(
					"AuditInfoLog.findByPage", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("·ÖÒ³²éÑ¯Ê§°Ü");
		}
		return auditInfoLogList;
	}

	public AuditInfoLog getById(long id) throws DAOException {
		AuditInfoLog auditInfoLog = null;
		try {
			auditInfoLog = (AuditInfoLog) getSqlMapClientTemplate()
					.queryForObject("AuditInfoLog.getById", id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("²éÑ¯Ê§°Ü");
		}
		return auditInfoLog;
	}

	public int getCount(AuditInfoLog auditInfoLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AuditInfoLog.findPage.count", auditInfoLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("²éÑ¯Í³¼ÆÊ§°Ü");
		}
		return count;
	}

	public void insert(AuditInfoLog auditInfoLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AuditInfoLog.insert",
					auditInfoLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("ÐÂÔöÊ§°Ü");
		}

	}

	public void update(AuditInfoLog auditInfoLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AuditInfoLog.update",
					auditInfoLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("¸üÐÂÊ§°Ü");
		}
	}
}
