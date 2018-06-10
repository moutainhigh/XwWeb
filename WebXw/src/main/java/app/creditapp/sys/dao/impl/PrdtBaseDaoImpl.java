package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.PrdtBaseDao;
import app.creditapp.sys.entity.PrdtBase;
/**
* Title: PrdtBaseDaoImpl.java
* Description:
**/
public class PrdtBaseDaoImpl extends BaseIbatisDao implements PrdtBaseDao {

	public void del(PrdtBase prdtBase) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("PrdtBase.del", prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<PrdtBase> findByPage(PrdtBase prdtBase) throws DAOException {
		List prdtBaseList = null;
		try {
			prdtBaseList = getSqlMapClientTemplate().queryForList(
					"PrdtBase.findByPage", prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return prdtBaseList;
	}

	public PrdtBase getById(PrdtBase prdtBase) throws DAOException {
		try {
			prdtBase = (PrdtBase) getSqlMapClientTemplate()
					.queryForObject("PrdtBase.getById", prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return prdtBase;
	}
	
	public PrdtBase getByPrdtNo(PrdtBase prdtBase) throws DAOException {
		try {
			prdtBase = (PrdtBase) getSqlMapClientTemplate()
					.queryForObject("PrdtBase.getByPrdtNo", prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return prdtBase;
	}
	
	public PrdtBase getMaxNoByBrNo(PrdtBase prdtBase) throws DAOException {
		try {
			prdtBase = (PrdtBase) getSqlMapClientTemplate()
					.queryForObject("PrdtBase.getMaxNoByBrNo", prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return prdtBase;
	}

	public int getCount(PrdtBase prdtBase) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PrdtBase.findPage.count", prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(PrdtBase prdtBase) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("PrdtBase.insert",
					prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	public void insertForCopy(PrdtBase prdtBase) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("PrdtBase.insertForCopy",
					prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	public void update(PrdtBase prdtBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("PrdtBase.update",
					prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public int getCountPrd(PrdtBase prdtBase) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PrdtBase.getCountPrd", prdtBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
}