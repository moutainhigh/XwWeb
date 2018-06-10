package  app.creditapp.ln.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.ln.bo.LnApprIdeaBo;
import app.creditapp.ln.dao.LnApprIdeaDao;
import app.creditapp.ln.entity.LnApprIdea;
import app.util.toolkit.Ipage;
/**
* Title: LnApprIdeaBoImplImpl.java
* Description:
**/
public class LnApprIdeaBoImpl extends BaseService implements LnApprIdeaBo {

	private LnApprIdeaDao lnApprIdeaDao;

	public void del(LnApprIdea lnApprIdea) throws ServiceException {
		try {
			lnApprIdeaDao.del(lnApprIdea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnApprIdea lnApprIdea)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnApprIdeaDao
					.getCount(lnApprIdea) });// 初始化分页类
			lnApprIdea.setStartNumAndEndNum (ipg);
			ipg.setResult(lnApprIdeaDao.findByPage(lnApprIdea));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public Ipage findByPageForRead(Ipage ipg, LnApprIdea lnApprIdea)
	throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnApprIdeaDao
					.getCountForRead(lnApprIdea) });// 初始化分页类
			lnApprIdea.setStartNumAndEndNum (ipg);
			ipg.setResult(lnApprIdeaDao.findByPageForRead(lnApprIdea));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public List<LnApprIdea> findByPageForAppId(LnApprIdea lnApprIdea) throws ServiceException {
		List<LnApprIdea>  lnApprIdeaList;
		try {
			lnApprIdeaList =  lnApprIdeaDao.findByPageForAppId(lnApprIdea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnApprIdeaList;
	}
	
	public LnApprIdea getById(LnApprIdea lnApprIdea) throws ServiceException {
		try {
			lnApprIdea = lnApprIdeaDao.getById(lnApprIdea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnApprIdea;
	}

	public void insert(LnApprIdea lnApprIdea) throws ServiceException {
		try {
			lnApprIdeaDao.insert(lnApprIdea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnApprIdea lnApprIdea) throws ServiceException {
		try {
			lnApprIdeaDao.update(lnApprIdea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public LnApprIdeaDao getLnApprIdeaDao() {
		return lnApprIdeaDao;
	}

	public void setLnApprIdeaDao(LnApprIdeaDao lnApprIdeaDao) {
		this.lnApprIdeaDao = lnApprIdeaDao;
	}
}