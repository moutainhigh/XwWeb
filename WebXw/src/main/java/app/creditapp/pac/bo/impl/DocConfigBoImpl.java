package  app.creditapp.pac.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.pac.bo.DocConfigBo;
import app.creditapp.pac.dao.DocConfigDao;
import app.creditapp.pac.entity.DocConfig;
/**
* Title: DocConfigBoImplImpl.java
* Description:
**/
public class DocConfigBoImpl extends BaseService implements DocConfigBo {

	private DocConfigDao docConfigDao;
	
	public DocConfig findDocTypeNo(DocConfig docConfig) throws ServiceException {
		try {
			docConfig = docConfigDao.findDocTypeNo(docConfig);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return docConfig;
	}
	
	public void del(DocConfig docConfig) throws ServiceException {
		try {
			docConfigDao.del(docConfig);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, DocConfig docConfig)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) docConfigDao
					.getCount(docConfig) });// 初始化分页类
			docConfig.setStartNumAndEndNum (ipg);
			ipg.setResult(docConfigDao.findByPage(docConfig));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public DocConfig getById(DocConfig docConfig) throws ServiceException {
		try {
			docConfig = docConfigDao.getById(docConfig);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return docConfig;
	}

	public void insert(DocConfig docConfig) throws ServiceException {
		try {
			docConfig.setDocTypeId(docConfigDao.getKey());
			docConfigDao.insert(docConfig);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(DocConfig docConfig) throws ServiceException {
		try {
			docConfigDao.update(docConfig);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public DocConfigDao getDocConfigDao() {
		return docConfigDao;
	}

	
	public void setDocConfigDao(DocConfigDao docConfigDao) {
		this.docConfigDao = docConfigDao;
	}

	
}
