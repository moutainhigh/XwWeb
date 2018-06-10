package  app.creditapp.ln.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnGageMidDao;
import app.creditapp.ln.entity.LnGageMid;
/**
* Title: LnGageMidDaoImpl.java
* Description:
**/
public class LnGageMidDaoImpl extends BaseIbatisDao implements LnGageMidDao {

	public void del(LnGageMid lnGageMid) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnGageMid.del", lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnGageMid> findByPage(LnGageMid lnGageMid) throws DAOException {
		List lnGageMidList = null;
		try {
			lnGageMidList = getSqlMapClientTemplate().queryForList(
					"LnGageMid.findByPage", lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnGageMidList;
	}

	public LnGageMid getById(LnGageMid lnGageMid) throws DAOException {
		try {
			lnGageMid = (LnGageMid) getSqlMapClientTemplate()
					.queryForObject("LnGageMid.getById", lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnGageMid;
	}

	public int getCount(LnGageMid lnGageMid) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnGageMid.findPage.count", lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnGageMid lnGageMid) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnGageMid.insert",
					lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnGageMid lnGageMid) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnGageMid.update",
					lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<LnGageMid> findByPageQuotaForLn(LnGageMid lnGageMid)
			throws DAOException {
		List lnGageMidList = null;
		try {
			lnGageMidList = getSqlMapClientTemplate().queryForList(
					"LnGageMid.findByPageQuotaForLn", lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnGageMidList;
	}

	@Override
	public int getCountQuotaForLn(LnGageMid lnGageMid) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnGageMid.findByPageQuotaForLn.count", lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LnGageMid> getListByLnGageMid(LnGageMid lnGageMid)
			throws DAOException {
		List<LnGageMid> lnGageMidList = null;
		try {
			lnGageMidList = (List<LnGageMid>)getSqlMapClientTemplate().queryForList(
					"LnGageMid.getListByLnGageMid", lnGageMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnGageMidList;
	}
}