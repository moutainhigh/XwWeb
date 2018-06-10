package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundRepayPlan;
import app.creditapp.fund.entity.FundBase;

/**
* Title: FundRepayPlanBo.java
* Description:
**/
public interface FundRepayPlanBo {

	public FundRepayPlan getById(FundRepayPlan fundRepayPlan) throws ServiceException;

	public void del(FundRepayPlan fundRepayPlan) throws ServiceException;

	public void insert(FundRepayPlan fundRepayPlan) throws ServiceException;

	public void update(FundRepayPlan fundRepayPlan) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundRepayPlan fundRepayPlan) throws ServiceException;
	
	public void auto_insert(FundBase fundBase) throws ServiceException;
	//获取一段时间内金额
	public double getBalByDate(FundRepayPlan FundRepayPlan) throws ServiceException;

}