package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.SysOrg;
/**
* Title: SysOrgDao.java
* Description:
**/
public interface SysOrgDao {

	public SysOrg getById(SysOrg sysOrg) throws DAOException;

	public void del(SysOrg sysOrg) throws DAOException;

	public void insert(SysOrg sysOrg) throws DAOException;

	public void update(SysOrg sysOrg) throws DAOException;
	
	public int getCount(SysOrg sysOrg) throws DAOException;

	public List<SysOrg > findByPage(SysOrg sysOrg) throws DAOException;
	
	public List<SysOrg> getBrnoChildren(String orgNo) throws DAOException;


}