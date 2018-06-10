package  app.creditapp.inf.bo;

import java.util.Map;

import app.base.ServiceException;
import app.creditapp.inf.entity.WsElyRepyBatch;
import app.creditapp.inf.entity.WsIn2803;
import app.creditapp.inf.entity.WsIn2804;
import app.util.toolkit.Ipage;

/**
* Title: WsElyRepyBatchBo.java
* Description:
**/
public interface WsElyRepyBatchBo {

	public WsElyRepyBatch getById(WsElyRepyBatch wsElyRepyBatch) throws ServiceException;

	public void del(WsElyRepyBatch wsElyRepyBatch) throws ServiceException;

	public void insert(WsElyRepyBatch wsElyRepyBatch) throws ServiceException;

	public void update(WsElyRepyBatch wsElyRepyBatch) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WsElyRepyBatch wsElyRepyBatch) throws ServiceException;
	
	public int getCount(WsElyRepyBatch wsElyRepyBatch) throws ServiceException;

	public Map<String,String> validateAndSave(WsIn2803 wsIn2803) throws ServiceException;

	public Map<String,String> validateAndSave(WsIn2804 wsIn2804) throws ServiceException;
}