package  app.creditapp.acc.loan.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.loan.dao.AcLoanBackLogDao;
import app.creditapp.acc.loan.entity.AcLoanBackLog;
/**
* Title: AcLoanBackLogDaoImpl.java
* Description:
**/
public class AcLoanBackLogDaoImpl extends BaseIbatisDao implements AcLoanBackLogDao {

	@Override
	public List<AcLoanBackLog> reconciZf(String timeSec) throws DAOException {
		List acLoanBackLogList = null;
		try {
			acLoanBackLogList = getSqlMapClientTemplate().queryForList(
					"AcLoanBackLog.getListFail",timeSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanBackLogList;
	}

	@Override
	public AcLoanBackLog getByBackCnt(AcLoanBackLog acLoanBackLog)
			throws DAOException {
		try {
			acLoanBackLog = (AcLoanBackLog) getSqlMapClientTemplate()
					.queryForObject("AcLoanBackLog.getByBackCnt", acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanBackLog;
	}

	public void del(AcLoanBackLog acLoanBackLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLoanBackLog.del", acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLoanBackLog> findByPage(AcLoanBackLog acLoanBackLog) throws DAOException {
		List acLoanBackLogList = null;
		try {
			acLoanBackLogList = getSqlMapClientTemplate().queryForList(
					"AcLoanBackLog.findByPage", acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanBackLogList;
	}
	
	public List<AcLoanBackLog> getListByLogNoAndType(AcLoanBackLog acLoanBackLog) throws DAOException {
		List acLoanBackLogList = null;
		try {
			acLoanBackLogList = getSqlMapClientTemplate().queryForList(
					"AcLoanBackLog.getListByLogNoAndType", acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanBackLogList;
	}
	
	public List<AcLoanBackLog> getListByBackLogNo(AcLoanBackLog acLoanBackLog) throws DAOException {
		List acLoanBackLogList = null;
		try {
			acLoanBackLogList = getSqlMapClientTemplate().queryForList(
					"AcLoanBackLog.getListByBackLogNo", acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanBackLogList;
	}
	public AcLoanBackLog getById(AcLoanBackLog acLoanBackLog) throws DAOException {
		try {
			acLoanBackLog = (AcLoanBackLog) getSqlMapClientTemplate()
					.queryForObject("AcLoanBackLog.getById", acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanBackLog;
	}

	public int getCount(AcLoanBackLog acLoanBackLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLoanBackLog.findPage.count", acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLoanBackLog acLoanBackLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLoanBackLog.insert",
					acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLoanBackLog acLoanBackLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLoanBackLog.update",
					acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void updateSts(AcLoanBackLog acLoanBackLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLoanBackLog.updateSts",
					acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public AcLoanBackLog getByLoanNoAndStsType(AcLoanBackLog acLoanBackLog) throws DAOException {
		try {
			acLoanBackLog = (AcLoanBackLog)getSqlMapClientTemplate().queryForObject("AcLoanBackLog.getByLoanNoAndStsType",
					acLoanBackLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLoanBackLog;
	}
}