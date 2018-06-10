package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundProvDao;
import app.creditapp.fund.entity.FundProv;
/**
* Title: FundProvDaoImpl.java
* Description:
**/
public class FundProvDaoImpl extends BaseIbatisDao implements FundProvDao {

	public void del(FundProv fundProv) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundProv.del", fundProv);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundProv> findByPage(FundProv fundProv) throws DAOException {
		List fundProvList = null;
		try {
			fundProvList = getSqlMapClientTemplate().queryForList(
					"FundProv.findByPage", fundProv);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvList;
	}

	public FundProv getById(FundProv fundProv) throws DAOException {
		try {
			fundProv = (FundProv) getSqlMapClientTemplate()
					.queryForObject("FundProv.getById", fundProv);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProv;
	}

	public int getCount(FundProv fundProv) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundProv.findPage.count", fundProv);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundProv fundProv) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundProv.insert",
					fundProv);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundProv fundProv) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundProv.update",
					fundProv);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}