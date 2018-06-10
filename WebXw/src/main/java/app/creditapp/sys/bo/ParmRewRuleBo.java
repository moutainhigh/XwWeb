package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.ParmRewRule;

/**
* Title: ParmRewRuleBo.java
* Description:
**/
public interface ParmRewRuleBo {

	public ParmRewRule getById(ParmRewRule parmRewRule) throws ServiceException;

	public void del(ParmRewRule parmRewRule) throws ServiceException;

	public void insert(ParmRewRule parmRewRule) throws ServiceException;

	public void update(ParmRewRule parmRewRule) throws ServiceException;
	
	public void updateRuleSts(ParmRewRule parmRewRule) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ParmRewRule parmRewRule) throws ServiceException;
	
	public List<ParmRewRule> findAll(ParmRewRule parmRewRule) throws ServiceException;

}