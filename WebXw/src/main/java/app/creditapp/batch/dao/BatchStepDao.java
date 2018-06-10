package  app.creditapp.batch.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.batch.entity.BatchStep;
/**
* Title: BatchStepDao.java
* Description:
**/
public interface BatchStepDao {

	public BatchStep getById(BatchStep batchStep) throws DAOException;

	public void del(BatchStep batchStep) throws DAOException;

	public void insert(BatchStep batchStep) throws DAOException;

	public void update(BatchStep batchStep) throws DAOException;
	
	public int getCount(BatchStep batchStep) throws DAOException;

	public List<BatchStep > findByPage(BatchStep batchStep) throws DAOException;

	public List<BatchStep> getAllStepList();

}