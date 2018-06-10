package  app.creditapp.cif.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cif.entity.CifPersHis;
/**
* Title: CifPersHisDao.java
* Description:
**/
public interface CifPersHisDao {

	public CifPersHis getById(CifPersHis cifPersHis) throws DAOException;

	public void del(CifPersHis cifPersHis) throws DAOException;

	public void insert(CifPersHis cifPersHis) throws DAOException;

	public void update(CifPersHis cifPersHis) throws DAOException;
	
	public int getCount(CifPersHis cifPersHis) throws DAOException;

	public List<CifPersHis > findByPage(CifPersHis cifPersHis) throws DAOException;

	public int getCountQuotaForCif(CifPersHis cifPersHis) throws DAOException;

	public List<CifPersHis> findByPageQuotaForCif(CifPersHis cifPersHis) throws DAOException;

	public String getSeqKey() throws DAOException;

}