package  app.creditapp.acc.option.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.option.dao.AcLnRepayPlnSuspDao;
import app.creditapp.acc.option.entity.AcLnRepayPlnSusp;
import app.creditapp.inf.entity.WsRepyPlan;
/**
* Title: AcLnRepayPlnSuspDaoImpl.java
* Description:
**/
public class AcLnRepayPlnSuspDaoImpl extends BaseIbatisDao implements AcLnRepayPlnSuspDao {

	public void del(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnRepayPlnSusp.del", acLnRepayPlnSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void delByPactnoAndBrno(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("AcLnRepayPlnSusp.delByPactnoAndBrno", acLnRepayPlnSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<AcLnRepayPlnSusp> findByPage(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException {
		List acLnRepayPlnSuspList = null;
		try {
			acLnRepayPlnSuspList = getSqlMapClientTemplate().queryForList(
					"AcLnRepayPlnSusp.findByPage", acLnRepayPlnSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnSuspList;
	}
	//根据批次取出list进行还款计划上传的逻辑判断：
	public List<WsRepyPlan> getByWsRepyPlanList(String wsRepyPlanBatch) throws DAOException {
		List wsRepyPlanList = null;
		try {
			wsRepyPlanList = getSqlMapClientTemplate().queryForList(
					"AcLnRepayPlnSusp.getByWsRepyPlanList", wsRepyPlanBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyPlanList;
	}
	
	public List<String> getByBatchDisPact(String wsRepyPlanBatch) throws DAOException {
		List<String> wsRepyPlanList = null;
		try {
			wsRepyPlanList = getSqlMapClientTemplate().queryForList(
					"AcLnRepayPlnSusp.getByBatchDisPact", wsRepyPlanBatch);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyPlanList;
	}

	public List<WsRepyPlan> getListByBatchAndPactno(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException {
		List<WsRepyPlan> wsRepyPlanList = null;
		try {
			wsRepyPlanList = getSqlMapClientTemplate().queryForList(
					"AcLnRepayPlnSusp.getListByBatchAndPactno", acLnRepayPlnSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyPlanList;
	}

	public AcLnRepayPlnSusp getById(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException {
		try {
			acLnRepayPlnSusp = (AcLnRepayPlnSusp) getSqlMapClientTemplate()
					.queryForObject("AcLnRepayPlnSusp.getById", acLnRepayPlnSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnRepayPlnSusp;
	}

	public int getCount(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"AcLnRepayPlnSusp.findPage.count", acLnRepayPlnSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("AcLnRepayPlnSusp.insert",
					acLnRepayPlnSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException {
		try {
			getSqlMapClientTemplate().update("AcLnRepayPlnSusp.update",
					acLnRepayPlnSusp);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}