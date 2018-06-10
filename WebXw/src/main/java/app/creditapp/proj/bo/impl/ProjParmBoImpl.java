package  app.creditapp.proj.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.proj.bo.ProjParmBo;
import app.creditapp.proj.dao.ProjParmDao;
import app.creditapp.proj.entity.ProjParm;
import app.util.toolkit.Ipage;
/**
* Title: ProjParmBoImplImpl.java
* Description:
**/
public class ProjParmBoImpl extends BaseService implements ProjParmBo {

	private ProjParmDao projParmDao;

	public void del(ProjParm projParm) throws ServiceException {
		try {
			projParmDao.del(projParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, ProjParm projParm)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) projParmDao
					.getCount(projParm) });// 初始化分页类
			projParm.setStartNumAndEndNum (ipg);
			ipg.setResult(projParmDao.findByPage(projParm));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public List<Object> findListBySts(String sts) throws ServiceException {
		List<Object> projParmList = null;
		try {
			projParmList = projParmDao.findListBySts(sts);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projParmList;
	}

	public ProjParm getById(ProjParm projParm) throws ServiceException {
		try {
			projParm = projParmDao.getById(projParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return projParm;
	}

	public void insert(ProjParm projParm) throws ServiceException {
		try {
			/**
			 * 新增项目信息的配置参数ID是序列
			 */
			//projParm.setParmId(projParmDao.getKey());
			projParmDao.insert(projParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(ProjParm projParm) throws ServiceException {
		try {
			projParmDao.update(projParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public ProjParmDao getProjParmDao() {
		return projParmDao;
	}

	public void setProjParmDao(ProjParmDao projParmDao) {
		this.projParmDao = projParmDao;
	}

	@Override
	public Ipage findByPageQuotaForCorp(Ipage ipage, ProjParm projParm)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) projParmDao
					.getCountQuotaForCorp(projParm) });// 初始化分页类
			projParm.setStartNumAndEndNum(ipage);
			ipage.setResult(projParmDao.findByPageQuotaForCorp(projParm));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
	public int count(ProjParm projParm) throws ServiceException {
		   int count=0;
		try {
			count = projParmDao.getCount(projParm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
	}

}