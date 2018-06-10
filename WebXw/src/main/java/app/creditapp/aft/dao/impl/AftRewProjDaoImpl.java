package  app.creditapp.aft.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftRewProjDao;
import app.creditapp.aft.entity.AftRewProj;
/**
* Title: AftRewProjDaoImpl.java
* Description:
**/
public class AftRewProjDaoImpl extends BaseIbatisDao implements AftRewProjDao {

	public void del(AftRewProj aftRewProj) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftRewProj.del", aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftRewProj> findByPage(AftRewProj aftRewProj) throws DAOException {
		List aftRewProjList = null;
		try {
			aftRewProjList = getSqlMapClientTemplate().queryForList(
					"AftRewProj.findByPage", aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewProjList;
	}

	public AftRewProj getById(AftRewProj aftRewProj) throws DAOException {
		try {
			aftRewProj = (AftRewProj) getSqlMapClientTemplate()
					.queryForObject("AftRewProj.getById", aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewProj;
	}

	public int getCount(AftRewProj aftRewProj) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftRewProj.findPage.count", aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftRewProj aftRewProj) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftRewProj.insert",
					aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftRewProj aftRewProj) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewProj.update",
					aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateSts(AftRewProj aftRewProj) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewProj.updateSts",
					aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateDealRest(AftRewProj aftRewProj) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewProj.updateDealRest",
					aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<AftRewProj> findByList(AftRewProj aftRewProj) throws DAOException {
		List aftRewProjList = null;
		try {
			aftRewProjList = getSqlMapClientTemplate().queryForList(
					"AftRewProj.findByList", aftRewProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewProjList;
	}
	
	public List<AftRewProj> findByDateBetween(Map<String, String> paramMap)throws DAOException{
		List aftRewProjList = null;
		try {
			aftRewProjList = getSqlMapClientTemplate().queryForList(
					"AftRewProj.findByDateBewteen", paramMap);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewProjList;
	}

}