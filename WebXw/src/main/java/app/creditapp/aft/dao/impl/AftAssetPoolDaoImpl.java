package  app.creditapp.aft.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftAssetPoolDao;
import app.creditapp.aft.entity.AftAssetPool;
/**
* Title: AftAssetPoolDaoImpl.java
* Description:
**/
public class AftAssetPoolDaoImpl extends BaseIbatisDao implements AftAssetPoolDao {

	public void del(AftAssetPool aftAssetPool) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftAssetPool.del", aftAssetPool);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftAssetPool> findByPage(AftAssetPool aftAssetPool) throws DAOException {
		List aftAssetPoolList = null;
		try {
			aftAssetPoolList = getSqlMapClientTemplate().queryForList(
					"AftAssetPool.findByPage", aftAssetPool);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftAssetPoolList;
	}

	public AftAssetPool getById(AftAssetPool aftAssetPool) throws DAOException {
		try {
			aftAssetPool = (AftAssetPool) getSqlMapClientTemplate()
					.queryForObject("AftAssetPool.getById", aftAssetPool);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftAssetPool;
	}

	public int getCount(AftAssetPool aftAssetPool) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftAssetPool.findPage.count", aftAssetPool);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftAssetPool aftAssetPool) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftAssetPool.insert",
					aftAssetPool);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftAssetPool aftAssetPool) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftAssetPool.update",
					aftAssetPool);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//更新资金池借据关联数、资金池金额、本金总额
	public void updateTotal(AftAssetPool aftAssetPool) throws DAOException{
		try {
			getSqlMapClientTemplate().update("AftAssetPool.updateTotal",
					aftAssetPool);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}