package  app.creditapp.proj.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.proj.bo.ProjMangBo;
import app.creditapp.proj.dao.ProjMangDao;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.proj.entity.ProjMang;
/**
* Title: ProjMangBoImplImpl.java
* Description:
**/
public class ProjMangBoImpl extends BaseService implements ProjMangBo {

	private ProjMangDao projMangDao;

	public void del(ProjMang projMang) throws ServiceException {
		try {
			projMangDao.del(projMang);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ProjMang projMang)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projMangDao
					.getCount(projMang) });// 初始化分页类
			projMang.setStartNumAndEndNum (ipg);
			ipg.setResult(projMangDao.findByPage(projMang));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public List<ProjMang> findByPageForManager(ProjMang projMang) throws ServiceException {
		List projMangList = null;
		try {
			projMangList = projMangDao.findByPageForManager(projMang);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projMangList;
	}
	public ProjMang getById(ProjMang projMang) throws ServiceException {
		try {
			projMang = projMangDao.getById(projMang);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projMang;
	}
	public List<ProjMang > getByIdLoginId(ProjMang projMang) throws ServiceException {
		List projMangList = null;
		try {
			projMangList = projMangDao.getByIdLoginId(projMang);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projMangList;
	}
	public void insert(ProjMang projMang) throws ServiceException {
		try {
			/**
			 * 新增项目经理信息的关系ID是序列
			 */
			projMang.setRelId(projMangDao.getKey());
			projMangDao.insert(projMang);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ProjMang projMang) throws ServiceException {
		try {
			projMangDao.update(projMang);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public ProjMangDao getProjMangDao() {
		return projMangDao;
	}

	public void setProjMangDao(ProjMangDao projMangDao) {
		this.projMangDao = projMangDao;
	}
}