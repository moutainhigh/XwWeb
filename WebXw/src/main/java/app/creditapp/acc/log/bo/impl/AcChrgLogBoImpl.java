package  app.creditapp.acc.log.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.log.bo.AcChrgLogBo;
import app.creditapp.acc.log.dao.AcChrgLogDao;
import app.creditapp.acc.log.entity.AcChrgLog;
/**
* Title: AcChrgLogBoImplImpl.java
* Description:
**/
public class AcChrgLogBoImpl extends BaseService implements AcChrgLogBo {

	private AcChrgLogDao acChrgLogDao;

	public void del(AcChrgLog acChrgLog) throws ServiceException {
		try {
			acChrgLogDao.del(acChrgLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcChrgLog acChrgLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acChrgLogDao
					.getCount(acChrgLog) });// 初始化分页类
			acChrgLog.setStartNumAndEndNum (ipg);
			ipg.setResult(acChrgLogDao.findByPage(acChrgLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcChrgLog getById(AcChrgLog acChrgLog) throws ServiceException {
		try {
			acChrgLog = acChrgLogDao.getById(acChrgLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acChrgLog;
	}
	@Override
	public AcChrgLog getAllAmt(AcChrgLog acChrgLog) throws ServiceException {
		try {
			acChrgLog = acChrgLogDao.getAllAmt(acChrgLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acChrgLog;
	}
	public double getLoFeeAmt(AcChrgLog acChrgLog) throws ServiceException {
		double loAmt = 0.00;
		try {
			loAmt = acChrgLogDao.getLoFeeAmt(acChrgLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return loAmt;
	}

	public void insert(AcChrgLog acChrgLog) throws ServiceException {
		try {
			acChrgLogDao.insert(acChrgLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcChrgLog acChrgLog) throws ServiceException {
		try {
			acChrgLogDao.update(acChrgLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcChrgLogDao getAcChrgLogDao() {
		return acChrgLogDao;
	}

	public void setAcChrgLogDao(AcChrgLogDao acChrgLogDao) {
		this.acChrgLogDao = acChrgLogDao;
	}


}