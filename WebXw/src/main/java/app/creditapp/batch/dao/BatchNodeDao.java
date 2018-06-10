package  app.creditapp.batch.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.batch.entity.BatchNode;
/**
* Title: BatchNodeDao.java
* Description:
**/
public interface BatchNodeDao {

	public BatchNode getById(BatchNode batchNode) throws DAOException;

	public void del(BatchNode batchNode) throws DAOException;

	public void insert(BatchNode batchNode) throws DAOException;

	public void update(BatchNode batchNode) throws DAOException;
	
	public int getCount(BatchNode batchNode) throws DAOException;

	public List<BatchNode> findByPage(BatchNode batchNode) throws DAOException;
	
	public boolean isGroupRunning() throws DAOException;
	
	public List<BatchNode> getNoDepenTaskInfoList() throws DAOException;
	
	public List<BatchNode> listRules(String note_no) throws DAOException;
	
	public String getPreTaskState(String Node_no) throws DAOException;

	public boolean getFatherState(String noteNo) throws DAOException;

}