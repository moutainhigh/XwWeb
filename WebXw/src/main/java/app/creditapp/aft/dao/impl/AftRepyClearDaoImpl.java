package  app.creditapp.aft.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftRepyClearDao;
import app.creditapp.aft.entity.AftRepyClear;
/**
* Title: AftRepyClearDaoImpl.java
* Description:
**/
public class AftRepyClearDaoImpl extends BaseIbatisDao implements AftRepyClearDao {

	public void del(AftRepyClear aftRepyClear) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftRepyClear.del", aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftRepyClear> findByPage(AftRepyClear aftRepyClear) throws DAOException {
		List aftRepyClearList = null;
		try {
			aftRepyClearList = getSqlMapClientTemplate().queryForList(
					"AftRepyClear.findByPage", aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRepyClearList;
	}

	public AftRepyClear getById(AftRepyClear aftRepyClear) throws DAOException {
		try {
			aftRepyClear = (AftRepyClear) getSqlMapClientTemplate()
					.queryForObject("AftRepyClear.getById", aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRepyClear;
	}
	
	public AftRepyClear getByWsId(AftRepyClear aftRepyClear) throws DAOException {
		try {
			aftRepyClear = (AftRepyClear) getSqlMapClientTemplate()
					.queryForObject("AftRepyClear.getByWsId", aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRepyClear;
	}
	
	public AftRepyClear getByPactnoAndPerdno(AftRepyClear aftRepyClear) throws DAOException {
		try {
			aftRepyClear = (AftRepyClear) getSqlMapClientTemplate()
					.queryForObject("AftRepyClear.getByPactnoAndPerdno", aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRepyClear;
	}
	public AftRepyClear getByPactNoIng(AftRepyClear aftRepyClear) throws DAOException {
		try {
			aftRepyClear = (AftRepyClear) getSqlMapClientTemplate()
					.queryForObject("AftRepyClear.getByPactNoIng", aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftRepyClear;
	}
	public int getCount(AftRepyClear aftRepyClear) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftRepyClear.findPage.count", aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftRepyClear aftRepyClear) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftRepyClear.insert",
					aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftRepyClear aftRepyClear) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftRepyClear.update",
					aftRepyClear);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}