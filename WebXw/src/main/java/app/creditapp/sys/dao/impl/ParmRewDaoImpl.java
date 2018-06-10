package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.ParmRewDao;
import app.creditapp.sys.entity.ParmRew;
/**
* Title: ParmRewDaoImpl.java
* Description:
**/
public class ParmRewDaoImpl extends BaseIbatisDao implements ParmRewDao {

	public void del(ParmRew parmRew) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ParmRew.del", parmRew);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ParmRew> findByPage(ParmRew parmRew) throws DAOException {
		List parmRewList = null;
		try {
			parmRewList = getSqlMapClientTemplate().queryForList(
					"ParmRew.findByPage", parmRew);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return parmRewList;
	}

	public ParmRew getById(ParmRew parmRew) throws DAOException {
		try {
			parmRew = (ParmRew) getSqlMapClientTemplate()
					.queryForObject("ParmRew.getById", parmRew);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return parmRew;
	}

	public int getCount(ParmRew parmRew) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ParmRew.findPage.count", parmRew);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ParmRew parmRew) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ParmRew.insert",
					parmRew);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ParmRew parmRew) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ParmRew.update",
					parmRew);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}