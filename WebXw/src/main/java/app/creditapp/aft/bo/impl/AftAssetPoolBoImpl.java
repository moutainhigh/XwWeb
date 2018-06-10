package  app.creditapp.aft.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftAssetPoolBo;
import app.creditapp.aft.dao.AftAssetPoolDao;
import app.creditapp.aft.entity.AftAssetPool;
/**
* Title: AftAssetPoolBoImplImpl.java
* Description:
**/
public class AftAssetPoolBoImpl extends BaseService implements AftAssetPoolBo {

	private AftAssetPoolDao aftAssetPoolDao;

	public void del(AftAssetPool aftAssetPool) throws ServiceException {
		try {
			aftAssetPoolDao.del(aftAssetPool);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftAssetPool aftAssetPool)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftAssetPoolDao
					.getCount(aftAssetPool) });// 初始化分页类
			aftAssetPool.setStartNumAndEndNum (ipg);
			ipg.setResult(aftAssetPoolDao.findByPage(aftAssetPool));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftAssetPool getById(AftAssetPool aftAssetPool) throws ServiceException {
		try {
			aftAssetPool = aftAssetPoolDao.getById(aftAssetPool);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftAssetPool;
	}

	public void insert(AftAssetPool aftAssetPool) throws ServiceException {
		try {
			aftAssetPoolDao.insert(aftAssetPool);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftAssetPool aftAssetPool) throws ServiceException {
		try {
			aftAssetPoolDao.update(aftAssetPool);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//更新资金池借据关联数、资金池金额、本金总额
	public void updateTotal(AftAssetPool aftAssetPool) throws ServiceException{
		try {
			aftAssetPoolDao.updateTotal(aftAssetPool);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AftAssetPoolDao getAftAssetPoolDao() {
		return aftAssetPoolDao;
	}

	public void setAftAssetPoolDao(AftAssetPoolDao aftAssetPoolDao) {
		this.aftAssetPoolDao = aftAssetPoolDao;
	}
}