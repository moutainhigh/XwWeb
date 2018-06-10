package  app.creditapp.aft.bo.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftRewFundBo;
import app.creditapp.aft.dao.AftRewFundDao;
import app.creditapp.aft.entity.AftRewFund;
/**
* Title: AftRewFundBoImplImpl.java
* Description:
**/
public class AftRewFundBoImpl extends BaseService implements AftRewFundBo {

	private AftRewFundDao aftRewFundDao;

	public void del(AftRewFund aftRewFund) throws ServiceException {
		try {
			aftRewFundDao.del(aftRewFund);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftRewFund aftRewFund)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewFundDao
					.getCount(aftRewFund) });// 初始化分页类
			aftRewFund.setStartNumAndEndNum (ipg);
			ipg.setResult(aftRewFundDao.findByPage(aftRewFund));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public Ipage findForAll(Ipage ipg, AftRewFund aftRewFund)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewFundDao
					.getCount(aftRewFund) });// 初始化分页类
			ipg.setResult(aftRewFundDao.findByList(aftRewFund));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftRewFund getById(AftRewFund aftRewFund) throws ServiceException {
		try {
			aftRewFund = aftRewFundDao.getById(aftRewFund);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftRewFund;
	}

	public void insert(AftRewFund aftRewFund) throws ServiceException {
		try {
			aftRewFundDao.insert(aftRewFund);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftRewFund aftRewFund) throws ServiceException {
		try {
			aftRewFundDao.update(aftRewFund);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateSts(AftRewFund aftRewFund) throws ServiceException {
		try {
			aftRewFundDao.updateSts(aftRewFund);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateDealRest(AftRewFund aftRewFund) throws ServiceException {
		try {
			aftRewFundDao.updateDealRest(aftRewFund);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public List<AftRewFund> findByList(AftRewFund aftRewFund)
			throws ServiceException {
		
		return aftRewFundDao.findByList(aftRewFund);
	}
	
	public List<AftRewFund> findByDateBetween(Map<String, String> paramMap)throws ServiceException{
		return aftRewFundDao.findByDateBetween(paramMap);
	}

	public AftRewFundDao getAftRewFundDao() {
		return aftRewFundDao;
	}

	public void setAftRewFundDao(AftRewFundDao aftRewFundDao) {
		this.aftRewFundDao = aftRewFundDao;
	}

}