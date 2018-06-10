package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcReplanSecDao;
import app.creditapp.acc.option.entity.AcReplanSec;
/**
* Title: AcReplanSecDaoImpl.java
**/
public class AcReplanSecDaoImpl extends BaseIbatisDao implements AcReplanSecDao {

	public void del(AcReplanSec acReplanSec) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcReplanSec.del", acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public void delByReplanId(AcReplanSec acReplanSec) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcReplanSec.delByReplanId", acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcReplanSec> findByPage(AcReplanSec acReplanSec) throws DAOException {
		List acReplanSecList = null;
		try {
			acReplanSecList = getSqlMapClientTemplate().queryForList(
					"AcReplanSec.findByPage", acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acReplanSecList;
	}
	
	public List<AcReplanSec> getListByReplanId(AcReplanSec acReplanSec) throws DAOException {
		List acReplanSecList = null;
		try {
			acReplanSecList = getSqlMapClientTemplate().queryForList(
					"AcReplanSec.getListByReplanId", acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acReplanSecList;
	}

	public AcReplanSec getById(AcReplanSec acReplanSec) throws DAOException {
		try {
			acReplanSec = (AcReplanSec) getSqlMapClientTemplate()
					.queryForObject("AcReplanSec.getById", acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acReplanSec;
	}
	
	public AcReplanSec getByIdAndOrderNo(AcReplanSec acReplanSec) throws DAOException {
		try {
			acReplanSec = (AcReplanSec) getSqlMapClientTemplate()
					.queryForObject("AcReplanSec.getByIdAndOrderNo", acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acReplanSec;
	}

	public int getCount(AcReplanSec acReplanSec) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcReplanSec.findPage.count", acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcReplanSec acReplanSec) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcReplanSec.insert",
					acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcReplanSec acReplanSec) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcReplanSec.update",
					acReplanSec);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}