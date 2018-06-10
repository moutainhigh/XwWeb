package  app.creditapp.acc.log.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.log.dao.AcLnIntstLogDao;
import app.creditapp.acc.log.entity.AcLnIntstLog;
/**
* Title: AcLnIntstLogDaoImpl.java
* Description:
**/
public class AcLnIntstLogDaoImpl extends BaseIbatisDao implements AcLnIntstLogDao {

	public void del(AcLnIntstLog acLnIntstLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnIntstLog.del", acLnIntstLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLnIntstLog> findByPage(AcLnIntstLog acLnIntstLog) throws DAOException {
		List acLnIntstLogList = null;
		try {
			acLnIntstLogList = getSqlMapClientTemplate().queryForList(
					"AcLnIntstLog.findByPage", acLnIntstLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnIntstLogList;
	}

	public AcLnIntstLog getById(AcLnIntstLog acLnIntstLog) throws DAOException {
		try {
			acLnIntstLog = (AcLnIntstLog) getSqlMapClientTemplate()
					.queryForObject("AcLnIntstLog.getById", acLnIntstLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnIntstLog;
	}

	public int getCount(AcLnIntstLog acLnIntstLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnIntstLog.findPage.count", acLnIntstLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLnIntstLog acLnIntstLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLnIntstLog.insert",
					acLnIntstLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLnIntstLog acLnIntstLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnIntstLog.update",
					acLnIntstLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}