package  app.creditapp.inf.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.inf.entity.WsElyRepyBatch;
/**
* Title: WsElyRepyBatchDao.java
* Description:
**/
public interface WsElyRepyBatchDao {

	public WsElyRepyBatch getById(WsElyRepyBatch wsElyRepyBatch) throws DAOException;

	public void del(WsElyRepyBatch wsElyRepyBatch) throws DAOException;

	public void insert(WsElyRepyBatch wsElyRepyBatch) throws DAOException;

	public void update(WsElyRepyBatch wsElyRepyBatch) throws DAOException;
	
	public int getCount(WsElyRepyBatch wsElyRepyBatch) throws DAOException;

	public List<WsElyRepyBatch > findByPage(Map map) throws DAOException;
	
	public int getCnt(WsElyRepyBatch wsElyRepyBatch) throws DAOException;

}