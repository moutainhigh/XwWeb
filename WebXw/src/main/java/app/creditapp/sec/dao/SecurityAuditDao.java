package  app.creditapp.sec.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sec.entity.SecurityAudit;


/**
* Title: SecurityAuditDao.java
* Description:
**/

public interface SecurityAuditDao {

	public SecurityAudit getById(String id) throws DAOException;

	public void del(String id) throws DAOException;

	public void insert(SecurityAudit secAudit) throws DAOException;

	public void update(SecurityAudit secAudit) throws DAOException;
	
	public void updateIsUse(SecurityAudit secAudit) throws DAOException;
	
	public int getCount(SecurityAudit secAudit) throws DAOException;

	public List<SecurityAudit> findByPage(Map map) throws DAOException;

	public List<SecurityAudit> getListForSec(SecurityAudit secAudit) throws DAOException;
	
	public List<SecurityAudit> findAuditListByCode(SecurityAudit secAudit) throws DAOException;
}
