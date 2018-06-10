package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.sys.entity.DailyEvent;
import app.util.toolkit.Ipage;

/**
* Title: DailyEventBo.java
* Description:
**/
public interface DailyEventBo {

	public DailyEvent getById(DailyEvent dailyEvent) throws ServiceException;

	public void del(DailyEvent dailyEvent) throws ServiceException;

	public String insert(DailyEvent dailyEvent) throws ServiceException;

	public void update(DailyEvent dailyEvent) throws ServiceException;

	public Ipage findByPage(Ipage ipg, DailyEvent dailyEvent) throws ServiceException;
	public List<DailyEvent> findAllEvent(DailyEvent dailyEvent)throws ServiceException ;
	
	public void insertadd(DailyEvent dailyEvent) throws ServiceException;
	
	public void fundpayRemind(DailyEvent dailyEvent) throws ServiceException;

}