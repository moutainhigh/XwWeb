package  app.creditapp.ln.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.ln.bo.LnApplyBo;
import app.creditapp.ln.dao.LnApplyDao;
import app.creditapp.ln.entity.LnApply;
import app.util.toolkit.Ipage;
/**
* Title: LnApplyBoImplImpl.java
* Description:
**/
public class LnApplyBoImpl extends BaseService implements LnApplyBo {

	private LnApplyDao lnApplyDao;

	public void del(LnApply lnApply) throws ServiceException {
		try {
			lnApplyDao.del(lnApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnApply lnApply)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnApplyDao
					.getCount(lnApply) });// 初始化分页类
			lnApply.setStartNumAndEndNum (ipg);
			ipg.setResult(lnApplyDao.findByPage(lnApply));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public LnApply getById(LnApply lnApply) throws ServiceException {
		try {
			lnApply = lnApplyDao.getById(lnApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnApply;
	}

	public void insert(LnApply lnApply) throws ServiceException {
		try {
			/**
			 * 新增申请信息的申请ID是序列
			 */
			lnApply.setAppId(lnApplyDao.getKey());
			lnApplyDao.insert(lnApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnApply lnApply) throws ServiceException {
		try {
			lnApplyDao.update(lnApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	//插入自动审批returnId
	public int resultIdUpdate(LnApply lnApply) throws ServiceException{
		int result = 0;
		try {
			result = lnApplyDao.resultIdUpdate(lnApply);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return result;
	}
	public LnApplyDao getLnApplyDao() {
		return lnApplyDao;
	}

	public void setLnApplyDao(LnApplyDao lnApplyDao) {
		this.lnApplyDao = lnApplyDao;
	}
}