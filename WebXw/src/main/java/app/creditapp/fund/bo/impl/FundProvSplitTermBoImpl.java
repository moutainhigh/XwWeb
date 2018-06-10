package  app.creditapp.fund.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.fund.bo.FundProvSplitTermBo;
import app.creditapp.fund.dao.FundProvSplitTermDao;
import app.creditapp.fund.entity.FundProvSplitTerm;
/**
* Title: FundProvSplitTermBoImplImpl.java
* Description:
**/
public class FundProvSplitTermBoImpl extends BaseService implements FundProvSplitTermBo {

	private FundProvSplitTermDao fundProvSplitTermDao;

	public void del(FundProvSplitTerm fundProvSplitTerm) throws ServiceException {
		try {
			fundProvSplitTermDao.del(fundProvSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundProvSplitTerm fundProvSplitTerm)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundProvSplitTermDao
					.getCount(fundProvSplitTerm) });// 初始化分页类
			fundProvSplitTerm.setStartNumAndEndNum (ipg);
			ipg.setResult(fundProvSplitTermDao.findByPage(fundProvSplitTerm));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

//管理费用查询	
	public Ipage Manager_findByPage(Ipage ipg, FundProvSplitTerm fundProvSplitTerm)
	        throws ServiceException {
        try {
	        ipg.initPageCounts(new Integer[] { (Integer) fundProvSplitTermDao
			        .Manager_getCount(fundProvSplitTerm) });// 初始化分页类
	        fundProvSplitTerm.setStartNumAndEndNum (ipg);
	        ipg.setResult(fundProvSplitTermDao.Manager_findByPage(fundProvSplitTerm));
        } catch (Exception e) {
	        throw new ServiceException(e.getMessage());
        }
        return ipg;
    }
	public FundProvSplitTerm getById(FundProvSplitTerm fundProvSplitTerm) throws ServiceException {
		try {
			fundProvSplitTerm = fundProvSplitTermDao.getById(fundProvSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundProvSplitTerm;
	}
	//管理费用查询
	public FundProvSplitTerm Manager_getById(FundProvSplitTerm fundProvSplitTerm) throws ServiceException {
		try {
			fundProvSplitTerm = fundProvSplitTermDao.getById(fundProvSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundProvSplitTerm;
	}

	public void insert(FundProvSplitTerm fundProvSplitTerm) throws ServiceException {
		try {
			fundProvSplitTermDao.insert(fundProvSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(FundProvSplitTerm fundProvSplitTerm) throws ServiceException {
		try {
			fundProvSplitTermDao.update(fundProvSplitTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public FundProvSplitTermDao getFundProvSplitTermDao() {
		return fundProvSplitTermDao;
	}

	public void setFundProvSplitTermDao(FundProvSplitTermDao fundProvSplitTermDao) {
		this.fundProvSplitTermDao = fundProvSplitTermDao;
	}
}