package  app.creditapp.corp.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpBaseBo;
import app.creditapp.corp.dao.CorpBaseDao;
import app.creditapp.corp.entity.CorpBase;
/**
* Title: CorpBaseBoImplImpl.java
* Description:
**/
public class CorpBaseBoImpl extends BaseService implements CorpBaseBo {

	private CorpBaseDao corpBaseDao;

	public void del(CorpBase corpBase) throws ServiceException {
		try {
			corpBaseDao.del(corpBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CorpBase corpBase)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) corpBaseDao
					.getCount(corpBase) });// 初始化分页类
			corpBase.setStartNumAndEndNum (ipg);
			ipg.setResult(corpBaseDao.findByPage(corpBase));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CorpBase getById(CorpBase corpBase) throws ServiceException {
		try {
			corpBase = corpBaseDao.getById(corpBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpBase;
	}

	public void insert(CorpBase corpBase) throws ServiceException {
		try {
			/**
			 * 新增合作机构信息的机构号是序列
			 */
			//合作机构号不使用序列，新增时手动输入
			//corpBase.setBrNo(corpBaseDao.getKey());
			
			corpBaseDao.insert(corpBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CorpBase corpBase) throws ServiceException {
		try {
			corpBaseDao.update(corpBase);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//增加合作机构 通用方法 判断信息 是否填写完整
	public int  getAllInf(String brNo) throws ServiceException {
		int count = 0;
		try {
			count = corpBaseDao.getAllInf(brNo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

	public CorpBaseDao getCorpBaseDao() {
		return corpBaseDao;
	}

	public void setCorpBaseDao(CorpBaseDao corpBaseDao) {
		this.corpBaseDao = corpBaseDao;
	}

	@Override
	public CorpBase getBybrNo(String brNo) throws ServiceException{
		CorpBase corpBase = null;
		try {
			corpBase = corpBaseDao.getByBrNo(brNo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpBase;
	}
}