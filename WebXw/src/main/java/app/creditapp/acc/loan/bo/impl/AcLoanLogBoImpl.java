package  app.creditapp.acc.loan.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.loan.bo.AcLoanLogBo;
import app.creditapp.acc.loan.dao.AcLoanLogDao;
import app.creditapp.acc.loan.entity.AcLoanLog;
/**
* Title: AcLoanLogBoImplImpl.java
* Description:
**/
public class AcLoanLogBoImpl extends BaseService implements AcLoanLogBo {

	private AcLoanLogDao acLoanLogDao;

	public void del(AcLoanLog acLoanLog) throws ServiceException {
		try {
			acLoanLogDao.del(acLoanLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLoanLog acLoanLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLoanLogDao
					.getCount(acLoanLog) });// 初始化分页类
			acLoanLog.setStartNumAndEndNum (ipg);
			ipg.setResult(acLoanLogDao.findByPage(acLoanLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcLoanLog getById(AcLoanLog acLoanLog) throws ServiceException {
		try {
			acLoanLog = acLoanLogDao.getById(acLoanLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLoanLog;
	}

	public void insert(AcLoanLog acLoanLog) throws ServiceException {
		try {
			acLoanLogDao.insert(acLoanLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLoanLog acLoanLog) throws ServiceException {
		try {
			acLoanLogDao.update(acLoanLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcLoanLogDao getAcLoanLogDao() {
		return acLoanLogDao;
	}

	public void setAcLoanLogDao(AcLoanLogDao acLoanLogDao) {
		this.acLoanLogDao = acLoanLogDao;
	}
}