package  app.creditapp.cif.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cif.dao.CifAcctDao;
import app.creditapp.cif.entity.CifAcct;
/**
* Title: CifAcctDaoImpl.java
* Description:
**/
public class CifAcctDaoImpl extends BaseIbatisDao implements CifAcctDao {

	public void del(CifAcct cifAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CifAcct.del", cifAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CifAcct> findByPage(CifAcct cifAcct) throws DAOException {
		List cifAcctList = null;
		try {
			cifAcctList = getSqlMapClientTemplate().queryForList(
					"CifAcct.findByPage", cifAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifAcctList;
	}

	public CifAcct getById(CifAcct cifAcct) throws DAOException {
		try {
			cifAcct = (CifAcct) getSqlMapClientTemplate()
					.queryForObject("CifAcct.getById", cifAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifAcct;
	}

	public int getCount(CifAcct cifAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifAcct.findPage.count", cifAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CifAcct cifAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CifAcct.insert",
					cifAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CifAcct cifAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CifAcct.update",
					cifAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<CifAcct> findByPageQuotaForCif(CifAcct cifAcct) throws DAOException {
		List lnPactList = null;
		try {
			lnPactList = getSqlMapClientTemplate().queryForList(
					"CifAcct.findByPageQuotaForCif", cifAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnPactList;
	}

	@Override
	public int getCountQuotaForCif(CifAcct cifAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifAcct.findByPageQuotaForCif.count", cifAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
}