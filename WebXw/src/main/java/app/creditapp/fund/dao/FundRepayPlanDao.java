package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundRepayPlan;
/**
* Title: FundRepayPlanDao.java
* Description:
**/
public interface FundRepayPlanDao {

	public FundRepayPlan getById(FundRepayPlan fundRepayPlan) throws DAOException;

	public void del(FundRepayPlan fundRepayPlan) throws DAOException;

	public void insert(FundRepayPlan fundRepayPlan) throws DAOException;

	public void update(FundRepayPlan fundRepayPlan) throws DAOException;
	
	public double getBalByDate(FundRepayPlan fundRepayPlan) throws DAOException;
	
	public int getCount(FundRepayPlan fundRepayPlan) throws DAOException;

	public List<FundRepayPlan > findByPage(FundRepayPlan fundRepayPlan) throws DAOException;
	
	public void delByFunoNo(FundRepayPlan fundRepayPlan) throws DAOException;

}