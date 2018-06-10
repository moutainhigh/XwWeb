package  app.base.quartz.dao;

import java.util.List;

import app.base.DAOException;
import app.base.quartz.entity.ScheduleJob;
/**
* Title: ScheduleJobDao.java
* Description:
**/
public interface ScheduleJobDao {

	public ScheduleJob getById(ScheduleJob scheduleJob) throws DAOException;

	public void del(ScheduleJob scheduleJob) throws DAOException;

	public void insert(ScheduleJob scheduleJob) throws DAOException;

	public void update(ScheduleJob scheduleJob) throws DAOException;
	
	public int getCount(ScheduleJob scheduleJob) throws DAOException;

	public List<ScheduleJob > findByPage(ScheduleJob scheduleJob) throws DAOException;
	
	public void chaStart(ScheduleJob scheduleJob) throws DAOException;
	
	public void chaStop(ScheduleJob scheduleJob) throws DAOException;

}