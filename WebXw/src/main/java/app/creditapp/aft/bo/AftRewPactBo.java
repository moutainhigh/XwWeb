package  app.creditapp.aft.bo;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.aft.entity.AftRewPact;
import app.util.toolkit.Ipage;

/**
* Title: AftRewPactBo.java
* Description:
**/
public interface AftRewPactBo {

	public AftRewPact getById(AftRewPact aftRewPact) throws ServiceException;

	public void del(AftRewPact aftRewPact) throws ServiceException;

	public void insert(AftRewPact aftRewPact) throws ServiceException;
	//分账失败插入aftRewPact表
	public void insertForSplit(AftRewPact aftRewPact) throws ServiceException;

	public void update(AftRewPact aftRewPact) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftRewPact aftRewPact) throws ServiceException;
	public Ipage findForAll(Ipage ipg, AftRewPact aftRewPact) throws ServiceException;

	public List<AftRewPact> findToList(AftRewPact aftRewPact)throws ServiceException;
	public List<AftRewPact> findForCollect(AftRewPact aftRewPact) throws ServiceException;
	public List<AftRewPact> findForCollectBetween(Map<String, String> paramMap) throws ServiceException;
	
	public void updateSts(AftRewPact aftRewPact) throws ServiceException;

	public void updateDealRest(AftRewPact rewPact)throws ServiceException;

	public List<AftRewPact> findByDateBetween(Map<String, String> paramMap)throws ServiceException;
	
	public String getRewNo(String RewName)throws ServiceException;
	//根据合同号查询
	public AftRewPact getByPactNo(AftRewPact aftRewPact) throws ServiceException;
}