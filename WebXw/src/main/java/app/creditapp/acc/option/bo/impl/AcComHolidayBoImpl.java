package app.creditapp.acc.option.bo.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import accounting.domain.sys.AcComHoliday;
import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.option.bo.AcComHolidayBo;
import app.creditapp.acc.option.dao.AcComHolidayDao;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;

public class AcComHolidayBoImpl extends BaseService implements AcComHolidayBo {

	private AcComHolidayDao acComHolidayDao ;
	
	public AcComHolidayDao getAcComHolidayDao() {
		return acComHolidayDao;
	}

	public void setAcComHolidayDao(AcComHolidayDao acComHolidayDao) {
		this.acComHolidayDao = acComHolidayDao;
	}

	public void del(AcComHoliday acComHoliday) throws ServiceException {
		try {
			if (acComHoliday != null) {
				acComHolidayDao.del(acComHoliday);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcComHoliday find(AcComHoliday acComHoliday) throws ServiceException {
		try {
			if (acComHoliday != null) {
				acComHoliday = acComHolidayDao.find(acComHoliday);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acComHoliday;	
	}

	public Ipage findByPage(Ipage ipage, AcComHoliday acComHoliday)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[]{(Integer)acComHolidayDao.getCount(acComHoliday)});//初始化分页类 
		      HashMap<String,Object> map = (HashMap<String,Object>)IbatisUtils.getEntityPropertyMap(ipage, acComHoliday); 
		      map.put("startNum", ipage.getStartRow());
		      map.put("endNum", ipage.getEndNum());
		      ipage.setResult(acComHolidayDao.findByPage(map)); 
		    } catch (Exception e) {
		      throw new ServiceException(e.getMessage());
		    }
		return ipage; 
	}

	public AcComHoliday getById(AcComHoliday acComHoliday)
			throws ServiceException {
		try {
			if (acComHoliday != null) {
				acComHoliday = acComHolidayDao.getById(acComHoliday);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acComHoliday;
	}


	public void insert(AcComHoliday acComHoliday) throws ServiceException {
		try {
				acComHolidayDao.insert(acComHoliday);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	public void update(AcComHoliday acComHoliday) throws ServiceException {
		try {
				acComHolidayDao.update(acComHoliday);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	
	public void delByBegDt(String begDt) throws ServiceException {
		try {
			acComHolidayDao.delByBegDt(begDt);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<AcComHoliday> getListByBegDt(String begDt) throws ServiceException {
		List<AcComHoliday> acComHolidayList = null;
		try {
			acComHolidayList = acComHolidayDao.getListByBegDt(begDt);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acComHolidayList;
	}
	/**
	 * @作者 wangxingkai
	 * @日期 2016-7-6
	 * @描述 生成自定义节假日json串
	 */
	@Override
	public String findHolidayBySysDate(String sysDate) {
		List<AcComHoliday> acComHolidayList = null;
		String json = "";
		try {
			acComHolidayList = acComHolidayDao.findHolidayBySysDate(sysDate);
			StringBuilder holidayBuilder = new StringBuilder();
			DecimalFormat df = new DecimalFormat("0000");
			holidayBuilder.append("{");
			int listsize = acComHolidayList.size();
			for(int i=0;i < listsize;i++){
				String begDt = acComHolidayList.get(i).getBegDt();
				String endDt = acComHolidayList.get(i).getEndDt();
				String strBeg = begDt.substring(4, 8);
				String strEnd = endDt.substring(4, 8);
				Integer holidayBeg = Integer.parseInt(strBeg);
				Integer holidayEnd = Integer.parseInt(strEnd);
				int holidayDays = holidayEnd - holidayBeg;
				for(int j=0;j<=holidayDays;j++){
					holidayBuilder.append("'"+df.format(holidayBeg+j)+"'").append(":'h,自定义'").append(",");
				}
				holidayBuilder.append("'"+df.format(holidayEnd+1)+"'").append(":'w,自定义'").append(",");
			}
			if (listsize>0){
				holidayBuilder.deleteCharAt(holidayBuilder.lastIndexOf(","));
				holidayBuilder.append("}");
				json = holidayBuilder.toString();
			}
		} catch (Exception e){
			throw new ServiceException(e.getMessage());
		}
		return json;
	}
}
