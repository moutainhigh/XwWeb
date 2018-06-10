package  app.creditapp.cif.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cif.entity.CifBlack;
import app.creditapp.pact.entity.LnPact;
/**
* Title: CifBlackDao.java
* Description:
**/
public interface CifBlackDao {

	public CifBlack getById(CifBlack cifBlack) throws DAOException;

	public void del(CifBlack cifBlack) throws DAOException;

	public void insert(CifBlack cifBlack) throws DAOException;

	public void update(CifBlack cifBlack) throws DAOException;
	
	public int getCount(CifBlack cifBlack) throws DAOException;
	
	public int getCountForTask(CifBlack cifBlack) throws DAOException;

	public int getCountForAppr(CifBlack cifBlack) throws DAOException;

	public List<CifBlack > findByPage(CifBlack cifBlack) throws DAOException;
	
	public List<CifBlack > findByPageForTask(CifBlack cifBlack) throws DAOException;

	public List<CifBlack > findByPageForAppr(CifBlack cifBlack) throws DAOException;

	public String getKey()throws DAOException;
	

}