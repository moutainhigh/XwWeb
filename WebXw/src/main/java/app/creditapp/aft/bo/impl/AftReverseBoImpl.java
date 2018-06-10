package  app.creditapp.aft.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftReverseBo;
import app.creditapp.aft.dao.AftReverseDao;
import app.creditapp.aft.entity.AftReverse;
/**
* Title: AftReverseBoImplImpl.java
* Description:
**/
public class AftReverseBoImpl extends BaseService implements AftReverseBo {

	private AftReverseDao aftReverseDao;

	public void del(AftReverse aftReverse) throws ServiceException {
		try {
			aftReverseDao.del(aftReverse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftReverse aftReverse)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftReverseDao
					.getCount(aftReverse) });// 初始化分页类
			aftReverse.setStartNumAndEndNum (ipg);
			ipg.setResult(aftReverseDao.findByPage(aftReverse));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftReverse getById(AftReverse aftReverse) throws ServiceException {
		try {
			aftReverse = aftReverseDao.getById(aftReverse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftReverse;
	}
	public AftReverse getByIdForRead(AftReverse aftReverse) throws ServiceException {
		try {
			aftReverse = aftReverseDao.getByIdForRead(aftReverse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftReverse;
	}

	public void insert(AftReverse aftReverse) throws ServiceException {
		try {
			aftReverseDao.insert(aftReverse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftReverse aftReverse) throws ServiceException {
		try {
			aftReverseDao.update(aftReverse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateReveSts(AftReverse aftReverse) throws ServiceException {
		try {
			aftReverseDao.updateReveSts(aftReverse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AftReverseDao getAftReverseDao() {
		return aftReverseDao;
	}

	public void setAftReverseDao(AftReverseDao aftReverseDao) {
		this.aftReverseDao = aftReverseDao;
	}
}