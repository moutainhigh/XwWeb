package  app.creditapp.aft.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftRewRealDao;
import app.creditapp.aft.entity.AftRewReal;
/**
* Title: AftRewRealDaoImpl.java
* Description:
**/
public class AftRewRealDaoImpl extends BaseIbatisDao implements AftRewRealDao {

	public void del(AftRewReal aftRewReal) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftRewReal.del", aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftRewReal> findByPage(AftRewReal aftRewReal) throws DAOException {
		List aftRewRealList = null;
		try {
			aftRewRealList = getSqlMapClientTemplate().queryForList(
					"AftRewReal.findByPage", aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewRealList;
	}

	public AftRewReal getById(AftRewReal aftRewReal) throws DAOException {
		try {
			aftRewReal = (AftRewReal) getSqlMapClientTemplate()
					.queryForObject("AftRewReal.getById", aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewReal;
	}

	public int getCount(AftRewReal aftRewReal) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftRewReal.findPage.count", aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftRewReal aftRewReal) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftRewReal.insert",
					aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftRewReal aftRewReal) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewReal.update",
					aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public List<AftRewReal> findByList(AftRewReal aftRewReal) throws DAOException {
		List aftRewRealList = null;
		try {
			aftRewRealList = getSqlMapClientTemplate().queryForList(
					"AftRewReal.findByList", aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewRealList;
	}
	public List<AftRewReal> findByDateBetween(Map<String, String> paramMap)throws DAOException{
		List<AftRewReal> aftRewRealList = null;
		try {
			aftRewRealList = getSqlMapClientTemplate().queryForList( "AftRewReal.findByDateBewteen", paramMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewRealList;
	}
	public void updateDealRest(AftRewReal aftRewReal) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewReal.updateDealRest",
					aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void updateSts(AftRewReal aftRewReal) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewReal.updateSts",
					aftRewReal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}