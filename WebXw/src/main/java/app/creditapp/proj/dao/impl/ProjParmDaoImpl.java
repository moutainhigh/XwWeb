package  app.creditapp.proj.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.proj.dao.ProjParmDao;
import app.creditapp.proj.entity.ProjParm;
/**
* Title: ProjParmDaoImpl.java
* Description:
**/
public class ProjParmDaoImpl extends BaseIbatisDao implements ProjParmDao {

	public void del(ProjParm projParm) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ProjParm.del", projParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ProjParm> findByPage(ProjParm projParm) throws DAOException {
		List projParmList = null;
		try {
			projParmList = getSqlMapClientTemplate().queryForList(
					"ProjParm.findByPage", projParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projParmList;
	}

	public ProjParm getById(ProjParm projParm) throws DAOException {
		try {
			projParm = (ProjParm) getSqlMapClientTemplate()
					.queryForObject("ProjParm.getById", projParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projParm;
	}

	public int getCount(ProjParm projParm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjParm.findPage.count", projParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ProjParm projParm) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ProjParm.insert",
					projParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ProjParm projParm) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ProjParm.update",
					projParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<ProjParm> findByPageQuotaForCorp(ProjParm projParm)
			throws DAOException {
		List projParmList = null;
		try {
			projParmList = getSqlMapClientTemplate().queryForList(
					"ProjParm.findByPageQuotaForCorp", projParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projParmList;
	}

	@Override
	public int getCountQuotaForCorp(ProjParm projParm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjParm.findByPageQuotaForCorp.count", projParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public List<Object>  findListBySts(String sts)throws DAOException{
		List<Object>  projParmList = null;
		try {
			projParmList = getSqlMapClientTemplate().queryForList("ProjParm.findListBySts", sts);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projParmList;
	}

}