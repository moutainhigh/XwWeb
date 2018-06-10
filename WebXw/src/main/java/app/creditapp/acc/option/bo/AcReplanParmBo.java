package  app.creditapp.acc.option.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcReplanParm;

/**
* Title: AcReplanParmBo.java
* Description:
**/
public interface AcReplanParmBo {

	public AcReplanParm getById(AcReplanParm acReplanParm) throws ServiceException;

	public void del(AcReplanParm acReplanParm) throws ServiceException;

	public void insert(AcReplanParm acReplanParm) throws ServiceException;

	public void update(AcReplanParm acReplanParm) throws ServiceException;

	public void updateReplanSts(AcReplanParm acReplanParm) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcReplanParm acReplanParm) throws ServiceException;

}