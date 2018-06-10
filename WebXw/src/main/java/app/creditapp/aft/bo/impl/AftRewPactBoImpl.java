package  app.creditapp.aft.bo.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftRewPactBo;
import app.creditapp.aft.dao.AftRewPactDao;
import app.creditapp.aft.entity.AftRewPact;
/**
* Title: AftRewPactBoImplImpl.java
* Description:
**/
public class AftRewPactBoImpl extends BaseService implements AftRewPactBo {

	private AftRewPactDao aftRewPactDao;

	public void del(AftRewPact aftRewPact) throws ServiceException {
		try {
			aftRewPactDao.del(aftRewPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftRewPact aftRewPact)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewPactDao
					.getCount(aftRewPact) });// 初始化分页类
			aftRewPact.setStartNumAndEndNum (ipg);
			ipg.setResult(aftRewPactDao.findByPage(aftRewPact));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public Ipage findForAll(Ipage ipg, AftRewPact aftRewPact)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewPactDao
					.getCount(aftRewPact) });// 初始化分页类
			aftRewPact.setStartNumAndEndNum (ipg);
			ipg.setResult(aftRewPactDao.findToList(aftRewPact));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public AftRewPact getById(AftRewPact aftRewPact) throws ServiceException {
		try {
			aftRewPact = aftRewPactDao.getById(aftRewPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftRewPact;
	}

	public AftRewPact getByPactNo(AftRewPact aftRewPact) throws ServiceException {
		try {
			aftRewPact = aftRewPactDao.getByPactNo(aftRewPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftRewPact;
	}
	
	public void insert(AftRewPact aftRewPact) throws ServiceException {
		try {
			aftRewPactDao.insert(aftRewPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//分账失败插入aftRewPact表
	public void insertForSplit(AftRewPact aftRewPact) throws ServiceException {
		try {
			aftRewPactDao.insertForSplit(aftRewPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftRewPact aftRewPact) throws ServiceException {
		try {
			aftRewPactDao.update(aftRewPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateSts(AftRewPact aftRewPact) throws ServiceException {
		try {
			aftRewPactDao.updateSts(aftRewPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateDealRest(AftRewPact aftRewPact) throws ServiceException {
		try {
			aftRewPactDao.updateDealRest(aftRewPact);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public String getRewNo(String RewName) throws ServiceException {
		String rewNo = "";
		try {
			aftRewPactDao.getRewNo(RewName);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rewNo;
	}
	@Override
	public List<AftRewPact> findToList(AftRewPact aftRewPact) throws ServiceException {
		return aftRewPactDao.findToList(aftRewPact);
	}
	
	public List<AftRewPact> findForCollect(AftRewPact aftRewPact) throws ServiceException {
		return aftRewPactDao.findPactCollect(aftRewPact);
	}
	public List<AftRewPact> findForCollectBetween(Map<String, String> paramMap) throws ServiceException {
		return aftRewPactDao.findPactCollectBetween(paramMap);
	}
	
	@Override
	public List<AftRewPact> findByDateBetween(Map<String, String> paramMap)throws ServiceException{
		return aftRewPactDao.findByDateBetween(paramMap);
	}

	public AftRewPactDao getAftRewPactDao() {
		return aftRewPactDao;
	}

	public void setAftRewPactDao(AftRewPactDao aftRewPactDao) {
		this.aftRewPactDao = aftRewPactDao;
	}

	

	
}