package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundProvSplitTerm;
/**
* Title: FundProvSplitTermDao.java
* Description:
**/
public interface FundProvSplitTermDao {

	public FundProvSplitTerm getById(FundProvSplitTerm fundProvSplitTerm) throws DAOException;
    
	public FundProvSplitTerm Manager_getById(FundProvSplitTerm fundProvSplitTerm) throws DAOException;
	
	public void del(FundProvSplitTerm fundProvSplitTerm) throws DAOException;

	public void insert(FundProvSplitTerm fundProvSplitTerm) throws DAOException;

	public void update(FundProvSplitTerm fundProvSplitTerm) throws DAOException;
	
	public int getCount(FundProvSplitTerm fundProvSplitTerm) throws DAOException;
	
	public int Manager_getCount(FundProvSplitTerm fundProvSplitTerm) throws DAOException;

	public List<FundProvSplitTerm > findByPage(FundProvSplitTerm fundProvSplitTerm) throws DAOException;
	
	public List<FundProvSplitTerm > Manager_findByPage(FundProvSplitTerm fundProvSplitTerm) throws DAOException;

}