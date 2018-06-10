package  app.creditapp.aft.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftRewDealDao;
import app.creditapp.aft.entity.AftRewDeal;
/**
* Title: AftRewDealDaoImpl.java
* Description:
**/
public class AftRewDealDaoImpl extends BaseIbatisDao implements AftRewDealDao {

	public void del(AftRewDeal aftRewDeal) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftRewDeal.del", aftRewDeal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftRewDeal> findByPage(AftRewDeal aftRewDeal) throws DAOException {
		List aftRewDealList = null;
		try {
			aftRewDealList = getSqlMapClientTemplate().queryForList(
					"AftRewDeal.findByPage", aftRewDeal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewDealList;
	}

	public AftRewDeal getById(AftRewDeal aftRewDeal) throws DAOException {
		try {
			aftRewDeal = (AftRewDeal) getSqlMapClientTemplate()
					.queryForObject("AftRewDeal.getById", aftRewDeal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRewDeal;
	}

	public int getCount(AftRewDeal aftRewDeal) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftRewDeal.findPage.count", aftRewDeal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftRewDeal aftRewDeal) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftRewDeal.insert",
					aftRewDeal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftRewDeal aftRewDeal) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRewDeal.update",
					aftRewDeal);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}