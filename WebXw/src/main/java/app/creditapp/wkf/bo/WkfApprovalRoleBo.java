package  app.creditapp.wkf.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.wkf.entity.WkfApprovalRole;

/**
* Title: WkfApprovalRoleBo.java
* Description:
**/
public interface WkfApprovalRoleBo {

	public WkfApprovalRole getById(WkfApprovalRole wkfApprovalRole) throws ServiceException;

	public void del(WkfApprovalRole wkfApprovalRole) throws ServiceException;

	public void insert(WkfApprovalRole wkfApprovalRole) throws ServiceException;

	public void update(WkfApprovalRole wkfApprovalRole) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WkfApprovalRole wkfApprovalRole) throws ServiceException;
	
	public Ipage findByPagePop(Ipage ipg, WkfApprovalRole wkfApprovalRole) throws ServiceException;
	
	public List<WkfApprovalRole> getAllList(WkfApprovalRole wkfApprovalRole) throws ServiceException;

	public void insertOrUpdate(WkfApprovalRole wkfApprovalRole, String members,String saveFlag)throws ServiceException;

	public void delGroup(WkfApprovalRole wkfApprovalRole)throws ServiceException;
	
	public List<WkfApprovalRole>  findWkfApprovalRole(WkfApprovalRole wkfApprovalRole)throws ServiceException;
	

}