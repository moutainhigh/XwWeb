package  app.creditapp.acc.log.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.log.bo.AcLnIntstLogBo;
import app.creditapp.acc.log.dao.AcLnIntstLogDao;
import app.creditapp.acc.log.entity.AcLnIntstLog;
/**
* Title: AcLnIntstLogBoImplImpl.java
* Description:
**/
public class AcLnIntstLogBoImpl extends BaseService implements AcLnIntstLogBo {

	private AcLnIntstLogDao acLnIntstLogDao;

	public void del(AcLnIntstLog acLnIntstLog) throws ServiceException {
		try {
			acLnIntstLogDao.del(acLnIntstLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLnIntstLog acLnIntstLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnIntstLogDao
					.getCount(acLnIntstLog) });// 初始化分页类
			acLnIntstLog.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnIntstLogDao.findByPage(acLnIntstLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcLnIntstLog getById(AcLnIntstLog acLnIntstLog) throws ServiceException {
		try {
			acLnIntstLog = acLnIntstLogDao.getById(acLnIntstLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnIntstLog;
	}

	public void insert(AcLnIntstLog acLnIntstLog) throws ServiceException {
		try {
			acLnIntstLogDao.insert(acLnIntstLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLnIntstLog acLnIntstLog) throws ServiceException {
		try {
			acLnIntstLogDao.update(acLnIntstLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcLnIntstLogDao getAcLnIntstLogDao() {
		return acLnIntstLogDao;
	}

	public void setAcLnIntstLogDao(AcLnIntstLogDao acLnIntstLogDao) {
		this.acLnIntstLogDao = acLnIntstLogDao;
	}
}