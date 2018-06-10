package  app.creditapp.fund.bo;

import java.util.List;

import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.fund.entity.FundProvProj;

/**
* Title: FundProvProjBo.java
* Description:
**/
public interface FundProvProjBo {

	public FundProvProj getById(FundProvProj fundProvProj) throws ServiceException;

	public void del(FundProvProj fundProvProj) throws ServiceException;

	public FundProvProj insert(FundProvProj fundProvProj) throws ServiceException;

	public void update(FundProvProj fundProvProj) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundProvProj fundProvProj) throws ServiceException;

	public List<FundProvProj> findAll(FundProvProj fundProvProj) throws ServiceException;

	public String getProjNameByProjNo(String projNo) throws ServiceException;
}