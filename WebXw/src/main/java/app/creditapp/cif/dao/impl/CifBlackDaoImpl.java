package  app.creditapp.cif.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cif.dao.CifBlackDao;
import app.creditapp.cif.entity.CifBlack;
import app.creditapp.pact.entity.LnPact;
/**
* Title: CifBlackDaoImpl.java
* Description:
**/
public class CifBlackDaoImpl extends BaseIbatisDao implements CifBlackDao {

	public void del(CifBlack cifBlack) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CifBlack.del", cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CifBlack> findByPage(CifBlack cifBlack) throws DAOException {
		List cifBlackList = null;
		try {
			cifBlackList = getSqlMapClientTemplate().queryForList(
					"CifBlack.findByPage", cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifBlackList;
	}
	
	public List<CifBlack> findByPageForTask(CifBlack cifBlack) throws DAOException {
		List cifBlackList = null;
		try {
			cifBlackList = getSqlMapClientTemplate().queryForList(
					"CifBlack.findByPageForTask", cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifBlackList;
	}
	public List<CifBlack> findByPageForAppr(CifBlack cifBlack) throws DAOException {
		List cifBlackList = null;
		try {
			cifBlackList = getSqlMapClientTemplate().queryForList(
					"CifBlack.findByPageForAppr", cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifBlackList;
	}

	public CifBlack getById(CifBlack cifBlack) throws DAOException {
		try {
			cifBlack = (CifBlack) getSqlMapClientTemplate()
					.queryForObject("CifBlack.getById", cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifBlack;
	}

	public int getCount(CifBlack cifBlack) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifBlack.findPage.count", cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public int getCountForTask(CifBlack cifBlack) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifBlack.findPage.countForTask", cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountForAppr(CifBlack cifBlack) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifBlack.findPageForAppr.count", cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public void insert(CifBlack cifBlack) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CifBlack.insert",
					cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CifBlack cifBlack) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CifBlack.update",
					cifBlack);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getKey() throws DAOException {
		String blkSeq = "";
		try {
			blkSeq = (String) getSqlMapClientTemplate()
					.queryForObject("CifBlack.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return blkSeq;
	}
}