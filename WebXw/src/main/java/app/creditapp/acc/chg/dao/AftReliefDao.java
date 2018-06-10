package  app.creditapp.acc.chg.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.chg.entity.AftRelief;
/**
* Title: AftReliefDao.java
* Description:
**/
public interface AftReliefDao {

	public AftRelief getById(AftRelief aftRelief) throws DAOException;
	public AftRelief getByIdForTrace(AftRelief aftRelief) throws DAOException;

	public void del(AftRelief aftRelief) throws DAOException;

	public void insert(AftRelief aftRelief) throws DAOException;

	public void update(AftRelief aftRelief) throws DAOException;
	
	public int getCount(AftRelief aftRelief) throws DAOException;

	public int getCountForTask(AftRelief aftRelief) throws DAOException;

	public List<AftRelief > findByPage(AftRelief aftRelief) throws DAOException;

	public List<AftRelief > findByPageForTask(AftRelief aftRelief) throws DAOException;

	public String getRefId() throws DAOException;

}