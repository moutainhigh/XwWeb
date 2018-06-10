package  app.creditapp.sys.bo.impl;

import java.util.List;
import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.PrdtBaseBo;
import app.creditapp.sys.dao.PrdtBaseDao;
import app.creditapp.sys.entity.PrdtBase;
/**
* Title: PrdtBaseBoImplImpl.java
* Description:
**/
public class PrdtBaseBoImpl extends BaseService implements PrdtBaseBo {

	private PrdtBaseDao prdtBaseDao;

	public void del(PrdtBase prdtBase) throws ServiceException {
		try {
			prdtBaseDao.del(prdtBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, PrdtBase prdtBase)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) prdtBaseDao
					.getCount(prdtBase) });// 初始化分页类
			prdtBase.setStartNumAndEndNum (ipg);
			ipg.setResult(prdtBaseDao.findByPage(prdtBase));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public PrdtBase getById(PrdtBase prdtBase) throws ServiceException {
		try {
			prdtBase = prdtBaseDao.getById(prdtBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return prdtBase;
	}
	
	public PrdtBase getByPrdtNo(PrdtBase prdtBase) throws ServiceException {
		try {
			prdtBase = prdtBaseDao.getByPrdtNo(prdtBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return prdtBase;
	}

	public PrdtBase getMaxNoByBrNo(PrdtBase prdtBase) throws ServiceException {
		try {
			prdtBase = prdtBaseDao.getMaxNoByBrNo(prdtBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return prdtBase;
	}
	
	public void insert(PrdtBase prdtBase) throws ServiceException {
		try {
			prdtBaseDao.insert(prdtBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void insertForCopy(PrdtBase prdtBase) throws ServiceException {
		try {
			prdtBaseDao.insertForCopy(prdtBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void update(PrdtBase prdtBase) throws ServiceException {
		try {
			prdtBaseDao.update(prdtBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public int getCountPrd(PrdtBase prdtBase) throws ServiceException {
		int count = 0;
		try {
			count = prdtBaseDao.getCountPrd(prdtBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	public PrdtBaseDao getPrdtBaseDao() {
		return prdtBaseDao;
	}

	public void setPrdtBaseDao(PrdtBaseDao prdtBaseDao) {
		this.prdtBaseDao = prdtBaseDao;
	}

	@Override
	public List<PrdtBase> findWkfApprovalRole(PrdtBase prdtBase) {
		// TODO Auto-generated method stub
		return null;
	}
}