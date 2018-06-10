package  app.creditapp.aft.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftRewPactDao;
import app.creditapp.aft.entity.AftRewPact;
/**
* Title: AftRewPactDaoImpl.java
* Description:
**/
public class AftRewPactDaoImpl extends BaseIbatisDao implements AftRewPactDao {

	public void del(AftRewPact aftRewPact) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftRewPact.del", aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftRewPact> findByPage(AftRewPact aftRewPact) throws DAOException {
		List aftRewPactList = null;
		try {
			aftRewPactList = getSqlMapClientTemplate().queryForList(
					"AftRewPact.findByPage", aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewPactList;
	}

	public AftRewPact getById(AftRewPact aftRewPact) throws DAOException {
		try {
			aftRewPact = (AftRewPact) getSqlMapClientTemplate()
					.queryForObject("AftRewPact.getById", aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewPact;
	}

	public AftRewPact getByPactNo(AftRewPact aftRewPact) throws DAOException {
		try {
			aftRewPact = (AftRewPact) getSqlMapClientTemplate()
					.queryForObject("AftRewPact.getByPactNo", aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewPact;
	}
	
	public int getCount(AftRewPact aftRewPact) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftRewPact.findPage.count", aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftRewPact aftRewPact) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftRewPact.insert",
					aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	//∑÷’À ß∞‹≤Â»ÎaftRewPact±Ì
	public void insertForSplit(AftRewPact aftRewPact) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftRewPact.insertForSplit",
					aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	
	public void update(AftRewPact aftRewPact) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewPact.update",
					aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateSts(AftRewPact aftRewPact) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewPact.updateSts",
					aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateDealRest(AftRewPact aftRewPact) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewPact.updateDealRest",
					aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<AftRewPact> findToList(AftRewPact aftRewPact)
			throws DAOException {
		List aftRewPactList = null;
		try {
			aftRewPactList = getSqlMapClientTemplate().queryForList("AftRewPact.findByList", aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewPactList;
	}
	
	public List<AftRewPact> findPactCollect(AftRewPact aftRewPact)
			throws DAOException {
		List aftRewPactList = null;
		try {
			aftRewPactList = getSqlMapClientTemplate().queryForList("AftRewPact.findPactCollect", aftRewPact);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewPactList;
	}
	
	public List<AftRewPact> findPactCollectBetween(Map<String, String> paramMap)
			throws DAOException {
		List aftRewPactList = null;
		try {
			aftRewPactList = getSqlMapClientTemplate().queryForList("AftRewPact.findPactCollect", paramMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewPactList;
	}
	
	public List<AftRewPact> findByDateBetween(Map<String, String> paramMap){
		List<AftRewPact> aftRewPactList = null;
		try {
			aftRewPactList = getSqlMapClientTemplate().queryForList("AftRewPact.findByDateBewteen", paramMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewPactList;
	}

	@Override
	public String getRewNo(String RewName) throws DAOException {
		String RewNo = "";
		try {
			RewNo = (String) getSqlMapClientTemplate().queryForObject("AftRewPact.getRewNo", RewName);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return RewNo;
	}
}