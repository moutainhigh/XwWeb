package  app.creditapp.sys.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysPathDao;
import app.creditapp.sys.entity.SysPath;
/**
* Title: SysPathDaoImpl.java
* Description:
**/
public class SysPathDaoImpl extends BaseIbatisDao implements SysPathDao {

	public void del(SysPath sysPath) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysPath.del", sysPath);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysPath> findByPage(SysPath sysPath) throws DAOException {
		List sysPathList = null;
		try {
			sysPathList = getSqlMapClientTemplate().queryForList(
					"SysPath.findByPage", sysPath);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysPathList;
	}
	
	public List<SysPath> findList() throws DAOException {
		List sysPathList = null;
		try {
			sysPathList = getSqlMapClientTemplate().queryForList(
					"SysPath.findList");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysPathList;
	}

	public SysPath getById(SysPath sysPath) throws DAOException {
		try {
			sysPath = (SysPath) getSqlMapClientTemplate()
					.queryForObject("SysPath.getById", sysPath);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysPath;
	}

	public int getCount(SysPath sysPath) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysPath.findPage.count", sysPath);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysPath sysPath) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysPath.insert",
					sysPath);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysPath sysPath) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysPath.update",
					sysPath);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}