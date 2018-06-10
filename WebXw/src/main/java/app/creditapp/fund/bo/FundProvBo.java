package  app.creditapp.fund.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundProv;

/**
* Title: FundProvBo.java
* Description:
**/
public interface FundProvBo {

	public FundProv getById(FundProv fundProv) throws ServiceException;

	public void del(FundProv fundProv) throws ServiceException;

	public void insert(FundProv fundProv) throws ServiceException;

	public void update(FundProv fundProv) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundProv fundProv) throws ServiceException;

}