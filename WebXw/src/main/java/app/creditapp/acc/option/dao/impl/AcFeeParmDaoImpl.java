package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcFeeParmDao;
import app.creditapp.acc.option.entity.AcFeeParm;
/**
* Title: AcFeeParmDaoImpl.java
* Description:
**/
public class AcFeeParmDaoImpl extends BaseIbatisDao implements AcFeeParmDao {

	public void del(AcFeeParm acFeeParm) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcFeeParm.del", acFeeParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcFeeParm> findByPage(AcFeeParm acFeeParm) throws DAOException {
		List acFeeParmList = null;
		try {
			acFeeParmList = getSqlMapClientTemplate().queryForList(
					"AcFeeParm.findByPage", acFeeParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acFeeParmList;
	}

	public AcFeeParm getById(AcFeeParm acFeeParm) throws DAOException {
		try {
			acFeeParm = (AcFeeParm) getSqlMapClientTemplate()
					.queryForObject("AcFeeParm.getById", acFeeParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acFeeParm;
	}

	public int getCount(AcFeeParm acFeeParm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcFeeParm.findPage.count", acFeeParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcFeeParm acFeeParm) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcFeeParm.insert",
					acFeeParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcFeeParm acFeeParm) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcFeeParm.update",
					acFeeParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateSts(AcFeeParm acFeeParm) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcFeeParm.updateSts",
					acFeeParm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}