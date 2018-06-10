package  app.creditapp.inf.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.inf.entity.WsOut2902_1;
import app.creditapp.inf.entity.WsRedctn;
/**
* Title: WsRedctnDao.java
* Description:
**/
public interface WsRedctnDao {

	public WsRedctn getById(WsRedctn wsRedctn) throws DAOException;

	public void del(WsRedctn wsRedctn) throws DAOException;

	public void insert(WsRedctn wsRedctn) throws DAOException;

	public void update(WsRedctn wsRedctn) throws DAOException;
	
	public int getCount(WsRedctn wsRedctn) throws DAOException;

	public List<WsRedctn > findByPage(WsRedctn wsRedctn) throws DAOException;
	//接口ws2902符合条件返回列表
	public List<WsOut2902_1> findByPageforws2902(WsRedctn wsRedctn) throws DAOException;
	//接口ws2902符合条件返回个数
	public int getCountforws2902(WsRedctn wsRedctn) throws DAOException;
	//接口 ws2901删除操作
	public void deleteforws2901(WsRedctn wsRedctn) throws DAOException;
}