package app.creditapp.wkf.bo.impl;

import java.util.ArrayList;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.wkf.bo.WfTaskBo;
import app.creditapp.wkf.dao.WfTaskDao;
import app.creditapp.wkf.entity.WfTask;

public class WfTaskBoImpl extends BaseService implements WfTaskBo {
	
	private WfTaskDao wfTaskDao;

	@Override
	public WfTask findTaskByAppId(WfTask wfTask) throws ServiceException {
		try {
			wfTask = wfTaskDao.findTaskByAppId(wfTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wfTask;
	}

	@Override
	public void updateToAPPROVE(WfTask wfTask) throws ServiceException {
		try {
			wfTaskDao.updateToAPPROVE(wfTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void deleteByAppId(WfTask wfTask) throws ServiceException {
		try {
			wfTaskDao.deleteByAppId(wfTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public WfTask findTaskByProcessId(WfTask wfTask) throws ServiceException {
		try {
			wfTask = wfTaskDao.findTaskByProcessId(wfTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wfTask;
	}

	@Override
	public WfTask findTaskByTaskId(WfTask wfTask) throws ServiceException {
		try {
			wfTask = wfTaskDao.findTaskByTaskId(wfTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wfTask;
	}

	@Override
	public List<WfTask> findTaskByUserId(WfTask wfTask) throws ServiceException {
		List<WfTask> list = new ArrayList<WfTask>();
		try {
			list = wfTaskDao.findTaskByUserId(wfTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public List<WfTask> findTaskByUserIdAndBranchId(WfTask wfTask) throws ServiceException {
		List<WfTask> list = new ArrayList<WfTask>();
		try {
			list = wfTaskDao.findTaskByUserIdAndBranchId(wfTask);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		return list;
	}

	public WfTaskDao getWfTaskDao() {
		return wfTaskDao;
	}

	public void setWfTaskDao(WfTaskDao wfTaskDao) {
		this.wfTaskDao = wfTaskDao;
	}

}
