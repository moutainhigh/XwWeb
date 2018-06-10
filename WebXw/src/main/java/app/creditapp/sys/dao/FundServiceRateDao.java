package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.FundServiceRate;
/**
* Title: FundServiceRateDao.java
* Description:
**/
public interface FundServiceRateDao {

	public FundServiceRate getById(FundServiceRate fundServiceRate) throws DAOException;
	
	public FundServiceRate getByProjAndMinamt(FundServiceRate fundServiceRate) throws DAOException;
	
	public void del(FundServiceRate fundServiceRate) throws DAOException;

	public void insert(FundServiceRate fundServiceRate) throws DAOException;

	public void update(FundServiceRate fundServiceRate) throws DAOException;
	
	public int getCount(FundServiceRate fundServiceRate) throws DAOException;

	public List<FundServiceRate > findByPage(FundServiceRate fundServiceRate) throws DAOException;

}