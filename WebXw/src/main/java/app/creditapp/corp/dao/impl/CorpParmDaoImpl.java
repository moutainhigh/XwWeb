package  app.creditapp.corp.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.CorpParmDao;
import app.creditapp.corp.entity.CorpParm;
/**
* Title: CorpParmDaoImpl.java
* Description:
**/
public class CorpParmDaoImpl extends BaseIbatisDao implements CorpParmDao {

	public void del(CorpParm corpParm) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpParm.del", corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void delForBase(CorpParm corpParm) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpParm.delForBase", corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CorpParm> findByPage(CorpParm corpParm) throws DAOException {
		List corpParmList = null;
		try {
			corpParmList = getSqlMapClientTemplate().queryForList(
					"CorpParm.findByPage", corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpParmList;
	}

	public CorpParm getById(CorpParm corpParm) throws DAOException {
		try {
			corpParm = (CorpParm) getSqlMapClientTemplate()
					.queryForObject("CorpParm.getById", corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpParm;
	}

	public int getCount(CorpParm corpParm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpParm.findPage.count", corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpParm corpParm) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpParm.insert",
					corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CorpParm corpParm) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpParm.update",
					corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<CorpParm> findByPageQuotaForCorp(CorpParm corpParm)
			throws DAOException {
		List corpParmList = null;
		try {
			corpParmList = getSqlMapClientTemplate().queryForList(
					"CorpParm.findByPageQuotaForCorp", corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpParmList;
	}

	@Override
	public int getCountQuotaForCorp(CorpParm corpParm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpParm.findByPageQuotaForCorp.count", corpParm);
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
					.queryForObject("CorpParm.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpSeq;
	}
	
	public List<Object> findListBySts(CorpParm corpParm) throws DAOException {
		List<Object> corpParmList = null;
		try {
			corpParmList =  getSqlMapClientTemplate().queryForList("CorpParm.findListBySts", corpParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpParmList;
	}
}