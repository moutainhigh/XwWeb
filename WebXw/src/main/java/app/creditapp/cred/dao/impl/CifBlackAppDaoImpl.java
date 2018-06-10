package  app.creditapp.cred.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cred.dao.CifBlackAppDao;
import app.creditapp.cred.entity.CifBlackApp;
/**
* Title: CifBlackAppDaoImpl.java
* Description:
**/
public class CifBlackAppDaoImpl extends BaseIbatisDao implements CifBlackAppDao {

	public void del(CifBlackApp cifBlackApp) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CifBlackApp.del", cifBlackApp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CifBlackApp> findByPage(CifBlackApp cifBlackApp) throws DAOException {
		List cifBlackAppList = null;
		try {
			cifBlackAppList = getSqlMapClientTemplate().queryForList(
					"CifBlackApp.findByPage", cifBlackApp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifBlackAppList;
	}

	public CifBlackApp getById(CifBlackApp cifBlackApp) throws DAOException {
		try {
			cifBlackApp = (CifBlackApp) getSqlMapClientTemplate()
					.queryForObject("CifBlackApp.getById", cifBlackApp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifBlackApp;
	}

	public int getCount(CifBlackApp cifBlackApp) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifBlackApp.findPage.count", cifBlackApp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CifBlackApp cifBlackApp) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CifBlackApp.insert",
					cifBlackApp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CifBlackApp cifBlackApp) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CifBlackApp.update",
					cifBlackApp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getAppKey() throws DAOException {
		String appSeq = "";
		try {
			appSeq = (String) getSqlMapClientTemplate()
					.queryForObject("CifBlackApp.getAppKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return appSeq;
	}

}