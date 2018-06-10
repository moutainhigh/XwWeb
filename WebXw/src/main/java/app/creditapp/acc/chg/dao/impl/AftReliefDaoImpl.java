package  app.creditapp.acc.chg.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.chg.dao.AftReliefDao;
import app.creditapp.acc.chg.entity.AftRelief;
/**
* Title: AftReliefDaoImpl.java
* Description:
**/
public class AftReliefDaoImpl extends BaseIbatisDao implements AftReliefDao {

	public void del(AftRelief aftRelief) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftRelief.del", aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftRelief> findByPage(AftRelief aftRelief) throws DAOException {
		List aftReliefList = null;
		try {
			aftReliefList = getSqlMapClientTemplate().queryForList(
					"AftRelief.findByPage", aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftReliefList;
	}
	
	public List<AftRelief> findByPageForTask(AftRelief aftRelief) throws DAOException {
		List aftReliefList = null;
		try {
			aftReliefList = getSqlMapClientTemplate().queryForList(
					"AftRelief.findByPageForTask", aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftReliefList;
	}
	
	public String getRefId() throws DAOException {
		
		String refId = (String) getSqlMapClientTemplate().queryForObject("AftRelief.getRefId");
		
		return refId;
	}

	public AftRelief getById(AftRelief aftRelief) throws DAOException {
		try {
			aftRelief = (AftRelief) getSqlMapClientTemplate()
					.queryForObject("AftRelief.getById", aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRelief;
	}
	public AftRelief getByIdForTrace(AftRelief aftRelief) throws DAOException {
		try {
			aftRelief = (AftRelief) getSqlMapClientTemplate()
			.queryForObject("AftRelief.getByIdForTrace", aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRelief;
	}

	public int getCount(AftRelief aftRelief) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftRelief.findPage.count", aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public int getCountForTask(AftRelief aftRelief) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftRelief.findPage.countForTask", aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftRelief aftRelief) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftRelief.insert",
					aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftRelief aftRelief) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRelief.update",
					aftRelief);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}