package  app.creditapp.acc.loan.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.loan.bo.AcLoanBackLogBo;
import app.creditapp.acc.loan.dao.AcLoanBackLogDao;
import app.creditapp.acc.loan.entity.AcLoanBackLog;
/**
* Title: AcLoanBackLogBoImplImpl.java
* Description:
**/
public class AcLoanBackLogBoImpl extends BaseService implements AcLoanBackLogBo {

	private AcLoanBackLogDao acLoanBackLogDao;

	public void del(AcLoanBackLog acLoanBackLog) throws ServiceException {
		try {
			acLoanBackLogDao.del(acLoanBackLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcLoanBackLog acLoanBackLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLoanBackLogDao
					.getCount(acLoanBackLog) });// 初始化分页类
			acLoanBackLog.setStartNumAndEndNum (ipg);
			ipg.setResult(acLoanBackLogDao.findByPage(acLoanBackLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcLoanBackLog getById(AcLoanBackLog acLoanBackLog) throws ServiceException {
		try {
			acLoanBackLog = acLoanBackLogDao.getById(acLoanBackLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLoanBackLog;
	}

	public void insert(AcLoanBackLog acLoanBackLog) throws ServiceException {
		try {
			acLoanBackLogDao.insert(acLoanBackLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLoanBackLog acLoanBackLog) throws ServiceException {
		try {
			acLoanBackLogDao.update(acLoanBackLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcLoanBackLogDao getAcLoanBackLogDao() {
		return acLoanBackLogDao;
	}

	public void setAcLoanBackLogDao(AcLoanBackLogDao acLoanBackLogDao) {
		this.acLoanBackLogDao = acLoanBackLogDao;
	}
}