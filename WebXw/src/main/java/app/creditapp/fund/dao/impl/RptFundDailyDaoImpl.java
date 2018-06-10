package  app.creditapp.fund.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.RptFundDailyDao;
import app.creditapp.fund.entity.RptFundDaily;
/**
* Title: RptFundDailyDaoImpl.java
* Description:
**/
public class RptFundDailyDaoImpl extends BaseIbatisDao implements RptFundDailyDao {

	public void del(RptFundDaily rptFundDaily) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("RptFundDaily.del", rptFundDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<RptFundDaily> findByPage(RptFundDaily rptFundDaily) throws DAOException {
		List rptFundDailyList = null;
		try {
			rptFundDailyList = getSqlMapClientTemplate().queryForList(
					"RptFundDaily.findByPage", rptFundDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return rptFundDailyList;
	}
	public List<RptFundDaily> findByFdType(RptFundDaily rptFundDaily) throws DAOException {
		List rptFundDailyList = null;
		try {
			rptFundDailyList = getSqlMapClientTemplate().queryForList(
					"RptFundDaily.findByFdType", rptFundDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return rptFundDailyList;
	}
	public RptFundDaily getById(RptFundDaily rptFundDaily) throws DAOException {
		try {
			rptFundDaily = (RptFundDaily) getSqlMapClientTemplate()
					.queryForObject("RptFundDaily.getById", rptFundDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return rptFundDaily;
	}

	public int getCount(RptFundDaily rptFundDaily) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"RptFundDaily.findPage.count", rptFundDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(RptFundDaily rptFundDaily) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("RptFundDaily.insert",
					rptFundDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(RptFundDaily rptFundDaily) throws DAOException {
		try {
			getSqlMapClientTemplate().update("RptFundDaily.update",
					rptFundDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<RptFundDaily> getList(RptFundDaily rptFundDaily)
			throws DAOException {
		List<RptFundDaily> rptFundDailylist = null;
		try {
			rptFundDailylist = getSqlMapClientTemplate().queryForList(
					"RptFundDaily.getList", rptFundDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("≤È—Ø ß∞‹");
		}
		return rptFundDailylist;
	}
}