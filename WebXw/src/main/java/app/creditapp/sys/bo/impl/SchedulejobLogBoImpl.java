package  app.creditapp.sys.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.base.SourceTemplate;
import app.creditapp.sys.bo.SchedulejobLogBo;
import app.creditapp.sys.dao.SchedulejobLogDao;
import app.creditapp.sys.entity.SchedulejobLog;
import app.util.toolkit.Ipage;
/**
* Title: SchedulejobLogBoImpl.java
* Description:
**/
public class SchedulejobLogBoImpl extends BaseService implements SchedulejobLogBo {

	private SchedulejobLogDao schedulejobLogDao;

	public void del(SchedulejobLog schedulejobLog) throws ServiceException {
		try {
			schedulejobLogDao.del(schedulejobLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SchedulejobLog schedulejobLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) schedulejobLogDao
					.getCount(schedulejobLog) });// 初始化分页类
			schedulejobLog.setStartNumAndEndNum (ipg);
			ipg.setResult(schedulejobLogDao.findByPage(schedulejobLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SchedulejobLog getById(SchedulejobLog schedulejobLog) throws ServiceException {
		try {
			schedulejobLog = schedulejobLogDao.getById(schedulejobLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return schedulejobLog;
	}

	public void insert(SchedulejobLog schedulejobLog) throws ServiceException {
		try {
			if(schedulejobLogDao == null){
				schedulejobLogDao = SourceTemplate.getSpringContextInstance().getBean(
						"schedulejobLogDao", SchedulejobLogDao.class);
			}
			String schedulejobLogSeq = schedulejobLogDao.getSchedulejobLogSeq();
			schedulejobLog.setLogId(schedulejobLogSeq);
			schedulejobLogDao.insert(schedulejobLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SchedulejobLog schedulejobLog) throws ServiceException {
		try {
			schedulejobLogDao.update(schedulejobLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public SchedulejobLogDao getSchedulejobLogDao() {
		return schedulejobLogDao;
	}

	public void setSchedulejobLogDao(SchedulejobLogDao schedulejobLogDao) {
		this.schedulejobLogDao = schedulejobLogDao;
	}
}