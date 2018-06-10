package  app.creditapp.aft.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.aft.entity.AftRewPact;
/**
* Title: AftRewPactDao.java
* Description:
**/
public interface AftRewPactDao {

	public AftRewPact getById(AftRewPact aftRewPact) throws DAOException;

	public void del(AftRewPact aftRewPact) throws DAOException;

	public void insert(AftRewPact aftRewPact) throws DAOException;
	//∑÷’À ß∞‹≤Â»ÎaftRewPact±Ì
	public void insertForSplit(AftRewPact aftRewPact) throws DAOException;

	public void update(AftRewPact aftRewPact) throws DAOException;
	
	public int getCount(AftRewPact aftRewPact) throws DAOException;

	public List<AftRewPact > findByPage(AftRewPact aftRewPact) throws DAOException;
	
	public List<AftRewPact> findToList(AftRewPact aftRewPact) throws DAOException;
	public List<AftRewPact> findPactCollect(AftRewPact aftRewPact)throws DAOException;
	public List<AftRewPact> findPactCollectBetween(Map<String, String> paramMap)throws DAOException;

	public void updateSts(AftRewPact aftRewPact)throws DAOException;

	public void updateDealRest(AftRewPact aftRewPact)throws DAOException;

	public List<AftRewPact> findByDateBetween(Map<String, String> paramMap);

	public String getRewNo(String RewName)throws DAOException;
	
	public AftRewPact getByPactNo(AftRewPact aftRewPact) throws DAOException;

}