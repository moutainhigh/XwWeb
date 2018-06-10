package  app.creditapp.trade.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.trade.entity.CipRule;

/**
* Title: CipRuleBo.java
* Description:
**/
public interface CipRuleBo {

	public CipRule getById(CipRule cipRule) throws ServiceException;

	public void del(CipRule cipRule) throws ServiceException;

	public void insert(CipRule cipRule) throws ServiceException;

	public void update(CipRule cipRule) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CipRule cipRule) throws ServiceException;
	
	public List<CipRule> getCipRuleList(String trade_id) throws ServiceException;

	public void delByTradeId(CipRule cipRule) throws ServiceException;

}