package  app.creditapp.cif.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.cif.entity.CifBlack;
import app.creditapp.pact.entity.LnPact;

/**
* Title: CifBlackBo.java
* Description:
**/
public interface CifBlackBo {

	public CifBlack getById(CifBlack cifBlack) throws ServiceException;

	public void del(CifBlack cifBlack) throws ServiceException;

	public void insert(CifBlack cifBlack) throws ServiceException;

	public void update(CifBlack cifBlack) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CifBlack cifBlack) throws ServiceException;
	
	public Ipage findByPageForTask(Ipage ipg, CifBlack cifBlack) throws ServiceException;
	
	public Ipage findByPageForAppr(Ipage ipg, CifBlack cifBlack) throws ServiceException;


}