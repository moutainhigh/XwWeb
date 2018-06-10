package  app.creditapp.acc.option.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.option.bo.AcReplanParmBo;
import app.creditapp.acc.option.dao.AcReplanParmDao;
import app.creditapp.acc.option.dao.AcReplanSecDao;
import app.creditapp.acc.option.entity.AcReplanParm;
import app.creditapp.acc.option.entity.AcReplanSec;
import app.util.toolkit.Ipage;
/**
* Title: AcReplanParmBoImplImpl.java
* Description:
**/
public class AcReplanParmBoImpl extends BaseService implements AcReplanParmBo {

	private AcReplanParmDao acReplanParmDao;
	private AcReplanSecDao acReplanSecDao;


	public void del(AcReplanParm acReplanParm) throws ServiceException {
		try {
			acReplanParmDao.del(acReplanParm);
			AcReplanSec acReplanSec = new AcReplanSec();
			acReplanSec.setReplanId(acReplanParm.getReplanId());
			acReplanSecDao.delByReplanId(acReplanSec);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcReplanParm acReplanParm)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acReplanParmDao
					.getCount(acReplanParm) });// 初始化分页类
			acReplanParm.setStartNumAndEndNum (ipg);
			ipg.setResult(acReplanParmDao.findByPage(acReplanParm));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcReplanParm getById(AcReplanParm acReplanParm) throws ServiceException {
		try {
			acReplanParm = acReplanParmDao.getById(acReplanParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acReplanParm;
	}

	public void insert(AcReplanParm acReplanParm) throws ServiceException {
		try {
			acReplanParmDao.insert(acReplanParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcReplanParm acReplanParm) throws ServiceException {
		try {
			acReplanParmDao.update(acReplanParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateReplanSts(AcReplanParm acReplanParm) throws ServiceException {
		try {
			acReplanParmDao.updateReplanSts(acReplanParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcReplanParmDao getAcReplanParmDao() {
		return acReplanParmDao;
	}

	public void setAcReplanParmDao(AcReplanParmDao acReplanParmDao) {
		this.acReplanParmDao = acReplanParmDao;
	}

	public AcReplanSecDao getAcReplanSecDao() {
		return acReplanSecDao;
	}

	public void setAcReplanSecDao(AcReplanSecDao acReplanSecDao) {
		this.acReplanSecDao = acReplanSecDao;
	}
}