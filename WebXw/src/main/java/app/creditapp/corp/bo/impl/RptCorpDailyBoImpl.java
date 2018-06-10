package app.creditapp.corp.bo.impl;
import java.util.HashMap;
import java.util.List;

import app.base.ServiceException;
import app.creditapp.corp.bo.RptCorpDailyBo;
import app.creditapp.corp.dao.RptCorpDailyDao;
import app.creditapp.corp.entity.RptCorpDaily;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;

public class RptCorpDailyBoImpl implements RptCorpDailyBo {

	private RptCorpDailyDao rptCorpDailyDao;
	
	public void del(RptCorpDaily rptCorpDaily) throws ServiceException {
		try {
			rptCorpDailyDao.delete(rptCorpDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, RptCorpDaily rptCorpDaily)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) rptCorpDailyDao
					.getCount(rptCorpDaily) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, rptCorpDaily);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(rptCorpDailyDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public RptCorpDaily getById(RptCorpDaily rptCorpDaily) throws ServiceException {
		try {
			rptCorpDaily = rptCorpDailyDao.getById(rptCorpDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptCorpDaily;
	}

	public String insert(RptCorpDaily rptCorpDaily) throws ServiceException {
		String sid = "";
		try {
			sid = (String)rptCorpDailyDao.insert(rptCorpDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sid;
	}
	
	public void insertBatch(final List<RptCorpDaily> rptCorpDailyList) throws ServiceException {
		try {
			rptCorpDailyDao.insertBatch(rptCorpDailyList);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(RptCorpDaily rptCorpDaily) throws ServiceException {
		try {
			rptCorpDailyDao.update(rptCorpDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public RptCorpDailyDao getRptCorpDailyDao() {
		return rptCorpDailyDao;
	}

	public void setRptCorpDailyDao(RptCorpDailyDao rptCorpDailyDao) {
		this.rptCorpDailyDao = rptCorpDailyDao;
	}

	@Override
	public List<RptCorpDaily> getList(RptCorpDaily rptCorpDaily)
			throws ServiceException {
		List<RptCorpDaily> list = null;
		try {
			list = rptCorpDailyDao.getList(rptCorpDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public List<RptCorpDaily> findByAllNum(RptCorpDaily rptCorpDaily) throws ServiceException {
		List<RptCorpDaily> list = null;
		try {
			list = rptCorpDailyDao.findByAllNum(rptCorpDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	public RptCorpDaily findRpt(RptCorpDaily rptCorpDaily) throws ServiceException {
		try {
			rptCorpDaily = rptCorpDailyDao.findRpt(rptCorpDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rptCorpDaily;
	}

}
