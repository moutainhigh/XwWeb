package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcFeeParm;
/**
* Title: AcFeeParmDao.java
* Description:
**/
public interface AcFeeParmDao {

	public AcFeeParm getById(AcFeeParm acFeeParm) throws DAOException;

	public void del(AcFeeParm acFeeParm) throws DAOException;

	public void insert(AcFeeParm acFeeParm) throws DAOException;

	public void update(AcFeeParm acFeeParm) throws DAOException;
	
	public void updateSts(AcFeeParm acFeeParm) throws DAOException;

	public int getCount(AcFeeParm acFeeParm) throws DAOException;

	public List<AcFeeParm > findByPage(AcFeeParm acFeeParm) throws DAOException;

}