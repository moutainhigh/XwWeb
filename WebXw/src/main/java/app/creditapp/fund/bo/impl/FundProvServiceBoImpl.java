package  app.creditapp.fund.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundProvServiceBo;
import app.creditapp.fund.dao.FundProvServiceDao;
import app.creditapp.fund.entity.FundProvService;
/**
* Title: FundProvServiceBoImplImpl.java
* Description:
**/
public class FundProvServiceBoImpl extends BaseService implements FundProvServiceBo {

	private FundProvServiceDao fundProvServiceDao;

	public void del(FundProvService fundProvService) throws ServiceException {
		try {
			fundProvServiceDao.del(fundProvService);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundProvService fundProvService)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundProvServiceDao
					.getCount(fundProvService) });// 初始化分页类
			fundProvService.setStartNumAndEndNum (ipg);
			ipg.setResult(fundProvServiceDao.findByPage(fundProvService));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public FundProvService getById(FundProvService fundProvService) throws ServiceException {
		try {
			fundProvService = fundProvServiceDao.getById(fundProvService);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundProvService;
	}

	public void insert(FundProvService fundProvService) throws ServiceException {
		try {
			fundProvServiceDao.insert(fundProvService);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundProvService fundProvService) throws ServiceException {
		try {
			fundProvServiceDao.update(fundProvService);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundProvServiceDao getFundProvServiceDao() {
		return fundProvServiceDao;
	}

	public void setFundProvServiceDao(FundProvServiceDao fundProvServiceDao) {
		this.fundProvServiceDao = fundProvServiceDao;
	}
}