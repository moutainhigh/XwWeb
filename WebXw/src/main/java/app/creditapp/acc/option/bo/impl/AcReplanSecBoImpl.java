package  app.creditapp.acc.option.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcReplanSecBo;
import app.creditapp.acc.option.dao.AcReplanSecDao;
import app.creditapp.acc.option.entity.AcReplanSec;
/**
* Title: AcReplanSecBoImplImpl.java
* Description:
**/
public class AcReplanSecBoImpl extends BaseService implements AcReplanSecBo {

	private AcReplanSecDao acReplanSecDao;

	public void del(AcReplanSec acReplanSec) throws ServiceException {
		try {
			acReplanSecDao.del(acReplanSec);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void delByReplanId(AcReplanSec acReplanSec) throws ServiceException {
		try {
			acReplanSecDao.delByReplanId(acReplanSec);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}


	public Ipage findByPage(Ipage ipg, AcReplanSec acReplanSec)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acReplanSecDao
					.getCount(acReplanSec) });// 初始化分页类
			acReplanSec.setStartNumAndEndNum (ipg);
			ipg.setResult(acReplanSecDao.findByPage(acReplanSec));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public List<AcReplanSec> getListByReplanId(AcReplanSec acReplanSec) throws DAOException {
		List acReplanSecList = null;
		try {
			acReplanSecList = acReplanSecDao.getListByReplanId(acReplanSec);
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		return acReplanSecList;
	}

	public AcReplanSec getById(AcReplanSec acReplanSec) throws ServiceException {
		try {
			acReplanSec = acReplanSecDao.getById(acReplanSec);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acReplanSec;
	}
	
	public AcReplanSec getByIdAndOrderNo(AcReplanSec acReplanSec) throws ServiceException {
		try {
			acReplanSec = acReplanSecDao.getByIdAndOrderNo(acReplanSec);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acReplanSec;
	}

	public void insert(AcReplanSec acReplanSec) throws ServiceException {
		try {
			acReplanSecDao.insert(acReplanSec);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcReplanSec acReplanSec) throws ServiceException {
		try {
			acReplanSecDao.update(acReplanSec);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcReplanSecDao getAcReplanSecDao() {
		return acReplanSecDao;
	}

	public void setAcReplanSecDao(AcReplanSecDao acReplanSecDao) {
		this.acReplanSecDao = acReplanSecDao;
	}
}