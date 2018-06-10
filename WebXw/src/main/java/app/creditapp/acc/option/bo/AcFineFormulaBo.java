package  app.creditapp.acc.option.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcFineFormula;

/**
* Title: AcFineFormulaBo.java
* Description:
**/
public interface AcFineFormulaBo {

	public AcFineFormula getById(AcFineFormula acFineFormula) throws ServiceException;

	public void del(AcFineFormula acFineFormula) throws ServiceException;

	public void insert(AcFineFormula acFineFormula) throws ServiceException;

	public void update(AcFineFormula acFineFormula) throws ServiceException;
	
	public void updateReplanSts(AcFineFormula acFineFormula) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcFineFormula acFineFormula) throws ServiceException;

}