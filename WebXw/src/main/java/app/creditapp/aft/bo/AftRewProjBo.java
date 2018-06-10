package  app.creditapp.aft.bo;

import java.util.List;
import java.util.Map;

import app.base.ServiceException;
import app.creditapp.aft.entity.AftRewProj;
import app.util.toolkit.Ipage;

/**
* Title: AftRewProjBo.java
* Description:
**/
public interface AftRewProjBo {

	public AftRewProj getById(AftRewProj aftRewProj) throws ServiceException;

	public void del(AftRewProj aftRewProj) throws ServiceException;

	public void insert(AftRewProj aftRewProj) throws ServiceException;

	public void update(AftRewProj aftRewProj) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftRewProj aftRewProj) throws ServiceException;
	public List<AftRewProj> findByList(AftRewProj aftRewProj) throws ServiceException;

	public void updateSts(AftRewProj rewProj) throws ServiceException;

	public void updateDealRest(AftRewProj rewProj) throws ServiceException;

	public Ipage findForAll(Ipage ipage, AftRewProj rewProj);

	public List<AftRewProj> findByDateBetween(Map<String, String> paramMap);

}