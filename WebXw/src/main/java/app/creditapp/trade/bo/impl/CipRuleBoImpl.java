package  app.creditapp.trade.bo.impl;

import java.util.ArrayList;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.trade.bo.CipRuleBo;
import app.creditapp.trade.dao.CipRuleDao;
import app.creditapp.trade.entity.CipRule;
/**
* Title: CipRuleBoImplImpl.java
* Description:
**/
public class CipRuleBoImpl extends BaseService implements CipRuleBo {

	private CipRuleDao cipRuleDao;

	public void del(CipRule cipRule) throws ServiceException {
		try {
			cipRuleDao.del(cipRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CipRule cipRule)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cipRuleDao
					.getCount(cipRule) });// 初始化分页类
			cipRule.setStartNumAndEndNum (ipg);
			ipg.setResult(cipRuleDao.findByPage(cipRule));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CipRule getById(CipRule cipRule) throws ServiceException {
		try {
			cipRule = cipRuleDao.getById(cipRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cipRule;
	}

	public void insert(CipRule cipRule) throws ServiceException {
		try {
			cipRuleDao.insert(cipRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CipRule cipRule) throws ServiceException {
		try {
			cipRuleDao.update(cipRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	 * @方法说明:依据交易号获取接口字段规则
	 * @param tradeId
	 * @return
	 * @throws ServiceException 
	 * @see app.creditapp.trade.bo.CipRuleBo#getCipRuleList(java.lang.String)
	 * @修改说明:
	 */
	@Override
	public List<CipRule> getCipRuleList(String trade_id) throws ServiceException {
		List<CipRule> list = new ArrayList<CipRule>();
		try {
			list = cipRuleDao.getCipRuleList(trade_id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	public CipRuleDao getCipRuleDao() {
		return cipRuleDao;
	}

	public void setCipRuleDao(CipRuleDao cipRuleDao) {
		this.cipRuleDao = cipRuleDao;
	}

	/**
	 * @方法说明:
	 * @param cipRule
	 * @throws ServiceException 
	 * @see app.creditapp.trade.bo.CipRuleBo#delByTradeId(app.creditapp.trade.entity.CipRule)
	 * @修改说明:
	 */
	@Override
	public void delByTradeId(CipRule cipRule) throws ServiceException {
		try {
			cipRuleDao.delByTradeId(cipRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}