package  app.creditapp.acc.chg.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.chg.bo.AcRepydayChgaBo;
import app.creditapp.acc.chg.dao.AcRepydayChgaDao;
import app.creditapp.acc.chg.entity.AcRepydayChga;
/**
* Title: AcRepydayChgaBoImplImpl.java
* Description:
**/
public class AcRepydayChgaBoImpl extends BaseService implements AcRepydayChgaBo {

	private AcRepydayChgaDao acRepydayChgaDao;

	public void del(AcRepydayChga acRepydayChga) throws ServiceException {
		try {
			acRepydayChgaDao.del(acRepydayChga);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcRepydayChga acRepydayChga)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acRepydayChgaDao
					.getCount(acRepydayChga) });// 初始化分页类
			acRepydayChga.setStartNumAndEndNum (ipg);
			ipg.setResult(acRepydayChgaDao.findByPage(acRepydayChga));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcRepydayChga getById(AcRepydayChga acRepydayChga) throws ServiceException {
		try {
			acRepydayChga = acRepydayChgaDao.getById(acRepydayChga);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acRepydayChga;
	}

	public void insert(AcRepydayChga acRepydayChga) throws ServiceException {
		try {
			acRepydayChgaDao.insert(acRepydayChga);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcRepydayChga acRepydayChga) throws ServiceException {
		try {
			acRepydayChgaDao.update(acRepydayChga);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcRepydayChgaDao getAcRepydayChgaDao() {
		return acRepydayChgaDao;
	}

	public void setAcRepydayChgaDao(AcRepydayChgaDao acRepydayChgaDao) {
		this.acRepydayChgaDao = acRepydayChgaDao;
	}
}