package  app.creditapp.sec.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sec.entity.AuditInfoLog;


/**
* Title: AuditInfoLogDao.java
* Description:
**/

public interface AuditInfoLogDao {

	public AuditInfoLog getById(long id) throws DAOException;

	public void del(long id) throws DAOException;

	public void insert(AuditInfoLog auditInfoLog) throws DAOException;

	public void update(AuditInfoLog auditInfoLog) throws DAOException;
	
	public int getCount(AuditInfoLog auditInfoLog) throws DAOException;

	public List<AuditInfoLog > findByPage(Map map) throws DAOException;

}
