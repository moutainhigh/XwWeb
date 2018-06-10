package  app.creditapp.aft.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftReliefDtlBo;
import app.creditapp.aft.dao.AftReliefDtlDao;
import app.creditapp.aft.entity.AftReliefDtl;
/**
* Title: AftReliefDtlBoImplImpl.java
* Description:
**/
public class AftReliefDtlBoImpl extends BaseService implements AftReliefDtlBo {

	private AftReliefDtlDao aftReliefDtlDao;

	public void del(AftReliefDtl aftReliefDtl) throws ServiceException {
		try {
			aftReliefDtlDao.del(aftReliefDtl);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftReliefDtl aftReliefDtl)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftReliefDtlDao
					.getCount(aftReliefDtl) });// 初始化分页类
			aftReliefDtl.setStartNumAndEndNum (ipg);
			ipg.setResult(aftReliefDtlDao.findByPage(aftReliefDtl));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftReliefDtl getById(AftReliefDtl aftReliefDtl) throws ServiceException {
		try {
			aftReliefDtl = aftReliefDtlDao.getById(aftReliefDtl);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftReliefDtl;
	}

	public void insert(AftReliefDtl aftReliefDtl) throws ServiceException {
		try {
			aftReliefDtlDao.insert(aftReliefDtl);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftReliefDtl aftReliefDtl) throws ServiceException {
		try {
			aftReliefDtlDao.update(aftReliefDtl);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AftReliefDtlDao getAftReliefDtlDao() {
		return aftReliefDtlDao;
	}

	public void setAftReliefDtlDao(AftReliefDtlDao aftReliefDtlDao) {
		this.aftReliefDtlDao = aftReliefDtlDao;
	}
}