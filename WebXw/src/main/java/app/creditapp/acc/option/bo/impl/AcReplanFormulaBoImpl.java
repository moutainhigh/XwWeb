package  app.creditapp.acc.option.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcReplanFormulaBo;
import app.creditapp.acc.option.dao.AcReplanFormulaDao;
import app.creditapp.acc.option.entity.AcReplanFormula;
/**
* Title: AcReplanFormulaBoImplImpl.java
* Description:
**/
public class AcReplanFormulaBoImpl extends BaseService implements AcReplanFormulaBo {

	private AcReplanFormulaDao acReplanFormulaDao;

	public void del(AcReplanFormula acReplanFormula) throws ServiceException {
		try {
			acReplanFormulaDao.del(acReplanFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcReplanFormula acReplanFormula)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acReplanFormulaDao
					.getCount(acReplanFormula) });// 初始化分页类
			acReplanFormula.setStartNumAndEndNum (ipg);
			ipg.setResult(acReplanFormulaDao.findByPage(acReplanFormula));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcReplanFormula getById(AcReplanFormula acReplanFormula) throws ServiceException {
		try {
			acReplanFormula = acReplanFormulaDao.getById(acReplanFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acReplanFormula;
	}

	public void insert(AcReplanFormula acReplanFormula) throws ServiceException {
		try {
			acReplanFormulaDao.insert(acReplanFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcReplanFormula acReplanFormula) throws ServiceException {
		try {
			acReplanFormulaDao.update(acReplanFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcReplanFormulaDao getAcReplanFormulaDao() {
		return acReplanFormulaDao;
	}

	public void setAcReplanFormulaDao(AcReplanFormulaDao acReplanFormulaDao) {
		this.acReplanFormulaDao = acReplanFormulaDao;
	}
}