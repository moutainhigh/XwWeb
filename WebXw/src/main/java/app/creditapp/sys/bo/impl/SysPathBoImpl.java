package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysPathBo;
import app.creditapp.sys.dao.SysPathDao;
import app.creditapp.sys.entity.SysPath;
/**
* Title: SysPathBoImplImpl.java
* Description:
**/
public class SysPathBoImpl extends BaseService implements SysPathBo {

	private SysPathDao sysPathDao;

	public void del(SysPath sysPath) throws ServiceException {
		try {
			sysPathDao.del(sysPath);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysPath sysPath)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysPathDao
					.getCount(sysPath) });// 初始化分页类
			sysPath.setStartNumAndEndNum (ipg);
			ipg.setResult(sysPathDao.findByPage(sysPath));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public List<SysPath> findList() throws ServiceException{
		List sysPathlist = null;
		try {
			sysPathlist = sysPathDao.findList();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysPathlist;
	}

	public SysPath getById(SysPath sysPath) throws ServiceException {
		try {
			sysPath = sysPathDao.getById(sysPath);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysPath;
	}

	public void insert(SysPath sysPath) throws ServiceException {
		try {
			sysPathDao.insert(sysPath);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysPath sysPath) throws ServiceException {
		try {
			sysPathDao.update(sysPath);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public SysPathDao getSysPathDao() {
		return sysPathDao;
	}

	public void setSysPathDao(SysPathDao sysPathDao) {
		this.sysPathDao = sysPathDao;
	}
}