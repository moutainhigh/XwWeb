package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.ParmAreaBo;
import app.creditapp.sys.dao.ParmAreaDao;
import app.creditapp.sys.entity.ParmArea;
/**
* Title: ParmAreaBoImplImpl.java
* Description:
**/
public class ParmAreaBoImpl extends BaseService implements ParmAreaBo {

	private ParmAreaDao parmAreaDao;

	public void del(ParmArea parmArea) throws ServiceException {
		try {
			parmAreaDao.del(parmArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ParmArea parmArea)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) parmAreaDao
					.getCount(parmArea) });// 初始化分页类
			parmArea.setStartNumAndEndNum (ipg);
			ipg.setResult(parmAreaDao.findByPage(parmArea));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public ParmArea getById(ParmArea parmArea) throws ServiceException {
		try {
			parmArea = parmAreaDao.getById(parmArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return parmArea;
	}

	public void insert(ParmArea parmArea) throws ServiceException {
		try {
			parmAreaDao.insert(parmArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ParmArea parmArea) throws ServiceException {
		try {
			parmAreaDao.update(parmArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public ParmArea getTreeTop() throws ServiceException {
		ParmArea parmArea = new ParmArea();
		try{
			parmArea = parmAreaDao.getTreeTop();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return parmArea;
	}
	
	public List<ParmArea> getChildren(String areaLev)throws ServiceException {
		List<ParmArea> parmAreaList = null;
		try{
			parmAreaList = parmAreaDao.getChildren(areaLev);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
		return parmAreaList;
	}
	public ParmArea getByDeptid(ParmArea parmArea)throws ServiceException {
		try{
			parmArea = parmAreaDao.getByDeptid(parmArea);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return parmArea;
	}
	public ParmAreaDao getParmAreaDao() {
		return parmAreaDao;
	}

	public void setParmAreaDao(ParmAreaDao parmAreaDao) {
		this.parmAreaDao = parmAreaDao;
	}
}