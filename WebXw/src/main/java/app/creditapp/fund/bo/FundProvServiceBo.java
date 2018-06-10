package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundProvService;

/**
* Title: FundProvServiceBo.java
* Description:
**/
public interface FundProvServiceBo {

	public FundProvService getById(FundProvService fundProvService) throws ServiceException;

	public void del(FundProvService fundProvService) throws ServiceException;

	public void insert(FundProvService fundProvService) throws ServiceException;

	public void update(FundProvService fundProvService) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundProvService fundProvService) throws ServiceException;

}