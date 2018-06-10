package app.creditapp.acc.option.dao;

import java.util.List;
import java.util.Map;

import accounting.domain.sys.AcComHoliday;
import app.base.DAOException;

public interface AcComHolidayDao {

	public AcComHoliday find(AcComHoliday acComHoliday) throws DAOException;
	
	public List<AcComHoliday> findByPage(Map map) throws DAOException;
	
	public AcComHoliday getById(AcComHoliday acComHoliday) throws DAOException;
	
	public AcComHoliday getByDay(String day) throws DAOException;
	
	public void insert(AcComHoliday acComHoliday) throws DAOException;

	public void update(AcComHoliday acComHoliday) throws DAOException;
	
	public void del(AcComHoliday acComHoliday) throws DAOException;
	
	public int getCount(AcComHoliday acComHoliday) throws DAOException;
	
	public void delByBegDt(String begDt) throws DAOException; 
	
	public List<AcComHoliday > getListByBegDt(String begDt) throws DAOException;
	
	public List<AcComHoliday> findHolidayBySysDate(String sysDate) throws DAOException;
	
}
