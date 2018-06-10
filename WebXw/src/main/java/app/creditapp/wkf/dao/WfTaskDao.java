package app.creditapp.wkf.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.wkf.entity.WfTask;

public interface WfTaskDao {
	
	public WfTask findTaskByAppId(WfTask wfTask)throws DAOException;
	
	public WfTask findTaskByProcessId(WfTask wfTask)throws DAOException;
	
	public WfTask findTaskByTaskId(WfTask wfTask)throws DAOException;
	
	public List<WfTask> findTaskByUserId(WfTask wfTask)throws DAOException;
	
	public List<WfTask> findTaskByUserIdAndBranchId(WfTask wfTask)throws DAOException;
	
	public void deleteByAppId(WfTask wfTask)throws DAOException;
	
	public void updateToAPPROVE(WfTask wfTask)throws DAOException;

}
