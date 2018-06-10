package  app.creditapp.ln.bo;

import java.util.List;
import java.util.Map;

import app.base.ServiceException;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2001;
import app.creditapp.inf.entity.WsIn2004;
import app.creditapp.inf.entity.WsIn2005;
import app.creditapp.inf.entity.WsOut2004;
import app.creditapp.ln.entity.PreBatch;
import app.util.toolkit.Ipage;

/**
* Title: PreBatchBo.java
* Description:
**/
public interface PreBatchBo {

	public PreBatch getById(PreBatch preBatch) throws ServiceException;

	public void del(PreBatch preBatch) throws ServiceException;

	public void insert(PreBatch preBatch) throws ServiceException;

	public void update(PreBatch preBatch) throws ServiceException;
	
	public void updateNum(PreBatch preBatch) throws ServiceException;

	public Ipage findByPage(Ipage ipg, PreBatch preBatch) throws ServiceException;

	public PreBatch getByPreApply(PreBatch preBatch) throws ServiceException;

    public List<WsOut2004> findByBatchNo(WsOut2004 wsOut2004) throws ServiceException;
	
	public  Map<String,String> validateAndSave(WsIn2001 wsIn2001) throws ServiceException;
	
	public  ResponseParm validateforListWs(WsIn2005 wsIn2005) throws ServiceException;
	
	public  ResponseParm validateforPreApplyInsertWs(WsIn2004 wsIn2004) throws ServiceException;
	
	public  Ipage findByPageforListWs(Ipage ipg, PreBatch preBatch) throws ServiceException;
	
	public int coutforListWs(PreBatch preBatch) throws ServiceException;	
}