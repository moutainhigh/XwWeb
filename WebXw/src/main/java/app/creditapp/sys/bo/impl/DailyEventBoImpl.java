package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sys.bo.DailyEventBo;
import app.creditapp.sys.dao.DailyEventDao;
import app.creditapp.sys.entity.DailyEvent;
import app.util.toolkit.Ipage;
/**
* Title: DailyEventBoImplImpl.java
* Description:
**/
public class DailyEventBoImpl extends BaseService implements DailyEventBo {

	private DailyEventDao dailyEventDao;

	public void del(DailyEvent dailyEvent) throws ServiceException {
		try {
			dailyEventDao.del(dailyEvent);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, DailyEvent dailyEvent)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) dailyEventDao
					.getCount(dailyEvent) });// 初始化分页类
			dailyEvent.setStartNumAndEndNum (ipg);
			ipg.setResult(dailyEventDao.findByPage(dailyEvent));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public List<DailyEvent> findAllEvent(DailyEvent dailyEvent)
			throws ServiceException {
		try {
			return dailyEventDao.findAllEvent(dailyEvent);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public DailyEvent getById(DailyEvent dailyEvent) throws ServiceException {
		try {
			dailyEvent = dailyEventDao.getById(dailyEvent);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return dailyEvent;
	}

	public String insert(DailyEvent dailyEvent) throws ServiceException {
		try {
			String dailyEventSeq = dailyEventDao.getDailyEventSeq();
			dailyEvent.setEventId(dailyEventSeq);
			dailyEventDao.insert(dailyEvent);
			return dailyEventSeq;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void insertadd(DailyEvent dailyEvent) throws ServiceException {
	try {
//		String dailyEventSeq = dailyEventDao.getDailyEventSeq();
//		dailyEvent.setEventId(dailyEventSeq);
		dailyEventDao.insertadd(dailyEvent);
	} catch (Exception e) {
		throw new ServiceException(e.getMessage());
	}
}
	public void fundpayRemind(DailyEvent dailyEvent) throws ServiceException{
		try {
			dailyEventDao.fundpayRemind(dailyEvent);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	public void update(DailyEvent dailyEvent) throws ServiceException {
		try {
			dailyEventDao.update(dailyEvent);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public DailyEventDao getDailyEventDao() {
		return dailyEventDao;
	}

	public void setDailyEventDao(DailyEventDao dailyEventDao) {
		this.dailyEventDao = dailyEventDao;
	}
}