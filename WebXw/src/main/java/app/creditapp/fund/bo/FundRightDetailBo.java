package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundRightDetail;

/**
* Title: FundRightDetailBo.java
* Description:
**/
public interface FundRightDetailBo {

	public FundRightDetail getById(FundRightDetail fundRightDetail) throws ServiceException;

	public void del(FundRightDetail fundRightDetail) throws ServiceException;

	public void insert(FundRightDetail fundRightDetail) throws ServiceException;

	public void update(FundRightDetail fundRightDetail) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundRightDetail fundRightDetail) throws ServiceException;

}