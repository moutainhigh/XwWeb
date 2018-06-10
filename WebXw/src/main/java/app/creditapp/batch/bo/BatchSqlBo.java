package  app.creditapp.batch.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.batch.entity.BatchSql;

/**
* Title: BatchSqlBo.java
* Description:
**/
public interface BatchSqlBo {

	public BatchSql getById(BatchSql batchSql) throws ServiceException;

	public void del(BatchSql batchSql) throws ServiceException;

	public void insert(BatchSql batchSql) throws ServiceException;

	public void update(BatchSql batchSql) throws ServiceException;

	public Ipage findByPage(Ipage ipg, BatchSql batchSql) throws ServiceException;

	public void delByNodeNo(String nodeNo)throws ServiceException;

}