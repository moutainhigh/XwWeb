package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundPayPlanDao;
import app.creditapp.fund.entity.FundPayPlan;
/**
* Title: FundPayPlanDaoImpl.java
* Description:
**/
public class FundPayPlanDaoImpl extends BaseIbatisDao implements FundPayPlanDao {

	public void del(FundPayPlan fundPayPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundPayPlan.del", fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundPayPlan> findByPage(FundPayPlan fundPayPlan) throws DAOException {
		List fundPayPlanList = null;
		try {
			fundPayPlanList = getSqlMapClientTemplate().queryForList(
					"FundPayPlan.findByPage", fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundPayPlanList;
	}
	public List<FundPayPlan> findByPageForAmt(FundPayPlan fundPayPlan) throws DAOException {
		List fundPayPlanList = null;
		try {
			fundPayPlanList = getSqlMapClientTemplate().queryForList(
					"FundPayPlan.findByPageForAmt", fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundPayPlanList;
	}
	public FundPayPlan getById(FundPayPlan fundPayPlan) throws DAOException {
		try {
			fundPayPlan = (FundPayPlan) getSqlMapClientTemplate()
					.queryForObject("FundPayPlan.getById", fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundPayPlan;
	}

	public int getCount(FundPayPlan fundPayPlan) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundPayPlan.findPage.count", fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountForAmt(FundPayPlan fundPayPlan) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundPayPlan.findByPageForAmt.count", fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public void insert(FundPayPlan fundPayPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundPayPlan.insert",
					fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundPayPlan fundPayPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundPayPlan.update",
					fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//增加按照分配方案删除
	public void delByPartNo(FundPayPlan fundPayPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundPayPlan.delByPartNo", fundPayPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	public double getBalByDate(FundPayPlan fundPayPlan) throws DAOException {
		double bal = 0.00;
		try {
			bal= Double.parseDouble(getSqlMapClientTemplate().queryForObject("FundPayPlan.getBalByDate", fundPayPlan).toString());
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return bal;
	}	
}