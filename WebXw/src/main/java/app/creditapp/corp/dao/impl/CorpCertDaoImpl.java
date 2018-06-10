package  app.creditapp.corp.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.CorpCertDao;
import app.creditapp.corp.entity.CorpCert;
/**
* Title: CorpCertDaoImpl.java
* Description:
**/
public class CorpCertDaoImpl extends BaseIbatisDao implements CorpCertDao {

	public void del(CorpCert corpCert) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpCert.del", corpCert);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CorpCert> findByPage(CorpCert corpCert) throws DAOException {
		List corpCertList = null;
		try {
			corpCertList = getSqlMapClientTemplate().queryForList(
					"CorpCert.findByPage", corpCert);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpCertList;
	}

	public CorpCert getById(CorpCert corpCert) throws DAOException {
		try {
			corpCert = (CorpCert) getSqlMapClientTemplate()
					.queryForObject("CorpCert.getById", corpCert);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpCert;
	}

	public int getCount(CorpCert corpCert) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpCert.findPage.count", corpCert);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpCert corpCert) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpCert.insert",
					corpCert);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CorpCert corpCert) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpCert.update",
					corpCert);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<CorpCert> findByPageQuotaForCorp(CorpCert corpCert)
			throws DAOException {
		List corpCertList = null;
		try {
			corpCertList = getSqlMapClientTemplate().queryForList(
					"CorpCert.findByPageQuotaForCorp", corpCert);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpCertList;
	}

	@Override
	public int getCountQuotaForCorp(CorpCert corpCert) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpCert.findByPageQuotaForCorp.count", corpCert);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public String getKey() throws DAOException {
		String corpSeq = "";
		try {
			corpSeq = (String) getSqlMapClientTemplate()
					.queryForObject("CorpCert.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpSeq;
	}
}