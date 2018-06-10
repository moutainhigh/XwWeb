package app.creditapp.acc.option.bo;

import java.util.List;

import accounting.domain.sys.AcComHoliday;
import app.base.ServiceException;
import app.util.User;
import app.util.toolkit.Ipage;

public interface AcComHolidayBo {
	
	public Ipage findByPage(Ipage ipage,AcComHoliday acComHoliday) throws ServiceException;
	
	public AcComHoliday getById(AcComHoliday acComHoliday) throws ServiceException;
	
	public AcComHoliday find(AcComHoliday acComHoliday) throws ServiceException;

	public void update(AcComHoliday acComHoliday) throws ServiceException;
	
	public void insert(AcComHoliday acComHoliday) throws ServiceException;
	
	public void del(AcComHoliday acComHoliday) throws ServiceException;
	
	public List<AcComHoliday> getListByBegDt(String begDt) throws ServiceException;
	
	public void delByBegDt(String begDt) throws ServiceException;
	
	public String findHolidayBySysDate(String sysDate)throws ServiceException;

}
