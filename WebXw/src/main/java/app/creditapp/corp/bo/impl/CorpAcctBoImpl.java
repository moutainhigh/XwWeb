package  app.creditapp.corp.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpAcctBo;
import app.creditapp.corp.dao.CorpAcctDao;
import app.creditapp.corp.entity.CorpAcct;
import app.creditapp.corp.entity.CorpBase;
/**
* Title: CorpAcctBoImplImpl.java
* Description:
**/
public class CorpAcctBoImpl extends BaseService implements CorpAcctBo {

	private CorpAcctDao corpAcctDao;

	public void del(CorpAcct corpAcct) throws ServiceException {
		try {
			corpAcctDao.del(corpAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public int getCount(CorpAcct corpAcct) throws ServiceException {
		int count = 0;
		try {
			count = corpAcctDao.getCount(corpAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	
	
	public void delForBase(CorpAcct corpAcct) throws ServiceException {
		try {
			corpAcctDao.delForBase(corpAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public CorpAcct getByIdFor5202(String acctNo) throws ServiceException{
		CorpAcct corpAcct = null;
		try {
			corpAcct = corpAcctDao.getByIdFor5202(acctNo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpAcct;
	}
	public Ipage findByPage(Ipage ipg, CorpAcct corpAcct)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) corpAcctDao
					.getCount(corpAcct) });// 初始化分页类
			corpAcct.setStartNumAndEndNum (ipg);
			ipg.setResult(corpAcctDao.findByPage(corpAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CorpAcct getById(CorpAcct corpAcct) throws ServiceException {
		try {
			corpAcct = corpAcctDao.getById(corpAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpAcct;
	}

	public void insert(CorpAcct corpAcct) throws ServiceException {
		try {
			/**
			 * 新增合作机构信息的账户号是序列
			 */
			corpAcct.setAcctId(corpAcctDao.getKey());
			corpAcctDao.insert(corpAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CorpAcct corpAcct) throws ServiceException {
		try {
			corpAcctDao.update(corpAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void updateAcctJS(CorpAcct corpAcct) throws ServiceException {
		try {
			corpAcctDao.updateAcctJS(corpAcct);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public CorpAcctDao getCorpAcctDao() {
		return corpAcctDao;
	}

	public void setCorpAcctDao(CorpAcctDao corpAcctDao) {
		this.corpAcctDao = corpAcctDao;
	}

	@Override
	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpAcct corpAcct)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) corpAcctDao
					.getCountQuotaForCorp(corpAcct) });// 初始化分页类
			corpAcct.setStartNumAndEndNum(ipage);
			ipage.setResult(corpAcctDao.findByPageQuotaForCorp(corpAcct));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
}