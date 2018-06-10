package  app.creditapp.aft.bo.impl;

import java.util.Map;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftAssetRelBo;
import app.creditapp.aft.dao.AftAssetRelDao;
import app.creditapp.aft.entity.AftAssetRel;
/**
* Title: AftAssetRelBoImplImpl.java
* Description:
**/
public class AftAssetRelBoImpl extends BaseService implements AftAssetRelBo {

	private AftAssetRelDao aftAssetRelDao;

	public void del(AftAssetRel aftAssetRel) throws ServiceException {
		try {
			aftAssetRelDao.del(aftAssetRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftAssetRel aftAssetRel)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftAssetRelDao
					.getCount(aftAssetRel) });// 初始化分页类
			aftAssetRel.setStartNumAndEndNum (ipg);
			ipg.setResult(aftAssetRelDao.findByPage(aftAssetRel));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftAssetRel getById(AftAssetRel aftAssetRel) throws ServiceException {
		try {
			aftAssetRel = aftAssetRelDao.getById(aftAssetRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftAssetRel;
	}

	public void insert(AftAssetRel aftAssetRel) throws ServiceException {
		try {
			aftAssetRelDao.insert(aftAssetRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftAssetRel aftAssetRel) throws ServiceException {
		try {
			aftAssetRelDao.update(aftAssetRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void updateValue(Map<?, ?> map) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			aftAssetRelDao.updateValue(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public double getTotalAmt(String pactNo,String recId)throws ServiceException{
		double totalAmt=0.00;
		try {
			 totalAmt = aftAssetRelDao.getTotalAmt(pactNo,recId);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalAmt;
	}
	public AftAssetRelDao getAftAssetRelDao() {
		return aftAssetRelDao;
	}

	public void setAftAssetRelDao(AftAssetRelDao aftAssetRelDao) {
		this.aftAssetRelDao = aftAssetRelDao;
	}
}