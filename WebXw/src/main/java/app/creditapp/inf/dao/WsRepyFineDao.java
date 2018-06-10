package  app.creditapp.inf.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.inf.entity.WsIn2301_1_1;
import app.creditapp.inf.entity.WsRepyFine;
/**
* Title: WsRepyFineDao.java
* Description:
**/
public interface WsRepyFineDao {

	public WsRepyFine getById(WsRepyFine wsRepyFine) throws DAOException;

	public void del(WsRepyFine wsRepyFine) throws DAOException;

	public void insert(WsRepyFine wsRepyFine) throws DAOException;

	public void update(WsRepyFine wsRepyFine) throws DAOException;
	
	public int getCount(WsRepyFine wsRepyFine) throws DAOException;

	public List<WsRepyFine > findByPage(WsRepyFine wsRepyFine) throws DAOException;
	//列表插入数据库ws2301接口使用
	public void insertBatchfor2301(List<WsIn2301_1_1> list2301_1_1list) throws DAOException;

	public List<WsRepyFine > getListByWsId(WsRepyFine wsRepyFine) throws DAOException;

}