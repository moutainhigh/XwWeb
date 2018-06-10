package  app.creditapp.aft.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.aft.entity.AftAdvpay;
/**
* Title: AftAdvpayDao.java
* Description:
**/
public interface AftAdvpayDao {

	public AftAdvpay getById(AftAdvpay aftAdvpay) throws DAOException;
	public AftAdvpay getByIdForTrace(AftAdvpay aftAdvpay) throws DAOException;

	public void del(AftAdvpay aftAdvpay) throws DAOException;

	public void insert(AftAdvpay aftAdvpay) throws DAOException;

	public void update(AftAdvpay aftAdvpay) throws DAOException;
	
	public int getCount(AftAdvpay aftAdvpay) throws DAOException;
	
	public int getSucByTxDate(AftAdvpay aftAdvpay) throws DAOException;

	public List<AftAdvpay > findByPage(AftAdvpay aftAdvpay) throws DAOException;

	public String getKey()throws DAOException;

}