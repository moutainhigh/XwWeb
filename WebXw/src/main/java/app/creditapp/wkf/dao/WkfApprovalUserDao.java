package  app.creditapp.wkf.dao;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.wkf.entity.WkfApprovalUser;
/**
* Title: WkfApprovalUserDao.java
* Description:
**/
public interface WkfApprovalUserDao {

	public WkfApprovalUser getById(WkfApprovalUser wkfApprovalUser) throws DAOException;
	
	public WkfApprovalUser getById2(WkfApprovalUser wkfApprovalUser) throws DAOException;

	public void del(WkfApprovalUser wkfApprovalUser) throws DAOException;
	
	public void delForRole(WkfApprovalUser wkfApprovalUser) throws DAOException;

	public void insert(WkfApprovalUser wkfApprovalUser) throws DAOException;

	public void update(WkfApprovalUser wkfApprovalUser) throws DAOException;
	
	public int getCount(WkfApprovalUser wkfApprovalUser) throws DAOException;

	public List<WkfApprovalUser > findByPage(WkfApprovalUser wkfApprovalUser) throws DAOException;

	public String getSeq() throws DAOException;

	public List<WkfApprovalUser> getByIdAndBrNo(WkfApprovalUser wkfApprovalUser) throws DAOException;
	
	public WkfApprovalUser getByUser(WkfApprovalUser wkfApprovalUser) throws DAOException;
	
	public int getUserCount(String wkfUserName) throws DAOException;

	public List<String> getWkfApprovalUserList(WkfApprovalUser wkfApprovalUser)throws DAOException;

	public List<Map<String,Object>> findByPageMapPop(WkfApprovalUser wkfApprovalUser)throws DAOException;

	public List<WkfApprovalUser> getAllList(WkfApprovalUser wkfApprovalUser)throws DAOException;
	public List<WkfApprovalUser> getAllList(String wkf_user_name)throws DAOException;

	public void delByWkfRoleNo(WkfApprovalUser wkfApprovalUser)throws DAOException;

	public Integer getApprovalUserCount(WkfApprovalUser wkfApprovalUser)throws DAOException;

	public List<Map<String,Object>> findApprovalUserByPage(WkfApprovalUser wkfApprovalUser)throws DAOException;
	
	/**
	 * 
	 * 功能描述：查询当前节点的会签成员
	 * @param wkfApprovalUser
	 * @return
	 * @throws DAOException
	 * @修改日志：
	 */
	public List<Map<String, Object>> findApproveUserByPage(WkfApprovalUser wkfApprovalUser)throws DAOException;
	
	/**
	 * 
	 * 功能描述：查询当前节点会签任务可选择人员
	 * @param wkfApprovalUser
	 * @return
	 * @throws DAOException
	 * @修改日志：
	 */
	public Integer getApproveUserCount(WkfApprovalUser wkfApprovalUser)throws DAOException;
	
	/**
	 * 
	 * 功能描述： 根据操作员号，取审批人员类型
	 * @return
	 * @throws DAOException
	 * @修改日志：
	 */
	public Integer getSignType(String op_no) throws DAOException;
	/**
	 * 根据用户名和审批角色名来查询实体
	 * @param wkfApprovalUser
	 * @return
	 * @throws DAOException
	 */
	public WkfApprovalUser getByRoleName(WkfApprovalUser wkfApprovalUser) throws DAOException;

}