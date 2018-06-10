package app.creditapp.corp.bo.impl;
import java.util.HashMap;
import java.util.List;

import app.base.ServiceException;
import app.creditapp.corp.bo.RptCorpAreaBo;
import app.creditapp.corp.dao.RptCorpAreaDao;
import app.creditapp.corp.entity.RptCorpArea;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;

public class RptCorpAreaBoImpl implements RptCorpAreaBo {

	private RptCorpAreaDao rptCorpAreaDao;
	
	public void del(RptCorpArea rptCorpArea) throws ServiceException {
		try {
			rptCorpAreaDao.delete(rptCorpArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, RptCorpArea rptCorpArea)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) rptCorpAreaDao
					.getCount(rptCorpArea) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, rptCorpArea);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(rptCorpAreaDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public RptCorpArea getById(RptCorpArea rptCorpArea) throws ServiceException {
		try {
			rptCorpArea = rptCorpAreaDao.getById(rptCorpArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptCorpArea;
	}

	public String insert(RptCorpArea rptCorpArea) throws ServiceException {
		String sid = "";
		try {
			sid = (String)rptCorpAreaDao.insert(rptCorpArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sid;
	}
	
	public void insertBatch(final List<RptCorpArea> rptCorpAreaList) throws ServiceException {
		try {
			rptCorpAreaDao.insertBatch(rptCorpAreaList);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(RptCorpArea rptCorpArea) throws ServiceException {
		try {
			rptCorpAreaDao.update(rptCorpArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<RptCorpArea> findByAll(RptCorpArea rptCorpArea) throws ServiceException {
		List<RptCorpArea> rptCorpArealist = null;
		try {
			rptCorpArealist = rptCorpAreaDao.findByAll(rptCorpArea);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptCorpArealist;
	}
	
	public RptCorpAreaDao getRptCorpAreaDao() {
		return rptCorpAreaDao;
	}

	public void setRptCorpAreaDao(RptCorpAreaDao rptCorpAreaDao) {
		this.rptCorpAreaDao = rptCorpAreaDao;
	}

}
