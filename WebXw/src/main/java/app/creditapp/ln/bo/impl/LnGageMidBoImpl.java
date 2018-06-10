package  app.creditapp.ln.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.ln.bo.LnGageMidBo;
import app.creditapp.ln.dao.LnGageMidDao;
import app.creditapp.ln.entity.LnGageMid;
/**
* Title: LnGageMidBoImplImpl.java
* Description:
**/
public class LnGageMidBoImpl extends BaseService implements LnGageMidBo {

	private LnGageMidDao lnGageMidDao;

	public void del(LnGageMid lnGageMid) throws ServiceException {
		try {
			lnGageMidDao.del(lnGageMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnGageMid lnGageMid)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnGageMidDao
					.getCount(lnGageMid) });// 初始化分页类
			lnGageMid.setStartNumAndEndNum (ipg);
			ipg.setResult(lnGageMidDao.findByPage(lnGageMid));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public LnGageMid getById(LnGageMid lnGageMid) throws ServiceException {
		try {
			lnGageMid = lnGageMidDao.getById(lnGageMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnGageMid;
	}

	public void insert(LnGageMid lnGageMid) throws ServiceException {
		try {
			lnGageMidDao.insert(lnGageMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnGageMid lnGageMid) throws ServiceException {
		try {
			lnGageMidDao.update(lnGageMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public LnGageMidDao getLnGageMidDao() {
		return lnGageMidDao;
	}

	public void setLnGageMidDao(LnGageMidDao lnGageMidDao) {
		this.lnGageMidDao = lnGageMidDao;
	}

	@Override
	public Ipage findByPageQuotaForLn(Ipage ipage, LnGageMid lnGageMid)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) lnGageMidDao
					.getCountQuotaForLn(lnGageMid) });// 初始化分页类
			lnGageMid.setStartNumAndEndNum(ipage);
			ipage.setResult(lnGageMidDao.findByPageQuotaForLn(lnGageMid));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	@Override
	public List<LnGageMid> getListByLnGageMid(LnGageMid lnGageMid)
			throws ServiceException {
		List<LnGageMid> list = null;
		try {
			list = lnGageMidDao.getListByLnGageMid(lnGageMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
}