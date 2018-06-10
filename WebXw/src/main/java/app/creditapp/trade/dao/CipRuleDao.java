package  app.creditapp.trade.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.trade.entity.CipRule;
/**
* Title: CipRuleDao.java
* Description:
**/
public interface CipRuleDao {

	public CipRule getById(CipRule cipRule) throws DAOException;

	public void del(CipRule cipRule) throws DAOException;

	public void insert(CipRule cipRule) throws DAOException;

	public void update(CipRule cipRule) throws DAOException;
	
	public int getCount(CipRule cipRule) throws DAOException;

	public List<CipRule > findByPage(CipRule cipRule) throws DAOException;

	public List<CipRule> getCipRuleList(String trade_id) throws DAOException;

	public void delByTradeId(CipRule cipRule) throws DAOException;

}