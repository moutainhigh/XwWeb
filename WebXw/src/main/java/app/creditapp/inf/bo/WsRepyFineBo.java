package  app.creditapp.inf.bo;

import app.base.ServiceException;
import app.creditapp.inf.entity.WsRepyFine;
import app.util.toolkit.Ipage;

/**
* Title: WsRepyFineBo.java
* Description:
**/
public interface WsRepyFineBo {

	public WsRepyFine getById(WsRepyFine wsRepyFine) throws ServiceException;

	public void del(WsRepyFine wsRepyFine) throws ServiceException;

	public void insert(WsRepyFine wsRepyFine) throws ServiceException;

	public void update(WsRepyFine wsRepyFine) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WsRepyFine wsRepyFine) throws ServiceException;

}