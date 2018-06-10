package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundDetail;

/**
* Title: FundDetailBo.java
* Description:
**/
public interface FundDetailBo {

	public FundDetail getById(FundDetail fundDetail) throws ServiceException;

	public void del(FundDetail fundDetail) throws ServiceException;

	public void insert(FundDetail fundDetail) throws ServiceException;

	public void update(FundDetail fundDetail) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundDetail fundDetail) throws ServiceException;

	public double getBalBytradeType(FundDetail fundDetail) throws ServiceException;
	
	//获取明细表的最迟日期
	public String getMaxDate(FundDetail fundDetail) throws ServiceException;
	
	//调用校验规则校验资金明细信息
	public String validate(FundDetail fundDetail) throws ServiceException;

}