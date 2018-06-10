package  app.creditapp.proj.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.proj.dao.ProjMangDao;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.proj.entity.ProjMang;
/**
* Title: ProjMangDaoImpl.java
* Description:
**/
public class ProjMangDaoImpl extends BaseIbatisDao implements ProjMangDao {

	public void del(ProjMang projMang) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ProjMang.del", projMang);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ProjMang> findByPage(ProjMang projMang) throws DAOException {
		List projMangList = null;
		try {
			projMangList = getSqlMapClientTemplate().queryForList(
					"ProjMang.findByPage", projMang);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projMangList;
	}

	public List<ProjMang> findByPageForManager(ProjMang projMang) throws DAOException {
		List projMangList = null;
		try {
			projMangList = getSqlMapClientTemplate().queryForList(
					"ProjMang.findByPageForManager", projMang);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projMangList;
	}
	
	public ProjMang getById(ProjMang projMang) throws DAOException {
		try {
			projMang = (ProjMang) getSqlMapClientTemplate()
					.queryForObject("ProjMang.getById", projMang);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projMang;
	}

	public List<ProjMang> getByIdLoginId(ProjMang projMang) throws DAOException {
		List projMangList = null;
		try {
			projMangList = getSqlMapClientTemplate().queryForList(
					"ProjMang.getByIdLoginId", projMang);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projMangList;
	}
	public int getCount(ProjMang projMang) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ProjMang.findPage.count", projMang);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ProjMang projMang) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ProjMang.insert",
					projMang);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ProjMang projMang) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ProjMang.update",
					projMang);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//软通过度的项目  项目经理同步
	public void updateforvw(ProjBase projBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ProjMangforvw.update");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getKey() throws DAOException {
		String projSeq = "";
		try {
			projSeq = (String) getSqlMapClientTemplate()
					.queryForObject("ProjMang.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projSeq;
	}
}