package  app.creditapp.wkf.dao;
import java.util.List;

import app.base.DAOException;
import app.creditapp.wkf.entity.WkfApprovalRole;
/**
* Title: WkfApprovalRoleDao.java
* Description:
**/
public interface WkfApprovalRoleDao {

	public WkfApprovalRole getById(WkfApprovalRole wkfApprovalRole) throws DAOException;

	public void del(WkfApprovalRole wkfApprovalRole) throws DAOException;

	public void insert(WkfApprovalRole wkfApprovalRole) throws DAOException;

	public void update(WkfApprovalRole wkfApprovalRole) throws DAOException;
	
	public int getCount(WkfApprovalRole wkfApprovalRole) throws DAOException;
	
	public int getCountPop(WkfApprovalRole wkfApprovalRole) throws DAOException;
	
	public int getCountTwoPop(WkfApprovalRole wkfApprovalRole) throws DAOException;

	public List<WkfApprovalRole > findByPage(WkfApprovalRole wkfApprovalRole) throws DAOException;
	
	public List<WkfApprovalRole > findByPageTwoPop(WkfApprovalRole wkfApprovalRole) throws DAOException;
	
	public List<WkfApprovalRole > findByPagePop(WkfApprovalRole wkfApprovalRole) throws DAOException;

	public List<WkfApprovalRole> getAllList(WkfApprovalRole wkfApprovalRole)throws DAOException;
	
	public List<WkfApprovalRole> findWkfApprovalRole(WkfApprovalRole wkfApprovalRole)throws DAOException;

}