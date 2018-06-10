package  app.util.syslog.dao.impl;
import java.util.ArrayList;
import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.util.syslog.dao.SysExceptionDao;
import app.util.syslog.entity.SysException;
/**
* Title: SysExceptionDaoImpl.java
* Description:
**/
public class SysExceptionDaoImpl extends BaseIbatisDao implements SysExceptionDao {

	public void del(SysException sysException) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysException.del", sysException);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<SysException> findByPage(SysException sysException) throws DAOException {
		List<SysException> sysExceptionList = new ArrayList<SysException>();
		try {
			System.out.println("insert here");
			List<SysException>  queryForList = getSqlMapClientTemplate().queryForList(
					"SysException.findByPage", sysException);
			sysExceptionList = queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysExceptionList;
	}

	public SysException getById(SysException sysException) throws DAOException {
		try {
			sysException = (SysException) getSqlMapClientTemplate()
					.queryForObject("SysException.getById", sysException);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysException;
	}

	public int getCount(SysException sysException) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysException.findPage.count", sysException);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysException sysException) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysException.insert",
					sysException);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysException sysException) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysException.update",
					sysException);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}