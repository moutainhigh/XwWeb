package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundSplitDetail;

/**
* Title: FundSplitDetailBo.java
* Description:
**/
public interface FundSplitDetailBo {

	public FundSplitDetail getById(FundSplitDetail fundSplitDetail) throws ServiceException;

	public void del(FundSplitDetail fundSplitDetail) throws ServiceException;

	public void insert(FundSplitDetail fundSplitDetail) throws ServiceException;

	public void update(FundSplitDetail fundSplitDetail) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundSplitDetail fundSplitDetail) throws ServiceException;

}