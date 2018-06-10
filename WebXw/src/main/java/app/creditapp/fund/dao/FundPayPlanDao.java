package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundPayPlan;
/**
* Title: FundPayPlanDao.java
* Description:
**/
public interface FundPayPlanDao {

	public FundPayPlan getById(FundPayPlan fundPayPlan) throws DAOException;

	public void del(FundPayPlan fundPayPlan) throws DAOException;

	public void insert(FundPayPlan fundPayPlan) throws DAOException;

	public void update(FundPayPlan fundPayPlan) throws DAOException;
	
	public int getCount(FundPayPlan fundPayPlan) throws DAOException;
	
	public int getCountForAmt(FundPayPlan fundPayPlan) throws DAOException;

	public List<FundPayPlan > findByPage(FundPayPlan fundPayPlan) throws DAOException;
	
	public List<FundPayPlan > findByPageForAmt(FundPayPlan fundPayPlan) throws DAOException;

	public void delByPartNo(FundPayPlan fundPayPlan) throws DAOException;
	
	public double getBalByDate(FundPayPlan fundPayPlan) throws DAOException;
}