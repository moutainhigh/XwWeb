package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundProv;
/**
* Title: FundProvDao.java
* Description:
**/
public interface FundProvDao {

	public FundProv getById(FundProv fundProv) throws DAOException;

	public void del(FundProv fundProv) throws DAOException;

	public void insert(FundProv fundProv) throws DAOException;

	public void update(FundProv fundProv) throws DAOException;
	
	public int getCount(FundProv fundProv) throws DAOException;

	public List<FundProv > findByPage(FundProv fundProv) throws DAOException;

}