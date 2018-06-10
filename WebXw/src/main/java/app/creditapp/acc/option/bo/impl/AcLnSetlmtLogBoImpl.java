package  app.creditapp.acc.option.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcLnSetlmtLogBo;
import app.creditapp.acc.option.dao.AcLnSetlmtLogDao;
import app.creditapp.acc.option.entity.AcLnSetlmtLog;
/**
* Title: AcLnSetlmtLogBoImplImpl.java
* Description:
**/
public class AcLnSetlmtLogBoImpl extends BaseService implements AcLnSetlmtLogBo {

	private AcLnSetlmtLogDao acLnSetlmtLogDao;

	public void del(AcLnSetlmtLog acLnSetlmtLog) throws ServiceException {
		try {
			acLnSetlmtLogDao.del(acLnSetlmtLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLnSetlmtLog acLnSetlmtLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnSetlmtLogDao
					.getCount(acLnSetlmtLog) });// 初始化分页类
			acLnSetlmtLog.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnSetlmtLogDao.findByPage(acLnSetlmtLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcLnSetlmtLog getById(AcLnSetlmtLog acLnSetlmtLog) throws ServiceException {
		try {
			acLnSetlmtLog = acLnSetlmtLogDao.getById(acLnSetlmtLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnSetlmtLog;
	}

	public void insert(AcLnSetlmtLog acLnSetlmtLog) throws ServiceException {
		try {
			acLnSetlmtLogDao.insert(acLnSetlmtLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLnSetlmtLog acLnSetlmtLog) throws ServiceException {
		try {
			acLnSetlmtLogDao.update(acLnSetlmtLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcLnSetlmtLogDao getAcLnSetlmtLogDao() {
		return acLnSetlmtLogDao;
	}

	public void setAcLnSetlmtLogDao(AcLnSetlmtLogDao acLnSetlmtLogDao) {
		this.acLnSetlmtLogDao = acLnSetlmtLogDao;
	}
}