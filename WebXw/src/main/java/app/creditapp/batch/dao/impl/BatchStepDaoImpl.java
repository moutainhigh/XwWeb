package  app.creditapp.batch.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.batch.dao.BatchStepDao;
import app.creditapp.batch.entity.BatchStep;
/**
* Title: BatchStepDaoImpl.java
* Description:
**/
public class BatchStepDaoImpl extends BaseIbatisDao implements BatchStepDao {

	public void del(BatchStep batchStep) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("BatchStep.del", batchStep);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<BatchStep> findByPage(BatchStep batchStep) throws DAOException {
		List batchStepList = null;
		try {
			batchStepList = getSqlMapClientTemplate().queryForList(
					"BatchStep.findByPage", batchStep);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchStepList;
	}

	public BatchStep getById(BatchStep batchStep) throws DAOException {
		try {
			batchStep = (BatchStep) getSqlMapClientTemplate()
					.queryForObject("BatchStep.getById", batchStep);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchStep;
	}

	public int getCount(BatchStep batchStep) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"BatchStep.findPage.count", batchStep);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(BatchStep batchStep) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("BatchStep.insert",
					batchStep);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(BatchStep batchStep) throws DAOException {
		try {
			getSqlMapClientTemplate().update("BatchStep.update",
					batchStep);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<BatchStep> getAllStepList() {
		List batchStepList = null;
		try {
			batchStepList = getSqlMapClientTemplate().queryForList(
					"BatchStep.getAllStepList");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchStepList;
	}
}