package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcFeeFormula;
/**
* Title: AcFeeFormulaDao.java
* Description:
**/
public interface AcFeeFormulaDao {

	public AcFeeFormula getById(AcFeeFormula acFeeFormula) throws DAOException;

	public void del(AcFeeFormula acFeeFormula) throws DAOException;

	public void insert(AcFeeFormula acFeeFormula) throws DAOException;

	public void update(AcFeeFormula acFeeFormula) throws DAOException;
	
	public int getCount(AcFeeFormula acFeeFormula) throws DAOException;

	public List<AcFeeFormula > findByPage(AcFeeFormula acFeeFormula) throws DAOException;

}