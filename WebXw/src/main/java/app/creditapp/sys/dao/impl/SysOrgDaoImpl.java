package  app.creditapp.sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysOrgDao;
import app.creditapp.sys.entity.SysOrg;
/**
* Title: SysOrgDaoImpl.java
* Description:
**/
public class SysOrgDaoImpl extends BaseIbatisDao implements SysOrgDao {

	public void del(SysOrg sysOrg) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysOrg.del", sysOrg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysOrg> findByPage(SysOrg sysOrg) throws DAOException {
		List sysOrgList = null;
		try {
			sysOrgList = getSqlMapClientTemplate().queryForList(
					"SysOrg.findByPage", sysOrg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysOrgList;
	}

	public SysOrg getById(SysOrg sysOrg) throws DAOException {
		try {
			sysOrg = (SysOrg) getSqlMapClientTemplate()
					.queryForObject("SysOrg.getById", sysOrg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sysOrg;
	}

	public int getCount(SysOrg sysOrg) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysOrg.findPage.count", sysOrg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysOrg sysOrg) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysOrg.insert",
					sysOrg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysOrg sysOrg) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysOrg.update",
					sysOrg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public List<SysOrg> getBrnoChildren(String orgNo) throws DAOException {
		List<SysOrg> list = null;
		try {
			Map map = new HashMap();
			map.put("orgNo", orgNo);
			list = (List<SysOrg>) getSqlMapClientTemplate().queryForList("SysOrg.getBrnoChildren", map);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new DAOException("≤È—Ø ß∞‹");
		}
		return list;
	}
}