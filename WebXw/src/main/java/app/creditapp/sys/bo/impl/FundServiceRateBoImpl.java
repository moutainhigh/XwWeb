package  app.creditapp.sys.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.FundServiceRateBo;
import app.creditapp.sys.dao.FundServiceRateDao;
import app.creditapp.sys.entity.FundServiceRate;
/**
* Title: FundServiceRateBoImplImpl.java
* Description:
**/
public class FundServiceRateBoImpl extends BaseService implements FundServiceRateBo {

	private FundServiceRateDao fundServiceRateDao;

	public void del(FundServiceRate fundServiceRate) throws ServiceException {
		try {
			fundServiceRateDao.del(fundServiceRate);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundServiceRate fundServiceRate)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundServiceRateDao
					.getCount(fundServiceRate) });// 初始化分页类
			fundServiceRate.setStartNumAndEndNum (ipg);
			ipg.setResult(fundServiceRateDao.findByPage(fundServiceRate));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public FundServiceRate getById(FundServiceRate fundServiceRate) throws ServiceException {
		try {
			fundServiceRate = fundServiceRateDao.getById(fundServiceRate);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundServiceRate;
	}

	public void insert(FundServiceRate fundServiceRate) throws ServiceException {
		try {
			fundServiceRateDao.insert(fundServiceRate);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundServiceRate fundServiceRate) throws ServiceException {
		try {
			fundServiceRateDao.update(fundServiceRate);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundServiceRateDao getFundServiceRateDao() {
		return fundServiceRateDao;
	}

	public void setFundServiceRateDao(FundServiceRateDao fundServiceRateDao) {
		this.fundServiceRateDao = fundServiceRateDao;
	}
}