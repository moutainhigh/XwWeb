package  app.creditapp.aft.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.aft.entity.AftRewDeal;
/**
* Title: AftRewDealDao.java
* Description:
**/
public interface AftRewDealDao {

	public AftRewDeal getById(AftRewDeal aftRewDeal) throws DAOException;

	public void del(AftRewDeal aftRewDeal) throws DAOException;

	public void insert(AftRewDeal aftRewDeal) throws DAOException;

	public void update(AftRewDeal aftRewDeal) throws DAOException;
	
	public int getCount(AftRewDeal aftRewDeal) throws DAOException;

	public List<AftRewDeal > findByPage(AftRewDeal aftRewDeal) throws DAOException;

}