package  app.creditapp.cif.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cif.entity.CifPersInf;
/**
* Title: CifPersDao.java
* Description:
**/
public interface CifPersInfDao {

	public CifPersInf getById(CifPersInf cifPers) throws DAOException;

	public void del(CifPersInf cifPers) throws DAOException;

	public void insert(CifPersInf cifPers) throws DAOException;

	public void update(CifPersInf cifPers) throws DAOException;
	
	public int getCount(CifPersInf cifPers) throws DAOException;

	public List<CifPersInf > findByPage(CifPersInf cifPers) throws DAOException;

	public String getKey()throws DAOException;

}