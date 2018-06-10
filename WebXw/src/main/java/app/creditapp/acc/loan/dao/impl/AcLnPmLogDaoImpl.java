package  app.creditapp.acc.loan.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.loan.dao.AcLnPmLogDao;
import app.creditapp.acc.loan.entity.AcLnPmLog;
/**
* Title: AcLnPmLogDaoImpl.java
* Description:
**/
public class AcLnPmLogDaoImpl extends BaseIbatisDao implements AcLnPmLogDao {

	public void del(AcLnPmLog acLnPmLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnPmLog.del", acLnPmLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLnPmLog> findByPage(AcLnPmLog acLnPmLog) throws DAOException {
		List acLnPmLogList = null;
		try {
			acLnPmLogList = getSqlMapClientTemplate().queryForList(
					"AcLnPmLog.findByPage", acLnPmLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnPmLogList;
	}
	
	public List<AcLnPmLog> getListByTraceNoAndLoanNo(AcLnPmLog acLnPmLog) throws DAOException {
		List acLnPmLogList = null;
		try {
			acLnPmLogList = getSqlMapClientTemplate().queryForList(
					"AcLnPmLog.getListByTraceNoAndLoanNo", acLnPmLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnPmLogList;
	}
	
	public List<AcLnPmLog> getPmLogByCompst(HashMap<String, String> map) throws DAOException {
		List<AcLnPmLog> acLnPmLogList = null;
	    try {
	    	acLnPmLogList = this.getSqlMapClientTemplate().queryForList("AcLnPmLog.getPmLogByCompst",map);
	    } catch (Exception e) {
	      log.error(e);
	      throw new DAOException("分页查询失败");
	    }
	    return acLnPmLogList;
	}
	
	public AcLnPmLog getSumRepayByLoanNo(AcLnPmLog acLnPmLog) throws DAOException {
	    try {
	    	acLnPmLog = (AcLnPmLog)getSqlMapClientTemplate().queryForObject("AcLnPmLog.getSumRepayByLoanNo",acLnPmLog);
	    } catch (Exception e) {
	      log.error(e);
	      throw new DAOException("统计还款金额失败");
	    }
	    return acLnPmLog;
	}
	
	public AcLnPmLog getById(AcLnPmLog acLnPmLog) throws DAOException {
		try {
			acLnPmLog = (AcLnPmLog) getSqlMapClientTemplate()
					.queryForObject("AcLnPmLog.getById", acLnPmLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnPmLog;
	}

	public int getCount(AcLnPmLog acLnPmLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnPmLog.findPage.count", acLnPmLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLnPmLog acLnPmLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLnPmLog.insert",
					acLnPmLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLnPmLog acLnPmLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnPmLog.update",
					acLnPmLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<AcLnPmLog> procReaccFund(AcLnPmLog acLnPmLog)
			throws DAOException {
		List acLnPmLogList = null; 
		try {
			acLnPmLogList = this.getSqlMapClientTemplate().queryForList("AcLnPmLog.procReaccFund",acLnPmLog);
		 } catch (Exception e) {
		    log.error(e);
		    throw new DAOException(e.getMessage());
		 }
		return acLnPmLogList;
	}
}