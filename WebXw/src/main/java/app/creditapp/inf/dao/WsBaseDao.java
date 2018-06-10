package  app.creditapp.inf.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.inf.entity.WsBase;
/**
* Title: WsBaseDao.java
* Description:
**/
public interface WsBaseDao {

	public WsBase getById(WsBase wsBase) throws DAOException;

	public void del(WsBase wsBase) throws DAOException;

	public void insert(WsBase wsBase) throws DAOException;

	public void update(WsBase wsBase) throws DAOException;
	
	public int getCount(WsBase wsBase) throws DAOException;

	public List<WsBase > findByPage(WsBase wsBase) throws DAOException;

	public void insertWsBaseReq(WsBase wsBase) throws DAOException;
	
	public void updateWsBaseResp(WsBase wsBase) throws DAOException;
	
	public void insertDelBack(WsBase wsBase) throws DAOException;
	
	public void updateDelBack(WsBase wsBase) throws DAOException;
}