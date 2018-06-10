package  app.creditapp.cif.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cif.entity.CifAcct;
/**
* Title: CifAcctDao.java
* Description:
**/
public interface CifAcctDao {

	public CifAcct getById(CifAcct cifAcct) throws DAOException;

	public void del(CifAcct cifAcct) throws DAOException;

	public void insert(CifAcct cifAcct) throws DAOException;

	public void update(CifAcct cifAcct) throws DAOException;
	
	public int getCount(CifAcct cifAcct) throws DAOException;

	public List<CifAcct > findByPage(CifAcct cifAcct) throws DAOException;

	public int getCountQuotaForCif(CifAcct cifAcct) throws DAOException;

	public List<CifAcct> findByPageQuotaForCif(CifAcct cifAcct) throws DAOException;

}