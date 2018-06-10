package  app.creditapp.aft.bo;

import java.util.List;
import java.util.Map;

import app.base.ServiceException;
import app.creditapp.aft.entity.AftRewReal;
import app.util.toolkit.Ipage;

/**
* Title: AftRewRealBo.java
* Description:
**/
public interface AftRewRealBo {

	public AftRewReal getById(AftRewReal aftRewReal) throws ServiceException;

	public void del(AftRewReal aftRewReal) throws ServiceException;

	public void insert(AftRewReal aftRewReal) throws ServiceException;

	public void update(AftRewReal aftRewReal) throws ServiceException;
	
	public Ipage findForAll(Ipage ipage, AftRewReal rewReal)throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftRewReal aftRewReal) throws ServiceException;
	
	public void updateSts(AftRewReal rewReal) throws ServiceException;
	
	public void updateDealRest(AftRewReal aftRewReal)throws ServiceException;
	
	public List<AftRewReal> findByList(AftRewReal aftRewReal) throws ServiceException;
	
	public List<AftRewReal> findByDateBetween(Map<String, String> paramMap)throws ServiceException;

}