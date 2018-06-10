package  app.creditapp.cif.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.cif.bo.CifPersCareerBo;
import app.creditapp.cif.dao.CifPersCareerDao;
import app.creditapp.cif.entity.CifPersCareer;
/**
* Title: CifPersCareerBoImplImpl.java
* Description:
**/
public class CifPersCareerBoImpl extends BaseService implements CifPersCareerBo {

	private CifPersCareerDao cifPersCareerDao;

	public void del(CifPersCareer cifPersCareer) throws ServiceException {
		try {
			cifPersCareerDao.del(cifPersCareer);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CifPersCareer cifPersCareer)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifPersCareerDao
					.getCount(cifPersCareer) });// 初始化分页类
			cifPersCareer.setStartNumAndEndNum (ipg);
			ipg.setResult(cifPersCareerDao.findByPage(cifPersCareer));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CifPersCareer getById(CifPersCareer cifPersCareer) throws ServiceException {
		try {
			cifPersCareer = cifPersCareerDao.getById(cifPersCareer);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifPersCareer;
	}

	public void insert(CifPersCareer cifPersCareer) throws ServiceException {
		try {
			
			/**
			 * 新增客户信息的客户号是序列
			 */
			cifPersCareer.setRecId(cifPersCareerDao.getKey());
			
			cifPersCareerDao.insert(cifPersCareer);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CifPersCareer cifPersCareer) throws ServiceException {
		try {
			cifPersCareerDao.update(cifPersCareer);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public CifPersCareerDao getCifPersCareerDao() {
		return cifPersCareerDao;
	}

	public void setCifPersCareerDao(CifPersCareerDao cifPersCareerDao) {
		this.cifPersCareerDao = cifPersCareerDao;
	}

	@Override
	public Ipage findByPageQuotaForCif(Ipage ipage, CifPersCareer cifPersCareer)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) cifPersCareerDao
					.getCountQuotaForCif(cifPersCareer) });// 初始化分页类
			cifPersCareer.setStartNumAndEndNum(ipage);
			ipage.setResult(cifPersCareerDao.findByPageQuotaForCif(cifPersCareer));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	@Override
	public CifPersCareer findForCif(CifPersCareer cifPersCareer)
			throws ServiceException {
		try {
			cifPersCareer = cifPersCareerDao.findForCif(cifPersCareer);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifPersCareer;
	}
}