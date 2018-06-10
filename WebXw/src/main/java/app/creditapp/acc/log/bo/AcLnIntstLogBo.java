package  app.creditapp.acc.log.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.log.entity.AcLnIntstLog;

/**
* Title: AcLnIntstLogBo.java
* Description:
**/
public interface AcLnIntstLogBo {

	public AcLnIntstLog getById(AcLnIntstLog acLnIntstLog) throws ServiceException;

	public void del(AcLnIntstLog acLnIntstLog) throws ServiceException;

	public void insert(AcLnIntstLog acLnIntstLog) throws ServiceException;

	public void update(AcLnIntstLog acLnIntstLog) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLnIntstLog acLnIntstLog) throws ServiceException;

}