package  app.creditapp.corp.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.CorpContDao;
import app.creditapp.corp.entity.CorpCont;
/**
* Title: CorpContDaoImpl.java
* Description:
**/
public class CorpContDaoImpl extends BaseIbatisDao implements CorpContDao {

	public void del(CorpCont corpCont) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpCont.del", corpCont);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CorpCont> findByPage(CorpCont corpCont) throws DAOException {
		List corpContList = null;
		try {
			corpContList = getSqlMapClientTemplate().queryForList(
					"CorpCont.findByPage", corpCont);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpContList;
	}

	public CorpCont getById(CorpCont corpCont) throws DAOException {
		try {
			corpCont = (CorpCont) getSqlMapClientTemplate()
					.queryForObject("CorpCont.getById", corpCont);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpCont;
	}

	public int getCount(CorpCont corpCont) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpCont.findPage.count", corpCont);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpCont corpCont) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpCont.insert",
					corpCont);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CorpCont corpCont) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpCont.update",
					corpCont);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<CorpCont> findByPageQuotaForCorp(CorpCont corpCont)
			throws DAOException {
		List corpContList = null;
		try {
			corpContList = getSqlMapClientTemplate().queryForList(
					"CorpCont.findByPageQuotaForCorp", corpCont);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpContList;
	}

	@Override
	public int getCountQuotaForCorp(CorpCont corpCont) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpCont.findByPageQuotaForCorp.count", corpCont);
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
					.queryForObject("CorpCont.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpSeq;
	}
}