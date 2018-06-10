package  app.creditapp.batch.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.batch.dao.BatchSqlDao;
import app.creditapp.batch.entity.BatchSql;
/**
* Title: BatchSqlDaoImpl.java
* Description:
**/
public class BatchSqlDaoImpl extends BaseIbatisDao implements BatchSqlDao {

	public void del(BatchSql batchSql) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("BatchSql.del", batchSql);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void delByNodeNo(String node_no) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("BatchSql.delByNodeNo", node_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<BatchSql> findByPage(BatchSql batchSql) throws DAOException {
		List batchSqlList = null;
		try {
			batchSqlList = getSqlMapClientTemplate().queryForList(
					"BatchSql.findByPage", batchSql);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchSqlList;
	}

	public BatchSql getById(BatchSql batchSql) throws DAOException {
		try {
			batchSql = (BatchSql) getSqlMapClientTemplate()
					.queryForObject("BatchSql.getById", batchSql);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchSql;
	}

	public int getCount(BatchSql batchSql) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"BatchSql.findPage.count", batchSql);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(BatchSql batchSql) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("BatchSql.insert",
					batchSql);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(BatchSql batchSql) throws DAOException {
		try {
			getSqlMapClientTemplate().update("BatchSql.update",
					batchSql);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}