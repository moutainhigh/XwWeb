package  app.creditapp.wkf.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.util.toolkit.Ipage;

/**
* Title: WkfApprovalUserBo.java
* Description:
**/
public interface WkfApprovalUserBo {

	public WkfApprovalUser getById(WkfApprovalUser wkfApprovalUser) throws ServiceException;
	
	public WkfApprovalUser getById2(WkfApprovalUser wkfApprovalUser) throws ServiceException;
	
	public void del(WkfApprovalUser wkfApprovalUser) throws ServiceException;

	public void delForRole(WkfApprovalUser wkfApprovalUser) throws ServiceException;

	public void insert(WkfApprovalUser wkfApprovalUser) throws ServiceException;

	public void update(WkfApprovalUser wkfApprovalUser) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WkfApprovalUser wkfApprovalUser) throws ServiceException;

	public void batchInsert(String wkfRoleNoStr,String roleNoStr,String brNoStr) throws ServiceException;
	
	public WkfApprovalUser getByUser(WkfApprovalUser wkfApprovalUser) throws ServiceException;
	
	public int getUserCount(String wkfUserName)throws ServiceException;
	
	public List<String> getWkfApprovalUserList(WkfApprovalUser wkfApprovalUser)throws ServiceException;

	public Ipage findByPageMapPop(Ipage ipage, WkfApprovalUser wkfApprovalUser)throws ServiceException;
	
	public List<WkfApprovalUser> getAllList(WkfApprovalUser wkfApprovalUser)throws ServiceException;
	
	public String getAllRoles(String wkfUserName)throws ServiceException;
	
	public void delByWkfRoleNo(WkfApprovalUser wkfApprovalUser)throws ServiceException;

	public Ipage findApprovalUserByPage(Ipage ipage,WkfApprovalUser wkfApprovalUser)throws ServiceException;
	
	public List<WkfApprovalUser>  getByIdAndBrNo(WkfApprovalUser wkfApprovalUser) throws ServiceException;
	
	/**
	 * 
	 * 功能描述：查询当前节点的会签成员
	 * @param ipage
	 * @param wkfApprovalUser
	 * @return
	 * @throws ServiceException
	 * @修改日志：
	 */
	public Ipage findApproveUserByPage(Ipage ipage,WkfApprovalUser wkfApprovalUser) throws ServiceException;
	
	/**
	 * 根据角色名字查询实体
	 * @param wkfApprovalUser
	 * @return
	 * @throws ServiceException
	 */
	public WkfApprovalUser getByRoleName(WkfApprovalUser wkfApprovalUser) throws ServiceException;

}