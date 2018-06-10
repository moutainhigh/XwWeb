package  app.creditapp.sec.bo.impl;

import java.util.HashMap;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sec.bo.UserApptimeBo;
import app.creditapp.sec.dao.UserApptimeDao;
import app.creditapp.sec.entity.UserApptime;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;


/**
* Title: UserApptimeBoImpl.java
* Description:
**/

public class UserApptimeBoImpl extends BaseService implements UserApptimeBo {

	private UserApptimeDao userApptimeDao;

	public void del(long id) throws ServiceException {
		try {
			userApptimeDao.del(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, UserApptime userApptime)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) userApptimeDao
					.getCount(userApptime) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, userApptime);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(userApptimeDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public UserApptime getById(long id) throws ServiceException {
		UserApptime userApptime = null;
		try {
			userApptime = userApptimeDao.getById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return userApptime;
	}

	public void insert(UserApptime userApptime) throws ServiceException {
		try {
			userApptimeDao.insert(userApptime);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(UserApptime userApptime) throws ServiceException {
		try {
			userApptimeDao.update(userApptime);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Ipage allFindByPage(Ipage ipg, UserApptime userApptime)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) userApptimeDao
					.getAllCount(userApptime) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, userApptime);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(userApptimeDao.allFindByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public UserApptimeDao getUserApptimeDao() {
		return userApptimeDao;
	}

	public void setUserApptimeDao(UserApptimeDao userApptimeDao) {
		this.userApptimeDao = userApptimeDao;
	}

}
