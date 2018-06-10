package  app.creditapp.corp.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.CorpGdinfoDao;
import app.creditapp.corp.entity.CorpGdinfo;
/**
* Title: CorpGdinfoDaoImpl.java
* Description:
**/
public class CorpGdinfoDaoImpl extends BaseIbatisDao implements CorpGdinfoDao {

	public void del(CorpGdinfo corpGdinfo) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpGdinfo.del", corpGdinfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CorpGdinfo> findByPage(CorpGdinfo corpGdinfo) throws DAOException {
		List corpGdinfoList = null;
		try {
			corpGdinfoList = getSqlMapClientTemplate().queryForList(
					"CorpGdinfo.findByPage", corpGdinfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpGdinfoList;
	}

	public CorpGdinfo getById(CorpGdinfo corpGdinfo) throws DAOException {
		try {
			corpGdinfo = (CorpGdinfo) getSqlMapClientTemplate()
					.queryForObject("CorpGdinfo.getById", corpGdinfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpGdinfo;
	}

	public int getCount(CorpGdinfo corpGdinfo) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpGdinfo.findPage.count", corpGdinfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpGdinfo corpGdinfo) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpGdinfo.insert",
					corpGdinfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CorpGdinfo corpGdinfo) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpGdinfo.update",
					corpGdinfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<CorpGdinfo> findByPageQuotaForCorp(CorpGdinfo corpGdinfo)
			throws DAOException {
		List corpGdinfoList = null;
		try {
			corpGdinfoList = getSqlMapClientTemplate().queryForList(
					"CorpGdinfo.findByPageQuotaForCorp", corpGdinfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpGdinfoList;
	}

	@Override
	public int getCountQuotaForCorp(CorpGdinfo corpGdinfo) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpGdinfo.findByPageQuotaForCorp.count", corpGdinfo);
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
					.queryForObject("CorpGdinfo.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpSeq;
	}
}