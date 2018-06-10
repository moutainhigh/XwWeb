package  app.creditapp.acc.option.dao;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.acc.option.entity.AcFineFormula;
/**
* Title: AcFineFormulaDao.java
* Description:
**/
public interface AcFineFormulaDao {

	public AcFineFormula getById(AcFineFormula acFineFormula) throws DAOException;

	public void del(AcFineFormula acFineFormula) throws DAOException;

	public void insert(AcFineFormula acFineFormula) throws DAOException;

	public void update(AcFineFormula acFineFormula) throws DAOException;
	
	public void updateReplanSts(AcFineFormula acFineFormula) throws DAOException;

	
	public int getCount(AcFineFormula acFineFormula) throws DAOException;

	public List<AcFineFormula > findByPage(AcFineFormula acFineFormula) throws DAOException;

}