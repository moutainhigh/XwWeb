package  app.creditapp.inf.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.inf.bo.WsRepyFineBo;
import app.creditapp.inf.dao.WsRepyFineDao;
import app.creditapp.inf.entity.WsRepyFine;
import app.util.toolkit.Ipage;
/**
* Title: WsRepyFineBoImplImpl.java
* Description:
**/
public class WsRepyFineBoImpl extends BaseService implements WsRepyFineBo {

	private WsRepyFineDao wsRepyFineDao;

	public void del(WsRepyFine wsRepyFine) throws ServiceException {
		try {
			wsRepyFineDao.del(wsRepyFine);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsRepyFine wsRepyFine)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRepyFineDao
					.getCount(wsRepyFine) });// 初始化分页类
			wsRepyFine.setStartNumAndEndNum (ipg);
			ipg.setResult(wsRepyFineDao.findByPage(wsRepyFine));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public WsRepyFine getById(WsRepyFine wsRepyFine) throws ServiceException {
		try {
			wsRepyFine = wsRepyFineDao.getById(wsRepyFine);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsRepyFine;
	}

	public void insert(WsRepyFine wsRepyFine) throws ServiceException {
		try {
			wsRepyFineDao.insert(wsRepyFine);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WsRepyFine wsRepyFine) throws ServiceException {
		try {
			wsRepyFineDao.update(wsRepyFine);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public WsRepyFineDao getWsRepyFineDao() {
		return wsRepyFineDao;
	}

	public void setWsRepyFineDao(WsRepyFineDao wsRepyFineDao) {
		this.wsRepyFineDao = wsRepyFineDao;
	}
}