package  app.creditapp.aft.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftRewDealBo;
import app.creditapp.aft.dao.AftRewDealDao;
import app.creditapp.aft.entity.AftRewDeal;
/**
* Title: AftRewDealBoImplImpl.java
**/
public class AftRewDealBoImpl extends BaseService implements AftRewDealBo {

	private AftRewDealDao aftRewDealDao;

	public void del(AftRewDeal aftRewDeal) throws ServiceException {
		try {
			aftRewDealDao.del(aftRewDeal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftRewDeal aftRewDeal)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewDealDao
					.getCount(aftRewDeal) });// 初始化分页类
			aftRewDeal.setStartNumAndEndNum (ipg);
			ipg.setResult(aftRewDealDao.findByPage(aftRewDeal));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftRewDeal getById(AftRewDeal aftRewDeal) throws ServiceException {
		try {
			aftRewDeal = aftRewDealDao.getById(aftRewDeal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftRewDeal;
	}

	public void insert(AftRewDeal aftRewDeal) throws ServiceException {
		try {
			aftRewDealDao.insert(aftRewDeal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftRewDeal aftRewDeal) throws ServiceException {
		try {
			aftRewDealDao.update(aftRewDeal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AftRewDealDao getAftRewDealDao() {
		return aftRewDealDao;
	}

	public void setAftRewDealDao(AftRewDealDao aftRewDealDao) {
		this.aftRewDealDao = aftRewDealDao;
	}
}