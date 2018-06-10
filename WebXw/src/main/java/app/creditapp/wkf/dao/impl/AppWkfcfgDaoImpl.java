package  app.creditapp.wkf.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.entity.ProdBrno;
import app.creditapp.wkf.dao.AppWkfcfgDao;
import app.creditapp.wkf.entity.AppWkfcfg;
/**
* Title: AppWkfcfgDaoImpl.java
* Description:
**/
public class AppWkfcfgDaoImpl extends BaseIbatisDao implements AppWkfcfgDao {


	public List<AppWkfcfg> findByPage(AppWkfcfg appWkfcfg) throws DAOException {
		List appWkfcfgList = null;
		try {
			appWkfcfgList = getSqlMapClientTemplate().queryForList(
					"AppWkfcfg.findByPage", appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return appWkfcfgList;
	}

	public String getById(AppWkfcfg appWkfcfg) throws DAOException {
		String wkfNo = "";
		try {
			wkfNo = (String) getSqlMapClientTemplate().queryForObject("AppWkfcfg.getById", appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfNo;
	}
	public AppWkfcfg getById2(AppWkfcfg appWkfcfg) throws DAOException {
		try {
			appWkfcfg = (AppWkfcfg) getSqlMapClientTemplate().queryForObject("AppWkfcfg.getById2", appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return appWkfcfg;
	}
	
	public String getByIdForLoan(AppWkfcfg appWkfcfg) throws DAOException {
		String wkfNo = "";
		try {
			wkfNo = (String)getSqlMapClientTemplate()
					.queryForObject("AppWkfcfg.getByIdForLoan", appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfNo;
	}
	
	public int getCountForWkfLoan(AppWkfcfg appWkfcfg) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AppWkfcfg.findWkfCount.count", appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public int getCount(AppWkfcfg appWkfcfg) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AppWkfcfg.findPage.count", appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void update(AppWkfcfg appWkfcfg) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AppWkfcfg.update",
					appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ProdBrno> getProdList(String brNo) throws DAOException {
		List<ProdBrno> prodList = null;
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("brNo", brNo);
			prodList = (List<ProdBrno>)getSqlMapClientTemplate().queryForList("AppWkfcfg.getProdList",
					map);
			map.clear();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return prodList;
	}

	public List<AppWkfcfg> chkPrdtNo(AppWkfcfg appWkfcfg)
			throws DAOException {
		List<AppWkfcfg> list = null;
		try {
			list = (List<AppWkfcfg>) getSqlMapClientTemplate().queryForList(
					"AppWkfcfg.chkPrdtNo", appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return list;
	}

	public void insert(AppWkfcfg appWkfcfg) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AppWkfcfg.insert",
					appWkfcfg);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}

	public String getNextVal() throws DAOException {
		String nextVal = "";
		try {
			nextVal = (String)getSqlMapClientTemplate().queryForObject("AppWkfcfg.getNextVal");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return nextVal;
	}
	
	public List<AppWkfcfg> getAppWkfcfgList(AppWkfcfg appWkfcfg) throws DAOException {
		List<AppWkfcfg> appWkfcfgList = null;
		try {
			appWkfcfgList = (List<AppWkfcfg>) getSqlMapClientTemplate().queryForList(
					"AppWkfcfg.getAppWkfcfgList", appWkfcfg);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return appWkfcfgList;
	}

	public List<AppWkfcfg> getByProcessKey(String processKey) throws Exception {
		List<AppWkfcfg> appWkfcfg = null;
		try{
			appWkfcfg = (List<AppWkfcfg>) getSqlMapClientTemplate().queryForList("AppWkfcfg.getByProcessKey", processKey);
		}catch(Exception e){
			e.printStackTrace();
		}
		return appWkfcfg;
	}

	public void updateProcessStsByKey(AppWkfcfg appWkfcfg) throws Exception {
		try{
			getSqlMapClientTemplate().update("AppWkfcfg.updateProcessSts", appWkfcfg);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void deleteByKeyAndType(AppWkfcfg appWkfcfg) throws Exception {
		try{
			getSqlMapClientTemplate().delete("AppWkfcfg.deleteByKey", appWkfcfg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}