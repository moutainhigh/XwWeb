package  app.creditapp.corp.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.corp.entity.CorpFin;
/**
* Title: CorpFinDao.java
* Description:
**/
public interface CorpFinDao {

	public CorpFin getById(CorpFin corpFin) throws DAOException;

	public void del(CorpFin corpFin) throws DAOException;

	public void insert(CorpFin corpFin) throws DAOException;

	public void update(CorpFin corpFin) throws DAOException;
	
	public int getCount(CorpFin corpFin) throws DAOException;

	public List<CorpFin > findByPage(CorpFin corpFin) throws DAOException;

	public int getCountQuotaForCorp(CorpFin corpFin)throws DAOException;

	public List<CorpFin> findByPageQuotaForCorp(CorpFin corpFin)throws DAOException;

	public String getKey()throws DAOException;

}