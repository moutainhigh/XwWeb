package  app.creditapp.corp.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.corp.entity.CorpGdinfo;

/**
* Title: CorpGdinfoBo.java
* Description:
**/
public interface CorpGdinfoBo {

	public CorpGdinfo getById(CorpGdinfo corpGdinfo) throws ServiceException;

	public void del(CorpGdinfo corpGdinfo) throws ServiceException;

	public void insert(CorpGdinfo corpGdinfo) throws ServiceException;

	public void update(CorpGdinfo corpGdinfo) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CorpGdinfo corpGdinfo) throws ServiceException;

	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpGdinfo corpGdinfo)throws ServiceException;

}