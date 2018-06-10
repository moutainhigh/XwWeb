package  app.creditapp.batch.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.batch.bo.BatchSqlBo;
import app.creditapp.batch.dao.BatchSqlDao;
import app.creditapp.batch.entity.BatchSql;
/**
* Title: BatchSqlBoImplImpl.java
* Description:
**/
public class BatchSqlBoImpl extends BaseService implements BatchSqlBo {

	private BatchSqlDao batchSqlDao;

	public void del(BatchSql batchSql) throws ServiceException {
		try {
			batchSqlDao.del(batchSql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void delByNodeNo(String nodeNo) throws ServiceException {
		try {
			batchSqlDao.delByNodeNo(nodeNo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	

	public Ipage findByPage(Ipage ipg, BatchSql batchSql)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) batchSqlDao
					.getCount(batchSql) });// 初始化分页类
			batchSql.setStartNumAndEndNum (ipg);
			ipg.setResult(batchSqlDao.findByPage(batchSql));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public BatchSql getById(BatchSql batchSql) throws ServiceException {
		try {
			batchSql = batchSqlDao.getById(batchSql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return batchSql;
	}

	public void insert(BatchSql batchSql) throws ServiceException {
		try {
			batchSqlDao.insert(batchSql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(BatchSql batchSql) throws ServiceException {
		try {
			batchSqlDao.update(batchSql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public BatchSqlDao getBatchSqlDao() {
		return batchSqlDao;
	}

	public void setBatchSqlDao(BatchSqlDao batchSqlDao) {
		this.batchSqlDao = batchSqlDao;
	}
}