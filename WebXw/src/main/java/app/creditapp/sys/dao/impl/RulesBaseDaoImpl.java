package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.RulesBaseDao;
import app.creditapp.sys.entity.RulesBase;
/**
* Title: RulesBaseDaoImpl.java
* Description:
**/
public class RulesBaseDaoImpl extends BaseIbatisDao implements RulesBaseDao {

	public void del(RulesBase rulesBase) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("RulesBase.del", rulesBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<RulesBase> findByPage(RulesBase rulesBase) throws DAOException {
		List rulesBaseList = null;
		try {
			rulesBaseList = getSqlMapClientTemplate().queryForList(
					"RulesBase.findByPage", rulesBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return rulesBaseList;
	}

	public List<RulesBase> findById(RulesBase rulesBase) throws DAOException {
		List rulesBaseList = null;
		try {
			rulesBaseList = getSqlMapClientTemplate().queryForList(
					"RulesBase.findById", rulesBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return rulesBaseList;
	}
	
	public RulesBase getById(RulesBase rulesBase) throws DAOException {
		try {
			rulesBase = (RulesBase) getSqlMapClientTemplate()
					.queryForObject("RulesBase.getById", rulesBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return rulesBase;
	}

	public int getCount(RulesBase rulesBase) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"RulesBase.findPage.count", rulesBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(RulesBase rulesBase) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("RulesBase.insert",
					rulesBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(RulesBase rulesBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("RulesBase.update",
					rulesBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}