package  app.creditapp.sec.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.sec.entity.SecurityAudit;
import app.util.toolkit.Ipage;


/**
* Title: SecurityAuditBo.java
* Description:
**/

public interface SecurityAuditBo {

	public SecurityAudit getById(String id) throws ServiceException;

	public void del(String id) throws ServiceException;

	public void insert(SecurityAudit secAudit) throws ServiceException;

	public void update(SecurityAudit secAudit) throws ServiceException;
	
	public void updateIsUse(SecurityAudit secAudit) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SecurityAudit secAudit) throws ServiceException;

	public List<SecurityAudit> getListForSec(SecurityAudit secAudit) throws ServiceException;
	
	public List<SecurityAudit> findAuditListByCode(String codeType)throws ServiceException;
}
