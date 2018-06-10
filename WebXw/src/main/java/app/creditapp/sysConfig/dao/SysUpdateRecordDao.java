package  app.creditapp.sysConfig.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sysConfig.entity.SysUpdateRecord;


/**
* Title: TableUpdateRecordDao.java
* Description:
**/

public interface SysUpdateRecordDao {

	public SysUpdateRecord getById(String id) throws DAOException;

	public void del(String id) throws DAOException;

	public void insert(SysUpdateRecord sysUpdateRecord) throws DAOException;

	public void update(SysUpdateRecord sysUpdateRecord) throws DAOException;
	
	public int getCount(SysUpdateRecord sysUpdateRecord) throws DAOException;

	public List<SysUpdateRecord> findByPage(Map map) throws DAOException;

}
