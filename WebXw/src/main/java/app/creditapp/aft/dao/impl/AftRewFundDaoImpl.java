package  app.creditapp.aft.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftRewFundDao;
import app.creditapp.aft.entity.AftRewFund;
/**
* Title: AftRewFundDaoImpl.java
**/
public class AftRewFundDaoImpl extends BaseIbatisDao implements AftRewFundDao {

	public void del(AftRewFund aftRewFund) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftRewFund.del", aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftRewFund> findByPage(AftRewFund aftRewFund) throws DAOException {
		List aftRewFundList = null;
		try {
			aftRewFundList = getSqlMapClientTemplate().queryForList(
					"AftRewFund.findByPage", aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewFundList;
	}

	public AftRewFund getById(AftRewFund aftRewFund) throws DAOException {
		try {
			aftRewFund = (AftRewFund) getSqlMapClientTemplate()
					.queryForObject("AftRewFund.getById", aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewFund;
	}

	public int getCount(AftRewFund aftRewFund) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftRewFund.findPage.count", aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftRewFund aftRewFund) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftRewFund.insert",
					aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftRewFund aftRewFund) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewFund.update",
					aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateSts(AftRewFund aftRewFund) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewFund.updateSts",
					aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateDealRest(AftRewFund aftRewFund) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewFund.updateDealRest",
					aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<AftRewFund> findByList(AftRewFund aftRewFund)
			throws DAOException {
		List aftRewFundList = null;
		try {
			aftRewFundList = getSqlMapClientTemplate().queryForList(
					"AftRewFund.findByList", aftRewFund);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewFundList;
	}
	
	@SuppressWarnings("unchecked")
	public List<AftRewFund> findByDateBetween(Map<String, String> paramMap)throws DAOException{
		List<AftRewFund> aftRewFundList = null;
		try {
			aftRewFundList = getSqlMapClientTemplate().queryForList( "AftRewFund.findByDateBewteen", paramMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewFundList;
	}
}