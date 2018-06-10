package  app.creditapp.aft.bo;

import java.util.List;
import java.util.Map;

import app.base.ServiceException;
import app.creditapp.aft.entity.AftRewFund;
import app.util.toolkit.Ipage;

/**
* Title: AftRewFundBo.java
* Description:
**/
public interface AftRewFundBo {

	public AftRewFund getById(AftRewFund aftRewFund) throws ServiceException;

	public void del(AftRewFund aftRewFund) throws ServiceException;

	public void insert(AftRewFund aftRewFund) throws ServiceException;

	public void update(AftRewFund aftRewFund) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftRewFund aftRewFund) throws ServiceException;
	public List<AftRewFund> findByList(AftRewFund aftRewFund) throws ServiceException;

	public void updateSts(AftRewFund rewFund) throws ServiceException;

	public void updateDealRest(AftRewFund rewFund)throws ServiceException;

	public Ipage findForAll(Ipage ipage, AftRewFund rewFund)throws ServiceException;

	public List<AftRewFund> findByDateBetween(Map<String, String> paramMap)throws ServiceException;
}