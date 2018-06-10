package  app.creditapp.gage.bo.impl;

import java.util.HashMap;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.gage.bo.LnGageBo;
import app.creditapp.gage.dao.LnGageDao;
import app.creditapp.gage.entity.LnGage;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;
/**
* Title: LnGageBoImplImpl.java
* Description:
**/
public class LnGageBoImpl extends BaseService implements LnGageBo {

	private LnGageDao lnGageDao;

	public void del(LnGage lnGage) throws ServiceException {
		try {
			lnGageDao.del(lnGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnGage lnGage)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnGageDao
					.getCount(lnGage) });// 初始化分页类
			lnGage.setStartNumAndEndNum (ipg);
			ipg.setResult(lnGageDao.findByPage(lnGage));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public LnGage getById(LnGage lnGage) throws ServiceException {
		try {
			lnGage = lnGageDao.getById(lnGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnGage;
	}

	public void insert(LnGage lnGage) throws ServiceException {
		try {
			lnGageDao.insert(lnGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnGage lnGage) throws ServiceException {
		try {
			lnGageDao.update(lnGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public LnGageDao getLnGageDao() {
		return lnGageDao;
	}

	public void setLnGageDao(LnGageDao lnGageDao) {
		this.lnGageDao = lnGageDao;
	}

	@Override
	public Ipage findByPageQuotaForCif(Ipage ipage, LnGage lnGage)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) lnGageDao.getCountQuotaForCif(lnGage) });// 初始化分页类
			lnGage.setStartNumAndEndNum(ipage);
			ipage.setResult(lnGageDao.findByPageQuotaForCif(lnGage));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	@Override
	public Ipage findByPageQuotaForLn(Ipage ipage, LnGage lnGage)
			throws ServiceException {
	try {
		ipage.initPageCounts(new Integer[] { (Integer) lnGageDao
				.getCountQuotaForLn(lnGage) });// 初始化分页类
		lnGage.setStartNumAndEndNum(ipage);
		ipage.setResult(lnGageDao.findByPageQuotaForLn(lnGage));
	} catch (Exception e) {
		throw new ServiceException(e.getMessage());
	}
	return ipage;
	}
	//查询所有待解押产品信息
	@Override
	public Ipage findlistBygegeSts(Ipage ipage,LnGage lnGage) throws ServiceException {
		List lnGageList = null;
		try {
			ipage.initPageCounts(new Integer[] { (Integer) lnGageDao
					.findlistBygegeStsCount() });// 初始化分页类
			 HashMap<String,Object> map = (HashMap<String,Object>)IbatisUtils.getEntityPropertyMap(ipage,lnGage); 
		      map.put("startNum", ipage.getStartRow());
		      map.put("endNum", ipage.getEndNum());
//			lnGageList = lnGageDao.findlistBygegeSts(map);
			ipage.setResult(lnGageDao.findlistBygegeSts(map)); 
		} catch (Exception e) {
			
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	@Override
	public void updateSts(LnGage lnGage) throws ServiceException {
		try {
			lnGageDao.updateSts(lnGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		
	}
	/**
	 * 根据押品ID查询申请ID
	 */
	@Override
	public String findAppIdByGageId(LnGage lnGage) throws ServiceException {
		String  appId = "";
		try {
			appId = lnGageDao.findAppIdByGageId(lnGage);
		} catch (Exception e) {
			
			throw new ServiceException(e.getMessage());
		}
		return appId;
	}
}