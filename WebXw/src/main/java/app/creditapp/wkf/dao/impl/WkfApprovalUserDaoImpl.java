package  app.creditapp.wkf.dao.impl;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.wkf.dao.WkfApprovalUserDao;
import app.creditapp.wkf.entity.WkfApprovalUser;
/**
* Title: WkfApprovalUserDaoImpl.java
* Description:
**/
public class WkfApprovalUserDaoImpl extends BaseIbatisDao implements WkfApprovalUserDao {

	public void del(WkfApprovalUser wkfApprovalUser) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WkfApprovalUser.del", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public void delForRole(WkfApprovalUser wkfApprovalUser) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WkfApprovalUser.delForRole", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}	
	public void delByWkfRoleNo(WkfApprovalUser wkfApprovalUser)throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WkfApprovalUser.delByWkfRoleNo", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<WkfApprovalUser> findByPage(WkfApprovalUser wkfApprovalUser) throws DAOException {
		List<WkfApprovalUser> wkfApprovalUserList = null;
		try {
			wkfApprovalUserList = getSqlMapClientTemplate().queryForList("WkfApprovalUser.findByPage", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUserList;
	}
	public List<Map<String, Object>> findApprovalUserByPage(WkfApprovalUser wkfApprovalUser)throws DAOException {
		List<Map<String, Object>> wkfApprovalUserList = null;
		try {
			wkfApprovalUserList = getSqlMapClientTemplate().queryForList("WkfApprovalUser.findApprovalUserByPage", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUserList;
	}
	/**
	 * 
	 * 功能描述：查询当前节点的会签成员
	 * @param wkfApprovalUser
	 * @return
	 * @修改日志：
	 */
	public List<Map<String, Object>> findApproveUserByPage(WkfApprovalUser wkfApprovalUser)throws DAOException {
		List<Map<String, Object>> wkfApprovalUserList = null;
		try {
			wkfApprovalUserList = getSqlMapClientTemplate().queryForList("WkfApprovalUser.findApproveUserByPage", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUserList;
	}
	public List<Map<String,Object>> findByPageMapPop(WkfApprovalUser wkfApprovalUser)throws DAOException 
	{
		List<Map<String,Object>> wkfApprovalUserMapList = null;
		try {
			wkfApprovalUserMapList = getSqlMapClientTemplate().queryForList("WkfApprovalUser.findByPageMapPop", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUserMapList;
		
	}

	@SuppressWarnings("unchecked")
	public List<String> getWkfApprovalUserList(WkfApprovalUser wkfApprovalUser) throws DAOException {
		List<String> wkfApprovalUserList = null;
		try {
			wkfApprovalUserList = getSqlMapClientTemplate().queryForList("WkfApprovalUser.getListForWKF", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUserList;
	}
	public List<WkfApprovalUser> getAllList(WkfApprovalUser wkfApprovalUser) throws DAOException {
		List<WkfApprovalUser> wkfApprovalUserList = null;
		try {
			wkfApprovalUserList = getSqlMapClientTemplate().queryForList("WkfApprovalUser.getAllList", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUserList;
	}
	
	public List<WkfApprovalUser> getAllList(String wkfUserName) throws DAOException {
		List<WkfApprovalUser> wkfApprovalUserList = null;
		try {
			wkfApprovalUserList = getSqlMapClientTemplate().queryForList("WkfApprovalUser.getAllList2", wkfUserName);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUserList;
	}
	
	public WkfApprovalUser getById(WkfApprovalUser wkfApprovalUser) throws DAOException {
		try {
			wkfApprovalUser = (WkfApprovalUser) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.getById", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUser;
	}
	public WkfApprovalUser getById2(WkfApprovalUser wkfApprovalUser) throws DAOException {
		try {
			wkfApprovalUser = (WkfApprovalUser) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.getById2", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUser;
	}
	
	public WkfApprovalUser getByUser(WkfApprovalUser wkfApprovalUser) throws DAOException {
		try {
			wkfApprovalUser = (WkfApprovalUser) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.getByUser", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUser;
	}
	public int getUserCount(String wkfUserName) throws DAOException{
		int count =0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.getUserCount", wkfUserName);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public int getCount(WkfApprovalUser wkfApprovalUser) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WkfApprovalUser.findPage.count", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public Integer getApprovalUserCount(WkfApprovalUser wkfApprovalUser)throws DAOException 
	{
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.findApprovalUserByPage.count", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	/**
	 * 
	 * 功能描述：查询当前节点会签任务可选择人员
	 * @param wkfApprovalUser
	 * @return
	 * @throws DAOException
	 * @修改日志：
	 */
	public Integer getApproveUserCount(WkfApprovalUser wkfApprovalUser)throws DAOException 
	{
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.getApproveUserCount.count", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(WkfApprovalUser wkfApprovalUser) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WkfApprovalUser.insert",
					wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(WkfApprovalUser wkfApprovalUser) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WkfApprovalUser.update",
					wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public String getSeq() throws DAOException {
		String seq = null;
		try {
			seq=(String) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.getSeq");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return seq;
	}
	/**
	 * 
	 * 功能描述： 根据操作员号，取审批人员类型
	 * @return
	 * @throws DAOException
	 * @修改日志：
	 */
	public Integer getSignType(String op_no) throws DAOException {
		int type = 0;
		try {
			type=(Integer) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.getSignType",op_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return type;
	}

	public List<WkfApprovalUser> getByIdAndBrNo(WkfApprovalUser wkfApprovalUser)
			throws DAOException {
		List<WkfApprovalUser> wkf = null;
		try{
			wkf =  getSqlMapClientTemplate().queryForList("WkfApprovalUser.getRoleNoById", wkfApprovalUser);
		}catch(Exception e){
			e.printStackTrace();
		}
		return wkf;
	}

	@Override
	public WkfApprovalUser getByRoleName(WkfApprovalUser wkfApprovalUser)
			throws DAOException {
		try {
			wkfApprovalUser = (WkfApprovalUser) getSqlMapClientTemplate().queryForObject("WkfApprovalUser.getByRoleName", wkfApprovalUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wkfApprovalUser;
	}

}