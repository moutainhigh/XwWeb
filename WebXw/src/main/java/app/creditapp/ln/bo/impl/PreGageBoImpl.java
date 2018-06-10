package  app.creditapp.ln.bo.impl;

import java.util.ArrayList;
import java.util.List;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.inf.entity.WsIn2004_1;
import app.creditapp.inf.entity.WsOut2006_1_1;
import app.creditapp.ln.bo.PreGageBo;
import app.creditapp.ln.dao.PreGageDao;
import app.creditapp.ln.entity.PreGage;
import app.util.toolkit.Ipage;
/**
* Title: PreGageBoImplImpl.java
* Description:
**/
public class PreGageBoImpl extends BaseService implements PreGageBo {

	private PreGageDao preGageDao;

	public void del(PreGage preGage) throws ServiceException {
		try {
			preGageDao.del(preGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, PreGage preGage)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) preGageDao
					.getCount(preGage) });// 初始化分页类
			preGage.setStartNumAndEndNum (ipg);
			ipg.setResult(preGageDao.findByPage(preGage));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public PreGage getById(PreGage preGage) throws ServiceException {
		try {
			preGage = preGageDao.getById(preGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return preGage;
	}
	
	//接口ws2006 wsout2006_1_1 结果（list<Gage>）

	public List<WsOut2006_1_1> ws2006_1_1list(PreGage preGage) throws ServiceException {
		List<WsOut2006_1_1> wsOut2006_1_1list = new  ArrayList<WsOut2006_1_1>();
		try {
			wsOut2006_1_1list = preGageDao.getws2006_1_1list(preGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wsOut2006_1_1list;
	}

	public void insert(PreGage preGage) throws ServiceException {
		try {
			preGageDao.insert(preGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void insertfor2004_1(WsIn2004_1 wsIn2004_1) throws ServiceException {
		try {
			preGageDao.insertfor2004_1(wsIn2004_1);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	

	public void update(PreGage preGage) throws ServiceException {
		try {
			preGageDao.update(preGage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public PreGageDao getPreGageDao() {
		return preGageDao;
	}

	public void setPreGageDao(PreGageDao preGageDao) {
		this.preGageDao = preGageDao;
	}

	@Override
	public Ipage findByPageQuotaForLn(Ipage ipage, PreGage preGage)
			throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) preGageDao
					.getCountQuotaForLn(preGage) });// 初始化分页类
			preGage.setStartNumAndEndNum(ipage);
			ipage.setResult(preGageDao.findByPageQuotaForLn(preGage));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
}