package  app.creditapp.dev.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.dev.entity.PopParmConf;
/**
* Title: PopParmConfDao.java
* Description:
**/
public interface PopParmConfDao {

	public PopParmConf getById(PopParmConf popParmConf) throws DAOException;

	public void del(PopParmConf popParmConf) throws DAOException;

	public void insert(PopParmConf popParmConf) throws DAOException;

	public void update(PopParmConf popParmConf) throws DAOException;
	
	public int getCount(PopParmConf popParmConf) throws DAOException;
	
	public int getCountPop(Connection conn,String sql) throws DAOException;
	
	public List<PopParmConf > findByPage(PopParmConf popParmConf) throws DAOException;
	
	/**
	 * @description 获取场景号最大的ID
	 * @return
	 * @throws DAOException
	 * @version 1.0
	 */
	public int getMaxId() throws DAOException;
	
	public Map queryAjax(Connection conn,String sql,Map map) throws DAOException;

}