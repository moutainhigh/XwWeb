package  app.creditapp.trade.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.trade.entity.CipTrade;

/**
* Title: CipTradeBo.java
* Description:
**/
public interface CipTradeBo {

	public CipTrade getById(CipTrade cipTrade) throws ServiceException;

	public void del(CipTrade cipTrade) throws ServiceException;

	public void insert(CipTrade cipTrade) throws ServiceException;

	public void update(CipTrade cipTrade) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CipTrade cipTrade) throws ServiceException;

	public CipTrade getCipTradeSql(String channel_id,String trade_id) throws ServiceException;

	public List<CipTrade> getCipTradeList(String trade_id) throws ServiceException;
	
	public CipTrade getByReqMsgid(CipTrade cipTrade) throws ServiceException;

	public void updateTxFlag(CipTrade cipTrade) throws ServiceException;

	public void updateTxFlagByTradeId(CipTrade cipTrade) throws ServiceException;
}