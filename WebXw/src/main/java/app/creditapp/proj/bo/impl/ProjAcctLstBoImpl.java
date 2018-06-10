package  app.creditapp.proj.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.proj.bo.ProjAcctLstBo;
import app.creditapp.proj.dao.ProjAcctLstDao;
import app.creditapp.proj.entity.ProjAcctLst;
/**
* Title: ProjAcctLstBoImplImpl.java
* Description:
**/
public class ProjAcctLstBoImpl extends BaseService implements ProjAcctLstBo {

	private ProjAcctLstDao projAcctLstDao;

	public void del(ProjAcctLst projAcctLst) throws ServiceException {
		try {
			projAcctLstDao.del(projAcctLst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ProjAcctLst projAcctLst)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projAcctLstDao
					.getCount(projAcctLst) });// 初始化分页类
			projAcctLst.setStartNumAndEndNum (ipg);
			ipg.setResult(projAcctLstDao.findByPage(projAcctLst));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public ProjAcctLst getById(ProjAcctLst projAcctLst) throws ServiceException {
		try {
			projAcctLst = projAcctLstDao.getById(projAcctLst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projAcctLst;
	}

	public void insert(ProjAcctLst projAcctLst) throws ServiceException {
		try {
			projAcctLstDao.insert(projAcctLst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ProjAcctLst projAcctLst) throws ServiceException {
		try {
			projAcctLstDao.update(projAcctLst);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public ProjAcctLstDao getProjAcctLstDao() {
		return projAcctLstDao;
	}

	public void setProjAcctLstDao(ProjAcctLstDao projAcctLstDao) {
		this.projAcctLstDao = projAcctLstDao;
	}

	@Override
	public Ipage findByPageQuotaForCorp(Ipage ipage, ProjAcctLst projAcctLst)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) projAcctLstDao
					.getCountQuotaForCorp(projAcctLst) });// 初始化分页类
			projAcctLst.setStartNumAndEndNum(ipage);
			ipage.setResult(projAcctLstDao.findByPageQuotaForCorp(projAcctLst));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
}