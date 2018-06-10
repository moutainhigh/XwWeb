package  app.creditapp.corp.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpGdinfoBo;
import app.creditapp.corp.dao.CorpGdinfoDao;
import app.creditapp.corp.entity.CorpGdinfo;
/**
* Title: CorpGdinfoBoImplImpl.java
* Description:
**/
public class CorpGdinfoBoImpl extends BaseService implements CorpGdinfoBo {

	private CorpGdinfoDao corpGdinfoDao;

	public void del(CorpGdinfo corpGdinfo) throws ServiceException {
		try {
			corpGdinfoDao.del(corpGdinfo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CorpGdinfo corpGdinfo)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) corpGdinfoDao
					.getCount(corpGdinfo) });// 初始化分页类
			corpGdinfo.setStartNumAndEndNum (ipg);
			ipg.setResult(corpGdinfoDao.findByPage(corpGdinfo));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CorpGdinfo getById(CorpGdinfo corpGdinfo) throws ServiceException {
		try {
			corpGdinfo = corpGdinfoDao.getById(corpGdinfo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpGdinfo;
	}

	public void insert(CorpGdinfo corpGdinfo) throws ServiceException {
		try {
			/**
			 * 新增合作机构信息的股东信息ID是序列
			 */
			corpGdinfo.setGdId(corpGdinfoDao.getKey());
			corpGdinfoDao.insert(corpGdinfo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CorpGdinfo corpGdinfo) throws ServiceException {
		try {
			corpGdinfoDao.update(corpGdinfo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public CorpGdinfoDao getCorpGdinfoDao() {
		return corpGdinfoDao;
	}

	public void setCorpGdinfoDao(CorpGdinfoDao corpGdinfoDao) {
		this.corpGdinfoDao = corpGdinfoDao;
	}

	@Override
	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpGdinfo corpGdinfo)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) corpGdinfoDao
					.getCountQuotaForCorp(corpGdinfo) });// 初始化分页类
			corpGdinfo.setStartNumAndEndNum(ipage);
			ipage.setResult(corpGdinfoDao.findByPageQuotaForCorp(corpGdinfo));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
}