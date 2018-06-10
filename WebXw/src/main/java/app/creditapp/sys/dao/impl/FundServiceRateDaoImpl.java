package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.FundServiceRateDao;
import app.creditapp.sys.entity.FundServiceRate;
/**
* Title: FundServiceRateDaoImpl.java
* Description:
**/
public class FundServiceRateDaoImpl extends BaseIbatisDao implements FundServiceRateDao {

	public void del(FundServiceRate fundServiceRate) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundServiceRate.del", fundServiceRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundServiceRate> findByPage(FundServiceRate fundServiceRate) throws DAOException {
		List fundServiceRateList = null;
		try {
			fundServiceRateList = getSqlMapClientTemplate().queryForList(
					"FundServiceRate.findByPage", fundServiceRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundServiceRateList;
	}

	public FundServiceRate getById(FundServiceRate fundServiceRate) throws DAOException {
		try {
			fundServiceRate = (FundServiceRate) getSqlMapClientTemplate()
					.queryForObject("FundServiceRate.getById", fundServiceRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundServiceRate;
	}
	public FundServiceRate getByProjAndMinamt(FundServiceRate fundServiceRate) throws DAOException {
		try {
			fundServiceRate = (FundServiceRate) getSqlMapClientTemplate()
					.queryForObject("FundServiceRate.getByProjAndMinamt", fundServiceRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundServiceRate;
	}
	public int getCount(FundServiceRate fundServiceRate) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundServiceRate.findPage.count", fundServiceRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundServiceRate fundServiceRate) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundServiceRate.insert",
					fundServiceRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundServiceRate fundServiceRate) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundServiceRate.update",
					fundServiceRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}