package  app.creditapp.batch.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.batch.entity.BatchStep;
import app.util.toolkit.Ipage;

/**
* Title: BatchStepBo.java
* Description:
**/
public interface BatchStepBo {

	public BatchStep getById(BatchStep batchStep) throws ServiceException;

	public void del(BatchStep batchStep) throws ServiceException;

	public void insert(BatchStep batchStep) throws ServiceException;

	public void update(BatchStep batchStep) throws ServiceException;

	public Ipage findByPage(Ipage ipg, BatchStep batchStep) throws ServiceException;
	
	public List<BatchStep> getAllStepList();

}