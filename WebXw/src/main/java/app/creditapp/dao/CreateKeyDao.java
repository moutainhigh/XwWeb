package app.creditapp.dao;

import app.base.DAOException;

public interface CreateKeyDao {
	
	/**
	 * @方法说明：获取预进件批次编号
	 * @返回参数 String
	 */
	public String getPreBatchNo()throws DAOException;
	
	public String getQueBatchNo()throws DAOException;
	
	public String getUUID()throws DAOException;
	
	public String getBatchNo()throws DAOException;
	
	public String getWsId()throws DAOException;
	
	public String getPreAppId()throws DAOException;
	
	public String getLnBatchNo()throws DAOException;
	
	public String getLnApplyId()throws DAOException;
	
	/**
	 * 获得合同号
	 * @param 
	 * @return 
	 * @throws DAOException
	 */
	public String getKeyByDate( String sys_date )throws DAOException;
	/**
	 * 获得客户号
	 * @param 
	 * @return 
	 * @throws DAOException
	 */
	public String getCif_no( String sys_date )throws DAOException;
	/**
	 * 获得申请编号
	 * @param 
	 * @return 
	 * @throws DAOException
	 */
	public String getOutInId()throws DAOException;
	
	/**
	 * @description 通知单编号
	 * @return
	 * @throws DAOException
	 * @version 1.0
	 */
	public String getNoteModelId() throws DAOException;
	
	/**
	 * 获取系统当天客户经理组最大编号
	 * @return
	 * @throws DAOException
	 */
	public String getGroupNo(String sys_date) throws DAOException;
	
	public String getPublicKeyByDate(String sysDate, String tableName, String id) throws DAOException;
	
	/**
	 * 获取资产池编号
	 * @return
	 * @throws DAOException
	 */
	public String getPoolId() throws DAOException;
	

}
