package  app.creditapp.acc.option.bo;

import java.util.List;

import accounting.domain.fee.AcChrgLog;
import app.base.ServiceException;
import app.creditapp.acc.loan.entity.AcLnMst;
import app.creditapp.acc.option.entity.AcDamFormula;
import app.creditapp.aft.entity.AftAdvpay;
import app.util.toolkit.Ipage;

/**
* Title: AcDamFormulaBo.java
* Description:
**/
public interface AcDamFormulaBo {

	public AcDamFormula getById(AcDamFormula acDamFormula) throws ServiceException;

	public void del(AcDamFormula acDamFormula) throws ServiceException;

	public void insert(AcDamFormula acDamFormula) throws ServiceException;

	public void update(AcDamFormula acDamFormula) throws ServiceException;

	public void updateSts(AcDamFormula acDamFormula) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcDamFormula acDamFormula) throws ServiceException;
	
	public double getDamAmtInAftAdvpay(AftAdvpay aftAdvpay,AcLnMst acLnMst) throws ServiceException;
	
	public List<AcChrgLog> getDamAmtInAftAdvpay(AftAdvpay aftAdvpay,AcLnMst acLnMst,String traceNo) throws ServiceException;

}