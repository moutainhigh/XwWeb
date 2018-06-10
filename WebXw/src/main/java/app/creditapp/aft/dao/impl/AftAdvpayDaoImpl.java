package  app.creditapp.aft.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftAdvpayDao;
import app.creditapp.aft.entity.AftAdvpay;
/**
* Title: AftAdvpayDaoImpl.java
* Description:
**/
public class AftAdvpayDaoImpl extends BaseIbatisDao implements AftAdvpayDao {

	@Override
	public int getSucByTxDate(AftAdvpay aftAdvpay) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftAdvpay.getSucByTxDate", aftAdvpay);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void del(AftAdvpay aftAdvpay) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftAdvpay.del", aftAdvpay);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftAdvpay> findByPage(AftAdvpay aftAdvpay) throws DAOException {
		List aftAdvpayList = null;
		try {
			aftAdvpayList = getSqlMapClientTemplate().queryForList(
					"AftAdvpay.findByPage", aftAdvpay);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftAdvpayList;
	}

	public AftAdvpay getById(AftAdvpay aftAdvpay) throws DAOException {
		try {
			aftAdvpay = (AftAdvpay) getSqlMapClientTemplate()
					.queryForObject("AftAdvpay.getById", aftAdvpay);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftAdvpay;
	}
	public AftAdvpay getByIdForTrace(AftAdvpay aftAdvpay) throws DAOException {
		try {
			aftAdvpay = (AftAdvpay) getSqlMapClientTemplate()
			.queryForObject("AftAdvpay.getByIdForTrace", aftAdvpay);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftAdvpay;
	}

	public int getCount(AftAdvpay aftAdvpay) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftAdvpay.findPage.count", aftAdvpay);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftAdvpay aftAdvpay) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftAdvpay.insert",
					aftAdvpay);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftAdvpay aftAdvpay) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftAdvpay.update",
					aftAdvpay);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getKey() throws DAOException {
		String aftSeq = "";
		try {
			aftSeq = (String) getSqlMapClientTemplate()
					.queryForObject("AftAdvpay.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftSeq;
	}
}