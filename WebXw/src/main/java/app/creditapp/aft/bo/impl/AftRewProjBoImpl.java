package  app.creditapp.aft.bo.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftRewProjBo;
import app.creditapp.aft.dao.AftRewProjDao;
import app.creditapp.aft.entity.AftRewProj;
/**
* Title: AftRewProjBoImplImpl.java
* Description:
**/
public class AftRewProjBoImpl extends BaseService implements AftRewProjBo {

	private AftRewProjDao aftRewProjDao;

	public void del(AftRewProj aftRewProj) throws ServiceException {
		try {
			aftRewProjDao.del(aftRewProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftRewProj aftRewProj)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewProjDao
					.getCount(aftRewProj) });// 初始化分页类
			aftRewProj.setStartNumAndEndNum (ipg);
			ipg.setResult(aftRewProjDao.findByPage(aftRewProj));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public Ipage findForAll(Ipage ipg, AftRewProj aftRewProj)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftRewProjDao
					.getCount(aftRewProj) });// 初始化分页类
			ipg.setResult(aftRewProjDao.findByList(aftRewProj));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftRewProj getById(AftRewProj aftRewProj) throws ServiceException {
		try {
			aftRewProj = aftRewProjDao.getById(aftRewProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftRewProj;
	}

	public void insert(AftRewProj aftRewProj) throws ServiceException {
		try {
			aftRewProjDao.insert(aftRewProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftRewProj aftRewProj) throws ServiceException {
		try {
			aftRewProjDao.update(aftRewProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateSts(AftRewProj aftRewProj) throws ServiceException {
		try {
			aftRewProjDao.updateSts(aftRewProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateDealRest(AftRewProj aftRewProj) throws ServiceException {
		try {
			aftRewProjDao.updateDealRest(aftRewProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public List<AftRewProj> findByList(AftRewProj aftRewProj)
			throws ServiceException {
		return aftRewProjDao.findByList(aftRewProj);
	}
	
	public List<AftRewProj> findByDateBetween(Map<String, String> paramMap){
		return aftRewProjDao.findByDateBetween(paramMap);
	}


	public AftRewProjDao getAftRewProjDao() {
		return aftRewProjDao;
	}

	public void setAftRewProjDao(AftRewProjDao aftRewProjDao) {
		this.aftRewProjDao = aftRewProjDao;
	}

}