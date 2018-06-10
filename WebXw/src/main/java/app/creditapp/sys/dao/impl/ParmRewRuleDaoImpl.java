package  app.creditapp.sys.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.ParmRewRuleDao;
import app.creditapp.sys.entity.ParmRewRule;
/**
* Title: ParmRewRuleDaoImpl.java
* Description:
**/
public class ParmRewRuleDaoImpl extends BaseIbatisDao implements ParmRewRuleDao {

	public void del(ParmRewRule parmRewRule) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ParmRewRule.del", parmRewRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ParmRewRule> findByPage(ParmRewRule parmRewRule) throws DAOException {
		List parmRewRuleList = null;
		try {
			parmRewRuleList = getSqlMapClientTemplate().queryForList(
					"ParmRewRule.findByPage", parmRewRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return parmRewRuleList;
	}

	public ParmRewRule getById(ParmRewRule parmRewRule) throws DAOException {
		try {
			parmRewRule = (ParmRewRule) getSqlMapClientTemplate()
					.queryForObject("ParmRewRule.getById", parmRewRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return parmRewRule;
	}

	public int getCount(ParmRewRule parmRewRule) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ParmRewRule.findPage.count", parmRewRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ParmRewRule parmRewRule) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ParmRewRule.insert",
					parmRewRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ParmRewRule parmRewRule) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ParmRewRule.update",
					parmRewRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateRuleSts(ParmRewRule parmRewRule) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ParmRewRule.updateRuleSts",
					parmRewRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public List<ParmRewRule> findAll(ParmRewRule parmRewRule) throws DAOException {
		List parmRewRuleList = null;
		try {
			parmRewRuleList = getSqlMapClientTemplate().queryForList(
					"ParmRewRule.findAll", parmRewRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return parmRewRuleList;
	}
}