package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.FundServiceRate;

/**
* Title: FundServiceRateBo.java
* Description:
**/
public interface FundServiceRateBo {

	public FundServiceRate getById(FundServiceRate fundServiceRate) throws ServiceException;

	public void del(FundServiceRate fundServiceRate) throws ServiceException;

	public void insert(FundServiceRate fundServiceRate) throws ServiceException;

	public void update(FundServiceRate fundServiceRate) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundServiceRate fundServiceRate) throws ServiceException;

}