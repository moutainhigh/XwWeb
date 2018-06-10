package  app.creditapp.fund.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.fund.bo.RptFundDailyBo;
import app.creditapp.fund.dao.RptFundDailyDao;
import app.creditapp.fund.entity.RptFundDaily;
import app.util.toolkit.Ipage;
/**
* Title: RptFundDailyBoImplImpl.java
* Description:
**/
public class RptFundDailyBoImpl extends BaseService implements RptFundDailyBo {

	private RptFundDailyDao rptFundDailyDao;

	public void del(RptFundDaily rptFundDaily) throws ServiceException {
		try {
			rptFundDailyDao.del(rptFundDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, RptFundDaily rptFundDaily)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) rptFundDailyDao
					.getCount(rptFundDaily) });// 初始化分页类
			rptFundDaily.setStartNumAndEndNum (ipg);
			ipg.setResult(rptFundDailyDao.findByPage(rptFundDaily));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public RptFundDaily getById(RptFundDaily rptFundDaily) throws ServiceException {
		try {
			rptFundDaily = rptFundDailyDao.getById(rptFundDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptFundDaily;
	}

	public void insert(RptFundDaily rptFundDaily) throws ServiceException {
		try {
			rptFundDailyDao.insert(rptFundDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(RptFundDaily rptFundDaily) throws ServiceException {
		try {
			rptFundDailyDao.update(rptFundDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public List<RptFundDaily> findByFdType(RptFundDaily rptFundDaily) throws ServiceException {
		List<RptFundDaily> RptFundDailyList = null;
        try {
        	 RptFundDailyList = rptFundDailyDao.findByFdType(rptFundDaily);
        } catch (Exception e) {
	        throw new ServiceException(e.getMessage());
        }
        return RptFundDailyList;
    }
	@Override
	public List<RptFundDaily> getList(RptFundDaily rptFundDaily)
			throws ServiceException {
		List<RptFundDaily> list = null;
		try {
			list = rptFundDailyDao.getList(rptFundDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	public RptFundDailyDao getRptFundDailyDao() {
		return rptFundDailyDao;
	}

	public void setRptFundDailyDao(RptFundDailyDao rptFundDailyDao) {
		this.rptFundDailyDao = rptFundDailyDao;
	}
}