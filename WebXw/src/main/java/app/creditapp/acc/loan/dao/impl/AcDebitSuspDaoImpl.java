package  app.creditapp.acc.loan.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.loan.dao.AcDebitSuspDao;
import app.creditapp.acc.loan.entity.AcDebitSusp;
/**
* Title: AcDebitSuspDaoImpl.java
* Description:
**/
public class AcDebitSuspDaoImpl extends BaseIbatisDao implements AcDebitSuspDao {

	public void del(AcDebitSusp acDebitSusp) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcDebitSusp.del", acDebitSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcDebitSusp> findByPage(AcDebitSusp acDebitSusp) throws DAOException {
		List acDebitSuspList = null;
		try {
			acDebitSuspList = getSqlMapClientTemplate().queryForList(
					"AcDebitSusp.findByPage", acDebitSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebitSuspList;
	}

	public AcDebitSusp getById(AcDebitSusp acDebitSusp) throws DAOException {
		try {
			acDebitSusp = (AcDebitSusp) getSqlMapClientTemplate()
					.queryForObject("AcDebitSusp.getById", acDebitSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebitSusp;
	}

	public AcDebitSusp getBySerialNo(AcDebitSusp acDebitSusp) throws DAOException {
		try {
			acDebitSusp = (AcDebitSusp) getSqlMapClientTemplate()
					.queryForObject("AcDebitSusp.getBySerialNo", acDebitSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acDebitSusp;
	}
	
	public int getCount(AcDebitSusp acDebitSusp) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcDebitSusp.findPage.count", acDebitSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcDebitSusp acDebitSusp) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcDebitSusp.insert",
					acDebitSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcDebitSusp acDebitSusp) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcDebitSusp.update",
					acDebitSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}