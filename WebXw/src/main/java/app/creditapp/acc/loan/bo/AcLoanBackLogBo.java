package  app.creditapp.acc.loan.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.loan.entity.AcLoanBackLog;

/**
* Title: AcLoanBackLogBo.java
* Description:
**/
public interface AcLoanBackLogBo {

	public AcLoanBackLog getById(AcLoanBackLog acLoanBackLog) throws ServiceException;

	public void del(AcLoanBackLog acLoanBackLog) throws ServiceException;

	public void insert(AcLoanBackLog acLoanBackLog) throws ServiceException;

	public void update(AcLoanBackLog acLoanBackLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLoanBackLog acLoanBackLog) throws ServiceException;

}