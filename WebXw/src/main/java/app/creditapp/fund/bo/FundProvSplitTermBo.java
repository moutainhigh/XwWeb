package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundProvSplitTerm;

/**
* Title: FundProvSplitTermBo.java
* Description:
**/
public interface FundProvSplitTermBo {

	public FundProvSplitTerm getById(FundProvSplitTerm fundProvSplitTerm) throws ServiceException;
	
	public FundProvSplitTerm Manager_getById(FundProvSplitTerm fundProvSplitTerm) throws ServiceException;

	public void del(FundProvSplitTerm fundProvSplitTerm) throws ServiceException;

	public void insert(FundProvSplitTerm fundProvSplitTerm) throws ServiceException;

	public void update(FundProvSplitTerm fundProvSplitTerm) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundProvSplitTerm fundProvSplitTerm) throws ServiceException;
	
	public Ipage Manager_findByPage(Ipage ipg, FundProvSplitTerm fundProvSplitTerm) throws ServiceException;

}