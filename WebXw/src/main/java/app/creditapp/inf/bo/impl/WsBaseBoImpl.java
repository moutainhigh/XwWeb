package  app.creditapp.inf.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.inf.bo.WsBaseBo;
import app.creditapp.inf.dao.WsBaseDao;
import app.creditapp.inf.entity.WsBase;
/**
* Title: WsBaseBoImplImpl.java
* Description:
**/
public class WsBaseBoImpl extends BaseService implements WsBaseBo {


	private WsBaseDao wsBaseDao;
	

	@Override
	public void insertDelBack(WsBase wsBase) throws ServiceException {
		try {
			wsBaseDao.insertDelBack(wsBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void updateDelBack(WsBase wsBase) throws ServiceException {
		try {
			wsBaseDao.updateDelBack(wsBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	public void del(WsBase wsBase) throws ServiceException {
		try {
			wsBaseDao.del(wsBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsBase wsBase)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsBaseDao
					.getCount(wsBase) });// 初始化分页类
			wsBase.setStartNumAndEndNum (ipg);
			ipg.setResult(wsBaseDao.findByPage(wsBase));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public WsBase getById(WsBase wsBase) throws ServiceException {
		try {
			wsBase = wsBaseDao.getById(wsBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsBase;
	}

	public void insert(WsBase wsBase) throws ServiceException {
		try {
			wsBaseDao.insert(wsBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WsBase wsBase) throws ServiceException {
		try {
			wsBaseDao.update(wsBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public WsBaseDao getWsBaseDao() {
		return wsBaseDao;
	}

	public void setWsBaseDao(WsBaseDao wsBaseDao) {
		this.wsBaseDao = wsBaseDao;
	}
}