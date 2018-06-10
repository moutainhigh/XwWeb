package  app.creditapp.acc.log.bo;

import app.base.ServiceException;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.wkf.entity.WkfParm;
import app.util.toolkit.Ipage;

/**
* Title: AcTraceLogBo.java
* Description:
**/
public interface AcTraceLogBo {

	public AcTraceLog getById(AcTraceLog acTraceLog) throws ServiceException;

	public AcTraceLog getRecentTraceLog(AcTraceLog acTraceLog) throws ServiceException;

	public void del(AcTraceLog acTraceLog) throws ServiceException;

	public void insert(AcTraceLog acTraceLog) throws ServiceException;

	public void update(AcTraceLog acTraceLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcTraceLog acTraceLog) throws ServiceException;
	
	public Ipage findByPageForTask(Ipage ipg, AcTraceLog acTraceLog) throws ServiceException;

	//接口ws3202校验
//	public ResponseParm getresponseParm(WsIn3202 wsIn3202)throws ServiceException;
	//接口ws3202实现分页操作
	public Ipage findByPageforws3202(Ipage ipg, AcTraceLog acTraceLog)throws ServiceException;
	//获取符合条件的总数量ws3202
	public int getCountfor3202(AcTraceLog acTraceLog) throws ServiceException;
	//流程
	public String doSubmit(WkfParm parm ,AcTraceLog acTraceLog,String opNo, String brNo) throws ServiceException;

}