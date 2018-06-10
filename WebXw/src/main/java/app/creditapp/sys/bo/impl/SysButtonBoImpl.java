package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.dao.SysRoleButtonDAO;
import app.creditapp.sys.bo.SysButtonBo;
import app.creditapp.sys.dao.SysButtonDao;
import app.creditapp.sys.entity.SysButton;
import app.util.toolkit.Ipage;
/**
* Title: SysButtonBoImplImpl.java
* Description:
**/
public class SysButtonBoImpl extends BaseService implements SysButtonBo {

	private SysButtonDao sysButtonDao;
	private SysRoleButtonDAO sysRoleButtonDAO;

	public void del(SysButton sysButton) throws ServiceException {
		try {
			sysButtonDao.del(sysButton);
			sysRoleButtonDAO.deleteByMenuButtonNo(sysButton.getMenu_no(),sysButton.getButton_no());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysButton sysButton)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysButtonDao
					.getCount(sysButton) });// 初始化分页类
			sysButton.setStartNumAndEndNum (ipg);
			ipg.setResult(sysButtonDao.findByPage(sysButton));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public List<SysButton> findAllByMenu(SysButton sysButton)
		throws ServiceException {
		List<SysButton> list = null;
		try {
			list = sysButtonDao.findAllByMenu(sysButton);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public SysButton getById(SysButton sysButton) throws ServiceException {
		try {
			sysButton = sysButtonDao.getById(sysButton);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysButton;
	}

	public void insert(SysButton sysButton) throws ServiceException {
		try {
			sysButtonDao.insert(sysButton);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysButton sysButton) throws ServiceException {
		try {
			sysButtonDao.update(sysButton);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public SysButtonDao getSysButtonDao() {
		return sysButtonDao;
	}

	public void setSysButtonDao(SysButtonDao sysButtonDao) {
		this.sysButtonDao = sysButtonDao;
	}

	public SysRoleButtonDAO getSysRoleButtonDAO() {
		return sysRoleButtonDAO;
	}

	public void setSysRoleButtonDAO(SysRoleButtonDAO sysRoleButtonDAO) {
		this.sysRoleButtonDAO = sysRoleButtonDAO;
	}
}