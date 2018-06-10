package  app.creditapp.corp.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.corp.entity.CorpTaRel;

/**
* Title: CorpTaRelBo.java
* Description:
**/
public interface CorpTaRelBo {

	public CorpTaRel getById(CorpTaRel corpTaRel) throws ServiceException;

	public void del(CorpTaRel corpTaRel) throws ServiceException;

	public void insert(CorpTaRel corpTaRel) throws ServiceException;

	public void update(CorpTaRel corpTaRel) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CorpTaRel corpTaRel) throws ServiceException;

}