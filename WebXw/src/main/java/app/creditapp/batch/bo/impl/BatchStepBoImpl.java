package  app.creditapp.batch.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.batch.bo.BatchStepBo;
import app.creditapp.batch.dao.BatchStepDao;
import app.creditapp.batch.entity.BatchStep;
import app.util.toolkit.Ipage;
/**
* Title: BatchStepBoImplImpl.java
* Description:
**/
public class BatchStepBoImpl extends BaseService implements BatchStepBo {

	private BatchStepDao batchStepDao;

	public void del(BatchStep batchStep) throws ServiceException {
		try {
			batchStepDao.del(batchStep);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, BatchStep batchStep)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) batchStepDao
					.getCount(batchStep) });// 初始化分页类
			batchStep.setStartNumAndEndNum (ipg);
			ipg.setResult(batchStepDao.findByPage(batchStep));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public BatchStep getById(BatchStep batchStep) throws ServiceException {
		try {
			batchStep = batchStepDao.getById(batchStep);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return batchStep;
	}

	public void insert(BatchStep batchStep) throws ServiceException {
		try {
			batchStepDao.insert(batchStep);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(BatchStep batchStep) throws ServiceException {
		try {
			batchStepDao.update(batchStep);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public BatchStepDao getBatchStepDao() {
		return batchStepDao;
	}

	public void setBatchStepDao(BatchStepDao batchStepDao) {
		this.batchStepDao = batchStepDao;
	}

	@Override
	public List<BatchStep> getAllStepList() {
		return batchStepDao.getAllStepList();
	}
}