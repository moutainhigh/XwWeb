package  app.creditapp.acc.option.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcFeeRateBo;
import app.creditapp.acc.option.dao.AcFeeRateDao;
import app.creditapp.acc.option.entity.AcFeeRate;
/**
* Title: AcFeeRateBoImplImpl.java
* Description:
**/
public class AcFeeRateBoImpl extends BaseService implements AcFeeRateBo {

	private AcFeeRateDao acFeeRateDao;

	public void del(AcFeeRate acFeeRate) throws ServiceException {
		try {
			acFeeRateDao.del(acFeeRate);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcFeeRate acFeeRate)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acFeeRateDao
					.getCount(acFeeRate) });// 初始化分页类
			acFeeRate.setStartNumAndEndNum (ipg);
			ipg.setResult(acFeeRateDao.findByPage(acFeeRate));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcFeeRate getById(AcFeeRate acFeeRate) throws ServiceException {
		try {
			acFeeRate = acFeeRateDao.getById(acFeeRate);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acFeeRate;
	}

	public void insert(AcFeeRate acFeeRate) throws ServiceException {
		try {
			acFeeRateDao.insert(acFeeRate);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcFeeRate acFeeRate) throws ServiceException {
		try {
			acFeeRateDao.update(acFeeRate);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcFeeRateDao getAcFeeRateDao() {
		return acFeeRateDao;
	}

	public void setAcFeeRateDao(AcFeeRateDao acFeeRateDao) {
		this.acFeeRateDao = acFeeRateDao;
	}
}