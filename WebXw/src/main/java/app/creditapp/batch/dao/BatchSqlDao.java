package  app.creditapp.batch.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.batch.entity.BatchSql;
/**
* Title: BatchSqlDao.java
* Description:
**/
public interface BatchSqlDao {

	public BatchSql getById(BatchSql batchSql) throws DAOException;

	public void del(BatchSql batchSql) throws DAOException;

	public void insert(BatchSql batchSql) throws DAOException;

	public void update(BatchSql batchSql) throws DAOException;
	
	public int getCount(BatchSql batchSql) throws DAOException;

	public List<BatchSql > findByPage(BatchSql batchSql) throws DAOException;

	public void delByNodeNo(String nodeNo)throws DAOException;

}