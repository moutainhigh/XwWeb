package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundProvServiceDao;
import app.creditapp.fund.entity.FundProvService;
/**
* Title: FundProvServiceDaoImpl.java
* Description:
**/
public class FundProvServiceDaoImpl extends BaseIbatisDao implements FundProvServiceDao {

	public void del(FundProvService fundProvService) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundProvService.del", fundProvService);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundProvService> findByPage(FundProvService fundProvService) throws DAOException {
		List fundProvServiceList = null;
		try {
			fundProvServiceList = getSqlMapClientTemplate().queryForList(
					"FundProvService.findByPage", fundProvService);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvServiceList;
	}

	public FundProvService getById(FundProvService fundProvService) throws DAOException {
		try {
			fundProvService = (FundProvService) getSqlMapClientTemplate()
					.queryForObject("FundProvService.getById", fundProvService);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvService;
	}

	public int getCount(FundProvService fundProvService) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundProvService.findPage.count", fundProvService);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundProvService fundProvService) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundProvService.insert",
					fundProvService);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundProvService fundProvService) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundProvService.update",
					fundProvService);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}