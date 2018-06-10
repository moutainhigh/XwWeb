package  app.creditapp.acc.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.entity.WsRepyFee;

/**
* Title: WsRepyFeeBo.java
* Description:
**/
public interface WsRepyFeeBo {

//	public WsRepyFee getById(WsRepyFee wsRepyFee) throws ServiceException;

	public void del(WsRepyFee wsRepyFee) throws ServiceException;

	public void insert(WsRepyFee wsRepyFee) throws ServiceException;

	public void update(WsRepyFee wsRepyFee) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WsRepyFee wsRepyFee) throws ServiceException;

}