package  app.creditapp.acc.option.bo.impl;
import java.util.HashMap;
import app.base.BaseService;
import app.base.ServiceException;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;
import app.creditapp.acc.option.bo.AcFineFormulaBo;
import app.creditapp.acc.option.dao.AcFineFormulaDao;
import app.creditapp.acc.option.entity.AcFineFormula;
/**
* Title: AcFineFormulaBoImplImpl.java
* Description:
**/
public class AcFineFormulaBoImpl extends BaseService implements AcFineFormulaBo {

	private AcFineFormulaDao acFineFormulaDao;

	public void del(AcFineFormula acFineFormula) throws ServiceException {
		try {
			acFineFormulaDao.del(acFineFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, AcFineFormula acFineFormula)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) acFineFormulaDao
					.getCount(acFineFormula) });// 初始化分页类
			acFineFormula.setStartNumAndEndNum (ipg);
			ipg.setResult(acFineFormulaDao.findByPage(acFineFormula));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public AcFineFormula getById(AcFineFormula acFineFormula) throws ServiceException {
		try {
			acFineFormula = acFineFormulaDao.getById(acFineFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return acFineFormula;
	}

	public void insert(AcFineFormula acFineFormula) throws ServiceException {
		try {
			acFineFormulaDao.insert(acFineFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(AcFineFormula acFineFormula) throws ServiceException {
		try {
			acFineFormulaDao.update(acFineFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updateReplanSts(AcFineFormula acFineFormula) throws ServiceException {
		try {
			acFineFormulaDao.updateReplanSts(acFineFormula);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public AcFineFormulaDao getAcFineFormulaDao() {
		return acFineFormulaDao;
	}

	public void setAcFineFormulaDao(AcFineFormulaDao acFineFormulaDao) {
		this.acFineFormulaDao = acFineFormulaDao;
	}
}