package  app.creditapp.cif.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.cif.bo.CifPersHisBo;
import app.creditapp.cif.dao.CifPersHisDao;
import app.creditapp.cif.entity.CifPersHis;
/**
* Title: CifPersHisBoImplImpl.java
* Description:
**/
public class CifPersHisBoImpl extends BaseService implements CifPersHisBo {

	private CifPersHisDao cifPersHisDao;

	public void del(CifPersHis cifPersHis) throws ServiceException {
		try {
			cifPersHisDao.del(cifPersHis);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CifPersHis cifPersHis)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifPersHisDao
					.getCount(cifPersHis) });// 初始化分页类
			cifPersHis.setStartNumAndEndNum (ipg);
			ipg.setResult(cifPersHisDao.findByPage(cifPersHis));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CifPersHis getById(CifPersHis cifPersHis) throws ServiceException {
		try {
			cifPersHis = cifPersHisDao.getById(cifPersHis);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifPersHis;
	}

	public void insert(CifPersHis cifPersHis) throws ServiceException {
		try {
			cifPersHisDao.insert(cifPersHis);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CifPersHis cifPersHis) throws ServiceException {
		try {
			cifPersHisDao.update(cifPersHis);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPageQuotaForCif(Ipage ipage, CifPersHis cifPersHis)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) cifPersHisDao
					.getCountQuotaForCif(cifPersHis) });// 初始化分页类
			cifPersHis.setStartNumAndEndNum(ipage);
			ipage.setResult(cifPersHisDao.findByPageQuotaForCif(cifPersHis));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
	
	public CifPersHisDao getCifPersHisDao() {
		return cifPersHisDao;
	}

	public void setCifPersHisDao(CifPersHisDao cifPersHisDao) {
		this.cifPersHisDao = cifPersHisDao;
	}
}