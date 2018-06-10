package  app.creditapp.acc.option.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcFeeFormula;

/**
* Title: AcFeeFormulaBo.java
* Description:
**/
public interface AcFeeFormulaBo {

	public AcFeeFormula getById(AcFeeFormula acFeeFormula) throws ServiceException;

	public void del(AcFeeFormula acFeeFormula) throws ServiceException;

	public void insert(AcFeeFormula acFeeFormula) throws ServiceException;

	public void update(AcFeeFormula acFeeFormula) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcFeeFormula acFeeFormula) throws ServiceException;

}