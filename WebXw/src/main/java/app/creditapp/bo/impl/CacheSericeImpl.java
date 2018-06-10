
package app.creditapp.bo.impl;

import java.util.ArrayList;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.bo.CacheService;
import app.creditapp.dao.CacheDAO;
import app.creditapp.sys.bo.SysCacheBo;
import app.creditapp.sys.bo.SysGlobalBo;
import app.creditapp.sys.entity.SysCache;
import app.util.AppConstant;

public class CacheSericeImpl extends BaseService implements CacheService {

	//◊¢»ÎCifBaseDaoImpl¿‡
	private CacheDAO cacheDao;
	private SysGlobalBo sysGlobalBo;
	private SysCacheBo sysCacheBo;
	
	public List<Object> findByCondition(Object object) throws ServiceException {
		List<Object> list = null;
		try {
			list = cacheDao.findByCondition(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public List<String> findKeyByCondition()
			throws ServiceException {
		List<String> list = null;
		try {
			list = cacheDao.findKeyByCondition();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	
	public List<Object> getCacheValue(String cache_name, String cache_key) throws ServiceException {
		List<Object> list = new ArrayList<Object>();
		try {
			if (AppConstant.CACHE_DICT.equals(cache_name)) {
				list = cacheDao.findByCondition(cache_key);
			} else if (AppConstant.CACHE_SECU.equals(cache_name)) {
				
			} else if (AppConstant.CACHE_TBLOG.equals(cache_name)) {
				
			} else if (AppConstant.CACHE_BUTTON.equals(cache_name)) {
				
			} else if (AppConstant.CACHE_SYSSTS.equals(cache_name)) {
				list.add(sysGlobalBo.getSysGlobal().getSys_sts());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public List<String> getCacheKey(String cache_name) throws ServiceException {
		List<String> list = new ArrayList<String>();
		try {
			if (AppConstant.CACHE_DICT.equals(cache_name)) {
				list = cacheDao.findKeyByCondition();
			} else if (AppConstant.CACHE_SECU.equals(cache_name)) {
				
			} else if (AppConstant.CACHE_TBLOG.equals(cache_name)) {
				
			} else if (AppConstant.CACHE_BUTTON.equals(cache_name)) {
				
			} else if (AppConstant.CACHE_SYSSTS.equals(cache_name)) {
				list.add(AppConstant.SYS_STS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	public List<SysCache> getSysCache() throws ServiceException {
		return sysCacheBo.getSysCache();
	}
	
	public CacheDAO getCacheDao() {
		return cacheDao;
	}

	public void setCacheDao(CacheDAO cacheDao) {
		this.cacheDao = cacheDao;
	}

	public SysGlobalBo getSysGlobalBo() {
		return sysGlobalBo;
	}

	public void setSysGlobalBo(SysGlobalBo sysGlobalBo) {
		this.sysGlobalBo = sysGlobalBo;
	}

	public SysCacheBo getSysCacheBo() {
		return sysCacheBo;
	}

	public void setSysCacheBo(SysCacheBo sysCacheBo) {
		this.sysCacheBo = sysCacheBo;
	}
	
}
