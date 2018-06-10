package  app.creditapp.acc.log.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.log.dao.AcChrgLogDao;
import app.creditapp.acc.log.entity.AcChrgLog;
/**
* Title: AcChrgLogDaoImpl.java
* Description:
**/
public class AcChrgLogDaoImpl extends BaseIbatisDao implements AcChrgLogDao {

	public void del(AcChrgLog acChrgLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcChrgLog.del", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcChrgLog> findByPage(AcChrgLog acChrgLog) throws DAOException {
		List acChrgLogList = null;
		try {
			acChrgLogList = getSqlMapClientTemplate().queryForList(
					"AcChrgLog.findByPage", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acChrgLogList;
	}
	
	public List<AcChrgLog> getLoFeeList(AcChrgLog acChrgLog) throws DAOException {
		List acChrgLogList = null;
		try {
			acChrgLogList = getSqlMapClientTemplate().queryForList(
					"AcChrgLog.getLoFeeList", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acChrgLogList;
	}

	public List<AcChrgLog> getListByPerdNo(AcChrgLog acChrgLog) throws DAOException {
		List acChrgLogList = null;
		try {
			acChrgLogList = getSqlMapClientTemplate().queryForList(
					"AcChrgLog.getListByPerdNo", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acChrgLogList;
	}
	
	
	public List<AcChrgLog> getListByTraceno(AcChrgLog acChrgLog) throws DAOException {
		List acChrgLogList = null;
		try {
			acChrgLogList = getSqlMapClientTemplate().queryForList(
					"AcChrgLog.getListByTraceno", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acChrgLogList;
	}
	
	public AcChrgLog getById(AcChrgLog acChrgLog) throws DAOException {
		try {
			acChrgLog = (AcChrgLog) getSqlMapClientTemplate()
					.queryForObject("AcChrgLog.getById", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acChrgLog;
	}

	public AcChrgLog getLoFeeMinPerd(AcChrgLog acChrgLog) throws DAOException {
		try {
			acChrgLog = (AcChrgLog) getSqlMapClientTemplate()
					.queryForObject("AcChrgLog.getLoFeeMinPerd", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acChrgLog;
	}

	public int getCount(AcChrgLog acChrgLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcChrgLog.findPage.count", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public double getLoFeeAmt(AcChrgLog acChrgLog) throws DAOException {
		double loFeeAmt = 0;
		try {
			loFeeAmt = (Double) getSqlMapClientTemplate().queryForObject(
					"AcChrgLog.getLoFeeAmt", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return loFeeAmt;
	}

	public void insert(AcChrgLog acChrgLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcChrgLog.insert",
					acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcChrgLog acChrgLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcChrgLog.update",
					acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public AcChrgLog getAllAmt(AcChrgLog acChrgLog) throws DAOException {
		try {
			acChrgLog = (AcChrgLog) getSqlMapClientTemplate()
					.queryForObject("AcChrgLog.getAllAmt", acChrgLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acChrgLog;
	}
}