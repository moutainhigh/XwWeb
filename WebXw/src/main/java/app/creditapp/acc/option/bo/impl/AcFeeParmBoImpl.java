package  app.creditapp.acc.option.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcFeeParmBo;
import app.creditapp.acc.option.dao.AcFeeParmDao;
import app.creditapp.acc.option.entity.AcFeeParm;
/**
* Title: AcFeeParmBoImplImpl.java
* Description:
**/
public class AcFeeParmBoImpl extends BaseService implements AcFeeParmBo {

	private AcFeeParmDao acFeeParmDao;

	public void del(AcFeeParm acFeeParm) throws ServiceException {
		try {
			acFeeParmDao.del(acFeeParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcFeeParm acFeeParm)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acFeeParmDao
					.getCount(acFeeParm) });// 初始化分页类
			acFeeParm.setStartNumAndEndNum (ipg);
			ipg.setResult(acFeeParmDao.findByPage(acFeeParm));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcFeeParm getById(AcFeeParm acFeeParm) throws ServiceException {
		try {
			acFeeParm = acFeeParmDao.getById(acFeeParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acFeeParm;
	}

	public void insert(AcFeeParm acFeeParm) throws ServiceException {
		try {
			acFeeParmDao.insert(acFeeParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcFeeParm acFeeParm) throws ServiceException {
		try {
			acFeeParmDao.update(acFeeParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateSts(AcFeeParm acFeeParm) throws ServiceException {
		try {
			acFeeParmDao.updateSts(acFeeParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcFeeParmDao getAcFeeParmDao() {
		return acFeeParmDao;
	}

	public void setAcFeeParmDao(AcFeeParmDao acFeeParmDao) {
		this.acFeeParmDao = acFeeParmDao;
	}
}