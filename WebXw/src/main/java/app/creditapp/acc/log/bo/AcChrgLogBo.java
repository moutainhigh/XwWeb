package  app.creditapp.acc.log.bo;

import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.log.entity.AcChrgLog;

/**
* Title: AcChrgLogBo.java
* Description:
**/
public interface AcChrgLogBo {

	public AcChrgLog getById(AcChrgLog acChrgLog) throws ServiceException;
	
	public double getLoFeeAmt(AcChrgLog acChrgLog) throws ServiceException;

	public void del(AcChrgLog acChrgLog) throws ServiceException;

	public AcChrgLog getAllAmt(AcChrgLog acChrgLog) throws ServiceException;
	
	public void insert(AcChrgLog acChrgLog) throws ServiceException;

	public void update(AcChrgLog acChrgLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcChrgLog acChrgLog) throws ServiceException;

}