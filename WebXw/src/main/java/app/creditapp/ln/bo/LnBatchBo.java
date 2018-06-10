package  app.creditapp.ln.bo;

import java.util.List;
import java.util.Map;

import app.base.ServiceException;
import app.creditapp.inf.entity.RequestParm;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2101;
import app.creditapp.inf.entity.WsIn2103;
import app.creditapp.inf.entity.WsOut2101_1;
import app.creditapp.ln.entity.LnBatch;
import app.util.toolkit.Ipage;

/**
* Title: LnBatchBo.java
* Description:
**/
public interface LnBatchBo {

	public LnBatch getById(LnBatch lnBatch) throws ServiceException;

	public void del(LnBatch lnBatch) throws ServiceException;

	public void insert(LnBatch lnBatch) throws ServiceException;

	public void update(LnBatch lnBatch) throws ServiceException;

	public void updateNum(LnBatch lnBatch) throws ServiceException;
	
	public Ipage findByPage(Ipage ipg, LnBatch lnBatch) throws ServiceException;
	
	/*********ÐÂÔö**************/
	
	public ResponseParm search(RequestParm reqParm) throws ServiceException;
	
	public void insertBatMidAndStage(WsIn2101 wsIn2101) throws ServiceException;
	
	public List<WsOut2101_1> validateApplyMid(WsIn2101 wsIn2101) throws ServiceException;

	public LnBatch getByLnApplyMid(LnBatch lnBatch) throws ServiceException;
	
	public  ResponseParm validateWsIn(WsIn2103 wsIn2103) throws ServiceException;
	
	public Ipage findByPageforList(Ipage ipg, LnBatch lnBatch) throws ServiceException;
	
	public int countforListWs(LnBatch lnBatch) throws ServiceException;
	
	public  Map<String,String> validateAndSave(WsIn2101 wsIn2101) throws ServiceException;
}