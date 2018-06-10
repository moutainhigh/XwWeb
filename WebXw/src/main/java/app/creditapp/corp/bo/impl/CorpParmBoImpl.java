package  app.creditapp.corp.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.bo.CorpParmBo;
import app.creditapp.corp.dao.CorpParmDao;
import app.creditapp.corp.entity.CorpParm;
/**
* Title: CorpParmBoImplImpl.java
* Description:
**/
public class CorpParmBoImpl extends BaseService implements CorpParmBo {

	private CorpParmDao corpParmDao;

	public void del(CorpParm corpParm) throws ServiceException {
		try {
			corpParmDao.del(corpParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void delForBase(CorpParm corpParm) throws ServiceException {
		try {
			corpParmDao.delForBase(corpParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CorpParm corpParm)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) corpParmDao
					.getCount(corpParm) });// 初始化分页类
			corpParm.setStartNumAndEndNum (ipg);
			ipg.setResult(corpParmDao.findByPage(corpParm));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CorpParm getById(CorpParm corpParm) throws ServiceException {
		try {
			corpParm = corpParmDao.getById(corpParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return corpParm;
	}

	public void insert(CorpParm corpParm) throws ServiceException {
		try {
			/**
			 * 新增合作机构信息的配置参数ID是序列
			 */
			corpParm.setParmId(corpParmDao.getKey());
			corpParmDao.insert(corpParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CorpParm corpParm) throws ServiceException {
		try {
			corpParmDao.update(corpParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public CorpParmDao getCorpParmDao() {
		return corpParmDao;
	}

	public void setCorpParmDao(CorpParmDao corpParmDao) {
		this.corpParmDao = corpParmDao;
	}

	@Override
	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpParm corpParm)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) corpParmDao
					.getCountQuotaForCorp(corpParm) });// 初始化分页类
			corpParm.setStartNumAndEndNum(ipage);
			ipage.setResult(corpParmDao.findByPageQuotaForCorp(corpParm));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
	
	public List<Object> findListBySts(CorpParm corpParm) throws ServiceException {
		List<Object> corpParmList = null;
		try {
			corpParmList =  corpParmDao.findListBySts(corpParm);
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		return corpParmList;
	}
}