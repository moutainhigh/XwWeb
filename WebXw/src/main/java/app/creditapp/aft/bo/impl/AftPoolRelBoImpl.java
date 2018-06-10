package  app.creditapp.aft.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.aft.bo.AftPoolRelBo;
import app.creditapp.aft.dao.AftPoolRelDao;
import app.creditapp.aft.entity.AftPoolRel;
/**
* Title: AftPoolRelBoImplImpl.java
* Description:
**/
public class AftPoolRelBoImpl extends BaseService implements AftPoolRelBo {

	private AftPoolRelDao aftPoolRelDao;

	public void del(AftPoolRel aftPoolRel) throws ServiceException {
		try {
			aftPoolRelDao.del(aftPoolRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//根据资金池id和合同id删除
	public void delect(AftPoolRel aftPoolRel) throws ServiceException {
		try {
			aftPoolRelDao.delect(aftPoolRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	//通过fundNo将ln_due中的数据批量插入aft_pool_rel表中 
	public void insertList(AftPoolRel aftPoolRel) throws ServiceException{
		try {
			aftPoolRelDao.insertList(aftPoolRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AftPoolRel aftPoolRel)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) aftPoolRelDao
					.getCount(aftPoolRel) });// 初始化分页类
			aftPoolRel.setStartNumAndEndNum (ipg);
			ipg.setResult(aftPoolRelDao.findByPage(aftPoolRel));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AftPoolRel getById(AftPoolRel aftPoolRel) throws ServiceException {
		try {
			aftPoolRel = aftPoolRelDao.getById(aftPoolRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return aftPoolRel;
	}

	public void insert(AftPoolRel aftPoolRel) throws ServiceException {
		try {
			aftPoolRelDao.insert(aftPoolRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AftPoolRel aftPoolRel) throws ServiceException {
		try {
			aftPoolRelDao.update(aftPoolRel);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AftPoolRelDao getAftPoolRelDao() {
		return aftPoolRelDao;
	}

	public void setAftPoolRelDao(AftPoolRelDao aftPoolRelDao) {
		this.aftPoolRelDao = aftPoolRelDao;
	}
}