package app.creditapp.batch.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.batch.dao.BatchExeDao;
import app.creditapp.batch.entity.BatchExe;

/**
 * Title: BatchExeDaoImpl.java Description:
 * 
 **/
public class BatchExeDaoImpl extends BaseIbatisDao implements BatchExeDao {

	public void del(BatchExe batchExe) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("BatchExe.del", batchExe);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public void init(BatchExe batchExe) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("BatchExe.init", batchExe);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<BatchExe> findByPage(BatchExe batchExe) throws DAOException {
		List batchExeList = null;
		try {
			batchExeList = getSqlMapClientTemplate().queryForList("BatchExe.findByPage", batchExe);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchExeList;
	}

	public BatchExe getById(BatchExe batchExe) throws DAOException {
		try {
			batchExe = (BatchExe) getSqlMapClientTemplate().queryForObject("BatchExe.getById", batchExe);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchExe;
	}

	public int getCount(BatchExe batchExe) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("BatchExe.findPage.count", batchExe);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public int getCountByBtchDate() throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("BatchExe.getCountByBtchDate");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public int getFailBatchNodeCount(String nodeSts) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("BatchExe.getFailBatchNodeCount", nodeSts);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(BatchExe batchExe) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("BatchExe.insert", batchExe);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(BatchExe batchExe) throws DAOException {
		try {
			getSqlMapClientTemplate().update("BatchExe.update", batchExe);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public List<BatchExe> getAllBatchExeByDate(String batchDate) {
		List batchExeList = null;
		try {
			batchExeList = getSqlMapClientTemplate().queryForList("BatchExe.getAllBatchExeByDate", batchDate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchExeList;
	}

	@Override
	public List<BatchExe> getBatchExeList(String sysdate) {
		List batchExeList = null;
		try {
			batchExeList = getSqlMapClientTemplate().queryForList("BatchExe.getBatchExeStatusList", sysdate);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchExeList;
	}

	@Override
	public List<BatchExe> getListByDate(BatchExe batchExe) throws DAOException {
		List<BatchExe> batchExeList = null;
		try {
			batchExeList = getSqlMapClientTemplate().queryForList("BatchExe.getListByDate", batchExe);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchExeList;
	}
}