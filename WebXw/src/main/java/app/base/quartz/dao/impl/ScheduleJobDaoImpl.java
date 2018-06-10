package  app.base.quartz.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.base.quartz.dao.ScheduleJobDao;
import app.base.quartz.entity.ScheduleJob;
/**
* Title: ScheduleJobDaoImpl.java
* Description:
**/
public class ScheduleJobDaoImpl extends BaseIbatisDao implements ScheduleJobDao {

	public void del(ScheduleJob scheduleJob) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("ScheduleJob.del", scheduleJob);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<ScheduleJob> findByPage(ScheduleJob scheduleJob) throws DAOException {
		List scheduleJobList = null;
		try {
			scheduleJobList = getSqlMapClientTemplate().queryForList(
					"ScheduleJob.findByPage", scheduleJob);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return scheduleJobList;
	}

	public ScheduleJob getById(ScheduleJob scheduleJob) throws DAOException {
		try {
			scheduleJob = (ScheduleJob) getSqlMapClientTemplate()
					.queryForObject("ScheduleJob.getById", scheduleJob);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return scheduleJob;
	}

	public int getCount(ScheduleJob scheduleJob) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"ScheduleJob.findPage.count", scheduleJob);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(ScheduleJob scheduleJob) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("ScheduleJob.insert",
					scheduleJob);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(ScheduleJob scheduleJob) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ScheduleJob.update",
					scheduleJob);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void chaStart(ScheduleJob scheduleJob) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ScheduleJob.chaStart",
					scheduleJob);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}
	@Override
	public void chaStop(ScheduleJob scheduleJob) throws DAOException {
		try {
			getSqlMapClientTemplate().update("ScheduleJob.chaStop",
					scheduleJob);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		
	}
}