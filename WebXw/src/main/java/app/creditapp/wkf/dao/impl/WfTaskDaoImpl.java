package app.creditapp.wkf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.wkf.dao.WfTaskDao;
import app.creditapp.wkf.entity.WfTask;

public class WfTaskDaoImpl extends BaseIbatisDao implements WfTaskDao {

	@Override
	public WfTask findTaskByProcessId(WfTask wfTask) throws DAOException {
		try {
			wfTask = (WfTask) getSqlMapClientTemplate()
					.queryForObject("WfTask.findTaskByProcessId", wfTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wfTask;
	}

	@Override
	public void updateToAPPROVE(WfTask wfTask) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WfTask.updateToAPPROVE", wfTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}

	@Override
	public void deleteByAppId(WfTask wfTask) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WfTask.deleteByAppId", wfTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}

	@Override
	public WfTask findTaskByAppId(WfTask wfTask) throws DAOException {
		try {
			wfTask = (WfTask) getSqlMapClientTemplate()
					.queryForObject("WfTask.findTaskByAppId", wfTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wfTask;
	}

	@Override
	public WfTask findTaskByTaskId(WfTask wfTask) throws DAOException {
		try {
			wfTask = (WfTask) getSqlMapClientTemplate()
					.queryForObject("WfTask.findTaskByTaskId", wfTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wfTask;
	}

	@Override
	public List<WfTask> findTaskByUserId(WfTask wfTask) throws DAOException {
		List<WfTask> list = new ArrayList<WfTask>();
		try {
			list = getSqlMapClientTemplate().queryForList(
					"WfTask.findTaskByUserId", wfTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return list;
	}

	@Override
	public List<WfTask> findTaskByUserIdAndBranchId(WfTask wfTask) throws DAOException {
		List<WfTask> list = new ArrayList<WfTask>();
		try {
			list = getSqlMapClientTemplate().queryForList(
					"WfTask.findTaskByUserIdAndBranchId", wfTask);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return list;
	}

}
