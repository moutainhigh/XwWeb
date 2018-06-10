package  app.creditapp.cif.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.cif.bo.CifBlackBo;
import app.creditapp.cif.dao.CifBlackDao;
import app.creditapp.cif.entity.CifBlack;
import app.creditapp.pact.entity.LnPact;
/**
* Title: CifBlackBoImplImpl.java
* Description:
**/
public class CifBlackBoImpl extends BaseService implements CifBlackBo {

	private CifBlackDao cifBlackDao;

	public void del(CifBlack cifBlack) throws ServiceException {
		try {
			cifBlackDao.del(cifBlack);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CifBlack cifBlack)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifBlackDao
					.getCount(cifBlack) });// 初始化分页类
			cifBlack.setStartNumAndEndNum (ipg);
			ipg.setResult(cifBlackDao.findByPage(cifBlack));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public Ipage findByPageForTask(Ipage ipg, CifBlack cifBlack)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifBlackDao
					.getCountForTask(cifBlack) });// 初始化分页类
			cifBlack.setStartNumAndEndNum(ipg);
			ipg.setResult(cifBlackDao.findByPageForTask(cifBlack));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public Ipage findByPageForAppr(Ipage ipg, CifBlack cifBlack)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifBlackDao
					.getCountForAppr(cifBlack) });// 初始化分页类
			cifBlack.setStartNumAndEndNum(ipg);
			ipg.setResult(cifBlackDao.findByPageForAppr(cifBlack));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public CifBlack getById(CifBlack cifBlack) throws ServiceException {
		try {
			cifBlack = cifBlackDao.getById(cifBlack);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifBlack;
	}

	public void insert(CifBlack cifBlack) throws ServiceException {
		try {
			/**
			 * 新增黑名单信息的ID号是序列
			 */
			cifBlack.setBlkId(cifBlackDao.getKey());
			cifBlackDao.insert(cifBlack);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CifBlack cifBlack) throws ServiceException {
		try {
			cifBlackDao.update(cifBlack);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public CifBlackDao getCifBlackDao() {
		return cifBlackDao;
	}

	public void setCifBlackDao(CifBlackDao cifBlackDao) {
		this.cifBlackDao = cifBlackDao;
	}
}