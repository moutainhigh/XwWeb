package  app.creditapp.cif.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cif.dao.CifPersCareerDao;
import app.creditapp.cif.entity.CifPersCareer;
/**
* Title: CifPersCareerDaoImpl.java
* Description:
**/
public class CifPersCareerDaoImpl extends BaseIbatisDao implements CifPersCareerDao {

	public void del(CifPersCareer cifPersCareer) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CifPersCareer.del", cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CifPersCareer> findByPage(CifPersCareer cifPersCareer) throws DAOException {
		List cifPersCareerList = null;
		try {
			cifPersCareerList = getSqlMapClientTemplate().queryForList(
					"CifPersCareer.findByPage", cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPersCareerList;
	}

	public CifPersCareer getById(CifPersCareer cifPersCareer) throws DAOException {
		try {
			cifPersCareer = (CifPersCareer) getSqlMapClientTemplate()
					.queryForObject("CifPersCareer.getById", cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPersCareer;
	}

	public int getCount(CifPersCareer cifPersCareer) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPersCareer.findPage.count", cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CifPersCareer cifPersCareer) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CifPersCareer.insert",
					cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CifPersCareer cifPersCareer) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CifPersCareer.update",
					cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<CifPersCareer> findByPageQuotaForCif(CifPersCareer cifPersCareer)
			throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"CifPersCareer.findByPageQuotaForCif", cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}

	@Override
	public int getCountQuotaForCif(CifPersCareer cifPersCareer)
			throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPersCareer.findByPageQuotaForCif.count", cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public CifPersCareer findForCif(CifPersCareer cifPersCareer)
			throws DAOException {
		try {
			cifPersCareer = (CifPersCareer) getSqlMapClientTemplate()
					.queryForObject("CifPersCareer.findForCif", cifPersCareer);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPersCareer;
	}

	@Override
	public String getKey() throws DAOException {
		String cifSeq = "";
		try {
			cifSeq = (String) getSqlMapClientTemplate().queryForObject("CifPersCareer.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifSeq;
	}
}