package  app.creditapp.acc.loan.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.loan.bo.AcLnPmLogBo;
import app.creditapp.acc.loan.dao.AcLnPmLogDao;
import app.creditapp.acc.loan.entity.AcLnPmLog;
/**
* Title: AcLnPmLogBoImplImpl.java
* Description:
**/
public class AcLnPmLogBoImpl extends BaseService implements AcLnPmLogBo {

	private AcLnPmLogDao acLnPmLogDao;

	public void del(AcLnPmLog acLnPmLog) throws ServiceException {
		try {
			acLnPmLogDao.del(acLnPmLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLnPmLog acLnPmLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnPmLogDao
					.getCount(acLnPmLog) });// 初始化分页类
			acLnPmLog.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnPmLogDao.findByPage(acLnPmLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcLnPmLog getById(AcLnPmLog acLnPmLog) throws ServiceException {
		try {
			acLnPmLog = acLnPmLogDao.getById(acLnPmLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnPmLog;
	}

	public void insert(AcLnPmLog acLnPmLog) throws ServiceException {
		try {
			acLnPmLogDao.insert(acLnPmLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLnPmLog acLnPmLog) throws ServiceException {
		try {
			acLnPmLogDao.update(acLnPmLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcLnPmLogDao getAcLnPmLogDao() {
		return acLnPmLogDao;
	}

	public void setAcLnPmLogDao(AcLnPmLogDao acLnPmLogDao) {
		this.acLnPmLogDao = acLnPmLogDao;
	}
}