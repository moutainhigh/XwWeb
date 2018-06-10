package  app.creditapp.inf.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.inf.entity.WsBase;

/**
* Title: WsBaseBo.java
* Description:
**/
public interface WsBaseBo {

	public WsBase getById(WsBase wsBase) throws ServiceException;

	public void del(WsBase wsBase) throws ServiceException;

	public void insert(WsBase wsBase) throws ServiceException;
	
	public void insertDelBack(WsBase wsBase) throws ServiceException;
	
	public void updateDelBack(WsBase wsBase) throws ServiceException;

	public void update(WsBase wsBase) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WsBase wsBase) throws ServiceException;

}