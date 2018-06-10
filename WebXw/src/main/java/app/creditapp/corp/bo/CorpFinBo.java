package  app.creditapp.corp.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.corp.entity.CorpFin;

/**
* Title: CorpFinBo.java
* Description:
**/
public interface CorpFinBo {

	public CorpFin getById(CorpFin corpFin) throws ServiceException;

	public void del(CorpFin corpFin) throws ServiceException;

	public void insert(CorpFin corpFin) throws ServiceException;

	public void update(CorpFin corpFin) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CorpFin corpFin) throws ServiceException;

	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpFin corpFin) throws ServiceException;

}