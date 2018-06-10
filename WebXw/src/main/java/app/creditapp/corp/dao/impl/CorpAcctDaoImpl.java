package  app.creditapp.corp.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.CorpAcctDao;
import app.creditapp.corp.entity.CorpAcct;
/**
* Title: CorpAcctDaoImpl.java
* Description:
**/
public class CorpAcctDaoImpl extends BaseIbatisDao implements CorpAcctDao {

	public void del(CorpAcct corpAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpAcct.del", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void delForBase(CorpAcct corpAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpAcct.delForBase", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public List<CorpAcct> findByPage(CorpAcct corpAcct) throws DAOException {
		List corpAcctList = null;
		try {
			corpAcctList = getSqlMapClientTemplate().queryForList(
					"CorpAcct.findByPage", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpAcctList;
	}
	@Override
	public CorpAcct getByIdFor5202(String opnAcno) throws DAOException {
		CorpAcct corpAcct = null;
		try {
			corpAcct = (CorpAcct) getSqlMapClientTemplate()
					.queryForObject("CorpAcct.getByIdFor5202", opnAcno);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpAcct;
	}
	public CorpAcct getById(CorpAcct corpAcct) throws DAOException {
		try {
			corpAcct = (CorpAcct) getSqlMapClientTemplate()
					.queryForObject("CorpAcct.getById", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpAcct;
	}
	
	public CorpAcct getByBrNo(CorpAcct corpAcct) throws DAOException {
		try {
			corpAcct = (CorpAcct) getSqlMapClientTemplate()
					.queryForObject("CorpAcct.getByBrNo", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpAcct;
	}

	public int getCount(CorpAcct corpAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpAcct.findPage.count", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpAcct corpAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpAcct.insert",
					corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CorpAcct corpAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpAcct.update",
					corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void updateAcctJS(CorpAcct corpAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpAcct.updateAcctJS",
					corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	@Override
	public List<CorpAcct> findByPageQuotaForCorp(CorpAcct corpAcct) throws DAOException {
		List corpAcctList = null;
		try {
			corpAcctList = getSqlMapClientTemplate().queryForList(
					"CorpAcct.findByPageQuotaForCorp", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpAcctList;
	}

	@Override
	public int getCountQuotaForCorp(CorpAcct corpAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpAcct.findByPageQuotaForCorp.count", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public String getKey() throws DAOException {
		String corpSeq = "";
		try {
			corpSeq = (String) getSqlMapClientTemplate()
					.queryForObject("CorpAcct.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpSeq;
	}
	@Override
	public CorpAcct getByBrNoAndAcNo(CorpAcct corpAcct) throws DAOException {
		try {
			corpAcct = (CorpAcct) getSqlMapClientTemplate()
					.queryForObject("CorpAcct.getByBrNoAndAcNo", corpAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpAcct;
	}
}