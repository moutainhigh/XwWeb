package app.creditapp.corp.bo.impl;
import java.util.HashMap;
import app.base.ServiceException;
import app.creditapp.corp.bo.RptCorpPrdtBo;
import app.creditapp.corp.dao.RptCorpPrdtDao;
import app.creditapp.corp.entity.RptCorpPrdt;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;
import java.util.List;

public class RptCorpPrdtBoImpl implements RptCorpPrdtBo {

	private RptCorpPrdtDao rptCorpPrdtDao;
	
	public void del(RptCorpPrdt rptCorpPrdt) throws ServiceException {
		try {
			rptCorpPrdtDao.delete(rptCorpPrdt);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, RptCorpPrdt rptCorpPrdt)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) rptCorpPrdtDao
					.getCount(rptCorpPrdt) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, rptCorpPrdt);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(rptCorpPrdtDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public RptCorpPrdt getById(RptCorpPrdt rptCorpPrdt) throws ServiceException {
		try {
			rptCorpPrdt = rptCorpPrdtDao.getById(rptCorpPrdt);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptCorpPrdt;
	}

	public String insert(RptCorpPrdt rptCorpPrdt) throws ServiceException {
		String sid = "";
		try {
			sid = (String)rptCorpPrdtDao.insert(rptCorpPrdt);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sid;
	}
	
	public void insertBatch(final List<RptCorpPrdt> rptCorpPrdtList) throws ServiceException {
		try {
			rptCorpPrdtDao.insertBatch(rptCorpPrdtList);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(RptCorpPrdt rptCorpPrdt) throws ServiceException {
		try {
			rptCorpPrdtDao.update(rptCorpPrdt);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<RptCorpPrdt> findByAll(RptCorpPrdt rptCorpPrdt) throws ServiceException {
		List<RptCorpPrdt> rptCorpPrdtlist = null;
		try {
			rptCorpPrdtlist = rptCorpPrdtDao.findByAll(rptCorpPrdt);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptCorpPrdtlist;
	}
	
	public RptCorpPrdtDao getRptCorpPrdtDao() {
		return rptCorpPrdtDao;
	}

	public void setRptCorpPrdtDao(RptCorpPrdtDao rptCorpPrdtDao) {
		this.rptCorpPrdtDao = rptCorpPrdtDao;
	}

}
