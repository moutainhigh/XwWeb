package app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysUserLogDao;
import app.creditapp.sys.entity.SysUserLog;

/**
 * Title: SysUserLogDaoImpl.java Description:
 * 
 **/
public class SysUserLogDaoImpl extends BaseIbatisDao implements SysUserLogDao {

	public void del(SysUserLog sysUserLog) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysUserLog.del", sysUserLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysUserLog> findByPage(SysUserLog sysUserLog) throws DAOException {
		List sysUserLogList = null;
		try {
			sysUserLogList = getSqlMapClientTemplate().queryForList("SysUserLog.findByPage", sysUserLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysUserLogList;
	}

	public SysUserLog getById(SysUserLog sysUserLog) throws DAOException {
		try {
			sysUserLog = (SysUserLog) getSqlMapClientTemplate().queryForObject("SysUserLog.getById", sysUserLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysUserLog;
	}

	public int getCount(SysUserLog sysUserLog) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("SysUserLog.findPage.count", sysUserLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysUserLog sysUserLog) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysUserLog.insert", sysUserLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysUserLog sysUserLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysUserLog.update", sysUserLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void userLogin(SysUserLog sysUserLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysUserLog.userLogin", sysUserLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void userLogout(SysUserLog sysUserLog) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysUserLog.userLogout", sysUserLog);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}