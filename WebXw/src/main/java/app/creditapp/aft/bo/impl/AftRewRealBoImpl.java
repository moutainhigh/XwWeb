package  app.creditapp.aft.bo.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.aft.bo.AftRewRealBo;
import app.creditapp.aft.dao.AftRewRealDao;
import app.creditapp.aft.entity.AftRewReal;
import app.util.toolkit.Ipage;
/**
* Title: AftRewRealBoImplImpl.java
**/
public class AftRewRealBoImpl extends BaseService implements AftRewRealBo {

	private AftRewRealDao aftRewRealDao;

	public void del(AftRewReal aftRewReal) throws ServiceException {
		try {
			aftRewRealDao.del(aftRewReal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftRewReal aftRewReal)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewRealDao
					.getCount(aftRewReal) });// 初始化分页类
			aftRewReal.setStartNumAndEndNum (ipg);
			ipg.setResult(aftRewRealDao.findByPage(aftRewReal));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftRewReal getById(AftRewReal aftRewReal) throws ServiceException {
		try {
			aftRewReal = aftRewRealDao.getById(aftRewReal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftRewReal;
	}

	public void insert(AftRewReal aftRewReal) throws ServiceException {
		try {
			aftRewRealDao.insert(aftRewReal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Ipage findForAll(Ipage ipg, AftRewReal aftRewReal) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewRealDao
					.getCount(aftRewReal) });// 初始化分页类
			ipg.setResult(aftRewRealDao.findByList(aftRewReal));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
		}

	public void update(AftRewReal aftRewReal) throws ServiceException {
		try {
			aftRewRealDao.update(aftRewReal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateSts(AftRewReal aftRewReal) throws ServiceException {
		try {
			aftRewRealDao.updateSts(aftRewReal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateDealRest(AftRewReal aftRewReal) throws ServiceException {
		try {
			aftRewRealDao.updateDealRest(aftRewReal);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<AftRewReal> findByList(AftRewReal aftRewReal) throws ServiceException {
		return aftRewRealDao.findByList(aftRewReal);
	}
	
	public List<AftRewReal> findByDateBetween(Map<String, String> paramMap)throws ServiceException{
		return aftRewRealDao.findByDateBetween(paramMap);
	}
	
	public AftRewRealDao getAftRewRealDao() {
		return aftRewRealDao;
	}

	public void setAftRewRealDao(AftRewRealDao aftRewRealDao) {
		this.aftRewRealDao = aftRewRealDao;
	}
}