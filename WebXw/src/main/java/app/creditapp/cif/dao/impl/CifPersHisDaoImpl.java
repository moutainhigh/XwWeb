package  app.creditapp.cif.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cif.dao.CifPersHisDao;
import app.creditapp.cif.entity.CifPersHis;
/**
* Title: CifPersHisDaoImpl.java
* Description:
**/
public class CifPersHisDaoImpl extends BaseIbatisDao implements CifPersHisDao {

	public void del(CifPersHis cifPersHis) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CifPersHis.del", cifPersHis);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CifPersHis> findByPage(CifPersHis cifPersHis) throws DAOException {
		List cifPersHisList = null;
		try {
			cifPersHisList = getSqlMapClientTemplate().queryForList(
					"CifPersHis.findByPage", cifPersHis);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPersHisList;
	}

	public CifPersHis getById(CifPersHis cifPersHis) throws DAOException {
		try {
			cifPersHis = (CifPersHis) getSqlMapClientTemplate()
					.queryForObject("CifPersHis.getById", cifPersHis);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPersHis;
	}

	public int getCount(CifPersHis cifPersHis) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPersHis.findPage.count", cifPersHis);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CifPersHis cifPersHis) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CifPersHis.insert",
					cifPersHis);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CifPersHis cifPersHis) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CifPersHis.update",
					cifPersHis);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<CifPersHis> findByPageQuotaForCif(CifPersHis cifPersHis)
			throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"CifPersHis.findByPageQuotaForCif", cifPersHis);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}

	@Override
	public int getCountQuotaForCif(CifPersHis cifPersHis) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPersHis.findByPageQuotaForCif.count", cifPersHis);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public String getSeqKey() throws DAOException {

		String cifSeq = "";
		try {
			cifSeq = (String) getSqlMapClientTemplate().queryForObject("CifPersHis.getSeqKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifSeq;
	}
}