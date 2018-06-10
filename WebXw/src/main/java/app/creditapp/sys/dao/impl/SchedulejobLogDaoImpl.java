package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SchedulejobLogDao;
import app.creditapp.sys.entity.SchedulejobLog;
/**
* Title: SchedulejobLogDaoImpl.java
* Description:
**/
public class SchedulejobLogDaoImpl extends BaseIbatisDao implements SchedulejobLogDao {

	public void del(SchedulejobLog schedulejobLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SchedulejobLog.del", schedulejobLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SchedulejobLog> findByPage(SchedulejobLog schedulejobLog) throws DAOException {
		List schedulejobLogList = null;
		try {
			schedulejobLogList = getSqlMapClientTemplate().queryForList(
					"SchedulejobLog.findByPage", schedulejobLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return schedulejobLogList;
	}

	public SchedulejobLog getById(SchedulejobLog schedulejobLog) throws DAOException {
		try {
			schedulejobLog = (SchedulejobLog) getSqlMapClientTemplate()
					.queryForObject("SchedulejobLog.getById", schedulejobLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return schedulejobLog;
	}

	public int getCount(SchedulejobLog schedulejobLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SchedulejobLog.findPage.count", schedulejobLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SchedulejobLog schedulejobLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SchedulejobLog.insert",
					schedulejobLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SchedulejobLog schedulejobLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SchedulejobLog.update",
					schedulejobLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getSchedulejobLogSeq() throws DAOException {
		String deSeq = null;
		try {
			deSeq = (String) getSqlMapClientTemplate().queryForObject(
					"SchedulejobLog.nextSeq");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return deSeq;
	}
}