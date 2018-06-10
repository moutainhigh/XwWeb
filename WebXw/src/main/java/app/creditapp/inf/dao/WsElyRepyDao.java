package  app.creditapp.inf.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.inf.entity.WsElyRepy;
/**
* Title: WsElyRepyDao.java
* Description:
**/
public interface WsElyRepyDao {

	public WsElyRepy getById(WsElyRepy wsElyRepy) throws DAOException;
	
	public WsElyRepy getByBatchNo(WsElyRepy wsElyRepy) throws DAOException;

	public void del(WsElyRepy wsElyRepy) throws DAOException;

	public void insert(WsElyRepy wsElyRepy) throws DAOException;

	public void update(WsElyRepy wsElyRepy) throws DAOException;
	
	public void updateStsAndDesc(WsElyRepy wsElyRepy) throws DAOException;
	
	public int getCount(WsElyRepy wsElyRepy) throws DAOException;

	public List<WsElyRepy > findByPage(WsElyRepy wsElyRepy) throws DAOException;

	public void delByPactNo(WsElyRepy wsElyRepy) throws DAOException;
	
	public void insertForList(final List<WsElyRepy> wsElyRepylist) throws DAOException;
	
	public int getCountforlist(WsElyRepy wsElyRepy) throws DAOException;
	
	public List<WsElyRepy> findByPageForList(WsElyRepy wsElyRepy) throws DAOException ;

}