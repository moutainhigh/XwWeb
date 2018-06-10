package  app.creditapp.fund.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.fund.bo.FundSplitBo;
import app.creditapp.fund.dao.FundSplitDao;
import app.creditapp.fund.entity.FundSplit;
import app.util.toolkit.Ipage;
/**
* Title: FundSplitBoImplImpl.java
* Description:
**/
public class FundSplitBoImpl extends BaseService implements FundSplitBo {

	private FundSplitDao fundSplitDao;

	public void del(FundSplit fundSplit) throws ServiceException {
		try {
			fundSplitDao.del(fundSplit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundSplit fundSplit)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundSplitDao
					.getCount(fundSplit) });// 初始化分页类
			fundSplit.setStartNumAndEndNum (ipg);
			ipg.setResult(fundSplitDao.findByPage(fundSplit));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public  List<FundSplit> echarts(FundSplit fundSplit) throws ServiceException {
		List fundSplitList = null;
		try {
			fundSplitList = fundSplitDao.echarts(fundSplit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundSplitList;
	}
	public FundSplit getById(FundSplit fundSplit) throws ServiceException {
		try {
			fundSplit = fundSplitDao.getById(fundSplit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundSplit;
	}

	public void insert(FundSplit fundSplit) throws ServiceException {
		try {
			fundSplitDao.insert(fundSplit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundSplit fundSplit) throws ServiceException {
		try {
			fundSplitDao.update(fundSplit);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundSplitDao getFundSplitDao() {
		return fundSplitDao;
	}

	public void setFundSplitDao(FundSplitDao fundSplitDao) {
		this.fundSplitDao = fundSplitDao;
	}
}