package  app.creditapp.aft.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.aft.entity.AftRewFund;
/**
* Title: AftRewFundDao.java
**/
public interface AftRewFundDao {

	public AftRewFund getById(AftRewFund aftRewFund) throws DAOException;

	public void del(AftRewFund aftRewFund) throws DAOException;

	public void insert(AftRewFund aftRewFund) throws DAOException;

	public void update(AftRewFund aftRewFund) throws DAOException;
	
	public int getCount(AftRewFund aftRewFund) throws DAOException;

	public List<AftRewFund > findByPage(AftRewFund aftRewFund) throws DAOException;
	public List<AftRewFund > findByList(AftRewFund aftRewFund) throws DAOException;

	public void updateSts(AftRewFund aftRewFund) throws DAOException;

	public void updateDealRest(AftRewFund aftRewFund)throws DAOException;

	public List<AftRewFund> findByDateBetween(Map<String, String> paramMap)throws DAOException;

}