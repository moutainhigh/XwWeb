package  app.creditapp.acc.option.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcReplanFormula;

/**
* Title: AcReplanFormulaBo.java
* Description:
**/
public interface AcReplanFormulaBo {

	public AcReplanFormula getById(AcReplanFormula acReplanFormula) throws ServiceException;

	public void del(AcReplanFormula acReplanFormula) throws ServiceException;

	public void insert(AcReplanFormula acReplanFormula) throws ServiceException;

	public void update(AcReplanFormula acReplanFormula) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcReplanFormula acReplanFormula) throws ServiceException;

}