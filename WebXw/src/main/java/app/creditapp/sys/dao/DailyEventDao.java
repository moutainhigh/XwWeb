package  app.creditapp.sys.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.sys.entity.DailyEvent;
/**
* Title: DailyEventDao.java
* Description:
**/
public interface DailyEventDao {

	public DailyEvent getById(DailyEvent dailyEvent) throws DAOException;

	public void del(DailyEvent dailyEvent) throws DAOException;

	public void insert(DailyEvent dailyEvent) throws DAOException;

	public void update(DailyEvent dailyEvent) throws DAOException;
	
	public int getCount(DailyEvent dailyEvent) throws DAOException;

	public List<DailyEvent > findByPage(DailyEvent dailyEvent) throws DAOException;
	public List<DailyEvent > findAllEvent(DailyEvent dailyEvent) throws DAOException;
	public String getDailyEventSeq() throws DAOException;
	
	public void insertadd(DailyEvent dailyEvent) throws DAOException;
	
	public void fundpayRemind(DailyEvent dailyEvent) throws DAOException;
}