package  app.creditapp.acc.loan.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.loan.dao.AcLoanLogDao;
import app.creditapp.acc.loan.entity.AcLoanLog;
/**
* Title: AcLoanLogDaoImpl.java
* Description:
**/
public class AcLoanLogDaoImpl extends BaseIbatisDao implements AcLoanLogDao {

	public void del(AcLoanLog acLoanLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLoanLog.del", acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLoanLog> findByPage(AcLoanLog acLoanLog) throws DAOException {
		List acLoanLogList = null;
		try {
			acLoanLogList = getSqlMapClientTemplate().queryForList(
					"AcLoanLog.findByPage", acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanLogList;
	}

	public List<AcLoanLog> getListByAcnoAndLoanno(AcLoanLog acLoanLog) throws DAOException {
		List acLoanLogList = null;
		try {
			acLoanLogList = getSqlMapClientTemplate().queryForList(
					"AcLoanLog.getListByAcnoAndLoanno", acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanLogList;
	}
	
	public List<AcLoanLog> getListByTraceNo(AcLoanLog acLoanLog) throws DAOException {
		List acLoanLogList = null;
		try {
			acLoanLogList = getSqlMapClientTemplate().queryForList(
					"AcLoanLog.getListByTraceNo", acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanLogList;
	}

	public AcLoanLog getById(AcLoanLog acLoanLog) throws DAOException {
		try {
			acLoanLog = (AcLoanLog) getSqlMapClientTemplate()
					.queryForObject("AcLoanLog.getById", acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanLog;
	}

	public AcLoanLog getSuccessByAcno(AcLoanLog acLoanLog) throws DAOException {
		try {
			acLoanLog = (AcLoanLog) getSqlMapClientTemplate()
					.queryForObject("AcLoanLog.getSuccessByAcno", acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanLog;
	}
	
	public int getCount(AcLoanLog acLoanLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLoanLog.findPage.count", acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLoanLog acLoanLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLoanLog.insert",
					acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLoanLog acLoanLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLoanLog.update",
					acLoanLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}