package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.ParmKeyDao;
import app.creditapp.sys.entity.ParmKey;
/**
* Title: ParmKeyDaoImpl.java
* Description:
**/
public class ParmKeyDaoImpl extends BaseIbatisDao implements ParmKeyDao {

	public void del(ParmKey ParmKey) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ParmKey.del", ParmKey);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ParmKey> findByPage(ParmKey ParmKey) throws DAOException {
		List ParmKeyList = null;
		try {
			ParmKeyList = getSqlMapClientTemplate().queryForList(
					"ParmKey.findByPage", ParmKey);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return ParmKeyList;
	}

	public ParmKey getById(ParmKey ParmKey) throws DAOException {
		try {
			ParmKey = (ParmKey) getSqlMapClientTemplate()
					.queryForObject("ParmKey.getById", ParmKey);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return ParmKey;
	}

	public int getCount(ParmKey ParmKey) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ParmKey.findPage.count", ParmKey);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ParmKey ParmKey) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ParmKey.insert",
					ParmKey);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ParmKey ParmKey) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ParmKey.update",
					ParmKey);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}