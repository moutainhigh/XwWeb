package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcDamFormula;
/**
* Title: AcDamFormulaDao.java
* Description:
**/
public interface AcDamFormulaDao {

	public AcDamFormula getById(AcDamFormula acDamFormula) throws DAOException;

	public void del(AcDamFormula acDamFormula) throws DAOException;

	public void insert(AcDamFormula acDamFormula) throws DAOException;

	public void update(AcDamFormula acDamFormula) throws DAOException;
	
	public void updateSts(AcDamFormula acDamFormula) throws DAOException;

	public int getCount(AcDamFormula acDamFormula) throws DAOException;

	public List<AcDamFormula > findByPage(AcDamFormula acDamFormula) throws DAOException;

}