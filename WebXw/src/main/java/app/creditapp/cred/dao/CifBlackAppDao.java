package  app.creditapp.cred.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cred.entity.CifBlackApp;
/**
* Title: CifBlackAppDao.java
* Description:
**/
public interface CifBlackAppDao {

	public CifBlackApp getById(CifBlackApp cifBlackApp) throws DAOException;

	public void del(CifBlackApp cifBlackApp) throws DAOException;

	public void insert(CifBlackApp cifBlackApp) throws DAOException;

	public void update(CifBlackApp cifBlackApp) throws DAOException;
	
	public int getCount(CifBlackApp cifBlackApp) throws DAOException;

	public List<CifBlackApp > findByPage(CifBlackApp cifBlackApp) throws DAOException;

	public String getAppKey()throws DAOException;

}