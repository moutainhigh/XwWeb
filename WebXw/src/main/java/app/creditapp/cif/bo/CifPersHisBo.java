package  app.creditapp.cif.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.cif.entity.CifPersHis;

/**
* Title: CifPersHisBo.java
* Description:
**/
public interface CifPersHisBo {

	public CifPersHis getById(CifPersHis cifPersHis) throws ServiceException;

	public void del(CifPersHis cifPersHis) throws ServiceException;

	public void insert(CifPersHis cifPersHis) throws ServiceException;

	public void update(CifPersHis cifPersHis) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CifPersHis cifPersHis) throws ServiceException;

	public Ipage findByPageQuotaForCif(Ipage ipage, CifPersHis cifPersHis) throws ServiceException;

}