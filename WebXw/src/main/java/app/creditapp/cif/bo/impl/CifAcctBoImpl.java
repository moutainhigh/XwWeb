package  app.creditapp.cif.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.cif.bo.CifAcctBo;
import app.creditapp.cif.dao.CifAcctDao;
import app.creditapp.cif.entity.CifAcct;
/**
* Title: CifAcctBoImplImpl.java
* Description:
**/
public class CifAcctBoImpl extends BaseService implements CifAcctBo {

	private CifAcctDao cifAcctDao;

	public void del(CifAcct cifAcct) throws ServiceException {
		try {
			cifAcctDao.del(cifAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CifAcct cifAcct)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifAcctDao
					.getCount(cifAcct) });// 初始化分页类
			cifAcct.setStartNumAndEndNum (ipg);
			ipg.setResult(cifAcctDao.findByPage(cifAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CifAcct getById(CifAcct cifAcct) throws ServiceException {
		try {
			cifAcct = cifAcctDao.getById(cifAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifAcct;
	}

	public void insert(CifAcct cifAcct) throws ServiceException {
		try {
			cifAcctDao.insert(cifAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CifAcct cifAcct) throws ServiceException {
		try {
			cifAcctDao.update(cifAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public CifAcctDao getCifAcctDao() {
		return cifAcctDao;
	}

	public void setCifAcctDao(CifAcctDao cifAcctDao) {
		this.cifAcctDao = cifAcctDao;
	}

	@Override
	public Ipage findByPageQuotaForCif(Ipage ipage, CifAcct cifAcct)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) cifAcctDao
					.getCountQuotaForCif(cifAcct) });// 初始化分页类
			cifAcct.setStartNumAndEndNum(ipage);
			ipage.setResult(cifAcctDao.findByPageQuotaForCif(cifAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
}