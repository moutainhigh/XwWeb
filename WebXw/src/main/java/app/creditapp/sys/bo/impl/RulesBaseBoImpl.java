package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.RulesBaseBo;
import app.creditapp.sys.dao.RulesBaseDao;
import app.creditapp.sys.entity.RulesBase;
/**
* Title: RulesBaseBoImplImpl.java
* Description:
**/
public class RulesBaseBoImpl extends BaseService implements RulesBaseBo {

	private RulesBaseDao rulesBaseDao;

	public void del(RulesBase rulesBase) throws ServiceException {
		try {
			rulesBaseDao.del(rulesBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, RulesBase rulesBase)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) rulesBaseDao
					.getCount(rulesBase) });// 初始化分页类
			rulesBase.setStartNumAndEndNum (ipg);
			ipg.setResult(rulesBaseDao.findByPage(rulesBase));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public RulesBase getById(RulesBase rulesBase) throws ServiceException {
		try {
			rulesBase = rulesBaseDao.getById(rulesBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rulesBase;
	}

	public List<RulesBase> findById(RulesBase rulesBase) throws ServiceException {
		List<RulesBase> rulesBaseList = null;
		try {
			rulesBaseList = rulesBaseDao.findById(rulesBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return rulesBaseList;
	}
	
	public void insert(RulesBase rulesBase) throws ServiceException {
		try {
			rulesBaseDao.insert(rulesBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(RulesBase rulesBase) throws ServiceException {
		try {
			rulesBaseDao.update(rulesBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public RulesBaseDao getRulesBaseDao() {
		return rulesBaseDao;
	}

	public void setRulesBaseDao(RulesBaseDao rulesBaseDao) {
		this.rulesBaseDao = rulesBaseDao;
	}
}