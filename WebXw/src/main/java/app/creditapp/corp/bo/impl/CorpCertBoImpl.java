package  app.creditapp.corp.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpCertBo;
import app.creditapp.corp.dao.CorpCertDao;
import app.creditapp.corp.entity.CorpCert;
/**
* Title: CorpCertBoImplImpl.java
* Description:
**/
public class CorpCertBoImpl extends BaseService implements CorpCertBo {

	private CorpCertDao corpCertDao;

	public void del(CorpCert corpCert) throws ServiceException {
		try {
			corpCertDao.del(corpCert);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CorpCert corpCert)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) corpCertDao
					.getCount(corpCert) });// 初始化分页类
			corpCert.setStartNumAndEndNum (ipg);
			ipg.setResult(corpCertDao.findByPage(corpCert));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CorpCert getById(CorpCert corpCert) throws ServiceException {
		try {
			corpCert = corpCertDao.getById(corpCert);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpCert;
	}

	public void insert(CorpCert corpCert) throws ServiceException {
		try {
			/**
			 * 新增合作机构信息的资质编号是序列
			 */
			corpCert.setCertId(corpCertDao.getKey());
			corpCertDao.insert(corpCert);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CorpCert corpCert) throws ServiceException {
		try {
			corpCertDao.update(corpCert);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public CorpCertDao getCorpCertDao() {
		return corpCertDao;
	}

	public void setCorpCertDao(CorpCertDao corpCertDao) {
		this.corpCertDao = corpCertDao;
	}

	@Override
	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpCert corpCert)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) corpCertDao
					.getCountQuotaForCorp(corpCert) });// 初始化分页类
			corpCert.setStartNumAndEndNum(ipage);
			ipage.setResult(corpCertDao.findByPageQuotaForCorp(corpCert));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
}