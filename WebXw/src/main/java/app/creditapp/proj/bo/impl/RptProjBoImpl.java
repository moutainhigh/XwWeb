package app.creditapp.proj.bo.impl;
import java.util.HashMap;
import app.base.ServiceException;
import app.creditapp.proj.bo.RptProjBo;
import app.creditapp.proj.dao.RptProjDao;
import app.creditapp.proj.entity.RptProj;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;
import java.util.List;

public class RptProjBoImpl implements RptProjBo {

	private RptProjDao rptProjDao;
	
	public void del(RptProj rptProj) throws ServiceException {
		try {
			rptProjDao.delete(rptProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, RptProj rptProj)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) rptProjDao
					.getCount(rptProj) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, rptProj);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(rptProjDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public RptProj getById(RptProj rptProj) throws ServiceException {
		try {
			rptProj = rptProjDao.getById(rptProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptProj;
	}
	public RptProj getDailyById(RptProj rptProj) throws ServiceException {
		try {
			rptProj = rptProjDao.getDailyById(rptProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptProj;
	}
	public String insert(RptProj rptProj) throws ServiceException {
		String sid = "";
		try {
			sid = (String)rptProjDao.insert(rptProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sid;
	}
	
	public void insertBatch(final List<RptProj> rptProjList) throws ServiceException {
		try {
			rptProjDao.insertBatch(rptProjList);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(RptProj rptProj) throws ServiceException {
		try {
			rptProjDao.update(rptProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<RptProj> findByAll(RptProj rptProj) throws ServiceException {
		List<RptProj> rptProjlist = null;
		try {
			rptProjlist = rptProjDao.findByAll(rptProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptProjlist;
	}
	
	public List<RptProj> findByAllNum(RptProj rptProj) throws ServiceException {
		List<RptProj> rptProjlist = null;
		try {
			rptProjlist = rptProjDao.findByAllNum(rptProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptProjlist;
	}
	
	public List<RptProj> getAccountBal(RptProj rptProj) throws ServiceException {
		List<RptProj> rptProjlist = null;
		try {
			rptProjlist = rptProjDao.getAccountBal(rptProj);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptProjlist;
	}	
	public RptProjDao getRptProjDao() {
		return rptProjDao;
	}

	public void setRptProjDao(RptProjDao rptProjDao) {
		this.rptProjDao = rptProjDao;
	}

}
