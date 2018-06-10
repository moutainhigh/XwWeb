package  app.creditapp.ln.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.ln.entity.LnApprIdea;

/**
* Title: LnApprIdeaBo.java
* Description:
**/
public interface LnApprIdeaBo {

	public LnApprIdea getById(LnApprIdea lnApprIdea) throws ServiceException;

	public void del(LnApprIdea lnApprIdea) throws ServiceException;

	public void insert(LnApprIdea lnApprIdea) throws ServiceException;

	public void update(LnApprIdea lnApprIdea) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnApprIdea lnApprIdea) throws ServiceException;
	public Ipage findByPageForRead(Ipage ipg, LnApprIdea lnApprIdea) throws ServiceException;
	
	public List<LnApprIdea> findByPageForAppId(LnApprIdea lnApprIdea) throws ServiceException;

}