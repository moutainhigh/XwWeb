package  app.creditapp.corp.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpContBo;
import app.creditapp.corp.dao.CorpContDao;
import app.creditapp.corp.entity.CorpCont;
/**
* Title: CorpContBoImplImpl.java
* Description:
**/
public class CorpContBoImpl extends BaseService implements CorpContBo {

	private CorpContDao corpContDao;

	public void del(CorpCont corpCont) throws ServiceException {
		try {
			corpContDao.del(corpCont);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public int  getCount(CorpCont corpCont) throws ServiceException {
		int count = 0;
		try {
			count = corpContDao.getCount(corpCont);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

	public Ipage findByPage(Ipage ipg, CorpCont corpCont)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) corpContDao
					.getCount(corpCont) });// 初始化分页类
			corpCont.setStartNumAndEndNum (ipg);
			ipg.setResult(corpContDao.findByPage(corpCont));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CorpCont getById(CorpCont corpCont) throws ServiceException {
		try {
			corpCont = corpContDao.getById(corpCont);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpCont;
	}

	public void insert(CorpCont corpCont) throws ServiceException {
		try {
			/**
			 * 新增合作机构信息的联系人编号是序列
			 */
			corpCont.setContNo(corpContDao.getKey());
			corpContDao.insert(corpCont);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CorpCont corpCont) throws ServiceException {
		try {
			corpContDao.update(corpCont);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public CorpContDao getCorpContDao() {
		return corpContDao;
	}

	public void setCorpContDao(CorpContDao corpContDao) {
		this.corpContDao = corpContDao;
	}

	@Override
	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpCont corpCont)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) corpContDao
					.getCountQuotaForCorp(corpCont) });// 初始化分页类
			corpCont.setStartNumAndEndNum(ipage);
			ipage.setResult(corpContDao.findByPageQuotaForCorp(corpCont));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
}