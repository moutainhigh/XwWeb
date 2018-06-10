package  app.creditapp.trade.dao.impl;

import java.util.ArrayList;
import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.trade.dao.CipRuleDao;
import app.creditapp.trade.entity.CipRule;
/**
* Title: CipRuleDaoImpl.java
* Description:
**/
public class CipRuleDaoImpl extends BaseIbatisDao implements CipRuleDao {

	public void del(CipRule cipRule) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CipRule.del", cipRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CipRule> findByPage(CipRule cipRule) throws DAOException {
		List cipRuleList = null;
		try {
			cipRuleList = getSqlMapClientTemplate().queryForList(
					"CipRule.findByPage", cipRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cipRuleList;
	}

	public CipRule getById(CipRule cipRule) throws DAOException {
		try {
			cipRule = (CipRule) getSqlMapClientTemplate()
					.queryForObject("CipRule.getById", cipRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cipRule;
	}

	public int getCount(CipRule cipRule) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CipRule.findPage.count", cipRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CipRule cipRule) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CipRule.insert",
					cipRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CipRule cipRule) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CipRule.update",
					cipRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * @方法说明:依据交易号获取接口字段规则
	 * @param tradeId
	 * @return
	 * @throws DAOException 
	 * @see app.creditapp.trade.dao.CipRuleDao#getCipRuleList(java.lang.String)
	 * @修改说明:
	 */
	@Override
	public List<CipRule> getCipRuleList(String trade_id) throws DAOException {
		List<CipRule> list = new ArrayList<CipRule>();
		try {
			list = getSqlMapClientTemplate().queryForList("CipRule.getCipRuleList", trade_id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return list;
	}

	/**
	 * @方法说明:
	 * @param cipRule 
	 * @see app.creditapp.trade.dao.CipRuleDao#delByTradeId(app.creditapp.trade.entity.CipRule)
	 * @修改说明:
	 */
	@Override
	public void delByTradeId(CipRule cipRule) throws DAOException  {
		try {
			getSqlMapClientTemplate().delete("CipRule.delByTradeId", cipRule);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}