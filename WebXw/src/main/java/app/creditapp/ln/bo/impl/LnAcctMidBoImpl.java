package  app.creditapp.ln.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.ln.bo.LnAcctMidBo;
import app.creditapp.ln.dao.LnAcctMidDao;
import app.creditapp.ln.entity.LnAcctMid;
import app.creditapp.ln.entity.LnApplyMid;
/**
* Title: LnAcctMidBoImplImpl.java
* Description:
**/
public class LnAcctMidBoImpl extends BaseService implements LnAcctMidBo {

	private LnAcctMidDao lnAcctMidDao;

	public void del(LnAcctMid lnAcctMid) throws ServiceException {
		try {
			lnAcctMidDao.del(lnAcctMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnAcctMid lnAcctMid)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnAcctMidDao
					.getCount(lnAcctMid) });// 初始化分页类
			lnAcctMid.setStartNumAndEndNum (ipg);
			ipg.setResult(lnAcctMidDao.findByPage(lnAcctMid));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public LnAcctMid getById(LnAcctMid lnAcctMid) throws ServiceException {
		try {
			lnAcctMid = lnAcctMidDao.getById(lnAcctMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnAcctMid;
	}

	public void insert(LnAcctMid lnAcctMid) throws ServiceException {
		try {
			lnAcctMidDao.insert(lnAcctMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnAcctMid lnAcctMid) throws ServiceException {
		try {
			lnAcctMidDao.update(lnAcctMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public LnAcctMidDao getLnAcctMidDao() {
		return lnAcctMidDao;
	}

	public void setLnAcctMidDao(LnAcctMidDao lnAcctMidDao) {
		this.lnAcctMidDao = lnAcctMidDao;
	}

	@Override
	public Ipage findByPageQuotaForLn(Ipage ipage, LnAcctMid lnAcctMid)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) lnAcctMidDao
					.getCountQuotaForLn(lnAcctMid) });// 初始化分页类
			lnAcctMid.setStartNumAndEndNum(ipage);
			ipage.setResult(lnAcctMidDao.findByPageQuotaForLn(lnAcctMid));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	@Override
	public List<LnAcctMid> getListByLnAcctMid(LnAcctMid lnAcctMid)
			throws ServiceException {
		List<LnAcctMid> lnAcctMidList = null;
		try {
			lnAcctMidList = lnAcctMidDao.getListByLnAcctMid(lnAcctMid);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnAcctMidList;
	}
}