package  app.creditapp.proj.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.proj.dao.ProjAcctLstDao;
import app.creditapp.proj.entity.ProjAcctLst;
/**
* Title: ProjAcctLstDaoImpl.java
* Description:
**/
public class ProjAcctLstDaoImpl extends BaseIbatisDao implements ProjAcctLstDao {

	public void del(ProjAcctLst projAcctLst) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ProjAcctLst.del", projAcctLst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ProjAcctLst> findByPage(ProjAcctLst projAcctLst) throws DAOException {
		List projAcctLstList = null;
		try {
			projAcctLstList = getSqlMapClientTemplate().queryForList(
					"ProjAcctLst.findByPage", projAcctLst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcctLstList;
	}

	public ProjAcctLst getById(ProjAcctLst projAcctLst) throws DAOException {
		try {
			projAcctLst = (ProjAcctLst) getSqlMapClientTemplate()
					.queryForObject("ProjAcctLst.getById", projAcctLst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcctLst;
	}

	public int getCount(ProjAcctLst projAcctLst) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjAcctLst.findPage.count", projAcctLst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ProjAcctLst projAcctLst) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ProjAcctLst.insert",
					projAcctLst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ProjAcctLst projAcctLst) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ProjAcctLst.update",
					projAcctLst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<ProjAcctLst> findByPageQuotaForCorp(ProjAcctLst projAcctLst) {
		List projAcctLstList = null;
		try {
			projAcctLstList = getSqlMapClientTemplate().queryForList(
					"ProjAcctLst.findByPageQuotaForCorp", projAcctLst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projAcctLstList;
	}

	@Override
	public int getCountQuotaForCorp(ProjAcctLst projAcctLst) {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjAcctLst.findByPageQuotaForCorp.count", projAcctLst);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
}