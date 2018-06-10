package app.creditapp.batch.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.batch.entity.BatchExe;
import app.util.toolkit.Ipage;

/**
 * Title: BatchExeBo.java Description:
 * 
 **/
public interface BatchExeBo {

	public BatchExe getById(BatchExe batchExe) throws ServiceException;

	public void del(BatchExe batchExe) throws ServiceException;

	public void insert(BatchExe batchExe) throws ServiceException;

	public void update(BatchExe batchExe) throws ServiceException;

	public Ipage findByPage(Ipage ipg, BatchExe batchExe) throws ServiceException;

	public List<BatchExe> getAllBatchExeByDate(String batchDate);

	public List<BatchExe> getBatchExeList(String sysdate) throws ServiceException;

	public int getCountByBtchDate() throws ServiceException;

	public int getFailBatchNodeCount(String sts) throws ServiceException;
	
	public int checkBatch(String date) throws ServiceException;
	
	public int runBatch(String date) throws ServiceException;
	
	public void runBatchExe() throws ServiceException;
	
	public void runZfBatch() throws ServiceException;

}