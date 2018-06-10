package app.creditapp.batch.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.batch.bo.BatchNodeBo;
import app.creditapp.batch.bo.BatchSqlBo;
import app.creditapp.batch.dao.BatchNodeDao;
import app.creditapp.batch.entity.BatchNode;
import app.util.toolkit.Ipage;

/**
 * Title: BatchNodeBoImplImpl.java Description:
 * 
 **/
public class BatchNodeBoImpl extends BaseService implements BatchNodeBo {

	private BatchNodeDao batchNodeDao;
	private BatchSqlBo batchSqlBo;

	public void del(BatchNode batchNode) throws ServiceException {
		try {
			batchNodeDao.del(batchNode);
			batchSqlBo.delByNodeNo(batchNode.getNode_no());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, BatchNode batchNode)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) batchNodeDao
					.getCount(batchNode) });// 初始化分页类
			batchNode.setStartNumAndEndNum(ipg);
			ipg.setResult(batchNodeDao.findByPage(batchNode));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public BatchNode getById(BatchNode batchNode) throws ServiceException {
		try {
			batchNode = batchNodeDao.getById(batchNode);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return batchNode;
	}

	public void insert(BatchNode batchNode) throws ServiceException {
		try {
			batchNodeDao.insert(batchNode);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(BatchNode batchNode) throws ServiceException {
		try {
			batchNodeDao.update(batchNode);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean isGroupRunning() throws ServiceException {
		boolean isRunning = false;
		try {
			isRunning = batchNodeDao.isGroupRunning();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return isRunning;
	}

	public String getPreTaskState(String note_no) throws ServiceException {
		String state = "";
		try {
			state = batchNodeDao.getPreTaskState(note_no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return state;
	}
	
	public BatchNodeDao getBatchNodeDao() {
		return batchNodeDao;
	}

	public void setBatchNodeDao(BatchNodeDao batchNodeDao) {
		this.batchNodeDao = batchNodeDao;
	}

	public BatchSqlBo getBatchSqlBo() {
		return batchSqlBo;
	}

	public void setBatchSqlBo(BatchSqlBo batchSqlBo) {
		this.batchSqlBo = batchSqlBo;
	}
	
}