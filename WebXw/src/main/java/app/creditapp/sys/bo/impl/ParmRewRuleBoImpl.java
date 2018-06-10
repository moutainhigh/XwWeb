package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sys.bo.ParmRewRuleBo;
import app.creditapp.sys.dao.ParmRewRuleDao;
import app.creditapp.sys.entity.ParmRewRule;
import app.util.toolkit.Ipage;
/**
* Title: ParmRewRuleBoImplImpl.java
* Description:
**/
public class ParmRewRuleBoImpl extends BaseService implements ParmRewRuleBo {

	private ParmRewRuleDao parmRewRuleDao;

	public void del(ParmRewRule parmRewRule) throws ServiceException {
		try {
			parmRewRuleDao.del(parmRewRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ParmRewRule parmRewRule)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) parmRewRuleDao
					.getCount(parmRewRule) });// 初始化分页类
			parmRewRule.setStartNumAndEndNum (ipg);
			ipg.setResult(parmRewRuleDao.findByPage(parmRewRule));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public ParmRewRule getById(ParmRewRule parmRewRule) throws ServiceException {
		try {
			parmRewRule = parmRewRuleDao.getById(parmRewRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return parmRewRule;
	}

	public void insert(ParmRewRule parmRewRule) throws ServiceException {
		try {
			parmRewRuleDao.insert(parmRewRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ParmRewRule parmRewRule) throws ServiceException {
		try {
			parmRewRuleDao.update(parmRewRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateRuleSts(ParmRewRule parmRewRule) throws ServiceException {
		try {
			parmRewRuleDao.updateRuleSts(parmRewRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<ParmRewRule> findAll(ParmRewRule parmRewRule) throws ServiceException{
		List<ParmRewRule> parmRewRuleList = null;
		try {
			parmRewRuleList = parmRewRuleDao.findAll(parmRewRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return parmRewRuleList;
	}
	
	public ParmRewRuleDao getParmRewRuleDao() {
		return parmRewRuleDao;
	}

	public void setParmRewRuleDao(ParmRewRuleDao parmRewRuleDao) {
		this.parmRewRuleDao = parmRewRuleDao;
	}
}