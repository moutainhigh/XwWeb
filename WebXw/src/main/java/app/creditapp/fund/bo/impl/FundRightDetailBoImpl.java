package  app.creditapp.fund.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundRightDetailBo;
import app.creditapp.fund.dao.FundRightDetailDao;
import app.creditapp.fund.entity.FundRightDetail;
/**
* Title: FundRightDetailBoImplImpl.java
* Description:
**/
public class FundRightDetailBoImpl extends BaseService implements FundRightDetailBo {

	private FundRightDetailDao fundRightDetailDao;

	public void del(FundRightDetail fundRightDetail) throws ServiceException {
		try {
			fundRightDetailDao.del(fundRightDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundRightDetail fundRightDetail)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundRightDetailDao
					.getCount(fundRightDetail) });// 初始化分页类
			fundRightDetail.setStartNumAndEndNum (ipg);
			ipg.setResult(fundRightDetailDao.findByPage(fundRightDetail));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public FundRightDetail getById(FundRightDetail fundRightDetail) throws ServiceException {
		try {
			fundRightDetail = fundRightDetailDao.getById(fundRightDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundRightDetail;
	}

	public void insert(FundRightDetail fundRightDetail) throws ServiceException {
		try {
			fundRightDetailDao.insert(fundRightDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundRightDetail fundRightDetail) throws ServiceException {
		try {
			fundRightDetailDao.update(fundRightDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundRightDetailDao getFundRightDetailDao() {
		return fundRightDetailDao;
	}

	public void setFundRightDetailDao(FundRightDetailDao fundRightDetailDao) {
		this.fundRightDetailDao = fundRightDetailDao;
	}
}