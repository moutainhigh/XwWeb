package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundSplitDetail;
/**
* Title: FundSplitDetailDao.java
* Description:
**/
public interface FundSplitDetailDao {

	public FundSplitDetail getById(FundSplitDetail fundSplitDetail) throws DAOException;

	public void del(FundSplitDetail fundSplitDetail) throws DAOException;

	public void insert(FundSplitDetail fundSplitDetail) throws DAOException;

	public void update(FundSplitDetail fundSplitDetail) throws DAOException;
	
	public int getCount(FundSplitDetail fundSplitDetail) throws DAOException;

	public List<FundSplitDetail > findByPage(FundSplitDetail fundSplitDetail) throws DAOException;

}