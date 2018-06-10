package  app.creditapp.cif.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cif.dao.CifPersInfDao;
import app.creditapp.cif.entity.CifPersInf;
/**
* Title: CifPersDaoImpl.java
* Description:
**/
public class CifPersInfDaoImpl extends BaseIbatisDao implements CifPersInfDao {

	public void del(CifPersInf cifPersInf) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CifPersInf.del", cifPersInf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CifPersInf> findByPage(CifPersInf cifPersInf) throws DAOException {
		List cifPersList = null;
		try {
			cifPersList = getSqlMapClientTemplate().queryForList(
					"CifPersInf.findByPage", cifPersInf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPersList;
	}

	public CifPersInf getById(CifPersInf cifPersInf) throws DAOException {
		try {
			cifPersInf = (CifPersInf) getSqlMapClientTemplate()
					.queryForObject("CifPersInf.getById", cifPersInf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPersInf;
	}

	public int getCount(CifPersInf cifPersInf) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPersInf.findPage.count", cifPersInf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CifPersInf cifPersInf) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CifPersInf.insert",
					cifPersInf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CifPersInf cifPersInf) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CifPersInf.update",
					cifPersInf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getKey() throws DAOException {

		String cifSeq = "";
		try {
			cifSeq = (String) getSqlMapClientTemplate()
					.queryForObject("CifPersInf.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifSeq;
	}
}