package  app.creditapp.ln.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnApplyDao;
import app.creditapp.ln.entity.LnApply;
import app.creditapp.ln.entity.LnApplyMid;
/**
* Title: LnApplyDaoImpl.java
* Description:
**/
public class LnApplyDaoImpl extends BaseIbatisDao implements LnApplyDao {

	public void del(LnApply lnApply) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnApply.del", lnApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnApply> findByPage(LnApply lnApply) throws DAOException {
		List lnApplyList = null;
		try {
			lnApplyList = getSqlMapClientTemplate().queryForList(
					"LnApply.findByPage", lnApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyList;
	}

	public LnApply getById(LnApply lnApply) throws DAOException {
		try {
			lnApply = (LnApply) getSqlMapClientTemplate()
					.queryForObject("LnApply.getById", lnApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApply;
	}

	public int getCount(LnApply lnApply) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnApply.findPage.count", lnApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnApply lnApply) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnApply.insert",
					lnApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnApply lnApply) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnApply.update",
					lnApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getKey() throws DAOException {
		String lnSeq = "";
		try {
			lnSeq = (String) getSqlMapClientTemplate()
					.queryForObject("LnApply.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnSeq;
	}
	public int resultIdUpdate(LnApply lnApply) throws DAOException{
		int result = 0;
		try {
			result = getSqlMapClientTemplate().update("LnApply.resultIdUpdate",
					lnApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return result;
	}
}