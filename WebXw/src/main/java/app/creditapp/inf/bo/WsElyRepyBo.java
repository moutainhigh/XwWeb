package  app.creditapp.inf.bo;

import java.util.Map;

import app.base.ServiceException;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsElyRepy;
import app.creditapp.inf.entity.WsIn2801;
import app.creditapp.inf.entity.WsIn2802;
import app.creditapp.inf.entity.WsIn2805;
import app.creditapp.inf.entity.WsIn2806;
import app.creditapp.inf.entity.WsOut2806;
import app.util.toolkit.Ipage;

/**
* Title: WsElyRepyBo.java
* Description:
**/
public interface WsElyRepyBo {

	public WsElyRepy getById(WsElyRepy wsElyRepy) throws ServiceException;

	public void del(WsElyRepy wsElyRepy) throws ServiceException;

	public void insert(WsElyRepy wsElyRepy) throws ServiceException;

	public void update(WsElyRepy wsElyRepy) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WsElyRepy wsElyRepy) throws ServiceException;

	public Map<String,String> validateAndSave(WsIn2801 wsIn2801) throws ServiceException;

	public Map<String,String> validateAndSave(WsIn2802 wsIn2802) throws ServiceException;
	
	public  ResponseParm validateWsIn(WsIn2805 wsIn2805) throws ServiceException;
	
	public int countforListWs(WsElyRepy wsElyRepy) throws ServiceException;

	public Ipage findByPageForList(Ipage ipg, WsElyRepy wsElyRepy) throws ServiceException;
	
	public WsOut2806 calcu(WsIn2806 wsIn2806) throws ServiceException;
	
}