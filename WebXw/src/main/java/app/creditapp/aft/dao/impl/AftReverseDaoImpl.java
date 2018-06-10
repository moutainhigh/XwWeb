package  app.creditapp.aft.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.aft.dao.AftReverseDao;
import app.creditapp.aft.entity.AftReverse;
/**
* Title: AftReverseDaoImpl.java
**/
public class AftReverseDaoImpl extends BaseIbatisDao implements AftReverseDao {

	public void del(AftReverse aftReverse) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AftReverse.del", aftReverse);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AftReverse> findByPage(AftReverse aftReverse) throws DAOException {
		List aftReverseList = null;
		try {
			aftReverseList = getSqlMapClientTemplate().queryForList(
					"AftReverse.findByPage", aftReverse);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftReverseList;
	}

	public AftReverse getById(AftReverse aftReverse) throws DAOException {
		try {
			aftReverse = (AftReverse) getSqlMapClientTemplate()
					.queryForObject("AftReverse.getById", aftReverse);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftReverse;
	}
	public AftReverse getByIdForRead(AftReverse aftReverse) throws DAOException {
		try {
			aftReverse = (AftReverse) getSqlMapClientTemplate()
			.queryForObject("AftReverse.getByIdForRead", aftReverse);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return aftReverse;
	}

	public int getCount(AftReverse aftReverse) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AftReverse.findPage.count", aftReverse);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AftReverse aftReverse) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AftReverse.insert",
					aftReverse);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AftReverse aftReverse) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftReverse.update",
					aftReverse);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void updateReveSts(AftReverse aftReverse) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AftReverse.updateReveSts",
					aftReverse);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}