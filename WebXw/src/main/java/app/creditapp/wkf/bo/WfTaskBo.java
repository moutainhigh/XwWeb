package app.creditapp.wkf.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.wkf.entity.WfTask;

public interface WfTaskBo {
	
	public WfTask findTaskByAppId(WfTask wfTask)throws ServiceException;
	
	public WfTask findTaskByProcessId(WfTask wfTask)throws ServiceException;
	
	public WfTask findTaskByTaskId(WfTask wfTask)throws ServiceException;
	
	public List<WfTask> findTaskByUserId(WfTask wfTask)throws ServiceException;
	
	public List<WfTask> findTaskByUserIdAndBranchId(WfTask wfTask)throws ServiceException;
	
	public void deleteByAppId(WfTask wfTask)throws ServiceException;
	
	public void updateToAPPROVE(WfTask wfTask)throws ServiceException;

}
