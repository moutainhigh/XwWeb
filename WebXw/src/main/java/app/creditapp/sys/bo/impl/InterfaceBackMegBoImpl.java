package  app.creditapp.sys.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.InterfaceBackMegBo;
import app.creditapp.sys.dao.InterfaceBackMegDao;
import app.creditapp.sys.entity.InterfaceBackMeg;
/**
* Title: InterfaceBackMegBoImplImpl.java
* Description:
**/
public class InterfaceBackMegBoImpl extends BaseService implements InterfaceBackMegBo {

	private InterfaceBackMegDao interfaceBackMegDao;

	public void del(InterfaceBackMeg interfaceBackMeg) throws ServiceException {
		try {
			interfaceBackMegDao.del(interfaceBackMeg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, InterfaceBackMeg interfaceBackMeg)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) interfaceBackMegDao
					.getCount(interfaceBackMeg) });// 初始化分页类
			interfaceBackMeg.setStartNumAndEndNum (ipg);
			ipg.setResult(interfaceBackMegDao.findByPage(interfaceBackMeg));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public InterfaceBackMeg getById(InterfaceBackMeg interfaceBackMeg) throws ServiceException {
		try {
			interfaceBackMeg = interfaceBackMegDao.getById(interfaceBackMeg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return interfaceBackMeg;
	}

	public void insert(InterfaceBackMeg interfaceBackMeg) throws ServiceException {
		try {
			interfaceBackMegDao.insert(interfaceBackMeg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(InterfaceBackMeg interfaceBackMeg) throws ServiceException {
		try {
			interfaceBackMegDao.update(interfaceBackMeg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public InterfaceBackMegDao getInterfaceBackMegDao() {
		return interfaceBackMegDao;
	}

	public void setInterfaceBackMegDao(InterfaceBackMegDao interfaceBackMegDao) {
		this.interfaceBackMegDao = interfaceBackMegDao;
	}
}