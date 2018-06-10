package  app.creditapp.sys.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.DailyEventDao;
import app.creditapp.sys.entity.DailyEvent;
/**
* Title: DailyEventDaoImpl.java
* Description:
**/
public class DailyEventDaoImpl extends BaseIbatisDao implements DailyEventDao {

	public void del(DailyEvent dailyEvent) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("DailyEvent.del", dailyEvent);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<DailyEvent> findByPage(DailyEvent dailyEvent) throws DAOException {
		List dailyEventList = null;
		try {
			dailyEventList = getSqlMapClientTemplate().queryForList(
					"DailyEvent.findByPage", dailyEvent);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return dailyEventList;
	}
	
	public List<DailyEvent> findAllEvent(DailyEvent dailyEvent) throws DAOException {
		List dailyEventList = null;
		try {
			dailyEventList = getSqlMapClientTemplate().queryForList(
					"DailyEvent.findAll", dailyEvent);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return dailyEventList;
	}

	public DailyEvent getById(DailyEvent dailyEvent) throws DAOException {
		try {
			dailyEvent = (DailyEvent) getSqlMapClientTemplate()
					.queryForObject("DailyEvent.getById", dailyEvent);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return dailyEvent;
	}

	public int getCount(DailyEvent dailyEvent) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"DailyEvent.findPage.count", dailyEvent);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public String getDailyEventSeq() throws DAOException {
		String deSeq = null;
		try {
			deSeq = (String) getSqlMapClientTemplate().queryForObject(
					"DailyEvent.nextSeq");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return deSeq;
	}

	public void insert(DailyEvent dailyEvent) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("DailyEvent.insert",
					dailyEvent);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(DailyEvent dailyEvent) throws DAOException {
		try {
			getSqlMapClientTemplate().update("DailyEvent.updateInfo",
					dailyEvent);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void insertadd(DailyEvent dailyEvent) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("DailyEvent.insertadd",dailyEvent
					);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	public void fundpayRemind(DailyEvent dailyEvent){
		try {
			getSqlMapClientTemplate().insert("DailyEvent.insertaddremind",dailyEvent
					);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}