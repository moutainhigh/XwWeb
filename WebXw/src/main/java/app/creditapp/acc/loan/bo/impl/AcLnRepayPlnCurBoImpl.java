package  app.creditapp.acc.loan.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.loan.bo.AcLnRepayPlnCurBo;
import app.creditapp.acc.loan.dao.AcLnRepayPlnCurDao;
import app.creditapp.acc.loan.entity.AcLnRepayPlnCur;
/**
* Title: AcLnRepayPlnCurBoImplImpl.java
* Description:
**/
public class AcLnRepayPlnCurBoImpl extends BaseService implements AcLnRepayPlnCurBo {

	private AcLnRepayPlnCurDao acLnRepayPlnCurDao;

	public void del(AcLnRepayPlnCur acLnRepayPlnCur) throws ServiceException {
		try {
			acLnRepayPlnCurDao.del(acLnRepayPlnCur);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	//根据合同号查 当期还款计划表中的应收本金-已还本金
//	public int pactAmt_repayPactAmt(AcLnRepayPlnCur acLnRepayPlnCur) throws ServiceException {
//		int count =0;
//		try {
//			count = acLnRepayPlnCurDao.pactAmt_repayPactAmt(acLnRepayPlnCur);
//		} catch (Exception e) {
//			throw new ServiceException(e.getMessage());
//		}
//		return count;
//	}

	public Ipage findByPage(Ipage ipg, AcLnRepayPlnCur acLnRepayPlnCur)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acLnRepayPlnCurDao
					.getCount(acLnRepayPlnCur) });// 初始化分页类
			acLnRepayPlnCur.setStartNumAndEndNum (ipg);
			ipg.setResult(acLnRepayPlnCurDao.findByPage(acLnRepayPlnCur));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcLnRepayPlnCur getById(AcLnRepayPlnCur acLnRepayPlnCur) throws ServiceException {
		try {
			acLnRepayPlnCur = acLnRepayPlnCurDao.getById(acLnRepayPlnCur);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acLnRepayPlnCur;
	}

	public void insert(AcLnRepayPlnCur acLnRepayPlnCur) throws ServiceException {
		try {
			acLnRepayPlnCurDao.insert(acLnRepayPlnCur);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcLnRepayPlnCur acLnRepayPlnCur) throws ServiceException {
		try {
			acLnRepayPlnCurDao.update(acLnRepayPlnCur);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcLnRepayPlnCurDao getAcLnRepayPlnCurDao() {
		return acLnRepayPlnCurDao;
	}

	public void setAcLnRepayPlnCurDao(AcLnRepayPlnCurDao acLnRepayPlnCurDao) {
		this.acLnRepayPlnCurDao = acLnRepayPlnCurDao;
	}

	@Override
	public AcLnRepayPlnCur getCurByCnt(AcLnRepayPlnCur acLnRepayPlnCur) {
		return acLnRepayPlnCurDao.getCurByCnt(acLnRepayPlnCur);
	}
}