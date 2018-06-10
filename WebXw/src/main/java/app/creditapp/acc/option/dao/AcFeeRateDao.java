package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcFeeRate;
/**
* Title: AcFeeRateDao.java
* Description:
**/
public interface AcFeeRateDao {

	public AcFeeRate getById(AcFeeRate acFeeRate) throws DAOException;

	public void del(AcFeeRate acFeeRate) throws DAOException;

	public void insert(AcFeeRate acFeeRate) throws DAOException;

	public void update(AcFeeRate acFeeRate) throws DAOException;
	
	public int getCount(AcFeeRate acFeeRate) throws DAOException;

	public List<AcFeeRate > findByPage(AcFeeRate acFeeRate) throws DAOException;

}