package  app.creditapp.corp.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.corp.entity.CorpCont;

/**
* Title: CorpContBo.java
* Description:
**/
public interface CorpContBo {

	public CorpCont getById(CorpCont corpCont) throws ServiceException;

	public void del(CorpCont corpCont) throws ServiceException;

	public void insert(CorpCont corpCont) throws ServiceException;

	public void update(CorpCont corpCont) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CorpCont corpCont) throws ServiceException;

	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpCont corpCont) throws ServiceException;
	
	public int  getCount(CorpCont corpCont) throws ServiceException;

}