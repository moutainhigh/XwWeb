package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.ParmKeyBo;
import app.creditapp.sys.dao.ParmKeyDao;
import app.creditapp.sys.entity.ParmKey;
/**
* Title: ParmKeyBoImplImpl.java
* Description:
**/
public class ParmKeyBoImpl extends BaseService implements ParmKeyBo {

	private ParmKeyDao ParmKeyDao;

	public void del(ParmKey ParmKey) throws ServiceException {
		try {
			ParmKeyDao.del(ParmKey);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ParmKey ParmKey)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) ParmKeyDao
					.getCount(ParmKey) });// 初始化分页类
			ParmKey.setStartNumAndEndNum (ipg);
			ipg.setResult(ParmKeyDao.findByPage(ParmKey));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public ParmKey getById(ParmKey ParmKey) throws ServiceException {
		try {
			ParmKey = ParmKeyDao.getById(ParmKey);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ParmKey;
	}

	public void insert(ParmKey ParmKey) throws ServiceException {
		try {
			ParmKeyDao.insert(ParmKey);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ParmKey ParmKey) throws ServiceException {
		try {
			ParmKeyDao.update(ParmKey);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public ParmKeyDao getParmKeyDao() {
		return ParmKeyDao;
	}

	public void setParmKeyDao(ParmKeyDao ParmKeyDao) {
		this.ParmKeyDao = ParmKeyDao;
	}
}