package  app.creditapp.ln.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnAcctMidDao;
import app.creditapp.ln.entity.LnAcctMid;
/**
* Title: LnAcctMidDaoImpl.java
* Description:
**/
public class LnAcctMidDaoImpl extends BaseIbatisDao implements LnAcctMidDao {

	public void del(LnAcctMid lnAcctMid) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnAcctMid.del", lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnAcctMid> findByPage(LnAcctMid lnAcctMid) throws DAOException {
		List lnAcctMidList = null;
		try {
			lnAcctMidList = getSqlMapClientTemplate().queryForList(
					"LnAcctMid.findByPage", lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnAcctMidList;
	}

	public LnAcctMid getById(LnAcctMid lnAcctMid) throws DAOException {
		try {
			lnAcctMid = (LnAcctMid) getSqlMapClientTemplate()
					.queryForObject("LnAcctMid.getById", lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnAcctMid;
	}

	public int getCount(LnAcctMid lnAcctMid) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnAcctMid.findPage.count", lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnAcctMid lnAcctMid) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnAcctMid.insert",
					lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnAcctMid lnAcctMid) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnAcctMid.update",
					lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<LnAcctMid> findByPageQuotaForLn(LnAcctMid lnAcctMid)
			throws DAOException {
		List LnAcctMidList = null;
		try {
			LnAcctMidList = getSqlMapClientTemplate().queryForList(
					"LnAcctMid.findByPageQuotaForLn", lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return LnAcctMidList;
	}

	@Override
	public int getCountQuotaForLn(LnAcctMid lnAcctMid) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnAcctMid.findByPageQuotaForLn.count", lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LnAcctMid> getListByLnAcctMid(LnAcctMid lnAcctMid)
			throws DAOException {
		List<LnAcctMid> LnAcctMidList = null;
		try {
			LnAcctMidList = (List<LnAcctMid>)getSqlMapClientTemplate().queryForList(
					"LnAcctMid.getListByLnAcctMid", lnAcctMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return LnAcctMidList;
	}
}