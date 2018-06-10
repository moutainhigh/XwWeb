package  app.creditapp.aft.bo;

import app.base.ServiceException;
import app.creditapp.aft.entity.AftRepyClear;
import app.creditapp.inf.entity.WsRepyClear;
import app.util.toolkit.Ipage;

/**
* Title: AftRepyClearBo.java
* Description:
**/
public interface AftRepyClearBo {

	public AftRepyClear getById(AftRepyClear aftRepyClear) throws ServiceException;
	
	public WsRepyClear selectClearResult(WsRepyClear wsRepyClear) throws ServiceException;


	public WsRepyClear WsInsert(WsRepyClear wsRepyClear) throws ServiceException;

	public void dealClear(WsRepyClear wsRepyClear) throws ServiceException;

	public void del(AftRepyClear aftRepyClear) throws ServiceException;

	public void insert(AftRepyClear aftRepyClear) throws ServiceException;

	public void update(AftRepyClear aftRepyClear) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftRepyClear aftRepyClear) throws ServiceException;

}