package  app.creditapp.cif.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cif.entity.CifPersCareer;
/**
* Title: CifPersCareerDao.java
* Description:
**/
public interface CifPersCareerDao {

	public CifPersCareer getById(CifPersCareer cifPersCareer) throws DAOException;

	public void del(CifPersCareer cifPersCareer) throws DAOException;

	public void insert(CifPersCareer cifPersCareer) throws DAOException;

	public void update(CifPersCareer cifPersCareer) throws DAOException;
	
	public int getCount(CifPersCareer cifPersCareer) throws DAOException;

	public List<CifPersCareer > findByPage(CifPersCareer cifPersCareer) throws DAOException;

	public int getCountQuotaForCif(CifPersCareer cifPersCareer) throws DAOException;

	public List<CifPersCareer> findByPageQuotaForCif(CifPersCareer cifPersCareer) throws DAOException;

	public CifPersCareer findForCif(CifPersCareer cifPersCareer) throws DAOException;

	public String getKey() throws DAOException;

}