package  app.creditapp.cif.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.cif.entity.CifPersCareer;

/**
* Title: CifPersCareerBo.java
* Description:
**/
public interface CifPersCareerBo {

	public CifPersCareer getById(CifPersCareer cifPersCareer) throws ServiceException;

	public void del(CifPersCareer cifPersCareer) throws ServiceException;

	public void insert(CifPersCareer cifPersCareer) throws ServiceException;

	public void update(CifPersCareer cifPersCareer) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CifPersCareer cifPersCareer) throws ServiceException;

	public Ipage findByPageQuotaForCif(Ipage ipage, CifPersCareer cifPersCareer) throws ServiceException;

	public CifPersCareer findForCif(CifPersCareer cifPersCareer) throws ServiceException;

}