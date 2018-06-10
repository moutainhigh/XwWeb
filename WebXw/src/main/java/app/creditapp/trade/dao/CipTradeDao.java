package  app.creditapp.trade.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.trade.entity.CipTrade;
/**
* Title: CipTradeDao.java
* Description:
**/
public interface CipTradeDao {

	public CipTrade getById(CipTrade cipTrade) throws DAOException;

	public void del(CipTrade cipTrade) throws DAOException;

	public void insert(CipTrade cipTrade) throws DAOException;

	public void update(CipTrade cipTrade) throws DAOException;
	
	public int getCount(CipTrade cipTrade) throws DAOException;

	public List<CipTrade > findByPage(CipTrade cipTrade) throws DAOException;

	public CipTrade getCipTradeSql(CipTrade cipTrade) throws DAOException;

	public List<CipTrade> getCipTradeList(String trade_id) throws DAOException;

	public CipTrade getByReqMsgid(CipTrade cipTrade) throws DAOException;

	public void updateTxFlag(CipTrade cipTrade) throws DAOException;

	public void updateTxFlagByTradeId(CipTrade cipTrade) throws DAOException;

}