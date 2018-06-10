package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcReplanParm;
/**
* Title: AcReplanParmDao.java
* Description:
**/
public interface AcReplanParmDao {

	public AcReplanParm getById(AcReplanParm acReplanParm) throws DAOException;

	public void del(AcReplanParm acReplanParm) throws DAOException;

	public void insert(AcReplanParm acReplanParm) throws DAOException;

	public void update(AcReplanParm acReplanParm) throws DAOException;
	
	public void updateReplanSts(AcReplanParm acReplanParm) throws DAOException;

	public int getCount(AcReplanParm acReplanParm) throws DAOException;

	public List<AcReplanParm > findByPage(AcReplanParm acReplanParm) throws DAOException;

}