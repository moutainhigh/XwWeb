package  app.creditapp.sys.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.ParmRewBo;
import app.creditapp.sys.dao.ParmRewDao;
import app.creditapp.sys.entity.ParmRew;
/**
* Title: ParmRewBoImplImpl.java
* Description:
**/
public class ParmRewBoImpl extends BaseService implements ParmRewBo {

	private ParmRewDao parmRewDao;

	public void del(ParmRew parmRew) throws ServiceException {
		try {
			parmRewDao.del(parmRew);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ParmRew parmRew)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) parmRewDao
					.getCount(parmRew) });// 初始化分页类
			parmRew.setStartNumAndEndNum (ipg);
			ipg.setResult(parmRewDao.findByPage(parmRew));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public ParmRew getById(ParmRew parmRew) throws ServiceException {
		try {
			parmRew = parmRewDao.getById(parmRew);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return parmRew;
	}

	public void insert(ParmRew parmRew) throws ServiceException {
		try {
			parmRewDao.insert(parmRew);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ParmRew parmRew) throws ServiceException {
		try {
			parmRewDao.update(parmRew);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public ParmRewDao getParmRewDao() {
		return parmRewDao;
	}

	public void setParmRewDao(ParmRewDao parmRewDao) {
		this.parmRewDao = parmRewDao;
	}
}