package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcLnSetlmtLogDao;
import app.creditapp.acc.option.entity.AcLnSetlmtLog;
/**
* Title: AcLnSetlmtLogDaoImpl.java
* Description:
**/
public class AcLnSetlmtLogDaoImpl extends BaseIbatisDao implements AcLnSetlmtLogDao {

	public void del(AcLnSetlmtLog acLnSetlmtLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnSetlmtLog.del", acLnSetlmtLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLnSetlmtLog> findByPage(AcLnSetlmtLog acLnSetlmtLog) throws DAOException {
		List acLnSetlmtLogList = null;
		try {
			acLnSetlmtLogList = getSqlMapClientTemplate().queryForList(
					"AcLnSetlmtLog.findByPage", acLnSetlmtLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnSetlmtLogList;
	}

	public AcLnSetlmtLog getById(AcLnSetlmtLog acLnSetlmtLog) throws DAOException {
		try {
			acLnSetlmtLog = (AcLnSetlmtLog) getSqlMapClientTemplate()
					.queryForObject("AcLnSetlmtLog.getById", acLnSetlmtLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnSetlmtLog;
	}

	public int getCount(AcLnSetlmtLog acLnSetlmtLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnSetlmtLog.findPage.count", acLnSetlmtLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLnSetlmtLog acLnSetlmtLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLnSetlmtLog.insert",
					acLnSetlmtLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLnSetlmtLog acLnSetlmtLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnSetlmtLog.update",
					acLnSetlmtLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}