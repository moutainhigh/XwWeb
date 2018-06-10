package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundProvSplitTermDao;
import app.creditapp.fund.entity.FundProvSplitTerm;
/**
* Title: FundProvSplitTermDaoImpl.java
* Description:
**/
public class FundProvSplitTermDaoImpl extends BaseIbatisDao implements FundProvSplitTermDao {

	public void del(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundProvSplitTerm.del", fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundProvSplitTerm> findByPage(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		List fundProvSplitTermList = null;
		try {
			fundProvSplitTermList = getSqlMapClientTemplate().queryForList(
					"FundProvSplitTerm.findByPage", fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvSplitTermList;
	}

	public List<FundProvSplitTerm> Manager_findByPage(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		List fundProvSplitTermList = null;
		try {
			fundProvSplitTermList = getSqlMapClientTemplate().queryForList(
					"FundProvSplitTermmanager.findByPage", fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvSplitTermList;
	}
	public FundProvSplitTerm getById(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		try {
			fundProvSplitTerm = (FundProvSplitTerm) getSqlMapClientTemplate()
					.queryForObject("FundProvSplitTerm.getById", fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvSplitTerm;
	}

	public FundProvSplitTerm Manager_getById(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		try {
			fundProvSplitTerm = (FundProvSplitTerm) getSqlMapClientTemplate()
					.queryForObject("FundProvSplitTermmanager.getById", fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvSplitTerm;
	}
	public int getCount(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundProvSplitTerm.findPage.count", fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public int Manager_getCount(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundProvSplitTermmanager.findPage.count", fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public void insert(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundProvSplitTerm.insert",
					fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundProvSplitTerm fundProvSplitTerm) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundProvSplitTerm.update",
					fundProvSplitTerm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}