package  app.creditapp.ln.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnApprIdeaDao;
import app.creditapp.ln.entity.LnApprIdea;
/**
* Title: LnApprIdeaDaoImpl.java
* Description:
**/
public class LnApprIdeaDaoImpl extends BaseIbatisDao implements LnApprIdeaDao {

	public void del(LnApprIdea lnApprIdea) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnApprIdea.del", lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnApprIdea> findByPage(LnApprIdea lnApprIdea) throws DAOException {
		List lnApprIdeaList = null;
		try {
			lnApprIdeaList = getSqlMapClientTemplate().queryForList(
					"LnApprIdea.findByPage", lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApprIdeaList;
	}
	public List<LnApprIdea> findByPageForRead(LnApprIdea lnApprIdea) throws DAOException {
		List lnApprIdeaList = null;
		try {
			lnApprIdeaList = getSqlMapClientTemplate().queryForList(
					"LnApprIdea.findByPageForRead", lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApprIdeaList;
	}

	public List<LnApprIdea> findByPageForAppId(LnApprIdea lnApprIdea) throws DAOException {
		List lnApprIdeaList = null;
		try {
			lnApprIdeaList = getSqlMapClientTemplate().queryForList(
					"LnApprIdea.findByPageForAppId", lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApprIdeaList;
	}
	
	public LnApprIdea getById(LnApprIdea lnApprIdea) throws DAOException {
		try {
			lnApprIdea = (LnApprIdea) getSqlMapClientTemplate()
					.queryForObject("LnApprIdea.getById", lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApprIdea;
	}

	public int getCount(LnApprIdea lnApprIdea) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnApprIdea.findPage.count", lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountForRead(LnApprIdea lnApprIdea) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnApprIdea.findPageForRead.count", lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public void insert(LnApprIdea lnApprIdea) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnApprIdea.insert",
					lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnApprIdea lnApprIdea) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnApprIdea.update",
					lnApprIdea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}