package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcFeeRateDao;
import app.creditapp.acc.option.entity.AcFeeRate;
/**
* Title: AcFeeRateDaoImpl.java
* Description:
**/
public class AcFeeRateDaoImpl extends BaseIbatisDao implements AcFeeRateDao {

	public void del(AcFeeRate acFeeRate) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcFeeRate.del", acFeeRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcFeeRate> findByPage(AcFeeRate acFeeRate) throws DAOException {
		List acFeeRateList = null;
		try {
			acFeeRateList = getSqlMapClientTemplate().queryForList(
					"AcFeeRate.findByPage", acFeeRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acFeeRateList;
	}

	public AcFeeRate getById(AcFeeRate acFeeRate) throws DAOException {
		try {
			acFeeRate = (AcFeeRate) getSqlMapClientTemplate()
					.queryForObject("AcFeeRate.getById", acFeeRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acFeeRate;
	}

	public int getCount(AcFeeRate acFeeRate) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcFeeRate.findPage.count", acFeeRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcFeeRate acFeeRate) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcFeeRate.insert",
					acFeeRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcFeeRate acFeeRate) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcFeeRate.update",
					acFeeRate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}