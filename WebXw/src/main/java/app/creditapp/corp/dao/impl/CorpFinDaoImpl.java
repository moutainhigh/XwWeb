package  app.creditapp.corp.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.CorpFinDao;
import app.creditapp.corp.entity.CorpFin;
/**
* Title: CorpFinDaoImpl.java
* Description:
**/
public class CorpFinDaoImpl extends BaseIbatisDao implements CorpFinDao {

	public void del(CorpFin corpFin) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpFin.del", corpFin);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CorpFin> findByPage(CorpFin corpFin) throws DAOException {
		List corpFinList = null;
		try {
			corpFinList = getSqlMapClientTemplate().queryForList(
					"CorpFin.findByPage", corpFin);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpFinList;
	}

	public CorpFin getById(CorpFin corpFin) throws DAOException {
		try {
			corpFin = (CorpFin) getSqlMapClientTemplate()
					.queryForObject("CorpFin.getById", corpFin);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpFin;
	}

	public int getCount(CorpFin corpFin) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpFin.findPage.count", corpFin);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpFin corpFin) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpFin.insert",
					corpFin);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CorpFin corpFin) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpFin.update",
					corpFin);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<CorpFin> findByPageQuotaForCorp(CorpFin corpFin)
			throws DAOException {
		List corpFinList = null;
		try {
			corpFinList = getSqlMapClientTemplate().queryForList(
					"CorpFin.findByPageQuotaForCorp", corpFin);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpFinList;
	}

	@Override
	public int getCountQuotaForCorp(CorpFin corpFin) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpFin.findByPageQuotaForCorp.count", corpFin);
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
					.queryForObject("CorpFin.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpSeq;
	}
}