package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundPayPlan;
import app.creditapp.fund.entity.FundSplit;;
/**
* Title: FundPayPlanBo.java
* Description:
**/
public interface FundPayPlanBo {

	public FundPayPlan getById(FundPayPlan fundPayPlan) throws ServiceException;

	public void del(FundPayPlan fundPayPlan) throws ServiceException;

	public void insert(FundPayPlan fundPayPlan) throws ServiceException;
	
	public void auto_insert(FundSplit fundSplit) throws ServiceException;
	
	public void update(FundPayPlan fundPayPlan) throws ServiceException;

	public int findBycount(FundPayPlan fundPayPlan) throws ServiceException;
	
	public Ipage findByPage(Ipage ipg, FundPayPlan fundPayPlan) throws ServiceException;
	
	public Ipage findByPageForAmt(Ipage ipg, FundPayPlan fundPayPlan) throws ServiceException;
	
	public double getBalByDate(FundPayPlan FundPayPlan) throws ServiceException;
}