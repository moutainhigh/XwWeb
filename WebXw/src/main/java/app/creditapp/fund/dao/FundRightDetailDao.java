package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundRightDetail;
/**
* Title: FundRightDetailDao.java
* Description:
**/
public interface FundRightDetailDao {

	public FundRightDetail getById(FundRightDetail fundRightDetail) throws DAOException;

	public void del(FundRightDetail fundRightDetail) throws DAOException;

	public void insert(FundRightDetail fundRightDetail) throws DAOException;

	public void update(FundRightDetail fundRightDetail) throws DAOException;
	
	public int getCount(FundRightDetail fundRightDetail) throws DAOException;

	public List<FundRightDetail > findByPage(FundRightDetail fundRightDetail) throws DAOException;

}