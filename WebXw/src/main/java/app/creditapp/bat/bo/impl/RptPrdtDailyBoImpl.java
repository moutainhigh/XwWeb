package app.creditapp.bat.bo.impl;
import app.base.ServiceException;
import app.creditapp.bat.bo.RptPrdtDailyBo;
import app.creditapp.bat.dao.RptPrdtDailyDao;
import app.creditapp.bat.entity.RptPrdtDaily;
import app.creditapp.proj.entity.ProjAcct;

import java.util.List;

public class RptPrdtDailyBoImpl implements RptPrdtDailyBo {

	private RptPrdtDailyDao rptPrdtDailyDao;
	
	@Override
	public List<RptPrdtDaily> getList(RptPrdtDaily rptPrdtDaily)
			throws ServiceException {
		List<RptPrdtDaily> list = null;
		try {
			list = rptPrdtDailyDao.getList(rptPrdtDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	public int getCount(RptPrdtDaily rptPrdtDaily) throws ServiceException{
		int count = 0;
		try {
			count = rptPrdtDailyDao.getCount(rptPrdtDaily);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	public RptPrdtDailyDao getRptPrdtDailyDao() {
		return rptPrdtDailyDao;	
	}

	public void setRptPrdtDailyDao(RptPrdtDailyDao rptPrdtDailyDao) {
		this.rptPrdtDailyDao = rptPrdtDailyDao;
	}

}
