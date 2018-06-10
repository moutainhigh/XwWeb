package  app.creditapp.proj.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.proj.entity.ProjAcctLst;
/**
* Title: ProjAcctLstDao.java
* Description:
**/
public interface ProjAcctLstDao {

	public ProjAcctLst getById(ProjAcctLst projAcctLst) throws DAOException;

	public void del(ProjAcctLst projAcctLst) throws DAOException;

	public void insert(ProjAcctLst projAcctLst) throws DAOException;

	public void update(ProjAcctLst projAcctLst) throws DAOException;
	
	public int getCount(ProjAcctLst projAcctLst) throws DAOException;

	public List<ProjAcctLst > findByPage(ProjAcctLst projAcctLst) throws DAOException;

	public int getCountQuotaForCorp(ProjAcctLst projAcctLst);

	public List<ProjAcctLst> findByPageQuotaForCorp(ProjAcctLst projAcctLst);

}