package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundRepayPlanDao;
import app.creditapp.fund.entity.FundRepayPlan;
/**
* Title: FundRepayPlanDaoImpl.java
* Description:
**/
public class FundRepayPlanDaoImpl extends BaseIbatisDao implements FundRepayPlanDao {

	public void del(FundRepayPlan fundRepayPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundRepayPlan.del", fundRepayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundRepayPlan> findByPage(FundRepayPlan fundRepayPlan) throws DAOException {
		List fundRepayPlanList = null;
		try {
			fundRepayPlanList = getSqlMapClientTemplate().queryForList(
					"FundRepayPlan.findByPage", fundRepayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundRepayPlanList;
	}

	public FundRepayPlan getById(FundRepayPlan fundRepayPlan) throws DAOException {
		try {
			fundRepayPlan = (FundRepayPlan) getSqlMapClientTemplate()
					.queryForObject("FundRepayPlan.getById", fundRepayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundRepayPlan;
	}

	public int getCount(FundRepayPlan fundRepayPlan) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundRepayPlan.findPage.count", fundRepayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundRepayPlan fundRepayPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundRepayPlan.insert",
					fundRepayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundRepayPlan fundRepayPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundRepayPlan.update",
					fundRepayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public double getBalByDate(FundRepayPlan fundRepayPlan) throws DAOException {
		double sumbal = 0.00;
		try {
			sumbal = Double.parseDouble(getSqlMapClientTemplate().queryForObject("FundRepayPlan.getBalByDate", fundRepayPlan).toString());
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return sumbal;
	}	
	//新增清算计划时，先删掉对应资金的之前的清算计划
	public void delByFunoNo(FundRepayPlan fundRepayPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundRepayPlan.delByFunoNo", fundRepayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}