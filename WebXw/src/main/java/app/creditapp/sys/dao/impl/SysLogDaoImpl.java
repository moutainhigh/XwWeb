package  app.creditapp.sys.dao.impl;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysLogDao;
import app.creditapp.sys.entity.SysLog;
/**
* Title: SysLogDaoImpl.java
* Description:
**/

public class SysLogDaoImpl extends BaseIbatisDao implements SysLogDao {
	
	
	public void delete(String op_id) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysLog.delete", op_id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("É¾³ýÊ§°Ü");
		}
	}

	public List<SysLog> findByPage(Map map) throws DAOException {
		List sysloglist = null;
		try {
			sysloglist = getSqlMapClientTemplate().queryForList(
					"SysLog.findByPage", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("·ÖÒ³²éÑ¯Ê§°Ü");
		}
		return sysloglist;
	}

	public SysLog getByOp_id(String opId) throws DAOException {
		SysLog syslog = null;
		try {
			syslog = (SysLog) getSqlMapClientTemplate().queryForObject(
					"SysLog.getByOpId", opId);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("²éÑ¯Ê§°Ü");
		}
		return syslog;
	}

	public int getCount(SysLog syslog) throws DAOException {

		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysLog.findPage.count", syslog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("²éÑ¯Í³¼ÆÊ§°Ü");
		}
		return count;
	}

	public void insert(SysLog syslog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysLog.insert", syslog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("ÐÂÔöÊ§°Ü");
		}
	}

	public void update(SysLog syslog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysLog.update", syslog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("¸üÐÂÊ§°Ü");
		}
	}
}
