package  app.creditapp.ln.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.ln.bo.LnApplyRegBo;
import app.creditapp.ln.dao.LnApplyRegDao;
import app.creditapp.ln.entity.LnApplyReg;
import app.util.toolkit.Ipage;
/**
* Title: LnApplyRegBoImplImpl.java
* Description:
**/
public class LnApplyRegBoImpl extends BaseService implements LnApplyRegBo {

	private LnApplyRegDao lnApplyRegDao;
    
	public void del(LnApplyReg lnApplyReg) throws ServiceException {
		try {
			lnApplyRegDao.del(lnApplyReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}



	public Ipage findByPage(Ipage ipg, LnApplyReg lnApplyReg)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnApplyRegDao
					.getCount(lnApplyReg) });// 初始化分页类
			lnApplyReg.setStartNumAndEndNum (ipg);
			ipg.setResult(lnApplyRegDao.findByPage(lnApplyReg));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public LnApplyReg getById(LnApplyReg lnApplyReg) throws ServiceException {
		try {
			lnApplyReg = lnApplyRegDao.getById(lnApplyReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnApplyReg;
	}

	public void insert(LnApplyReg lnApplyReg) throws ServiceException {
		try {
			
			/**
			 * 新增进件申请信息的申请ID是序列
			 */
			lnApplyReg.setAppId(lnApplyRegDao.getKey());
			lnApplyRegDao.insert(lnApplyReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnApplyReg lnApplyReg) throws ServiceException {
		try {
			lnApplyRegDao.update(lnApplyReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public int resultIdUpdate(LnApplyReg lnApplyReg) throws ServiceException{
		int result = 0;
		try {
			result = lnApplyRegDao.resultIdUpdate(lnApplyReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return result;
	}
	
	public LnApplyRegDao getLnApplyRegDao() {
		return lnApplyRegDao;
	}

	public void setLnApplyRegDao(LnApplyRegDao lnApplyRegDao) {
		this.lnApplyRegDao = lnApplyRegDao;
	}

	@Override
	public String getByNo(LnApplyReg lnApplyReg)
			throws ServiceException {
		// TODO Auto-generated method stub
		String t = null;
		try {
			t = lnApplyRegDao.getByNo(lnApplyReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return t;
		
	}



	@Override
	public LnApplyReg getBypactNo(LnApplyReg lnApplyReg)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			lnApplyReg = lnApplyRegDao.getBypactNo(lnApplyReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnApplyReg;
	
		
	}



	@Override
	public void updateBypactNo(LnApplyReg lnApplyReg) {
		// TODO Auto-generated method stub
		try {
			lnApplyRegDao.updateBypactNo(lnApplyReg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}