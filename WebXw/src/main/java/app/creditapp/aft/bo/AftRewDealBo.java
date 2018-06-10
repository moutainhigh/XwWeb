package  app.creditapp.aft.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.aft.entity.AftRewDeal;

/**
* Title: AftRewDealBo.java
* Description:
**/
public interface AftRewDealBo {

	public AftRewDeal getById(AftRewDeal aftRewDeal) throws ServiceException;

	public void del(AftRewDeal aftRewDeal) throws ServiceException;

	public void insert(AftRewDeal aftRewDeal) throws ServiceException;

	public void update(AftRewDeal aftRewDeal) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftRewDeal aftRewDeal) throws ServiceException;

}