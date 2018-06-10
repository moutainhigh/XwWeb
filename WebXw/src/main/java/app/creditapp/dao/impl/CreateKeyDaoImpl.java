package app.creditapp.dao.impl;

import java.util.HashMap;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.dao.CreateKeyDao;

public class CreateKeyDaoImpl extends BaseIbatisDao implements CreateKeyDao {

	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 23, 2016
	 * @方法说明：获取预进件批次编号
	 * @返回参数 String
	 */
	public String getPreBatchNo()throws DAOException{
		String batchNo = "";
		try {
			batchNo = (String)getSqlMapClientTemplate().queryForObject("getPreBatchNo.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchNo;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 23, 2016
	 * @方法说明：获取预进件批次编号
	 * @返回参数 String
	 */
	public String getQueBatchNo()throws DAOException{
		String batchNo = "";
		try {
			batchNo = (String)getSqlMapClientTemplate().queryForObject("getQueBatchNo.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchNo;
	}
	
	public String getWsId()throws DAOException{
		String wsId = "";
		try {
			wsId = (String)getSqlMapClientTemplate().queryForObject("getWsId.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsId;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 23, 2016
	 * @方法说明：获取账户维护流水号
	 * @返回参数 String
	 */
	public String getUUID()throws DAOException{
		String UUID = "";
		try {
			UUID = (String)getSqlMapClientTemplate().queryForObject("getUUID.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return UUID;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 23, 2016
	 * @方法说明：获取预进件业务编号
	 * @返回参数 String
	 */
	public String getPreAppId()throws DAOException{
		String appId = "";
		try {
			appId = (String)getSqlMapClientTemplate().queryForObject("getPreAppId.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return appId;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 23, 2016
	 * @方法说明：获取正式进件批次编号
	 * @返回参数 String
	 */
	public String getLnBatchNo()throws DAOException{
		String batchNo = "";
		try {
			batchNo = (String)getSqlMapClientTemplate().queryForObject("getLnBatchNo.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchNo;
	}
	
	/**
	 * @作者 DHCC-SONG
	 * @日期 Jun 23, 2016
	 * @方法说明：获取正式进件业务编号
	 * @返回参数 String
	 */
	public String getLnApplyId()throws DAOException{
		String applyId = "";
		try {
			applyId = (String)getSqlMapClientTemplate().queryForObject("getLnApplyNo.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return applyId;
	}
	
	
	
	public String getKeyByDate(String sys_date) throws DAOException {
		String key = "";
		try {
			key = (String)getSqlMapClientTemplate().queryForObject("getKeyByDate.getkey",sys_date);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}
	
	public String getPublicKeyByDate(String sys_date,String tableName, String id) throws DAOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sys_date", sys_date);
		map.put("tableName", tableName);
		map.put("id", id);
		String key = "";
		try {
			key = (String)getSqlMapClientTemplate().queryForObject("getKeyByDate.getPublickey",map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}
	
	public String getCif_no(String sys_date) throws DAOException {
		String key = "";
		try {
			key = (String)getSqlMapClientTemplate().queryForObject("getKeyByDate.getCif_no",sys_date);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}	
	public String getOutInId() throws DAOException {
		String key = "";
		try {
			key = (String)getSqlMapClientTemplate().queryForObject("getKey.getOutInId");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}
	@Override
	public String getNoteModelId() throws DAOException {
		String key = "";
		try {
			key = (String)getSqlMapClientTemplate().queryForObject("getKey.getNoteModel");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}
	
	public String getGroupNo(String sys_date) throws DAOException {
		String key = "";
		try {
			key = (String)getSqlMapClientTemplate().queryForObject("getGroupNo.getkey",sys_date);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}
	
	public String getAuthNo() throws DAOException {
		String key = "";
		try {
			key = (String)getSqlMapClientTemplate().queryForObject("getAuthNo.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}
	public Integer getAcChangeId() throws DAOException {
		Integer key = 0;
		try {
			key = (Integer) getSqlMapClientTemplate().queryForObject("getAcChangeId.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}

	/**
	 * 核算授权生成loanNo
	 * @return
	 * @throws DAOException
	 */
	public String getLoanNo() throws DAOException {
		String key = "";
		try {
			key = (String)getSqlMapClientTemplate().queryForObject("getLoanNo.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return key;
	}
	
	public String getBatchNo()throws DAOException{
		String batchNo = "";
		try {
			batchNo = (String)getSqlMapClientTemplate().queryForObject("getBatchNo.getkey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return batchNo;
	}
	
	/**
	 * 获取资产池编号
	 * @return
	 * @throws DAOException
	 */
	public String getPoolId() throws DAOException{
		String poolId = "";
		try {
			poolId = (String)getSqlMapClientTemplate().queryForObject("CreateKey.PoolId.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return poolId;
	}
	
}
