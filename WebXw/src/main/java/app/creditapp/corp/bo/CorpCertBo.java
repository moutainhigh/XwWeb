package  app.creditapp.corp.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.corp.entity.CorpCert;

/**
* Title: CorpCertBo.java
* Description:
**/
public interface CorpCertBo {

	public CorpCert getById(CorpCert corpCert) throws ServiceException;

	public void del(CorpCert corpCert) throws ServiceException;

	public void insert(CorpCert corpCert) throws ServiceException;

	public void update(CorpCert corpCert) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CorpCert corpCert) throws ServiceException;

	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpCert corpCert)throws ServiceException;

}