package  app.creditapp.acc.option.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcFeeParm;

/**
* Title: AcFeeParmBo.java
* Description:
**/
public interface AcFeeParmBo {

	public AcFeeParm getById(AcFeeParm acFeeParm) throws ServiceException;

	public void del(AcFeeParm acFeeParm) throws ServiceException;

	public void insert(AcFeeParm acFeeParm) throws ServiceException;

	public void update(AcFeeParm acFeeParm) throws ServiceException;

	public void updateSts(AcFeeParm acFeeParm) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcFeeParm acFeeParm) throws ServiceException;

}