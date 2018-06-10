package  app.creditapp.acc.loan.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.loan.entity.AcDebitSusp;
/**
* Title: AcDebitSuspDao.java
* Description:
**/
public interface AcDebitSuspDao {

	public AcDebitSusp getById(AcDebitSusp acDebitSusp) throws DAOException;
	
	public AcDebitSusp getBySerialNo(AcDebitSusp acDebitSusp) throws DAOException;


	public void del(AcDebitSusp acDebitSusp) throws DAOException;

	public void insert(AcDebitSusp acDebitSusp) throws DAOException;

	public void update(AcDebitSusp acDebitSusp) throws DAOException;
	
	public int getCount(AcDebitSusp acDebitSusp) throws DAOException;

	public List<AcDebitSusp > findByPage(AcDebitSusp acDebitSusp) throws DAOException;

}