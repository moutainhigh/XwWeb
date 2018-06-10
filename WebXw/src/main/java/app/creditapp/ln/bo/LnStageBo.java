package  app.creditapp.ln.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.ln.entity.LnStage;

/**
* Title: LnStageBo.java
* Description:
**/
public interface LnStageBo {

	public LnStage getById(LnStage lnStage) throws ServiceException;

	public void del(LnStage lnStage) throws ServiceException;

	public void insert(LnStage lnStage) throws ServiceException;

	public void update(LnStage lnStage) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnStage lnStage) throws ServiceException;
	
	public void updatests(String appId,String columns,String value) throws ServiceException;

}