package  app.creditapp.acc.loan.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.loan.entity.AcLoanLog;

/**
* Title: AcLoanLogBo.java
* Description:
**/
public interface AcLoanLogBo {

	public AcLoanLog getById(AcLoanLog acLoanLog) throws ServiceException;

	public void del(AcLoanLog acLoanLog) throws ServiceException;

	public void insert(AcLoanLog acLoanLog) throws ServiceException;

	public void update(AcLoanLog acLoanLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLoanLog acLoanLog) throws ServiceException;

}