package  app.creditapp.batch.bo;


import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.batch.entity.BatchNode;

/**
* Title: BatchNodeBo.java
* Description:
**/
public interface BatchNodeBo {

	public BatchNode getById(BatchNode batchNode) throws ServiceException;

	public void del(BatchNode batchNode) throws ServiceException;

	public void insert(BatchNode batchNode) throws ServiceException;

	public void update(BatchNode batchNode) throws ServiceException;

	public Ipage findByPage(Ipage ipg, BatchNode batchNode) throws ServiceException;

	public boolean isGroupRunning() throws ServiceException;
	
	public String getPreTaskState(String Node_no) throws ServiceException;
	
}