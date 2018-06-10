package  app.creditapp.ln.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnAcctDao;
import app.creditapp.ln.entity.LnAcct;
/**
* Title: LnAcctDaoImpl.java
* Description:
**/
public class LnAcctDaoImpl extends BaseIbatisDao implements LnAcctDao {
	
	public List<LnAcct> getByIdAndSts(LnAcct lnAcct)throws DAOException{
		List lnAcctList = null;
	try {
		lnAcctList = getSqlMapClientTemplate().queryForList(
				"LnAcct.getByIdAndSts", lnAcct);
	} catch (Exception e) {
		log.error(e);
		throw new DAOException(e.getMessage());
	}
	return lnAcctList;
	}

	public void del(LnAcct lnAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnAcct.del", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public LnAcct getByIdforAdv(LnAcct lnAcct) throws DAOException {
		try {
			lnAcct = (LnAcct) getSqlMapClientTemplate()
					.queryForObject("LnAcct.getByIdforAdv", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnAcct;
	}

	public void delById(LnAcct lnAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnAcct.delById", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnAcct> findByPage(LnAcct lnAcct) throws DAOException {
		List lnAcctList = null;
		try {
			lnAcctList = getSqlMapClientTemplate().queryForList(
					"LnAcct.findByPage", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnAcctList;
	}

	public LnAcct getById(LnAcct lnAcct) throws DAOException {
		try {
			lnAcct = (LnAcct) getSqlMapClientTemplate()
					.queryForObject("LnAcct.getById", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnAcct;
	}

	public int getCount(LnAcct lnAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnAcct.findPage.count", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnAcct lnAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnAcct.insert",
					lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnAcct lnAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnAcct.update",
					lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateByAppIdAndUse(LnAcct lnAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnAcct.updateByAppIdAndUse",
					lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	

	public void updateByAppIdAndUseForF(LnAcct lnAcct) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnAcct.updateByAppIdAndUseForF",
					lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<LnAcct> findByPageQuotaForLn(LnAcct lnAcct) throws DAOException {
		List LnAcctList = null;
		try {
			LnAcctList = getSqlMapClientTemplate().queryForList(
					"LnAcct.findByPageQuotaForLn", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return LnAcctList;
	}
	
	public List<LnAcct> getListByFk(LnAcct lnAcct) throws DAOException {
		List LnAcctList = null;
		try {
			LnAcctList = getSqlMapClientTemplate().queryForList(
					"LnAcct.getListByFk", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return LnAcctList;
	}

	@Override
	public int getCountQuotaForLn(LnAcct lnAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnAcct.findByPageQuotaForLn.count", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	//接口ws2501接口查询使用
	public int getCountFor2501(LnAcct lnAcct) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnAcct.findFor2501.count", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public LnAcct getOldCount(LnAcct lnAcct) throws DAOException {
		int count = 0;
		try {
			lnAcct = (LnAcct) getSqlMapClientTemplate().queryForObject(
					"LnAcct.getOldCount.count", lnAcct);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnAcct;
	}
	public void updateAcSts(LnAcct lnAcct) throws DAOException{
		try {
			getSqlMapClientTemplate().update("LnAcct.updateAcSts",
					lnAcct);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}
}