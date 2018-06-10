package  app.creditapp.inf.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2302;
import app.creditapp.inf.entity.WsOut2302_1;
import app.creditapp.inf.entity.WsRepyMes;
import app.creditapp.inf.entity.WsRepyMes_Count;
import app.util.toolkit.Ipage;

/**
* Title: WsRepyMesBo.java
* Description:
**/
public interface WsRepyMesBo {

	public WsRepyMes getById(WsRepyMes wsRepyMes) throws ServiceException;

	public void del(WsRepyMes wsRepyMes) throws ServiceException;

	public void insert(WsRepyMes wsRepyMes) throws ServiceException;

	public void update(WsRepyMes wsRepyMes) throws ServiceException;

	public Ipage findByPage(Ipage ipg, WsRepyMes wsRepyMes) throws ServiceException;
	//端口ws2302，校验
	public ResponseParm getresponse(WsIn2302 wsIn2302)throws ServiceException;
	//接口ws2302分页操作
	public Ipage findByPageforws2302(Ipage ipg, WsRepyMes wsRepyMes)throws ServiceException;
	//接口ws2302符合条件的总数
	public int getCountforws2302(WsRepyMes wsRepyMes) throws ServiceException;
	
	public WsOut2302_1 getByIdForNew(WsRepyMes wsRepyMes) throws ServiceException;
	//扣款信息统计
	public Ipage getCountMes(Ipage ipg, WsRepyMes_Count wc) throws ServiceException;
}