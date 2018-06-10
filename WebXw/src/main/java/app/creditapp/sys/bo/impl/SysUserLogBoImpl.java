package  app.creditapp.sys.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysUserLogBo;
import app.creditapp.sys.dao.SysUserLogDao;
import app.creditapp.sys.entity.SysUserLog;
/**
* Title: SysUserLogBoImplImpl.java
* Description:
**/
public class SysUserLogBoImpl extends BaseService implements SysUserLogBo {

	private SysUserLogDao sysUserLogDao;

	public void del(SysUserLog sysUserLog) throws ServiceException {
		try {
			sysUserLogDao.del(sysUserLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysUserLog sysUserLog)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysUserLogDao
					.getCount(sysUserLog) });// 初始化分页类
			sysUserLog.setStartNumAndEndNum (ipg);
			ipg.setResult(sysUserLogDao.findByPage(sysUserLog));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysUserLog getById(SysUserLog sysUserLog) throws ServiceException {
		try {
			String userId=sysUserLog.getUserId();
			sysUserLog = sysUserLogDao.getById(sysUserLog);
			if(sysUserLog==null){
				sysUserLog = new SysUserLog();
				sysUserLog.setUserId(userId);
				sysUserLog.setPassError(0);
				sysUserLog.setLoginCnt(0);
				this.insert(sysUserLog);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysUserLog;
	}

	public void insert(SysUserLog sysUserLog) throws ServiceException {
		try {
			sysUserLogDao.insert(sysUserLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysUserLog sysUserLog) throws ServiceException {
		try {
			sysUserLogDao.update(sysUserLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void userLogin(SysUserLog sysUserLog) throws ServiceException {
		try {
			sysUserLogDao.userLogin(sysUserLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void userLogout(SysUserLog sysUserLog) throws ServiceException {
		try {
			sysUserLogDao.userLogout(sysUserLog);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public SysUserLogDao getSysUserLogDao() {
		return sysUserLogDao;
	}

	public void setSysUserLogDao(SysUserLogDao sysUserLogDao) {
		this.sysUserLogDao = sysUserLogDao;
	}
}