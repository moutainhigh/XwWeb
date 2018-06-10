package  app.creditapp.cred.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.cred.entity.CifBlackApp;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.wkf.entity.WkfParm;

/**
* Title: CifBlackAppBo.java
* Description:
**/
public interface CifBlackAppBo {

	public CifBlackApp getById(CifBlackApp cifBlackApp) throws ServiceException;

	public void del(CifBlackApp cifBlackApp) throws ServiceException;

	public void insert(CifBlackApp cifBlackApp) throws ServiceException;

	public void update(CifBlackApp cifBlackApp) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CifBlackApp cifBlackApp) throws ServiceException;
	
	public String doSubmit(WkfParm parm ,CifBlackApp cifBlackApp,String opNo, String brNo) throws ServiceException;

}