package  app.creditapp.inf.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.inf.dao.WsElyRepyBatchDao;
import app.creditapp.inf.entity.WsElyRepyBatch;
/**
* Title: WsElyRepyBatchDaoImpl.java
* Description:
**/
public class WsElyRepyBatchDaoImpl extends BaseIbatisDao implements WsElyRepyBatchDao {

	//通过批次号获取提前还款申请主表记录条数
	public int getCnt(WsElyRepyBatch wsElyRepyBatch) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WsElyRepyBatch.cnt", wsElyRepyBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void del(WsElyRepyBatch wsElyRepyBatch) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WsElyRepyBatch.del", wsElyRepyBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<WsElyRepyBatch> findByPage(Map map) throws DAOException {
		List wsElyRepyBatchList = null;
		try {
			wsElyRepyBatchList = getSqlMapClientTemplate().queryForList(
					"WsElyRepyBatch.findByPage", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsElyRepyBatchList;
	}

	public WsElyRepyBatch getById(WsElyRepyBatch wsElyRepyBatch) throws DAOException {
		try {
			wsElyRepyBatch = (WsElyRepyBatch) getSqlMapClientTemplate()
					.queryForObject("WsElyRepyBatch.getById", wsElyRepyBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsElyRepyBatch;
	}

	public int getCount(WsElyRepyBatch wsElyRepyBatch) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WsElyRepyBatch.findPage.count", wsElyRepyBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(WsElyRepyBatch wsElyRepyBatch) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsElyRepyBatch.insert",
					wsElyRepyBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(WsElyRepyBatch wsElyRepyBatch) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WsElyRepyBatch.update",
					wsElyRepyBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}