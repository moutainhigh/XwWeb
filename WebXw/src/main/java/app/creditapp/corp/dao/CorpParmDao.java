package  app.creditapp.corp.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.corp.entity.CorpParm;
/**
* Title: CorpParmDao.java
* Description:
**/
public interface CorpParmDao {

	public CorpParm getById(CorpParm corpParm) throws DAOException;

	public void del(CorpParm corpParm) throws DAOException;

	public void insert(CorpParm corpParm) throws DAOException;

	public void update(CorpParm corpParm) throws DAOException;
	
	public int getCount(CorpParm corpParm) throws DAOException;

	public List<CorpParm > findByPage(CorpParm corpParm) throws DAOException;

	public int getCountQuotaForCorp(CorpParm corpParm)throws DAOException;

	public List<CorpParm> findByPageQuotaForCorp(CorpParm corpParm)throws DAOException;

	public String getKey()throws DAOException;
	
	public List<Object> findListBySts(CorpParm corpParm) throws DAOException;
	public void delForBase(CorpParm corpParm) throws DAOException;

}