package  app.creditapp.corp.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.corp.entity.CorpCont;
/**
* Title: CorpContDao.java
* Description:
**/
public interface CorpContDao {

	public CorpCont getById(CorpCont corpCont) throws DAOException;

	public void del(CorpCont corpCont) throws DAOException;

	public void insert(CorpCont corpCont) throws DAOException;

	public void update(CorpCont corpCont) throws DAOException;
	
	public int getCount(CorpCont corpCont) throws DAOException;

	public List<CorpCont > findByPage(CorpCont corpCont) throws DAOException;

	public int getCountQuotaForCorp(CorpCont corpCont)throws DAOException;

	public List<CorpCont> findByPageQuotaForCorp(CorpCont corpCont)throws DAOException;

	public String getKey()throws DAOException;

}