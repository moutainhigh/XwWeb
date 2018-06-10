package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.RulesBase;

/**
* Title: RulesBaseBo.java
* Description:
**/
public interface RulesBaseBo {

	public RulesBase getById(RulesBase rulesBase) throws ServiceException;
	
	public List<RulesBase> findById(RulesBase rulesBase) throws ServiceException;

	public void del(RulesBase rulesBase) throws ServiceException;

	public void insert(RulesBase rulesBase) throws ServiceException;

	public void update(RulesBase rulesBase) throws ServiceException;

	public Ipage findByPage(Ipage ipg, RulesBase rulesBase) throws ServiceException;

}