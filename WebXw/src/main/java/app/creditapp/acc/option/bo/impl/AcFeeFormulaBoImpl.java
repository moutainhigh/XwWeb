package  app.creditapp.acc.option.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcFeeFormulaBo;
import app.creditapp.acc.option.dao.AcFeeFormulaDao;
import app.creditapp.acc.option.entity.AcFeeFormula;
/**
* Title: AcFeeFormulaBoImplImpl.java
* Description:
**/
public class AcFeeFormulaBoImpl extends BaseService implements AcFeeFormulaBo {

	private AcFeeFormulaDao acFeeFormulaDao;

	public void del(AcFeeFormula acFeeFormula) throws ServiceException {
		try {
			acFeeFormulaDao.del(acFeeFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcFeeFormula acFeeFormula)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acFeeFormulaDao
					.getCount(acFeeFormula) });// 初始化分页类
			acFeeFormula.setStartNumAndEndNum (ipg);
			ipg.setResult(acFeeFormulaDao.findByPage(acFeeFormula));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcFeeFormula getById(AcFeeFormula acFeeFormula) throws ServiceException {
		try {
			acFeeFormula = acFeeFormulaDao.getById(acFeeFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acFeeFormula;
	}

	public void insert(AcFeeFormula acFeeFormula) throws ServiceException {
		try {
			acFeeFormulaDao.insert(acFeeFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcFeeFormula acFeeFormula) throws ServiceException {
		try {
			acFeeFormulaDao.update(acFeeFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcFeeFormulaDao getAcFeeFormulaDao() {
		return acFeeFormulaDao;
	}

	public void setAcFeeFormulaDao(AcFeeFormulaDao acFeeFormulaDao) {
		this.acFeeFormulaDao = acFeeFormulaDao;
	}
}