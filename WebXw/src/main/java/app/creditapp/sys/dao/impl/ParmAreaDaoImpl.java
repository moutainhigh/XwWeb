package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.ParmAreaDao;
import app.creditapp.sys.entity.ParmArea;
/**
* Title: ParmAreaDaoImpl.java
* Description:
**/
public class ParmAreaDaoImpl extends BaseIbatisDao implements ParmAreaDao {

	public void del(ParmArea parmArea) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ParmArea.del", parmArea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ParmArea> findByPage(ParmArea parmArea) throws DAOException {
		List parmAreaList = null;
		try {
			parmAreaList = getSqlMapClientTemplate().queryForList(
					"ParmArea.findByPage", parmArea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return parmAreaList;
	}

	public ParmArea getById(ParmArea parmArea) throws DAOException {
		try {
			parmArea = (ParmArea) getSqlMapClientTemplate()
					.queryForObject("ParmArea.getById", parmArea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return parmArea;
	}

	public int getCount(ParmArea parmArea) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ParmArea.findPage.count", parmArea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ParmArea parmArea) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ParmArea.insert",
					parmArea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ParmArea parmArea) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ParmArea.update",
					parmArea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public ParmArea getTreeTop() throws DAOException {
		ParmArea parmArea = new ParmArea();
		try{
			parmArea = (ParmArea) getSqlMapClientTemplate().queryForObject("ParmArea.getTreeTop");
		}catch (Exception e) {
		      log.error(e);
		      throw new DAOException("≤È—Ø ß∞‹");
		} 
		return parmArea;
	}
	public List<ParmArea> getChildren(String areaLev)throws DAOException {
		List<ParmArea> parmAreaList = null;
		try{
			parmAreaList = (List<ParmArea>) getSqlMapClientTemplate().queryForList("ParmArea.getChildren", areaLev);
		}catch (Exception e) {
		    log.error(e);
		    throw new DAOException("≤È—Ø ß∞‹");
		} 
		
		return parmAreaList;
	}

	public ParmArea getByDeptid(ParmArea parmArea)
			throws DAOException {
		try {
			parmArea = (ParmArea) this
					.getSqlMapClientTemplate().queryForObject(
							"ParmArea.getByDeptid", parmArea);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("≤È—Ø ß∞‹");
		}
		return parmArea;
	}
	public List<ParmArea> getByUpOne(String upOne) throws DAOException {
		List<ParmArea> parmAreaList = null;
		try {
			parmAreaList = this.getSqlMapClientTemplate().queryForList("ParmArea.getByUpOne",upOne);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("≤È—Ø ß∞‹");
		}
		return parmAreaList;
	}
}