package  app.creditapp.aft.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.aft.entity.AftRewReal;
/**
* Title: AftRewRealDao.java
* Description:
**/
public interface AftRewRealDao {

	public AftRewReal getById(AftRewReal aftRewReal) throws DAOException;

	public void del(AftRewReal aftRewReal) throws DAOException;

	public void insert(AftRewReal aftRewReal) throws DAOException;

	public void update(AftRewReal aftRewReal) throws DAOException;
	
	public List<AftRewReal > findByList(AftRewReal aftRewReal) throws DAOException;
	
	public int getCount(AftRewReal aftRewReal) throws DAOException;

	public void updateSts(AftRewReal aftRewReal) throws DAOException;
	
	public void updateDealRest(AftRewReal aftRewReal)throws DAOException;
	
	public List<AftRewReal > findByPage(AftRewReal aftRewReal) throws DAOException;
	
	public List<AftRewReal> findByDateBetween(Map<String, String> paramMap)throws DAOException;

}