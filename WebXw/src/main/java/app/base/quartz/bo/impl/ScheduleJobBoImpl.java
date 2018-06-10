package  app.base.quartz.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.base.quartz.bo.ScheduleJobBo;
import app.base.quartz.dao.ScheduleJobDao;
import app.base.quartz.entity.ScheduleJob;
import app.util.toolkit.Ipage;
/**
* Title: ScheduleJobBoImplImpl.java
* Description:
**/
public class ScheduleJobBoImpl extends BaseService implements ScheduleJobBo {

	private ScheduleJobDao scheduleJobDao;

	public void del(ScheduleJob scheduleJob) throws ServiceException {
		try {
			scheduleJobDao.del(scheduleJob);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ScheduleJob scheduleJob)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) scheduleJobDao
					.getCount(scheduleJob) });// 初始化分页类
			scheduleJob.setStartNumAndEndNum (ipg);
			ipg.setResult(scheduleJobDao.findByPage(scheduleJob));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public ScheduleJob getById(ScheduleJob scheduleJob) throws ServiceException {
		try {
			scheduleJob = scheduleJobDao.getById(scheduleJob);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return scheduleJob;
	}

	public void insert(ScheduleJob scheduleJob) throws ServiceException {
		try {
			scheduleJobDao.insert(scheduleJob);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ScheduleJob scheduleJob) throws ServiceException {
		try {
			scheduleJobDao.update(scheduleJob);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void chaStart(ScheduleJob scheduleJob) throws ServiceException {
		try {
			scheduleJobDao.chaStart(scheduleJob);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	@Override
	public void chaStop(ScheduleJob scheduleJob) throws ServiceException {
		try {
			scheduleJobDao.chaStop(scheduleJob);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public ScheduleJobDao getScheduleJobDao() {
		return scheduleJobDao;
	}

	public void setScheduleJobDao(ScheduleJobDao scheduleJobDao) {
		this.scheduleJobDao = scheduleJobDao;
	}


}