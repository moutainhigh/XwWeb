package  app.creditapp.proj.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.proj.entity.ProjAcctLst;

/**
* Title: ProjAcctLstBo.java
* Description:
**/
public interface ProjAcctLstBo {

	public ProjAcctLst getById(ProjAcctLst projAcctLst) throws ServiceException;

	public void del(ProjAcctLst projAcctLst) throws ServiceException;

	public void insert(ProjAcctLst projAcctLst) throws ServiceException;

	public void update(ProjAcctLst projAcctLst) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ProjAcctLst projAcctLst) throws ServiceException;

	public Ipage findByPageQuotaForCorp(Ipage ipage, ProjAcctLst projAcctLst)throws ServiceException;

}