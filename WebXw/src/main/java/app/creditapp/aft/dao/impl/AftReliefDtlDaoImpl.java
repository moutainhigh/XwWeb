package  app.creditapp.aft.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftReliefDtlDao;
import app.creditapp.aft.entity.AftReliefDtl;
/**
* Title: AftReliefDtlDaoImpl.java
**/
public class AftReliefDtlDaoImpl extends BaseIbatisDao implements AftReliefDtlDao {

	public void del(AftReliefDtl aftReliefDtl) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftReliefDtl.del", aftReliefDtl);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftReliefDtl> findByPage(AftReliefDtl aftReliefDtl) throws DAOException {
		List aftReliefDtlList = null;
		try {
			aftReliefDtlList = getSqlMapClientTemplate().queryForList(
					"AftReliefDtl.findByPage", aftReliefDtl);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftReliefDtlList;
	}

	public AftReliefDtl getById(AftReliefDtl aftReliefDtl) throws DAOException {
		try {
			aftReliefDtl = (AftReliefDtl) getSqlMapClientTemplate()
					.queryForObject("AftReliefDtl.getById", aftReliefDtl);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftReliefDtl;
	}

	public int getCount(AftReliefDtl aftReliefDtl) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftReliefDtl.findPage.count", aftReliefDtl);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftReliefDtl aftReliefDtl) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftReliefDtl.insert",
					aftReliefDtl);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftReliefDtl aftReliefDtl) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftReliefDtl.update",
					aftReliefDtl);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}