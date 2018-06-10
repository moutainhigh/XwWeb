package  app.creditapp.inf.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2202;
import app.creditapp.inf.entity.WsOut2202;
import app.creditapp.inf.entity.WsRepyPlan;
import app.util.toolkit.Ipage;

/**
* Title: WsRepyPlanBo.java
* Description:
**/
public interface WsRepyPlanBo {

	public WsRepyPlan getById(WsRepyPlan wsRepyPlan) throws ServiceException;

	public void del(WsRepyPlan wsRepyPlan) throws ServiceException;

	public void insert(WsRepyPlan wsRepyPlan) throws ServiceException;

	public void update(WsRepyPlan wsRepyPlan) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WsRepyPlan wsRepyPlan) throws ServiceException;
	
	public ResponseParm validateForWs(WsIn2202 wsIn2202) throws ServiceException;

	public List<WsOut2202> findByWsIn(WsIn2202 wsIn2202) throws ServiceException;
	
	public double getPrcpAmtCount(WsRepyPlan wsRepyPlan)throws  ServiceException;

}