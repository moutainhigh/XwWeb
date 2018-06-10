package  app.creditapp.ln.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.ln.dao.LnBatchDao;
import app.creditapp.ln.entity.LnBatch;
import java.util.Map;
import app.creditapp.inf.entity.WsOut2103_1;
/**
* Title: LnBatchDaoImpl.java
* Description:
**/
public class LnBatchDaoImpl extends BaseIbatisDao implements LnBatchDao {

	public void del(LnBatch lnBatch) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnBatch.del", lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}


	public LnBatch getById(LnBatch lnBatch) throws DAOException {
		try {
			lnBatch = (LnBatch) getSqlMapClientTemplate()
					.queryForObject("LnBatch.getById", lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnBatch;
	}

	public int getCount(LnBatch lnBatch) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnBatch.findPage.count", lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnBatch lnBatch) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnBatch.insert",
					lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnBatch lnBatch) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnBatch.update",
					lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public void updateNum(LnBatch lnBatch) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnBatch.updateNum",
					lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public LnBatch getByLnApplyMid(LnBatch lnBatch) throws DAOException {
		try {
			lnBatch = (LnBatch) getSqlMapClientTemplate()
					.queryForObject("LnBatch.getByLnApplyMid", lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnBatch;
	}
	public List<LnBatch> findByPage(LnBatch lnBatch) throws DAOException {
		List lnBatchList = null;
		try {
			lnBatchList = getSqlMapClientTemplate().queryForList(
					"LnBatch.findByPage", lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnBatchList;
	}
	//接口ws2103分页输出结果，带有分页功能
	public List<WsOut2103_1> findByPageforlist(Map map) throws DAOException {
		List wsOut2103_1 = null;
		try {
			wsOut2103_1 = getSqlMapClientTemplate().queryForList(
					"LnBatch.findByPageforlist", map);		
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return wsOut2103_1;
	}
	
	public int getCountforlist(LnBatch lnBatch) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnBatch.findPageforlist.count", lnBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
}