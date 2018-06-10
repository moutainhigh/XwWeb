package  app.base.quartz.bo;

import app.base.DAOException;
import app.base.ServiceException;
import app.base.quartz.entity.ScheduleJob;
import app.util.toolkit.Ipage;

/**
* Title: ScheduleJobBo.java
* Description:
**/
public interface ScheduleJobBo {

	public ScheduleJob getById(ScheduleJob scheduleJob) throws ServiceException;

	public void del(ScheduleJob scheduleJob) throws ServiceException;

	public void insert(ScheduleJob scheduleJob) throws ServiceException;

	public void update(ScheduleJob scheduleJob) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ScheduleJob scheduleJob) throws ServiceException;
	
	public void chaStart(ScheduleJob scheduleJob) throws ServiceException;
	
	public void chaStop(ScheduleJob scheduleJob) throws ServiceException;

}