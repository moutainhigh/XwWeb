package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundSplitTerm;

import app.creditapp.fund.entity.FundSplitDetail;
/**
* Title: FundSplitTermBo.java
* Description:
**/
public interface FundSplitTermBo {

	public FundSplitTerm getById(FundSplitTerm fundSplitTerm) throws ServiceException;

	public void del(FundSplitTerm fundSplitTerm) throws ServiceException;

	public void insert(FundSplitTerm fundSplitTerm) throws ServiceException;

	public void update(FundSplitTerm fundSplitTerm) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundSplitTerm fundSplitTerm) throws ServiceException;
	
	public void auto_insert(FundSplitDetail fundSplitDetail) throws ServiceException;

}