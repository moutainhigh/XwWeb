package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundSplitTerm;
/**
* Title: FundSplitTermDao.java
* Description:
**/
public interface FundSplitTermDao {

	public FundSplitTerm getById(FundSplitTerm fundSplitTerm) throws DAOException;

	public void del(FundSplitTerm fundSplitTerm) throws DAOException;

	public void insert(FundSplitTerm fundSplitTerm) throws DAOException;

	public void update(FundSplitTerm fundSplitTerm) throws DAOException;
	
	public int getCount(FundSplitTerm fundSplitTerm) throws DAOException;

	public List<FundSplitTerm > findByPage(FundSplitTerm fundSplitTerm) throws DAOException;
	//获取是否存在未到期的存续段
	public FundSplitTerm getByTxdate(FundSplitTerm fundSplitTerm) throws DAOException;

}