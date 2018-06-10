package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcReplanFormula;
/**
* Title: AcReplanFormulaDao.java
* Description:
**/
public interface AcReplanFormulaDao {

	public AcReplanFormula getById(AcReplanFormula acReplanFormula) throws DAOException;

	public void del(AcReplanFormula acReplanFormula) throws DAOException;

	public void insert(AcReplanFormula acReplanFormula) throws DAOException;

	public void update(AcReplanFormula acReplanFormula) throws DAOException;
	
	public int getCount(AcReplanFormula acReplanFormula) throws DAOException;

	public List<AcReplanFormula > findByPage(AcReplanFormula acReplanFormula) throws DAOException;

}