package  app.creditapp.ln.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.ln.bo.LnAcctBo;
import app.creditapp.ln.dao.LnAcctDao;
import app.creditapp.ln.entity.LnAcct;
import app.util.toolkit.Ipage;
/**
* Title: LnAcctBoImplImpl.java
* Description:
**/
public class LnAcctBoImpl extends BaseService implements LnAcctBo {

	private LnAcctDao lnAcctDao;

	public void del(LnAcct lnAcct) throws ServiceException {
		try {
			lnAcctDao.del(lnAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//2501接口获取账号是否存在
	public int  getCountFor2501(LnAcct lnAcct) throws ServiceException {
		int count = 0;
		try {
			count = lnAcctDao.getCountFor2501(lnAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

	public Ipage findByPage(Ipage ipg, LnAcct lnAcct)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnAcctDao
					.getCount(lnAcct) });// 初始化分页类
			lnAcct.setStartNumAndEndNum (ipg);
			ipg.setResult(lnAcctDao.findByPage(lnAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	

	public LnAcct getById(LnAcct lnAcct) throws ServiceException {
		try {
			lnAcct = lnAcctDao.getById(lnAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnAcct;
	}

	public void insert(LnAcct lnAcct) throws ServiceException {
		try {
			lnAcctDao.insert(lnAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnAcct lnAcct) throws ServiceException {
		try {
			lnAcctDao.update(lnAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public LnAcctDao getLnAcctDao() {
		return lnAcctDao;
	}

	public void setLnAcctDao(LnAcctDao lnAcctDao) {
		this.lnAcctDao = lnAcctDao;
	}

	@Override
	public Ipage findByPageQuotaForLn(Ipage ipage, LnAcct lnAcct)
			throws ServiceException {
	try {
		ipage.initPageCounts(new Integer[] { (Integer) lnAcctDao
				.getCountQuotaForLn(lnAcct) });// 初始化分页类
		lnAcct.setStartNumAndEndNum(ipage);
		ipage.setResult(lnAcctDao.findByPageQuotaForLn(lnAcct));
	} catch (Exception e) {
		throw new ServiceException(e.getMessage());
	}
	return ipage;
	}
	@Override
	public List<LnAcct> getByIdAndSts(LnAcct lnAcct) throws ServiceException {
		List<LnAcct> listLnAcct = null;
		try {
			listLnAcct = lnAcctDao.getByIdAndSts(lnAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		
		return listLnAcct;
	}
}