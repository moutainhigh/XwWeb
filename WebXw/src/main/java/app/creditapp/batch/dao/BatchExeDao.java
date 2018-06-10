package app.creditapp.batch.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.batch.entity.BatchExe;

/**
 * Title: BatchExeDao.java Description:
 * 
 **/
public interface BatchExeDao {

	public BatchExe getById(BatchExe batchExe) throws DAOException;

	public void del(BatchExe batchExe) throws DAOException;

	public void insert(BatchExe batchExe) throws DAOException;

	public void update(BatchExe batchExe) throws DAOException;

	public int getCount(BatchExe batchExe) throws DAOException;

	public List<BatchExe> findByPage(BatchExe batchExe) throws DAOException;

	public List<BatchExe> getAllBatchExeByDate(String batchDate) throws DAOException;

	public List<BatchExe> getBatchExeList(String sysdate) throws DAOException;

	public int getCountByBtchDate() throws DAOException;

	public int getFailBatchNodeCount(String sts) throws DAOException;

	public void init(BatchExe batchExe) throws DAOException;
	
	public List<BatchExe> getListByDate(BatchExe batchExe) throws DAOException;
}