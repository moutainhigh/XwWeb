package  app.creditapp.fund.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundProvBo;
import app.creditapp.fund.dao.FundProvDao;
import app.creditapp.fund.entity.FundProv;
/**
* Title: FundProvBoImplImpl.java
* Description:
**/
public class FundProvBoImpl extends BaseService implements FundProvBo {

	private FundProvDao fundProvDao;

	public void del(FundProv fundProv) throws ServiceException {
		try {
			fundProvDao.del(fundProv);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundProv fundProv)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundProvDao
					.getCount(fundProv) });// 初始化分页类
			fundProv.setStartNumAndEndNum (ipg);
			ipg.setResult(fundProvDao.findByPage(fundProv));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public FundProv getById(FundProv fundProv) throws ServiceException {
		try {
			fundProv = fundProvDao.getById(fundProv);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundProv;
	}

	public void insert(FundProv fundProv) throws ServiceException {
		try {
			fundProvDao.insert(fundProv);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundProv fundProv) throws ServiceException {
		try {
			fundProvDao.update(fundProv);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundProvDao getFundProvDao() {
		return fundProvDao;
	}

	public void setFundProvDao(FundProvDao fundProvDao) {
		this.fundProvDao = fundProvDao;
	}
}