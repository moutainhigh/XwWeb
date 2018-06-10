package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcReplanParmDao;
import app.creditapp.acc.option.entity.AcReplanParm;
/**
* Title: AcReplanParmDaoImpl.java
* Description:
**/
public class AcReplanParmDaoImpl extends BaseIbatisDao implements AcReplanParmDao {

	public void del(AcReplanParm acReplanParm) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcReplanParm.del", acReplanParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcReplanParm> findByPage(AcReplanParm acReplanParm) throws DAOException {
		List acReplanParmList = null;
		try {
			acReplanParmList = getSqlMapClientTemplate().queryForList(
					"AcReplanParm.findByPage", acReplanParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acReplanParmList;
	}

	public AcReplanParm getById(AcReplanParm acReplanParm) throws DAOException {
		try {
			acReplanParm = (AcReplanParm) getSqlMapClientTemplate()
					.queryForObject("AcReplanParm.getById", acReplanParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acReplanParm;
	}

	public int getCount(AcReplanParm acReplanParm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcReplanParm.findPage.count", acReplanParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcReplanParm acReplanParm) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcReplanParm.insert",
					acReplanParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcReplanParm acReplanParm) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcReplanParm.update",
					acReplanParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateReplanSts(AcReplanParm acReplanParm) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcReplanParm.updateReplanSts",
					acReplanParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}