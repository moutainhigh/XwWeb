package  app.creditapp.corp.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpTaRelBo;
import app.creditapp.corp.dao.CorpTaRelDao;
import app.creditapp.corp.entity.CorpTaRel;
/**
* Title: CorpTaRelBoImplImpl.java
* Description:
**/
public class CorpTaRelBoImpl extends BaseService implements CorpTaRelBo {

	private CorpTaRelDao corpTaRelDao;

	public void del(CorpTaRel corpTaRel) throws ServiceException {
		try {
			corpTaRelDao.del(corpTaRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CorpTaRel corpTaRel)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) corpTaRelDao
					.getCount(corpTaRel) });// 初始化分页类
			corpTaRel.setStartNumAndEndNum (ipg);
			ipg.setResult(corpTaRelDao.findByPage(corpTaRel));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CorpTaRel getById(CorpTaRel corpTaRel) throws ServiceException {
		try {
			corpTaRel = corpTaRelDao.getById(corpTaRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpTaRel;
	}

	public void insert(CorpTaRel corpTaRel) throws ServiceException {
		try {
			corpTaRelDao.insert(corpTaRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CorpTaRel corpTaRel) throws ServiceException {
		try {
			corpTaRelDao.update(corpTaRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public CorpTaRelDao getCorpTaRelDao() {
		return corpTaRelDao;
	}

	public void setCorpTaRelDao(CorpTaRelDao corpTaRelDao) {
		this.corpTaRelDao = corpTaRelDao;
	}
}