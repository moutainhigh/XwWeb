package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysCacheDao;
import app.creditapp.sys.entity.SysCache;
/**
* Title: SysCacheDaoImpl.java
* Description:
**/
public class SysCacheDaoImpl extends BaseIbatisDao implements SysCacheDao {

	public void del(SysCache sysCache) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysCache.del", sysCache);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysCache> findByPage(SysCache sysCache) throws DAOException {
		List sysCacheList = null;
		try {
			sysCacheList = getSqlMapClientTemplate().queryForList(
					"SysCache.findByPage", sysCache);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysCacheList;
	}

	@Override
	public List<SysCache> getSysCache() throws DAOException {
		List sysCacheList = null;
		try{
			sysCacheList=getSqlMapClientTemplate().queryForList(
					"SysCache.getSysCache");
		} catch (Exception e){
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysCacheList;
	}
	
	public SysCache getById(SysCache sysCache) throws DAOException {
		try {
			sysCache = (SysCache) getSqlMapClientTemplate()
					.queryForObject("SysCache.getById", sysCache);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysCache;
	}

	public int getCount(SysCache sysCache) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysCache.findPage.count", sysCache);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysCache sysCache) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysCache.insert",
					sysCache);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysCache sysCache) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysCache.update",
					sysCache);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

}