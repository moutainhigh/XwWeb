package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysCacheBo;
import app.creditapp.sys.dao.SysCacheDao;
import app.creditapp.sys.entity.SysCache;
/**
* Title: SysCacheBoImplImpl.java
* Description:
**/
public class SysCacheBoImpl extends BaseService implements SysCacheBo {

	private SysCacheDao sysCacheDao;

	public void del(SysCache sysCache) throws ServiceException {
		try {
			sysCacheDao.del(sysCache);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysCache sysCache)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysCacheDao
					.getCount(sysCache) });// 初始化分页类
			sysCache.setStartNumAndEndNum (ipg);
			ipg.setResult(sysCacheDao.findByPage(sysCache));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysCache getById(SysCache sysCache) throws ServiceException {
		try {
			sysCache = sysCacheDao.getById(sysCache);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysCache;
	}

	public void insert(SysCache sysCache) throws ServiceException {
		try {
			sysCacheDao.insert(sysCache);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysCache sysCache) throws ServiceException {
		try {
			sysCacheDao.update(sysCache);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<SysCache> getSysCache() throws ServiceException{
		List<SysCache> sysCacheList = null;
		try {
			sysCacheDao.getSysCache();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysCacheList;
	}

	public SysCacheDao getSysCacheDao() {
		return sysCacheDao;
	}

	public void setSysCacheDao(SysCacheDao sysCacheDao) {
		this.sysCacheDao = sysCacheDao;
	}
}