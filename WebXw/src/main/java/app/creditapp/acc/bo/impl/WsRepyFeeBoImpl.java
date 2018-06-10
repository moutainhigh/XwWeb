package  app.creditapp.acc.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.bo.WsRepyFeeBo;
import app.creditapp.acc.dao.WsRepyFeeDao;
import app.creditapp.acc.entity.WsRepyFee;
/**
* Title: WsRepyFeeBoImplImpl.java
* Description:
**/
public class WsRepyFeeBoImpl extends BaseService implements WsRepyFeeBo {

	private WsRepyFeeDao wsRepyFeeDao;

	public void del(WsRepyFee wsRepyFee) throws ServiceException {
		try {
			wsRepyFeeDao.del(wsRepyFee);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WsRepyFee wsRepyFee)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wsRepyFeeDao
					.getCount(wsRepyFee) });// 初始化分页类
			wsRepyFee.setStartNumAndEndNum (ipg);
			ipg.setResult(wsRepyFeeDao.findByPage(wsRepyFee));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

//	public WsRepyFee getById(WsRepyFee wsRepyFee) throws ServiceException {
//		try {
//			wsRepyFee = wsRepyFeeDao.getById(wsRepyFee);
//		} catch (Exception e) {
//			throw new ServiceException(e.getMessage());
//		}
//		return wsRepyFee;
//	}

	public void insert(WsRepyFee wsRepyFee) throws ServiceException {
		try {
			wsRepyFeeDao.insert(wsRepyFee);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WsRepyFee wsRepyFee) throws ServiceException {
		try {
			wsRepyFeeDao.update(wsRepyFee);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public WsRepyFeeDao getWsRepyFeeDao() {
		return wsRepyFeeDao;
	}

	public void setWsRepyFeeDao(WsRepyFeeDao wsRepyFeeDao) {
		this.wsRepyFeeDao = wsRepyFeeDao;
	}
}