package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundProvService;
/**
* Title: FundProvServiceDao.java
* Description:
**/
public interface FundProvServiceDao {

	public FundProvService getById(FundProvService fundProvService) throws DAOException;

	public void del(FundProvService fundProvService) throws DAOException;

	public void insert(FundProvService fundProvService) throws DAOException;

	public void update(FundProvService fundProvService) throws DAOException;
	
	public int getCount(FundProvService fundProvService) throws DAOException;

	public List<FundProvService > findByPage(FundProvService fundProvService) throws DAOException;

}