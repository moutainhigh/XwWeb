package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.ParmKey;
/**
* Title: ParmKeyDao.java
* Description:
**/
public interface ParmKeyDao {

	public ParmKey getById(ParmKey ParmKey) throws DAOException;

	public void del(ParmKey ParmKey) throws DAOException;

	public void insert(ParmKey ParmKey) throws DAOException;

	public void update(ParmKey ParmKey) throws DAOException;
	
	public int getCount(ParmKey ParmKey) throws DAOException;

	public List<ParmKey > findByPage(ParmKey ParmKey) throws DAOException;

}