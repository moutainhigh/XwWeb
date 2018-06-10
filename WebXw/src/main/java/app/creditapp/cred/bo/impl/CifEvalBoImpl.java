package  app.creditapp.cred.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.cred.bo.CifEvalBo;
import app.creditapp.cred.dao.CifEvalDao;
import app.creditapp.cred.entity.CifEval;
/**
* Title: CifEvalBoImplImpl.java
* Description:
**/
public class CifEvalBoImpl extends BaseService implements CifEvalBo {

	private CifEvalDao cifEvalDao;

	public void del(CifEval cifEval) throws ServiceException {
		try {
			cifEvalDao.del(cifEval);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CifEval cifEval)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifEvalDao
					.getCount(cifEval) });// 初始化分页类
			cifEval.setStartNumAndEndNum (ipg);
			ipg.setResult(cifEvalDao.findByPage(cifEval));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CifEval getById(CifEval cifEval) throws ServiceException {
		try {
			cifEval = cifEvalDao.getById(cifEval);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifEval;
	}
	//获取最新评分信息
	public CifEval getByMaxId(CifEval cifEval) throws ServiceException{
		try {
			cifEval = cifEvalDao.getByMaxId(cifEval);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifEval;
	}
	public void insert(CifEval cifEval) throws ServiceException {
		try {
			/**
			 * 新增客户评级信息的ID号是序列
			 */
			cifEval.setEvalNo(cifEvalDao.getKey());
			cifEvalDao.insert(cifEval);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CifEval cifEval) throws ServiceException {
		try {
			cifEvalDao.update(cifEval);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public int cifScoreUpdate(CifEval cifEval) throws ServiceException {
		int result = 0;
		try {
			result = cifEvalDao.cifScoreUpdate(cifEval);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return result;
	}

	public CifEvalDao getCifEvalDao() {
		return cifEvalDao;
	}

	public void setCifEvalDao(CifEvalDao cifEvalDao) {
		this.cifEvalDao = cifEvalDao;
	}
}