package  app.creditapp.fund.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundSplitDetailBo;
import app.creditapp.fund.dao.FundSplitDetailDao;
import app.creditapp.fund.entity.FundSplitDetail;
/**
* Title: FundSplitDetailBoImplImpl.java
* Description:
**/
public class FundSplitDetailBoImpl extends BaseService implements FundSplitDetailBo {

	private FundSplitDetailDao fundSplitDetailDao;

	public void del(FundSplitDetail fundSplitDetail) throws ServiceException {
		try {
			fundSplitDetailDao.del(fundSplitDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundSplitDetail fundSplitDetail)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundSplitDetailDao
					.getCount(fundSplitDetail) });// 初始化分页类
			fundSplitDetail.setStartNumAndEndNum (ipg);
			ipg.setResult(fundSplitDetailDao.findByPage(fundSplitDetail));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public FundSplitDetail getById(FundSplitDetail fundSplitDetail) throws ServiceException {
		try {
			fundSplitDetail = fundSplitDetailDao.getById(fundSplitDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundSplitDetail;
	}

	public void insert(FundSplitDetail fundSplitDetail) throws ServiceException {
		try {
			fundSplitDetailDao.insert(fundSplitDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundSplitDetail fundSplitDetail) throws ServiceException {
		try {
			fundSplitDetailDao.update(fundSplitDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundSplitDetailDao getFundSplitDetailDao() {
		return fundSplitDetailDao;
	}

	public void setFundSplitDetailDao(FundSplitDetailDao fundSplitDetailDao) {
		this.fundSplitDetailDao = fundSplitDetailDao;
	}
}