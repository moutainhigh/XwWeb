package  app.creditapp.fund.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.fund.entity.FundSplit;
/**
* Title: FundSplitDao.java
**/
public interface FundSplitDao {

	public FundSplit getById(FundSplit fundSplit) throws DAOException;

	public void del(FundSplit fundSplit) throws DAOException;

	public void insert(FundSplit fundSplit) throws DAOException;

	public void update(FundSplit fundSplit) throws DAOException;
	
	public int getCount(FundSplit fundSplit) throws DAOException;

	public List<FundSplit > findByPage(FundSplit fundSplit) throws DAOException;
	
	public List<FundSplit > echarts(FundSplit fundSplit) throws DAOException;

}