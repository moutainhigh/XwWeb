package  app.creditapp.ln.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnStageDao;
import app.creditapp.ln.entity.LnStage;
/**
* Title: LnStageDaoImpl.java
* Description:
**/
public class LnStageDaoImpl extends BaseIbatisDao implements LnStageDao {

	public void del(LnStage lnStage) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnStage.del", lnStage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnStage> findByPage(LnStage lnStage) throws DAOException {
		List lnStageList = null;
		try {
			lnStageList = getSqlMapClientTemplate().queryForList(
					"LnStage.findByPage", lnStage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnStageList;
	}

	public LnStage getById(LnStage lnStage) throws DAOException {
		try {
			lnStage = (LnStage) getSqlMapClientTemplate()
					.queryForObject("LnStage.getById", lnStage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnStage;
	}

	public int getCount(LnStage lnStage) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnStage.findPage.count", lnStage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnStage lnStage) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnStage.insert",
					lnStage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnStage lnStage) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnStage.update",
					lnStage);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}