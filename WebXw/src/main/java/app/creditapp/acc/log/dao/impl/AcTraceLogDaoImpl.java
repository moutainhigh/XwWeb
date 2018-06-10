package  app.creditapp.acc.log.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.log.dao.AcTraceLogDao;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.inf.entity.WsOut3202_1;
/**
* Title: AcTraceLogDaoImpl.java
* Description:
**/
public class AcTraceLogDaoImpl extends BaseIbatisDao implements AcTraceLogDao {

	public void del(AcTraceLog acTraceLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcTraceLog.del", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcTraceLog> findByPage(AcTraceLog acTraceLog) throws DAOException {
		List acTraceLogList = null;
		try {
			acTraceLogList = getSqlMapClientTemplate().queryForList(
					"AcTraceLog.findByPage_Read", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acTraceLogList;
	}
	public List<AcTraceLog> findByPage_Read(AcTraceLog acTraceLog) throws DAOException {
		List acTraceLogList = null;
		try {
			acTraceLogList = getSqlMapClientTemplate().queryForList(
					"AcTraceLog.findByPage_Read", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acTraceLogList;
	}
	
	public List<AcTraceLog> findByPageForTask(AcTraceLog acTraceLog) throws DAOException {
		List acTraceLogList = null;
		try {
			acTraceLogList = getSqlMapClientTemplate().queryForList(
					"AcTraceLog.findByPageForTask", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acTraceLogList;
	}

	public AcTraceLog getById(AcTraceLog acTraceLog) throws DAOException {
		try {
			acTraceLog = (AcTraceLog) getSqlMapClientTemplate()
					.queryForObject("AcTraceLog.getById", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acTraceLog;
	}
	
	public AcTraceLog getByLoanNoAndTxCde(AcTraceLog acTraceLog) throws DAOException {
		try {
			acTraceLog = (AcTraceLog) getSqlMapClientTemplate()
					.queryForObject("AcTraceLog.getByLoanNoAndTxCde", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acTraceLog;
	}
	
	public AcTraceLog getRecentTraceLog(AcTraceLog acTraceLog) throws DAOException {
		try {
			acTraceLog = (AcTraceLog) getSqlMapClientTemplate()
					.queryForObject("AcTraceLog.getRecentTraceLog", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acTraceLog;
	}

	public int getCount(AcTraceLog acTraceLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcTraceLog.findPage.count", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public int getCountForTask(AcTraceLog acTraceLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcTraceLog.findPage.countForTask", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public int getCountForRead(AcTraceLog acTraceLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcTraceLog.findPage_Read.count", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcTraceLog acTraceLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcTraceLog.insert",
					acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcTraceLog acTraceLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcTraceLog.update",
					acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public String getKey()throws DAOException{
		String traceNo = null;
		try{
			traceNo = (String)getSqlMapClientTemplate().queryForObject("AcTraceLog.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return traceNo;
	}
	//接口ws3202查询返回列表
	public List<WsOut3202_1> getlistforws3202(AcTraceLog acTraceLog) throws DAOException{
		List<WsOut3202_1> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList(
					"AcTraceLog.findByPageforws3202", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return list;
	}
	//接口ws3202查询返回符合条件的信息条数
	public int getCountforws3202(AcTraceLog acTraceLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcTraceLog.findPage.countforws3202", acTraceLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
}